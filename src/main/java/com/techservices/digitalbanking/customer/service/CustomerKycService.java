/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.customer.service;

import com.techservices.digitalbanking.customer.domian.dto.request.CustomerKycRequest;
import com.techservices.digitalbanking.customer.domian.dto.response.CustomerDtoResponse;

public interface CustomerKycService {

	CustomerDtoResponse updateCustomerKyc(CustomerKycRequest customerKycRequest, Long customerId);
}
