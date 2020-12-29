package com.rssreader.server.service;

import com.rssreader.server.dto.RssChannelDto;
import com.rssreader.server.model.RssChannel;
import org.springframework.stereotype.Service;

@Service
public class RssChannelToDtoConverter implements BaseConverter<RssChannel, RssChannelDto> {


    @Override
    public RssChannelDto convert(RssChannel from) {
        return new RssChannelDto(from.getRssUrl(),from.getUrl(),from.getTitle(),from.getDescription());
    }

}
