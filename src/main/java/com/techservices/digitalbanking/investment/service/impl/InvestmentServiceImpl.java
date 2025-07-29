/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.investment.service.impl;

import com.techservices.digitalbanking.core.domain.BaseAppResponse;
import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.core.domain.dto.GenericApiResponse;
import com.techservices.digitalbanking.core.domain.enums.AccountType;
import com.techservices.digitalbanking.core.exception.PlatformServiceException;
import com.techservices.digitalbanking.core.exception.ValidationException;
import com.techservices.digitalbanking.core.fineract.configuration.FineractProperty;
import com.techservices.digitalbanking.core.fineract.model.request.PostAccountTransfersRequest;
import com.techservices.digitalbanking.core.fineract.model.request.PostRecurringDepositAccountsRequest;
import com.techservices.digitalbanking.core.fineract.model.request.PutRecurringDepositProductRequest;
import com.techservices.digitalbanking.core.fineract.model.response.*;
import com.techservices.digitalbanking.core.fineract.service.*;
import com.techservices.digitalbanking.customer.domian.data.model.Customer;
import com.techservices.digitalbanking.customer.service.CustomerService;
import com.techservices.digitalbanking.fixeddeposit.domain.request.FixedDepositCommandRequest;
import com.techservices.digitalbanking.investment.domain.enums.InvestmentType;
import com.techservices.digitalbanking.investment.domain.request.FixedDepositApplicationRequest;
import com.techservices.digitalbanking.investment.domain.request.InvestmentApplicationRequest;
import com.techservices.digitalbanking.investment.domain.request.InvestmentUpdateRequest;
import com.techservices.digitalbanking.investment.domain.response.InvestmentApplicationResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.techservices.digitalbanking.investment.service.InvestmentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Objects;

import static com.techservices.digitalbanking.core.util.CommandUtil.ACTIVATE;
import static com.techservices.digitalbanking.core.util.CommandUtil.APPROVE;
import static com.techservices.digitalbanking.core.util.DateUtil.*;

@Service
@RequiredArgsConstructor
public class InvestmentServiceImpl implements InvestmentService {
	private final FixedDepositService fixedDepositService;
	private final CustomerService customerService;
	private final FineractProperty fineractProperty;
	private final ClientService clientService;
	private final RecurringDepositAccountService recurringDepositAccountService;
	private final RecurringDepositProductService recurringDepositProductService;
	private final AccountService accountService;
	private final AccountTransactionService accountTransactionService;
	private final RecurringDepositTransactionService recurringDepositTransactionService;

	@Override
	public InvestmentApplicationResponse submitApplication(
			Long customerId, InvestmentType investmentType, InvestmentApplicationRequest request) {
		Customer foundCustomer = customerService.getCustomerById(customerId);
        if (investmentType == InvestmentType.FLEX) {
//			This feature is yet to be impl
			throw new PlatformServiceException("feature.not.implemented",
					"Recurring Deposit feature is not yet implemented.");
		} else if (investmentType == InvestmentType.LOCK) {
			FixedDepositApplicationRequest fixedDepositApplicationRequest = new FixedDepositApplicationRequest();
			fixedDepositApplicationRequest.setClientId(Long.valueOf(foundCustomer.getExternalId()));
			fixedDepositApplicationRequest.setLinkAccountId(Long.valueOf(foundCustomer.getAccountId()));
			fixedDepositApplicationRequest.setDepositAmount(request.getAmount());
			fixedDepositApplicationRequest.setDepositPeriod(request.getDepositPeriod());
			fixedDepositApplicationRequest.setAllocationName(request.getAllocationName());
			fixedDepositApplicationRequest.setProductId(fineractProperty.getDefaultFixedDepositProductId());
			return fixedDepositService.submitApplication(fixedDepositApplicationRequest, false);
		} else {
			throw new ValidationException("invalid.investment.type",
					"Invalid investment type provided. Allowed values are 'flex' for Recurring Deposit and 'lock' for Fixed Deposit.");
		}
	}

	@Override
	public BaseAppResponse updateAnInvestment(Long customerId, InvestmentType investmentType, InvestmentUpdateRequest request, String investmentId) {
		customerService.getCustomerById(customerId);
		this.retrieveInvestmentById(Long.valueOf(investmentId), null, null, investmentType.name(), customerId);
		if (investmentType == InvestmentType.FLEX) {
			PutRecurringDepositProductRequest putRecurringDepositProductRequest = new PutRecurringDepositProductRequest();
			if (request.getAmount() != null && request.getAmount().compareTo(BigDecimal.ZERO) > 0) {

			}
			return recurringDepositAccountService.updateAnInvestment(investmentId, putRecurringDepositProductRequest);
		}
		return null;
	}

	@Override
	public BaseAppResponse fundInvestment(Long customerId, InvestmentType investmentType, InvestmentUpdateRequest request, Long investmentId) {
		Customer foundCustomer = customerService.getCustomerById(customerId);
		this.retrieveInvestmentById(investmentId, null, null, investmentType.name(), customerId);
		GetSavingsAccountsAccountIdResponse savingsAccount = accountService.retrieveSavingsAccountById(Long.valueOf(foundCustomer.getAccountId()));
		if (investmentType == InvestmentType.FLEX) {
			if (request == null || request.getAmount() == null || request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
				throw new ValidationException("invalid.amount",
						"Invalid amount provided for funding the investment. Amount must be greater than zero.");
			}
			if (savingsAccount.getAccountBalance().compareTo(request.getAmount()) < 0) {
				throw new ValidationException("insufficient.funds",
						"Insufficient funds in the customer's savings account to create an investment.");
			}
			accountTransactionService.handleRecurringDepositAccountTransfer(savingsAccount,
					investmentId, request.getAmount(), "Investment funding");
		} else if (investmentType == InvestmentType.LOCK) {
			GetFixedDepositAccountsAccountIdResponse response = fixedDepositService.retrieveInvestmentById(investmentId, null, null, null);
			if (savingsAccount.getAccountBalance().compareTo(response.getDepositAmount()) < 0) {
				throw new ValidationException("insufficient.funds",
						"Insufficient funds in the customer's savings account to create an investment.");
			}
			try {
				fixedDepositService.processInvestmentCommand(investmentId, null, APPROVE);
				fixedDepositService.processInvestmentCommand(investmentId, null, ACTIVATE);
			} catch (Exception e) {
				throw new PlatformServiceException("investment.funding.failed", "You have already funded this investment or the investment is currently active.", e);
			}
		}
		return GenericApiResponse.builder().message("Flex account has been funded successfully").build();
	}

	@Override
	public PostSavingsAccountsResponse submitApplication(
			FixedDepositApplicationRequest fixedDepositApplicationRequest, @Valid boolean activate) {
		return fixedDepositService.submitApplication(fixedDepositApplicationRequest, activate);
	}

	@Override
	public PostFixedDepositAccountsAccountIdResponse processInvestmentCommand(Long investmentId,
																			  @Valid FixedDepositCommandRequest fixedDepositCommandRequest, String command) {
		return fixedDepositService.processInvestmentCommand(investmentId, fixedDepositCommandRequest,
				command);
	}

	@Override
	public GetFixedDepositAccountsResponse retrieveAllInvestments(Boolean paged, Integer offset,
																  Integer limit, String orderBy, String sortOrder) {
		return fixedDepositService.retrieveAllInvestments(paged, offset, limit, orderBy, sortOrder);
	}

	@Override
	public BaseAppResponse retrieveInvestmentById(Long id, Boolean staffInSelectedOfficeOnly,
												  @Valid String chargeStatus, String investmentType, Long customerId) {
		Customer foundCustomer = customerService.getCustomerById(customerId);
		if ("lock".equalsIgnoreCase(investmentType)) {
			GetFixedDepositAccountsAccountIdResponse response = fixedDepositService.retrieveInvestmentById(id, staffInSelectedOfficeOnly, chargeStatus, null);
			if (!StringUtils.equalsIgnoreCase(foundCustomer.getExternalId(), String.valueOf(response.getClientId()))) {
				throw new ValidationException("investment.not.found",
						"Investment not found for the provided customer ID.");
			}
			return response;
		} else if ("flex".equalsIgnoreCase(investmentType)) {
			GetRecurringDepositAccountsResponse response = recurringDepositAccountService.retrieveInvestmentById(id, staffInSelectedOfficeOnly, chargeStatus, null);
			if (!StringUtils.equalsIgnoreCase(foundCustomer.getExternalId(), String.valueOf(response.getClientId()))) {
				throw new ValidationException("investment.not.found",
						"Investment not found for the provided customer ID.");
			}
			return response;
		} else {
			throw new ValidationException("invalid.investment.type",
					"Invalid investment type provided. Allowed values are 'flex' for Recurring Deposit and 'lock' for Fixed Deposit.");
		}
	}

	@Override
	public Object retrieveTemplate(Long clientId, Long productId) {
		return null;
	}

	@Override
	public BasePageResponse<GetClientsSavingsAccounts> retrieveAllCustomerInvestments(Long customerId, String investmentType) {
		String externalId = this.customerService.getCustomerById(customerId).getExternalId();
		AccountType accountType;
		if (StringUtils.isNotBlank(investmentType)) {
            if (investmentType.equalsIgnoreCase("flex")) {
                accountType = AccountType.RECURRING_DEPOSIT;
            } else if (investmentType.equalsIgnoreCase("lock")) {
                accountType = AccountType.FIXED_DEPOSIT;
            } else {
                throw new ValidationException("invalid.investment.type",
						"Invalid investment type provided. Allowed values are 'flex' for Recurring Deposit and 'lock' for Fixed Deposit.");
			}
		} else {
            accountType = null;
        }
        GetClientsClientIdAccountsResponse customerAccounts = this.clientService.getClientAccountsByClientId(externalId, "savingsAccounts");
		return BasePageResponse.instance(
				accountType == null ? customerAccounts.getSavingsAccounts().stream().filter(account ->
						account != null && account.getDepositType() != null && !Objects.equals(account.getDepositType().getId(), 100L)).toList() :
				customerAccounts.getSavingsAccounts().stream().filter(account ->
						account != null && account.getDepositType() != null && Objects.equals(account.getDepositType().getId(), accountType.getCode())
				).toList()
		);
	}

	@Override
	public Object retrieveInvestmentTransactionsById(Long id, Boolean staffInSelectedOfficeOnly, String chargeStatus, String investmentType, Long customerId) {
		customerService.getCustomerById(customerId);
		if ("lock".equalsIgnoreCase(investmentType)) {
			return BasePageResponse.instance(fixedDepositService.retrieveInvestmentById(id, staffInSelectedOfficeOnly, chargeStatus, "all").getTransactions());
		} else if ("flex".equalsIgnoreCase(investmentType)) {
			return BasePageResponse.instance(recurringDepositAccountService.retrieveInvestmentById(id, staffInSelectedOfficeOnly, chargeStatus, "all").getTransactions());
		} else {
			throw new ValidationException("invalid.investment.type",
					"Invalid investment type provided. Allowed values are 'flex' for Recurring Deposit and 'lock' for Fixed Deposit.");
		}
	}
}
