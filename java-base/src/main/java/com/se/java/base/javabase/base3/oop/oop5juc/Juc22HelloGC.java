package com.se.java.base.javabase.base3.oop.oop5juc;

public class Juc22HelloGC {

    public static void main(String[] args){
        //单位字节
        Long x=Runtime.getRuntime().totalMemory()/1024/1024;//java虚拟机中的内存总量
        Long y=Runtime.getRuntime().maxMemory()/1024/1024;//java虚拟机中试图的最大内存
        System.out.println(x);
        System.out.println(y);
    }
}
