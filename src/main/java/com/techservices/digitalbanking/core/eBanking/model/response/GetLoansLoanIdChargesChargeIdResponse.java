/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import java.util.Objects;

/** GetLoansLoanIdChargesChargeIdResponse */
@Schema(
    name = "GetLoansLoanIdChargesChargeIdResponse",
    description = "GetLoansLoanIdChargesChargeIdResponse")
@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]",
    comments = "Generator version: 7.5.0")
public class GetLoansLoanIdChargesChargeIdResponse {

  private Double amount;

  private Double amountOrPercentage;

  private Double amountOutstanding;

  private Double amountPaid;

  private Double amountPercentageAppliedTo;

  private Double amountWaived;

  private Double amountWrittenOff;

  private GetLoanChargeCalculationType chargeCalculationType;

  private Integer chargeId;

  private GetLoanChargeTimeType chargeTimeType;

  private GetLoanChargeCurrency currency;

  private Integer id;

  private String name;

  private Boolean penalty;

  private Double percentage;

  public GetLoansLoanIdChargesChargeIdResponse amount(Double amount) {

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

  public GetLoansLoanIdChargesChargeIdResponse amountOrPercentage(Double amountOrPercentage) {

    this.amountOrPercentage = amountOrPercentage;
    return this;
  }

  /**
   * Get amountOrPercentage
   *
   * @return amountOrPercentage
   */
  @Schema(
      name = "amountOrPercentage",
      example = "100",
      requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("amountOrPercentage")
  public Double getAmountOrPercentage() {

    return amountOrPercentage;
  }

  public void setAmountOrPercentage(Double amountOrPercentage) {

    this.amountOrPercentage = amountOrPercentage;
  }

  public GetLoansLoanIdChargesChargeIdResponse amountOutstanding(Double amountOutstanding) {

    this.amountOutstanding = amountOutstanding;
    return this;
  }

  /**
   * Get amountOutstanding
   *
   * @return amountOutstanding
   */
  @Schema(
      name = "amountOutstanding",
      example = "100",
      requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("amountOutstanding")
  public Double getAmountOutstanding() {

    return amountOutstanding;
  }

  public void setAmountOutstanding(Double amountOutstanding) {

    this.amountOutstanding = amountOutstanding;
  }

  public GetLoansLoanIdChargesChargeIdResponse amountPaid(Double amountPaid) {

    this.amountPaid = amountPaid;
    return this;
  }

  /**
   * Get amountPaid
   *
   * @return amountPaid
   */
  @Schema(name = "amountPaid", example = "0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("amountPaid")
  public Double getAmountPaid() {

    return amountPaid;
  }

  public void setAmountPaid(Double amountPaid) {

    this.amountPaid = amountPaid;
  }

  public GetLoansLoanIdChargesChargeIdResponse amountPercentageAppliedTo(
      Double amountPercentageAppliedTo) {

    this.amountPercentageAppliedTo = amountPercentageAppliedTo;
    return this;
  }

  /**
   * Get amountPercentageAppliedTo
   *
   * @return amountPercentageAppliedTo
   */
  @Schema(
      name = "amountPercentageAppliedTo",
      example = "0",
      requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("amountPercentageAppliedTo")
  public Double getAmountPercentageAppliedTo() {

    return amountPercentageAppliedTo;
  }

  public void setAmountPercentageAppliedTo(Double amountPercentageAppliedTo) {

    this.amountPercentageAppliedTo = amountPercentageAppliedTo;
  }

  public GetLoansLoanIdChargesChargeIdResponse amountWaived(Double amountWaived) {

    this.amountWaived = amountWaived;
    return this;
  }

  /**
   * Get amountWaived
   *
   * @return amountWaived
   */
  @Schema(name = "amountWaived", example = "0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("amountWaived")
  public Double getAmountWaived() {

    return amountWaived;
  }

  public void setAmountWaived(Double amountWaived) {

    this.amountWaived = amountWaived;
  }

  public GetLoansLoanIdChargesChargeIdResponse amountWrittenOff(Double amountWrittenOff) {

    this.amountWrittenOff = amountWrittenOff;
    return this;
  }

  /**
   * Get amountWrittenOff
   *
   * @return amountWrittenOff
   */
  @Schema(name = "amountWrittenOff", example = "0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("amountWrittenOff")
  public Double getAmountWrittenOff() {

    return amountWrittenOff;
  }

  public void setAmountWrittenOff(Double amountWrittenOff) {

    this.amountWrittenOff = amountWrittenOff;
  }

  public GetLoansLoanIdChargesChargeIdResponse chargeCalculationType(
      GetLoanChargeCalculationType chargeCalculationType) {

    this.chargeCalculationType = chargeCalculationType;
    return this;
  }

  /**
   * Get chargeCalculationType
   *
   * @return chargeCalculationType
   */
  @Valid
  @Schema(name = "chargeCalculationType", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("chargeCalculationType")
  public GetLoanChargeCalculationType getChargeCalculationType() {

    return chargeCalculationType;
  }

  public void setChargeCalculationType(GetLoanChargeCalculationType chargeCalculationType) {

    this.chargeCalculationType = chargeCalculationType;
  }

  public GetLoansLoanIdChargesChargeIdResponse chargeId(Integer chargeId) {

    this.chargeId = chargeId;
    return this;
  }

  /**
   * Get chargeId
   *
   * @return chargeId
   */
  @Schema(name = "chargeId", example = "1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("chargeId")
  public Integer getChargeId() {

    return chargeId;
  }

  public void setChargeId(Integer chargeId) {

    this.chargeId = chargeId;
  }

  public GetLoansLoanIdChargesChargeIdResponse chargeTimeType(
      GetLoanChargeTimeType chargeTimeType) {

    this.chargeTimeType = chargeTimeType;
    return this;
  }

  /**
   * Get chargeTimeType
   *
   * @return chargeTimeType
   */
  @Valid
  @Schema(name = "chargeTimeType", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("chargeTimeType")
  public GetLoanChargeTimeType getChargeTimeType() {

    return chargeTimeType;
  }

  public void setChargeTimeType(GetLoanChargeTimeType chargeTimeType) {

    this.chargeTimeType = chargeTimeType;
  }

  public GetLoansLoanIdChargesChargeIdResponse currency(GetLoanChargeCurrency currency) {

    this.currency = currency;
    return this;
  }

  /**
   * Get currency
   *
   * @return currency
   */
  @Valid
  @Schema(name = "currency", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("currency")
  public GetLoanChargeCurrency getCurrency() {

    return currency;
  }

  public void setCurrency(GetLoanChargeCurrency currency) {

    this.currency = currency;
  }

  public GetLoansLoanIdChargesChargeIdResponse id(Integer id) {

    this.id = id;
    return this;
  }

  /**
   * Get id
   *
   * @return id
   */
  @Schema(name = "id", example = "1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public Integer getId() {

    return id;
  }

  public void setId(Integer id) {

    this.id = id;
  }

  public GetLoansLoanIdChargesChargeIdResponse name(String name) {

    this.name = name;
    return this;
  }

  /**
   * Get name
   *
   * @return name
   */
  @Schema(
      name = "name",
      example = "Loan Processing fee",
      requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("name")
  public String getName() {

    return name;
  }

  public void setName(String name) {

    this.name = name;
  }

  public GetLoansLoanIdChargesChargeIdResponse penalty(Boolean penalty) {

    this.penalty = penalty;
    return this;
  }

  /**
   * Get penalty
   *
   * @return penalty
   */
  @Schema(name = "penalty", example = "false", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("penalty")
  public Boolean getPenalty() {

    return penalty;
  }

  public void setPenalty(Boolean penalty) {

    this.penalty = penalty;
  }

  public GetLoansLoanIdChargesChargeIdResponse percentage(Double percentage) {

    this.percentage = percentage;
    return this;
  }

  /**
   * Get percentage
   *
   * @return percentage
   */
  @Schema(name = "percentage", example = "0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("percentage")
  public Double getPercentage() {

    return percentage;
  }

  public void setPercentage(Double percentage) {

    this.percentage = percentage;
  }

  @Override
  public boolean equals(Object o) {

    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetLoansLoanIdChargesChargeIdResponse getLoansLoanIdChargesChargeIdResponse =
        (GetLoansLoanIdChargesChargeIdResponse) o;
    return Objects.equals(this.amount, getLoansLoanIdChargesChargeIdResponse.amount)
        && Objects.equals(
            this.amountOrPercentage, getLoansLoanIdChargesChargeIdResponse.amountOrPercentage)
        && Objects.equals(
            this.amountOutstanding, getLoansLoanIdChargesChargeIdResponse.amountOutstanding)
        && Objects.equals(this.amountPaid, getLoansLoanIdChargesChargeIdResponse.amountPaid)
        && Objects.equals(
            this.amountPercentageAppliedTo,
            getLoansLoanIdChargesChargeIdResponse.amountPercentageAppliedTo)
        && Objects.equals(this.amountWaived, getLoansLoanIdChargesChargeIdResponse.amountWaived)
        && Objects.equals(
            this.amountWrittenOff, getLoansLoanIdChargesChargeIdResponse.amountWrittenOff)
        && Objects.equals(
            this.chargeCalculationType, getLoansLoanIdChargesChargeIdResponse.chargeCalculationType)
        && Objects.equals(this.chargeId, getLoansLoanIdChargesChargeIdResponse.chargeId)
        && Objects.equals(this.chargeTimeType, getLoansLoanIdChargesChargeIdResponse.chargeTimeType)
        && Objects.equals(this.currency, getLoansLoanIdChargesChargeIdResponse.currency)
        && Objects.equals(this.id, getLoansLoanIdChargesChargeIdResponse.id)
        && Objects.equals(this.name, getLoansLoanIdChargesChargeIdResponse.name)
        && Objects.equals(this.penalty, getLoansLoanIdChargesChargeIdResponse.penalty)
        && Objects.equals(this.percentage, getLoansLoanIdChargesChargeIdResponse.percentage);
  }

  @Override
  public int hashCode() {

    return Objects.hash(
        amount,
        amountOrPercentage,
        amountOutstanding,
        amountPaid,
        amountPercentageAppliedTo,
        amountWaived,
        amountWrittenOff,
        chargeCalculationType,
        chargeId,
        chargeTimeType,
        currency,
        id,
        name,
        penalty,
        percentage);
  }

  @Override
  public String toString() {

    StringBuilder sb = new StringBuilder();
    sb.append("class GetLoansLoanIdChargesChargeIdResponse {\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    amountOrPercentage: ").append(toIndentedString(amountOrPercentage)).append("\n");
    sb.append("    amountOutstanding: ").append(toIndentedString(amountOutstanding)).append("\n");
    sb.append("    amountPaid: ").append(toIndentedString(amountPaid)).append("\n");
    sb.append("    amountPercentageAppliedTo: ")
        .append(toIndentedString(amountPercentageAppliedTo))
        .append("\n");
    sb.append("    amountWaived: ").append(toIndentedString(amountWaived)).append("\n");
    sb.append("    amountWrittenOff: ").append(toIndentedString(amountWrittenOff)).append("\n");
    sb.append("    chargeCalculationType: ")
        .append(toIndentedString(chargeCalculationType))
        .append("\n");
    sb.append("    chargeId: ").append(toIndentedString(chargeId)).append("\n");
    sb.append("    chargeTimeType: ").append(toIndentedString(chargeTimeType)).append("\n");
    sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    penalty: ").append(toIndentedString(penalty)).append("\n");
    sb.append("    percentage: ").append(toIndentedString(percentage)).append("\n");
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
