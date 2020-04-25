package com.se.java.base.java8NewFeature.how;

import java.util.function.Function;
/**
 * @Author Science
 * @Date 2020/4/25 21:39
 * @Version 1.0
 *
 *
 * 主要是通过匿名参数的方式来使用，具体的实现就函数接口方法的实现，函数接口方法可以有或无入参和返回值
 */
public class LambdaDemo {
    public static void main(String[] args) {
     Long tt=test((x)->{
         x="ee";
         return 100l;
     },10l);

        System.out.println(tt);
    }

    public static Long test(Function<String,Long> cc,Long l){
        return  cc.apply("aa")+l;
    }
}
