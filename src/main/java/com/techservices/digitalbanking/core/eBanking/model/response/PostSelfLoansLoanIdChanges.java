/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import java.util.Objects;

/** PostSelfLoansLoanIdChanges */
@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]",
    comments = "Generator version: 7.5.0")
public class PostSelfLoansLoanIdChanges {

  private String closedOnDate;

  private String dateFormat;

  private String locale;

  private PostSelfLoansLoanIdStatus status;

  private String withdrawnOnDate;

  public PostSelfLoansLoanIdChanges closedOnDate(String closedOnDate) {

    this.closedOnDate = closedOnDate;
    return this;
  }

  /**
   * Get closedOnDate
   *
   * @return closedOnDate
   */
  @Schema(
      name = "closedOnDate",
      example = "20 September 2011",
      requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("closedOnDate")
  public String getClosedOnDate() {

    return closedOnDate;
  }

  public void setClosedOnDate(String closedOnDate) {

    this.closedOnDate = closedOnDate;
  }

  public PostSelfLoansLoanIdChanges dateFormat(String dateFormat) {

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

  public PostSelfLoansLoanIdChanges locale(String locale) {

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

  public PostSelfLoansLoanIdChanges status(PostSelfLoansLoanIdStatus status) {

    this.status = status;
    return this;
  }

  /**
   * Get status
   *
   * @return status
   */
  @Valid
  @Schema(name = "status", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("status")
  public PostSelfLoansLoanIdStatus getStatus() {

    return status;
  }

  public void setStatus(PostSelfLoansLoanIdStatus status) {

    this.status = status;
  }

  public PostSelfLoansLoanIdChanges withdrawnOnDate(String withdrawnOnDate) {

    this.withdrawnOnDate = withdrawnOnDate;
    return this;
  }

  /**
   * Get withdrawnOnDate
   *
   * @return withdrawnOnDate
   */
  @Schema(
      name = "withdrawnOnDate",
      example = "20 September 2011",
      requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("withdrawnOnDate")
  public String getWithdrawnOnDate() {

    return withdrawnOnDate;
  }

  public void setWithdrawnOnDate(String withdrawnOnDate) {

    this.withdrawnOnDate = withdrawnOnDate;
  }

  @Override
  public boolean equals(Object o) {

    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PostSelfLoansLoanIdChanges postSelfLoansLoanIdChanges = (PostSelfLoansLoanIdChanges) o;
    return Objects.equals(this.closedOnDate, postSelfLoansLoanIdChanges.closedOnDate)
        && Objects.equals(this.dateFormat, postSelfLoansLoanIdChanges.dateFormat)
        && Objects.equals(this.locale, postSelfLoansLoanIdChanges.locale)
        && Objects.equals(this.status, postSelfLoansLoanIdChanges.status)
        && Objects.equals(this.withdrawnOnDate, postSelfLoansLoanIdChanges.withdrawnOnDate);
  }

  @Override
  public int hashCode() {

    return Objects.hash(closedOnDate, dateFormat, locale, status, withdrawnOnDate);
  }

  @Override
  public String toString() {

    StringBuilder sb = new StringBuilder();
    sb.append("class PostSelfLoansLoanIdChanges {\n");
    sb.append("    closedOnDate: ").append(toIndentedString(closedOnDate)).append("\n");
    sb.append("    dateFormat: ").append(toIndentedString(dateFormat)).append("\n");
    sb.append("    locale: ").append(toIndentedString(locale)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    withdrawnOnDate: ").append(toIndentedString(withdrawnOnDate)).append("\n");
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
