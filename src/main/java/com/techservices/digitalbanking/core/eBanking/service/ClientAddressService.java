/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.service;

import com.techservices.digitalbanking.core.eBanking.api.CustomerApiClient;
import com.techservices.digitalbanking.core.eBanking.model.request.PutClientClientIdAddressesRequest;
import com.techservices.digitalbanking.core.eBanking.model.response.GetClientClientIdAddressesResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.PutClientClientIdAddressesResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClientAddressService {

  private final CustomerApiClient customerApiClient;

  public List<GetClientClientIdAddressesResponse> getCustomerAddresses(Long customerId) {

    return customerApiClient.getClientAddresses(customerId, null, null);
  }

  public PutClientClientIdAddressesResponse updateCustomerAddress(
      Long customerId, PutClientClientIdAddressesRequest putClientClientIdAddressesRequest) {

    return customerApiClient.updateClientAddress(
        customerId, null, putClientClientIdAddressesRequest);
  }
}
