package com.nure.komarkova.backend.controller;

import com.nure.komarkova.backend.bean.GenderProductivity;
import com.nure.komarkova.backend.bean.TotalProductivityBean;
import com.nure.komarkova.backend.entity.Workflow;
import com.nure.komarkova.backend.service.WorkflowService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
public class WorkflowController {

    private WorkflowService workflowService;

    public WorkflowController(WorkflowService workflowService) {
        this.workflowService = workflowService;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/findAllWorkflows")
    public ResponseEntity<List<Workflow>> findAllWorkflows() {
        return new ResponseEntity<>(workflowService.findAllWorkFlows(), HttpStatus.OK);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/findWorkFlow/{id}")
    public ResponseEntity<Workflow> findAllWorkflowById(@PathVariable String id) {
        Workflow workflow = workflowService.findAllWorkFlowById(Long.parseLong(id));
        return new ResponseEntity<>(workflow, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/findTotalProductivity")
    public ResponseEntity<TotalProductivityBean> findTotalProductivity() {
        List<Workflow> allWorkflows = workflowService.findAllWorkFlows();
        TotalProductivityBean totalProductivityBean = workflowService.compoundProductivity(allWorkflows);
        return new ResponseEntity<>(totalProductivityBean, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/findTotalProductivity/{date}")
    public ResponseEntity<TotalProductivityBean> findTotalProductivity(@PathVariable String date) {
        List<Workflow> allWorkflows = workflowService.findWorkflowsByDate(Date.valueOf(date));
        TotalProductivityBean totalProductivityBean = workflowService.compoundProductivity(allWorkflows);
        return new ResponseEntity<>(totalProductivityBean, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/findWorkflows/{date}")
    public ResponseEntity<List<Workflow>> findWorkflowsByDate(@PathVariable String date) {
        List<Workflow> allWorkflows = workflowService.findWorkflowsByDate(Date.valueOf(date));
        return new ResponseEntity<>(allWorkflows, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/findGenderProductivity")
    public ResponseEntity<GenderProductivity> findGenderProductivity() {
        List<Workflow> allWorkflows = workflowService.findAllWorkFlows();
        GenderProductivity genderProductivity = workflowService.findGenderProductivity(allWorkflows);
        return new ResponseEntity<>(genderProductivity, HttpStatus.OK);
    }
}
