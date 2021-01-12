package com.rssreader.server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rssreader.server.config.security.JwtTokenUtil;
import com.rssreader.server.dto.ItemDto;
import com.rssreader.server.dto.RssChannelDto;
import com.rssreader.server.feed.Channel;
import com.rssreader.server.feed.Feed;
import com.rssreader.server.model.Item;
import com.rssreader.server.model.RssChannel;
import com.rssreader.server.service.*;
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
    @Autowired
    UserItemService userItemService;


    @GetMapping
    public List<ItemDto> getAllItems(@RequestHeader("Authorization") String token){
        List<ItemDto> items=itemService.getItemsByUser(userService.getByUsername(jwtTokenUtil.getUsernameFromToken(token.split(" ")[1])));
        return items;
    }

    @GetMapping(value = "/readlater")
    public List<ItemDto> getReadLaterItems(@RequestHeader("Authorization") String token){
        List<ItemDto> items=itemService.getReadLaterItemsByUser(userService.getByUsername(jwtTokenUtil.getUsernameFromToken(token.split(" ")[1])));
        return items;
    }

    @GetMapping(value = "/favorite")
    public List<ItemDto> getFavoriteItems(@RequestHeader("Authorization") String token){
        List<ItemDto> items=itemService.getFavoriteItemsByUser(userService.getByUsername(jwtTokenUtil.getUsernameFromToken(token.split(" ")[1])));
        return items;
    }

    @GetMapping(value = "/today")
    public List<ItemDto> getTodayItems(@RequestHeader("Authorization") String token){
        List<ItemDto> items=itemService.getTodayItemsByUser(userService.getByUsername(jwtTokenUtil.getUsernameFromToken(token.split(" ")[1])));
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

    @PostMapping(value = "/update")
    public void updateItem(@RequestBody ItemDto itemDto, @RequestHeader("Authorization") String token){
        userItemService.updateUserItem(itemDto,userService.getByUsername(jwtTokenUtil.getUsernameFromToken(token.split(" ")[1])));
    }
}
