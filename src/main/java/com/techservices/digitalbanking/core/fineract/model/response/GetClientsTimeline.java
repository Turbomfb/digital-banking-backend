/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.response;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetClientsTimeline {

	private String activatedByFirstname;

	private String activatedByLastname;

	private String activatedByUsername;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate activatedOnDate;

	private String submittedByFirstname;

	private String submittedByLastname;

	private String submittedByUsername;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate submittedOnDate;
}
