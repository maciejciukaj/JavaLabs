package com.example.libraryProject.bookReport;

import com.example.libraryProject.interfaces.BookReport;
import com.example.libraryProject.repository.BookRepository;
import org.springframework.stereotype.Component;

//Tydzien 2, factory, klasa fabryki pozwala na zwrócenie żądanego obiektu, wywołując metodę fabryki createBookReport i podając w argumencie gatunek ksiązki który sprawdzany jest przez switch.
// Po poprawnym wpisaniu gatunku zwrócony zostanie obiekt, na którym mozna wywolac metodę generateReport zdefiniowaną w interfejsie BookReport.
@Component
public class BookReportFactory {
    private BookRepository bookRepository;

    public BookReportFactory(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookReport createBookReport(String genre) {
        switch (genre.toLowerCase()) {
            case "fantasy":
                return new FantasyBookReport(bookRepository);
            case "sci-fi":
                return new ScifiBookReport(bookRepository);
            default:
                throw new IllegalArgumentException("Unknown genre: " + genre);
        }
    }
}
//Tydzien 2, factory, koniec
