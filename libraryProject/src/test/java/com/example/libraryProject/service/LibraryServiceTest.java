package com.example.libraryProject.service;

import com.example.libraryProject.model.Library;
import com.example.libraryProject.repository.LibraryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//Tydzien 14 Testy początek
@ExtendWith(MockitoExtension.class)
class LibraryServiceTest {

    @Mock
    private LibraryRepository libraryRepository;

    @InjectMocks
    private LibraryService libraryService;

    private Library library;

    @BeforeEach
    void setUp() {
        library = new Library();
        library.setId(1L);
        library.setOpeningTime(LocalTime.of(9, 0)); // Biblioteka otwiera się o 9:00
        library.setClosingTime(LocalTime.of(17, 0)); // Biblioteka zamyka się o 17:00
    }

    @Test
    void testFindAllLibraries() {
        libraryService.findAllLibraries();
        verify(libraryRepository, times(1)).findAll();
    }

    @Test
    void testFindLibraryById() {
        when(libraryRepository.findById(1L)).thenReturn(Optional.of(library));
        Optional<Library> foundLibrary = libraryService.findLibraryById(1L);
        assertTrue(foundLibrary.isPresent());
        assertEquals(library, foundLibrary.get());
        verify(libraryRepository, times(1)).findById(1L);
    }

    @Test
    void testSaveLibrary() {
        when(libraryRepository.save(library)).thenReturn(library);
        Library savedLibrary = libraryService.saveLibrary(library);
        assertEquals(library, savedLibrary);
        verify(libraryRepository, times(1)).save(library);
    }

    @Test
    void testDeleteLibrary() {
        libraryService.deleteLibrary(1L);
        verify(libraryRepository, times(1)).deleteById(1L);
    }



    @Test
    void testIsLibraryOpenWhenLibraryNotFound() {
        when(libraryRepository.findById(1L)).thenReturn(Optional.empty());
        assertFalse(libraryService.isLibraryOpen());
        verify(libraryRepository, times(1)).findById(1L);
    }
    //Tydzien 14 Testy koniec
}
