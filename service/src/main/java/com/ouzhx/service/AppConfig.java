package com.ouzhx.service;

import com.ouzhx.common.redis.RedisConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by ouzhx on 2017/4/13.
 */
@Configuration
@Import({DubboServerConfig.class, RedisConfig.class})
public class AppConfig {

}
