package org.com;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ListTest {
    @Resource
    private StringRedisTemplate template;
    @Test
    public void lpush(){
        template.opsForList().leftPush("lists","1");
        template.opsForList().leftPush("lists","2");
        template.opsForList().leftPush("lists","3");
        template.opsForList().leftPush("lists","4");
    }
    @Test
    public void Lpushall(){
        List<String> lists=new ArrayList<>();
        lists.add("5");
        lists.add("6");
        lists.add("7");
        template.opsForList().leftPushAll("lists",lists);
    }
    @Test
    public void lpushall(){
        template.opsForList().leftPushAll("lists","1","2","3","4");
    }
    @Test
    public void range(){//返回指定范围的元素
        System.out.println(template.opsForList().range("lists", 0, -1));
    }
    @Test
    public void lpop(){//返回左边第一个元素
        System.out.println(template.opsForList().leftPop("lists"));
    }
    @Test
    public void rpop(){//返回右边第一个元素
        System.out.println(template.opsForList().rightPop("lists"));
    }
    @Test
    public void remove(){//删除指定元素的个数
        System.out.println(template.opsForList().remove("lists", 1, "6"));
    }
    @Test
    public void trim(){//截取指定索引的元素
        template.opsForList().trim("lists",1,2);
    }
    @Test
    public void size(){//截取指定索引的元素
        template.opsForList().size("lists");
    }
}
