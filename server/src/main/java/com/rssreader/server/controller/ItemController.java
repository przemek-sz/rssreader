package com.rssreader.server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rssreader.server.config.security.JwtTokenUtil;
import com.rssreader.server.dto.RssChannelDto;
import com.rssreader.server.feed.Channel;
import com.rssreader.server.feed.Feed;
import com.rssreader.server.feed.Item;
import com.rssreader.server.model.RssChannel;
import com.rssreader.server.service.FeedProcess;
import com.rssreader.server.service.RssChannelService;
import com.rssreader.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/item")
public class ItemController {

    @Autowired
    UserService userService;
    @Autowired
    RssChannelService rssChannelService;
    @Autowired
    FeedProcess feedProcess;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @GetMapping
    public List<Channel> getAll(@RequestHeader("Authorization") String token){
        List<Channel> feeds = new ArrayList<>();
        for(RssChannel rssChannel:rssChannelService.getAllChannels(userService.getByUsername(jwtTokenUtil.getUsernameFromToken(token.split(" ")[1])).getId())){
            feeds.add(feedProcess.feedFactory(feedProcess.getXmlFeed(rssChannel.getRssUrl())));
        }

        for (Channel f:feeds) {
            for(Item i: f.getItems()){
                System.out.println(i.getImgLink());
            }
        }
        return feeds;
    }

    @PostMapping
    public List<Feed> getByChannel(@RequestBody String channel){
        List<Feed> feeds = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        RssChannelDto chann =null;
        try {
            chann=mapper.readValue(channel, RssChannelDto.class);
        }catch (IOException e){
            e.printStackTrace();
        }
        feeds.add(feedProcess.feedFactory(feedProcess.getXmlFeed(chann.getRssUrl())));

        return feeds;
    }
}
