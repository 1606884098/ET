package com.se.java.base.javabase.base3.oop.oop5juc;

import java.lang.ref.WeakReference;
//发生gc就会被回收
public class Juc25WeakReferenceDemo {
    public static void main(String[] args){
        Object o1 = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(o1);
        System.out.println(o1);
        System.out.println(weakReference.get());

        o1 = null;
        System.gc();
        System.out.println("...............");

        System.out.println(o1);
        System.out.println(weakReference.get());


    }
}
