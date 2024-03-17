package com.example.libraryProject.publisher;

import com.example.libraryProject.interfaces.Publisher;
import org.springframework.stereotype.Component;

//Tydzien 3, Decorator, implementacja komponentu wydawcy
@Component
public class PlainPublisher implements Publisher {

    public PlainPublisher() {
    }

    public PlainPublisher(String name) {
        this.name = name;
        this.info = getInfo();
    }

    public String name;
    public String info;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String getInfo() {
        return "wydawca";
    }
}
//Tydzien 3, Decorator, koniec
