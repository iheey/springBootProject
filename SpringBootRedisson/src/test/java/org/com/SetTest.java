package org.com;

import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.Arrays;
import java.util.List;

public class SetTest {
    private RedissonClient redisson;
    public SetTest(){
        Config config=new Config();
        config.useSingleServer().setAddress("redis://192.168.142.142:6379").setPassword("kwygugdu19930408");
        redisson= Redisson.create(config);
    }
    @Test
    public void setadd(){
//        RSet<Object> stu = redisson.getSet("stu");
//        stu.add("张三");
//        List<String> stu1= Arrays.asList("李四","王五","朱闪闪");
//        stu.addAll(stu1);

//        RSet<Object> stu = redisson.getSet("java");
//        stu.add("房乐淇");
//        List<String> stu1= Arrays.asList("张三","小宝","朱闪闪");
//        stu.addAll(stu1);

        RSet<Object> stu = redisson.getSet("hello");
        stu.add("房乐淇");
        List<String> stu1= Arrays.asList("魏分","万亮","石继龙");
        stu.addAll(stu1);
    }
    @Test
    public void get(){
        RSet<Object> stu = redisson.getSet("stu");
        stu.readAll().forEach(System.out::println);
    }
    @Test
    public void diff(){//以stu为标准，将stu中的元素添加到其他集合中没有该元素的集合中去
        RSet<Object> stu = redisson.getSet("stu");
        System.out.println(stu.diff("java"));
    }
    @Test
    public void readDiff(){//以stu为标准，找出其他集合中（除去）含有stu的元素的元素，其他元素显示，stu中的元素其他集中中包含的不显示
        RSet<Object> stu = redisson.getSet("stu");
        System.out.println(stu.readDiff("hello"));
    }
    @Test
    public void intert(){//交集(同时都有的元素)
        RSet<Object> stu = redisson.getSet("stu");
        System.out.println(stu.intersection("hello"));//交集(将hello中的元素交集到stu中，将两个表的数据进行交集一致,以hello为主)
//        stu.readIntersection("hello").forEach(System.out::println);
    }
    @Test
    public void Union(){//并集（将两个key中的元素全部显示，重复的只显示一个）
        RSet<Object> stu = redisson.getSet("stu");
        System.out.println(stu.union("hello"));//以hello为主
//        stu.readUnion("hello").forEach(System.out::println);
    }
}
