/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.investment.service.impl;

import com.techservices.digitalbanking.core.domain.BaseAppResponse;
import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.core.domain.enums.AccountType;
import com.techservices.digitalbanking.core.exception.ValidationException;
import com.techservices.digitalbanking.core.fineract.model.response.*;
import com.techservices.digitalbanking.core.fineract.service.ClientService;
import com.techservices.digitalbanking.core.fineract.service.FixedDepositService;
import com.techservices.digitalbanking.core.fineract.service.RecurringDepositAccountService;
import com.techservices.digitalbanking.customer.service.CustomerService;
import com.techservices.digitalbanking.fixeddeposit.domain.request.FixedDepositCommandRequest;
import com.techservices.digitalbanking.investment.domain.request.FixedDepositApplicationRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.techservices.digitalbanking.investment.service.InvestmentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class InvestmentServiceImpl implements InvestmentService {
	private final FixedDepositService fixedDepositService;
	private final CustomerService customerService;
	private final ClientService clientService;
	private final RecurringDepositAccountService recurringDepositAccountService;

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
		customerService.getCustomerById(customerId);
		if ("lock".equalsIgnoreCase(investmentType)) {
			return fixedDepositService.retrieveInvestmentById(id, staffInSelectedOfficeOnly, chargeStatus, null);
		} else if ("flex".equalsIgnoreCase(investmentType)) {
			return recurringDepositAccountService.retrieveInvestmentById(id, staffInSelectedOfficeOnly, chargeStatus, null);
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
