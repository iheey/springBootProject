package org.com;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisCommands;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class GeoTest {
    @Resource
    private StringRedisTemplate template;
    @Test
    public void geoadd(){//单个添加
        template.opsForGeo().add("citys",new Point(104.010343,30.566423),"成都");
    }
    @Test
    public void geoadds(){//多个添加
        Map<String, Point> citys=new HashMap<>();
        citys.put("重庆",new Point(108.888273,34.27483));
        citys.put("石家庄",new Point(112.887296,28.153765));
        citys.put("西安",new Point(114.469327,38.207453));
        citys.put("南昌",new Point(115.963468,28.810409));
        template.opsForGeo().add("citys",citys);
    }
    @Test
    public void range(){//返回当前Key中指定索引范围的值
        template.opsForZSet().range("city",0,-1).forEach(System.out::println);
    }
    @Test
    public void geopos(){//获取当前key中指定元素的经纬度
        List<Point> position = template.opsForGeo().position("city", "重庆");//查单个
        position.forEach(p->{
            System.out.println(p.getX()+"========"+p.getY());
        });
        List<Point> position1 = template.opsForGeo().position("city", "成都", "武汉", "贵阳");//查询 多个
        position1.forEach(c->{
            System.out.println(c.getX()+"--------"+c.getY());
        });
    }
    @Test
    public void distance(){//测算两个地点之间的距离
        Distance distance = template.opsForGeo().distance("city", "重庆", "成都", RedisGeoCommands.DistanceUnit.KILOMETERS);
        System.out.println(distance);
    }
    @Test
    //随机找一个点，以这个点画圆，找出符合指定距离的附近的其他点
    public void radiuCircle(){
        //circle圆                           point指定的点（经纬度）              指定距离
        Circle circle=new Circle(new Point(107.033753,28.126713),new Distance(800,RedisGeoCommands.DistanceUnit.KILOMETERS));
                    //显示距离
        RedisGeoCommands.GeoRadiusCommandArgs args=RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs().includeDistance();
        GeoResults<RedisGeoCommands.GeoLocation<String>> city = template.opsForGeo().radius("city", circle, args);
        city.forEach(c->{
            System.out.println(c.getContent().getName()+"========"+c.getDistance().getValue()+c.getDistance().getUnit());
        });
    }

    @Test
    //返距离指定地点（指定距离）范围内的元素
    public void geoRadius(){
        //指定距离及单位
        Distance distance=new Distance(800,RedisGeoCommands.DistanceUnit.KILOMETERS);
        //显示距离  sortAscending升序排序  limit是显示条目数
        RedisGeoCommands.GeoRadiusCommandArgs args=RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs().includeDistance().sortAscending().limit(5);
        GeoResults<RedisGeoCommands.GeoLocation<String>> radius = template.opsForGeo().radius("city", "南昌", distance, args);
        radius.forEach(c->{
            System.out.println(c.getContent().getName()+"========"+c.getDistance().getValue()+c.getDistance().getUnit());
        });
    }

}
