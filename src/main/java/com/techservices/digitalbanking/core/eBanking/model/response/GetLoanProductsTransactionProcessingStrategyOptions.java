/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import java.util.Objects;

/** GetLoanProductsTransactionProcessingStrategyOptions */
@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]",
    comments = "Generator version: 7.5.0")
public class GetLoanProductsTransactionProcessingStrategyOptions {

  private String code;

  private Integer id;

  private String name;

  public GetLoanProductsTransactionProcessingStrategyOptions code(String code) {

    this.code = code;
    return this;
  }

  /**
   * Get code
   *
   * @return code
   */
  @Schema(
      name = "code",
      example = "mifos-standard-strategy",
      requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("code")
  public String getCode() {

    return code;
  }

  public void setCode(String code) {

    this.code = code;
  }

  public GetLoanProductsTransactionProcessingStrategyOptions id(Integer id) {

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

  public GetLoanProductsTransactionProcessingStrategyOptions name(String name) {

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
      example = "Penalties, Fees, Interest, Principal order",
      requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("name")
  public String getName() {

    return name;
  }

  public void setName(String name) {

    this.name = name;
  }

  @Override
  public boolean equals(Object o) {

    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetLoanProductsTransactionProcessingStrategyOptions
        getLoanProductsTransactionProcessingStrategyOptions =
            (GetLoanProductsTransactionProcessingStrategyOptions) o;
    return Objects.equals(this.code, getLoanProductsTransactionProcessingStrategyOptions.code)
        && Objects.equals(this.id, getLoanProductsTransactionProcessingStrategyOptions.id)
        && Objects.equals(this.name, getLoanProductsTransactionProcessingStrategyOptions.name);
  }

  @Override
  public int hashCode() {

    return Objects.hash(code, id, name);
  }

  @Override
  public String toString() {

    StringBuilder sb = new StringBuilder();
    sb.append("class GetLoanProductsTransactionProcessingStrategyOptions {\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
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
