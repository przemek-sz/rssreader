package com.rssreader.server.service;

import com.rssreader.server.dto.ItemDto;
import com.rssreader.server.model.User;

public interface UserItemService {

    public void updateUserItem(ItemDto itemDto, User user);
}
