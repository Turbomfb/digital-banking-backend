/* (C)2024 */
package com.techservices.digitalbanking.customer.service;

import com.techservices.digitalbanking.core.domain.BaseAppResponse;
import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.core.domain.dto.KycTierDto;
import com.techservices.digitalbanking.customer.domian.dto.request.CustomerKycRequest;

public interface CustomerKycService {

  BaseAppResponse updateCustomerKyc(
      CustomerKycRequest customerKycRequest, Long customerId, String command);

  BasePageResponse<KycTierDto> retrieveAllKycTier();
}
