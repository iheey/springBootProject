package org.com.test;

import jdk.nashorn.internal.ir.CallNode;
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
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.142.142:6379");
        client = Redisson.create(config);
//        提交更改
        //分支测试。。。
    }
    @Test
    public void connectionRedisson() {
        try{

            RBucket<Object> a = client.getBucket("a");
            a.set("张三");
            Object o = a.get();
            System.out.println(o);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Test
    public void setex(){
        RBucket<Object> a = client.getBucket("a");
        a.set("b",30, TimeUnit.SECONDS);
        System.out.println(a.remainTimeToLive());

    }

    @Test
    public void getset(){
        RBucket<Object> a = client.getBucket("a");
        a.set("张三");
        System.out.println(a.getAndSet("李四"));
    }

    @Test
    public void getDel(){
        RBucket<Object> a = client.getBucket("a");
        System.out.println(a.getAndDelete());
    }

    @Test
    public void getByte(){
        RBucket<Object> a = client.getBucket("a");
        a.set("张三");
        System.out.println(a.size());
    }

    @Test
    public void setnx(){
        RBucket<Object> a = client.getBucket("a");
        System.out.println(a.trySet("abc"));
    }

    @Test
    public void exprie(){
        RBucket<Object> a = client.getBucket("age");
        a.set(10);
    }

    @Test
    public void incrAnddecr(){
        RAtomicLong age = client.getAtomicLong("age");
        //age.set(10);
       // System.out.println(age.incrementAndGet());
       // System.out.println(age.addAndGet(5));
        System.out.println(age.decrementAndGet());

    }

    @Test
    public void incrbyfloat(){
        RAtomicDouble age = client.getAtomicDouble("age");
        System.out.println(age.addAndGet(3.14));
    }
}
