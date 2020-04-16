package com.nure.komarkova.backend.service;

import com.nure.komarkova.backend.entity.Employee;
import com.nure.komarkova.backend.entity.Workflow;

import java.util.List;

public interface EmployeeService {

    void createEmployee(Employee employee);

    List<Employee> findAllEmployees();

    Employee findEmployeeById(Long id);

    List<Workflow> findWorkflowsByEmployeeId(Long id);
}
