package com.ouzhx.common.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.ouzhx.common.config.RedisConstants;

import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by ouzhx on 2017/4/28.
 */
@Configuration
@ComponentScan("com.ouzhx.common.redis")
public class RedisConfig {

  @Bean
  public JedisConnectionFactory jedisConnectionFactory() {
    JedisConnectionFactory factory = new JedisConnectionFactory();
    factory.setUsePool(true);
    // 连接池配置
    JedisPoolConfig poolConfig = new JedisPoolConfig();
    factory.setPoolConfig(poolConfig);

    factory.setHostName(RedisConstants.HOST);
    factory.setPort(RedisConstants.PORT);
    return factory;
  }

  @Bean
  public RedisTemplate redisTemplate(JedisConnectionFactory connectionFactory) {
    RedisTemplate redisTemplate = new RedisTemplate();
    redisTemplate.setConnectionFactory(connectionFactory);
    return redisTemplate;
  }


}
