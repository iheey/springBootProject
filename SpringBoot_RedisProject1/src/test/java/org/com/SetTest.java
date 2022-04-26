package org.com;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

@SpringBootTest
public class SetTest {
    @Resource
    private StringRedisTemplate template;
    @Test
    public void add(){
        System.out.println(template.opsForSet().add("names", "张三", "李四", "王五", "赵六", "孙七"));
    }
    @Test
    public void members(){//获取指定key的所有元素
        template.opsForSet().members("names").forEach(System.out::println);
    }
    @Test
    public void move(){//获取指定key的所有元素
        template.opsForSet().add("student","房乐淇");
        template.opsForSet().move("names","张三","student");
        template.opsForSet().members("student").forEach(System.out::println);
    }
    @Test
    public void remove(){//移除指定元素
        System.out.println(template.opsForSet().remove("student", "张三"));
    }
    @Test
    public void ismember(){//查看地当前key中指定的元素是否存在
        System.out.println(template.opsForSet().isMember("student", "张三"));
        System.out.println(template.opsForSet().isMember("names", "李四"));
    }
    @Test
    public void randomember(){
        System.out.println(template.opsForSet().randomMembers("names",3));//随机返回当前key中指定数量元素
        System.out.println(template.opsForSet().randomMember("student"));//随机返回单个元素
    }
//    =====================================================
    @Test
    public void sdiff(){//去掉第一个集合中其他集合含有的相同元素
        template.opsForSet().difference("names","student").forEach(System.out::println);
    }
    @Test
    public void intersect(){//返回当前指定key中的元素的交集
        template.opsForSet().intersect("names","student").forEach(System.out::println);
    }
    @Test
    public void union(){//返回当前指定key中的元素的并集
        template.opsForSet().union("names","student").forEach(System.out::println);
    }
    @Test
    public void size(){//返回当前指定key中的元素的并集
        System.out.println(template.opsForSet().size("names"));
    }
}
