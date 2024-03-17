package com.example.libraryProject.publisher;

import com.example.libraryProject.decorator.PublisherDecorator;
import com.example.libraryProject.interfaces.Publisher;

//Tydzien 3, Decorator, dekorator rozszerzający
public class FamousPublisher extends PublisherDecorator {

    public FamousPublisher(Publisher publisher) {
        super(publisher);
    }

    @Override
    public String getInfo() {
        return "znany " + super.getInfo();
    }
}
//Tydzien 3, Decorator, koniec
