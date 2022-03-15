package config;

import model.User;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableCaching
public class RedisConfig
{
    @Bean(name = "redisCacheManager")
    public CacheManager cacheManager(RedisTemplate redisTemplate)
    {
        RedisCacheConfiguration defaultConfiguration = RedisCacheConfiguration
                .defaultCacheConfig()
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(
                        redisTemplate.getKeySerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(
                        redisTemplate.getValueSerializer()))
                .disableCachingNullValues()
                .entryTtl(Duration.ofHours(1));

        Set<String> set = new HashSet();
        set.add("temp");

        return RedisCacheManager.RedisCacheManagerBuilder
                .fromConnectionFactory(redisTemplate.getConnectionFactory())
                .cacheDefaults(defaultConfiguration)
                .initialCacheNames(set)
                .transactionAware()
                .build();
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory()
    {
        return new JedisConnectionFactory();
    }

    @Bean
    public RedisTemplate<String, User> redisTemplate(RedisConnectionFactory factory)
    {
        RedisTemplate<String, User> template = new RedisTemplate<String, User>();

        template.setConnectionFactory(factory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new Jackson2JsonRedisSerializer<User>(User.class));

        return template;
    }
}
