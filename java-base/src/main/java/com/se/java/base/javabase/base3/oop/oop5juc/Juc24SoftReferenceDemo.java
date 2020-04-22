package com.se.java.base.javabase.base3.oop.oop5juc;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public class Juc24SoftReferenceDemo {
    /*
    * 内存够用的时候就保留，不够用就回收   mybatis缓存代码里就有这个例子
    * */
    public static void softRef_Memory_Enough(){
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());

        o1 = null;
        System.gc();
        System.out.println(o1);
        System.out.println(softReference.get());
    }

    public static void softRef_Memory_NotEnough(){
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());

        o1 = null;

        try{
            byte[] bytes = new byte[30*1024*1024];
        }catch (Throwable e){
            e.printStackTrace();
        }finally {
            System.out.println(o1);
            System.out.println(softReference.get());
        }
    }

    public static void main(String[] args){
        softRef_Memory_Enough();//内存够不回收
        softRef_Memory_NotEnough();//内存不够 回收

        Map<String,SoftReference<String>> imageCache = new HashMap<String,SoftReference<String>>();
    }
}
