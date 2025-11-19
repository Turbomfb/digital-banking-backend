/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import java.util.Objects;

/** GetLoanProductsInterestRecalculationData */
@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]",
    comments = "Generator version: 7.5.0")
public class GetLoanProductsInterestRecalculationData {

  private Integer id;

  private GetLoanProductsInterestRecalculationCompoundingFrequencyType
      interestRecalculationCompoundingFrequencyType;

  private GetLoanProductsInterestRecalculationCompoundingType interestRecalculationCompoundingType;

  private Boolean isArrearsBasedOnOriginalSchedule;

  private GetLoanProductsPreClosureInterestCalculationStrategy
      preClosureInterestCalculationStrategy;

  private Integer productId;

  private GetLoanProductsInterestRecalculationCompoundingFrequencyType
      recalculationRestFrequencyType;

  private GetLoanProductsRescheduleStrategyType rescheduleStrategyType;

  public GetLoanProductsInterestRecalculationData id(Integer id) {

    this.id = id;
    return this;
  }

  /**
   * Get id
   *
   * @return id
   */
  @Schema(name = "id", example = "3", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public Integer getId() {

    return id;
  }

  public void setId(Integer id) {

    this.id = id;
  }

  public GetLoanProductsInterestRecalculationData interestRecalculationCompoundingFrequencyType(
      GetLoanProductsInterestRecalculationCompoundingFrequencyType
          interestRecalculationCompoundingFrequencyType) {

    this.interestRecalculationCompoundingFrequencyType =
        interestRecalculationCompoundingFrequencyType;
    return this;
  }

  /**
   * Get interestRecalculationCompoundingFrequencyType
   *
   * @return interestRecalculationCompoundingFrequencyType
   */
  @Valid
  @Schema(
      name = "interestRecalculationCompoundingFrequencyType",
      requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("interestRecalculationCompoundingFrequencyType")
  public GetLoanProductsInterestRecalculationCompoundingFrequencyType
      getInterestRecalculationCompoundingFrequencyType() {

    return interestRecalculationCompoundingFrequencyType;
  }

  public void setInterestRecalculationCompoundingFrequencyType(
      GetLoanProductsInterestRecalculationCompoundingFrequencyType
          interestRecalculationCompoundingFrequencyType) {

    this.interestRecalculationCompoundingFrequencyType =
        interestRecalculationCompoundingFrequencyType;
  }

  public GetLoanProductsInterestRecalculationData interestRecalculationCompoundingType(
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

  public GetLoanProductsInterestRecalculationData isArrearsBasedOnOriginalSchedule(
      Boolean isArrearsBasedOnOriginalSchedule) {

    this.isArrearsBasedOnOriginalSchedule = isArrearsBasedOnOriginalSchedule;
    return this;
  }

  /**
   * Get isArrearsBasedOnOriginalSchedule
   *
   * @return isArrearsBasedOnOriginalSchedule
   */
  @Schema(
      name = "isArrearsBasedOnOriginalSchedule",
      example = "true",
      requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("isArrearsBasedOnOriginalSchedule")
  public Boolean getIsArrearsBasedOnOriginalSchedule() {

    return isArrearsBasedOnOriginalSchedule;
  }

  public void setIsArrearsBasedOnOriginalSchedule(Boolean isArrearsBasedOnOriginalSchedule) {

    this.isArrearsBasedOnOriginalSchedule = isArrearsBasedOnOriginalSchedule;
  }

  public GetLoanProductsInterestRecalculationData preClosureInterestCalculationStrategy(
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

  public GetLoanProductsInterestRecalculationData productId(Integer productId) {

    this.productId = productId;
    return this;
  }

  /**
   * Get productId
   *
   * @return productId
   */
  @Schema(name = "productId", example = "1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("productId")
  public Integer getProductId() {

    return productId;
  }

  public void setProductId(Integer productId) {

    this.productId = productId;
  }

  public GetLoanProductsInterestRecalculationData recalculationRestFrequencyType(
      GetLoanProductsInterestRecalculationCompoundingFrequencyType recalculationRestFrequencyType) {

    this.recalculationRestFrequencyType = recalculationRestFrequencyType;
    return this;
  }

  /**
   * Get recalculationRestFrequencyType
   *
   * @return recalculationRestFrequencyType
   */
  @Valid
  @Schema(name = "recalculationRestFrequencyType", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("recalculationRestFrequencyType")
  public GetLoanProductsInterestRecalculationCompoundingFrequencyType
      getRecalculationRestFrequencyType() {

    return recalculationRestFrequencyType;
  }

  public void setRecalculationRestFrequencyType(
      GetLoanProductsInterestRecalculationCompoundingFrequencyType recalculationRestFrequencyType) {

    this.recalculationRestFrequencyType = recalculationRestFrequencyType;
  }

  public GetLoanProductsInterestRecalculationData rescheduleStrategyType(
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
    GetLoanProductsInterestRecalculationData getLoanProductsInterestRecalculationData =
        (GetLoanProductsInterestRecalculationData) o;
    return Objects.equals(this.id, getLoanProductsInterestRecalculationData.id)
        && Objects.equals(
            this.interestRecalculationCompoundingFrequencyType,
            getLoanProductsInterestRecalculationData.interestRecalculationCompoundingFrequencyType)
        && Objects.equals(
            this.interestRecalculationCompoundingType,
            getLoanProductsInterestRecalculationData.interestRecalculationCompoundingType)
        && Objects.equals(
            this.isArrearsBasedOnOriginalSchedule,
            getLoanProductsInterestRecalculationData.isArrearsBasedOnOriginalSchedule)
        && Objects.equals(
            this.preClosureInterestCalculationStrategy,
            getLoanProductsInterestRecalculationData.preClosureInterestCalculationStrategy)
        && Objects.equals(this.productId, getLoanProductsInterestRecalculationData.productId)
        && Objects.equals(
            this.recalculationRestFrequencyType,
            getLoanProductsInterestRecalculationData.recalculationRestFrequencyType)
        && Objects.equals(
            this.rescheduleStrategyType,
            getLoanProductsInterestRecalculationData.rescheduleStrategyType);
  }

  @Override
  public int hashCode() {

    return Objects.hash(
        id,
        interestRecalculationCompoundingFrequencyType,
        interestRecalculationCompoundingType,
        isArrearsBasedOnOriginalSchedule,
        preClosureInterestCalculationStrategy,
        productId,
        recalculationRestFrequencyType,
        rescheduleStrategyType);
  }

  @Override
  public String toString() {

    StringBuilder sb = new StringBuilder();
    sb.append("class GetLoanProductsInterestRecalculationData {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    interestRecalculationCompoundingFrequencyType: ")
        .append(toIndentedString(interestRecalculationCompoundingFrequencyType))
        .append("\n");
    sb.append("    interestRecalculationCompoundingType: ")
        .append(toIndentedString(interestRecalculationCompoundingType))
        .append("\n");
    sb.append("    isArrearsBasedOnOriginalSchedule: ")
        .append(toIndentedString(isArrearsBasedOnOriginalSchedule))
        .append("\n");
    sb.append("    preClosureInterestCalculationStrategy: ")
        .append(toIndentedString(preClosureInterestCalculationStrategy))
        .append("\n");
    sb.append("    productId: ").append(toIndentedString(productId)).append("\n");
    sb.append("    recalculationRestFrequencyType: ")
        .append(toIndentedString(recalculationRestFrequencyType))
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
