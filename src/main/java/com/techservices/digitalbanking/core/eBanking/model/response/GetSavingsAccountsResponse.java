/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.Valid;
import java.util.Collections;
import java.util.Set;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetSavingsAccountsResponse {

  private Integer totalFilteredRecords;

  @Valid private Set<@Valid GetSavingsAccountsAccountIdResponse> pageItems = Collections.emptySet();
}
