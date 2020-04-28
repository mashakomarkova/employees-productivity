package com.nure.komarkova.backend.service;

import com.nure.komarkova.backend.bean.GenderProductivity;
import com.nure.komarkova.backend.bean.TotalProductivityBean;
import com.nure.komarkova.backend.bean.WorkflowEmployeesBean;
import com.nure.komarkova.backend.entity.Workflow;

import java.sql.Date;
import java.util.List;

public interface WorkflowService {

    List<Workflow> findAllWorkFlows();

    Workflow findAllWorkFlowById(Long id);

    List<Workflow> findWorkflowsByDate(Date date);

    TotalProductivityBean compoundProductivity(List<Workflow> workflows);

    GenderProductivity findGenderProductivity(List<Workflow> workflows);

    List<WorkflowEmployeesBean> findWorkflowsByDates(Date dateFrom, Date dateTo);
}
