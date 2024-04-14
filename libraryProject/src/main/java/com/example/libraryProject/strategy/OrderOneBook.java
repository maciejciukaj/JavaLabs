package com.example.libraryProject.strategy;

import com.example.libraryProject.interfaces.Strategy;
import com.example.libraryProject.model.Book;
import org.springframework.stereotype.Component;

//Tydzien 6, strategia, implementacja
@Component
public class OrderOneBook implements Strategy {
    @Override
    public void orderBook(Book book) {
        book.nextState();
        book.setAmount(book.getAmount() - 1);
    }
}
//Tydzien 6, strategia, koniec