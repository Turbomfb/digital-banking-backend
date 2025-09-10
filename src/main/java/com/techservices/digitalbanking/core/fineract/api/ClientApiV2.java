/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.api;

import com.techservices.digitalbanking.core.fineract.model.request.*;
import com.techservices.digitalbanking.core.fineract.model.response.*;
import org.springframework.web.bind.annotation.*;

public interface ClientApiV2 {

	@GetMapping(value = "/clients/search")
	ClientSearchResponse searchClient(@RequestBody ClientSearchRequest clientSearchRequest);

}
