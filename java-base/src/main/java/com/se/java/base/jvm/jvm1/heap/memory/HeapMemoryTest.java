package com.se.java.base.jvm.jvm1.heap.memory;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Science
 * @Date 2020/4/10 12:34
 * @Version 1.0
 * 堆内存中主要存放对象、数组等，只要不断地创建这些对象，并且保证GC Roots到对象之间有可达路径来
 * 避免垃圾收集回收机制清除这些对象，当这些对象所占空间超过最大堆容量时，就会产生OutOfMemoryError
 * 的异常。堆内存异常示例如下：
 *
 *  设置最大堆最小堆：-Xms20m -Xmx20m
 *  运行时，不断在堆中创建OOMObject类的实例对象，且while执行结束之前，GC Roots(代码中的oomObjectList)
 *  到对象(每一个OOMObject对象)之间有可达路径，垃圾收集器就无法回收它们，最终导致内存溢出。
 *
 * 运行后会报异常，在堆栈信息中可以看到 java.lang.OutOfMemoryError:Java heap space 的信息，说明在堆内
 * 存空间产生内存溢出的异常。
 * 新产生的对象最初分配在新生代，新生代满后会进行一次Minor GC，如果Minor GC后空间不足会把该对象和新
 * 生代满足条件的对象放入老年代，老年代空间不足时会进行Full GC，之后如果空间还不足以存放新对象则抛出
 * OutOfMemoryError异常。常见原因：内存中加载的数据过多如一次从数据库中取出过多数据；集合对对象引用过多且使用完后没有清空；代码中存在死循环或循环产生过多重复对象；堆内存分配不合理；网络连接问题、数据库问题等。
 */
public class HeapMemoryTest {
    static class OOMObject {
    }
    public static void main(String[] args) {
        List<OOMObject> oomObjectList = new ArrayList<>();
        while (true) {
            oomObjectList.add(new OOMObject());
        }
    }
}
