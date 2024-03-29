package com.example.libraryProject.bookReport;

import com.example.libraryProject.interfaces.BookReport;
import com.example.libraryProject.model.Book;
import com.example.libraryProject.repository.BookRepository;

import java.util.List;
//Tydzien 2, fabryka, klasa użyta w fabryce
public class FantasyBookReport implements BookReport {
    final private BookRepository bookRepository;

    public FantasyBookReport(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> generateReport() {
        System.out.println("Generating report for fantasy books...");
        return bookRepository.findBooksByGenre("Fantasy");
    }
}
//Tydzien 2, fabryka, koniec