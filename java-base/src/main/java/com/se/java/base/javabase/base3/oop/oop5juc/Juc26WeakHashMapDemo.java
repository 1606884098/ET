package com.se.java.base.javabase.base3.oop.oop5juc;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.WeakHashMap;
/**
 * 假如有一个应用需要读取大量的本地图片：
 * 如果每次读取图片都从硬盘读取则会严重影响性能。
 * 如果一次性全部加载到内存中有可能造成内存泄露。
 * 此时使用软引用可以解决这个问题。
 *
 * 设计思路：用一个HashMap来保存图片的路径和相应图片对象关联的软引用之间的映射关系，在内存不足时，
 * JVM会自动回收这些缓存图片对象所占用的空间，从而有效地避免OOM的问题。
 */
public class Juc26WeakHashMapDemo {
    public static void main(String[] args){
        myHashMap();
        System.out.println("========");
        myWeakHashMap();
    }

    private static void myHashMap(){
        HashMap<Integer,String> map = new HashMap<>();
        Integer key = new Integer(1);
        String value = "HashMap";

        map.put(key,value);
        System.out.println(map);

        key = null;
        System.out.println(map);

        System.gc();
        System.out.println(map);
    }

    private static void myWeakHashMap(){//WeakHashMap用高速缓冲和对内存敏感的业务需求的开发
        WeakHashMap<Integer,String> map = new WeakHashMap<>();
        Integer key = new Integer(2);
        String value = "WeakHashMap";

        map.put(key,value);
        System.out.println(map);

        key = null;
        System.out.println(map);

        System.gc();
        System.out.println(map);
    }
}
