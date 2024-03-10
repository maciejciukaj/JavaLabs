package com.example.libraryProject.repository;

import com.example.libraryProject.model.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//Tydzien 2, singleton, wzorzec singleton jest stosowany domyślnie przez kontener Springa do zarządzania beanami.
// @Service, @Controller i inne adnotacje stereotypowe Springa ( takie jak @Repository, @Component, itp.) są beanem singletonem
@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
    //Tydzień 2, singleton, koniec
}
