package com.yaping.common.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @aurhor wangyaping 【yapingw@dingtalk.com】
 * @date 2017/9/19 9:47
 * @description redis 缓存配置
 */
@Configuration
@EnableCaching
public class RedisConfig {

    @Bean
    public RedisCacheManager redisCacheManager(RedisTemplate redisTemplate) {
        redisTemplate.setDefaultSerializer(new StringRedisSerializer());
        return new RedisCacheManager(redisTemplate);
    }
}
