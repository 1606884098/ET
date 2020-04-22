package com.se.java.base.jvm.jvm1;

/**
 * @Author Science
 * @Date 2020/3/29 23:56
 * @Version 1.0
 */
public class ClassLoaderTest {
    public static void main(String[] args) {
        System.out.println(args[0]);//参数设置在program argument设置
        Object o=new Object();
        System.out.println(o.getClass().getClassLoader());//null系统加载器不暴露。在Launcher中
        Person a=new Person();
        System.out.println(a.getClass().getClassLoader());//应用加载器
        System.out.println(a.getClass().getClassLoader().getParent());//拓展加载器
        System.out.println(a.getClass().getClassLoader().getParent().getParent());
    }
}
