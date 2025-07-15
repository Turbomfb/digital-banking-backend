package com.techservices.digitalbanking.customer.domian.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.techservices.digitalbanking.common.domain.enums.UserType;
import com.techservices.digitalbanking.core.domain.BaseAppResponse;
import com.techservices.digitalbanking.core.fineract.service.ClientService;
import com.techservices.digitalbanking.customer.domian.CustomerKycTier;
import com.techservices.digitalbanking.customer.domian.data.model.Customer;
import com.techservices.digitalbanking.customer.domian.dto.CustomerTierData;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerDtoResponse extends BaseAppResponse {
    private Long id;
    private String accountId;
    private String firstname;
    private String lastname;
    private String emailAddress;
    private String externalId;
    private String phoneNumber;
    private String referralCode;
    private String nin;
    private String bvn;
    private boolean isActive;
    private boolean isTransactionPinSet;
    @JsonProperty("isPreQualifiedForLoan")
    private boolean isPreQualifiedForLoan;
    private CustomerKycTier kycTier;
    private CustomerTierData kycTierData;


    private UserType userType;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createdAt;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime updatedAt;

    public static CustomerDtoResponse parse(Customer customer, ClientService clientService) {
        CustomerDtoResponse customerDtoResponse = getCustomerDtoResponse(customer);
        if (customer.isActive() && StringUtils.isNotBlank(customer.getExternalId()))
            customerDtoResponse.setKycTierData(clientService.getCustomerById(Long.valueOf(customer.getExternalId())).getKycTier());
        return customerDtoResponse;
    }

    private static CustomerDtoResponse getCustomerDtoResponse(Customer customer) {
        CustomerDtoResponse customerDtoResponse = new CustomerDtoResponse();
        customerDtoResponse.setId(customer.getId());
        customerDtoResponse.setAccountId(customer.getAccountId());
        customerDtoResponse.setFirstname(customer.getFirstname());
        customerDtoResponse.setLastname(customer.getLastname());
        customerDtoResponse.setEmailAddress(customer.getEmailAddress());
        customerDtoResponse.setExternalId(customer.getExternalId());
        customerDtoResponse.setPhoneNumber(customer.getPhoneNumber());
        customerDtoResponse.setReferralCode(customer.getReferralCode());
        customerDtoResponse.setBvn(customer.getBvn());
        customerDtoResponse.setNin(customer.getNin());
        customerDtoResponse.setActive(customer.isActive());
        customerDtoResponse.setUserType(customer.getUserType());
        customerDtoResponse.setKycTier(customer.getKycTier());
        customerDtoResponse.setTransactionPinSet(customer.isTransactionPinSet());
        customerDtoResponse.setCreatedAt(customer.getCreatedAt());
        customerDtoResponse.setUpdatedAt(customer.getUpdatedAt());
        return customerDtoResponse;
    }

    public static CustomerDtoResponse parse(Customer customer) {
        return getCustomerDtoResponse(customer);
    }
}
