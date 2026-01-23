package org.example.springbootrest.dao;

import org.example.springbootrest.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> findAllEmployees();

    Employee findEmployeeById(long id);

    Employee saveEmployee(Employee employee);

    void deleteEmployeeById(long id);
}
