package org.example.springbootrest.service;

import org.example.springbootrest.dto.EmployeeDTO;
import org.example.springbootrest.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDTO> findAllEmployees();

    EmployeeDTO findEmployeeById(long id);

    EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);

    void deleteEmployeeById(long id);
}
