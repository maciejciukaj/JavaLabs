package com.example.libraryProject.controller;


import com.example.libraryProject.bridge.DatabasePersistenceUnit;
import com.example.libraryProject.bridge.TextFilePersistenceUnit;
import com.example.libraryProject.component.Navigation;
import com.example.libraryProject.component.SearchHistory;
import com.example.libraryProject.interfaces.Command;
import com.example.libraryProject.model.Author;
import com.example.libraryProject.model.Book;
import com.example.libraryProject.model.BookMemento;
import com.example.libraryProject.observer.BookLog;
import com.example.libraryProject.observer.LibraryLogger;
import com.example.libraryProject.service.AuthorService;
import com.example.libraryProject.service.BookService;
import com.example.libraryProject.service.BookServiceTranslationAdapterImpl;
import com.example.libraryProject.service.SearchBooksCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

//Tydzien 2, singleton, wzorzec singleton jest stosowany domyślnie przez kontener Springa do zarządzania beanami.
// @Service, @Controller i inne adnotacje stereotypowe Springa ( takie jak @Repository, @Component, itp.) są beanem singletonem
@Controller
public class BookController {
    //Tydzień 2, singleton, koniec

    @Autowired
    private BookService bookService;

    @Autowired
    private Navigation navigation;

    @Autowired
    private BookServiceTranslationAdapterImpl bookServiceTranslationAdapterImpl;

    @Autowired
    private DatabasePersistenceUnit databasePersistenceUnit;

    @Autowired
    private TextFilePersistenceUnit textFilePersistenceUnit;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private SearchHistory searchHistory;

    @Autowired
    private LibraryLogger logger;

    @Autowired
    private BookLog log;

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("navigationElements", navigation.getNavigation());
        logger.addLog(log);
    }


    @GetMapping("/books/add")
    public String showAddForm(Model model) {
        Book book = new Book.Builder().build();
        List<Author> authors = authorService.findAllAuthors();
        List<String> genres = Arrays.asList("Fiction", "Science", "Fantasy", "Biography", "History", "Horror","Sci-Fi");
        model.addAttribute("book", book);
        model.addAttribute("authors", authors);
        model.addAttribute("genres", genres);
        return "add-book";
    }
    //Tydzien 5, Command
    @GetMapping("/books/search")
    public String searchBooks(@RequestParam String query, Model model) {
        List<Book> results = bookService.searchBooks(query);
        model.addAttribute("searchResults", results);
        searchHistory.addCommand(new SearchBooksCommand(bookService, query, model));
        return "search-result";
    }

    @ModelAttribute("searchHistory")
    public List<Command> searchHistory() {
        return searchHistory.getHistory();
    }
    //Tydzien 5, Command,Koniec


    //Tydzien 3, Bridge, użycie
    @PostMapping("/books/add")
    public String addBook(@ModelAttribute("book") Book book) {
        databasePersistenceUnit.saveObject(book);
        textFilePersistenceUnit.saveObject(book);
        logger.setLogMessage("New book added");
        return "redirect:/books";
    }
    //Tydzien 3, Bridge, koniec



    @GetMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        logger.setLogMessage("Book deleted");
        return "redirect:/books";
    }
    @PostMapping("/books/update/{id}")
    public String updateBook(@PathVariable Long id, @ModelAttribute Book book, RedirectAttributes redirectAttributes) {

        Book existingBook = bookService.findBookById(id);
        if (existingBook != null) {
            existingBook.setTitle(book.getTitle());
            existingBook.setGenre(book.getGenre());
            existingBook.setAvailable(book.isAvailable());
            existingBook.setAmount(book.getAmount());
            bookService.saveBook(existingBook);
            redirectAttributes.addFlashAttribute("successMessage", "Książka została zaktualizowana.");
            logger.setLogMessage("Book updated");
            return "redirect:/books";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Nie znaleziono książki.");
            return "redirect:/books";
        }
    }
//Tydzien 2, prototyp, użycie
    @GetMapping("/cloneBook")
    public String cloneBook(Model model) {
        Book originalBook = new Book.Builder()     // stworzenie nowej książki, na podstawie której będę klonował
                .setTitle("Władca Pierścieni")
                .setGenre("Fantasy")
                .setIsAvailable(true)
                .setAmount(5)
                .build();

        Book clonedBook = originalBook.clone();

        clonedBook.setTitle("Władca Pierścieni - sklonowana książka"); // modyfikacja tytułu w sklonowanej książce, aby pokazać, że jest to nowa instancja

        model.addAttribute("originalBook", originalBook);
        model.addAttribute("clonedBook", clonedBook);

        return "clone-book";
    }
    //Tydzien 2, prototyp, koniec
//tydzień 5, memento
    @GetMapping("/books/edit/{id}")
    public String editBook(@PathVariable Long id, Model model) {
        Book book = bookService.findBookById(id);
        BookMemento memento = book.save();
        model.addAttribute("book", book);
        model.addAttribute("memento", memento);
        return "edit-book";
    }

    @PostMapping("/books/cancel/{id}")
    public String cancelEdit(@PathVariable Long id, @SessionAttribute BookMemento memento) {
        Book book = bookService.findBookById(id);
        book.restore(memento);
        return "redirect:/books";
    }
//tydzień 5, memento, koniec

    //tydzień 6, obserwator
    @GetMapping("/log")
    public String showLog(Model model) {
        model.addAttribute("log", log.getLogMessages());
        return "log";
    }
    //tydzień 6, obserwator, koniec

    //tydzień 6, state
    @GetMapping("/books/order/{id}")
    public String orderBook(@PathVariable Long id) {
        bookService.orderBook(id);
        logger.setLogMessage("Book ordered");
        return "redirect:/books";
    }
    @GetMapping("/books/ordertwice/{id}")
    public String orderBookTwice(@PathVariable Long id) {
        bookService.orderBookTwice(id);
        logger.setLogMessage("Book ordered");
        return "redirect:/books";
    }
    //tydzień 6, state, koniec
}