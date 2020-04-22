package com.se.java.base.javabase.base3.oop.oop5juc;

public class Juc23StrongReferenceDemo {
    public static void main(String[] args){
        Object obj1 = new Object();//这样定义默认的就是强引用
        Object obj2 = obj1;//obj2引用赋值
        obj1 = null;
        System.gc();
        System.out.println(obj2);
    }
}
