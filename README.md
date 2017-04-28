# ssm-repository
这是spring mvc+mybatis的手脚架项目  使用motan 作为rpc框架
* web 层添加了shiro 权限控制,需要自定义realm
* web层为motan的client service为motan的服务端
这里使用了zookeeper作为注册中心,60.205.228.78:2181 (一个docker容器 2017-04-26后该主机过期)
如果需修改注册中心地址,请修改下面两个配置文件 <motan:registry address属性/>
web模块 /src/main/resources/motan_client.xml
service模块 /src/main/resources/motan_client.xml

* 添加了redis 消息订阅和发布功能
service 为消息发布者 ,web为消息订阅者 
redis服务器配置在 common项目 com.ouzhx.common.config.RedisConstants.java文件中修改









spring 参考文档 http://spring.cndocs.tk/ 
 # spring 框架基础概述
 spring 框架,为实现java应用程序提供了全面基础架构支持,你可以专注于应用开发,让spring处理基础架构问题
 spring 使你能够使用纯粹的java对象来构建应用

 # 背景
 Java应用 — 运行着小到各种受限的小程序，大到n层结构的服务器端企业级应用 — 包含着相互协作的对象，从而创建正确的应用。 因此，程序里的对象彼此之间相互依赖。

 尽管Java平台提供的很多功能性的应用程序，但是他缺少将这些基础组件组织成一个整体的方法，最终把这些整合工作交给了架构师或是开发者。 你可以使用设计模式，例如工厂，抽象工厂，建造者，装饰和服务定位来将这些不同的类和示例对象组合起来，从而构建一个应用。 但是，这些模式仅仅只是：一个被给予名字的最佳实践，说明了该模式做什么，怎样应用，解决了什么问题等等。 模式是形式化的，你必须在你的应用中去实现它。

 Spring中的控制反转 (IoC)部分解决了这个问题，通过提供一种有效的方式将各个分开的组件组合成一个完全可供使用的应用。 Spring框架用函数化的形式实现了形式化的设计模式，这样你就可以在你的应用中继承它们。 很多组织个机构正在使用Spring框架来设计健壮的，便于维护的应用。

 # 模块
 Spring框架的各个特性被组织成20个模块。这些模块被分组成Core Container（核心容器）, Data Access/Integration（数据访问/集成）, Web（网络端）, AOP (Aspect Oriented Programming，切面编程), Instrumentation, Messaging（消息）,和Test（测试）
 * 核心容器 包含了 spring-core, spring-beans, spring-context, and spring-expression (Spring表达式语言) 四个模块。

 # spring 学习资源
 * spring 官网  spring.io
 * 框架概述以及模块划分 http://spring.cndocs.tk/overview.html
 * 版本升级指南 https://github.com/spring-projects/spring-framework/wiki/Migrating-from-earlier-versions-of-the-spring-framework
 * 系列deme入门指南 https://spring.io/guides
 * maven BOM 文件概述 http://spring.cndocs.tk/overview.html#overview-maven-bom
 * 核心技术目录 http://spring.cndocs.tk/spring-core.html
 * 配置元数据方式(xml/注解/基于java对象) http://spring.cndocs.tk/beans.html#beans-factory-metadata
 IOC 容器 ApplicationContext所管理的beans,实例化的方式有多种 如:http://spring.cndocs.tk/beans.html#context-create
 推荐基于java 配置 http://spring.cndocs.tk/beans.html#beans-java
 使用AnnotationConfigWebApplicationContext支持Web应用  http://spring.cndocs.tk/beans.html#beans-java-instantiating-container-web
 * 依赖注入 http://spring.cndocs.tk/beans.html#beans-factory-collaborators
 * bean 的作用域 http://spring.cndocs.tk/beans.html#beans-factory-scopes

 * Environment抽象 (包含了两个方面：profiles 和 properties.) http://spring.cndocs.tk/beans.html#beans-environment
 解决问题,多种数据源在不同环境下的切换(dev/product)
 在@Bean方法中  @Profile("dev")  @Profile("production") 标识配置文件,实现重载效果
 用 @Profile("default") 默认配置,如果有任何一个配置文件启用，default配置就会被覆盖。
 ctx.getEnvironment().setActiveProfiles("dev"); 在springIoc容器中激活使用的配置文件

 * Resource 接口 http://spring.cndocs.tk/resources.html
 java 标准 java.net.URL 和针对不同 URL 前缀的标准处理器并不能满足我们对各种底层资源的访问
 Rource接口提供访问相对类路径或者相对 ServletContext 的各种资源的能力
 classpath:   classpath:com/myapp/config.xml    从类路径加载
 file:        file:///data/config.xml           将其作为 URL 对象，从文件系统加载 [1]]
 http:        http://myserver/logo.png          将其作为 URL 对象 加载
 (none)       /data/config.xml                  取决于底层的 ApplicationContext


 # 开发步骤
 * 使用maven 或者gradle 等构建工具添加项目依赖
