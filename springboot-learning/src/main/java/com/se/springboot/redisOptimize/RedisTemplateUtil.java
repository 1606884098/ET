package com.se.springboot.redisOptimize;

/**
 * @Author Science
 * @Date 2020/4/14 11:42
 * @Version 1.0
 */
public class RedisTemplateUtil {

    String key;
    String key_mutex;
    Object

    public String get(key) {//key加密
        String value = redis.get(key);
        if (value == null) { //代表缓存值过期
            //设置3min的超时，防止del操作失败的时候，下次缓存过期一直不能load db
            if (redis.setnx(key_mutex, 1, 3 * 60) == 1) {  //代表设置成功
                value = db.get(key);
                redis.set(key, value, expire_secs);
                redis.del(key_mutex);
            } else {  //这个时候代表同时候的其他线程已经load db并回设到缓存了，这时候重试获取缓存值即可
                sleep(50);
                get(key);  //重试
            }
        } else {
            return value;
        }
    }
}
