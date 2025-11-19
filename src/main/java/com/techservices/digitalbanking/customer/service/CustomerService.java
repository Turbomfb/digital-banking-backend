/* (C)2024 */
package com.techservices.digitalbanking.customer.service;

import com.techservices.digitalbanking.common.domain.enums.UserType;
import com.techservices.digitalbanking.core.domain.BaseAppResponse;
import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.core.domain.dto.GenericApiResponse;
import com.techservices.digitalbanking.customer.domian.data.model.Customer;
import com.techservices.digitalbanking.customer.domian.dto.request.*;
import com.techservices.digitalbanking.customer.domian.dto.response.CustomerDashboardResponse;
import com.techservices.digitalbanking.customer.domian.dto.response.CustomerDtoResponse;
import java.util.Optional;
import org.springframework.data.domain.Pageable;

public interface CustomerService {

  BaseAppResponse createCustomer(
      CreateCustomerRequest createCustomerRequest, String command, UserType customerType);

  Customer updateCustomer(
      CustomerUpdateRequest customerUpdateRequest,
      Long customerId,
      Customer existingCustomer,
      boolean isExternalServiceUpdateAllowed);

  Customer getCustomerById(Long customerId);

  Optional<Customer> getCustomerByEmailAddress(String emailAddress);

  Optional<Customer> getCustomerByPhoneNumber(String phoneNumber);

  Optional<Customer> getCustomerByEmailAddressAndUserType(
      String emailAddress, UserType customerType);

  Optional<Customer> getCustomerByPhoneNumberAndUserType(String phoneNumber, UserType customerType);

  BasePageResponse<CustomerDtoResponse> getAllCustomers(Pageable pageable);

  CustomerDashboardResponse retrieveCustomerDashboard(Long customerId);

  GenericApiResponse createTransactionPin(
      Long customerId, CustomerTransactionPinRequest customerTransactionPinRequest);

  String getCustomerSavingsId(Long customerId);

  GenericApiResponse closeAccount(Long customerId, CustomerAccountClosureRequest request);

  void validateDuplicateCustomerByRcNumber(String rcNumber);

  GenericApiResponse activateAccount(CustomerAccountActivationRequest request, UserType userType);

  Customer getCustomerByEmailOrPhoneNumber(
      String emailAddress, String phoneNumber, UserType customerType);
}
