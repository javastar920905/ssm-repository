package com.ouzhx.service;

import com.ouzhx.common.redis.RedisCommonConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by ouzhx on 2017/4/13.
 */
@Configuration
@Import({MotanConfig.class, RedisCommonConfig.class})
public class AppConfig {

}
