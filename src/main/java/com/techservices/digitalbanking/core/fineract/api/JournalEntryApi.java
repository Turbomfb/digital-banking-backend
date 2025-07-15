/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.techservices.digitalbanking.core.fineract.model.request.PostGLToSavingAccountRequest;
import com.techservices.digitalbanking.core.fineract.model.request.PostSavingAccountToGlRequest;
import com.techservices.digitalbanking.core.fineract.model.response.PostJournalEntryResponse;

import jakarta.validation.Valid;

public interface JournalEntryApi {

	@PostMapping(value = "/journalentries/debit-gl-credit-casa")
	PostJournalEntryResponse postGlToCasaJournalEntry(
			@RequestBody @Valid PostGLToSavingAccountRequest postGLToSavingAccountRequest,
			@RequestParam(name = "savingsId") Long savingsId);

	@PostMapping(value = "/journalentries/debit-casa-credit-gl")
	PostJournalEntryResponse postCasaToGlJournalEntry(
			@RequestBody PostSavingAccountToGlRequest postSavingAccountToGlRequest,
			@RequestParam(name = "savingsId") Long savingsId,
			@RequestParam(name = "tenantIdentifier", defaultValue = "default") String tenantIdentifier);
}
