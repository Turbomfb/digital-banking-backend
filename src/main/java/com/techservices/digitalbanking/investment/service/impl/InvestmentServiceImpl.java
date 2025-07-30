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
import com.techservices.digitalbanking.customer.domian.data.repository.CustomerRepository;
import com.techservices.digitalbanking.customer.service.CustomerService;
import com.techservices.digitalbanking.fixeddeposit.domain.request.FixedDepositCommandRequest;
import com.techservices.digitalbanking.investment.domain.enums.InvestmentType;
import com.techservices.digitalbanking.investment.domain.request.FixedDepositApplicationRequest;
import com.techservices.digitalbanking.investment.domain.request.InvestmentApplicationRequest;
import com.techservices.digitalbanking.investment.domain.request.InvestmentCalculatorRequest;
import com.techservices.digitalbanking.investment.domain.request.InvestmentUpdateRequest;
import com.techservices.digitalbanking.investment.domain.response.InvestmentApplicationResponse;
import com.techservices.digitalbanking.investment.domain.response.InvestmentCalculatorResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.techservices.digitalbanking.investment.service.InvestmentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.techservices.digitalbanking.core.util.CommandUtil.ACTIVATE;
import static com.techservices.digitalbanking.core.util.CommandUtil.APPROVE;
import static com.techservices.digitalbanking.core.util.DateUtil.*;

@Service
@RequiredArgsConstructor
@Slf4j
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
    private final CustomerRepository customerRepository;
    private final FixedDepositProductService fixedDepositProductService;

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
        Customer foundCustomer = customerService.getCustomerById(customerId);
        if (investmentType == InvestmentType.FLEX) {
            PutRecurringDepositProductRequest putRecurringDepositProductRequest = new PutRecurringDepositProductRequest();
            if (request.getAmount() != null && request.getAmount().compareTo(BigDecimal.ZERO) > 0) {
                return recurringDepositAccountService.updateAnInvestment(foundCustomer.getRecurringDepositAccountId(), putRecurringDepositProductRequest);
            }
        }
        this.retrieveInvestmentById(Long.valueOf(investmentId), null, null, investmentType.name(), customerId);
        return null;
    }

    @Override
    public BaseAppResponse fundInvestment(Long customerId, InvestmentType investmentType, InvestmentUpdateRequest request, Long investmentId) {
        Customer foundCustomer = customerService.getCustomerById(customerId);
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
            if (StringUtils.isBlank(foundCustomer.getRecurringDepositAccountId())) {
                PostRecurringDepositAccountsRequest recurringDepositAccountsRequest = new PostRecurringDepositAccountsRequest();
				recurringDepositAccountsRequest.setProductId(fineractProperty.getDefaultRecurringDepositProductId());
				recurringDepositAccountsRequest.setClientId(String.valueOf(foundCustomer.getExternalId()));
				recurringDepositAccountsRequest.setDepositPeriodFrequencyId(0L);
				recurringDepositAccountsRequest.setDepositPeriod(1L);
				recurringDepositAccountsRequest.setMandatoryRecommendedDepositAmount(request.getAmount());
				recurringDepositAccountsRequest.setRecurringFrequency(1L);
				recurringDepositAccountsRequest.setRecurringFrequencyType(0L);
                PostRecurringDepositAccountsResponse response = recurringDepositAccountService.submitApplication(recurringDepositAccountsRequest, true);
                if (response != null) {
                    log.info("Recurring deposit account created successfully: {}", response);
                    foundCustomer.setRecurringDepositAccountId(String.valueOf(response.getResourceId()));
                    customerRepository.save(foundCustomer);
                } else {
                    log.warn("Failed to create recurring deposit account for client ID: {}", foundCustomer.getExternalId());
                }
            } else {
                investmentId = StringUtils.isNotBlank(foundCustomer.getRecurringDepositAccountId()) ? Long.valueOf(foundCustomer.getRecurringDepositAccountId()) : investmentId;
                accountTransactionService.handleRecurringDepositAccountTransfer(savingsAccount,
                        investmentId, request.getAmount(), "Investment funding");
            }
        } else if (investmentType == InvestmentType.LOCK) {
            this.retrieveInvestmentById(investmentId, null, null, investmentType.name(), customerId);
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
    public InvestmentCalculatorResponse calculateInvestment(Long customerId, InvestmentCalculatorRequest request) {
        GetFixedDepositProductsProductIdResponse product =
                fixedDepositProductService.retrieveAProduct(fineractProperty.getDefaultFixedDepositProductId());

        return product.getActiveChart().getChartSlabs().stream()
                .findFirst()
                .map(chartSlab -> {
                    BigDecimal annualInterestRate = chartSlab.getAnnualInterestRate();
                    BigDecimal amount = request.getAmount();
                    Long depositPeriodMonths = request.getDepositPeriod();

                    BigDecimal interest = amount
                            .multiply(annualInterestRate)
                            .divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP)
                            .multiply(BigDecimal.valueOf(depositPeriodMonths))
                            .divide(BigDecimal.valueOf(12), 2, RoundingMode.HALF_UP);

                    InvestmentCalculatorResponse response = new InvestmentCalculatorResponse();
                    response.setInterestExpected(interest);
                    response.setTotalPayout(amount.add(interest));
                    response.setMaturityDate(addMonthsToCurrentDate(depositPeriodMonths));
                    return response;
                })
                .orElseThrow(() -> new ValidationException("no.chart.slab.found",
                        "Investment calculation failed. Please try again later or contact support."));
    }


    public String addMonthsToCurrentDate(Long monthsToAdd) {
        LocalDate maturityDate = LocalDate.now().plusMonths(monthsToAdd);
        return maturityDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
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
            id = StringUtils.isNotBlank(foundCustomer.getRecurringDepositAccountId()) ? Long.valueOf(foundCustomer.getRecurringDepositAccountId()) : id;
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
        Customer foundCustomer = this.customerService.getCustomerById(customerId);
        String externalId = foundCustomer.getExternalId();
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
        List<GetClientsSavingsAccounts> response = accountType == null ? customerAccounts.getSavingsAccounts().stream().filter(account ->
                account != null && account.getDepositType() != null && !Objects.equals(account.getDepositType().getId(), 100L)).toList() :
                customerAccounts.getSavingsAccounts().stream().filter(account ->
                        account != null && account.getDepositType() != null && Objects.equals(account.getDepositType().getId(), accountType.getCode())
                ).toList();

        if (investmentType.equalsIgnoreCase("flex") && StringUtils.isNotBlank(foundCustomer.getRecurringDepositAccountId())) {
            return BasePageResponse.instance(
                    response.stream()
                            .filter(account -> Objects.equals(account.getId(), Long.valueOf(foundCustomer.getRecurringDepositAccountId())))
                            .toList()
            );
        } else {
            return BasePageResponse.instance(
                    response.stream()
                            .peek(account -> {
                                GetFixedDepositAccountsAccountIdResponse fixedDepositAccount = fixedDepositService.retrieveInvestmentById(account.getId(), null, null, null);
                                if (account.getStatus().getActive()){
                                    account.setMaturityDate(fixedDepositAccount.getMaturityDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
                                } else {
                                    account.setMaturityDate(addMonthsToCurrentDate(fixedDepositAccount.getDepositPeriod()));
                                }
                                account.setExpectedInterest(fixedDepositAccount.getMaturityAmount().subtract(fixedDepositAccount.getDepositAmount()));
                            })
                            .toList()
            );
        }
    }

    @Override
    public Object retrieveInvestmentTransactionsById(Long id, Boolean staffInSelectedOfficeOnly, String chargeStatus, String investmentType, Long customerId) {
        Customer foundCustomer = customerService.getCustomerById(customerId);
        if ("lock".equalsIgnoreCase(investmentType)) {
            return BasePageResponse.instance(fixedDepositService.retrieveInvestmentById(id, staffInSelectedOfficeOnly, chargeStatus, "all").getTransactions());
        } else if ("flex".equalsIgnoreCase(investmentType)) {
            id = StringUtils.isNotBlank(foundCustomer.getRecurringDepositAccountId()) ? Long.valueOf(foundCustomer.getRecurringDepositAccountId()) : id;
            return BasePageResponse.instance(recurringDepositAccountService.retrieveInvestmentById(id, staffInSelectedOfficeOnly, chargeStatus, "all").getTransactions());
        } else {
            throw new ValidationException("invalid.investment.type",
                    "Invalid investment type provided. Allowed values are 'flex' for Recurring Deposit and 'lock' for Fixed Deposit.");
        }
    }
}
