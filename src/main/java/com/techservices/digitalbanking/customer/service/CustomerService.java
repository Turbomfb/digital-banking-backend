/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.customer.service;

import com.techservices.digitalbanking.common.domain.enums.UserType;
import com.techservices.digitalbanking.core.domain.BaseAppResponse;
import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.core.domain.dto.GenericApiResponse;
import com.techservices.digitalbanking.core.fineract.model.response.GetClientsClientIdAccountsResponse;
import com.techservices.digitalbanking.customer.domian.data.model.Customer;
import com.techservices.digitalbanking.customer.domian.dto.request.CreateCustomerRequest;
import com.techservices.digitalbanking.customer.domian.dto.request.CustomerTransactionPinRequest;
import com.techservices.digitalbanking.customer.domian.dto.request.CustomerUpdateRequest;
import com.techservices.digitalbanking.customer.domian.dto.response.CustomerDashboardResponse;
import com.techservices.digitalbanking.customer.domian.dto.response.CustomerDtoResponse;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CustomerService {

	BaseAppResponse createCustomer(CreateCustomerRequest createCustomerRequest, String command, UserType customerType);

	Customer updateCustomer(CustomerUpdateRequest customerUpdateRequest, Long customerId, Customer existingCustomer);

	Customer getCustomerById(Long customerId);
	Optional<Customer> getCustomerByEmailAddress(String emailAddress);
	Optional<Customer> getCustomerByPhoneNumber(String phoneNumber);
	Optional<Customer> getCustomerByEmailAddressAndUserType(String emailAddress, UserType customerType);
	Optional<Customer> getCustomerByPhoneNumberAndUserType(String phoneNumber, UserType customerType);

	BasePageResponse<CustomerDtoResponse> getAllCustomers(Pageable pageable);

	GetClientsClientIdAccountsResponse getClientAccountsByClientId(Long customerId, String accountTypes);

	CustomerDashboardResponse retrieveCustomerDashboard(Long customerId);

	GenericApiResponse createTransactionPin(Long customerId, CustomerTransactionPinRequest customerTransactionPinRequest);

	String getCustomerSavingsId(Long customerId);
}
