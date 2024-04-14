package com.example.libraryProject.observer;

import com.example.libraryProject.interfaces.LibraryLogObserver;
import org.springframework.stereotype.Component;

import java.util.List;

//Tydzien 6, obserwator, implementacja obserwatora
@Component
public class BookLog implements LibraryLogObserver {

    private List<String> logMessages;
    @Override
    public void update(Object o) {
        this.setLogMessages((List<String>) o);
    }

    public List<String> getLogMessages() {
        return logMessages;
    }

    public void setLogMessages(List<String> logMessages) {
        this.logMessages = logMessages;
    }
}
//Tydzien 6, obserwator, koniec