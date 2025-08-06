package com.techservices.digitalbanking.authentication.domain.data.repository;

import com.techservices.digitalbanking.authentication.domain.data.model.UserLoginActivity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserLoginActivityRepository extends JpaRepository<UserLoginActivity, Long> {


    Optional<UserLoginActivity> findByDeviceName(String deviceName);

    Optional<UserLoginActivity> findByDeviceNameAndSource(String deviceName, String source);

    List<UserLoginActivity> findAllByCustomerId(Long customerId);
}
