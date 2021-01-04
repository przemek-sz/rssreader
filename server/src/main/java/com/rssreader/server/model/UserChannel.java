package com.rssreader.server.model;

import javax.persistence.*;

@Entity
@IdClass(UserChannelID.class)
public class UserChannel {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "rsschannel_id", referencedColumnName = "id")
    private RssChannel rsschannel;

    boolean favorite;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public RssChannel getRsschannel() {
        return rsschannel;
    }

    public void setRsschannel(RssChannel rsschannel) {
        this.rsschannel = rsschannel;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}
