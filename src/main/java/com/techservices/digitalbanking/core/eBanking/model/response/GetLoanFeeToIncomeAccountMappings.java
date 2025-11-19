/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import java.util.Objects;

/** GetLoanFeeToIncomeAccountMappings */
@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]",
    comments = "Generator version: 7.5.0")
public class GetLoanFeeToIncomeAccountMappings {

  private GetLoanCharge charge;

  private GetLoanIncomeFromFeeAccount incomeAccount;

  public GetLoanFeeToIncomeAccountMappings charge(GetLoanCharge charge) {

    this.charge = charge;
    return this;
  }

  /**
   * Get charge
   *
   * @return charge
   */
  @Valid
  @Schema(name = "charge", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("charge")
  public GetLoanCharge getCharge() {

    return charge;
  }

  public void setCharge(GetLoanCharge charge) {

    this.charge = charge;
  }

  public GetLoanFeeToIncomeAccountMappings incomeAccount(
      GetLoanIncomeFromFeeAccount incomeAccount) {

    this.incomeAccount = incomeAccount;
    return this;
  }

  /**
   * Get incomeAccount
   *
   * @return incomeAccount
   */
  @Valid
  @Schema(name = "incomeAccount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("incomeAccount")
  public GetLoanIncomeFromFeeAccount getIncomeAccount() {

    return incomeAccount;
  }

  public void setIncomeAccount(GetLoanIncomeFromFeeAccount incomeAccount) {

    this.incomeAccount = incomeAccount;
  }

  @Override
  public boolean equals(Object o) {

    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetLoanFeeToIncomeAccountMappings getLoanFeeToIncomeAccountMappings =
        (GetLoanFeeToIncomeAccountMappings) o;
    return Objects.equals(this.charge, getLoanFeeToIncomeAccountMappings.charge)
        && Objects.equals(this.incomeAccount, getLoanFeeToIncomeAccountMappings.incomeAccount);
  }

  @Override
  public int hashCode() {

    return Objects.hash(charge, incomeAccount);
  }

  @Override
  public String toString() {

    StringBuilder sb = new StringBuilder();
    sb.append("class GetLoanFeeToIncomeAccountMappings {\n");
    sb.append("    charge: ").append(toIndentedString(charge)).append("\n");
    sb.append("    incomeAccount: ").append(toIndentedString(incomeAccount)).append("\n");
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
