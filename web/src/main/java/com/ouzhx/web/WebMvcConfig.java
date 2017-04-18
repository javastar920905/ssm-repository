package com.ouzhx.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Created by Administrator on 2017/4/15. 参考配置文档 http://spring.cndocs.tk/mvc.html#mvc-config
 */
@Configuration
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {
  @Override
  public void configureViewResolvers(ViewResolverRegistry registry) {
    InternalResourceViewResolver resourceViewResolver = new InternalResourceViewResolver();
    resourceViewResolver.setPrefix("/resources/template/");
    resourceViewResolver.setSuffix(".ftl");
    registry.viewResolver(resourceViewResolver);
    super.configureViewResolvers(registry);
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    registry.addResourceHandler("/static/**").addResourceLocations("classpath:static/");
    registry.addResourceHandler("/api-doc/**").addResourceLocations("classpath:static/api-doc/");
    registry.addResourceHandler("/api-docs/**").addResourceLocations("classpath:static/api-doc/");
    registry.addResourceHandler("/favicon.ico").addResourceLocations("classpath:static/favicon.ico");
    super.addResourceHandlers(registry);
  }
}
