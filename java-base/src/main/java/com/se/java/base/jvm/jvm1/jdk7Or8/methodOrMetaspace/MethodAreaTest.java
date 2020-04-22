package com.se.java.base.jvm.jvm1.jdk7Or8.methodOrMetaspace;



import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author Science
 * @Date 2020/4/10 11:27
 * @Version 1.0
 * 方法区溢出
 * 前面说到，方法区主要用于存储虚拟机加载的类信息、常量、静态变量（java8常量、静态变量移入到堆），以及编译器编译后的代码等数据，
 * 所以方法区溢出的原因就是没有足够的内存来存放这些数据。
 *
 * 由于在jdk1.6之前字符串常量池是存在于方法区中的，所以基于jdk1.6之前的虚拟机，可以通过不断产生不
 * 一致的字符串（同时要保证和GC Roots之间保证有可达路径）来模拟方法区的OutOfMemoryError异常；
 * 但方法区还存储加载的类信息，所以基于jdk1.7的虚拟机，可以通过动态不断创建大量的类来模拟方法区溢出。
 *
 *  jdk7 默认值是64m 设置方法区最大、最小空间：-XX:PermSize=10m -XX:MaxPermSize=10m
 *  注意java8是元空间:-XX:MetaspaceSize=10m -XX:MaxMetaspaceSize=10m
 *  运行时，通过cglib不断创建MethodAreaTest的子类，方法区中类信息越来越多，
 *  最终没有可以为新的类分配的内存导致内存溢出
 *
 *  下面代码代码运行后会报“java.lang.OutOfMemoryError: PermGen space/java.lang.OutOfMemoryError: Metaspace”的异常，说明是在方法区出现了
 *  内存溢出的错误。
 */
public class MethodAreaTest {
        public static void main(final String[] args){
            try {
                while (true){
                    Enhancer enhancer=new Enhancer();
                    enhancer.setSuperclass(MethodAreaTest.class);
                    enhancer.setUseCache(false);
                    enhancer.setCallback(new MethodInterceptor() {
                        @Override
                        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                            return methodProxy.invokeSuper(o,objects);
                        }
                    });
                    enhancer.create();
                }
            }catch (Throwable t){
                t.printStackTrace();
            }
        }
}
