package com.example.libraryProject.model;

import jakarta.persistence.*;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalTime openingTime;
    private LocalTime closingTime;

    @OneToMany(mappedBy = "library", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Book> books = new HashSet<>();

    protected Library() {
    }

    private Library(Builder builder) {
        this.openingTime = builder.openingTime;
        this.closingTime = builder.closingTime;
        this.books = builder.books;
    }

    public void addBook(Book book) {
        books.add(book);
        book.setLibrary(this);
    }

    public void removeBook(Book book) {
        books.remove(book);
        book.setLibrary(null);
    }

    public static class Builder {
        private LocalTime openingTime;
        private LocalTime closingTime;
        private Set<Book> books = new HashSet<>();

        public Builder setOpeningTime(LocalTime openingTime) {
            this.openingTime = openingTime;
            return this;
        }

        public Builder setClosingTime(LocalTime closingTime) {
            this.closingTime = closingTime;
            return this;
        }

        public Builder setBooks(Set<Book> books) {
            this.books = books;
            return this;
        }

        public Library build() {
            return new Library(this);
        }
    }

    // Gettery i settery

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(LocalTime openingTime) {
        this.openingTime = openingTime;
    }

    public LocalTime getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(LocalTime closingTime) {
        this.closingTime = closingTime;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
