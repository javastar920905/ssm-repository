package com.ouzhx.service;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Administrator on 2017/4/15.
 */
public class DubboServerRun {

  public static void main(String[] args) throws Exception {
    AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(AppConfig.class);
    context.start();
    CityService cityService = context.getBean("cityService", CityService.class);
    cityService.hello();
    System.out.println("dubbo server start!...");
    System.in.read(); // 按任意键退出,挂起程序
  }
}
