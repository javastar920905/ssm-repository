package com.ouzhx.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * Created by Administrator on 2017/4/15.
 */
@SpringBootApplication
@Import({WebMvcConfig.class, ShiroConfig.class, DubboConsumerConfig.class, RedisConfig.class})
@ComponentScan(basePackages = "com.ouzhx.web.controller")
public class AppConfig {
  /**
   * 启动后访问 http://localhost:8080/api-doc/index.html 可以查看api文档 并进行接口测试
   * 
   * @param args
   */
  public static void main(String[] args) {
    SpringApplication.run(AppConfig.class);
  }
}
