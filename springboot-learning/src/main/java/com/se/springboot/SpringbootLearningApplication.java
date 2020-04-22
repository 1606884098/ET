package com.se.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * 设置线程数指应用的线程处理时间（线程阻塞时间，响应时间），
 * 设置线程数，压测看看cup在80%-90%时，就是最佳线程数据。不
 * 要100%，因为100%如果需要登录看日志也没有资源了。8G的内存，
 * 设置堆内存的时候也是要预留空间设置为4G-5为佳
 *
 * 没有处理的请求 超过设定的超时时间就是错误请求(jmeter 现实的错误请求)
 *
 * 提高连接数是没有效果的，一定要提高吞吐量，设置线程数。Tomcat参数设置
 * 的优化可以改变的参数太少了，所以只有代码优化才知重点，通过多线程，中
 * 间件来提高性能，也就是应该的处理时间尽量短。
 *
 * 多核cpu可以到理论上是可以到n*100%
 *
 *
 * 优化的第一步是监控
 *
 * Full gc stop the world
 * System.gc()很有可能导致fullgc
 * fullgc包括元空间
 *
 * 1.系统卡了 第一步肯定是看log文件，log文件是不能少的
 * 2.连接liunx  top命令看 按1可以看清楚每个核的使用情况  按q退出top 按c更详细
 * 3.看看jvm运行的情况无非就是一下线程  jcmd查看
 * 4.jstat pid > a.log  将线程日志输入到a.log文件
 * 5.more a.log看文件
 * Vi a.log   输入/  查找关键字查找
 */
@SpringBootApplication
@RestController//用于tomcat调优测试
@EnableAsync//允许异步
public class SpringbootLearningApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootLearningApplication.class,args);
    }
    //-Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=9999 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false
    //设置参数 -Dserver.tomcat.maxConnection=2 -Dserver.tomcat.maxThread=10 -Dserver.tomcat.acceptCount=3
    //java -jar -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=9999 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false
    // 这个方法固定延时3秒，用于测试线程/连接数量控制
    //java -jar springboot-learning-1.0-SNAPSHOT.jar --server.tomcat.maxConnection=2 --server.tomcat.maxThread=10 --server.tomcat.acceptCount=3
    @RequestMapping("/testCount")
    public String testCount() throws InterruptedException {
        Thread.sleep(3000);// connections  acceptCount
        return "Success";
    }

    @RequestMapping("/test")
    public String benchmark() throws InterruptedException {
        System.out.println("访问test：" + Thread.currentThread().getName());

        // 这段代码，一直运算。
        for (int i = 0; i < 200000; i++) {
            new Random().nextInt();
        }
        // 50毫秒的数据库等待，线程不干活
        //Thread.sleep(50L);
        TimeUnit.MILLISECONDS.sleep(50);
        return "Success";
    }

    // 异步支持
    @RequestMapping("/testAsync")
    @Async
    public Callable<String> benchmarkAsync() throws InterruptedException {
        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("访问testAsync：" + Thread.currentThread().getName());
                // 这段代码，一直运算。
                for (int i = 0; i < 200000; i++) {
                    new Random().nextInt();
                }
                // 50毫秒的数据库等待，线程不干活
                Thread.sleep(50L);
                return "Success";
            }
        };
    }
}
