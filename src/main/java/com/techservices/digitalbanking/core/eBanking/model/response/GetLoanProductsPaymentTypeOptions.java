/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import java.util.Objects;

/** GetLoanProductsPaymentTypeOptions */
@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]",
    comments = "Generator version: 7.5.0")
public class GetLoanProductsPaymentTypeOptions {

  private Integer id;

  private String name;

  private Integer position;

  public GetLoanProductsPaymentTypeOptions id(Integer id) {

    this.id = id;
    return this;
  }

  /**
   * Get id
   *
   * @return id
   */
  @Schema(name = "id", example = "10", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public Integer getId() {

    return id;
  }

  public void setId(Integer id) {

    this.id = id;
  }

  public GetLoanProductsPaymentTypeOptions name(String name) {

    this.name = name;
    return this;
  }

  /**
   * Get name
   *
   * @return name
   */
  @Schema(name = "name", example = "check", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("name")
  public String getName() {

    return name;
  }

  public void setName(String name) {

    this.name = name;
  }

  public GetLoanProductsPaymentTypeOptions position(Integer position) {

    this.position = position;
    return this;
  }

  /**
   * Get position
   *
   * @return position
   */
  @Schema(name = "position", example = "1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("position")
  public Integer getPosition() {

    return position;
  }

  public void setPosition(Integer position) {

    this.position = position;
  }

  @Override
  public boolean equals(Object o) {

    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetLoanProductsPaymentTypeOptions getLoanProductsPaymentTypeOptions =
        (GetLoanProductsPaymentTypeOptions) o;
    return Objects.equals(this.id, getLoanProductsPaymentTypeOptions.id)
        && Objects.equals(this.name, getLoanProductsPaymentTypeOptions.name)
        && Objects.equals(this.position, getLoanProductsPaymentTypeOptions.position);
  }

  @Override
  public int hashCode() {

    return Objects.hash(id, name, position);
  }

  @Override
  public String toString() {

    StringBuilder sb = new StringBuilder();
    sb.append("class GetLoanProductsPaymentTypeOptions {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    position: ").append(toIndentedString(position)).append("\n");
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
