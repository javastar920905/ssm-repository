package com.ouzhx.web.listener;

import com.ouzhx.common.redis.RedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

/**
 * Created by ouzhx on 2017/4/28.
 */
public class RedisMsgListenerImpl implements MessageListener {
  @Autowired
  private RedisDao redisDao;


  RedisSerializer serializer;

  @Override
  public void onMessage(Message message, byte[] pattern) {
    serializer = redisDao.redisTemplate.getValueSerializer();
    String messageStr = serializer.deserialize(message.getBody()).toString();
    System.out.println("message received:" + messageStr);
  }
}
