package org.com;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class HashTest {
    @Resource
    private StringRedisTemplate template;
    @Test
    public void putAndget(){
        template.opsForHash().put("user","name","唐三");
        System.out.println(template.opsForHash().get("user", "name"));
    }
    @Test
    public void putall(){//为key添加多个值（有小key加key对应的值）
        Map<String,String> hash=new HashMap<>();
        hash.put("age","20");
        hash.put("sex","男");
        hash.put("phone","13323145687");
        hash.put("address","斗罗大陆");
        template.opsForHash().putAll("user",hash);
    }
    @Test
    public void getall(){//获取key的所有元素
        Map<Object, Object> user = template.opsForHash().entries("user");
        user.forEach((k,v)->{
            System.out.println(k+"==="+v);
        });
    }
        @Test
    public void hmget(){//获取key的指定元素多个
        template.opsForHash().multiGet("user", Arrays.asList(new String[]{"name","age","address"})).forEach(System.out::println);
    }
    @Test
    public void valsAndkeys(){//获取key的所有key，获取key的所有值
        template.opsForHash().keys("user").forEach(System.out::println);
        template.opsForHash().values("user").forEach(System.out::println);
    }
    @Test
    public void hdel(){//删除指定小key
        System.out.println(template.opsForHash().delete("user", "phone"));
    }
    @Test
    public void hexists(){//查看当前小key是否存在
        System.out.println(template.opsForHash().hasKey("user", "phone"));
        System.out.println(template.opsForHash().hasKey("user", "name"));
    }
    @Test
    public void hsetnx(){//为key添加小key和值，（key不存在时添加），
        System.out.println(template.opsForHash().putIfAbsent("user", "phone", "12344321123"));
    }
    @Test
    public void hincrby(){//为指定小key添加数值
        System.out.println(template.opsForHash().increment("user", "age", 5));
    }
    @Test
    public void size(){
        System.out.println(template.opsForHash().size("user"));
    }
}
