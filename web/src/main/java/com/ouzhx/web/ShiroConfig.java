package com.ouzhx.web;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.config.Ini;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * Created by ouzhx on 2017/4/20.
 */
@Configuration
public class ShiroConfig {

  @Bean
  public Ini ini() {
    Ini ini = new Ini();
    return ini;
  }

  @Bean(name = "securityManager")
  public SecurityManager securityManager(Ini ini) {
    SecurityManager securityManager = new DefaultWebSecurityManager();
    SecurityUtils.setSecurityManager(securityManager);
    return securityManager;
  }

  // Shiro生命周期处理器
  @Bean(name = "lifecycleBeanPostProcessor")
  public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
    return new LifecycleBeanPostProcessor();
  }



  @Bean("shiroFilter")
  public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
    ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
    shiroFilter.setUnauthorizedUrl("/401");
    shiroFilter.setSuccessUrl("/index/");
    shiroFilter.setSecurityManager(securityManager);
    shiroFilter.setLoginUrl("/login");
    shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMap());
    return shiroFilter;
  }

  public Map<String, String> filterChainDefinitionMap() {
    Map<String, String> filterChainDefinitionMap = new HashMap<>();
    filterChainDefinitionMap.put("/user/edit/**", "authc,perms[user:edit]");// 这里为了测试，固定写死的值，也可以从数据库或其他配置中读取
    filterChainDefinitionMap.put("/login", "anon");
    filterChainDefinitionMap.put("/", "anon");
    filterChainDefinitionMap.put("/**", "user");// anon 可以理解为不拦截

    return filterChainDefinitionMap;
  }


  // 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),
  @Bean
  public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
    DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
    return advisorAutoProxyCreator;
  }

  @Bean
  public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
      SecurityManager securityManager) {
    AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
    advisor.setSecurityManager(securityManager);
    return advisor;
  }


  @Bean(name = "ehCacheManager")
  @DependsOn("lifecycleBeanPostProcessor")
  public EhCacheManager ehCacheManager() {
    EhCacheManager ehCacheManager = new EhCacheManager();
    return ehCacheManager;
  }

}
