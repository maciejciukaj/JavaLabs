package com.example.libraryProject.component;

import com.example.libraryProject.interfaces.Command;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SearchHistory {
    private List<Command> history = new ArrayList<>();

    public void addCommand(Command command) {
        command.execute();
        history.add(command);
    }

    public List<Command> getHistory() {
        return history;
    }
}
