package com.example.libraryProject.decorator;

import com.example.libraryProject.interfaces.Publisher;

//Tydzien 3, Decorator, sklasa dekoratora
public class PublisherDecorator implements Publisher {

    protected Publisher publisher;

    public PublisherDecorator(Publisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public String getName() {
        return this.publisher.getName();
    }

    @Override
    public String getInfo() {
        return this.publisher.getInfo();
    }
}
//Tydzien 3, Decorator, koniec
