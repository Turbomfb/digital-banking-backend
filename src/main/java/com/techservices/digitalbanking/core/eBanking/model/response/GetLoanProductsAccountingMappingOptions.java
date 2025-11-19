/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/** GetLoanProductsAccountingMappingOptions */
@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]",
    comments = "Generator version: 7.5.0")
public class GetLoanProductsAccountingMappingOptions {

  @Valid
  private Set<@Valid GetLoanProductsAssetAccountOptions> assetAccountOptions =
      new LinkedHashSet<>();

  @Valid
  private Set<@Valid GetLoanProductsExpenseAccountOptions> expenseAccountOptions =
      new LinkedHashSet<>();

  @Valid
  private Set<@Valid GetLoanProductsIncomeAccountOptions> incomeAccountOptions =
      new LinkedHashSet<>();

  @Valid
  private Set<@Valid GetLoanProductsLiabilityAccountOptions> liabilityAccountOptions =
      new LinkedHashSet<>();

  public GetLoanProductsAccountingMappingOptions assetAccountOptions(
      Set<@Valid GetLoanProductsAssetAccountOptions> assetAccountOptions) {

    this.assetAccountOptions = assetAccountOptions;
    return this;
  }

  public GetLoanProductsAccountingMappingOptions addAssetAccountOptionsItem(
      GetLoanProductsAssetAccountOptions assetAccountOptionsItem) {

    if (this.assetAccountOptions == null) {
      this.assetAccountOptions = new LinkedHashSet<>();
    }
    this.assetAccountOptions.add(assetAccountOptionsItem);
    return this;
  }

  /**
   * Get assetAccountOptions
   *
   * @return assetAccountOptions
   */
  @Valid
  @Schema(name = "assetAccountOptions", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("assetAccountOptions")
  public Set<@Valid GetLoanProductsAssetAccountOptions> getAssetAccountOptions() {

    return assetAccountOptions;
  }

  @JsonDeserialize(as = LinkedHashSet.class)
  public void setAssetAccountOptions(
      Set<@Valid GetLoanProductsAssetAccountOptions> assetAccountOptions) {

    this.assetAccountOptions = assetAccountOptions;
  }

  public GetLoanProductsAccountingMappingOptions expenseAccountOptions(
      Set<@Valid GetLoanProductsExpenseAccountOptions> expenseAccountOptions) {

    this.expenseAccountOptions = expenseAccountOptions;
    return this;
  }

  public GetLoanProductsAccountingMappingOptions addExpenseAccountOptionsItem(
      GetLoanProductsExpenseAccountOptions expenseAccountOptionsItem) {

    if (this.expenseAccountOptions == null) {
      this.expenseAccountOptions = new LinkedHashSet<>();
    }
    this.expenseAccountOptions.add(expenseAccountOptionsItem);
    return this;
  }

  /**
   * Get expenseAccountOptions
   *
   * @return expenseAccountOptions
   */
  @Valid
  @Schema(name = "expenseAccountOptions", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("expenseAccountOptions")
  public Set<@Valid GetLoanProductsExpenseAccountOptions> getExpenseAccountOptions() {

    return expenseAccountOptions;
  }

  @JsonDeserialize(as = LinkedHashSet.class)
  public void setExpenseAccountOptions(
      Set<@Valid GetLoanProductsExpenseAccountOptions> expenseAccountOptions) {

    this.expenseAccountOptions = expenseAccountOptions;
  }

  public GetLoanProductsAccountingMappingOptions incomeAccountOptions(
      Set<@Valid GetLoanProductsIncomeAccountOptions> incomeAccountOptions) {

    this.incomeAccountOptions = incomeAccountOptions;
    return this;
  }

  public GetLoanProductsAccountingMappingOptions addIncomeAccountOptionsItem(
      GetLoanProductsIncomeAccountOptions incomeAccountOptionsItem) {

    if (this.incomeAccountOptions == null) {
      this.incomeAccountOptions = new LinkedHashSet<>();
    }
    this.incomeAccountOptions.add(incomeAccountOptionsItem);
    return this;
  }

  /**
   * Get incomeAccountOptions
   *
   * @return incomeAccountOptions
   */
  @Valid
  @Schema(name = "incomeAccountOptions", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("incomeAccountOptions")
  public Set<@Valid GetLoanProductsIncomeAccountOptions> getIncomeAccountOptions() {

    return incomeAccountOptions;
  }

  @JsonDeserialize(as = LinkedHashSet.class)
  public void setIncomeAccountOptions(
      Set<@Valid GetLoanProductsIncomeAccountOptions> incomeAccountOptions) {

    this.incomeAccountOptions = incomeAccountOptions;
  }

  public GetLoanProductsAccountingMappingOptions liabilityAccountOptions(
      Set<@Valid GetLoanProductsLiabilityAccountOptions> liabilityAccountOptions) {

    this.liabilityAccountOptions = liabilityAccountOptions;
    return this;
  }

  public GetLoanProductsAccountingMappingOptions addLiabilityAccountOptionsItem(
      GetLoanProductsLiabilityAccountOptions liabilityAccountOptionsItem) {

    if (this.liabilityAccountOptions == null) {
      this.liabilityAccountOptions = new LinkedHashSet<>();
    }
    this.liabilityAccountOptions.add(liabilityAccountOptionsItem);
    return this;
  }

  /**
   * Get liabilityAccountOptions
   *
   * @return liabilityAccountOptions
   */
  @Valid
  @Schema(name = "liabilityAccountOptions", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("liabilityAccountOptions")
  public Set<@Valid GetLoanProductsLiabilityAccountOptions> getLiabilityAccountOptions() {

    return liabilityAccountOptions;
  }

  @JsonDeserialize(as = LinkedHashSet.class)
  public void setLiabilityAccountOptions(
      Set<@Valid GetLoanProductsLiabilityAccountOptions> liabilityAccountOptions) {

    this.liabilityAccountOptions = liabilityAccountOptions;
  }

  @Override
  public boolean equals(Object o) {

    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetLoanProductsAccountingMappingOptions getLoanProductsAccountingMappingOptions =
        (GetLoanProductsAccountingMappingOptions) o;
    return Objects.equals(
            this.assetAccountOptions, getLoanProductsAccountingMappingOptions.assetAccountOptions)
        && Objects.equals(
            this.expenseAccountOptions,
            getLoanProductsAccountingMappingOptions.expenseAccountOptions)
        && Objects.equals(
            this.incomeAccountOptions, getLoanProductsAccountingMappingOptions.incomeAccountOptions)
        && Objects.equals(
            this.liabilityAccountOptions,
            getLoanProductsAccountingMappingOptions.liabilityAccountOptions);
  }

  @Override
  public int hashCode() {

    return Objects.hash(
        assetAccountOptions, expenseAccountOptions, incomeAccountOptions, liabilityAccountOptions);
  }

  @Override
  public String toString() {

    StringBuilder sb = new StringBuilder();
    sb.append("class GetLoanProductsAccountingMappingOptions {\n");
    sb.append("    assetAccountOptions: ")
        .append(toIndentedString(assetAccountOptions))
        .append("\n");
    sb.append("    expenseAccountOptions: ")
        .append(toIndentedString(expenseAccountOptions))
        .append("\n");
    sb.append("    incomeAccountOptions: ")
        .append(toIndentedString(incomeAccountOptions))
        .append("\n");
    sb.append("    liabilityAccountOptions: ")
        .append(toIndentedString(liabilityAccountOptions))
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
