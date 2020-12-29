package com.rssreader.server.model;

import javax.persistence.*;

@Entity
@Table(name = "rsschannel")
public class RssChannel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String rssUrl;
    private String url;
    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public RssChannel(){

    }

    public RssChannel(String rssUrl,String url,String title, String description){

        this.rssUrl=rssUrl;
        this.url=url;
        this.title=title;
        this.description=description;
    }


    public Long getId() {
        return id;
    }

    public String getRssUrl() {
        return rssUrl;
    }

    public void setRssUrl(String rssUrl) {
        this.rssUrl = rssUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
