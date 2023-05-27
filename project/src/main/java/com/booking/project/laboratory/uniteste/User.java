package com.booking.project.laboratory.uniteste;

public class User {
    private String name;
    private RiskType riskType;

    public User(String name, RiskType riskType) {
        this.name = name;
        this.riskType = riskType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RiskType getRiskType() {
        return riskType;
    }

    public void setRiskType(RiskType riskType) {
        this.riskType = riskType;
    }
}
