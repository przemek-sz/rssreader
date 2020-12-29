package com.rssreader.server.service;

import com.rssreader.server.dto.RssChannelDto;
import com.rssreader.server.model.RssChannel;
import org.springframework.stereotype.Service;

@Service
public class RssChannelDtoToEntityConverter implements BaseConverter<RssChannelDto, RssChannel> {

    @Override
    public RssChannel convert(RssChannelDto from) {

        RssChannel  rssChannel = new RssChannel(
                from.getRssUrl(),
                from.getUrl(),
                from.getTitle(),
                from.getDescription()
        );

        return  rssChannel;

    }

}
