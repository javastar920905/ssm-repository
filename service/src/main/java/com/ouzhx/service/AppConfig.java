package com.ouzhx.service;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.ouzhx.repository.FlywayConfig;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by ouzhx on 2017/4/13.
 */
@Configuration
@Import({MotanConfig.class})
public class AppConfig {

}
