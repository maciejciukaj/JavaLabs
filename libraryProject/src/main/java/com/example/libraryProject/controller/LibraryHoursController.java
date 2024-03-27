package com.example.libraryProject.controller;

import com.example.libraryProject.interfaces.LibraryAccess;
import com.example.libraryProject.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//Tydzien 4 Proxy, użycie
@Controller
@RequestMapping("/")
public class LibraryHoursController {

    private final LibraryService libraryService;

    @Autowired
    public LibraryHoursController( LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("/accessLibrary")
    @ResponseBody
    public ResponseEntity<String> accessLibrary() {
        boolean isOpen = libraryService.isLibraryOpen();
        if (isOpen) {
            System.out.println("Biblioteka OTWARTA");
            return ResponseEntity.ok("OPEN");
        } else {
            System.out.println("Biblioteka ZAMKNIĘTA");
            return ResponseEntity.ok("CLOSED");
        }
    }

}

//Tydzien 4 Proxy, koniec

