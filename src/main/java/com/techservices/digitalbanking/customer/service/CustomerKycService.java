/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.customer.service;

import com.techservices.digitalbanking.core.domain.BaseAppResponse;
import com.techservices.digitalbanking.core.fineract.model.data.FineractPageResponse;
import com.techservices.digitalbanking.core.fineract.model.request.KycTier;
import com.techservices.digitalbanking.customer.domian.dto.request.CustomerKycRequest;
import com.techservices.digitalbanking.customer.domian.dto.response.CustomerDtoResponse;

public interface CustomerKycService {

	BaseAppResponse updateCustomerKyc(CustomerKycRequest customerKycRequest, Long customerId, String command);

	FineractPageResponse<KycTier> retrieveAllKycTier();
}
