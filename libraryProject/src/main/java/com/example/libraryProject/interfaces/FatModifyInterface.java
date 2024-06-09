package com.example.libraryProject.interfaces;

import com.example.libraryProject.model.Book;
import com.example.libraryProject.model.Library;
//Tydzien 8, segregacja interfejsow, interfejs dla Book oraz Library, zawierający wiele metod używanych
// do modyfikacji obiektów w bilbiotece
//interfejs zostal podzielony na kilka mniejszych - BookPersistance, LibraryAccess, LibraryLogObserver
public interface FatModifyInterface {
    void saveObject(Book book);
    public void orderBook(Book book);
    void setLibraryStatus(boolean status);
    void update(Object o);
    Library saveLibrary(Library library);
    void deleteLibrary(Long id);
    void deleteBook(Long id);
}
//Tydzien 8, segregacja interfejsow, koniec