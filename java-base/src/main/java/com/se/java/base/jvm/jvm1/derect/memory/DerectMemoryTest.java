package com.se.java.base.jvm.jvm1.derect.memory;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @Author Science
 * @Date 2020/4/2 23:08
 * @Version 1.0
 * 直接内存（Derect Memory）
 *
 * 1.JVM的内存模型，里面并不包含直接内存，也就是说这块内存区域并不是JVM运行时数据区的一部分，
 * 但它却会被频繁的使用，原因是NIO这个包。
 *
 * NIO（New input/output）是JDK1.4中新加入的类，引入了一种基于通道（channel）和缓冲区（buffer）
 * 的I/O方式，它可以使用Native函数库直接分配堆外内存，然后通过堆上的DirectByteBuffer对象对这块
 * 内存进行引用和操作。
 *
 * 可以看出，直接内存的大小并不受到java堆大小的限制，甚至不受到JVM进程内存大小的限制。它只受限
 * 于本机总内存（RAM及SWAP区或者分页文件）大小以及处理器寻址空间的限制（最常见的就是32位/64位
 * CPU的最大寻址空间限制不同）。
 *
 * 直接内存出现OutOfMemoryError的原因是对该区域进行内存分配时，其内存与其他内存加起来超过最大
 * 物理内存限制（包括物理的和操作系统级的限制），从而导致OutOfMemoryError。另外，若我们通过参
 * 数“-XX:MaxDirectMemorySize”指定了直接内存的最大值，其超过指定的最大值时，也会抛出内存溢
 * 出异常。
 */
public class DerectMemoryTest {

    private static final int _1MB = 1024 * 1024;
    /**
     * jvm直接内存溢出
     * JVM参数：-Xmx20M -XX:MaxDirectMemorySize=10M
     */
    public static void main(String[] args) throws IllegalAccessException {
        //通过反射获取Unsafe类并通过其分配直接内存
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true) {
            unsafe.allocateMemory(_1MB);
        }
    }
}
