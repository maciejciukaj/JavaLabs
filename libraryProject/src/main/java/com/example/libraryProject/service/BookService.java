package com.example.libraryProject.service;

import com.example.libraryProject.model.Book;
import com.example.libraryProject.repository.BookRepository;
import com.example.libraryProject.repository.ReaderRepository;
import com.example.libraryProject.strategy.OrderOneBook;
import com.example.libraryProject.strategy.OrderTwoBooks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

//Tydzien 7 otwarte zamkniete
@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ReaderRepository readerRepository;

    @Autowired
    private OrderOneBook orderSingle;

    @Autowired
    private OrderTwoBooks orderDouble;


    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    public List<Book> findAllBooks() {
        return (List<Book>) bookRepository.findAll();
    }

    //Tydzien 10, przetwarzanie strumieniowe
    public List<Book> findAvailableBooks() {
        List<Book> allBooks = (List<Book>) bookRepository.findAll();
        List<Book> availableBooks = allBooks.stream()
                .filter(b -> b != null)
                .filter(b -> b.isAvailable())
                .filter(b -> Objects.equals(b.getState().printStatus(), "Dostępna"))
                .toList();
        return availableBooks;
    }
    //Tydzien 10, koniec

    public Book findBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Nie znaleziono książki z ID: " + id));
    }


    public List<Book> searchBooks(String query) {
        return bookRepository.findBooksByTitleContaining(query);

    }


    public void deleteBook(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));

        book.getReaders().forEach(reader -> reader.getBooks().remove(book));
        readerRepository.saveAll(book.getReaders());

        bookRepository.delete(book);
    }

    //Tydzien 6, strategia, użycie
    public void orderBook(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        orderSingle.orderBook(book);
        bookRepository.save(book);
    }

    public void orderBookTwice(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        orderDouble.orderBook(book);
        bookRepository.save(book);
    }
    //Tydzien 6, strategia, koniec
}
