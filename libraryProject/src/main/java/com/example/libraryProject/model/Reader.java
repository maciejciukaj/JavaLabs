package com.example.libraryProject.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long cardNumber;
    private String firstName;
    private String lastName;

    @ManyToMany
    @JoinTable(
            name = "reader_books",
            joinColumns = @JoinColumn(name = "reader_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private Set<Book> books = new HashSet<>();

    protected Reader() {
    }

    private Reader(Builder builder) {
        this.cardNumber = builder.cardNumber;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.books = builder.books;
    }

    public void borrowBook(Book book) {
        books.add(book);
    }

    public void returnBook(Book book) {
        books.remove(book);
    }

    //tydzień 2, Builder, utworzona klasa statyczna builder ułatwiająca tworzenie obiektów klasy Book przykład Book newBook = new Book.Builder().setTitle("ABC").setGenre("Fantasy").....build();
    //tworzenie obiektów w taki sposób jest czytelniejsze w przypadku klas, których obiekty posiadają dużo pól
    public static class Builder {
        private Long cardNumber;
        private String firstName;
        private String lastName;
        private Set<Book> books = new HashSet<>();

        public Builder setCardNumber(Long cardNumber) {
            this.cardNumber = cardNumber;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setBooks(Set<Book> books) {
            this.books = books;
            return this;
        }

        public Reader build() {
            return new Reader(this);
        }
    }
    //tydzień 2, builder, koniec

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
