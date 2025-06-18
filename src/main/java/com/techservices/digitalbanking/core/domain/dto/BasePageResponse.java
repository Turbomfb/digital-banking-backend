package com.techservices.digitalbanking.core.domain.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Setter
@Getter
public class BasePageResponse<T> {
    private int pageNumber;
    private int pageSize;
    private int totalFilteredItems;
    private Long rowSize;
    private List<T> data;

    public static <T> BasePageResponse<T> instance(Page<T> pagedData) {
        BasePageResponse<T> response = new BasePageResponse<>();
        response.setPageNumber(pagedData.getNumber());
        response.setPageSize(pagedData.getSize());
        response.setRowSize(pagedData.getTotalElements());
        response.setTotalFilteredItems(pagedData.getContent().size());
        response.setData(pagedData.getContent());
        return response;
    }
}
