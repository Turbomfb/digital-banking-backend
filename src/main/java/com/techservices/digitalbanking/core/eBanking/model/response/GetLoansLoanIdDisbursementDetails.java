/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.Objects;
import org.springframework.format.annotation.DateTimeFormat;

/** GetLoansLoanIdDisbursementDetails */
@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]",
    comments = "Generator version: 7.5.0")
public class GetLoansLoanIdDisbursementDetails {

  private Double approvedPrincipal;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate expectedDisbursementDate;

  private Integer id;

  private Double principal;

  public GetLoansLoanIdDisbursementDetails approvedPrincipal(Double approvedPrincipal) {

    this.approvedPrincipal = approvedPrincipal;
    return this;
  }

  /**
   * Get approvedPrincipal
   *
   * @return approvedPrincipal
   */
  @Schema(
      name = "approvedPrincipal",
      example = "22000.0",
      requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("approvedPrincipal")
  public Double getApprovedPrincipal() {

    return approvedPrincipal;
  }

  public void setApprovedPrincipal(Double approvedPrincipal) {

    this.approvedPrincipal = approvedPrincipal;
  }

  public GetLoansLoanIdDisbursementDetails expectedDisbursementDate(
      LocalDate expectedDisbursementDate) {

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

  public GetLoansLoanIdDisbursementDetails id(Integer id) {

    this.id = id;
    return this;
  }

  /**
   * Get id
   *
   * @return id
   */
  @Schema(name = "id", example = "71", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public Integer getId() {

    return id;
  }

  public void setId(Integer id) {

    this.id = id;
  }

  public GetLoansLoanIdDisbursementDetails principal(Double principal) {

    this.principal = principal;
    return this;
  }

  /**
   * Get principal
   *
   * @return principal
   */
  @Schema(name = "principal", example = "22000.0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("principal")
  public Double getPrincipal() {

    return principal;
  }

  public void setPrincipal(Double principal) {

    this.principal = principal;
  }

  @Override
  public boolean equals(Object o) {

    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetLoansLoanIdDisbursementDetails getLoansLoanIdDisbursementDetails =
        (GetLoansLoanIdDisbursementDetails) o;
    return Objects.equals(
            this.approvedPrincipal, getLoansLoanIdDisbursementDetails.approvedPrincipal)
        && Objects.equals(
            this.expectedDisbursementDate,
            getLoansLoanIdDisbursementDetails.expectedDisbursementDate)
        && Objects.equals(this.id, getLoansLoanIdDisbursementDetails.id)
        && Objects.equals(this.principal, getLoansLoanIdDisbursementDetails.principal);
  }

  @Override
  public int hashCode() {

    return Objects.hash(approvedPrincipal, expectedDisbursementDate, id, principal);
  }

  @Override
  public String toString() {

    StringBuilder sb = new StringBuilder();
    sb.append("class GetLoansLoanIdDisbursementDetails {\n");
    sb.append("    approvedPrincipal: ").append(toIndentedString(approvedPrincipal)).append("\n");
    sb.append("    expectedDisbursementDate: ")
        .append(toIndentedString(expectedDisbursementDate))
        .append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    principal: ").append(toIndentedString(principal)).append("\n");
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
