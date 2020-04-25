package com.se.java.base.java8NewFeature.how;

import com.se.java.base.java8NewFeature.FunctionInterfaceDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @Author Science
 * @Date 2020/4/25 21:39
 * @Version 1.0
 *
 *
 * 主要是通过匿名参数的方式来使用
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
