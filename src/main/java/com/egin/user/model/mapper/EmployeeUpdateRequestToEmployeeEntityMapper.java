package com.egin.user.model.mapper;

import com.egin.user.model.dto.request.EmployeeUpdateRequest;
import com.egin.user.model.entity.EmployeeEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EmployeeUpdateRequestToEmployeeEntityMapper {

    public void mapForUpdate(EmployeeEntity employeeEntity, EmployeeUpdateRequest request) {

        if (request.getFirstName() != null) {
            employeeEntity.setFirstName(request.getFirstName());
        }

        if (request.getLastName() != null) {
            employeeEntity.setLastName(request.getLastName());
        }

        if (request.getDepartment() != null) {
            employeeEntity.setDepartment(request.getDepartment());
        }

        if (request.getPosition() != null) {
            employeeEntity.setPosition(request.getPosition());
        }

        if (request.getPhoneNumber() != null) {
            employeeEntity.setPhoneNumber(request.getPhoneNumber());
        }

        if (request.getHireDate() != null) {
            employeeEntity.setHireDate(request.getHireDate());
        }

        if (request.getSalary() != null) {
            employeeEntity.setSalary(request.getSalary());
        }
    }
}

