/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import java.util.Objects;

/** PostSelfLoansLoanIdRequest */
@Schema(name = "PostSelfLoansLoanIdRequest", description = "PostSelfLoansLoanIdRequest")
@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]",
    comments = "Generator version: 7.5.0")
public class PostSelfLoansLoanIdRequest {

  private String dateFormat;

  private String locale;

  private String note;

  private String withdrawnOnDate;

  public PostSelfLoansLoanIdRequest dateFormat(String dateFormat) {

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

  public PostSelfLoansLoanIdRequest locale(String locale) {

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

  public PostSelfLoansLoanIdRequest note(String note) {

    this.note = note;
    return this;
  }

  /**
   * Get note
   *
   * @return note
   */
  @Schema(
      name = "note",
      example = "Reason loan applicant withdrew from application.",
      requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("note")
  public String getNote() {

    return note;
  }

  public void setNote(String note) {

    this.note = note;
  }

  public PostSelfLoansLoanIdRequest withdrawnOnDate(String withdrawnOnDate) {

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
    PostSelfLoansLoanIdRequest postSelfLoansLoanIdRequest = (PostSelfLoansLoanIdRequest) o;
    return Objects.equals(this.dateFormat, postSelfLoansLoanIdRequest.dateFormat)
        && Objects.equals(this.locale, postSelfLoansLoanIdRequest.locale)
        && Objects.equals(this.note, postSelfLoansLoanIdRequest.note)
        && Objects.equals(this.withdrawnOnDate, postSelfLoansLoanIdRequest.withdrawnOnDate);
  }

  @Override
  public int hashCode() {

    return Objects.hash(dateFormat, locale, note, withdrawnOnDate);
  }

  @Override
  public String toString() {

    StringBuilder sb = new StringBuilder();
    sb.append("class PostSelfLoansLoanIdRequest {\n");
    sb.append("    dateFormat: ").append(toIndentedString(dateFormat)).append("\n");
    sb.append("    locale: ").append(toIndentedString(locale)).append("\n");
    sb.append("    note: ").append(toIndentedString(note)).append("\n");
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
