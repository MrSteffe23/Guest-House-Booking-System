package com.booking.project.singletone;

public class Main {
    public static void main(String[] args) {
        SingletoneACUtilities.getACUtilities().setType("Samsung");
        SingletoneACUtilities.getACUtilities().print();
        SingletoneACUtilities.getACUtilities().turnOn();
        SingletoneACUtilities.getACUtilities().degreesUp();
        SingletoneACUtilities.getACUtilities().degreesDown();
        SingletoneACUtilities.getACUtilities().turnOff();
        Observable observator = new Observable(SingletoneACUtilities.getACUtilities());
        observator.update();
        SingletoneACUtilities.getACUtilities().print();
    }
}
