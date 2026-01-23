package org.example.springbootrest.service;

import org.example.springbootrest.dao.EmployeeDAO;
import org.example.springbootrest.dto.EmployeeDTO;
import org.example.springbootrest.entity.Employee;
import org.example.springbootrest.exception_handling.NoSuchEmployeeException;
import org.example.springbootrest.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements  EmployeeService {

    private final EmployeeDAO employeeDAO;
    private final EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeDAO employeeDao, EmployeeMapper employeeMapper) {
        this.employeeDAO = employeeDao;
        this.employeeMapper = employeeMapper;
    }

    @Override
    @Transactional
    public List<EmployeeDTO> findAllEmployees() {
        List<Employee> employees = employeeDAO.findAllEmployees();
        if (employees == null ||  employees.isEmpty()) {
            throw new NoSuchEmployeeException("No employees found");
        }
        return employeeMapper.toEmployeeDTOList(employees);
    }

    @Override
    @Transactional
    public EmployeeDTO findEmployeeById(long id) {
        Employee employee = employeeDAO.findEmployeeById(id);
        if (employee == null) throw new NoSuchEmployeeException("No employee found");
        return employeeMapper.toEmployeeDTO(employee);
    }

    @Override
    @Transactional
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = employeeMapper.toEmployeeEntity(employeeDTO);
        Employee savedEmployee = employeeDAO.saveEmployee(employee);
        return employeeMapper.toEmployeeDTO(savedEmployee);
    }

    @Override
    @Transactional
    public void deleteEmployeeById(long id) {
        Employee employee = employeeDAO.findEmployeeById(id);
        if (employee == null) throw new NoSuchEmployeeException("No employee found");
        employeeDAO.deleteEmployeeById(id);
    }
}
