/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.Objects;
import org.springframework.format.annotation.DateTimeFormat;

/** GetLoansLoanIdTimeline */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]",
    comments = "Generator version: 7.5.0")
public class GetLoansLoanIdTimeline {

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate actualDisbursementDate;

  private String approvedByFirstname;

  private String approvedByLastname;

  private String approvedByUsername;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate approvedOnDate;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate closedOnDate;

  private String disbursedByFirstname;

  private String disbursedByLastname;

  private String disbursedByUsername;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate expectedDisbursementDate;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate expectedMaturityDate;

  private String submittedByFirstname;

  private String submittedByLastname;

  private String submittedByUsername;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate submittedOnDate;

  public GetLoansLoanIdTimeline actualDisbursementDate(LocalDate actualDisbursementDate) {

    this.actualDisbursementDate = actualDisbursementDate;
    return this;
  }

  /**
   * Get actualDisbursementDate
   *
   * @return actualDisbursementDate
   */
  @Valid
  @Schema(name = "actualDisbursementDate", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("actualDisbursementDate")
  public LocalDate getActualDisbursementDate() {

    return actualDisbursementDate;
  }

  public void setActualDisbursementDate(LocalDate actualDisbursementDate) {

    this.actualDisbursementDate = actualDisbursementDate;
  }

  public GetLoansLoanIdTimeline approvedByFirstname(String approvedByFirstname) {

    this.approvedByFirstname = approvedByFirstname;
    return this;
  }

  /**
   * Get approvedByFirstname
   *
   * @return approvedByFirstname
   */
  @Schema(
      name = "approvedByFirstname",
      example = "App",
      requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("approvedByFirstname")
  public String getApprovedByFirstname() {

    return approvedByFirstname;
  }

  public void setApprovedByFirstname(String approvedByFirstname) {

    this.approvedByFirstname = approvedByFirstname;
  }

  public GetLoansLoanIdTimeline approvedByLastname(String approvedByLastname) {

    this.approvedByLastname = approvedByLastname;
    return this;
  }

  /**
   * Get approvedByLastname
   *
   * @return approvedByLastname
   */
  @Schema(
      name = "approvedByLastname",
      example = "Administrator",
      requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("approvedByLastname")
  public String getApprovedByLastname() {

    return approvedByLastname;
  }

  public void setApprovedByLastname(String approvedByLastname) {

    this.approvedByLastname = approvedByLastname;
  }

  public GetLoansLoanIdTimeline approvedByUsername(String approvedByUsername) {

    this.approvedByUsername = approvedByUsername;
    return this;
  }

  /**
   * Get approvedByUsername
   *
   * @return approvedByUsername
   */
  @Schema(
      name = "approvedByUsername",
      example = "admin",
      requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("approvedByUsername")
  public String getApprovedByUsername() {

    return approvedByUsername;
  }

  public void setApprovedByUsername(String approvedByUsername) {

    this.approvedByUsername = approvedByUsername;
  }

  public GetLoansLoanIdTimeline approvedOnDate(LocalDate approvedOnDate) {

    this.approvedOnDate = approvedOnDate;
    return this;
  }

  /**
   * Get approvedOnDate
   *
   * @return approvedOnDate
   */
  @Valid
  @Schema(name = "approvedOnDate", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("approvedOnDate")
  public LocalDate getApprovedOnDate() {

    return approvedOnDate;
  }

  public void setApprovedOnDate(LocalDate approvedOnDate) {

    this.approvedOnDate = approvedOnDate;
  }

  public GetLoansLoanIdTimeline closedOnDate(LocalDate closedOnDate) {

    this.closedOnDate = closedOnDate;
    return this;
  }

  /**
   * Get closedOnDate
   *
   * @return closedOnDate
   */
  @Valid
  @Schema(name = "closedOnDate", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("closedOnDate")
  public LocalDate getClosedOnDate() {

    return closedOnDate;
  }

  public void setClosedOnDate(LocalDate closedOnDate) {

    this.closedOnDate = closedOnDate;
  }

  public GetLoansLoanIdTimeline disbursedByFirstname(String disbursedByFirstname) {

    this.disbursedByFirstname = disbursedByFirstname;
    return this;
  }

  /**
   * Get disbursedByFirstname
   *
   * @return disbursedByFirstname
   */
  @Schema(
      name = "disbursedByFirstname",
      example = "App",
      requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("disbursedByFirstname")
  public String getDisbursedByFirstname() {

    return disbursedByFirstname;
  }

  public void setDisbursedByFirstname(String disbursedByFirstname) {

    this.disbursedByFirstname = disbursedByFirstname;
  }

  public GetLoansLoanIdTimeline disbursedByLastname(String disbursedByLastname) {

    this.disbursedByLastname = disbursedByLastname;
    return this;
  }

  /**
   * Get disbursedByLastname
   *
   * @return disbursedByLastname
   */
  @Schema(
      name = "disbursedByLastname",
      example = "Administrator",
      requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("disbursedByLastname")
  public String getDisbursedByLastname() {

    return disbursedByLastname;
  }

  public void setDisbursedByLastname(String disbursedByLastname) {

    this.disbursedByLastname = disbursedByLastname;
  }

  public GetLoansLoanIdTimeline disbursedByUsername(String disbursedByUsername) {

    this.disbursedByUsername = disbursedByUsername;
    return this;
  }

  /**
   * Get disbursedByUsername
   *
   * @return disbursedByUsername
   */
  @Schema(
      name = "disbursedByUsername",
      example = "admin",
      requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("disbursedByUsername")
  public String getDisbursedByUsername() {

    return disbursedByUsername;
  }

  public void setDisbursedByUsername(String disbursedByUsername) {

    this.disbursedByUsername = disbursedByUsername;
  }

  public GetLoansLoanIdTimeline expectedDisbursementDate(LocalDate expectedDisbursementDate) {

    this.expectedDisbursementDate = expectedDisbursementDate;
    return this;
  }

  /**
   * Get expectedDisbursementDate
   *
   * @return expectedDisbursementDate
   */
  @Valid
  @Schema(name = "expectedDisbursementDate", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("expectedDisbursementDate")
  public LocalDate getExpectedDisbursementDate() {

    return expectedDisbursementDate;
  }

  public void setExpectedDisbursementDate(LocalDate expectedDisbursementDate) {

    this.expectedDisbursementDate = expectedDisbursementDate;
  }

  public GetLoansLoanIdTimeline expectedMaturityDate(LocalDate expectedMaturityDate) {

    this.expectedMaturityDate = expectedMaturityDate;
    return this;
  }

  /**
   * Get expectedMaturityDate
   *
   * @return expectedMaturityDate
   */
  @Valid
  @Schema(name = "expectedMaturityDate", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("expectedMaturityDate")
  public LocalDate getExpectedMaturityDate() {

    return expectedMaturityDate;
  }

  public void setExpectedMaturityDate(LocalDate expectedMaturityDate) {

    this.expectedMaturityDate = expectedMaturityDate;
  }

  public GetLoansLoanIdTimeline submittedByFirstname(String submittedByFirstname) {

    this.submittedByFirstname = submittedByFirstname;
    return this;
  }

  /**
   * Get submittedByFirstname
   *
   * @return submittedByFirstname
   */
  @Schema(
      name = "submittedByFirstname",
      example = "App",
      requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("submittedByFirstname")
  public String getSubmittedByFirstname() {

    return submittedByFirstname;
  }

  public void setSubmittedByFirstname(String submittedByFirstname) {

    this.submittedByFirstname = submittedByFirstname;
  }

  public GetLoansLoanIdTimeline submittedByLastname(String submittedByLastname) {

    this.submittedByLastname = submittedByLastname;
    return this;
  }

  /**
   * Get submittedByLastname
   *
   * @return submittedByLastname
   */
  @Schema(
      name = "submittedByLastname",
      example = "Administrator",
      requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("submittedByLastname")
  public String getSubmittedByLastname() {

    return submittedByLastname;
  }

  public void setSubmittedByLastname(String submittedByLastname) {

    this.submittedByLastname = submittedByLastname;
  }

  public GetLoansLoanIdTimeline submittedByUsername(String submittedByUsername) {

    this.submittedByUsername = submittedByUsername;
    return this;
  }

  /**
   * Get submittedByUsername
   *
   * @return submittedByUsername
   */
  @Schema(
      name = "submittedByUsername",
      example = "admin",
      requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("submittedByUsername")
  public String getSubmittedByUsername() {

    return submittedByUsername;
  }

  public void setSubmittedByUsername(String submittedByUsername) {

    this.submittedByUsername = submittedByUsername;
  }

  public GetLoansLoanIdTimeline submittedOnDate(LocalDate submittedOnDate) {

    this.submittedOnDate = submittedOnDate;
    return this;
  }

  /**
   * Get submittedOnDate
   *
   * @return submittedOnDate
   */
  @Valid
  @Schema(name = "submittedOnDate", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("submittedOnDate")
  public LocalDate getSubmittedOnDate() {

    return submittedOnDate;
  }

  public void setSubmittedOnDate(LocalDate submittedOnDate) {

    this.submittedOnDate = submittedOnDate;
  }

  @Override
  public boolean equals(Object o) {

    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetLoansLoanIdTimeline getLoansLoanIdTimeline = (GetLoansLoanIdTimeline) o;
    return Objects.equals(
            this.actualDisbursementDate, getLoansLoanIdTimeline.actualDisbursementDate)
        && Objects.equals(this.approvedByFirstname, getLoansLoanIdTimeline.approvedByFirstname)
        && Objects.equals(this.approvedByLastname, getLoansLoanIdTimeline.approvedByLastname)
        && Objects.equals(this.approvedByUsername, getLoansLoanIdTimeline.approvedByUsername)
        && Objects.equals(this.approvedOnDate, getLoansLoanIdTimeline.approvedOnDate)
        && Objects.equals(this.closedOnDate, getLoansLoanIdTimeline.closedOnDate)
        && Objects.equals(this.disbursedByFirstname, getLoansLoanIdTimeline.disbursedByFirstname)
        && Objects.equals(this.disbursedByLastname, getLoansLoanIdTimeline.disbursedByLastname)
        && Objects.equals(this.disbursedByUsername, getLoansLoanIdTimeline.disbursedByUsername)
        && Objects.equals(
            this.expectedDisbursementDate, getLoansLoanIdTimeline.expectedDisbursementDate)
        && Objects.equals(this.expectedMaturityDate, getLoansLoanIdTimeline.expectedMaturityDate)
        && Objects.equals(this.submittedByFirstname, getLoansLoanIdTimeline.submittedByFirstname)
        && Objects.equals(this.submittedByLastname, getLoansLoanIdTimeline.submittedByLastname)
        && Objects.equals(this.submittedByUsername, getLoansLoanIdTimeline.submittedByUsername)
        && Objects.equals(this.submittedOnDate, getLoansLoanIdTimeline.submittedOnDate);
  }

  @Override
  public int hashCode() {

    return Objects.hash(
        actualDisbursementDate,
        approvedByFirstname,
        approvedByLastname,
        approvedByUsername,
        approvedOnDate,
        closedOnDate,
        disbursedByFirstname,
        disbursedByLastname,
        disbursedByUsername,
        expectedDisbursementDate,
        expectedMaturityDate,
        submittedByFirstname,
        submittedByLastname,
        submittedByUsername,
        submittedOnDate);
  }

  @Override
  public String toString() {

    StringBuilder sb = new StringBuilder();
    sb.append("class GetLoansLoanIdTimeline {\n");
    sb.append("    actualDisbursementDate: ")
        .append(toIndentedString(actualDisbursementDate))
        .append("\n");
    sb.append("    approvedByFirstname: ")
        .append(toIndentedString(approvedByFirstname))
        .append("\n");
    sb.append("    approvedByLastname: ").append(toIndentedString(approvedByLastname)).append("\n");
    sb.append("    approvedByUsername: ").append(toIndentedString(approvedByUsername)).append("\n");
    sb.append("    approvedOnDate: ").append(toIndentedString(approvedOnDate)).append("\n");
    sb.append("    closedOnDate: ").append(toIndentedString(closedOnDate)).append("\n");
    sb.append("    disbursedByFirstname: ")
        .append(toIndentedString(disbursedByFirstname))
        .append("\n");
    sb.append("    disbursedByLastname: ")
        .append(toIndentedString(disbursedByLastname))
        .append("\n");
    sb.append("    disbursedByUsername: ")
        .append(toIndentedString(disbursedByUsername))
        .append("\n");
    sb.append("    expectedDisbursementDate: ")
        .append(toIndentedString(expectedDisbursementDate))
        .append("\n");
    sb.append("    expectedMaturityDate: ")
        .append(toIndentedString(expectedMaturityDate))
        .append("\n");
    sb.append("    submittedByFirstname: ")
        .append(toIndentedString(submittedByFirstname))
        .append("\n");
    sb.append("    submittedByLastname: ")
        .append(toIndentedString(submittedByLastname))
        .append("\n");
    sb.append("    submittedByUsername: ")
        .append(toIndentedString(submittedByUsername))
        .append("\n");
    sb.append("    submittedOnDate: ").append(toIndentedString(submittedOnDate)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces (except the first line).
   */
  private String toIndentedString(Object o) {

    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
