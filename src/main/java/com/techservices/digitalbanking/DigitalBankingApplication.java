/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking;

import com.techservices.digitalbanking.common.domain.enums.UserType;
import com.techservices.digitalbanking.core.domain.data.model.Address;
import com.techservices.digitalbanking.core.domain.data.repository.AddressRepository;
import com.techservices.digitalbanking.core.domain.data.repository.IdentityVerificationDataRepository;
import com.techservices.digitalbanking.core.domain.dto.response.IdentityVerificationResponse;
import com.techservices.digitalbanking.core.domain.enums.AccountType;
import com.techservices.digitalbanking.core.domain.enums.AddressType;
import com.techservices.digitalbanking.core.fineract.model.request.PostRecurringDepositAccountsRequest;
import com.techservices.digitalbanking.core.fineract.model.response.GetClientsSavingsAccounts;
import com.techservices.digitalbanking.core.fineract.model.response.PostRecurringDepositAccountsResponse;
import com.techservices.digitalbanking.core.fineract.service.ClientService;
import com.techservices.digitalbanking.core.fineract.service.RecurringDepositAccountService;
import com.techservices.digitalbanking.core.service.IdentityVerificationService;
import com.techservices.digitalbanking.customer.domian.data.model.Customer;
import com.techservices.digitalbanking.customer.domian.data.repository.CustomerRepository;
import com.techservices.digitalbanking.investment.domain.request.RecurringDepositCommandRequest;
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

import java.util.List;

import static com.techservices.digitalbanking.core.util.AppUtil.normalizePhoneNumber;

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

    public static void main(String[] args) {
        SpringApplication.run(DigitalBankingApplication.class, args);
    }

    @PostConstruct
    public void init() {
        customerRepository.findAll().forEach(foundCustomer -> {
            if (StringUtils.isNotBlank(foundCustomer.getNin())) {
                String url = identityVerificationService.buildUrl("nin");
                System.err.println("url = " + url);
                try {
                    List<Address> customerAddress = addressRepository.findByCustomerId(foundCustomer.getId());
                    if (customerAddress.isEmpty()) {
                        IdentityVerificationResponse response = identityVerificationService.fetchIdentityVerificationResponse(url, foundCustomer.getNin());
                        IdentityVerificationResponse.IdentityVerificationResponseData data = response.getData();
                        if (response.isSuccess() && data != null && data.getAddress() != null) {
                            IdentityVerificationResponse.IdentityVerificationResponseData.Address address = data.getAddress();
                            Address newAddress = new Address();
                            newAddress.setCustomerId(foundCustomer.getId());
                            newAddress.setAddressLine(address.getAddressLine());
                            newAddress.setType(AddressType.RESIDENTIAL.toString());
                            newAddress.setTown(address.getTown());
                            newAddress.setLga(address.getLga());
                            newAddress.setState(address.getState());
                            addressRepository.save(newAddress);
                        }
                    }
                } catch (Exception e) {
                    System.err.println("e = " + e.getMessage());
                }
            }
        });
    }
}
