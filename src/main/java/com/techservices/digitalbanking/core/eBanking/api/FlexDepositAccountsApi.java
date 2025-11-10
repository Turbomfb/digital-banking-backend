/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.eBanking.api;

import java.util.List;

import com.techservices.digitalbanking.core.domain.dto.ProductDto;
import com.techservices.digitalbanking.core.eBanking.model.request.*;
import com.techservices.digitalbanking.core.eBanking.model.response.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import jakarta.validation.Valid;

public interface FlexDepositAccountsApi {

	@PostMapping("flexaccounts")
	@Operation(summary = "Create a recurring deposit account", description = "Creates a recurring deposit account")
	FlexInvestmentCreationResponse create(@RequestBody FlexInvestmentCreationRequest request);

	@GetMapping("flexaccounts/{accountId}")
	@Operation(summary = "Get a recurring deposit account by ID", description = "Gets a recurring deposit account")
	GetRecurringDepositAccountsResponse retrieveOne(
			@Parameter(name = "accountId", description = "accountId", required = true, in = ParameterIn.PATH) @PathVariable("accountId") String accountId
	);

	@GetMapping("flexaccounts")
	@Operation(summary = "Get all recurring deposit accounts", description = "Gets all recurring deposit accounts")
	List<GetRecurringDepositAccountsResponse> retrieveAll();

	@PutMapping("flexaccounts/{accountId}")
	@Operation(summary = "Update a recurring deposit account", description = "Updates a recurring deposit account")
	GetRecurringDepositAccountsResponse update(
			@Parameter(in = ParameterIn.PATH, name = "accountId", required = true, description = "The recurring deposit account id") @PathVariable("accountId") Long accountId,
			@Valid @RequestBody PutRecurringDepositProductRequest request);

	@PostMapping("flexaccounts/{accountId}")
	@Operation(summary = "Approve a recurring deposit account | Activate a recurring deposit account | Undo approval | Reject recurring deposit account request | Pre-liquidate recurring deposit account | Mature closure")
	PostRecurringDepositAccountsResponse processCommand(
			@Parameter(in = ParameterIn.PATH, name = "accountId", required = true, description = "The recurring deposit account id") @PathVariable("accountId") Long accountId,
			PostRecurringDepositAccountsAccountIdRequest request, @RequestParam(value = "command") String command);

	@GetMapping("flexaccounts/{accountId}/transactions/{transactionId}")
	GetRecurringDepositTransactionResponse retrieveOneTransaction(@PathVariable String accountId,
			@PathVariable Long transactionId);

	@PostMapping("flexaccounts/{accountId}/transactions")
	PostRecurringDepositTransactionCommandResponse processTransactionCommand(@PathVariable String accountId,
			@RequestBody PostRecurringDepositTransactionCommandRequest postRecurringDepositTransactionCommandRequest,
			@Valid @RequestParam(value = "command") String command);

	@GetMapping("flexaccounts/product")
	ProductDto retrieveFlexProductDetails();
}
