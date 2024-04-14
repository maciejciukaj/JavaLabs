package com.example.libraryProject.state;

import com.example.libraryProject.interfaces.BookState;
import com.example.libraryProject.model.Book;
import org.springframework.stereotype.Component;

//Tydzien 6, State, stan
@Component
public class BorrowedState implements BookState {
    @Override
    public void next(Book b) {
        b.setState(new DelayedState());
    }

    @Override
    public void prev(Book b) {
        b.setState(new AvailableState());
        b.setAvailable(true);
    }

    @Override
    public String printStatus() {
        return("Wypożyczona");
    }
}
//Tydzien 6, State, koniec