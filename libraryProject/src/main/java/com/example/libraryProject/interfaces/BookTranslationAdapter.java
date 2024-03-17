package com.example.libraryProject.interfaces;

import com.example.libraryProject.model.Book;

import java.util.List;

//Tydzien 3, Adapter, stworzenie interfejsu umożliwiającego tłumaczenie tytułów książek
public interface BookTranslationAdapter {
    public List<Book> findAllBooksAndTranslateTitles();
}

//Tydzien 3, Adapter, koniec

