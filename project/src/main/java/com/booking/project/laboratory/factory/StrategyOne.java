package com.booking.project.laboratory.factory;

public class StrategyOne implements InterestStrategy{
    @Override
    public String setInterestStrategy(String interestStrategy) {
        return interestStrategy + " one";
    }
}
