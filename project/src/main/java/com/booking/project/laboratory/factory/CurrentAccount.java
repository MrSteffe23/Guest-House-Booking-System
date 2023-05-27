package com.booking.project.laboratory.factory;

public class CurrentAccount extends Account {
    public CurrentAccount(long accountNo, String accountHolderName) {
        super(accountNo, accountHolderName, AccountType.CURRENT);
        //setInterestStrategy("Simple Interest");
    }

    @Override
    public double getInterest(int term) {
        return 0;
    }

    @Override
    public double withdraw(double amount) {
        return 0;
    }
}