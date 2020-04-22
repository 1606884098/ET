package com.se.java.base.jvm.jvm1.stack.memory;

import java.util.concurrent.TimeUnit;

/**
 * @Author Science
 * @Date 2020/4/10 12:44
 * @Version 1.0
 * 栈是线程私有的，他的生命周期与线程相同，每个方法在执行的时候都会创建一个栈帧，用来存储局部变量
 * 表，操作数栈，动态链接，方法出口灯信息。局部变量表又包含基本数据类型，对象引用类型（局部变量表
 * 编译器完成，运行期间不会变化）
 * 参数：-Xss=2m
 * 只有递归调用会参数栈溢出，创建线程跟栈线程没关系，-Xss=2m是一个线程的大小
 */
public class StackMemoryTest {

    private int i = 0;
    public void a(){
        System.out.println(i++);
        a();
    }
    public static void main(String[] args) throws InterruptedException {
/*        StackMemoryTest j = new StackMemoryTest();
        j.a();*/
        new Thread(() -> {
            StackMemoryTest j = new StackMemoryTest();
            j.a();//递归调用栈溢出
        }, "a").start();

        while (true) {
            new Thread(() -> {
            }, "a").start();//创建线程不会栈溢出，Xss是一个线程内的大小
            //TimeUnit.SECONDS.sleep(5l);
            System.out.println("---------------------");
        }
    }
}
