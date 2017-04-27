package com.ouzhx.common.config;

/**
 * Created by ouzhx on 2017/4/27. motan 配置类
 */
public class MotanConstants {
  public static final String LOAD_BALANCE = "localFirst";
  public static final String HA_STRATEGY = "failfast";
  // 注册中心配置
  public static final String REGISTRY_ADDRESS = "60.205.228.78:2181";
  public static final String REGISTRY_NAME = "my_zookeeper";
  public static final String REGISTRY_REG_PROTOCOL = "zookeeper";
  // service 配置
  public static final String service_export_port = "8002";
  public static final String service_group = "motan-rpc";
  public static final String service_module = "service";

  // client 配置
  public static final String BASE_REFERER = "basicRefererConfig";
}
