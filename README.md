# ssm-repository
这是spring mvc+mybatis的手脚架项目  dubbo 作为rpc框架
* web 层添加了shiro 权限控制,需要自定义realm
* web层为motan的client service为motan的服务端
这里使用了zookeeper作为注册中心,60.205.228.78:2181 (一个docker容器 2017-04-26后该主机过期)
如果需修改注册中心地址,请修改下面两个配置文件 <dubbo:registry address属性/>
web模块 /src/main/resources/dubbo_client.xml
service模块 /src/main/resources/dubbo_client.xml

* 添加了redis 消息订阅和发布功能
service 为消息发布者 ,web为消息订阅者 
redis服务器配置在 common项目 com.ouzhx.common.config.RedisConstants.java文件中修改


 # 开发步骤
 * 使用maven 或者gradle 等构建工具添加项目依赖
