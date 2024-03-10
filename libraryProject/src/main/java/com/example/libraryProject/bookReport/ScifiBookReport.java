package com.example.libraryProject.bookReport;

import com.example.libraryProject.interfaces.BookReport;
import com.example.libraryProject.model.Book;
import com.example.libraryProject.repository.BookRepository;

import java.util.List;

public class ScifiBookReport implements BookReport {
    private BookRepository bookRepository;

    public ScifiBookReport(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> generateReport() {
        System.out.println("Generating report for Sci-Fi books...");
        return bookRepository.findBooksByGenre("Sci-Fi");
    }
}