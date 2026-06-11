package com.hector.papeleriasaas.observer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class ConcreteSubject implements Subject {

    private static ConcreteSubject instance;
    private final List<Observer> observers = Collections.synchronizedList(new ArrayList<>());

    public synchronized static ConcreteSubject getInstance() {
        if (instance == null) {
            instance = new ConcreteSubject();
        }

        return instance;
    }

    @Override
    public void register(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void remove(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(Observer::update);
    }

}
