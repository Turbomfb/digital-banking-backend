/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.investment.api;

import com.techservices.digitalbanking.core.fineract.model.response.GetFixedDepositAccountsAccountIdResponse;
import com.techservices.digitalbanking.core.fineract.model.response.GetFixedDepositAccountsResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techservices.digitalbanking.investment.service.InvestmentService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("api/v1/investments")
@RestController
@RequiredArgsConstructor
public class InvestmentApiResource {
	private final InvestmentService investmentService;

	@Operation(summary = "Retrieve Investment List")
	@GetMapping
	public ResponseEntity<GetFixedDepositAccountsResponse> retrieveAllInvestments(
			@Valid @RequestParam(value = "paged", required = false) Boolean paged,
			@Valid @RequestParam(value = "offset", required = false) Integer offset,
			@Valid @RequestParam(value = "limit", required = false) Integer limit,
			@Valid @RequestParam(value = "orderBy", required = false) String orderBy,
			@Valid @RequestParam(value = "sortOrder", required = false) String sortOrder) {
		GetFixedDepositAccountsResponse investments = investmentService.retrieveAllInvestments(paged, offset,
				limit, orderBy, sortOrder);
		return ResponseEntity.ok(investments);
	}

	@Operation(summary = "Retrieve an Investment")
	@GetMapping("{id}")
	public ResponseEntity<GetFixedDepositAccountsAccountIdResponse> retrieveInvestmentById(@PathVariable Long id,
																						   @RequestParam(value = "staffInSelectedOfficeOnly", required = false, defaultValue = "false") Boolean staffInSelectedOfficeOnly,
																						   @Valid @RequestParam(value = "chargeStatus", required = false, defaultValue = "all") String chargeStatus) {
		GetFixedDepositAccountsAccountIdResponse investment = investmentService.retrieveInvestmentById(id,
				staffInSelectedOfficeOnly, chargeStatus);
		return ResponseEntity.ok(investment);
	}

	@GetMapping("/template")
	public ResponseEntity<Object> retrieveInvestmentTemplate(@RequestParam Long clientId,
			@RequestParam Long productId) {
		return ResponseEntity.ok(investmentService.retrieveTemplate(clientId, productId));
	}
}
