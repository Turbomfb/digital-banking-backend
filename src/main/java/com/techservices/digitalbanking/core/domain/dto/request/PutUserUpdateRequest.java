/* (C)2025 */
package com.techservices.digitalbanking.core.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PutUserUpdateRequest {

	private String password;
	private String repeatPassword;
}
