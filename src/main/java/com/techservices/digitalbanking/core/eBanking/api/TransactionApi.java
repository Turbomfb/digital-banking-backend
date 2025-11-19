/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.api;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.techservices.digitalbanking.core.domain.dto.TransactionDto;
import com.techservices.digitalbanking.core.domain.enums.TransactionType;
import com.techservices.digitalbanking.core.eBanking.model.request.FilterDto;
import com.techservices.digitalbanking.core.eBanking.model.request.PostAccountTransfersRequest;
import com.techservices.digitalbanking.core.eBanking.model.response.PostAccountTransfersResponse;

import jakarta.validation.Valid;

public interface TransactionApi {
	@PostMapping(value = "/transactions")
	PostAccountTransfersResponse makeTransfer(@RequestParam TransactionType transactionType,
			@Valid @RequestBody PostAccountTransfersRequest postAccountTransfersRequest);

	@PostMapping(value = "/transactions/search")
	List<TransactionDto> retrieveAllTransactionsByAccountNo(@RequestBody FilterDto filter);
}
