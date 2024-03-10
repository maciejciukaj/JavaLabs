package com.example.libraryProject.bookReport;

import com.example.libraryProject.interfaces.BookReport;
import com.example.libraryProject.model.Book;
import com.example.libraryProject.repository.BookRepository;

import java.util.List;

public class FantasyBookReport implements BookReport {
    private BookRepository bookRepository;

    public FantasyBookReport(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> generateReport() {
        System.out.println("Generating report for Fantasy books...");
        return bookRepository.findBooksByGenre("Fantasy");
    }
}