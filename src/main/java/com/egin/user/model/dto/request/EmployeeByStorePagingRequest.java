package com.egin.user.model.dto.request;

import com.egin.common.model.dto.request.CustomPagingRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class EmployeeByStorePagingRequest extends CustomPagingRequest {
}

