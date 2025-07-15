package com.techservices.digitalbanking.core.domain.dto.request;

import com.techservices.digitalbanking.customer.domian.data.model.Customer;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class IdentityVerificationRequest {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;

    public static IdentityVerificationRequest parse(Customer customer) {
        IdentityVerificationRequest identityVerificationRequest = new IdentityVerificationRequest();
        if (customer != null) {
            identityVerificationRequest.setFirstName(customer.getFirstname());
            identityVerificationRequest.setLastName(customer.getLastname());
            identityVerificationRequest.setEmailAddress(customer.getEmailAddress());
            identityVerificationRequest.setPhoneNumber(customer.getPhoneNumber());
        }
        return identityVerificationRequest;
    }
}
