package com.example.libraryProject.publisher;

import com.example.libraryProject.decorator.PublisherDecorator;
import com.example.libraryProject.interfaces.Publisher;

//Tydzien 3, Decorator, dekorator rozszerzający
public class PolishPublisher extends PublisherDecorator {

    public PolishPublisher(Publisher publisher) {
        super(publisher);
    }

    @Override
    public String getInfo() {
        return "polski " + super.getInfo();
    }
}
//Tydzien 3, Decorator, koniec
