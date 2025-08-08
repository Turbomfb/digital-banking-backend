package com.techservices.digitalbanking.customer.domian.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerAccountClosureRequest {
    private String reasonForClosure;
}
