/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Generated;
import java.io.Serializable;
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
