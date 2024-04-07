package com.example.libraryProject.model;
//tydzień 5, memento
public class BookMemento {
    private String title;
    private String genre;
    private boolean isAvailable;
    private int amount;

    public BookMemento(String title, String genre, boolean isAvailable, int amount) {
        this.title = title;
        this.genre = genre;
        this.isAvailable = isAvailable;
        this.amount = amount;
    }

    // Gettery
    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public int getAmount() {
        return amount;
    }
}//tydzień 5, memento, koniec