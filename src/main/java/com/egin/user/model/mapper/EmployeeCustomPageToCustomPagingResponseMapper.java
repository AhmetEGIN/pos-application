package com.egin.user.model.mapper;

import com.egin.common.model.CustomPage;
import com.egin.common.model.dto.response.CustomPagingResponse;
import com.egin.user.model.Employee;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EmployeeCustomPageToCustomPagingResponseMapper {

    public CustomPagingResponse<Employee> toCustomPagingResponse(
            CustomPage<Employee> employeePage
    ) {
        if (employeePage == null) {
            return null;
        }

        return CustomPagingResponse.<Employee>builder()
                .content(employeePage.getContent())
                .totalElementCount(employeePage.getTotalElementCount())
                .totalPageCount(employeePage.getTotalPageCount())
                .pageNumber(employeePage.getPageNumber())
                .pageSize(employeePage.getPageSize())
                .build();
    }
}

