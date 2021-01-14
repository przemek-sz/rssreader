package com.rssreader.server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rssreader.server.config.security.JwtTokenUtil;
import com.rssreader.server.dto.RssChannelDto;
import com.rssreader.server.model.RssChannel;
import com.rssreader.server.service.BaseConverter;
import com.rssreader.server.service.ReadUrl;
import com.rssreader.server.service.RssChannelService;
import com.rssreader.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/channel")
public class ChannelController {

    @Autowired
    ReadUrl readUrl;
    @Autowired
    UserService userService;
    @Autowired
    RssChannelService rssChannelService;
    @Qualifier("rssChannelDtoToEntityConverter")
    @Autowired
    BaseConverter<RssChannelDto, RssChannel> dtoToEntity;
    @Autowired
    BaseConverter<RssChannel,RssChannelDto> entityToDto;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;



    @RequestMapping(method = RequestMethod.POST,value = "/new")
    public List<RssChannelDto> newChannel(@RequestBody String url){
       return readUrl.getChannel(url);
    }


    @RequestMapping(method = RequestMethod.POST)
    public void add(@RequestBody String channel,@RequestHeader("Authorization") String token){


        ObjectMapper mapper = new ObjectMapper();
        RssChannelDto rssChannelDto=null;

        try {
            rssChannelDto=mapper.readValue(channel,RssChannelDto.class);
        }catch (IOException e){
            e.printStackTrace();
        }

        RssChannel rssChannel=dtoToEntity.convert(rssChannelDto);

        rssChannelService.follow(rssChannel,jwtTokenUtil.getUsernameFromToken(token.split(" ")[1]));
    }

     @GetMapping
     public List<RssChannelDto> getAllByUser(@RequestHeader("Authorization") String token){

        String username = jwtTokenUtil.getUsernameFromToken(token.split(" ")[1]);

        return entityToDto.convertAll(rssChannelService.getAllChannelsByUser(userService.getByUsername(username)));

    }

    @DeleteMapping
    public void deletUserChannel(@RequestBody RssChannelDto rssChannelDto,@RequestHeader("Authorization") String token){
        rssChannelService.deleteChannelByUser(rssChannelDto,userService.getByUsername(jwtTokenUtil.getUsernameFromToken(token.split(" ")[1])));
    }

}
