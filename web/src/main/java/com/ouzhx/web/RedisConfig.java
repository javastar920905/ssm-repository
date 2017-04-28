package com.ouzhx.web;

import com.ouzhx.common.redis.RedisCommonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

import com.ouzhx.common.redis.RedisDao;
import com.ouzhx.web.listener.RedisMsgListenerImpl;

/**
 * Created by ouzhx on 2017/4/28.
 */
@Configuration
@Import({RedisCommonConfig.class,RedisMsgListenerImpl.class})
public class RedisConfig {
  @Autowired
  private RedisMsgListenerImpl redisMsgListener;

  @Bean // redis消息监听容器
  public RedisMessageListenerContainer redisMessageListenerContainer(
      JedisConnectionFactory connectionFactory) {
    RedisMessageListenerContainer redisMessageListenerContainer =
        new RedisMessageListenerContainer();
    redisMessageListenerContainer.setConnectionFactory(connectionFactory);

    redisMessageListenerContainer.addMessageListener(redisMsgListener,
        new ChannelTopic(RedisDao.TOPIC));
    return redisMessageListenerContainer;
  }
}
