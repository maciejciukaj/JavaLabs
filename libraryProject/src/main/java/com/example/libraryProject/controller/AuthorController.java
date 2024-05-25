package com.example.libraryProject.controller;

import com.example.libraryProject.component.Navigation;
import com.example.libraryProject.functionalInterface.AuthorBookAdder;
import com.example.libraryProject.functionalInterface.AuthorBookRemover;
import com.example.libraryProject.functionalInterface.AuthorBookZeroer;
import com.example.libraryProject.model.Author;
import com.example.libraryProject.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static java.util.Objects.isNull;

@Controller
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private Navigation navigation;

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("navigationElements", navigation.getNavigation());
    }

    //Tydzien 10, przetwarzanie strumieniowe
    @GetMapping("/authors")
    public String showAddForm(Model model) {
        List<Author> authors = authorService.findAllAuthors();
        List<Author> authorsSorted = authors.stream().sorted((a1, a2) -> a1.getName().compareTo(a2.getName())).toList();
        model.addAttribute("author", new Author.Builder().build());
        model.addAttribute("authors", authors);
        model.addAttribute("authorsSorted", authorsSorted);
        return "authors";
    }
    //Tydzien 10, koniec

    @GetMapping("/authors/add")
    public String addAuthor(Model model, @ModelAttribute("author") Author author) {
        authorService.saveAuthor(author);
        System.out.println(model.getAttribute("amount"));
        if(isNull(model.getAttribute("amount"))){
            model.addAttribute("amount", 0);
        }
        return "add-author";
    }//do poprawienia, jak otwiera sie formularz z dodaniem to dodaje pusty rekord ao autorów

    //Tydzien 10, implementacje interfejsów funkcyjnych
    @GetMapping("/authors/addBook/{amount}")
    public String addAuthorBook(Model model, @ModelAttribute("author") Author author, @PathVariable int amount) {
        AuthorBookAdder a = (int x) -> x + 1;
        int newAmount = a.addBook(amount);
        model.addAttribute("amount", newAmount);
        return "add-author";
    }

    @GetMapping("/authors/removeBook/{amount}")
    public String removeAuthorBook(Model model, @ModelAttribute("author") Author author, @PathVariable int amount) {
        AuthorBookRemover r = (int x) -> x - 1;
        int newAmount = r.removeBook(amount);
        model.addAttribute("amount", newAmount);
        return "add-author";
    }

    @GetMapping("/authors/zeroBooks/{amount}")
    public String zeroAuthorBooks(Model model, @ModelAttribute("author") Author author, @PathVariable int amount) {
        AuthorBookZeroer r = () -> 0;
        int newAmount = r.zeroBooks();
        model.addAttribute("amount", newAmount);
        return "add-author";
    }
    //Tydzien 10, koniec

    @GetMapping("/authors/delete/{id}")
    public String deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return "redirect:/authors";
    }

}
