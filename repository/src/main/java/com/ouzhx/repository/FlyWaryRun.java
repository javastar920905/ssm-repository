package com.ouzhx.repository;

import com.ouzhx.common.config.MybatisUtils;
import org.apache.tomcat.jdbc.pool.DataSourceFactory;
import org.flywaydb.core.Flyway;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by ouzhx on 2017/4/17.
 */
public class FlyWaryRun {
  /**
   * 添加新的sql版本后 可以手动执行版本合并动作 (或者:默认启动容器时就会执行版本合并)
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    // 创建数据源
    Resource resource = new ClassPathResource(MybatisUtils.configPropertiesFileName);
    Properties properties = new Properties();
    properties.load(resource.getInputStream());
    Flyway flyway = new Flyway();
    DataSourceFactory factory = new DataSourceFactory();
    DataSource dataSource = factory.createDataSource(properties);

    flyway.setDataSource(dataSource);

    // start migration
    flyway.migrate();

  }
}
