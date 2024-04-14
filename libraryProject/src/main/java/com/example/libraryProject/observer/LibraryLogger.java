package com.example.libraryProject.observer;

import com.example.libraryProject.interfaces.LibraryLogObserver;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//Tydzien 6, obserwator, klasa observable
@Component
public class LibraryLogger {
    private List<String> logMessages = new ArrayList<>();
    private List<LibraryLogObserver> logs = new ArrayList<>();

    public void addLog(LibraryLogObserver log){
        this.logs.add(log);
    }

    public void setLogMessage(String message){
        this.logMessages.add(message);
        for (LibraryLogObserver log : this.logs) {
            log.update(logMessages);
        }
    }
}
//Tydzien 6, obserwator, koniec
