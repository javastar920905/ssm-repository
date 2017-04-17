package com.ouzhx.repository;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.sql.DataSource;

/**
 * Created by ouzhx on 2017/4/17.
 */
@Configuration
@Import(mybatisConfig.class)
public class FlywayConfig {
  @Bean
  public Flyway flyway(DataSource dataSource) {
    Flyway flyway = new Flyway();
    flyway.setDataSource(dataSource);

    // 启动后立即 进行版本合并start migration
    flyway.migrate();
    return flyway;
  }

}
