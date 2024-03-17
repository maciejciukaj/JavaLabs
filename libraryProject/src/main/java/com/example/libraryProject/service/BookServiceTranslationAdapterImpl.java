package com.example.libraryProject.service;

import com.example.libraryProject.interfaces.BookTranslationAdapter;
import com.example.libraryProject.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

//Tydzien 3, Adapter, implementacja adaptera tłumaczenia nazw książek
@Service
public class BookServiceTranslationAdapterImpl extends BookService implements BookTranslationAdapter {

    @Override
    public List<Book> findAllBooks() {
        return super.findAllBooks();
    }

    @Override
    public List<Book> findAllBooksAndTranslateTitles() {
        List<Book> books = findAllBooks();
        return translateBookTitles(books);
    }

    private List<Book> translateBookTitles(List<Book> books) {
        for(Book book: books){
            String translatedTitle = switch (book.getTitle()) {
                case "Harry Potter and the Sorcerer's Stone" -> "Harry Potter i Kamień Filozoficzny";
                case "A Game of Thrones" -> "Gra o Tron";
                case "Foundation" -> "Fundacja";
                case "I, Robot" -> "Ja, Robot";
                case "It" -> "To";
                case "The Shining" -> "Lśnienie";
                case "The Handmaid's Tale" -> "Opowieść podręcznej";
                case "Oryx and Crake" -> "Oryks i Derkacz";
                case "The War of the Worlds" -> "Wojna Światów";
                case "The Time Machine" -> "Wehikuł Czasu";
                case "The Lord of the Rings" -> "Władca Pierścieni";
                case "The Hobbit" -> "Hobbit";
                default -> book.getTitle();
            };
            book.setTitle(translatedTitle);
        }
        return books;
    }
}
//Tydzien 3, Adapter, koniec implementacji adaptera
