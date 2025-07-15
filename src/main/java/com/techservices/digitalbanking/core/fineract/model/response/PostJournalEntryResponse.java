/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.annotation.Generated;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Generated("jsonschema2pojo")
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
public class PostJournalEntryResponse implements Serializable {

	@JsonProperty("officeId")
	private Long officeId;

	@JsonProperty("transactionId")
	private String transactionId;
}
