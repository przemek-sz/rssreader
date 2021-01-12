package com.rssreader.server.service;

import com.rssreader.server.dto.ItemDto;
import com.rssreader.server.model.User;
import com.rssreader.server.model.UserItem;
import com.rssreader.server.repository.UserItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserItemServiceImpl implements UserItemService{

    @Autowired
    UserItemRepository userItemRepository;

    @Override
    public void updateUserItem(ItemDto itemDto, User user) {

        UserItem userItem = userItemRepository.getByItemIdAndUserId(itemDto.getId(), user.getId());
        userItem.setFavorite(itemDto.isFavorite());
        userItem.setHide(itemDto.isHide());
        userItem.setReadlater(itemDto.isReadlater());
        userItem.setReaded(itemDto.isReaded());
        userItemRepository.save(userItem);
    }
}
