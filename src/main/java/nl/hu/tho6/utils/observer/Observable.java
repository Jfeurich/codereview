package nl.hu.tho6.utils.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Liam on 16-1-2015.
 */
public abstract class Observable {
    private List<Observer> observers = new ArrayList<Observer>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObersvers(Object object) {
        for (Observer observer : observers) {
            observer.update(object);
        }
    }
}
