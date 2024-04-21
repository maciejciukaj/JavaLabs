package com.example.libraryProject.component;

import com.example.libraryProject.interfaces.Mediator;

//tydzien 8, liskov, bazowa ConcreteMediator, pochodna ExtendedMediator, rozszerza funkcjonalność oblugi zdarzen Checkout oraz RETURN, dodając obsługę nowego zdarzenia RENEW. Dzięki dziedziczeniu i wywołaniu metody super.notify(event) z klasy bazowej,
// ExtendedMediator zachowuje istniejącą funkcjonalność oraz dodaje nową
public class ExtendedMediator extends ConcreteMediator {
    private String message = "";
    @Override
    public void notify(String event) {
        super.notify(event);
        if ("RENEW".equals(event)) {
            this.message = "Czas wypożyczenia książki został przedluzony.";
        }
    }
}
//tydzien 8, liskov, koniec