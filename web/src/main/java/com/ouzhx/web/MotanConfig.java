package com.ouzhx.web;

import com.ouzhx.common.config.MotanConstants;
import com.weibo.api.motan.config.springsupport.AnnotationBean;
import com.weibo.api.motan.config.springsupport.BasicRefererConfigBean;
import com.weibo.api.motan.config.springsupport.ProtocolConfigBean;
import com.weibo.api.motan.config.springsupport.RegistryConfigBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by ouzhx on 2017/4/26.
 */
@Configuration
// @ImportResource("classpath:motan_client.xml")
// 使用注解方式
public class MotanConfig {


  @Bean /// 添加用到motan注解的类的包名 用到service的需要添加注解 @MotanReferer(basicReferer = "basicRefererConfig")
  public AnnotationBean annotationBean() {
    AnnotationBean annotationBean = new AnnotationBean();
    // service api 路径
    annotationBean.setPackage("com.ouzhx.web");
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

  @Bean(name = MotanConstants.BASE_REFERER)
  public BasicRefererConfigBean basicRefererConfigBean() {
    BasicRefererConfigBean config = new BasicRefererConfigBean();
    config.setProtocol("motanProtocol");
    config.setGroup(MotanConstants.service_group);
    config.setModule(MotanConstants.service_module);
    config.setApplication("myMotanImpl");
    config.setRegistry("registryConfig");

    config.setCheck(false);
    config.setAccessLog(true);
    config.setRetries(2);
    config.setThrowException(true);
    return config;
  }
}
