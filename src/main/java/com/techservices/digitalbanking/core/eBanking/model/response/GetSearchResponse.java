/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetSearchResponse {

	private Long entityAccountNo;

	private String entityExternalId;

	private Long entityId;

	private String entityName;

	private EnumOptionData entityStatus;

	private String entityType;

	private Long parentId;

	private String parentName;
}
