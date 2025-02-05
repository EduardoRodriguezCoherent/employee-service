package com.coherent.employee_service.service;

import com.coherent.employee_service.dto.EmployeeDto;
import com.coherent.employee_service.dto.RegisterEmployeeDto;
import com.coherent.employee_service.model.EmployeeRole;

import java.util.List;
import java.util.Set;

public interface EmployeeService {

    List<EmployeeDto> getAll();

    EmployeeDto getById(Long id);

    EmployeeDto save(RegisterEmployeeDto registerEmployee);

    EmployeeDto update(EmployeeDto updateEmployee);

    List<EmployeeRole> addOrChangeRole(EmployeeDto employeeDto);

    EmployeeDto assignExpertiseArea(Long employeeId, Set<Long> expertiseAreas);
}
