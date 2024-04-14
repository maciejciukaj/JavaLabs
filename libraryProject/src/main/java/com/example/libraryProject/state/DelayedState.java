package com.example.libraryProject.state;

import com.example.libraryProject.interfaces.BookState;
import com.example.libraryProject.model.Book;
import org.springframework.stereotype.Component;

//Tydzien 6, State, stan
@Component
public class DelayedState implements BookState {
    @Override
    public void next(Book b) {
        b.setState(new AvailableState());
        b.setAvailable(true);
    }

    @Override
    public void prev(Book b) {
        b.setState(new BorrowedState());
    }

    @Override
    public String printStatus() {
        return("Opóźniona");
    }
}
//Tydzien 6, State, koniec