package org.com;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.support.collections.DefaultRedisSet;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

@SpringBootTest
public class ZsetTest {
    @Resource
    private StringRedisTemplate template;
    @Test
    public void zadd(){
        System.out.println(template.opsForZSet().add("scores", "王浩宇", 99.75));
    }
    @Test
    public void zadds(){//多个值添加操作
        Set<ZSetOperations.TypedTuple<String>> scores=new HashSet<>();
        scores.add(new DefaultTypedTuple("房乐淇",99.5));
        scores.add(new DefaultTypedTuple("小宝",18.5));
        scores.add(new DefaultTypedTuple("张淇",72.3));
        scores.add(new DefaultTypedTuple("刘妍",89.0));
        scores.add(new DefaultTypedTuple("赵瑞",59.5));
        template.opsForZSet().add("scores",scores);
    }
    @Test
    public void range(){//按分数升序排序--不显示分数
        template.opsForZSet().range("scores",0,-1).forEach(System.out::println);
    }
    @Test
    public void rangeWithScores(){//返回key中指定索引范围的值并赋上分数--默认升序排序
        Set<ZSetOperations.TypedTuple<String>> scores = template.opsForZSet().rangeWithScores("scores", 0, -1);
        scores.forEach(t->{
            System.out.println(t.getScore()+"========"+t.getValue());
        });
    }
    @Test
    public void reverseRange(){//按分数降序排序--不显示 分数
        template.opsForZSet().reverseRange("scores", 0, -1).forEach(System.out::println);
    }
    @Test
    public void reverseRangeWithScores(){//返回key中指定索引范围的值并赋上分数--降序排序
        Set<ZSetOperations.TypedTuple<String>> scores = template.opsForZSet().reverseRangeWithScores("scores", 0, -1);
        scores.forEach(t->{
            System.out.println(t.getScore()+"========"+t.getValue());
        });
    }
    @Test
    public void rangeByScore(){//按分值区间查询元素
        template.opsForZSet().rangeByScore("scores",60,80).forEach(System.out::println);
    }
    @Test
    public void rangeByScorewithscores(){//按分值区间查询元素并显示分数
        Set<ZSetOperations.TypedTuple<String>> scores = template.opsForZSet().rangeByScoreWithScores("scores", 60, 90);
        scores.forEach(t->{
            System.out.println(t.getScore()+"========"+t.getValue());
        });
    }
    @Test
    public void incrementScore(){//给指定元素的分数加分
        System.out.println(template.opsForZSet().incrementScore("scores", "刘妍", 5));
    }
    @Test
    public void score(){//返回指定元素的分值
        System.out.println(template.opsForZSet().score("scores", "刘妍"));
    }
    @Test
    public void size(){
        System.out.println(template.opsForZSet().size("scores"));
    }
}
