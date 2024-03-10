package com.example.libraryProject.service;

import com.example.libraryProject.model.Author;
import com.example.libraryProject.model.Book;
import com.example.libraryProject.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//Tydzien 2, singleton, wzorzec singleton jest stosowany domyślnie przez kontener Springa do zarządzania beanami.
// @Service, @Controller i inne adnotacje stereotypowe Springa ( takie jak @Repository, @Component, itp.) są beanem singletonem
@Service
public class AuthorService {
    //Tydzień 2, singleton, koniec
    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> findAllAuthors() {
        return (List<Author>) authorRepository.findAll();
    }

    public Optional<Author> findAuthorById(Long id) {
        return authorRepository.findById(id);
    }
    public void saveAuthor(Author author) {
        authorRepository.save(author);
    }
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}
