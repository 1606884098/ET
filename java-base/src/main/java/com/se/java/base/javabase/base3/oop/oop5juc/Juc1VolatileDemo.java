package com.se.java.base.javabase.base3.oop.oop5juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyData{
    volatile int number = 0;
    //int number = 0;
    public void addTo60(){
        this.number = 60;
    }
    public void addPlusPlus(){
        number++;
    }
    //number++在多线程下是非线程安全的,如何不加synchronized解决?
    AtomicInteger atomicInteger = new AtomicInteger();//CAS实现的
    public void addMyAtommic(){
        atomicInteger.getAndIncrement();
    }
}
/*
1 验证volatile的可见性
    1.1 加入int number=0，number变量之前根本没有添加volatile关键字修饰,没有可见性
    1.2 添加了volatile，可以解决可见性问题
2 验证volatile不保证原子性
    2.1 原子性是不可分割，完整性，也即某个线程正在做某个具体业务时，中间不可以被加塞或者分割。
    需要整体完成，要么同时成功，要么同时失败。
    2.2 volatile不可以保证原子性演示
    2.3 如何解决原子性
        *加sync
        *使用我们的JUC下AtomicInteger
* */
class VolatileDemo {
    public static void main(String[] args) throws InterruptedException {
        MyData myData = new MyData();
        //没加volatile不可见
        new Thread(()->{
            myData.addTo60();
            System.out.println(myData.number);
        },"没加volatile线程").start();

        TimeUnit.SECONDS.sleep(1);//如果不加这行代码有可能下面的代码比myData.addTo60();先执行,所以不能用可见性来做线程之间的判断操作
        while(myData.number==0){//不可见死循环
            System.out.println("主线程没有看到其他线程改变的值 60！");
            TimeUnit.SECONDS.sleep(2);
            break;
        }


        for (int i = 1; i <= 20 ; i++) {
            new Thread(()->{
                for (int j = 1; j <= 1000 ; j++) {
                    myData.addPlusPlus();
                    myData.addMyAtommic();
                }
            },String.valueOf(i)).start();
        }

        //需要等待上述20个线程都计算完成后，再用main线程去的最终的结果是多少？
//        try{TimeUnit.SECONDS.sleep(5);} catch (InterruptedException e) {e.printStackTrace();}
        while(Thread.activeCount() > 2){
            Thread.yield();
        }
        //会比2000少的原始是：比如线程a把number的值4写到主内存时，b线程把number的值3写到主内存，下一个线程只能从3开始加了，结果就小了
        System.out.println(Thread.currentThread().getName()+"\t finnally number value: "+myData.number);
        System.out.println(Thread.currentThread().getName()+"\t finnally number value: "+myData.atomicInteger);
    }
}
