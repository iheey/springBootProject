package org.com;

import org.junit.jupiter.api.Test;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class MainTest {
    @Resource
    private RedissonClient redisson;
    @Test
    public void add(){
        RBucket<Object> a = redisson.getBucket("a");
        a.set("hello");
        Object o = a.get();
        System.out.println(o);
    }

}
