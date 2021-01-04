package com.rssreader.server.service;


import com.rssreader.server.model.RssChannel;
import com.rssreader.server.model.User;
import com.rssreader.server.model.UserChannel;
import com.rssreader.server.repository.RssChannelRepository;
import com.rssreader.server.repository.UserChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RssChannelServiceImpl implements RssChannelService{

    RssChannelRepository rssChannelRepository;
    @Autowired
    UserService userService;
    ReadUrl readUrl;
    @Autowired
    UserChannelRepository userChannelRepository;
    @Autowired
    ItemService itemService;


    @Autowired
    public RssChannelServiceImpl(RssChannelRepository rssChannelRepository, ReadUrl readUrl) {
        this.rssChannelRepository = rssChannelRepository;
        this.readUrl = readUrl;
    }

    @Override
    public void follow(RssChannel rssChannel, String username) {
        User user = userService.getByUsername(username);
        RssChannel channelInDatabase=rssChannelRepository.getByRssUrl(rssChannel.getRssUrl());
        if(channelInDatabase==null){
            this.addNewChannel(rssChannel);
            channelInDatabase=rssChannelRepository.getByRssUrl(rssChannel.getRssUrl());
        }



        UserChannel userChannel = new UserChannel();
        userChannel.setRsschannel(channelInDatabase);
        userChannel.setUser(user);
        userChannelRepository.save(userChannel);
        itemService.assignItemsToUser(user,channelInDatabase);
    }

    @Override
    public void addNewChannel(RssChannel rssChannel) {
        rssChannelRepository.save(rssChannel);
    }


    @Override
    public void update(RssChannel rssChannel) {
        rssChannelRepository.save(rssChannel);
    }


    @Override
    public List<RssChannel> getAllChannelsByUser(User user) {

        List<RssChannel> rssChannels = new ArrayList<>();
        user.getChannelSet().forEach(userChannel -> {
            rssChannels.add(userChannel.getRsschannel());
        });
        return rssChannels;
    }

    @Override
    public void delete(RssChannel rssChannel) {
        rssChannelRepository.delete(rssChannel);
    }


    public RssChannel getChannel(String url){
        return rssChannelRepository.getByRssUrl(url);
    }


}
