package com.rssreader.server.service;

import com.rssreader.server.dto.ItemDto;
import com.rssreader.server.model.Item;
import com.rssreader.server.model.UserItem;
import org.springframework.stereotype.Service;

@Service
public class ItemToDtoConverter implements DoubleConverter<Item, UserItem, ItemDto>{

    @Override
    public ItemDto convert(Item from1, UserItem from2) {
        return new ItemDto(from1.getId(), from1.getTitle(), from1.getLink(), from1.getDescription(), from1.getImgLink(), from1.getPubdate(), from2.isReaded(), from2.isHide(), from2.isReadlater());
    }

    @Override
    public Object convert(Object from) {
        return null;
    }
}
