package com.booking.project.laboratory.singletone;

public class SingletoneACUtilities {
    private static ACUtilities acUtilities = null;

    private SingletoneACUtilities(){}

    public static ACUtilities getACUtilities(){
        if(acUtilities == null){
            acUtilities = new ACUtilities();
        }
        return acUtilities;
    }
}
