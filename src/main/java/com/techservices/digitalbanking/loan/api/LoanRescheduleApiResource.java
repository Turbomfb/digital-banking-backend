/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.loan.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.techservices.digitalbanking.core.fineract.model.request.LoanRescheduleRequest;
import com.techservices.digitalbanking.core.fineract.model.response.LoanRescheduleResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PostLoansLoanIdRequest;
import com.techservices.digitalbanking.core.fineract.model.response.PostLoansLoanIdResponse;
import com.techservices.digitalbanking.loan.service.LoanApplicationService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("api/v1/loans/reschedule")
@RestController
@RequiredArgsConstructor
public class LoanRescheduleApiResource {

	private final LoanApplicationService loanService;

	@Operation(summary = "Create a Loan Reschedule Request")
	@PostMapping("")
	public ResponseEntity<LoanRescheduleResponse> createALoanRescheduleRequest(
			@Valid @RequestBody LoanRescheduleRequest loanRescheduleRequest) {
		LoanRescheduleResponse loanRescheduleResponse = loanService.createALoanRescheduleRequest(loanRescheduleRequest);

		return ResponseEntity.ok(loanRescheduleResponse);
	}

	@Operation(summary = "Process Loan Reschedule Command")
	@PostMapping("{requestId}")
	public ResponseEntity<PostLoansLoanIdResponse> processLoanRescheduleCommand(
			@RequestBody(required = false) PostLoansLoanIdRequest postLoansLoanIdRequest,
			@PathVariable(value = "requestId") String requestId, @RequestParam(value = "command") String command) {
		PostLoansLoanIdResponse postLoansLoanIdResponse = loanService
				.processLoanRescheduleCommand(postLoansLoanIdRequest, requestId, command);

		return ResponseEntity.ok(postLoansLoanIdResponse);
	}
}
