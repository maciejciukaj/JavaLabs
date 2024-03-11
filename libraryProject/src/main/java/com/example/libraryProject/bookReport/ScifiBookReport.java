package com.example.libraryProject.bookReport;

import com.example.libraryProject.interfaces.BookReport;
import com.example.libraryProject.model.Book;
import com.example.libraryProject.repository.BookRepository;

import java.util.List;
//Tydzien 2, fabryka, klasa użyta w fabryce
public class ScifiBookReport implements BookReport {
    final private BookRepository bookRepository;

    public ScifiBookReport(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> generateReport() {
        System.out.println("Generating report for sci-fi books...");
        return bookRepository.findBooksByGenre("Sci-Fi");
    }
}
//Tydzien 2, fabryka, koniec