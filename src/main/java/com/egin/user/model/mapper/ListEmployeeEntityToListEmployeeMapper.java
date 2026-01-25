package com.egin.user.model.mapper;

import com.egin.user.model.Employee;
import com.egin.user.model.entity.EmployeeEntity;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class ListEmployeeEntityToListEmployeeMapper {

    public List<Employee> toEmployeeList(List<EmployeeEntity> employeeEntities) {
        return employeeEntities.stream()
                .map(EmployeeEntityToEmployeeMapper::toEmployee)
                .collect(Collectors.toList());
    }

}

