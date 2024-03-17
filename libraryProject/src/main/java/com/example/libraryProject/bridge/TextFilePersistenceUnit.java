package com.example.libraryProject.bridge;

import com.example.libraryProject.interfaces.BookPersistence;
import com.example.libraryProject.model.Book;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//Tydzien 3, Bridge, implementator pliku tekstowego
@Component
public class TextFilePersistenceUnit implements BookPersistence {
    @Override
    public void saveObject(Book book) {
        File bookSaveFile = new File(book.getId() + ".txt");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(book.getId() + ".txt"));
            writer.write("Title: " + book.getTitle() + "\n");
            writer.write("Genre: " + book.getGenre() + "\n");
            writer.write("Author: " + book.getAuthor().getName() + "\n");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
//Tydzien 3, Bridge, koniec
