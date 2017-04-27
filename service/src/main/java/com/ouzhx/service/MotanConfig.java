package com.ouzhx.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ouzhx.common.config.MotanConstants;
import com.weibo.api.motan.config.springsupport.AnnotationBean;
import com.weibo.api.motan.config.springsupport.BasicServiceConfigBean;
import com.weibo.api.motan.config.springsupport.ProtocolConfigBean;
import com.weibo.api.motan.config.springsupport.RegistryConfigBean;

/**
 * Created by ouzhx on 2017/4/26.
 */
@Configuration
// @ImportResource("classpath:motan_server.xml")
// 使用注解方式实现 实现类要求添加注解 @MotanService

public class MotanConfig {
  @Bean // 添加用到motan注解的service 实现类的包名
  public AnnotationBean annotationBean() {
    AnnotationBean annotationBean = new AnnotationBean();
    annotationBean.setPackage("com.ouzhx.service.impl");
    return annotationBean;
  }

  /** 配置ProtocolConfig、RegistryConfig、BasicServiceConfig的bean对象，功能与xml配置中的protocol、registry、basicService标签一致。 */
  @Bean("motanProtocol")
  public ProtocolConfigBean protocolConfig() {
    ProtocolConfigBean config = new ProtocolConfigBean();
    config.setDefault(true);
    config.setName("motan");
    config.setLoadbalance(MotanConstants.LOAD_BALANCE);
    config.setHaStrategy(MotanConstants.HA_STRATEGY);
    return config;
  }

  @Bean(name = "registryConfig")
  public RegistryConfigBean registryConfig() {
    RegistryConfigBean registryConfig = new RegistryConfigBean();
    registryConfig.setAddress(MotanConstants.REGISTRY_ADDRESS);
    registryConfig.setName(MotanConstants.REGISTRY_NAME);
    registryConfig.setRegProtocol(MotanConstants.REGISTRY_REG_PROTOCOL);
    return registryConfig;
  }

  @Bean
  public BasicServiceConfigBean basicServiceConfigBean() {
    BasicServiceConfigBean config = new BasicServiceConfigBean();
    config.setAccessLog(false);
    config.setShareChannel(true);
    config.setExport("motanProtocol:8002");
    config.setGroup(MotanConstants.service_group);
    config.setModule(MotanConstants.service_module);
    config.setApplication("myMotanImpl");
    config.setRegistry("registryConfig");
    return config;
  }

}
