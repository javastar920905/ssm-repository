package com.ouzhx.service.impl;

import com.ouzhx.service.CityService;
import com.weibo.api.motan.config.springsupport.annotation.MotanService;

/**
 * Created by ouzhx on 2017/4/26.
 */
@MotanService
public class CityServiceImpl implements CityService {
  @Override
  public String hello() {
    System.out.println("lalal");
    return "from cityServiceImpl";
  }
}
