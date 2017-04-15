package com.ouzhx.repository;

import com.ouzhx.entity.City;
import com.ouzhx.repository.mapper.CityMapper;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Administrator on 2017/4/15.
 */
public class Test {
  public static void main(String[] args) {
    AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(AppConfig.class);
    CityMapper cityMapper = context.getBean(CityMapper.class);
    City city = cityMapper.selectByPrimaryKey(1);
    System.out.println(city.getDescription());
  }
}
