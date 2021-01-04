package com.rssreader.server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rssreader.server.config.security.JwtTokenUtil;
import com.rssreader.server.dto.ItemDto;
import com.rssreader.server.dto.RssChannelDto;
import com.rssreader.server.feed.Channel;
import com.rssreader.server.feed.Feed;
import com.rssreader.server.model.Item;
import com.rssreader.server.model.RssChannel;
import com.rssreader.server.service.FeedProcess;
import com.rssreader.server.service.ItemService;
import com.rssreader.server.service.RssChannelService;
import com.rssreader.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    ItemService itemService;


    @GetMapping
    public List<ItemDto> getAllItems(@RequestHeader("Authorization") String token){
        List<ItemDto> items=itemService.getItemsByUser(userService.getByUsername(jwtTokenUtil.getUsernameFromToken(token.split(" ")[1])));
        return items;
    }

    @PostMapping
    public List<ItemDto> getByChannel(@RequestBody String channel,@RequestHeader("Authorization") String token){

        ObjectMapper mapper = new ObjectMapper();
        RssChannelDto chann =null;
        try {
            chann=mapper.readValue(channel, RssChannelDto.class);
        }catch (IOException e){
            e.printStackTrace();
        }

        RssChannel rssChannel = rssChannelService.getChannel(chann.getRssUrl());
        List<ItemDto> items=itemService.getItemsByChannel(rssChannel,userService.getByUsername(jwtTokenUtil.getUsernameFromToken(token.split(" ")[1])));

        return items;
    }
}
