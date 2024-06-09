package com.example.libraryProject.model;

import com.example.libraryProject.interfaces.BookState;
import com.example.libraryProject.state.AvailableState;
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
    private String status = "Dostępna";

    @Transient
    private BookState state = new AvailableState();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "library_id")
    private Library library;
    //tydzień 5, memento
    public BookMemento save() {
        return new BookMemento(title, genre, isAvailable, amount);
    }

    public void restore(BookMemento memento) {
        this.title = memento.getTitle();
        this.genre = memento.getGenre();
        this.isAvailable = memento.isAvailable();
        this.amount = memento.getAmount();
    }
    //tydzień 5, memento, koniec
    //Tydzien 4 Proxy, użycie, leniwe ladowanie danych az nie zostana one w calosci pobrane z bazy danych
    //dodac zaladowane dane do pamieci cache zaby dostep do nich byl szybki, lub np ze nie mozna przejsc
    // do linku /wypozycz ksiazke po godzinie 16:00 wtedy bedzie ze biblioteka jest nieczynna
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;
    //Tydzien 4 Proxy, koniec

    @ManyToMany(mappedBy = "books")
    private Set<Reader> readers = new HashSet<>();

    //Tydzien 6, State, użycie
    public void previousState() {
        state.prev(this);
        this.status = state.printStatus();
    }
    public void nextState() {
        state.next(this);
        this.status = state.printStatus();
    }
    public void printStatus() {
        state.printStatus();
    }
    public BookState getState() {
        return state;
    }
    public void setState(BookState state) {
        this.state = state;
    }
    //Tydzien 6, State, koniec

    private Book(Builder builder) {  // Konstruktor prywatny wymuszający użycie Buildera
        this.title = builder.title;
        this.genre = builder.genre;
        this.isAvailable = builder.isAvailable;
        this.amount = builder.amount;
        this.author = builder.author;
        this.coverPath = builder.coverPath;
        this.status = builder.status;
    }

    public Book() {
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
        private String status = "Dostępna";


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

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public String getCoverPath() {
            return coverPath;
        }

        public void setCoverPath(String coverPath) {
            this.coverPath = coverPath;
        }

        public String getTitle() {
            return title;
        }

        public String getGenre() {
            return genre;
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

        public Author getAuthor() {
            return author;
        }

        public String getStatus() {
            return status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
