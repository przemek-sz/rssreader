package com.rssreader.server.dto;

public class RssChannelDto {

    private String rssUrl;
    private String url;
    private String title;
    private String description;

    public RssChannelDto(){

    }

    public RssChannelDto(String rssUrl, String url, String title, String description) {
        this.rssUrl = rssUrl;
        this.url = url;
        this.title = title;
        this.description = description;
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

    @Override
    public String toString() {
        return "RssChannelDto{" +
                "rssUrl='" + rssUrl + '\'' +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
