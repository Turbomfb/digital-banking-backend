package com.techservices.digitalbanking.customer.domian.data.repository;

import com.techservices.digitalbanking.common.domain.enums.UserType;
import com.techservices.digitalbanking.customer.domian.data.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    @Query("SELECT c FROM Customer c WHERE LOWER(c.emailAddress) = LOWER(:emailAddress)")
    Optional<Customer> findByEmailAddress(String emailAddress);

    @Query("SELECT c FROM Customer c WHERE LOWER(c.phoneNumber) = LOWER(:phoneNumber)")
    Optional<Customer> findByPhoneNumber(String phoneNumber);

    Page<Customer> findAll(Pageable pageable);

    Optional<Customer> findByBvnAndUserType(String bvn, UserType userType);

    Optional<Customer> findByNinAndUserType(String nin, UserType userType);

    Optional<Customer> findByRcNumberAndUserType(String rcNumber, UserType userType);

    Optional<Customer> findByTinAndUserType(String tin, UserType userType);

    @Query("SELECT c FROM Customer c WHERE LOWER(c.emailAddress) = LOWER(:emailAddress) and c.userType = :userType")
    Optional<Customer> findByEmailAddressAndUserType(String emailAddress, UserType userType);

    @Query("SELECT c FROM Customer c WHERE LOWER(c.phoneNumber) = LOWER(:phoneNumber) and c.userType = :userType")
    Optional<Customer> findByPhoneNumberAndUserType(String phoneNumber, UserType userType);

    Optional<Customer> findByBusinessName(String businessName);

    Optional<Customer> findByRcNumber(String rcNumber);
}
