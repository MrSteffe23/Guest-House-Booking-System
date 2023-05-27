package com.booking.project.laboratory.singletone;

public class Observable {
    Observer observer;

    public Observable(Observer observer){
        this.observer = observer;
    }

    public void update(){
        observer.onChange("Del");
    }
}
