/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import java.util.Objects;

/** PutLoansLoanIdChargesChargeIdRequest */
@Schema(
    name = "PutLoansLoanIdChargesChargeIdRequest",
    description = " PutLoansLoanIdChargesChargeIdRequest")
@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]",
    comments = "Generator version: 7.5.0")
public class PutLoansLoanIdChargesChargeIdRequest {

  private Double amount;

  private String dateFormat;

  private String dueDate;

  private String locale;

  public PutLoansLoanIdChargesChargeIdRequest amount(Double amount) {

    this.amount = amount;
    return this;
  }

  /**
   * Get amount
   *
   * @return amount
   */
  @Schema(name = "amount", example = "60", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("amount")
  public Double getAmount() {

    return amount;
  }

  public void setAmount(Double amount) {

    this.amount = amount;
  }

  public PutLoansLoanIdChargesChargeIdRequest dateFormat(String dateFormat) {

    this.dateFormat = dateFormat;
    return this;
  }

  /**
   * Get dateFormat
   *
   * @return dateFormat
   */
  @Schema(
      name = "dateFormat",
      example = "dd MMMM yyyy",
      requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("dateFormat")
  public String getDateFormat() {

    return dateFormat;
  }

  public void setDateFormat(String dateFormat) {

    this.dateFormat = dateFormat;
  }

  public PutLoansLoanIdChargesChargeIdRequest dueDate(String dueDate) {

    this.dueDate = dueDate;
    return this;
  }

  /**
   * Get dueDate
   *
   * @return dueDate
   */
  @Schema(
      name = "dueDate",
      example = "27 March 2013",
      requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("dueDate")
  public String getDueDate() {

    return dueDate;
  }

  public void setDueDate(String dueDate) {

    this.dueDate = dueDate;
  }

  public PutLoansLoanIdChargesChargeIdRequest locale(String locale) {

    this.locale = locale;
    return this;
  }

  /**
   * Get locale
   *
   * @return locale
   */
  @Schema(name = "locale", example = "en", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("locale")
  public String getLocale() {

    return locale;
  }

  public void setLocale(String locale) {

    this.locale = locale;
  }

  @Override
  public boolean equals(Object o) {

    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PutLoansLoanIdChargesChargeIdRequest putLoansLoanIdChargesChargeIdRequest =
        (PutLoansLoanIdChargesChargeIdRequest) o;
    return Objects.equals(this.amount, putLoansLoanIdChargesChargeIdRequest.amount)
        && Objects.equals(this.dateFormat, putLoansLoanIdChargesChargeIdRequest.dateFormat)
        && Objects.equals(this.dueDate, putLoansLoanIdChargesChargeIdRequest.dueDate)
        && Objects.equals(this.locale, putLoansLoanIdChargesChargeIdRequest.locale);
  }

  @Override
  public int hashCode() {

    return Objects.hash(amount, dateFormat, dueDate, locale);
  }

  @Override
  public String toString() {

    StringBuilder sb = new StringBuilder();
    sb.append("class PutLoansLoanIdChargesChargeIdRequest {\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    dateFormat: ").append(toIndentedString(dateFormat)).append("\n");
    sb.append("    dueDate: ").append(toIndentedString(dueDate)).append("\n");
    sb.append("    locale: ").append(toIndentedString(locale)).append("\n");
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
