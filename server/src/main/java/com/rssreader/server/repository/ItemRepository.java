package com.rssreader.server.repository;

import com.rssreader.server.model.Item;
import com.rssreader.server.model.RssChannel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {

    public List<Item> getByLink(String link);
}
