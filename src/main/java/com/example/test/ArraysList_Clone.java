package com.example.test;

import java.util.ArrayList;
import java.util.List;
import java.util.RandomAccess;

public class ArraysList_Clone {

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>();
        list.add("人生就是旅途");
        list.add("也许终点和起点会重合");
        list.add("但是一开始就站在起点等待终点");
        list.add("那么其中就没有美丽的沿途风景和令人难忘的过往");
        list.add(new Object().toString());

        //调用方法进行克隆
        Object o = list.clone();
        list.set(4,new Object().toString());

        System.out.println(o == list); // false;
        System.out.println(o);
        System.out.println(list);
        System.out.println(o instanceof ArrayList);
    }
}
