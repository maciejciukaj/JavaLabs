package com.example.libraryProject.service;

import com.example.libraryProject.model.Book;
import com.example.libraryProject.repository.BookRepository;
import com.example.libraryProject.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ReaderRepository readerRepository;


    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    public List<Book> findAllBooks() {
        return (List<Book>) bookRepository.findAll();
    }

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
}
