package org.com;

import org.com.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class MainTest {
    @Resource(name="redisTemplate")
    private RedisTemplate template;
    @Test
    public void set(){
        User user = new User();
        user.setId("1001");
        user.setUsername("张三");
        user.setPhone("13323145168");
        user.setAddress("重庆巴南区中昂");
        user.setSex("男");
        user.setSubject("java");
        template.opsForValue().set(user.getId(),user);
    }
    @Test
    public void get(){
        Object o = template.opsForValue().get("1001");
        System.out.println(o);
    }

    @Test
    public void getandset(){//添加的同时设置过期时间
        User user = new User();
        user.setId("1002");
        user.setUsername("李四");
        user.setPhone("13323145168");
        user.setAddress("重庆巴南区中昂");
        user.setSex("男");
        user.setSubject("java");
//        Boolean aBoolean = template.opsForValue().setIfAbsent(user.getId(), user, 30, TimeUnit.SECONDS);
//        System.out.println(aBoolean);
        System.out.println(template.opsForValue().getAndSet("1002", "你好"));//获取原值并设置新值
    }
    @Test
    public void listadd(){//一次性添加多个对象
        User user1 = new User();
        user1.setId("1003");
        user1.setUsername("光波");
        user1.setPhone("13323145168");
        user1.setAddress("重庆巴南区中昂");
        user1.setSex("女");
        user1.setSubject("数学");

        User user2 = new User();
        user2.setId("1004");
        user2.setUsername("李五");
        user2.setPhone("13323145168");
        user2.setAddress("重庆巴南区中昂");
        user2.setSex("女");
        user2.setSubject("樊利");

        User user3 = new User();
        user3.setId("1005");
        user3.setUsername("太极");
        user3.setPhone("13323145168");
        user3.setAddress("重庆巴南区中昂");
        user3.setSex("男");
        user3.setSubject("语言");

        template.opsForList().leftPushAll("user1",user1,user2,user3);
        template.opsForList().range("user1",0,-1).forEach(System.out::println);
    }
}
