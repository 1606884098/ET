package com.se.java.base.javabase.base3.oop.oop5juc;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
//GC前会被放到队列保存一下
public class Juc27ReferenceQueueDemo {
    public static void main(String[] args) throws InterruptedException{
        Object o1 = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        WeakReference<Object> weakReference = new WeakReference<>(o1,referenceQueue);
        System.out.println(o1);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());//取元素

        System.out.println("=============");
        o1 = null;
        System.gc();
        Thread.sleep(500);

        System.out.println(o1);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());
    }
}
