/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking;

import com.techservices.digitalbanking.common.domain.enums.UserType;
import com.techservices.digitalbanking.core.domain.enums.AccountType;
import com.techservices.digitalbanking.core.fineract.model.request.PostRecurringDepositAccountsRequest;
import com.techservices.digitalbanking.core.fineract.model.response.GetClientsSavingsAccounts;
import com.techservices.digitalbanking.core.fineract.model.response.PostRecurringDepositAccountsResponse;
import com.techservices.digitalbanking.core.fineract.service.ClientService;
import com.techservices.digitalbanking.core.fineract.service.RecurringDepositAccountService;
import com.techservices.digitalbanking.customer.domian.data.model.Customer;
import com.techservices.digitalbanking.customer.domian.data.repository.CustomerRepository;
import com.techservices.digitalbanking.investment.domain.request.RecurringDepositCommandRequest;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@SpringBootApplication
@EnableScheduling
@EnableCaching
@EnableFeignClients(basePackages = "com.techservices.digitalbanking.core.fineract.api")
@RequiredArgsConstructor
public class DigitalBankingApplication {
    private final CustomerRepository customerRepository;
    private final RecurringDepositAccountService recurringDepositAccountService;
    private final ClientService clientService;

    public static void main(String[] args) {
        SpringApplication.run(DigitalBankingApplication.class, args);
    }

    @PostConstruct
    public void init() {
        for (Customer foundCustomer : customerRepository.findAll()) {
            if (foundCustomer.getExternalId() != null) {
                try {
                    Long clientId = Long.parseLong(foundCustomer.getExternalId());
                    @Valid Set<@Valid GetClientsSavingsAccounts> clientAccounts = clientService.getClientAccountsByClientId(String.valueOf(clientId), "savingsAccounts").getSavingsAccounts();

                    Optional<@Valid GetClientsSavingsAccounts> recurringAccount = clientAccounts.stream().filter(account -> account != null && account.isAccountType(AccountType.RECURRING_DEPOSIT)).findAny();
                    if (recurringAccount.isEmpty() || !recurringAccount.get().getStatus().getActive() || !Objects.equals(recurringAccount.get().getProductId(), 12L)) {
                        PostRecurringDepositAccountsRequest request = new PostRecurringDepositAccountsRequest();
                        request.setProductId(12L);
                        request.setClientId(String.valueOf(clientId));
                        request.setDepositPeriodFrequencyId(0L);
                        request.setDepositPeriod(1L);
                        request.setMandatoryRecommendedDepositAmount(BigDecimal.ONE);
                        request.setRecurringFrequency(1L);
                        request.setRecurringFrequencyType(0L);
                        PostRecurringDepositAccountsResponse response = recurringDepositAccountService.submitApplication(request, true);
                        if (response != null) {
                            System.err.println("Recurring deposit account created successfully: {}" + response);
                            foundCustomer.setRecurringDepositAccountId(String.valueOf(response.getSavingsId()));
                            customerRepository.save(foundCustomer);
                        } else {
                            System.err.println("Failed to create recurring deposit account for client ID: {}");
                        }
                    }
                } catch (Exception e) {
                    System.err.println("Error processing customer with external ID: " + foundCustomer.getExternalId() + ", " + e.getMessage());
                }
            }
        }
    }
}
