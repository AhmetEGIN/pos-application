package com.egin.user.model.mapper;

import com.egin.branch.model.entity.BranchEntity;
import com.egin.store.model.entity.StoreEntity;
import com.egin.user.model.dto.request.EmployeeCreateRequest;
import com.egin.user.model.entity.EmployeeEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EmployeeCreateRequestToEmployeeEntityMapper {

    public EmployeeEntity toEmployeeEntity(
            EmployeeCreateRequest request,
            StoreEntity storeEntity,
            BranchEntity branchEntity
    ) {
        return EmployeeEntity.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .userType(request.getUserType())
                .department(request.getDepartment())
                .position(request.getPosition())
                .phoneNumber(request.getPhoneNumber())
                .hireDate(request.getHireDate())
                .salary(request.getSalary())
                .store(storeEntity)
                .branch(branchEntity)
                .build();
    }
}

