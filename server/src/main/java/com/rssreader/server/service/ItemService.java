package com.rssreader.server.service;

import com.rssreader.server.dto.ItemDto;
import com.rssreader.server.feed.Channel;
import com.rssreader.server.model.Item;
import com.rssreader.server.model.RssChannel;
import com.rssreader.server.model.User;

import java.util.List;

public interface ItemService {

    public List<ItemDto> getItemsByUser(User user);
    public List<ItemDto> getItemsByChannel(RssChannel rssChannel,User user);
    public List<Item> getItemsFromChannel(User user);
    public void updateItemsOnDataBase(List<Item> items);
    public void assignItemsToUser(User user,RssChannel rssChannel);


}
