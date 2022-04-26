package org.com.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.com.entity.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import sun.plugin2.message.TextEventMessage;

import javax.annotation.Resource;

@Configuration
public class RedisConfig {

    @Resource(name = "redisTemplate")
    private RedisTemplate template;

    @Bean
    public RedisTemplate getRedisTemplate(){
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }
}
