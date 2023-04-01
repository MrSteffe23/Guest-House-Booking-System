package com.booking.project.singletone;

public class Observable {
    Observer observer;

    public Observable(Observer observer){
        this.observer = observer;
    }

    public void update(){
        observer.onChange("Del");
    }
}
