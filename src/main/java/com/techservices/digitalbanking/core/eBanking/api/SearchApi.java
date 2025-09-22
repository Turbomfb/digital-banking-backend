/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.eBanking.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.techservices.digitalbanking.core.eBanking.model.request.PostAdhocQuerySearchRequest;
import com.techservices.digitalbanking.core.eBanking.model.response.GetSearchResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.PostAdhocQuerySearchResponse;

import jakarta.validation.Valid;

public interface SearchApi {
	@PostMapping("/search/advance")
	List<PostAdhocQuerySearchResponse> advancedSearch(
			@Valid @RequestBody PostAdhocQuerySearchRequest postAdhocQuerySearchRequest);

	// resource - clients,clientIdentifiers
	@GetMapping("/search")
	List<GetSearchResponse> searchData(@RequestParam(value = "query", required = false) String query,
			@RequestParam(value = "resource", required = false) String resource,
			@RequestParam(value = "exactMatch", required = false, defaultValue = "false") Boolean exactMatch);
}
