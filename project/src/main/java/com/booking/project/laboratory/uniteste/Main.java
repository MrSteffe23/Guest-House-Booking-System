package com.booking.project.laboratory.uniteste;

public class Main {
    public static void main(String[] args) {
        InterfataDBOperations dbOperations = new DBOperations();

        OperatiiDobanda operatiiDobanda = new OperatiiDobanda(dbOperations);
        int dobanda = operatiiDobanda.calculDobanda(TipDobanda.MARE);
        System.out.printf("Dobanda primita este: " + dobanda + "\n");

/*      User user1 = dbOperations.getUser();
        int dobandaUser = operatiiDobanda.calculDobanda(user1);
        System.out.printf("Dobanda primita este: " + dobandaUser);*/
    }
}
