/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.techservices.digitalbanking.core.fineract.model.request.PostAccountTransfersRequest;
import com.techservices.digitalbanking.core.fineract.model.response.GetAccountTransfersResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PostAccountTransfersResponse;

import jakarta.validation.Valid;

public interface AccountTransferApi {
	@PostMapping(value = "/accounttransfers")
	PostAccountTransfersResponse makeTransfer(
			@Valid @RequestBody PostAccountTransfersRequest postAccountTransfersRequest);

	@GetMapping(value = "/accounttransfers")
	GetAccountTransfersResponse retrieveAll(
			@Valid @RequestParam(value = "sqlSearch", required = false) String sqlSearch,
			@Valid @RequestParam(value = "externalId", required = false) String externalId,
			@Valid @RequestParam(value = "offset", required = false) Long offset,
			@Valid @RequestParam(value = "limit", required = false) Long limit,
			@Valid @RequestParam(value = "orderBy", required = false) String orderBy,
			@Valid @RequestParam(value = "sortOrder", required = false) String sortOrder,
			@Valid @RequestParam(value = "accountDetailId", required = false) Long accountDetailId);
}
