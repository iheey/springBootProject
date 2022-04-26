package org.com;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class StringTest {
    @Resource
    private StringRedisTemplate template;
    @Test
    public void setGetValue(){
        template.opsForValue().set("name","张三");
        System.out.println(template.opsForValue().get("name"));
    }
    @Test
    public void setTimeValue(){
        template.opsForValue().set("age","20",1, TimeUnit.MINUTES);
    }

    @Test
    public void setNxExValue(){
        Boolean aBoolean = template.opsForValue().setIfAbsent("15086891052", "重庆市", 2, TimeUnit.MINUTES);
        System.out.println(aBoolean);
    }
    @Test
    public void getAndSet(){
        String andSet = template.opsForValue().getAndSet("hbllo", "李四");
        System.out.println(andSet);
    }
    @Test
    public void getrange(){
        String andSet = template.opsForValue().get("hbllo",3,9);
        System.out.println(andSet);
    }
    @Test
    public void incrAndincrByAndincrByFloat(){
        System.out.println(template.opsForValue().increment("age"));
        System.out.println(template.opsForValue().increment("age", 10));
        System.out.println(template.opsForValue().increment("age",3.14));
    }
    @Test
    public void decrAndDecrBy(){
        System.out.println(template.opsForValue().decrement("age"));
        System.out.println(template.opsForValue().decrement("age", 10));
    }
    @Test
    public void mset(){
        Map<String,String> keys=new HashMap<>();
        keys.put("address","重庆市");
        keys.put("stu","陈富强");
        keys.put("java","java");
        template.opsForValue().multiSet(keys);
    }
    @Test
    public void mget(){
        List<String> list= Arrays.asList("address","stu","java");
        System.out.println(template.opsForValue().multiGet(list));

    }
    @Test
    public void msetnx(){
        Map<String,String> keys=new HashMap<String, String>();
        keys.put("address1","重庆市");
        keys.put("stu1","陈富强");
        keys.put("java1","java");
        Boolean aBoolean = template.opsForValue().multiSetIfAbsent(keys);
        System.out.println(aBoolean);
    }

}
