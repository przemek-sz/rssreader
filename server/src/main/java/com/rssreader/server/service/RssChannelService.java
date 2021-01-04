package com.rssreader.server.service;


import com.rssreader.server.model.RssChannel;
import com.rssreader.server.model.User;

import java.util.List;
import java.util.Set;


public interface RssChannelService {

    public void follow(RssChannel rssChannel,String username);

    public void addNewChannel(RssChannel rssChannel);

    void update(RssChannel rssChannel);

    public List<RssChannel> getAllChannelsByUser(User user);

    void delete(RssChannel rssChannel);

    public RssChannel getChannel(String url);
}
