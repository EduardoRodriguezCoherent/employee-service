package com.coherent.employee_service.mapper;

import com.coherent.employee_service.dto.EmployeeDto;
import com.coherent.employee_service.dto.RegisterEmployeeDto;
import com.coherent.employee_service.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeDto toEmployee(Employee employee);

    @Mapping(target = "bankAccount", ignore = true)
    @Mapping(target = "salary", ignore = true)
    Employee toEmployee(EmployeeDto employeeDto);

    Employee toEntity(RegisterEmployeeDto registerEmployeeDto);
}
