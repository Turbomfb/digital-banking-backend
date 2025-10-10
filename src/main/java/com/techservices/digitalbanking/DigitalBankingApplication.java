/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking;

import com.techservices.digitalbanking.core.domain.data.repository.AddressRepository;
import com.techservices.digitalbanking.core.domain.data.repository.IdentityVerificationDataRepository;
import com.techservices.digitalbanking.core.domain.dto.AccountDto;
import com.techservices.digitalbanking.core.eBanking.api.CustomerApiClient;
import com.techservices.digitalbanking.core.eBanking.service.AccountService;
import com.techservices.digitalbanking.core.eBanking.service.ClientService;
import com.techservices.digitalbanking.core.eBanking.service.FlexDepositAccountService;
import com.techservices.digitalbanking.core.service.IdentityVerificationService;
import com.techservices.digitalbanking.customer.domian.data.model.Customer;
import com.techservices.digitalbanking.customer.domian.data.repository.CustomerRepository;
import com.techservices.digitalbanking.investment.service.InvestmentService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableScheduling
@EnableCaching
@EnableFeignClients(basePackages = "com.techservices.digitalbanking.core.eBanking.api")
@RequiredArgsConstructor
@EnableAsync
public class DigitalBankingApplication {
    private final CustomerRepository customerRepository;
    private final IdentityVerificationDataRepository verificationDataRepository;
    private final FlexDepositAccountService flexDepositAccountService;
    private final ClientService clientService;
    private final PasswordEncoder passwordEncoder;
    private final IdentityVerificationService identityVerificationService;
    private final AddressRepository addressRepository;
    private final CustomerApiClient customerApiClient;
    private final InvestmentService investmentService;
    private final AccountService accountService;

    public static void main(String[] args) {
        SpringApplication.run(DigitalBankingApplication.class, args);
    }

    @PostConstruct
    public void init() {
    }
}
