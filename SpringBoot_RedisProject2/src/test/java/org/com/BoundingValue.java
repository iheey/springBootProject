package org.com;

import org.com.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class BoundingValue {
    @Resource(name ="redisTemplate")
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
        template.opsForValue().set(user.getId(),user);//可以加上过期时间
    }
    @Test
    public void GetandSet(){
        BoundValueOperations boundValueOperations = template.boundValueOps("1001");
        User o =(User) boundValueOperations.get();
        System.out.println(o);
        o.setUsername("魏分");
        o.setSubject("地理");
        boundValueOperations.set(o);//还可以加上过期时间
        o=(User) boundValueOperations.get();
        System.out.println(o);
    }
    @Test
    public void getExpire(){//设置过期时间
        BoundValueOperations boundValueOperations = template.boundValueOps("1001");
        boundValueOperations.expire(20, TimeUnit.SECONDS);
    }
}
