package com.rssreader.server.dto;

public class ItemDto {

    private Long id;
    private String title;
    private String link;
    private String description;
    private String imgLink;
    private String pubdate;
    boolean readed;
    boolean hide;
    boolean readlater;

    public ItemDto(Long id, String title, String link, String description, String imgLink, String pubdate, boolean readed, boolean hide, boolean readlater) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.description = description;
        this.imgLink = imgLink;
        this.pubdate = pubdate;
        this.readed = readed;
        this.hide = hide;
        this.readlater = readlater;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean isReaded() {
        return readed;
    }

    public void setReaded(boolean readed) {
        this.readed = readed;
    }

    public boolean isHide() {
        return hide;
    }

    public void setHide(boolean hide) {
        this.hide = hide;
    }

    public boolean isReadlater() {
        return readlater;
    }

    public void setReadlater(boolean readlater) {
        this.readlater = readlater;
    }
}
