package org.com;

import org.com.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class StringEntityTest {
    //private StringRedisTemplate template; //  k(String)   v(String)
    @Resource(name = "redisTemplate")
    private RedisTemplate template;
    @Test
    public void entitySet(){
        Student student=new Student();
        student.setStudentNo("10000");
        student.setStudentName("王浩宇");
        student.setSex("男");
        student.setPhone("13312344321");
        student.setAge(25);
        student.setAddress("重庆市江北区西普大厦");
        template.opsForValue().set(student.getStudentNo(),student);
    }

    @Test
    public void entityGet(){
        Student o = (Student) template.opsForValue().get("10000");
        System.out.println(o);
    }

    @Test
    public void hadd(){
        Map<String,Student> stus=new HashMap<>();
        Student student=new Student();
        student.setStudentNo("10000");
        student.setStudentName("王浩宇");
        student.setSex("男");
        student.setPhone("13312344321");
        student.setAge(25);
        student.setAddress("重庆市江北区西普大厦");
        stus.put("王浩宇",student);
        Student student1=new Student();
        student1.setStudentNo("10001");
        student1.setStudentName("魏芬");
        student1.setSex("女");
        student1.setPhone("13343214321");
        student1.setAge(25);
        student1.setAddress("重庆市江北区西普大厦");
        stus.put("魏芬",student1);
        Student student2=new Student();
        student2.setStudentNo("10002");
        student2.setStudentName("石继龙");
        student2.setSex("男");
        student2.setPhone("1333333333");
        student2.setAge(29);
        student2.setAddress("重庆市江北区西普大厦");
        stus.put("石继龙",student2);
        template.opsForHash().putAll("stus",stus);
    }

    @Test
    public void hgetall(){
        Map stus = template.opsForHash().entries("stus");
        stus.forEach((k,v)->{
            System.out.println(k);
            System.out.println(v);
            System.out.println("============================================");
        });
    }

    @Test
    public void ctrlStu(){
        BoundValueOperations boundValueOperations = template.boundValueOps("10000");
        Student o = (Student) boundValueOperations.get();
        System.out.println(o);
        o.setStudentName("陈意");
        o.setSex("女");
        o.setPhone("18888888888");
        boundValueOperations.set(o);
        o = (Student) boundValueOperations.get();

        System.out.println(o);
    }

    @Test
    public void ctrlList(){
        BoundListOperations stuList = template.boundListOps("stuList");

        Student student=new Student();
        student.setStudentNo("10000");
        student.setStudentName("王浩宇");
        student.setSex("男");
        student.setPhone("13312344321");
        student.setAge(25);
        student.setAddress("重庆市江北区西普大厦");

        Student student1=new Student();
        student1.setStudentNo("10001");
        student1.setStudentName("魏芬");
        student1.setSex("女");
        student1.setPhone("13343214321");
        student1.setAge(25);
        student1.setAddress("重庆市江北区西普大厦");

        Student student2=new Student();
        student2.setStudentNo("10002");
        student2.setStudentName("石继龙");
        student2.setSex("男");
        student2.setPhone("1333333333");
        student2.setAge(29);
        student2.setAddress("重庆市江北区西普大厦");

        stuList.leftPushAll(student,student1,student2);

        List range = stuList.range(0, -1);
        range.forEach(System.out::println);
    }
}
