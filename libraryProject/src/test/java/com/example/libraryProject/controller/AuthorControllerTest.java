package com.example.libraryProject.controller;

import com.example.libraryProject.component.Navigation;
import com.example.libraryProject.model.Author;
import com.example.libraryProject.service.AuthorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

//Tydzien 14 Testy początek
@ExtendWith(MockitoExtension.class)
@WebMvcTest(AuthorController.class)
class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorService authorService;

    @MockBean
    private Navigation navigation;

    private List<Author> authors;

    @BeforeEach
    void setUp() {
        authors = new ArrayList<>();
        authors.add(new Author.Builder().setName("Author1").build());
        authors.add(new Author.Builder().setName("Author2").build());
    }

    @Test
    void testShowAddForm() throws Exception {
        when(authorService.findAllAuthors()).thenReturn(authors);

        mockMvc.perform(get("/authors"))
                .andExpect(status().isOk())
                .andExpect(view().name("authors"))
                .andExpect(model().attributeExists("author"))
                .andExpect(model().attribute("authors", hasSize(2)))
                .andExpect(model().attribute("authorsSorted", hasSize(2)));

        verify(authorService, times(1)).findAllAuthors();
    }

    @Test
    void testAddAuthor() throws Exception {
        mockMvc.perform(post("/authors/add").flashAttr("author", new Author.Builder().build()))
                .andExpect(status().isOk())
                .andExpect(view().name("add-author"));

        verify(authorService, times(1)).saveAuthor(any(Author.class));
    }

    @Test
    void testAddAuthorBook() throws Exception {
        mockMvc.perform(get("/authors/addBook/1").flashAttr("author", new Author.Builder().build()))
                .andExpect(status().isOk())
                .andExpect(view().name("add-author"))
                .andExpect(model().attribute("amount", 2));
    }

    @Test
    void testRemoveAuthorBook() throws Exception {
        mockMvc.perform(get("/authors/removeBook/1").flashAttr("author", new Author.Builder().build()))
                .andExpect(status().isOk())
                .andExpect(view().name("add-author"))
                .andExpect(model().attribute("amount", 0));
    }

    @Test
    void testZeroAuthorBooks() throws Exception {
        mockMvc.perform(get("/authors/zeroBooks/1").flashAttr("author", new Author.Builder().build()))
                .andExpect(status().isOk())
                .andExpect(view().name("add-author"))
                .andExpect(model().attribute("amount", 0));
    }

    @Test
    void testDeleteAuthor() throws Exception {
        mockMvc.perform(get("/authors/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/authors"));

        verify(authorService, times(1)).deleteAuthor(1L);
    }
    //Tydzien 14 Testy koniec
}
