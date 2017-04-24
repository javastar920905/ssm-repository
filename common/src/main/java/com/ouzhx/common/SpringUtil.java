package com.ouzhx.common;

import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

/**
 * Spring工具类 Created by chenjun on 2017-03-23.
 */
public class SpringUtil {

  /**
   * 生成 PropertySourcesPlaceholderConfigurer
   *
   * @return PropertySourcesPlaceholderConfigurer
   */
  public static PropertySourcesPlaceholderConfigurer getPropertySourcesPlaceholderConfigurer() {
    PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
    Resource serverProp = new FileSystemResource("/rencaijia/config/rencaijia.properties");
    configurer.setLocation(serverProp);
    configurer.setIgnoreResourceNotFound(true);
    configurer.setLocalOverride(true);
    return configurer;
  }
}
