package com.example.libraryProject.interfaces;

import com.example.libraryProject.model.Library;

import java.util.List;
import java.util.Optional;

public interface LibraryAccess {
    List<Library> findAllLibraries();
    Optional<Library> findLibraryById(Long id);
    Library saveLibrary(Library library);
    void deleteLibrary(Long id);
    boolean isLibraryOpen();
}
