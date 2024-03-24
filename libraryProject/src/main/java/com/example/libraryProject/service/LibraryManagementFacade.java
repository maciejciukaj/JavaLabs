package com.example.libraryProject.service;

import com.example.libraryProject.model.Book;
import com.example.libraryProject.repository.AuthorRepository;
import com.example.libraryProject.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//Tydzien 4 Fasada, kontroler, użycie
@Service
public class LibraryManagementFacade {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public LibraryManagementFacade(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public List<Book> findAllBooks() {
        return (List<Book>) bookRepository.findAll();
    }
//Tydzien 4 Fasada, kontroler, koniec
}

