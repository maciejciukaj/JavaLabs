package com.example.libraryProject.service;

import com.example.libraryProject.bookReport.BookReportFactory;
import com.example.libraryProject.model.Book;
import com.example.libraryProject.repository.AuthorRepository;
import com.example.libraryProject.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//Tydzien 4 Fasada, użycie
@Service
public class LibraryManagementFacade {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public LibraryManagementFacade(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public List<Book> findAllBooks(boolean isAvailable) {
        List<Book> bookList =  (List<Book>) bookRepository.findAll();
        if(isAvailable){
            bookList = bookList.stream()
                    .filter(book -> isAvailable)
                    .filter(book -> book.getCoverPath() != null)
                    .collect(Collectors.toList());
        }else{
            bookList = bookList.stream()
                    .filter(book -> !isAvailable)
                    .filter(book -> book.getCoverPath() != null)
                    .collect(Collectors.toList());
        }

        return bookList;
    }


//Tydzien 4 Fasada, koniec
}

