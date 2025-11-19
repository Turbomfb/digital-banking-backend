/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import java.util.Objects;

/** GetLoansLoanIdCollateralsResponse */
@Schema(
    name = "GetLoansLoanIdCollateralsResponse",
    description = "GetLoansLoanIdCollateralsResponse")
@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]",
    comments = "Generator version: 7.5.0")
public class GetLoansLoanIdCollateralsResponse {

  private Object currency;

  private String description;

  private Integer id;

  private Object type;

  private Long value;

  public GetLoansLoanIdCollateralsResponse currency(Object currency) {

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
  public Object getCurrency() {

    return currency;
  }

  public void setCurrency(Object currency) {

    this.currency = currency;
  }

  public GetLoansLoanIdCollateralsResponse description(String description) {

    this.description = description;
    return this;
  }

  /**
   * Get description
   *
   * @return description
   */
  @Schema(
      name = "description",
      example = "24 Carat Gold chain weighing 12 grams",
      requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("description")
  public String getDescription() {

    return description;
  }

  public void setDescription(String description) {

    this.description = description;
  }

  public GetLoansLoanIdCollateralsResponse id(Integer id) {

    this.id = id;
    return this;
  }

  /**
   * Get id
   *
   * @return id
   */
  @Schema(name = "id", example = "12", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public Integer getId() {

    return id;
  }

  public void setId(Integer id) {

    this.id = id;
  }

  public GetLoansLoanIdCollateralsResponse type(Object type) {

    this.type = type;
    return this;
  }

  /**
   * Get type
   *
   * @return type
   */
  @Valid
  @Schema(name = "type", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("type")
  public Object getType() {

    return type;
  }

  public void setType(Object type) {

    this.type = type;
  }

  public GetLoansLoanIdCollateralsResponse value(Long value) {

    this.value = value;
    return this;
  }

  /**
   * Get value
   *
   * @return value
   */
  @Schema(name = "value", example = "50000", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("value")
  public Long getValue() {

    return value;
  }

  public void setValue(Long value) {

    this.value = value;
  }

  @Override
  public boolean equals(Object o) {

    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetLoansLoanIdCollateralsResponse getLoansLoanIdCollateralsResponse =
        (GetLoansLoanIdCollateralsResponse) o;
    return Objects.equals(this.currency, getLoansLoanIdCollateralsResponse.currency)
        && Objects.equals(this.description, getLoansLoanIdCollateralsResponse.description)
        && Objects.equals(this.id, getLoansLoanIdCollateralsResponse.id)
        && Objects.equals(this.type, getLoansLoanIdCollateralsResponse.type)
        && Objects.equals(this.value, getLoansLoanIdCollateralsResponse.value);
  }

  @Override
  public int hashCode() {

    return Objects.hash(currency, description, id, type, value);
  }

  @Override
  public String toString() {

    StringBuilder sb = new StringBuilder();
    sb.append("class GetLoansLoanIdCollateralsResponse {\n");
    sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
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
