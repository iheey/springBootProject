package org.com;

import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.Arrays;
import java.util.List;

public class ListTest {

    private RedissonClient redisson;
    public ListTest(){
        Config config=new Config();
        config.useSingleServer().setAddress("redis://192.168.142.142:6379").setPassword("kwygugdu19930408");
        redisson= Redisson.create(config);
    }

    @Test
    public void add(){
        RList<Object> stus = redisson.getList("stus");
//        stus.add(1);
//        stus.add("张三");
//        stus.add(1,"李四");//在指定下标上添加值
//        stus.addBefore("hello","房乐淇");//在指定某一个元素之前添加新元素
//        stus.addAfter("张三","魏分");//在指定某一个元素之后添加新元素
        List<String> list= Arrays.asList("小宝","陈富强","王浩宇","刘妍妍","张嘉芮");//一次性添加多个值
        stus.addAll(list);
    }
    @Test
    public void get(){
        RList<Object> stus = redisson.getList("stus");//定位到当前Key
        System.out.println(stus.get(1));//通过当前key获取指定单个下标的单个元素
        System.out.println("======================");
        stus.get(1,2,3,4).forEach(System.out::println);//获取指定（多个）下标的多个元素
        System.out.println("======================");
        stus.range(0,-1).forEach(System.out::println);//获取指定索引范围的元素
    }
    @Test
    public void del(){
        RList<Object> stus = redisson.getList("stus");

    }
}
