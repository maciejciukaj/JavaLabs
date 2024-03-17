package com.example.libraryProject.bridge;

import com.example.libraryProject.interfaces.BookPersistence;
import com.example.libraryProject.model.Book;
import com.example.libraryProject.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//Tydzien 3, Bridge, implementator bazy danych
@Component
public class DatabasePersistenceUnit implements BookPersistence {

    @Autowired
    private BookService bookService;

    @Override
    public void saveObject(Book book) {
        bookService.saveBook(book);
    }
}
//Tydzien 3, Bridge, koniec
