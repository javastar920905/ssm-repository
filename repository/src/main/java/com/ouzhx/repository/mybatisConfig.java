package com.ouzhx.repository;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import com.ouzhx.common.MybatisUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.type.Alias;
import org.apache.tomcat.jdbc.pool.DataSourceFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.ClassPathMapperScanner;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.ResourceUtils;

/**
 * Created by ouzhx on 2017/4/14.
 */
@Configuration
@PropertySource("mybatis.properties")

@MapperScan(basePackages = "com.ouzhx.repository.mapper")
public class mybatisConfig {
  @Autowired
  private Environment env;

  /**
   * http://www.cnblogs.com/batys/archive/2012/10/17/2727637.html spring 配置数据源的几种方式
   * 使用org.springframework.jdbc.datasource.DriverManagerDataSource
   * 说明：DriverManagerDataSource建立连接是只要有连接就新建一个connection,根本没有连接池的作用。
   * <p>
   * 使用org.apache.commons.dbcp.BasicDataSource 说明:这是一种推荐说明的数据源配置方式，它真正使用了连接池技术
   * <p>
   * 使用org.springframework.jndi.JndiObjectFactoryBean 说明:JndiObjectFactoryBean 能够通过JNDI获取DataSource
   * <bean id="dataSource" class="org.springframework.jndi.JndiObjectFactoryBean">
   * <property name="jndiName"><value>java:comp/env/jdbc/roseindiaDB_local</value></property>
   * </bean> 总结：3种方式中的第一种没有使用连接池，故少在项目中用到，第三种方式需要在web server中配置数据源，不方便于部署，本人推荐使用每二种方式进行数据源的配置。
   *
   * @return
   * @throws Exception
   */
  @Bean
  @Profile("dev")
  public DataSource dataSource1() throws Exception {
    System.out.println("正在使用 dev 数据源!");
    // 这里出现一个问题 有系统属性username会优先于自定义配置的username 所以添加了mysql前缀
    DataSourceBuilder builder = new DataSourceBuilder(AppConfig.class.getClassLoader());
    builder.username(env.getProperty("mysql.username"));
    builder.url(env.getProperty("mysql.url"));
    builder.driverClassName(env.getProperty("mysql.driver"));
    builder.password(env.getProperty("mysql.password"));
    return builder.build();
  }

  @Bean
  @Profile("default")
  public DataSource dataSource2() throws Exception {
    System.out.println("正在使用 default 数据源!");
    Resource resource = new ClassPathResource(MybatisUtils.configPropertiesFileName);
    Properties properties = new Properties();
    properties.load(resource.getInputStream());
    DataSourceFactory factory = new DataSourceFactory();
    return factory.createDataSource(properties);
  }

  @Bean
  public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) throws Exception {
    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(dataSource);
    Resource[] xmlMappers =
        new PathMatchingResourcePatternResolver().getResources("classpath:xmlmapper/*.xml");
    sqlSessionFactoryBean.setMapperLocations(xmlMappers);
    // sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
    sqlSessionFactoryBean.setTypeAliasesPackage(env.getProperty("mybatis.typeAliasesPackage"));
    return sqlSessionFactoryBean;
  }

}
