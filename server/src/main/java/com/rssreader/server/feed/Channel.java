package com.rssreader.server.feed;

import java.util.ArrayList;
import java.util.List;

public class Channel implements Feed {

    private String title;
    private String link;
    private String description;

    private List<Item> items =null;


    public Channel(String title,String link,String description,List<Item> items){
        this.title=title;
        this.link=link;
        this.description=description;
        this.items=new ArrayList<>(items);
    }

    public Channel(String title,String link,String description){
        this.title=title;
        this.link=link;
        this.description=description;
    }



    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title=title;
    }

    @Override
    public String getLink() {
        return link;
    }

    @Override
    public void setLink(String link) {
        this.link=link;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description=description;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items){
        this.items=items;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", description='" + description + '\'' +
                ", items=" + items +
                '}';
    }
}
