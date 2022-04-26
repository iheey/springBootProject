package org.com;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

public class MainTest {
    @Test
    public void Test(){

        Jedis jedis=new Jedis("192.168.142.142",6379);
        jedis.auth("kwygugdu19930408");
        jedis.select(0);
        Set<String> keys = jedis.keys("*");
//        keys.forEach(System.out::println);
        String s = jedis.flushAll();
        System.out.println(s);
    }
    @Test
    public void TestKey(){
        Jedis jedis=new Jedis("192.168.142.142",6379);
        jedis.auth("kwygugdu19930408");
        jedis.select(0);
//        jedis.mset("hallo","hallo","hbllo","hbllo","hcllo","hcllo","hdllo","hdllo");
//        jedis.set("hdllo","hdllo");
//        String hello = jedis.get("hello");
//        System.out.println(hello);
//        List<String> mget = jedis.mget("hfllo", "hdllo", "hello");
//        mget.forEach(System.out::println);
//        Boolean hello1 = jedis.exists("hello");
//        System.out.println(hello1);
//        jedis.expire("hello",10);
//        Long hello = jedis.ttl("hello");
//        System.out.println(hello);
//        jedis.pexpire("hdllo",200);
//        Long hdllo = jedis.pttl("hdllo");
//        System.out.println(hdllo);
//        jedis.move("hallo",1);
//        jedis.select(0);
//        Set<String> keys = jedis.keys("*");
//        keys.forEach(System.out::println);
//        String s = jedis.randomKey();
//        System.out.println(s);
//        System.out.println(jedis.rename("hdllo", "hhllo"));//重命名
        System.out.println(jedis.type("hello"));//查key的类型

    }
    @Test
    public void Tes(){
        Jedis jedis=new Jedis("192.168.142.142",6379);
        jedis.auth("kwygugdu19930408");
        jedis.select(0);
        //System.out.println(jedis.mset("hallo", "hallo", "hbllo", "hbllo", "hcllo", "hcllo", "hdllo", "hdllo", "hello", "hello"));
        //System.out.println(jedis.strlen("hallo"));//获取key长度
        //System.out.println(jedis.getSet("hallo", "张三"));//返回原值，再重命名
        //System.out.println(jedis.append("hallo", "你好"));//向key值追 加内容
//        System.out.println(jedis.getrange("hallo", 0, -1));//截取指定索引范围的值
//        jedis.setex("hbllo",20,"你好世界");//对指定key设置时间
//        System.out.println(jedis.ttl("hbllo"));//获取当前Key的剩余过期时间
        //添加key(当此key不存在时添加操作，存在不做任何操作)
//        System.out.println(jedis.setnx("hbllo", "123456"));
//        System.out.println(jedis.msetnx("hcllo","123","hdllo","hdllo"));

    }
    /**
     * redis有五种基本数据类型（String,list,sex,zset,hash）
     *1、string:set key value单个添加,get key单个获取；    --mset key1 value1,key2 value2...多个添加,mget key1,key2...多个获取
     *  strlen key获取当前key的长度；  --getrange key start end截取当前key指定索引范围内的内容
     *  append key value向当前key的值后面追加内容；  --getset key value 返回当前key的内容并设置新的值
     *  setex key seconds value给当前key设置过期时间并赋上新的内容（s）；  --ttl key 返回当前Key的剩余过期时间（s）
     *  psetex key seconds value给当前key设置过期时间并赋上新的内容（ms）     --pttl key
     *  setnx key value(如果设置的key不存在做添加key操作)；  --msetnx key1 value1,key2 value2...(如果设置的key不存在做添加key操作)添加多个
     *  incr key(对当前Key做+1操作，要求值必须是数字类型)   --incrby key number (对当前Key做+number操作，要求值必须是数字类型)
     *  decr key(对当前Key做-1操作，要求值必须是数字类型)  --decrby key number(对当前Key做+number操作，要求值必须是数字类型)
     *  incrbyfloat key number(float)(对当前Key做+小数操作，要求值必须是数字类型)---如果key的值是小数则不能做+1或者-1操作
     *  ==============================================================================================================================
     *2、list:lpush key value1 value2 value3...将多个值添加到集合中   --lpushx key value
     */

}
