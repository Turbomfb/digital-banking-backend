/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.savingsaccount.domain.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Setter
@Getter
@Builder
public class StatementRequest {
    private Long savingsId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long productId;
    private Long offset;
    private Long limit;
    private Boolean includeReversals;
    private String transactionType;

    public static StatementRequest buildStatementRequest(Long savingsId, LocalDate startDate, LocalDate endDate,
                                                   Long productId, Long offset, Long limit, Boolean includeReversals, String transactionType) {

        // Set default date range if not provided (last 90 days)
        if (startDate == null) {
            startDate = LocalDate.now().minusDays(90);
        }
        if (endDate == null) {
            endDate = LocalDate.now();
        }

        // Validate date range
        if (startDate.isAfter(endDate)) {
            throw new InvalidParameterException("Start date cannot be after end date");
        }

        if (ChronoUnit.DAYS.between(startDate, endDate) > 365) {
            throw new InvalidParameterException("Date range cannot exceed 365 days");
        }

        return StatementRequest.builder()
                .savingsId(savingsId)
                .startDate(startDate)
                .endDate(endDate)
                .productId(productId)
                .offset(offset)
                .limit(limit)
                .includeReversals(includeReversals != null ? includeReversals : false)
                .transactionType(transactionType)
                .build();
    }

}
