package com.ouzhx.common.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by ouzhx on 2017/4/28.
 */
@Component
public class RedisDao {
  public static final String TOPIC = "msgTopic";
  @Autowired
  public RedisTemplate redisTemplate;

  @Resource(name = "redisTemplate")
  private ListOperations<String, String> listOps;

  public void addCache() {
    listOps.leftPush("java", "java test ");
    // or use template directly
    redisTemplate.boundListOps("java").leftPush("java test ");
  }

  // 发布一条消息到redis
  public void sendMsg() {
    redisTemplate.convertAndSend(TOPIC, "hello!");
  }

  public void sendMsg(String msg) {
    redisTemplate.convertAndSend(TOPIC, msg);
  }

}
