package com.example.libraryProject.service;

import com.example.libraryProject.interfaces.LibraryAccess;
import com.example.libraryProject.model.Library;
import com.example.libraryProject.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
//Tydzien 7 Pojedyncza odpowiedzialnosc
//Tydzien 4 Proxy, użycie
@Service
public class LibraryService implements LibraryAccess {

    private final LibraryRepository libraryRepository;

    @Autowired
    public LibraryService(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    public List<Library> findAllLibraries() {
        return libraryRepository.findAll();
    }

    public Optional<Library> findLibraryById(Long id) {
        return libraryRepository.findById(id);
    }

    public Library saveLibrary(Library library) {
        return libraryRepository.save(library);
    }

    public void deleteLibrary(Long id) {
        libraryRepository.deleteById(id);
    }

    public boolean isLibraryOpen() {
        Optional<Library> libraryOptional = libraryRepository.findById(1L);
        if (libraryOptional.isPresent()) {
            Library library = libraryOptional.get();
            LocalTime now = LocalTime.now();
            return !now.isBefore(library.getOpeningTime()) && !now.isAfter(library.getClosingTime());
        }
        return false;
    }
}
//Tydzien 4 Proxy, koniec
