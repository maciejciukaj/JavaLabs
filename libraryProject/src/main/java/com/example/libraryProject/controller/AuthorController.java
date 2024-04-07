package com.example.libraryProject.controller;

import com.example.libraryProject.component.Navigation;
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

    @GetMapping("/authors")
    public String showAddForm(Model model) {
        List<Author> authors = authorService.findAllAuthors();
        model.addAttribute("author", new Author.Builder().build());
        model.addAttribute("authors", authors);
        return "authors";
    }

    @GetMapping("/authors/add")
    public String addAuthor(@ModelAttribute("author") Author author) {
        authorService.saveAuthor(author);
        return "add-author";
    }//do poprawienia, jak otwiera sie formularz z dodaniem to dodaje pusty rekord ao autorów

    @GetMapping("/authors/delete/{id}")
    public String deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return "redirect:/authors";
    }

}
