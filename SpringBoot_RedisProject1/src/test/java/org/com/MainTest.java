package org.com;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class MainTest {
    @Resource
    private StringRedisTemplate template;
    @Test
    public void key(){
        //查询当前库中所有key
        Set<String> keys = template.keys("*");
//        keys.forEach(System.out::println);
        //删除key（类似于flushdb）
        //System.out.println(template.delete("keys"));
        //设置Key过期时间
//        Boolean hcllo = template.expire("hcllo", 1, TimeUnit.MINUTES);
//        System.out.println(template.getExpire("hcllo"));
        //获取当前key并重命名
//        template.rename("hdllo","hhello");
        //移动当前key到指定库中
//        template.move("hhello",1);
//        keys.forEach(System.out::println);
        //获取指定key的类型
//        DataType hcllo = template.type("hello");
//        System.out.println(hcllo.code());
        //判断当前Key是否存在
//        System.out.println(template.hasKey("heeello"));
    }

    @Test
    public void delKey() {
        Set<String> keys = template.keys("*");
        template.delete(keys);  //flushall   flushdb
        keys = template.keys("*");
        keys.forEach(System.out::println);
    }
    @Test
    public void ttlKey(){
        //template.expire("a",1, TimeUnit.MINUTES);
        Long a = template.getExpire("a");
        System.out.println(a);
    }

    @Test
    public void renameKey(){
        template.rename("b","name");
        Set<String> keys = template.keys("*");
        keys.forEach(System.out::println);
    }
    @Test
    public void moveKey(){
        Boolean name = template.move("name", 1);

    }

    @Test
    public void typeKey(){
        DataType f = template.type("f");
        System.out.println(f.code());
    }

    @Test
    public void existsKey(){
        System.out.println(template.hasKey("name"));
        System.out.println(template.hasKey("c"));
    }
}
