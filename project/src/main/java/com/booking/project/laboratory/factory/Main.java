package com.booking.project.laboratory.factory;

public class Main {
    public static void main(String[] args) {
        Account accountSaving = AccountFactory.createAccount(2, "Stefan", "SAVING");
        Account accountCurrent = AccountFactory.createAccount(1, "Andreea", "CURRENT");
        System.out.println(accountSaving.getInterest(1));
        System.out.println(accountCurrent.getInterest(1));

        StrategyOne strategyOne = new StrategyOne();
        StrategyTwo strategyTwo = new StrategyTwo();
        accountSaving.setInterestStrategy(strategyOne);
        accountSaving.useStrategy();
        accountSaving.setInterestStrategy(strategyTwo);
        accountSaving.useStrategy();
    }
}
