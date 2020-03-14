package com.alexur.sell.utils;

import com.google.common.collect.Lists;
import org.dozer.DozerBeanMapper;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class DozerUtil{
    public static DozerBeanMapper dozerBeanMapper=new DozerBeanMapper();
     public static <T> List<T> mapList(Collection sourceList,Class<T> destinationClass){
         List destinationList = Lists.newArrayList();
         for(Iterator i$=sourceList.iterator();i$.hasNext();){
             Object sourceObject = i$.next();
             Object destinyObject = dozerBeanMapper.map(sourceObject,destinationClass);
             destinationList.add(destinyObject);
         }
         return destinationList;
     }
}
