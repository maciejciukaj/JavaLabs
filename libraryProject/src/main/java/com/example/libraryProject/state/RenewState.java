package com.example.libraryProject.state;

import com.example.libraryProject.interfaces.BookState;
import com.example.libraryProject.model.Book;
import org.springframework.stereotype.Component;
//Tydzien 8, Liskov, klasa bazowa BookState, klasa pochodna RenewState, renewstate pelni funkcje zarządzania stanem ksiazki dostosowujac zachowanie ksiazki do wymagań biblioteki, nie łamiąc działania interfejsu BookState
@Component
public class RenewState implements BookState {
    @Override
    public void next(Book b) {
        b.setState(new AvailableState());
        b.setAvailable(true);
    }

    @Override
    public void prev(Book b) {
    }

    @Override
    public String printStatus() {
        return("Odnowiona");
    }
}
//Tydzine 8, liskov, koniec