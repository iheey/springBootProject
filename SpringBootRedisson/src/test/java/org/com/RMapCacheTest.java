package org.com;

import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.config.Config;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class RMapCacheTest {
    private RedissonClient redisson;
    public RMapCacheTest(){
        Config config=new Config();
        config.setCodec(new StringCodec());
        config.useSingleServer().setAddress("redis://192.168.142.142:6379").setPassword("kwygugdu19930408");
        redisson= Redisson.create(config);
    }
    @Test
    public void mapandTimeout() throws IOException {
        RMapCache<Object, Object> tea = redisson.getMapCache("tea");
        tea.put("15086891052","hello",2, TimeUnit.MINUTES);
        tea.put("18215468745","1234",30, TimeUnit.SECONDS);
        tea.put("19921548792","4321",1, TimeUnit.MINUTES);
        tea.put("15354875124","password",50, TimeUnit.SECONDS);
        tea.put("18623398076","123456",2,TimeUnit.MINUTES,20,TimeUnit.SECONDS);
        System.in.read();//必须阻塞程序才不会自动结束
    }

}
