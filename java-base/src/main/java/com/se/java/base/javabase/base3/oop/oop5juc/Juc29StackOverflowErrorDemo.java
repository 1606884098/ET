package com.se.java.base.javabase.base3.oop.oop5juc;

public class Juc29StackOverflowErrorDemo {
    public static void main(String[] args){
        stackOverflowError();
    }

    private static void stackOverflowError() {
        stackOverflowError();
    }
}
