package com.rssreader.server.service;

import java.util.ArrayList;
import java.util.List;

public interface DoubleConverter<F1,F2,T> extends BaseConverter{

    public T convert(F1 from1,F2 from2);


    default public List<T> convertAll(List<F1> fElements1, List<F2> fElements2){
        List<T> convertedElements=new ArrayList<>();

        for(int i=0; i<fElements1.size(); i++){
            convertedElements.add(convert(fElements1.get(i),fElements2.get(i)));
        }
        return convertedElements;

    }
}
