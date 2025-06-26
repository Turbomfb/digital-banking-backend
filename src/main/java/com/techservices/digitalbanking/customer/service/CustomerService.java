/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.customer.service;

import com.techservices.digitalbanking.core.domain.BaseAppResponse;
import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.core.fineract.model.response.GetClientsClientIdAccountsResponse;
import com.techservices.digitalbanking.customer.domian.data.model.Customer;
import com.techservices.digitalbanking.customer.domian.dto.request.CreateCustomerRequest;
import com.techservices.digitalbanking.customer.domian.dto.request.CustomerUpdateRequest;
import com.techservices.digitalbanking.customer.domian.dto.response.CustomerDashboardResponse;
import com.techservices.digitalbanking.customer.domian.dto.response.CustomerDtoResponse;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CustomerService {

	BaseAppResponse createCustomer(CreateCustomerRequest createCustomerRequest, String command);

	Customer updateCustomer(CustomerUpdateRequest customerUpdateRequest, Long customerId, Customer existingCustomer);

	Customer getCustomerById(Long customerId);
	Optional<Customer> getCustomerByEmailAddress(String emailAddress);
	Optional<Customer> getCustomerByPhoneNumber(String phoneNumber);

	BasePageResponse<CustomerDtoResponse> getAllCustomers(Pageable pageable);

	GetClientsClientIdAccountsResponse getClientAccountsByClientId(Long customerId, String accountTypes);

	CustomerDtoResponse getCustomerDtoResponse(Customer customerById);

	CustomerDashboardResponse retrieveCustomerDashboard(Long customerId);
}
