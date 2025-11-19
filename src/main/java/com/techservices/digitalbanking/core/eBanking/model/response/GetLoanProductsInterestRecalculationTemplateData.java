/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import java.util.Objects;

/** GetLoanProductsInterestRecalculationTemplateData */
@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]",
    comments = "Generator version: 7.5.0")
public class GetLoanProductsInterestRecalculationTemplateData {

  private GetLoanProductsInterestRecalculationCompoundingType interestRecalculationCompoundingType;

  private GetLoanProductsPreClosureInterestCalculationStrategy
      preClosureInterestCalculationStrategy;

  private GetLoanProductsRescheduleStrategyType rescheduleStrategyType;

  public GetLoanProductsInterestRecalculationTemplateData interestRecalculationCompoundingType(
      GetLoanProductsInterestRecalculationCompoundingType interestRecalculationCompoundingType) {

    this.interestRecalculationCompoundingType = interestRecalculationCompoundingType;
    return this;
  }

  /**
   * Get interestRecalculationCompoundingType
   *
   * @return interestRecalculationCompoundingType
   */
  @Valid
  @Schema(
      name = "interestRecalculationCompoundingType",
      requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("interestRecalculationCompoundingType")
  public GetLoanProductsInterestRecalculationCompoundingType
      getInterestRecalculationCompoundingType() {

    return interestRecalculationCompoundingType;
  }

  public void setInterestRecalculationCompoundingType(
      GetLoanProductsInterestRecalculationCompoundingType interestRecalculationCompoundingType) {

    this.interestRecalculationCompoundingType = interestRecalculationCompoundingType;
  }

  public GetLoanProductsInterestRecalculationTemplateData preClosureInterestCalculationStrategy(
      GetLoanProductsPreClosureInterestCalculationStrategy preClosureInterestCalculationStrategy) {

    this.preClosureInterestCalculationStrategy = preClosureInterestCalculationStrategy;
    return this;
  }

  /**
   * Get preClosureInterestCalculationStrategy
   *
   * @return preClosureInterestCalculationStrategy
   */
  @Valid
  @Schema(
      name = "preClosureInterestCalculationStrategy",
      requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("preClosureInterestCalculationStrategy")
  public GetLoanProductsPreClosureInterestCalculationStrategy
      getPreClosureInterestCalculationStrategy() {

    return preClosureInterestCalculationStrategy;
  }

  public void setPreClosureInterestCalculationStrategy(
      GetLoanProductsPreClosureInterestCalculationStrategy preClosureInterestCalculationStrategy) {

    this.preClosureInterestCalculationStrategy = preClosureInterestCalculationStrategy;
  }

  public GetLoanProductsInterestRecalculationTemplateData rescheduleStrategyType(
      GetLoanProductsRescheduleStrategyType rescheduleStrategyType) {

    this.rescheduleStrategyType = rescheduleStrategyType;
    return this;
  }

  /**
   * Get rescheduleStrategyType
   *
   * @return rescheduleStrategyType
   */
  @Valid
  @Schema(name = "rescheduleStrategyType", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("rescheduleStrategyType")
  public GetLoanProductsRescheduleStrategyType getRescheduleStrategyType() {

    return rescheduleStrategyType;
  }

  public void setRescheduleStrategyType(
      GetLoanProductsRescheduleStrategyType rescheduleStrategyType) {

    this.rescheduleStrategyType = rescheduleStrategyType;
  }

  @Override
  public boolean equals(Object o) {

    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetLoanProductsInterestRecalculationTemplateData
        getLoanProductsInterestRecalculationTemplateData =
            (GetLoanProductsInterestRecalculationTemplateData) o;
    return Objects.equals(
            this.interestRecalculationCompoundingType,
            getLoanProductsInterestRecalculationTemplateData.interestRecalculationCompoundingType)
        && Objects.equals(
            this.preClosureInterestCalculationStrategy,
            getLoanProductsInterestRecalculationTemplateData.preClosureInterestCalculationStrategy)
        && Objects.equals(
            this.rescheduleStrategyType,
            getLoanProductsInterestRecalculationTemplateData.rescheduleStrategyType);
  }

  @Override
  public int hashCode() {

    return Objects.hash(
        interestRecalculationCompoundingType,
        preClosureInterestCalculationStrategy,
        rescheduleStrategyType);
  }

  @Override
  public String toString() {

    StringBuilder sb = new StringBuilder();
    sb.append("class GetLoanProductsInterestRecalculationTemplateData {\n");
    sb.append("    interestRecalculationCompoundingType: ")
        .append(toIndentedString(interestRecalculationCompoundingType))
        .append("\n");
    sb.append("    preClosureInterestCalculationStrategy: ")
        .append(toIndentedString(preClosureInterestCalculationStrategy))
        .append("\n");
    sb.append("    rescheduleStrategyType: ")
        .append(toIndentedString(rescheduleStrategyType))
        .append("\n");
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
