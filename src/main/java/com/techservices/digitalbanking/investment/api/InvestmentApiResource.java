/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.investment.api;

import com.techservices.digitalbanking.core.configuration.security.SpringSecurityAuditorAware;
import com.techservices.digitalbanking.core.domain.BaseAppResponse;
import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.core.domain.dto.GenericApiResponse;
import com.techservices.digitalbanking.core.fineract.model.response.*;
import com.techservices.digitalbanking.investment.domain.enums.InvestmentType;
import com.techservices.digitalbanking.investment.domain.request.InvestmentApplicationRequest;
import com.techservices.digitalbanking.investment.domain.request.InvestmentCalculatorRequest;
import com.techservices.digitalbanking.investment.domain.request.InvestmentUpdateRequest;
import com.techservices.digitalbanking.investment.domain.response.InvestmentApplicationResponse;
import com.techservices.digitalbanking.investment.domain.response.InvestmentCalculatorResponse;
import com.techservices.digitalbanking.investment.domain.response.InvestmentUpdateResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.techservices.digitalbanking.investment.service.InvestmentService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.Comparator;

@RequestMapping("api/v1/customers/me/investments")
@RestController
@RequiredArgsConstructor
public class InvestmentApiResource {
	private final InvestmentService investmentService;
	private final SpringSecurityAuditorAware springSecurityAuditorAware;

	@Operation(summary = "Create an investment")
	@PostMapping
	public ResponseEntity<InvestmentApplicationResponse> createAnInvestment(
			@RequestBody InvestmentApplicationRequest request,
			@RequestParam(required = false) InvestmentType investmentType
	) {
		Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
		InvestmentApplicationResponse investment = investmentService.submitApplication(customerId, investmentType, request);
		return ResponseEntity.ok(investment);
	}


	@Operation(summary = "Update an investment")
	@PutMapping("/{investmentId}")
	public ResponseEntity<BaseAppResponse> updateAnInvestment(
			@RequestBody InvestmentUpdateRequest request,
			@RequestParam(required = false) InvestmentType investmentType,
			@PathVariable String investmentId
	) {
		Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
		BaseAppResponse investment = investmentService.updateAnInvestment(customerId, investmentType, request, investmentId);
		return ResponseEntity.ok(investment);
	}


	@Operation(summary = "Fund an investment")
	@PostMapping("/{investmentId}/fund")
	public ResponseEntity<BaseAppResponse> fundInvestment(
			@RequestBody(required = false) InvestmentUpdateRequest request,
			@RequestParam(required = false) InvestmentType investmentType,
			@PathVariable Long investmentId
	) {
		Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
		BaseAppResponse investment = investmentService.fundInvestment(customerId, investmentType, request, investmentId);
		return ResponseEntity.ok(investment);
	}

	@Operation(summary = "Calculate Investment")
	@PostMapping("/calculate-investment")
	public ResponseEntity<InvestmentCalculatorResponse> calculateInvestment(
			@RequestBody InvestmentCalculatorRequest request
			) {
		Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
		InvestmentCalculatorResponse response = investmentService.calculateInvestment(customerId, request);
		return ResponseEntity.ok(response);
	}

	@Operation(summary = "Retrieve Customer's Investments")
	@GetMapping()
	public ResponseEntity<BasePageResponse<GetClientsSavingsAccounts>> retrieveAllCustomerInvestments(
			@RequestParam(required = false, defaultValue = "FLEX") String investmentType
	) {
		Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
		BasePageResponse<GetClientsSavingsAccounts> investment = investmentService.retrieveAllCustomerInvestments(customerId, investmentType);
		investment.setData(
				investment.getData().stream()
						.sorted(Comparator.comparing(GetClientsSavingsAccounts::getId).reversed())
						.toList()
		);
		return ResponseEntity.ok(investment);
	}

	@Operation(summary = "Retrieve an Investment")
	@GetMapping("{id}")
	public ResponseEntity<BaseAppResponse> retrieveInvestmentById(
			@PathVariable Long id,
			@RequestParam(value = "staffInSelectedOfficeOnly", required = false, defaultValue = "false") Boolean staffInSelectedOfficeOnly,
			@Valid @RequestParam(value = "chargeStatus", required = false, defaultValue = "all") String chargeStatus,
			@RequestParam(required = false) String investmentType
	) {
		Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
		BaseAppResponse investment = investmentService.retrieveInvestmentById(id,
				staffInSelectedOfficeOnly, chargeStatus, investmentType, customerId);
		return ResponseEntity.ok(investment);
	}

	@Operation(summary = "Retrieve an Investment Transactions")
	@GetMapping("{id}/transactions")
	public ResponseEntity<Object> retrieveInvestmentTransactionsById(
			@PathVariable Long id,
			@RequestParam(value = "staffInSelectedOfficeOnly", required = false, defaultValue = "false") Boolean staffInSelectedOfficeOnly,
			@Valid @RequestParam(value = "chargeStatus", required = false, defaultValue = "all") String chargeStatus,
			@RequestParam(required = false) String investmentType
	) {
		Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
		Object investment = investmentService.retrieveInvestmentTransactionsById(id,
				staffInSelectedOfficeOnly, chargeStatus, investmentType, customerId);
		return ResponseEntity.ok(investment);
	}
}
