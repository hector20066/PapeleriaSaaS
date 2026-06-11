package com.hector.papeleriasaas.observer;

/**
 *
 * @author Ing. Hector Acevedo
 */
public interface Subject {

    void register(Observer observer);

    void remove(Observer observer);

    void notifyObservers();

}
