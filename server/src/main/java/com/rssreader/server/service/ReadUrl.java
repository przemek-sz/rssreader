package com.rssreader.server.service;


import com.rssreader.server.dto.RssChannelDto;
import com.rssreader.server.feed.Channel;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ReadUrl {

    @Autowired
    FeedProcess feedProcess;


    public List<RssChannelDto> getChannel(String url){


        if(checkIfRssUrl(url)){
            List<RssChannelDto> rssChannels=new ArrayList<>();
            Channel channel= feedProcess.setChannel(feedProcess.getXmlFeed(url));
            rssChannels.add(new RssChannelDto(url,channel.getLink(),channel.getTitle(),channel.getDescription()));
            return rssChannels;
        }
        else {
            return getUrlFromHTML(url);

        }
    }

    //==============================================================//

    public boolean checkIfRssUrl(String url){

        String regex = "(feed)|(xml)|(rss)";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(url);

        return matcher.find();
    }

    //==============================================================//

    public List<RssChannelDto> getUrlFromHTML(String url){

        String txt=null;

        try {
            txt= Jsoup.connect(url).get().toString();

        }catch (IOException e){
            System.out.println(e.getStackTrace());
        }




        String regex="<link[A-Za-z0-9\\s\"\\/:\\+\\?.=&\\nĄąĆćĘęŁłŃńÓóŚśŹźŻż-]*rel=\"alternate\"[A-Za-z0-9\\s\"\\/:\\+\\?.=&\\nĄąĆćĘęŁłŃńÓóŚśŹźŻż-]*\\/?>";

        //String regex="[\\S\\s]*?(<link[\\S\\s]*?rel=\"alternate\"[\\S\\s]*?\\/>)[\\S\\s]*?";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(txt);

        List<RssChannelDto> channelList=new ArrayList<>();



        while(matcher.find()){




            Pattern rssHrefPattern = Pattern.compile("href=\"(.*?)\"");
            Matcher rssHrefMatcher = rssHrefPattern.matcher(matcher.group());

            Pattern descriptionPattern = Pattern.compile("title=\"(.*?)\"");
            Matcher descriptionMatcher = descriptionPattern.matcher(matcher.group());

            Pattern titlePattern = Pattern.compile("https?:\\/\\/(.*?)\\/");
            Matcher titleMatcher = titlePattern.matcher(url);




            if(rssHrefMatcher.find()) {
                RssChannelDto rssChannelDto = new RssChannelDto();
                String rssUrl = rssHrefMatcher.group(1);
                rssChannelDto.setRssUrl(rssUrl);

                if (descriptionMatcher.find())
                    rssChannelDto.setDescription(descriptionMatcher.group(1));
                else rssChannelDto.setDescription("");

                if(titleMatcher.find())
                    rssChannelDto.setTitle(titleMatcher.group(1));
                else rssChannelDto.setTitle(url.split("//")[1]);

                rssChannelDto.setUrl(url);
                channelList.add(rssChannelDto);
            }
        }


        return channelList;
    }


}
