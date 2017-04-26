package com.ouzhx.service.impl;

import com.ouzhx.service.CityService;

/**
 * Created by ouzhx on 2017/4/26.
 */
public class CityServiceImpl implements CityService {
  @Override
  public String hello() {
    System.out.println("lalal");
    return "from cityServiceImpl";
  }
}
