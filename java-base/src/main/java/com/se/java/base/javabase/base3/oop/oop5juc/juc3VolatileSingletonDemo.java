package com.se.java.base.javabase.base3.oop.oop5juc;

public class juc3VolatileSingletonDemo {
    private static juc3VolatileSingletonDemo instance = null;
    private juc3VolatileSingletonDemo(){
        System.out.println(Thread.currentThread().getName()+"\t 我是构造函数SingletonDemo（）");
    }
//    DCL(Double Check Lock双端检锁机制)
    public static juc3VolatileSingletonDemo getInstance(){
        if(instance==null){
            synchronized (juc3VolatileSingletonDemo.class){//如果不用volatile禁止指令重排也会有安全问题
                if(instance==null){
                    instance = new juc3VolatileSingletonDemo();
                }
            }

        }
        return instance;
    }

    public static void main(String[] args){
//        单线程（main线程的操作）
//        System.out.println(SingletonDemo.getInstance()==SingletonDemo.getInstance());
//        System.out.println(SingletonDemo.getInstance()==SingletonDemo.getInstance());
//        System.out.println(SingletonDemo.getInstance()==SingletonDemo.getInstance());
//
//        System.out.println(",,,,,");

//        并发多线程后，情况发生了很大的变化
        for(int i=1;i<=10;i++){
            new Thread(()->{
                juc3VolatileSingletonDemo.getInstance();
            },String.valueOf(i)).start();
        }
    }
}
