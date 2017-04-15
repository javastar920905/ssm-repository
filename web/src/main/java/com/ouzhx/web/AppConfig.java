package com.ouzhx.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Administrator on 2017/4/15.
 */
@SpringBootApplication
public class AppConfig {

  public static void main(String[] args) {
    // 开启devtools
    System.setProperty("spring.devtools.restart.enabled", "true");
    SpringApplication.run(AppConfig.class);
  }
}
