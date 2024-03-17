package com.example.libraryProject.bridge;

import com.example.libraryProject.interfaces.BookPersistence;
import com.example.libraryProject.model.Book;

//Tydzien 3, Bridge, implementacja mostu
public class BookSaveDataBridge extends SaveDataBridger{

    BookPersistence bookPersistence;

    public BookSaveDataBridge(BookPersistence bookPersistence) {
        this.bookPersistence = bookPersistence;
    }

    @Override
    public void saveData(Object object) {
        if(object instanceof Book){
            bookPersistence.saveObject((Book) object);
        }
    }
}
//Tydzien 3, Bridge, koniec
