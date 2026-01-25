package org.example.springbootrest.service;

import org.example.springbootrest.dao.EmployeeRepository;
import org.example.springbootrest.dto.EmployeeDTO;
import org.example.springbootrest.entity.Employee;
import org.example.springbootrest.exception_handling.NoSuchEmployeeException;
import org.example.springbootrest.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements  EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public List<EmployeeDTO> findAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employeeMapper.toEmployeeDTOList(employees);
    }

    @Override
    public EmployeeDTO findEmployeeById(long id) {
        Optional<Employee> optional = employeeRepository.findById(id);
        Employee employee = optional.orElseThrow(() -> new NoSuchEmployeeException("No employee with id " + id));
        return employeeMapper.toEmployeeDTO(employee);
    }

    @Override
    @Transactional
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = employeeMapper.toEmployeeEntity(employeeDTO);
        Employee savedEmployee = employeeRepository.save(employee);
        return employeeMapper.toEmployeeDTO(savedEmployee);
    }

    @Override
    @Transactional
    public void deleteEmployeeById(long id) {
        Optional<Employee> optional = employeeRepository.findById(id);
        Employee employee =  optional.orElseThrow(() -> new NoSuchEmployeeException("No employee with id " + id));
        employeeRepository.deleteById(id);
    }
}
