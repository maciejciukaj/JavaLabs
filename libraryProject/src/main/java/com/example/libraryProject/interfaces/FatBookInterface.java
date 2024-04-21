package com.example.libraryProject.interfaces;

import com.example.libraryProject.model.Book;

import java.util.List;
//Tydzien 8, segregacja interfejsow, interfejs dla Book, zawierający wiele metod używanych dla róznych funkcjonalnosci zwiazanych z ksiazkami
//interfejs zostal podzielony na kilka mniejszych - BookPersistance, BookReport, BookState, Strategy
public interface FatBookInterface {
    void saveObject(Book book);
    List<Book> generateReport();
    public List<Book> findAllBooksAndTranslateTitles();
    void next(Book b);
    void prev(Book b);
    String printStatus();
    void previewBook(Long id);
    public void orderBook(Book book);
}
//Tydzien 8, Segregacja interfejsow, koniec