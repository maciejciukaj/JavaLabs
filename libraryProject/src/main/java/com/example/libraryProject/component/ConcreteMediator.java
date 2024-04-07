package com.example.libraryProject.component;

import com.example.libraryProject.interfaces.Mediator;
//Tydzien 5, Mediator
public class ConcreteMediator implements Mediator {
    private String message = "";

    @Override
    public void notify(String event) {
        if ("CHECKOUT".equals(event)) {
            this.message = "Książka została wypożyczona.";
        } else if ("RETURN".equals(event)) {
            this.message = "Książka została zwrócona.";
        }
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
//Tydzien 5, Mediator, koniec