package com.rssreader.server.repository;


import com.rssreader.server.model.RssChannel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RssChannelRepository extends JpaRepository<RssChannel,Long> {

    public List<RssChannel> getByUserId(Long id);

}
