package com.se.java.base.javabase.base3.oop.oop5juc;

import javax.jws.soap.SOAPBinding;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

class User{
    String userName;
    int age;
    public User(String userName,int age){

    }
}
class AtomicReferenceDemo {
    public static void main(String[] args){
        AtomicReference<User> atomicReference = new AtomicReference<>();

        User z3 = new User("z3",22);
        User li4 = new User("li4",25);

        atomicReference.set(z3);
        System.out.println(atomicReference.compareAndSet(z3,li4)+"\t"+atomicReference.get().toString());
        //已经是li4 再次修改就不允许了
        //atomicReference.compareAndSet(z3,li4)
        //atomicReference.compareAndSet(li4,z3);//又ABA了
        System.out.println(atomicReference.compareAndSet(z3,li4)+"\t"+atomicReference.get().toString());

    }
}
