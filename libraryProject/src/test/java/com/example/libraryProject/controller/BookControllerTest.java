package com.example.libraryProject.controller;

import com.example.libraryProject.bridge.DatabasePersistenceUnit;
import com.example.libraryProject.bridge.TextFilePersistenceUnit;
import com.example.libraryProject.component.Navigation;
import com.example.libraryProject.component.SearchHistory;
import com.example.libraryProject.interfaces.Command;
import com.example.libraryProject.model.Author;
import com.example.libraryProject.model.Book;
import com.example.libraryProject.observer.BookLog;
import com.example.libraryProject.observer.LibraryLogger;
import com.example.libraryProject.service.AuthorService;
import com.example.libraryProject.service.BookService;
import com.example.libraryProject.service.BookServiceTranslationAdapterImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//Tydzien 14 Testy początek
@ExtendWith(MockitoExtension.class)
@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    @Qualifier("bookService")
    private BookService bookService;

    @MockBean
    private Navigation navigation;

    @MockBean
    private BookServiceTranslationAdapterImpl bookServiceTranslationAdapterImpl;

    @MockBean
    private DatabasePersistenceUnit databasePersistenceUnit;

    @MockBean
    private TextFilePersistenceUnit textFilePersistenceUnit;

    @MockBean
    private AuthorService authorService;

    @MockBean
    private SearchHistory searchHistory;

    @MockBean
    private LibraryLogger logger;

    @MockBean
    private BookLog log;

    private List<Author> authors;
    private List<Book> books;

    @BeforeEach
    void setUp() {
        authors = new ArrayList<>();
        authors.add(new Author.Builder().setName("Author1").build());
        authors.add(new Author.Builder().setName("Author2").build());

        books = new ArrayList<>();
        books.add(new Book.Builder().setTitle("Book1").setGenre("Fiction").build());
        books.add(new Book.Builder().setTitle("Book2").setGenre("Science").build());
    }

    @Test
    void testShowAddForm() throws Exception {
        when(authorService.findAllAuthors()).thenReturn(authors);

        mockMvc.perform(get("/books/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-book"))
                .andExpect(model().attributeExists("book"))
                .andExpect(model().attribute("authors", hasSize(2)))
                .andExpect(model().attribute("genres", hasSize(7)));

        verify(authorService, times(1)).findAllAuthors();
    }

    @Test
    void testAddBook() throws Exception {
        mockMvc.perform(post("/books/add").flashAttr("book", new Book.Builder().build()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/books"));

        verify(databasePersistenceUnit, times(1)).saveObject(any(Book.class));
        verify(textFilePersistenceUnit, times(1)).saveObject(any(Book.class));
        verify(logger, times(1)).setLogMessage("New book added");
    }

    @Test
    void testSearchBooks() throws Exception {
        when(bookService.searchBooks(anyString())).thenReturn(books);

        mockMvc.perform(get("/books/search").param("query", "Book"))
                .andExpect(status().isOk())
                .andExpect(view().name("search-result"))
                .andExpect(model().attributeExists("searchResults"))
                .andExpect(model().attribute("searchResults", hasSize(2)));

        verify(bookService, times(1)).searchBooks(anyString());
        verify(searchHistory, times(1)).addCommand(any(Command.class));
    }

    @Test
    void testDeleteBook() throws Exception {
        mockMvc.perform(get("/books/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/books"));

        verify(bookService, times(1)).deleteBook(1L);
        verify(logger, times(1)).setLogMessage("Book deleted");
    }

    @Test
    void testUpdateBook() throws Exception {
        Book book = new Book.Builder().setTitle("Book1").setGenre("Fiction").build();
        when(bookService.findBookById(1L)).thenReturn(book);

        mockMvc.perform(post("/books/update/1").flashAttr("book", book))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/books"));

        verify(bookService, times(1)).findBookById(1L);
        verify(bookService, times(1)).saveBook(any(Book.class));
        verify(logger, times(1)).setLogMessage("Book updated");
    }

    @Test
    void testCloneBook() throws Exception {
        mockMvc.perform(get("/cloneBook"))
                .andExpect(status().isOk())
                .andExpect(view().name("clone-book"))
                .andExpect(model().attributeExists("originalBook"))
                .andExpect(model().attributeExists("clonedBook"));
    }

    @Test
    void testEditBook() throws Exception {
        Book book = new Book.Builder().setTitle("Book1").setGenre("Fiction").build();
        when(bookService.findBookById(1L)).thenReturn(book);

        mockMvc.perform(get("/books/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("edit-book"))
                .andExpect(model().attributeExists("book"))
                .andExpect(model().attributeExists("memento"));

        verify(bookService, times(1)).findBookById(1L);
    }

    @Test
    void testCancelEdit() throws Exception {
        Book book = new Book.Builder().setTitle("Book1").setGenre("Fiction").build();
        when(bookService.findBookById(1L)).thenReturn(book);

        mockMvc.perform(post("/books/cancel/1").sessionAttr("memento", book.save()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/books"));

        verify(bookService, times(1)).findBookById(1L);
    }

    @Test
    void testShowLog() throws Exception {
        mockMvc.perform(get("/log"))
                .andExpect(status().isOk())
                .andExpect(view().name("log"))
                .andExpect(model().attributeExists("log"));
    }

    @Test
    void testOrderBook() throws Exception {
        mockMvc.perform(get("/books/order/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/books"));

        verify(bookService, times(1)).orderBook(1L);
        verify(logger, times(1)).setLogMessage("Book ordered");
    }

    @Test
    void testOrderBookTwice() throws Exception {
        mockMvc.perform(get("/books/ordertwice/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/books"));

        verify(bookService, times(1)).orderBookTwice(1L);
        verify(logger, times(1)).setLogMessage("Book ordered");
    }
    //Tydzien 14 Testy koniec
}
