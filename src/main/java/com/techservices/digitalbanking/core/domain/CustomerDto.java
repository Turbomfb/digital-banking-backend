package com.techservices.digitalbanking.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
public class CustomerDto {

    private String id;

    private String firstName;

    private String lastName;

    private String emailAddress;
    private String phoneNumber;
    private String bvn;

    private String customerType;

    private String status;

    private LocalDate dateOfBirth;
    private String address;
    private String city;
    private String state;
    private String country;
    private String postalCode;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
