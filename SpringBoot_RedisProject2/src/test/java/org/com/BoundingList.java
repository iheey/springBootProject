package org.com;

import org.com.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class BoundingList {
    @Resource(name ="redisTemplate")
    private RedisTemplate template;

    @Test
    public void set(){
        User user = new User();
        user.setUsername("李好");
        user.setSubject("ajax");
        user.setId("1002");
        user.setPhone("13323154618");
        user.setAddress("重庆江北");

        User user1 = new User();
        user1.setUsername("张钱");
        user1.setSubject("java");
        user1.setId("1003");
        user1.setPhone("13351424618");
        user1.setAddress("重庆渝中");

        User user2 = new User();
        user2.setUsername("白恋");
        user2.setSubject("vue");
        user2.setId("1004");
        user2.setPhone("13243154618");
        user2.setAddress("重庆南岸");

        template.opsForList().leftPushAll("user",user,user1,user2);
    }

    @Test
    public void get(){//获取key的指定索引范围的值
        template.opsForList().range("user",0,-1).forEach(System.out::println);
    }
    @Test
    public void boundingrange(){
        BoundListOperations user = template.boundListOps("user");
        user.range(0,-1).forEach(System.out::println);
    }
    @Test
    public void boundinglistadd(){
        User user1 = new User();
        user1.setUsername("阿彩");
        user1.setSubject("小说");
        user1.setId("1005");
        user1.setPhone("15551424618");
        user1.setAddress("重庆九龙坡");

        User user2 = new User();
        user2.setUsername("张瑜");
        user2.setSubject("json");
        user2.setId("1006");
        user2.setPhone("16643154618");
        user2.setAddress("重庆巴南");
        BoundListOperations user = template.boundListOps("user");
        System.out.println(user.leftPushAll(user1, user2));
    }
    @Test
    public void boundinglistlpop(){//设置过期时间
        BoundListOperations user = template.boundListOps("user");
        user.leftPop(20, TimeUnit.SECONDS);
    }

}
