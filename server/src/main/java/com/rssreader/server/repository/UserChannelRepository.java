package com.rssreader.server.repository;

import com.rssreader.server.model.RssChannel;
import com.rssreader.server.model.User;
import com.rssreader.server.model.UserChannel;
import com.rssreader.server.model.UserChannelID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserChannelRepository extends JpaRepository<UserChannel, UserChannelID> {

    public List<UserChannel> getAllByUser(User user);
    public List<UserChannel> getAllByUserId(Long id);
    public void deleteByUserAndRsschannel(User user,RssChannel rsschannel);

}
