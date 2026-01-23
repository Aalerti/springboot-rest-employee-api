package org.example.springbootrest.mapper;

import org.example.springbootrest.dto.EmployeeDTO;
import org.example.springbootrest.entity.Employee;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    Employee toEmployeeEntity(EmployeeDTO employeeDTO);

    EmployeeDTO toEmployeeDTO(Employee employee);

    List<Employee> toEmployeeList(List<EmployeeDTO> employeesDTO);

    List<EmployeeDTO> toEmployeeDTOList(List<Employee> employees);
}
