package com.ouzhx.service;

import com.ouzhx.common.redis.RedisDao;
import com.weibo.api.motan.common.MotanConstants;
import com.weibo.api.motan.util.MotanSwitcherUtil;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Administrator on 2017/4/15.
 */
public class MotanServerRun {

  public static void main(String[] args) throws Exception {
    System.setProperty("server.port", "8081");
    AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(AppConfig.class);
    MotanSwitcherUtil.setSwitcherValue(MotanConstants.REGISTRY_HEARTBEAT_SWITCHER, true);
    System.out.println("motan server start!...");
    RedisDao redisTest = context.getBean(RedisDao.class);
    redisTest.addCache();
    System.out.println("load cache end!...");
    int i = 0;
    for (; i < 0; i++) {
      Thread.sleep(Long.parseLong("1000"));
      redisTest.sendMsg("count =" + i);
      System.out.println("service send a redis msg!");
    }
    System.out.println("publish msg end!");

  }
}
