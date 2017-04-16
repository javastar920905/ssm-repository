package com.ouzhx.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * Created by Administrator on 2017/4/15.
 */
@SpringBootApplication
@Import(WebMvcConfig.class)
public class AppConfig {

  public static void main(String[] args) {
    SpringApplication.run(AppConfig.class);
  }
}
