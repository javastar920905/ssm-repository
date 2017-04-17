package com.ouzhx.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Administrator on 2017/4/15. 参考配置文档 http://spring.cndocs.tk/mvc.html#mvc-config
 */
@Configuration
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    registry.addResourceHandler("/static/**").addResourceLocations("/resources/static/");
    registry.addResourceHandler("/api-doc/**").addResourceLocations("/resources/api-doc/");
    super.addResourceHandlers(registry);
  }
}
