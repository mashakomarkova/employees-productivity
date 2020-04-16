package com.nure.komarkova.backend.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Entity
public class Workflow {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Date workDate;
    private Time startTime;
    private Time endTime;
    @ManyToOne
    private Employee employee;
    @OneToMany
    private List<CommodityRealization> commodityRealizations;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getWorkDate() {
        return workDate;
    }

    public void setWorkDate(Date workDate) {
        this.workDate = workDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<CommodityRealization> getCommodityRealizations() {
        return commodityRealizations;
    }

    public void setCommodityRealizations(List<CommodityRealization> commodityRealizations) {
        this.commodityRealizations = commodityRealizations;
    }
}
