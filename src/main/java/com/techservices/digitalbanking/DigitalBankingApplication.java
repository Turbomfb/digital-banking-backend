/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking;

import com.techservices.digitalbanking.common.domain.enums.UserType;
import com.techservices.digitalbanking.core.domain.data.model.Address;
import com.techservices.digitalbanking.core.domain.data.repository.AddressRepository;
import com.techservices.digitalbanking.core.domain.data.repository.IdentityVerificationDataRepository;
import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.core.domain.dto.response.IdentityVerificationResponse;
import com.techservices.digitalbanking.core.domain.enums.AccountType;
import com.techservices.digitalbanking.core.domain.enums.AddressType;
import com.techservices.digitalbanking.core.fineract.api.ClientApiClient;
import com.techservices.digitalbanking.core.fineract.model.request.PostRecurringDepositAccountsRequest;
import com.techservices.digitalbanking.core.fineract.model.response.GetClientsSavingsAccounts;
import com.techservices.digitalbanking.core.fineract.model.response.GetRecurringDepositAccountsResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PostRecurringDepositAccountsResponse;
import com.techservices.digitalbanking.core.fineract.service.ClientService;
import com.techservices.digitalbanking.core.fineract.service.RecurringDepositAccountService;
import com.techservices.digitalbanking.core.service.IdentityVerificationService;
import com.techservices.digitalbanking.customer.domian.data.model.Customer;
import com.techservices.digitalbanking.customer.domian.data.repository.CustomerRepository;
import com.techservices.digitalbanking.investment.domain.enums.InvestmentType;
import com.techservices.digitalbanking.investment.domain.request.InvestmentUpdateRequest;
import com.techservices.digitalbanking.investment.domain.request.RecurringDepositCommandRequest;
import com.techservices.digitalbanking.investment.service.InvestmentService;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.List;

import static com.techservices.digitalbanking.core.util.AppUtil.DIRECTORS_DATATABLE_NAME;
import static com.techservices.digitalbanking.core.util.AppUtil.normalizePhoneNumber;
import static com.techservices.digitalbanking.core.util.CommandUtil.PREMATURE_CLOSE;

@SpringBootApplication
@EnableScheduling
@EnableCaching
@EnableFeignClients(basePackages = "com.techservices.digitalbanking.core.fineract.api")
@RequiredArgsConstructor
@EnableAsync
public class DigitalBankingApplication {
    private final CustomerRepository customerRepository;
    private final IdentityVerificationDataRepository verificationDataRepository;
    private final RecurringDepositAccountService recurringDepositAccountService;
    private final ClientService clientService;
    private final PasswordEncoder passwordEncoder;
    private final IdentityVerificationService identityVerificationService;
    private final AddressRepository addressRepository;
    private final ClientApiClient clientApiClient;
    private final InvestmentService investmentService;

    public static void main(String[] args) {
        SpringApplication.run(DigitalBankingApplication.class, args);
    }

    @PostConstruct
    public void init() {
        customerRepository.findAll().forEach(customer -> {
            if (customer.isActive()) {
                BasePageResponse<GetClientsSavingsAccounts> accountResponse = investmentService.retrieveAllCustomerInvestments(customer.getId(), InvestmentType.FLEX.name());
                System.err.println("accountResponse: " + accountResponse.getData());
                if (!accountResponse.getData().isEmpty()) {
                    try {
                        accountResponse.getData().forEach(response -> {
                            Long investmentId = response.getId();
                            RecurringDepositCommandRequest commandRequest = new RecurringDepositCommandRequest();
                            commandRequest.setOnAccountClosureId(200L);
                            commandRequest.setToSavingsAccountId(Long.valueOf(customer.getAccountId()));
                            commandRequest.setNote("Close please");
                            String command = response.getStatus().getActive() ? PREMATURE_CLOSE : response.getStatus().getMatured() ? "CLOSE" : null;
                            if (StringUtils.isNotBlank(command)) {
                                recurringDepositAccountService.processInvestmentCommand(investmentId, commandRequest, command);
                                BigDecimal balance = response.getAccountBalance();
                                customer.setRecurringDepositAccountId(null);
                                customerRepository.save(customer);
                                InvestmentUpdateRequest updateRequest = new InvestmentUpdateRequest();
                                updateRequest.setAmount(balance);
                                investmentService.fundInvestment(customer.getId(), InvestmentType.FLEX, updateRequest, String.valueOf(investmentId));
                            }
                        });
                    } catch (Exception e) {
                        System.err.println("Error processing investment for customer ID " + customer.getId() + ": " + e.getMessage());
                    }
                }
            }
        });
    }
}
