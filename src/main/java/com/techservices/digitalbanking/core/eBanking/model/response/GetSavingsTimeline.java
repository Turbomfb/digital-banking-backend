/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDate;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetSavingsTimeline {

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate activatedOnDate;

  private String approvedByFirstname;
  private String approvedByLastname;
  private String approvedByUsername;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate approvedOnDate;

  private String submittedByFirstname;
  private String submittedByLastname;
  private String submittedByUsername;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate submittedOnDate;
}
