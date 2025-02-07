package com.coherent.employee_service.service.impl;

import com.coherent.employee_service.dto.EmployeeDto;
import com.coherent.employee_service.dto.RegisterEmployeeDto;
import com.coherent.employee_service.mapper.EmployeeMapper;
import com.coherent.employee_service.model.Employee;
import com.coherent.employee_service.model.EmployeeRole;
import com.coherent.employee_service.repository.EmployeeRepository;
import com.coherent.employee_service.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public List<EmployeeDto> getAll() {
        return employeeRepository.findAll().stream()
                .map(employeeMapper::toEmployeeDto)
                .toList();
    }

    @Override
    public EmployeeDto getById(Long id) {

        return employeeRepository.findById(id)
                .map(employeeMapper::toEmployeeDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found with id: " + id));
    }

    @Override
    public EmployeeDto save(RegisterEmployeeDto registerEmployee) {
        Employee employee = employeeMapper.toEmployee(registerEmployee);
        if(employee.getName() == null || employee.getName().isBlank()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Employee name cannot be empty");
        }
        if (employee.getId() != null && employeeRepository.existsById(employee.getId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Employee with id already exists");
        }
        return employeeMapper.toEmployeeDto(employeeRepository.save(employee));

    }

    @Override
    public EmployeeDto update(Long id, RegisterEmployeeDto updateEmployee) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found with : " + id));

        if (updateEmployee.name() != null && !updateEmployee.name().isEmpty()) {
            existingEmployee.setName(updateEmployee.name());
        }
        if (updateEmployee.lastName() != null && !updateEmployee.lastName().isEmpty()) {
            existingEmployee.setLastName(updateEmployee.lastName());
        }
        if (updateEmployee.birthDate() != null) {
            existingEmployee.setBirthDate(updateEmployee.birthDate());
        }
        if (existingEmployee.getBankAccount() != null && !existingEmployee.getBankAccount().isEmpty()) {
            existingEmployee.setBankAccount(updateEmployee.bankAccount());
        }
        if (existingEmployee.getSalary() > 0) {
            existingEmployee.setSalary(updateEmployee.salary());
        }
        if (updateEmployee.roles() != null && !updateEmployee.roles().isEmpty()) {
            existingEmployee.setRoles(updateEmployee.roles());
        }
        if (updateEmployee.expertiseAreas() != null && !updateEmployee.expertiseAreas().isEmpty()) {
            existingEmployee.setExpertiseAreas(updateEmployee.expertiseAreas());
        }

        return employeeMapper.toEmployeeDto(existingEmployee);
    }

    @Override
    public Set<EmployeeRole> addRole(Long id, EmployeeRole role) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found with : " + id));

        Set<EmployeeRole> roles = employee.getRoles();

        if(roles.contains(role)){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Employee with id: " + id + " already has [" + role + "] role");
        }

        roles.add(role);

        return roles;
    }

    @Override
    public Set<EmployeeRole> deleteRole(Long id, EmployeeRole role) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found with : " + id));

        Set<EmployeeRole> roles = employee.getRoles();

        if(!roles.contains(role)){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Employee with id: " + id + " has not [" + role + "] role");
        }

        roles.remove(role);

        return roles;
    }

    @Override
    public EmployeeDto assignExpertiseArea(Long employeeId, Set<Long> expertiseAreas) {

        return null;
    }
}
