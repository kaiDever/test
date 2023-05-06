package org.example.designModle;

import java.util.ArrayList;
import java.util.List;

public class WeatherData implements ObserverAble {
    private String name;
    private String email;
    private final List<Observer> observers = new ArrayList<>();

    public WeatherData() {
    }

    public void setChangeInformation(String name, String email) {
        this.name = name;
        this.email = email;
        notifyRegister(name, email);
    }

    @Override
    public void removeRegister(Observer observer) {
        if (observers.size() > 0) {
            int i = observers.indexOf(observer);
            if (i >= 0) {
                observers.remove(i);
            }
        }
    }

    @Override
    public void addRegister(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void notifyRegister(String name, String email) {
        if (observers.size() <= 0)
            return;
        for (Observer observer : observers) {
            observer.updateInformation(name, email);
        }
    }
}
