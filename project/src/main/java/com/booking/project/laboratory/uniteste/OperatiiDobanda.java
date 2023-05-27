package com.booking.project.laboratory.uniteste;

public class OperatiiDobanda {
    public InterfataDBOperations dbOperations;

    public OperatiiDobanda(InterfataDBOperations dbOperations){
        this.dbOperations = dbOperations;
    }

    public OperatiiDobanda(){
        
    }

    public int calculDobanda(TipDobanda tipDobanda){
        switch(tipDobanda){
            case MICA:
                return 5;
            case MEDIE:
                return 10;
            case MARE:
                return 15;
            default:
                return 0;
        }
    }

    public int calculDobanda(){
        //aici avem o dependinta catre o clasa externa
        //in teste avem nevoie de cineva care sa faca legatura
        switch(dbOperations.getUser().getRiskType()){
            case LOW:
                return calculDobanda(TipDobanda.MARE);
            case MEDIUM:
                return calculDobanda(TipDobanda.MEDIE);
            case HIGH:
                return calculDobanda(TipDobanda.MICA);
            default:
                return 0;
        }
    }
}
