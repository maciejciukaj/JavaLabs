package com.example.libraryProject.controller;


import com.example.libraryProject.model.Author;
import com.example.libraryProject.model.Book;
import com.example.libraryProject.service.AuthorService;
import com.example.libraryProject.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
    private AuthorService authorService;

    @GetMapping("/books")
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.findAllBooks());
        return "books";
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


    @PostMapping("/books/add")
    public String addBook(@ModelAttribute("book") Book book) {
        bookService.saveBook(book);
        return "redirect:/books";
    }

    @GetMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
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

        Book clonedBook = originalBook.clone();  // klonowanie książki

        clonedBook.setTitle("Władca Pierścieni - sklonowana książka"); // modyfikacja tytułu w sklonowanej książce, aby pokazać, że jest to nowa instancja

        model.addAttribute("originalBook", originalBook);
        model.addAttribute("clonedBook", clonedBook);

        return "clone-book";
    }
    //Tydzien 2, prototyp, koniec

}