package com.booking.project.laboratory.factory;

public abstract class Account {
    private long accountNo;
    private String accountHolderName;
    private AccountType accountType;
    private InterestStrategy interestStrategy;
    private double amount;

    public void useStrategy(){
        System.out.println(interestStrategy.setInterestStrategy("Strategy "));
    }

    public InterestStrategy getInterestStrategy() {
        return interestStrategy;
    }

    public void setInterestStrategy(InterestStrategy interestStrategy) {
        this.interestStrategy = interestStrategy;
    }

    public long getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(long accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Account(long accountNo, String accountHolderName, AccountType
            accountType) {
        this.accountNo = accountNo;
        this.accountHolderName = accountHolderName;
        this.accountType = accountType;
    }

    public abstract double getInterest(int term);
    public abstract double withdraw(double amount);
}