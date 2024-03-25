package com.example.libraryProject.controller;

import com.example.libraryProject.component.Navigation;
import com.example.libraryProject.model.Book;
import com.example.libraryProject.service.BookService;
import com.example.libraryProject.service.BookServiceTranslationAdapterImpl;
import com.example.libraryProject.service.LibraryManagementFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

//Tydzien 4 Fasada, kontroler, użycie
@Controller
public class LibraryController {

    @Autowired
    private Navigation navigation;

    @Autowired
    private BookServiceTranslationAdapterImpl bookServiceTranslationAdapterImpl;

    @Autowired
    private BookService bookService;


    private final LibraryManagementFacade libraryManagementFacade;

    @Autowired
    public LibraryController(LibraryManagementFacade libraryManagementFacade) {
        this.libraryManagementFacade = libraryManagementFacade;
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("navigationElements", navigation.getNavigation());
    }

    //Tydzien 4 FlyWeight, aplikacja posiada wiele obiektów book oraz okładki tych książek
    @GetMapping("/books")
    public String listBooks(Model model) {
        List<Book> books = bookService.findAllBooks();
        model.addAttribute("books", books);
        return "books";
    }
    //Tydzień 4 FlyWeight, koniec

    @GetMapping("/books/{genre}")
    public String listBooksByGenre(Model model, @PathVariable("genre") String genre) {
        List<Book> books = libraryManagementFacade.findAllBooks(true,genre);
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/books/translated")
    public String listBooksWithTranslatedTitles(Model model) {
        model.addAttribute("books", bookServiceTranslationAdapterImpl.findAllBooksAndTranslateTitles());
        return "books";
    }
}

//Tydzien 4 Fasada, kontroler, koniec
