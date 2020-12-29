package com.rssreader.server.service;

import java.util.ArrayList;
import java.util.List;

public interface BaseConverter<F, T> {

    public T convert(F from);


    default public List<T> convertAll(List<F> fElements){
        List<T> convertedElements=new ArrayList<>();

        for(F element:fElements){
            convertedElements.add(convert(element));
        }

        return convertedElements;

    }
}
