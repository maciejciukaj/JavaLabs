package com.example.libraryProject.interfaces;

import com.example.libraryProject.model.Book;

//Tydzien 6, state, interfejs stanu
public interface BookState {
    void next(Book b);
    void prev(Book b);
    String printStatus();
}
//Tydzien 6, state, koniec