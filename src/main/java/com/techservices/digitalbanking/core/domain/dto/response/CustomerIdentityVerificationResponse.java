package com.techservices.digitalbanking.core.domain.dto.response;

import com.techservices.digitalbanking.customer.domian.dto.response.CustomerDtoResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

@Setter
@Getter
@ToString
public class CustomerIdentityVerificationResponse {
    private boolean isValid;
    private List<String> mismatchedFields;
}
