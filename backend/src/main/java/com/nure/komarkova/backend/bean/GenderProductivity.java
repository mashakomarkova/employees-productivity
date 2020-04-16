package com.nure.komarkova.backend.bean;

import org.springframework.stereotype.Component;

@Component
public class GenderProductivity {

    private double maleProductivity;
    private double femaleProductivity;

    public double getFemaleProductivity() {
        return femaleProductivity;
    }

    public void setFemaleProductivity(double femaleProductivity) {
        this.femaleProductivity = femaleProductivity;
    }

    public double getMaleProductivity() {
        return maleProductivity;
    }

    public void setMaleProductivity(double maleProductivity) {
        this.maleProductivity = maleProductivity;
    }
}
