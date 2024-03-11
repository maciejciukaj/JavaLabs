package com.example.libraryProject.singleton;
//Tydzien 2, Singleton, stworzenie prostej klasy singleton z metodami getInstance oraz getInfo
public class SimpleSingleton {
    private static SimpleSingleton instance;

    private String info = "Czesc, jestem Singletonem";

    private SimpleSingleton() {
    }

    public static SimpleSingleton getInstance() {
        if (instance == null) {
            instance = new SimpleSingleton();
        }
        return instance;
    }

    public String getInfo() {
        return info;
    }
}
//Tydzień 2, singleton, koniec