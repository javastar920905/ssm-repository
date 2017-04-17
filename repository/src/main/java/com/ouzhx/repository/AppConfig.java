package com.ouzhx.repository;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by ouzhx on 2017/4/13.
 */
@Configuration
@Import({FlywayConfig.class})
public class AppConfig {

}
