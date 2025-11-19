/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.techservices.digitalbanking.core.domain.dto.response.IdentityVerificationResponse;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostClientsAddressRequest {

  private String addressLine1;
  private String addressLine2;
  private String addressLine3;
  private Long addressTypeId;
  private String city;
  private Integer countryId;
  private String country;
  private Boolean isActive;
  private Long postalCode;
  private Integer stateProvinceId;
  private String state;
  private String lga;

  public static PostClientsAddressRequest parse(
      IdentityVerificationResponse.IdentityVerificationResponseData.Address address) {

    PostClientsAddressRequest postClientsAddressRequest = new PostClientsAddressRequest();
    postClientsAddressRequest.setAddressLine1(address.getAddressLine());
    postClientsAddressRequest.setCity(address.getTown());
    postClientsAddressRequest.setLga(address.getLga());
    postClientsAddressRequest.setState(address.getState());
    return postClientsAddressRequest;
  }
}
