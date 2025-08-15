package com.techservices.digitalbanking.core.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class ImageComparisonResponse {
    private Object errors;
    private boolean success;
    private String statusMessage;
    private String statusCode;
    private String message;
    private String dataSource;
    private ImageComparisonData data;

    public boolean isSuccess() {
        return success || StringUtils.equalsIgnoreCase(this.message, "success");
    }


    @Setter
    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ToString
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class ImageComparisonData {
        private String id;
        private String parentId;
        private String status;
        private String reason;
        private Boolean selfieValidation;
        private ImageComparison imageComparison;


        @Setter
        @Getter
        @ToString
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonIgnoreProperties(ignoreUnknown = true)
        public class ImageComparison {
            private Long confidenceLevel;
            private Long threshold;
            private Boolean match;
        }

    }
}
