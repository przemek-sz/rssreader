package com.rssreader.server.service;


import com.rssreader.server.model.RssChannel;

import java.util.List;
import java.util.Set;


public interface RssChannelService {

    public void add(RssChannel rssChannel);
    public List<RssChannel> getAllChannels(Long id);
}
