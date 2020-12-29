package com.rssreader.server.controller;



import com.rssreader.server.feed.Feed;
import com.rssreader.server.service.FeedProcess;
import com.rssreader.server.service.ReadUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.w3c.dom.Document;




@RestController
@RequestMapping("/api/read")
public class ReadController {

    @Autowired
    ReadUrl readUrl;
    @Autowired
    FeedProcess feedProcess;

    @PostMapping
    public Feed readFromUrl(@RequestBody String url){

        System.out.println(url);

        return feedProcess.feedFactory(feedProcess.getXmlFeed(url));

    }

    @GetMapping
    public Document getData(String url){

        System.out.println(url);
        return feedProcess.getXmlFeed(url);
    }
}
