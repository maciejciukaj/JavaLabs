package com.example.libraryProject.service;

import com.example.libraryProject.model.Book;
import com.example.libraryProject.repository.BookRepository;
import com.example.libraryProject.repository.ReaderRepository;
import com.example.libraryProject.strategy.OrderOneBook;
import com.example.libraryProject.strategy.OrderTwoBooks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//Tydzien 14 Testy początek
@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private ReaderRepository readerRepository;

    @Mock
    private OrderOneBook orderSingle;

    @Mock
    private OrderTwoBooks orderDouble;

    @InjectMocks
    private BookService bookService;

    private Book book;

    @BeforeEach
    void setUp() {
        book = new Book();
        book.setId(1L);
        book.setAvailable(true);
        book.setStatus("Dostępna");
    }

    @Test
    void testSaveBook() {
        bookService.saveBook(book);
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void testFindAllBooks() {
        when(bookRepository.findAll()).thenReturn(Collections.singletonList(book));
        List<Book> books = bookService.findAllBooks();
        assertEquals(1, books.size());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void testFindAvailableBooks() {
        when(bookRepository.findAll()).thenReturn(Collections.singletonList(book));
        List<Book> books = bookService.findAvailableBooks();
        assertEquals(1, books.size());
        assertEquals(book, books.get(0));
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void testFindBookById() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        Book foundBook = bookService.findBookById(1L);
        assertEquals(book, foundBook);
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    void testFindBookById_NotFound() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> bookService.findBookById(1L));
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    void testSearchBooks() {
        when(bookRepository.findBooksByTitleContaining("Title")).thenReturn(Collections.singletonList(book));
        List<Book> books = bookService.searchBooks("Title");
        assertEquals(1, books.size());
        verify(bookRepository, times(1)).findBooksByTitleContaining("Title");
    }

    @Test
    void testDeleteBook() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        bookService.deleteBook(1L);
        verify(bookRepository, times(1)).delete(book);
    }

    @Test
    void testOrderBook() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        bookService.orderBook(1L);
        verify(orderSingle, times(1)).orderBook(book);
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void testOrderBookTwice() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        bookService.orderBookTwice(1L);
        verify(orderDouble, times(1)).orderBook(book);
        verify(bookRepository, times(1)).save(book);
    }
    //Tydzien 14 Testy koniec
}
