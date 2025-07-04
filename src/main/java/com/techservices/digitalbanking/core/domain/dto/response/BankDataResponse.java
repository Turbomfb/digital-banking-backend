package com.techservices.digitalbanking.core.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BankDataResponse {
    private boolean success;
    private int statusCode;
    private String message;
    private List<BankData> data;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class BankData {
        private int id;
        private String name;
        private String slug;
        private String code;
        private String longCode;
        private String gateway;
        private boolean active;
        private String country;
        private String currency;
        private String type;
    }
}
