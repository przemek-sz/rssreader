package com.rssreader.server.service;

import com.rssreader.server.feed.Channel;
import com.rssreader.server.model.Item;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class FeedProcess {

    public Document getXmlFeed(String channelUrl){

        Document doc=null;

        try{

        URLConnection connection = new URL(channelUrl).openConnection();
        doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(connection.getInputStream());

        doc.getDocumentElement().normalize();

        }catch (MalformedURLException e){
            System.out.println(e.getStackTrace());
        }catch (IOException e){
            System.out.println(e.getStackTrace());
        }catch (ParserConfigurationException e){
            System.out.println(e.getStackTrace());
        }catch (SAXException e){
            System.out.println(e.getStackTrace());
        }


        return doc;
    }


    public List<Item> feedFactory(Document document){


        NodeList nodeList = document.getElementsByTagName("item");


        List<Item> items = new ArrayList<>();


        for(int i=0;i<nodeList.getLength();i++){

            Node node = nodeList.item(i);
            Element element = (Element) node;
            String imgLink;


            String imgRegex="<img[^>]+src=\"([^\">]+)\"";
            Pattern imgPattern = Pattern.compile(imgRegex);

            Matcher m = imgPattern.matcher(element.getElementsByTagName("description").item(0).getTextContent());
            if (m.find( )) {
                imgLink=m.group(0).split("\"")[1].split("\"")[0];
            }else {
                imgLink="https://fakeimg.pl/640x360";
            }

            Item item = new Item();
            item.setTitle(element.getElementsByTagName("title").item(0).getTextContent());
            item.setLink(element.getElementsByTagName("link").item(0).getTextContent());
            item.setDescription(element.getElementsByTagName("description").item(0).getTextContent().replaceAll("<[^>]*>",""));
            item.setImgLink(imgLink);
            item.setPubdate(element.getElementsByTagName("pubDate").item(0).getTextContent());

            items.add(item);
        }


        //Channel feed=setChannel(document);

        //feed.setItems(items);

        return items;
    }


    public Channel setChannel(Document document){

        //Channel channel =new Channel("s","s","s");
       Channel channel=new Channel(document.getElementsByTagName("title").item(0).getTextContent(),
                document.getElementsByTagName("link").item(0).getTextContent(),
                document.getElementsByTagName("description").item(0).getTextContent());


        return channel;
    }
}
