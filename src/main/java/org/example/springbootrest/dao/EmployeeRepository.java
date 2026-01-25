package org.example.springbootrest.dao;

import org.example.springbootrest.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeRepository extends JpaRepository<Employee, Long> { }
