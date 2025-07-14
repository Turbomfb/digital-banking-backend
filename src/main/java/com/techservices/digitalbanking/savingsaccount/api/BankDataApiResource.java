/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.savingsaccount.api;

import com.techservices.digitalbanking.core.domain.dto.response.BankDataResponse;
import com.techservices.digitalbanking.core.service.BankDataService;
import com.techservices.digitalbanking.savingsaccount.domain.request.NameEnquiryRequest;
import com.techservices.digitalbanking.savingsaccount.domain.response.NameEnquiryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/bank-data")
@RestController
@RequiredArgsConstructor
@Slf4j
public class BankDataApiResource {
	private final BankDataService bankDataService;

	@GetMapping
	public ResponseEntity<BankDataResponse> retrieveBankData() {
		return ResponseEntity.ok(bankDataService.retrieveAllBanks());
	}

	@PostMapping("/name-enquiry")
	public ResponseEntity<NameEnquiryResponse> processNameEnquiry(
			@RequestBody NameEnquiryRequest request
	) {
		return ResponseEntity.ok(bankDataService.processNameEnquiry(request));
	}


}
