package com.example.libraryProject.component;

import com.example.libraryProject.compositeNavigation.NavigationCompositeElement;
import com.example.libraryProject.interfaces.NavigationElement;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//Tydzien 3, Composite, implementacja nawigacji
@Component
public class Navigation {

    public List<NavigationElement> getNavigation() {

        List<NavigationElement> navigationElementsList = new ArrayList<>();

        NavigationCompositeElement mainElement = new NavigationCompositeElement("/", "Stona Główna");
        navigationElementsList.add(mainElement);

        NavigationCompositeElement bookListElement = new NavigationCompositeElement("/books", "Lista dostępnych książek (orginalne tytuły)");
        mainElement.add(bookListElement);
        navigationElementsList.add(bookListElement);

        NavigationCompositeElement bookListTranslatedElement = new NavigationCompositeElement("/books/translated", "Lista dostępnych książek (przetłumaczone tytuły)");
        bookListElement.add(bookListTranslatedElement);
        navigationElementsList.add(bookListTranslatedElement);

        NavigationCompositeElement authorsListElement = new NavigationCompositeElement("/authors", "Lista autorów");
        mainElement.add(authorsListElement);
        navigationElementsList.add(authorsListElement);

        NavigationCompositeElement PublishersListElement = new NavigationCompositeElement("/publishers", "Lista wydawców");
        mainElement.add(PublishersListElement);
        navigationElementsList.add(PublishersListElement);

        NavigationCompositeElement BookAddElement = new NavigationCompositeElement("/books/add", "Dodanie książki");
        mainElement.add(BookAddElement);
        navigationElementsList.add(BookAddElement);

        NavigationCompositeElement AuthorAddElement = new NavigationCompositeElement("/authors/add", "Dodanie autora");
        mainElement.add(AuthorAddElement);
        navigationElementsList.add(AuthorAddElement);

        NavigationCompositeElement LoginElement = new NavigationCompositeElement("/login", "Logowanie");
        mainElement.add(LoginElement);
        navigationElementsList.add(LoginElement);

        return navigationElementsList;
    }
}
//Tydzien 3, Composite, koniec
