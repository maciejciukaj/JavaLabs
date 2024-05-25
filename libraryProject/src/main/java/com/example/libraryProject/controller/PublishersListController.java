package com.example.libraryProject.controller;

import com.example.libraryProject.component.Navigation;
import com.example.libraryProject.component.PublishersList;
import com.example.libraryProject.interfaces.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

//Tydzien 3, Decorator, kontroler
@Controller
public class PublishersListController {

    @Autowired
    private PublishersList publishersList;

    @Autowired
    private Navigation navigation;

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("navigationElements", navigation.getNavigation());
    }

    @GetMapping("/publishers")
    public String generatePublishersList(Model model) {
        List<Publisher> publishers = publishersList.getPublishersList();
        model.addAttribute("publishers", publishers);
        return "publishers";
    }

    //Tydzien 10, przetwarzanie strumieniowe
    @GetMapping("/publishers/polish")
    public String generatePolishPublishersList(Model model) {
        List<Publisher> publishers = publishersList.getPublishersList().stream()
                .filter(p -> p.getInfo().toLowerCase().contains("polski"))
                .toList();
        model.addAttribute("publishers", publishers);
        return "publishers";
    }
    //Tydzien 10, koniec
}
//Tydzien 3, Decorator, koniec
