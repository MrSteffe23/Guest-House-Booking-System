package com.booking.project.laboratory.factory;

public class SavingAccount extends Account {
    public SavingAccount(long accountNo, String accountHolderName) {
        super(accountNo, accountHolderName, AccountType.SAVING);
        //setInterestStrategy("Compound Interest");
    }

    @Override
    public double getInterest(int term) {
        return 1;
    }

    @Override
    public double withdraw(double amount) {
        return 0;
    }
}