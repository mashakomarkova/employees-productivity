package com.nure.komarkova.backend.bean;

import com.nure.komarkova.backend.entity.Employee;
import com.nure.komarkova.backend.entity.Workflow;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeWorkflowBean {

    private Employee employee;
    private List<Workflow> workflows;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Workflow> getWorkflows() {
        return workflows;
    }

    public void setWorkflows(List<Workflow> workflows) {
        this.workflows = workflows;
    }
}
