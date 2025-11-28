package com.zhy.spring.cache.commonCache;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;

@Configuration
@ConditionalOnClass(StringRedisTemplate.class)
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class CommonCacheSpringConfiguration {

    @Bean
    @ConditionalOnMissingBean(CommonCacheManager.class)
    public CommonCacheManager commonCacheManager(List<CacheProvider> cacheProviderList) {
        return new CommonCacheManager(cacheProviderList);
    }

    @Bean
    @ConditionalOnMissingBean(CacheProvider.class)
    public CacheProvider redisCacheProvider(StringRedisTemplate stringRedisTemplate) {
        return new RedisCacheProvider(stringRedisTemplate);
    }
}