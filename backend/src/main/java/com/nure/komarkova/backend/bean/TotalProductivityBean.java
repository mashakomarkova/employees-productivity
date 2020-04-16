package com.nure.komarkova.backend.bean;

import org.springframework.stereotype.Component;

@Component
public class TotalProductivityBean {

    private Long totalTime;
    private Long sales;

    public Long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Long totalTime) {
        this.totalTime = totalTime;
    }

    public Long getSales() {
        return sales;
    }

    public void setSales(Long sales) {
        this.sales = sales;
    }
}
