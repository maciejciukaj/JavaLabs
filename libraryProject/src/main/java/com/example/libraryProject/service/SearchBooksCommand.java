package com.example.libraryProject.service;

import com.example.libraryProject.interfaces.Command;
import com.example.libraryProject.model.Book;
import org.springframework.ui.Model;

import java.util.List;
//Tydzien 7 otwarte zamkniete
//Tydzien 5, Command
public class SearchBooksCommand implements Command {
    private BookService bookService;
    private String query;
    private Model model;

    public SearchBooksCommand(BookService bookService, String query, Model model) {
        this.bookService = bookService;
        this.query = query;
        this.model = model;
    }
    public String getQuery() {
        return query;
    }

    @Override
    public void execute() {
        List<Book> results = bookService.searchBooks(query);
        model.addAttribute("searchResults", results);
    }
}
//Tydzien 5, Command, Koniec