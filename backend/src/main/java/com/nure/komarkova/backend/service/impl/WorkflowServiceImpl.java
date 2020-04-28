package com.nure.komarkova.backend.service.impl;

import com.nure.komarkova.backend.bean.GenderProductivity;
import com.nure.komarkova.backend.bean.TotalProductivityBean;
import com.nure.komarkova.backend.bean.WorkflowEmployeesBean;
import com.nure.komarkova.backend.entity.CommodityRealization;
import com.nure.komarkova.backend.entity.Workflow;
import com.nure.komarkova.backend.repository.WorkflowRepository;
import com.nure.komarkova.backend.service.WorkflowService;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class WorkflowServiceImpl implements WorkflowService {

    private WorkflowRepository workflowRepository;

    public WorkflowServiceImpl(WorkflowRepository workflowRepository) {
        this.workflowRepository = workflowRepository;
    }

    @Override
    public List<Workflow> findAllWorkFlows() {
        return workflowRepository.findAll();
    }

    @Override
    public Workflow findAllWorkFlowById(Long id) {
        return workflowRepository.findById(id).get();
    }

    @Override
    public List<Workflow> findWorkflowsByDate(Date date) {
        return workflowRepository.findByWorkDate(date);
    }

    @Override
    public TotalProductivityBean compoundProductivity(List<Workflow> allWorkflows) {
        long totalTime = 0;
        long totalSales = 0;
        for (Workflow workflow : allWorkflows) {
            totalTime += ((workflow.getEndTime().getTime() - workflow.getStartTime().getTime()) / (1000 * 60 * 60));
            for (CommodityRealization commodityRealization : workflow.getCommodityRealizations()) {
                totalSales += commodityRealization.getQuantity();
            }
        }
        TotalProductivityBean totalProductivityBean = new TotalProductivityBean();
        totalProductivityBean.setTotalTime(totalTime);
        totalProductivityBean.setSales(totalSales);
        return totalProductivityBean;
    }

    @Override
    public GenderProductivity findGenderProductivity(List<Workflow> workflows) {
        double maleProductivity = 0;
        double femaleProductivity = 0;
        for (Workflow workflow : workflows) {
            if (workflow.getEmployee().getGender().equals("male")) {
                maleProductivity += countProductivity(workflow);
            } else if (workflow.getEmployee().getGender().equals("female")) {
                femaleProductivity += countProductivity(workflow);
            }
        }
        GenderProductivity genderProductivity = new GenderProductivity();
        genderProductivity.setFemaleProductivity(femaleProductivity);
        genderProductivity.setMaleProductivity(maleProductivity);
        return genderProductivity;
    }

    @Override
    public List<WorkflowEmployeesBean> findWorkflowsByDates(Date dateFrom, Date dateTo) {
        List<Workflow> workflows = workflowRepository.findAllByWorkDateBetween(dateFrom, dateTo);
        List<WorkflowEmployeesBean> workflowEmployeesBeans = new ArrayList<>();
        for (Workflow workflow : workflows) {
            WorkflowEmployeesBean workflowEmployeesBean = new WorkflowEmployeesBean(workflow.getWorkDate());
            if (workflowEmployeesBeans.contains(workflowEmployeesBean)) {
                WorkflowEmployeesBean employeesBeanToAdd =
                        workflowEmployeesBeans.get(workflowEmployeesBeans.indexOf(workflowEmployeesBean));
                double productivity = employeesBeanToAdd.getProductivity()+countProductivity(workflow);
                employeesBeanToAdd.setProductivity(productivity);
                workflowEmployeesBeans.set(workflowEmployeesBeans.indexOf(workflowEmployeesBean),employeesBeanToAdd);
            } else {
                workflowEmployeesBeans.add(new WorkflowEmployeesBean(workflow.getWorkDate(), countProductivity(workflow)));
            }
        }
        return workflowEmployeesBeans;
    }

    private double countProductivity(Workflow workflow) {
        double productivity = 0;
        double sales = 0;
        double totalTime = 0;
        for (CommodityRealization commodityRealization : workflow.getCommodityRealizations()) {
            sales += commodityRealization.getQuantity();
        }
        totalTime += (double) (workflow.getEndTime().getTime() - workflow.getStartTime().getTime()) / (1000 * 60 * 60);
        productivity += sales / totalTime;
        return productivity;
    }
}
