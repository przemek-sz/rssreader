package com.rssreader.server.service;

import com.rssreader.server.dto.ItemDto;
import com.rssreader.server.model.Item;
import com.rssreader.server.model.RssChannel;
import com.rssreader.server.model.User;
import com.rssreader.server.model.UserItem;
import com.rssreader.server.repository.ItemRepository;
import com.rssreader.server.repository.UserItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    ItemToDtoConverter itemToDtoConverter;
    @Autowired
    RssChannelService rssChannelService;
    @Autowired
    FeedProcess feedProcess;
    @Autowired
    UserService userService;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    UserItemRepository userItemRepository;


    @Override
    public List<ItemDto> getItemsByUser(User user) {

        this.updateItemsOnDataBase(getItemsFromChannel(user));
        List<ItemDto> items = new ArrayList<>();
        this.getItemsFromChannel(user);

        user.getUseritemSet().forEach(userItem -> {
            Item item = userItem.getItem();
            items.add(itemToDtoConverter.convert(userItem.getItem(),userItem));
        });
        return items.stream().filter((e)->e.isHide()?false:true).collect(Collectors.toList());
    }

    @Override
    public List<ItemDto> getReadLaterItemsByUser(User user) {
        return getItemsByUser(user)
                .stream()
                .filter((e)->e.isReadlater()?true:false)
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemDto> getFavoriteItemsByUser(User user) {
        return getItemsByUser(user)
                .stream()
                .filter((e)->e.isFavorite()?true:false)
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemDto> getTodayItemsByUser(User user) {

        DateFormat df = new SimpleDateFormat("EEE, dd MMM yyyy kk:mm:ss", Locale.ENGLISH);

        return getItemsByUser(user)
                .stream()
                .filter(el->{
                    try {
                        Date result = df.parse(el.getPubdate().split("\\+")[0]);
                        //return result.compareTo(new Date())==1?true:false;
                        return Math.abs(result.getTime() - new Date().getTime()) < TimeUnit.DAYS.toMillis(1);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return false;
                }).collect(Collectors.toList());
    }


    @Override
    public List<ItemDto> getItemsByChannel(RssChannel rssChannel,User user) {

        this.updateItemsOnDataBase(getItemsFromChannel(user));

        List<Item> items = getItemsFromChannel(rssChannel);
        List<UserItem> userItems = new ArrayList<>();

        items.forEach(item -> {
            item.getUseritemSet().forEach(userItem -> {
                if(userItem.getUser().equals(user))
                    userItems.add(userItem);
            });
        });


        return itemToDtoConverter.convertAll(items,userItems);
    }

    @Override
    public List<Item> getItemsFromChannel(User user) {
        List<Item> items = new ArrayList<>();
        List<Item> itemsTemp = new ArrayList<>();
        for(RssChannel rssChannel:rssChannelService.getAllChannelsByUser(user)){
            itemsTemp=feedProcess.feedFactory(feedProcess.getXmlFeed(rssChannel.getRssUrl()));
            itemsTemp.forEach(item -> {
                item.setRssChannel(rssChannel);
            });
            items.addAll(itemsTemp);
        }
        return items;
    }


    public List<Item> getItemsFromChannel(RssChannel rssChannel) {
        List<Item> items;

        items=rssChannel.getItemSet().stream().collect(Collectors.toList());

        return items;
    }

    @Override
    public void updateItemsOnDataBase(List<Item> items) {

        List<Item> itemsToPersist=items.stream().filter(item -> itemRepository.getByLink(item.getLink()).size()==0).collect(Collectors.toList());

        itemsToPersist.forEach(item -> {
            itemRepository.save(item);

            item.getRssChannel().getChannelSet().forEach(userChannel -> {
                UserItem userItem = new UserItem();
                userItem.setItem(item);
                userItem.setUser(userChannel.getUser());
                userItemRepository.save(userItem);
                //userChannel.getUser();
            });
        });


    }

    @Override
    public void assignItemsToUser(User user, RssChannel rssChannel) {
        rssChannel.getItemSet().forEach(item -> {
            UserItem userItem = new UserItem();
            userItem.setUser(user);
            userItem.setItem(item);
            userItemRepository.save(userItem);
        });
    }


}
