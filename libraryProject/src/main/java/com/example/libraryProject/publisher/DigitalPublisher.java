package com.example.libraryProject.publisher;

import com.example.libraryProject.decorator.PublisherDecorator;
import com.example.libraryProject.interfaces.Publisher;
//Tydzine 8, liskov, bazowa - PublisherDecorator, pochodna DigitalPublisherm. DigitalPublisher jest dekoratorem, który rozszerza dowolny Publisher, dodając do publikacji informację o wersji cyfrowej. Dzięki wykorzystaniu wzorca dekoratora,
// DigitalPublisher nadal przestrzega kontraktu Publisher i rozszerza funkcjonalność bez zmiany zachowania bazowych komponentów
public class DigitalPublisher extends PublisherDecorator {
    public DigitalPublisher(Publisher publisher) {
        super(publisher);
    }

    @Override
    public String getInfo() {
        return  super.getInfo() + " cyfrowy";
    }
}
//tydzien 8, liskov, koniec