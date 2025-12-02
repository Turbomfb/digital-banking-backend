/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetClientAddressTemplateResponse {

	private List<GetCodeValuesDataResponse> countryIdOptions = Collections.emptyList();

	private List<GetCodeValuesDataResponse> stateProvinceIdOptions = Collections.emptyList();

	private List<GetCodeValuesDataResponse> addressTypeIdOptions = Collections.emptyList();
}
