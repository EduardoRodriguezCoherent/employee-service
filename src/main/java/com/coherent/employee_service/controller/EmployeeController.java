package com.coherent.employee_service.controller;

import com.coherent.employee_service.dto.EmployeeDto;
import com.coherent.employee_service.dto.RegisterEmployeeDto;
import com.coherent.employee_service.model.EmployeeRole;
import com.coherent.employee_service.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getById(id));
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody RegisterEmployeeDto employeeDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.save(employeeDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long id, @RequestBody RegisterEmployeeDto updateEmployeeDto) {
        return ResponseEntity.ok(employeeService.update(id, updateEmployeeDto));
    }

    @PostMapping("/{id}/roles")
    public ResponseEntity<Set<EmployeeRole>> addRole(@PathVariable Long id, @RequestBody EmployeeRole role) {
        return ResponseEntity.ok(employeeService.addRole(id, role));
    }

    @DeleteMapping("/{id}/roles")
    public ResponseEntity<Set<EmployeeRole>> removeRole(@PathVariable Long id, @RequestBody EmployeeRole role) {
        return ResponseEntity.ok(employeeService.deleteRole(id, role));
    }

    @PostMapping("/{employeeId}/assign-expertise")
    public ResponseEntity<EmployeeDto> assignExpertiseArea(
            @PathVariable Long employeeId,
            @RequestParam Long clubId,
            @RequestParam Long expertiseAreaId) {

        EmployeeDto updatedEmployee = employeeService.assignExpertiseArea(clubId, employeeId, expertiseAreaId);
        return ResponseEntity.ok(updatedEmployee);
    }
}
