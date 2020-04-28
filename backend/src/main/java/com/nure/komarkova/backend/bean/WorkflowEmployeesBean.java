package com.nure.komarkova.backend.bean;

import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Objects;

@Component
public class WorkflowEmployeesBean {

    private Date date;
    private double productivity;

    public WorkflowEmployeesBean() {
    }

    public WorkflowEmployeesBean(Date date) {
        this.date = date;
    }

    public WorkflowEmployeesBean(Date date, double productivity) {
        this.date = date;
        this.productivity = productivity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getProductivity() {
        return productivity;
    }

    public void setProductivity(double productivity) {
        this.productivity = productivity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WorkflowEmployeesBean that = (WorkflowEmployeesBean) o;
        return date.toLocalDate().getDayOfMonth() == that.date.toLocalDate().getDayOfMonth();
    }

    @Override
    public int hashCode() {
        return Objects.hash(date);
    }
}
