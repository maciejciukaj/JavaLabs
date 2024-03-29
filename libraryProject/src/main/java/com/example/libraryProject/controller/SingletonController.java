package com.example.libraryProject.controller;
import com.example.libraryProject.component.Navigation;
import com.example.libraryProject.singleton.SimpleSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

//Tydzien 2, Singleton, uzycie, kontroler do wyswietlenia singletona
@Controller
public class SingletonController {

    @Autowired
    private Navigation navigation;

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("navigationElements", navigation.getNavigation());
    }

    @GetMapping("/singleton")
    public String singletonInfo(Model model) {
        SimpleSingleton singleton = SimpleSingleton.getInstance();
        model.addAttribute("info", singleton.getInfo());
        return "singleton";
    }
}
//Tydzien 2, singleton,koniec