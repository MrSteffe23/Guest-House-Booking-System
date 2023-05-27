package com.booking.project.laboratory.factory;

public class AccountFactory {
    public static Account createAccount(long accountNo, String accountHolderName, String
            accountType) {
        Account account = null;
        AccountType type = AccountType.valueOf(accountType);
        switch (type){
            case SAVING -> account = new SavingAccount(accountNo, accountHolderName);
            case CURRENT -> account = new CurrentAccount(accountNo, accountHolderName);
        }
        return account;
    }
}