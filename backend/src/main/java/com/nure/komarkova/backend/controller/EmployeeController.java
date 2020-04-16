package com.nure.komarkova.backend.controller;

import com.google.gson.Gson;
import com.nure.komarkova.backend.bean.EmployeeBean;
import com.nure.komarkova.backend.bean.EmployeeWorkflowBean;
import com.nure.komarkova.backend.entity.Employee;
import com.nure.komarkova.backend.service.EmployeeService;
import com.nure.komarkova.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    private EmployeeService employeeService;
    private UserService userService;

    public EmployeeController(EmployeeService employeeService, UserService userService) {
        this.employeeService = employeeService;
        this.userService = userService;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/createEmployee")
    public HttpStatus create(@RequestBody String data) {
        EmployeeBean employeeBean = new Gson().fromJson(data, EmployeeBean.class);
        Employee employee = new Employee(employeeBean);
        employee.setUser(userService.findUserById(employeeBean.getUserId()));
        employeeService.createEmployee(employee);
        return HttpStatus.OK;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/viewAllEmployees")
    public ResponseEntity<List<Employee>> findAllEmployees() {
        List<Employee> employees = employeeService.findAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/viewAllEmployees/{id}")
    public ResponseEntity<EmployeeWorkflowBean> findAllEmployees(@PathVariable Long id) {
        Employee employee = employeeService.findEmployeeById(id);
        EmployeeWorkflowBean employeeWorkflowBean = new EmployeeWorkflowBean();
        employeeWorkflowBean.setEmployee(employee);
        employeeWorkflowBean.setWorkflows(employeeService.findWorkflowsByEmployeeId(employee.getId()));
        return new ResponseEntity<>(employeeWorkflowBean, HttpStatus.OK);
    }
}
