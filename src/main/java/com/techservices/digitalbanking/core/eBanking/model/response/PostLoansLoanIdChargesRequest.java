/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import java.util.Objects;

/** PostLoansLoanIdChargesRequest */
@Schema(name = "PostLoansLoanIdChargesRequest", description = " PostLoansLoanIdChargesRequest")
@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]",
    comments = "Generator version: 7.5.0")
public class PostLoansLoanIdChargesRequest {

  private Double amount;

  private Integer chargeId;

  private String dateFormat;

  private String dueDate;

  private String locale;

  public PostLoansLoanIdChargesRequest amount(Double amount) {

    this.amount = amount;
    return this;
  }

  /**
   * Get amount
   *
   * @return amount
   */
  @Schema(name = "amount", example = "100", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("amount")
  public Double getAmount() {

    return amount;
  }

  public void setAmount(Double amount) {

    this.amount = amount;
  }

  public PostLoansLoanIdChargesRequest chargeId(Integer chargeId) {

    this.chargeId = chargeId;
    return this;
  }

  /**
   * Get chargeId
   *
   * @return chargeId
   */
  @Schema(name = "chargeId", example = "2", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("chargeId")
  public Integer getChargeId() {

    return chargeId;
  }

  public void setChargeId(Integer chargeId) {

    this.chargeId = chargeId;
  }

  public PostLoansLoanIdChargesRequest dateFormat(String dateFormat) {

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

  public PostLoansLoanIdChargesRequest dueDate(String dueDate) {

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
      example = "29 April 2013",
      requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("dueDate")
  public String getDueDate() {

    return dueDate;
  }

  public void setDueDate(String dueDate) {

    this.dueDate = dueDate;
  }

  public PostLoansLoanIdChargesRequest locale(String locale) {

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
    PostLoansLoanIdChargesRequest postLoansLoanIdChargesRequest = (PostLoansLoanIdChargesRequest) o;
    return Objects.equals(this.amount, postLoansLoanIdChargesRequest.amount)
        && Objects.equals(this.chargeId, postLoansLoanIdChargesRequest.chargeId)
        && Objects.equals(this.dateFormat, postLoansLoanIdChargesRequest.dateFormat)
        && Objects.equals(this.dueDate, postLoansLoanIdChargesRequest.dueDate)
        && Objects.equals(this.locale, postLoansLoanIdChargesRequest.locale);
  }

  @Override
  public int hashCode() {

    return Objects.hash(amount, chargeId, dateFormat, dueDate, locale);
  }

  @Override
  public String toString() {

    StringBuilder sb = new StringBuilder();
    sb.append("class PostLoansLoanIdChargesRequest {\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    chargeId: ").append(toIndentedString(chargeId)).append("\n");
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
