/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.investment.api;

import com.techservices.digitalbanking.core.configuration.security.SpringSecurityAuditorAware;
import com.techservices.digitalbanking.core.domain.BaseAppResponse;
import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.core.fineract.model.response.GetClientsSavingsAccounts;
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

@RequestMapping("api/v1/customers/me/investments")
@RestController
@RequiredArgsConstructor
public class InvestmentApiResource {
	private final InvestmentService investmentService;
	private final SpringSecurityAuditorAware springSecurityAuditorAware;

	@Operation(summary = "Retrieve Customer's Investments")
	@GetMapping()
	public ResponseEntity<BasePageResponse<GetClientsSavingsAccounts>> retrieveAllCustomerInvestments(
			@RequestParam(required = false) String investmentType
	) {
		Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
		BasePageResponse<GetClientsSavingsAccounts> investment = investmentService.retrieveAllCustomerInvestments(customerId, investmentType);
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
