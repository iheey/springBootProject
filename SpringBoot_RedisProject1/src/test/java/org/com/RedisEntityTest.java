package org.com;

import org.com.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class RedisEntityTest {

    @Resource(name = "redisTemplate")
    private RedisTemplate template;

    @Test
    public void set(){
        Teacher teacher = new Teacher();
        teacher.setTname("吴旭");
        teacher.setTaddress("青木关");
        teacher.setTphone("13323112345");
        teacher.setTsex("男");
        teacher.setTsubject("五笔课程");
        template.opsForValue().set("tea",teacher);
    }
    @Test
    public void get(){
        Object tea = template.opsForValue().get("tea");
        System.out.println(tea);
    }
    @Test
    public void bounding(){
        BoundValueOperations tea = template.boundValueOps("tea");
        Teacher teacher = new Teacher();
        teacher.setTname("吴旭东");
        teacher.setTaddress("青木关");
        teacher.setTphone("13323112345");
        teacher.setTsex("男");
        teacher.setTsubject("五笔课程");
        tea.set(teacher);
        tea.expire(20, TimeUnit.SECONDS);
        Long expire = tea.getExpire();
        Object o = tea.get();
        System.out.println(o);
        System.out.println(expire);
    }
}
