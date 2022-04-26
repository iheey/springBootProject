package org.com;

import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.HashMap;
import java.util.Map;

public class ZSetTest {
    private RedissonClient redisson;
    public ZSetTest(){
        Config config=new Config();
        config.useSingleServer().setAddress("redis://192.168.142.142:6379").setPassword("kwygugdu19930408");
        redisson= Redisson.create(config);
    }
    @Test
    public void zadd(){
        RScoredSortedSet<Object> scores = redisson.getScoredSortedSet("scores");
        scores.add(99.3,"张三");
        //scores.addScore("张三",56.5);
        Map<Object,Double> score=new HashMap<>();
        score.put("李四",82.1);
        score.put("王五",62.5);
        score.put("朱六",71.0);
        score.put("赵七",88.0);
        score.put("虎八",52.4);
        scores.addAll(score);
    }
    @Test
    public void zrange(){
        RScoredSortedSet<Object> scores = redisson.getScoredSortedSet("scores");
        scores.entryRange(0,-1).forEach(s->{
            System.out.println(s.getScore()+"=="+s.getValue());
        });
    }

}
