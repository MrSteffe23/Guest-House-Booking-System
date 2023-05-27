package com.booking.project.laboratory.factory;

public class StrategyTwo implements InterestStrategy{
    @Override
    public String setInterestStrategy(String interestStrategy) {
        return interestStrategy + " two";
    }
}
