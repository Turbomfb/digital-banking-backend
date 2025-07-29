/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking;

import com.techservices.digitalbanking.customer.domian.data.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableCaching
@EnableFeignClients(basePackages = "com.techservices.digitalbanking.core.fineract.api")
@RequiredArgsConstructor
public class DigitalBankingApplication {
	private final CustomerRepository customerRepository;

	public static void main(String[] args) {
		SpringApplication.run(DigitalBankingApplication.class, args);
	}
}
