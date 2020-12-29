package com.rssreader.server.service;


import com.rssreader.server.model.RssChannel;
import com.rssreader.server.repository.RssChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RssChannelServiceImpl implements RssChannelService{

    @Autowired
    RssChannelRepository rssChannelRepository;

    @Override
    public void add(RssChannel rssChannel) {
        rssChannelRepository.save(rssChannel);
    }

    @Override
    public List<RssChannel> getAllChannels(Long id) {
        return rssChannelRepository.getByUserId(id);
    }


}
