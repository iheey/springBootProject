package org.com;

import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.*;
import org.redisson.config.Config;

import java.util.Map;

public class GeoTest {
    private RedissonClient redisson;
    public GeoTest(){
        Config config=new Config();
        config.useSingleServer().setAddress("redis://192.168.142.142:6379").setPassword("kwygugdu19930408");
        redisson= Redisson.create(config);
    }
    @Test
    public void geoadd(){
        RGeo<Object> city = redisson.getGeo("city");
        GeoEntry cq=new GeoEntry(107,30,"重庆");
        GeoEntry bj=new GeoEntry(107,39,"北京");
        city.add(cq,bj);
    }
    @Test
    public void dist(){//测算两点之间的距离
        RGeo<Object> city = redisson.getGeo("city");
        Double dist = city.dist("重庆", "北京", GeoUnit.KILOMETERS);
        System.out.println(dist);
    }
    @Test
    public void radius(){//选择一个地点，以该地点为半径，找出指定距离范围内的其他元素
        RGeo<Object> city = redisson.getGeo("city");
        city.radius("重庆",1200,GeoUnit.KILOMETERS).forEach(System.out::println);
    }
    @Test
    public void radiuswithdistance(){//选择一个地点，以该地点为半径，找出指定距离范围内的其他元素
        RGeo<Object> city = redisson.getGeo("city");
        Map<Object, Double> cq = city.radiusWithDistance("重庆", 1200, GeoUnit.KILOMETERS, GeoOrder.DESC);
        cq.forEach((k,v)->{
            System.out.println(k+"==="+v);
        });
    }
}
