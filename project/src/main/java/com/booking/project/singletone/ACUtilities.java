package com.booking.project.singletone;

public class ACUtilities implements Observer{
    private String type;

    public ACUtilities(){}
    public void turnOn() {
        System.out.println("AC turned on 😎");
    }
    public void turnOff(){
        System.out.println("AC turned off 😴");
    }
    public void degreesUp(){
        System.out.println("Degrees up");
    }
    public void degreesDown(){
        System.out.println("Degrees down");
    }

    public void print(){
        System.out.println("AC Utilities by --> " + type);
    }

    @Override
    public void onChange(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
