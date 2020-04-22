package com.se.java.base.javabase.base3.oop.oop5juc;

import java.util.Random;

public class Juc30JavaHeapSpaceDemo {
    public static void main(String[] args){
        String str = "seu";

        while(true){
            str += str + new Random().nextInt(11111111)+new Random().nextInt(22222222);
            str.intern();
        }

    }
}
