package com.techservices.digitalbanking.core.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BasePageResponse<T> {
    private Long pageNumber;
    private Long pageSize;
    private Long totalFilteredItems;
    private Long rowSize;
    private List<T> data;

    public static <T> BasePageResponse<T> instance(Page<T> pagedData) {
        BasePageResponse<T> response = new BasePageResponse<>();
        response.setPageNumber((long) pagedData.getNumber());
        response.setPageSize((long) pagedData.getSize());
        response.setRowSize(pagedData.getTotalElements());
        response.setTotalFilteredItems((long) pagedData.getContent().size());
        response.setData(pagedData.getContent());
        return response;
    }

    public static <T> BasePageResponse<T> instance(List<T> pagedData) {
        BasePageResponse<T> response = new BasePageResponse<>();
        response.setTotalFilteredItems((long) pagedData.size());
        response.setData(pagedData);
        response.setPageNumber(1L);
        response.setPageSize((long) pagedData.size());
        response.setRowSize((long) pagedData.size());
        return response;
    }
}
