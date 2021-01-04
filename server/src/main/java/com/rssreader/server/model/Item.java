package com.rssreader.server.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String link;
    @Type(type="text")
    private String description;
    private String imgLink;
    private String pubdate;

    @ManyToOne
    @JoinColumn(name = "rsschannel_id", referencedColumnName = "id")
    private RssChannel rssChannel;

    @OneToMany(mappedBy = "item")
    private Set<UserItem> useritemSet = new HashSet<>();


    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public RssChannel getRssChannel() {
        return rssChannel;
    }

    public void setRssChannel(RssChannel rssChannel) {
        this.rssChannel = rssChannel;
    }

    public Set<UserItem> getUseritemSet() {
        return useritemSet;
    }

    public void setUseritemSet(Set<UserItem> useritemSet) {
        this.useritemSet = useritemSet;
    }
}
