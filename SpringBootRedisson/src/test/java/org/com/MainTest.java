package org.com;

import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RAtomicDouble;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

public class MainTest {

    private RedissonClient client;
    public MainTest(){
        Config config=new Config();
        config.useSingleServer().setAddress("redis://192.168.142.142:6379");
        client= Redisson.create(config);
    }

    @Test
    public void set(){
        RBucket<Object> weifen = client.getBucket("weifen");
        weifen.set("魏分");
        Object o = weifen.get();//获取当前Key的值
        System.out.println(o);
    }
    @Test
    public void get(){
        RBucket<Object> weifen = client.getBucket("weifen");
        weifen.set("李四",30, TimeUnit.SECONDS);//给当前key设置过期时间
        System.out.println(weifen.get());
        System.out.println(weifen.remainTimeToLive());//获取当前Key的剩余时间
    }
    @Test
    public void getAndset(){//获取并删除
        RBucket<Object> hello = client.getBucket("hello");
        hello.set("hello");
        System.out.println(hello.get());
        Object andDelete = hello.getAndDelete();
        System.out.println(andDelete);
    }
    @Test
    public void size(){
        RBucket<Object> hello = client.getBucket("hello");
        hello.set("helloworld");
        System.out.println(hello.size());
    }
    @Test
    public void setnx(){
        RBucket<Object> hello = client.getBucket("hello1");
        boolean abc = hello.trySet("abc");
        System.out.println(abc);
    }
    @Test
    public void expire(){
        RBucket<Object> hello = client.getBucket("hello1");
        hello.expire(20,TimeUnit.SECONDS);
        System.out.println(hello.remainTimeToLive());
    }
    @Test
    public void incrandget(){
        RAtomicLong age = client.getAtomicLong("age");
//        age.set(10);
        System.out.println(age.incrementAndGet());//给指定key自增1
        System.out.println(age.addAndGet(5));//给指定key加设定的数
    }
    @Test
    public void decrandget(){
        RAtomicLong age = client.getAtomicLong("age");
        System.out.println(age.decrementAndGet());
    }
    @Test
    public void decrfloatandget(){
        RAtomicDouble age = client.getAtomicDouble("age");
        System.out.println(age.addAndGet(3.14));
    }

}
