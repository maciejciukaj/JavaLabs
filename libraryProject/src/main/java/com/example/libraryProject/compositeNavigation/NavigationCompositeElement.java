package com.example.libraryProject.compositeNavigation;

import com.example.libraryProject.interfaces.NavigationElement;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//Tydzien 3, Composite, kompozyt implementujący interfejs do nawigacji
@Component
public class NavigationCompositeElement implements NavigationElement {

    private String linkText = "->";
    private String link;
    private String text;
    private boolean isExpanded = true;
    private List<NavigationElement> childElementsList = new ArrayList<>();

    public NavigationCompositeElement(){}

    public NavigationCompositeElement(String link, String text) {
        this.link = link;
        this.text = text;
    }

    public String getLinkText() {
        return linkText;
    }

    public void setLinkText(String linkText) {
        this.linkText = linkText;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void expandOrCollapse() {
        if(this.isExpanded){
            this.linkText = "";
            this.isExpanded = false;
        }else{
            this.linkText = "->";
            this.isExpanded = true;
        }
        for(NavigationElement childElement: childElementsList){
            childElement.expandOrCollapse();
        }
    }

    public void add(NavigationElement navigationElement){
        this.childElementsList.add(navigationElement);
    }

    public void remove(NavigationElement navigationElement){
        this.childElementsList.remove(navigationElement);
    }

    public void clear(){
        this.childElementsList.clear();
    }
}
//Tydzien 3, Composite, koniec
