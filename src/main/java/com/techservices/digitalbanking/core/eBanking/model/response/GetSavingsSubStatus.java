/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetSavingsSubStatus {

	private Integer id;
	private String code;
	private String value;
	private Boolean none;
	private Boolean inactive;
	private Boolean dormant;
	private Boolean escheat;
	private Boolean block;
	private Boolean blockCredit;
	private Boolean blockDebit;
}
