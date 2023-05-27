package com.booking.project.laboratory.factory;

public enum AccountType {
    SAVING(2.0d), //constante si private (specifice enumului)
    CURRENT(1.0d);

    private double rate;

    AccountType(final double rate) {
        this.rate = rate;
    }

    public double getRate() {
        return rate;
    }
}