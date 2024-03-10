package com.example.libraryProject.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Book> books = new HashSet<>();

    protected Author() {
    }

    private Author(Builder builder) {
        this.name = builder.name;
        this.books = builder.books;
    }

    public void addBook(Book book) {
        books.add(book);
        book.setAuthor(this);
    }

    public void removeBook(Book book) {
        books.remove(book);
        book.setAuthor(null);
    }

    //tydzień 1, Builder, utworzona klasa statyczna builder ułatwiająca tworzenie obiektów klasy Book przykład Book newBook = new Book.Builder().setTitle("ABC").setGenre("Fantasy").....build();
    //tworzenie obiektów w taki sposób jest czytelniejsze w przypadku klas, których obiekty posiadają dużo pól
    public static class Builder {
        private String name;
        private Set<Book> books = new HashSet<>();

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setBooks(Set<Book> books) {
            this.books = books;
            return this;
        }

        public Author build() {
            return new Author(this);
        }
    }
    //tydzień 2, builder, koniec

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
