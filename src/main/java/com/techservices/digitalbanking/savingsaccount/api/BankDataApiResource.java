/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.savingsaccount.api;

import com.techservices.digitalbanking.core.domain.dto.GenericApiResponse;
import com.techservices.digitalbanking.core.domain.dto.response.BankDataResponse;
import com.techservices.digitalbanking.core.fineract.model.data.FineractPageResponse;
import com.techservices.digitalbanking.core.fineract.model.response.SavingsAccountTransactionData;
import com.techservices.digitalbanking.core.service.BankDataService;
import com.techservices.digitalbanking.customer.service.CustomerService;
import com.techservices.digitalbanking.savingsaccount.domain.request.NameEnquiryRequest;
import com.techservices.digitalbanking.savingsaccount.domain.request.SavingsAccountTransactionRequest;
import com.techservices.digitalbanking.savingsaccount.domain.request.StatementRequest;
import com.techservices.digitalbanking.savingsaccount.domain.response.NameEnquiryResponse;
import com.techservices.digitalbanking.savingsaccount.service.SavingsAccountStatementService;
import com.techservices.digitalbanking.savingsaccount.service.SavingsAccountTransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidParameterException;
import java.time.LocalDate;

import static com.techservices.digitalbanking.core.util.CommandUtil.GENERATE_OTP_COMMAND;

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
