package com.example.libraryProject.state;

import com.example.libraryProject.interfaces.BookState;
import com.example.libraryProject.model.Book;
import org.springframework.stereotype.Component;

//Tydzien 6, State, stan
@Component
public class AvailableState implements BookState {
    @Override
    public void next(Book b) {
        b.setState(new BorrowedState());
        b.setAvailable(false);
    }

    @Override
    public void prev(Book b) {
        throw new IllegalStateException("This is the first state");
    }

    @Override
    public String printStatus() {
        return("Dostępna");
    }
}
//Tydzien 6, State, koniec