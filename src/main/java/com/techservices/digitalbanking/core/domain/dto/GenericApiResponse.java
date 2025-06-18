package com.techservices.digitalbanking.core.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericApiResponse {
    private String uniqueId;
    private String message;
    private String status;
    private Object data;

    public GenericApiResponse(String message, String status, Object data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public GenericApiResponse(String uniqueId, String message, String status, Object data) {
        this.uniqueId = uniqueId;
        this.message = message;
        this.status = status;
        this.data = data;
    }

}
