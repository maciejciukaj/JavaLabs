package com.example.libraryProject.component;

import com.example.libraryProject.interfaces.Publisher;
import com.example.libraryProject.publisher.FamousPublisher;
import com.example.libraryProject.publisher.PlainPublisher;
import com.example.libraryProject.publisher.PolishPublisher;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//Tydzien 3, Decorator, użycie
@Component
public class PublishersList {

    public List<Publisher> getPublishersList(){

        List<Publisher> publishers = new ArrayList<>();

        publishers.add(new PlainPublisher("Hachette Books"));
        publishers.add(new PlainPublisher("Baen"));
        publishers.add(new FamousPublisher(new PlainPublisher("Cambridge University Press")));
        publishers.add(new PolishPublisher(new PlainPublisher("Znak")));
        publishers.add(new FamousPublisher(new PolishPublisher(new PlainPublisher("PWN"))));

        return publishers;
    }
}
//Tydzien 3, Decorator, koniec
