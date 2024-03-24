package com.example.libraryProject.compositeNavigation;

import com.example.libraryProject.interfaces.NavigationElement;
import org.springframework.stereotype.Component;


//Tydzien 3, Composite, liść kompozytu
@Component
public class NavigationLeafElement  implements NavigationElement {

    private String linkText = "----->";
    private String link;
    private String text;
    private boolean isExpanded = true;

    public NavigationLeafElement(){}

    public NavigationLeafElement(String link, String text) {
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
    }
}
//Tydzien 3, Composite, koniec
