/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.investment.service.impl;

import com.techservices.digitalbanking.core.domain.BaseAppResponse;
import com.techservices.digitalbanking.core.domain.dto.AccountDto;
import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.core.domain.dto.GenericApiResponse;
import com.techservices.digitalbanking.core.domain.dto.ProductDto;
import com.techservices.digitalbanking.core.exception.PlatformServiceException;
import com.techservices.digitalbanking.core.exception.ValidationException;
import com.techservices.digitalbanking.core.eBanking.configuration.FineractProperty;
import com.techservices.digitalbanking.core.eBanking.model.request.PostRecurringDepositAccountsRequest;
import com.techservices.digitalbanking.core.eBanking.model.request.PutRecurringDepositProductRequest;
import com.techservices.digitalbanking.core.eBanking.model.response.*;
import com.techservices.digitalbanking.core.eBanking.service.*;
import com.techservices.digitalbanking.customer.domian.data.model.Customer;
import com.techservices.digitalbanking.customer.domian.data.repository.CustomerRepository;
import com.techservices.digitalbanking.customer.service.CustomerService;
import com.techservices.digitalbanking.investment.domain.enums.InvestmentType;
import com.techservices.digitalbanking.investment.domain.request.FixedDepositApplicationRequest;
import com.techservices.digitalbanking.investment.domain.request.InvestmentApplicationRequest;
import com.techservices.digitalbanking.investment.domain.request.InvestmentCalculatorRequest;
import com.techservices.digitalbanking.investment.domain.request.InvestmentUpdateRequest;
import com.techservices.digitalbanking.investment.domain.response.InvestmentApplicationResponse;
import com.techservices.digitalbanking.investment.domain.response.InvestmentCalculatorResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.techservices.digitalbanking.investment.service.InvestmentService;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class InvestmentServiceImpl implements InvestmentService {
    private final CustomerService customerService;
    private final FineractProperty fineractProperty;
    private final ClientService clientService;
    private final FlexDepositAccountService flexDepositAccountService;
    private final AccountService accountService;
    private final AccountTransactionService accountTransactionService;
    private final CustomerRepository customerRepository;
    private final LockDepositService lockDepositService;

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
            return lockDepositService.submitApplication(fixedDepositApplicationRequest, false);
        } else {
            throw new ValidationException("invalid.investment.type",
                    "Invalid investment type provided. Allowed values are 'flex' for Recurring Deposit and 'lock' for Fixed Deposit.");
        }
    }

    @Override
    public BaseAppResponse updateAnInvestment(Long customerId, InvestmentType investmentType, InvestmentUpdateRequest request, String investmentId) {
        Customer foundCustomer = customerService.getCustomerById(customerId);
        this.retrieveInvestmentById(Long.valueOf(investmentId), investmentType, customerId);
        if (investmentType == InvestmentType.FLEX) {
            PutRecurringDepositProductRequest putRecurringDepositProductRequest = new PutRecurringDepositProductRequest();
            if (request.getAmount() != null && request.getAmount().compareTo(BigDecimal.ZERO) > 0) {
                return flexDepositAccountService.updateAnInvestment(foundCustomer.getFlexAccountId(), putRecurringDepositProductRequest);
            }
        }
        return null;
    }

    @Override
    public BaseAppResponse fundInvestment(Long customerId, InvestmentType investmentType, InvestmentUpdateRequest request, String investmentId) {
        Customer foundCustomer = customerService.getCustomerById(customerId);
        AccountDto savingsAccount = accountService.retrieveSavingsAccount(foundCustomer.getAccountId());
        if (investmentType == InvestmentType.FLEX) {
            if (request == null || request.getAmount() == null || request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
                throw new ValidationException("invalid.amount",
                        "Invalid amount provided for funding the investment. Amount must be greater than zero.");
            }
            if (savingsAccount.getAccountBalance().compareTo(request.getAmount()) < 0) {
                throw new ValidationException("insufficient.funds",
                        "Insufficient funds in the customer's savings account to create an investment.");
            }
            if (StringUtils.isBlank(foundCustomer.getFlexAccountId())) {
                PostRecurringDepositAccountsRequest recurringDepositAccountsRequest = new PostRecurringDepositAccountsRequest();
                recurringDepositAccountsRequest.setProductId(fineractProperty.getDefaultRecurringDepositProductId());
                recurringDepositAccountsRequest.setClientId(String.valueOf(foundCustomer.getExternalId()));
                recurringDepositAccountsRequest.setDepositPeriodFrequencyId(3L);
                recurringDepositAccountsRequest.setDepositPeriod(5L);
                recurringDepositAccountsRequest.setMandatoryRecommendedDepositAmount(BigDecimal.ONE);
                recurringDepositAccountsRequest.setRecurringFrequency(1L);
                recurringDepositAccountsRequest.setRecurringFrequencyType(0L);
                PostRecurringDepositAccountsResponse response = flexDepositAccountService.submitApplication(recurringDepositAccountsRequest, true);
                if (response != null) {
                    log.info("Recurring deposit account created successfully: {}", response);
                    foundCustomer.setFlexAccountId(String.valueOf(response.getResourceId()));
                    customerRepository.save(foundCustomer);
                } else {
                    log.warn("Failed to create recurring deposit account for client ID: {}", foundCustomer.getExternalId());
                }
            }
            investmentId = StringUtils.isNotBlank(foundCustomer.getFlexAccountId()) ? foundCustomer.getFlexAccountId() : investmentId;
            accountTransactionService.handleFlexDepositAccountTransfer(savingsAccount,
                    investmentId, request.getAmount(), "Investment funding");
        } else if (investmentType == InvestmentType.LOCK) {
            this.retrieveInvestmentById(Long.valueOf(investmentId), investmentType, customerId);
            GetFixedDepositAccountsAccountIdResponse response = lockDepositService.retrieveInvestmentById(investmentId);
            if (savingsAccount.getAccountBalance().compareTo(response.getDepositAmount()) < 0) {
                throw new ValidationException("insufficient.funds",
                        "Insufficient funds in the customer's savings account to create an investment.");
            }
        }
        return GenericApiResponse.builder().message("Flex account has been funded successfully").build();
    }

    @Override
    public BaseAppResponse withdrawFlexInvestment(Long customerId, InvestmentUpdateRequest request, String flexInvestmentId) {
        Customer foundCustomer = customerService.getCustomerById(customerId);
        AccountDto savingsAccount = accountService.retrieveSavingsAccount(foundCustomer.getAccountId());
        if (StringUtils.isBlank(foundCustomer.getFlexAccountId())) {
            throw new ValidationException("investment.not.found",
                    "Error withdrawing from investment. Please contact support or try again later.");
        }
        String investmentId = StringUtils.isNotBlank(foundCustomer.getFlexAccountId()) ? (foundCustomer.getFlexAccountId()) : flexInvestmentId;
        GetRecurringDepositAccountsResponse accountsResponse = flexDepositAccountService.retrieveInvestmentById(investmentId);
        if (accountsResponse.getAccountBalance().compareTo(request.getAmount()) < 0) {
            throw new ValidationException("insufficient.funds",
                    "Insufficient funds in the investment account to withdraw.");
        }

        investmentId = StringUtils.isNotBlank(foundCustomer.getFlexAccountId()) ? foundCustomer.getFlexAccountId() : investmentId;
        accountTransactionService.handleFlexWithdrawalAccountTransfer(savingsAccount,
                investmentId, request.getAmount(), "Investment withdrawal");
        return GenericApiResponse.builder().message("Withdrawal from Flex investment successful").build();
    }

    @Override
    public InvestmentCalculatorResponse calculateInvestment(Long customerId, InvestmentCalculatorRequest request) {
        ProductDto product =
                lockDepositService.retrieveALockProduct();

        BigDecimal annualInterestRate = BigDecimal.valueOf(product.getInterestRate());
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
    }


    public String addMonthsToCurrentDate(Long monthsToAdd) {
        LocalDate maturityDate = LocalDate.now().plusMonths(monthsToAdd);
        return maturityDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    @Override
    public AccountDto retrieveInvestmentById(Long id, InvestmentType investmentType, Long customerId) {
        Customer foundCustomer = customerService.getCustomerById(customerId);
        if (InvestmentType.isFlex(investmentType)) {
            return clientService.getFlexAccountByCustomer(foundCustomer);
        } else if (InvestmentType.isLock(investmentType)) {
            List<@Valid AccountDto> investments = clientService.getAllFlexAccountByExternalId(foundCustomer.getExternalId());
            return investments.stream().filter(accountDto -> StringUtils.equals(accountDto.getId(), String.valueOf(id))).findFirst().orElseThrow(
                    () -> new ValidationException("account.not.found",
                            "No investment account found with the provided ID for this customer.")
            );
        }
        throw new ValidationException("account.not.found",
                "No investment account found with the provided ID for this customer.");
    }

    @Override
    public BasePageResponse<AccountDto> retrieveAllCustomerInvestments(Long customerId, String investmentType) {
        Customer foundCustomer = this.customerService.getCustomerById(customerId);
        String externalId = foundCustomer.getExternalId();

        if (investmentType.equalsIgnoreCase("flex")) {

            List<AccountDto> response = clientService.getAllFlexAccountByExternalId(externalId);

            if (StringUtils.isBlank(foundCustomer.getFlexAccountId())) {
                return BasePageResponse.instance(List.of());
            }
            return BasePageResponse.instance(
                    response.stream()
                            .filter(account -> Objects.equals(account.getId(), Long.valueOf(foundCustomer.getFlexAccountId())))
                            .toList()
            );
        } else {
            List<AccountDto> response = clientService.getAllFlexAccountByExternalId(externalId);
            return BasePageResponse.instance(
                    response.stream()
                            .peek(account -> {
                                GetFixedDepositAccountsAccountIdResponse fixedDepositAccount = lockDepositService.retrieveInvestmentById(account.getId());
                                if (account.getStatus().getActive()) {
                                    account.setMaturityDate(fixedDepositAccount.getMaturityDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
                                } else {
                                    account.setMaturityDate(addMonthsToCurrentDate(fixedDepositAccount.getDepositPeriod()));
                                }
                                account.setExpectedInterest(fixedDepositAccount.getMaturityAmount().subtract(fixedDepositAccount.getDepositAmount()));
                                account.setDuration(fixedDepositAccount.getDepositPeriod());
                            })
                            .toList()
            );
        }
    }

    @Override
    public Object retrieveInvestmentTransactionsById(String id, String investmentType, Long customerId) {
        Customer foundCustomer = customerService.getCustomerById(customerId);
        if ("lock".equalsIgnoreCase(investmentType)) {
            return BasePageResponse.instance(lockDepositService.retrieveInvestmentById(id).getTransactions());
        } else if ("flex".equalsIgnoreCase(investmentType)) {
            id = StringUtils.isNotBlank(foundCustomer.getFlexAccountId()) ? foundCustomer.getFlexAccountId() : id;
            return BasePageResponse.instance(flexDepositAccountService.retrieveInvestmentById(id).getTransactions());
        } else {
            throw new ValidationException("invalid.investment.type",
                    "Invalid investment type provided. Allowed values are 'flex' for Recurring Deposit and 'lock' for Fixed Deposit.");
        }
    }
}
