package org.com;

import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.config.Config;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RMapTest {
    private RedissonClient redisson;
    public RMapTest(){
        Config config=new Config();
        config.setCodec(new StringCodec());//对某一些数值进行string的值的方式转换
        config.useSingleServer().setAddress("redis://192.168.142.142:6379").setPassword("kwygugdu19930408");
        redisson= Redisson.create(config);
    }
    @Test
    public void mapadd(){
        RMap<Object, Object> user = redisson.getMap("user");
        user.put("name","魏分");
        Map<Object,String> u=new HashMap<>();
        u.put("age","24");
        u.put("address","重庆市");
        u.put("phone","18623398076");
        user.putAll(u);
    }
    @Test
    public void hgetandhgetall(){
        RMap<Object, Object> user = redisson.getMap("user");
        System.out.println(user.get("name"));//获取指定key的值
        System.out.println("=================================");
        Set<Object> keys=new HashSet<>();
        keys.add("age");
        keys.add("address");
        Map<Object, Object> all = user.getAll(keys);//获取指定多个key和key的值
        all.forEach((k,v)->{
            System.out.println(k+"==="+v);
        });
        System.out.println("=================================");
        Set<Object> objects = user.keySet();//获取所有的键
        objects.forEach(c->{
            System.out.println(c);
        });
        System.out.println("=================================");
        user.values().forEach(System.out::println);//获取所有的值
    }
    @Test
    public void readAll(){//获取所有的keys和values
        RMap<Object, Object> user = redisson.getMap("user");
        Set<Map.Entry<Object, Object>> entries = user.readAllEntrySet();
        entries.forEach((k)->{
            System.out.println(k.getKey()+"=="+k.getValue());
        });
    }
    @Test
    public void fastremove(){
        RMap<Object, Object> user = redisson.getMap("user");
        System.out.println(user.fastRemove("phone"));
    }
    @Test
    public void incrby(){
        RMap<Object, Object> user = redisson.getMap("user");
        System.out.println(user.addAndGet("age", 10));
    }
}
