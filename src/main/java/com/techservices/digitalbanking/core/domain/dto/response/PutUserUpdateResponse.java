/* (C)2025 */
package com.techservices.digitalbanking.core.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PutUserUpdateResponse {

	private String officeId;
	private String resourceId;
	private Object changes;
}
