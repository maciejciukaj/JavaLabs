package com.example.libraryProject.interfaces;

import com.example.libraryProject.model.Library;

import java.util.List;
import java.util.Optional;
//Tydzien 8, segregacja interfejsow, interfejs dla Library, zawierający wiele metod używanych dla róznych funkcjonalnosci zwiazanych z biblioteką
//interfejs zostal podzielony na kilka mniejszych - LibraryAccess, LibraryLogObserver
public interface FatLibraryInterface {
    List<Library> findAllLibraries();
    Optional<Library> findLibraryById(Long id);
    Library saveLibrary(Library library);
    void deleteLibrary(Long id);
    boolean isLibraryOpen();
    void update(Object o);
    void setLibraryStatus(boolean status);
    void exportLibraryData();
}
//Tydzien 8, segregacja interfejsow, koniec