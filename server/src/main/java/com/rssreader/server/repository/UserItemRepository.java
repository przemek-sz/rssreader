package com.rssreader.server.repository;

import com.rssreader.server.model.UserChannel;
import com.rssreader.server.model.UserChannelID;
import com.rssreader.server.model.UserItem;
import com.rssreader.server.model.UserItemID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserItemRepository extends JpaRepository<UserItem, UserItemID> {
}
