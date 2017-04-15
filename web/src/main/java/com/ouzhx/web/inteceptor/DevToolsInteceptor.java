package com.ouzhx.web.inteceptor;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.boot.devtools.restart.Restarter;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;

/**
 * Created by Administrator on 2017/4/15. 开发人员工具提供的功能 自动重启: 将在类路径上的文件发生更改时自动重新启动
 * 请注意，某些资源（如静态资源和视图模板）不需要重新启动应用程序。
 *
 * 在IntelliJ IDEA中，构建项目（Build→Make Project） 触发重启,eclipse中 保存文件触发重启
 * 如果发现重新启动对应用程序不够快，或遇到类加载问题，您可以考虑来自ZeroTurnaround的JRebel等重新加载技术。
 *
 * 如果您需要完全禁用重新启动支持 则需要在调用SpringApplication.run（…）之前设置System属性。
 * System.setProperty("spring.devtools.restart.enabled", "false");
 * SpringApplication.run(MyApp.class, args);
 *
 * 要使用触发器文件，请使用spring.devtools.restart.trigger-file属性。
 * 一个特殊文件，当您要实际触发重新启动检查时，必须修改它。
 *
 */
@Order(Ordered.LOWEST_PRECEDENCE)
public class DevToolsInteceptor implements EnvironmentPostProcessor {
  private static final Map<String, Object> PROPERTIES;

  static {
    /*
     * 属性默认值
     * 
     * Spring Boots支持的几个库使用缓存来提高性能。 例如，模板引擎将缓存编译的模板，以避免重复解析模板文件。 此外，Spring
     * MVC可以在返回静态资源时向响应中添加HTTP缓存头。
     * 
     * 虽然缓存在生产中非常有益，但它在开发过程中可能会产生反效果，从而阻止您看到刚刚在应用程序中进行的更改。 因此，spring-boot-devtools将默认禁用这些缓存选项。
     * 
     * 缓存选项通常由您的application.properties文件中的设置配置。 例如，Thymeleaf提供了spring.thymeleaf.cache属性。
     * spring-boot-devtools模块不需要手动设置这些属性，而是自动应用更加合理的开发时(development-time)配置。
     */
    Map<String, Object> properties = new HashMap<String, Object>();
    properties.put("spring.thymeleaf.cache", "false");
    properties.put("spring.freemarker.cache", "false");
    properties.put("spring.groovy.template.cache", "false");
    properties.put("spring.mustache.cache", "false");
    properties.put("server.session.persistent", "true");
    properties.put("spring.h2.console.enabled", "true");
    properties.put("spring.resources.cache-period", "0");
    properties.put("spring.resources.chain.cache", "false");
    properties.put("spring.template.provider.cache", "false");
    properties.put("spring.mvc.log-resolved-exception", "true");
    properties.put("server.jsp-servlet.init-parameters.development", "true");
    /**
     * 在类路径下，某些资源在更改时不一定需要触发重新启动。 更改
     * /META-INF/maven，/META-INF/resources，/resources，/static，/public或/templates中的资源不会触发重新启动
     * 但会触发实时重新加载。 spring.devtools.restart.exclude=static/**,public/** //
     * 如果要保留这些默认值并添加其他排除项，请改用spring.devtools.restart.additional-exclude属性。
     **/
    properties.put("spring.devtools.restart.additional-exclude", "/jsp/static/**,public/**");
    /**
     * 有时当您对不在类路径中的文件进行更改时，需要重新启动或重新加载应用程序。为此，
     * 请使用spring.devtools.restart.additional-paths属性来配置其他路径以监视更改
     */
    PROPERTIES = Collections.unmodifiableMap(properties);
  }

  @Override
  public void postProcessEnvironment(ConfigurableEnvironment environment,
      SpringApplication application) {
    if (isLocalApplication(environment) && canAddProperties(environment)) {
      PropertySource<?> propertySource = new MapPropertySource("refresh", PROPERTIES);
      environment.getPropertySources().addLast(propertySource);
    }
  }

  private boolean isLocalApplication(ConfigurableEnvironment environment) {
    return environment.getPropertySources().get("remoteUrl") == null;
  }

  private boolean canAddProperties(Environment environment) {
    return isRestarterInitialized() || isRemoteRestartEnabled(environment);
  }

  private boolean isRestarterInitialized() {
    try {
      Restarter restarter = Restarter.getInstance();
      return (restarter != null && restarter.getInitialUrls() != null);
    } catch (Exception ex) {
      return false;
    }
  }

  private boolean isRemoteRestartEnabled(Environment environment) {
    RelaxedPropertyResolver resolver =
        new RelaxedPropertyResolver(environment, "spring.devtools.remote.");
    return resolver.containsProperty("secret");
  }
}
