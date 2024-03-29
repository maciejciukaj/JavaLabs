package com.example.libraryProject.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Book implements Cloneable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String genre;
    private boolean isAvailable;
    private int amount;
    private String coverPath;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "library_id")
    private Library library;

    //Tydzien 4 Proxy, użycie, leniwe ladowanie danych az nie zostana one w calosci pobrane z bazy danych
    //dodac zaladowane dane do pamieci cache zaby dostep do nich byl szybki, lub np ze nie mozna przejsc
    // do linku /wypozycz ksiazke po godzinie 16:00 wtedy bedzie ze biblioteka jest nieczynna
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;
    //Tydzien 4 Proxy, koniec

    @ManyToMany(mappedBy = "books")
    private Set<Reader> readers = new HashSet<>();


    private Book(Builder builder) {  // Konstruktor prywatny wymuszający użycie Buildera
        this.title = builder.title;
        this.genre = builder.genre;
        this.isAvailable = builder.isAvailable;
        this.amount = builder.amount;
        this.author = builder.author;
        this.coverPath = builder.coverPath;
    }

    protected Book() {
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    //tydzień 2, Builder, utworzona klasa statyczna builder ułatwiająca tworzenie obiektów klasy Book przykład Book newBook = new Book.Builder().setTitle("ABC").setGenre("Fantasy").....build();
    //tworzenie obiektów w taki sposób jest czytelniejsze w przypadku klas, które posiadają dużo pól
    public static class Builder {
        public String coverPath;
        private String title;
        private String genre;
        private boolean isAvailable;
        private int amount;
        private Author author;

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setGenre(String genre) {
            this.genre = genre;
            return this;
        }

        public Builder setIsAvailable(boolean isAvailable) {
            this.isAvailable = isAvailable;
            return this;
        }

        public Builder setAmount(int amount) {
            this.amount = amount;
            return this;
        }

        public Builder setAuthor(Author author) {
            this.author = author;
            return this;
        }


        public Book build() {
            return new Book(this);
        }
    }
    //tydzień 2, builder, koniec

    //Tydzień 2, prototyp, zastosowano tu wzorzec prototyp. Dodano funkcję clone(), której zadaniem będzie kopiowanie/duplikowanie
    //obiektu wywołującego metodę clone(), metoda ta jest dostępna do nadpisania po zaimplementowaniu interfejsu Cloneable
    //rozwiązanie to przydatne jest gdy chcemy sklonować obiekt nieznacznie różniący się od oryginału
    //przydatne także w przypadku tworzenia dużej ilości identycznych obiektów
    @Override
    public Book clone() {
        try {
            Book cloned = (Book) super.clone();
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Book cloning not supported", e);
        }
    }
    //Tydzień 2, prototyp, koniec

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Set<Reader> getReaders() {
        return readers;
    }

    public void setReaders(Set<Reader> readers) {
        this.readers = readers;
    }

    public String getCoverPath() {
        return coverPath;
    }

    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath;
    }
}
