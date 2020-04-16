package com.nure.komarkova.backend.service.impl;

import com.nure.komarkova.backend.entity.Employee;
import com.nure.komarkova.backend.entity.Workflow;
import com.nure.komarkova.backend.repository.EmployeeRepository;
import com.nure.komarkova.backend.repository.WorkflowRepository;
import com.nure.komarkova.backend.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private WorkflowRepository workflowRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, WorkflowRepository workflowRepository) {
        this.employeeRepository = employeeRepository;
        this.workflowRepository = workflowRepository;
    }

    @Override
    public void createEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findEmployeeById(Long id) {
        return employeeRepository.findById(id).get();
    }

    @Override
    public List<Workflow> findWorkflowsByEmployeeId(Long id) {
        return workflowRepository.findByEmployee_Id(id);
    }
}
