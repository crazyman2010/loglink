# 用途
log4j2微服务请求日志连接器

当请求从网关进来时， 可能会经过多个微服务， 每个服务都打印自己的日志， 我们无法单独查看一个请求的所有日志，
所以开发了这个功能: 当一个请求到来时分配一个请求ID，这个ID会打印到请求的每条日志中，只要搜索请求ID就能找到这个请求的所有日志了。

除了微服务，单应用也是可以使用的。

服务穿透方面目前仅支持 普通应用，spring-web和dubbo， 后续会添加其他模块。

暂时不支持spring-dev-tool。

# 使用方法
- 添加loglink-core依赖
- log4j配置文件中PatternLayout增加占位符RequestId， 例如
   ```xml 
      <PatternLayout pattern="[%RequestId] %d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"/>
   ```
- 增加运行时参数： 
   ```sbtshell
    -DLog4jLogEventFactory=com.aykj.loglink.RequestIdLogEventFactory
    ```
    
- spring-web（上级通过http调用下级，并通过servlet过滤器）
   
   上级和下级服务需要导入loglink-spring-web模块
   
- dubbo (上级通过dubbo调用下级)
    
    上级和下级服务需要导入loglink-dubbo模块
    
    配置dubbo过滤器:
    ``` 
    dubbo.provider.filter=dubboProviderLogRequestIdFilter
    dubbo.consumer.filter=dubboConsumerLogRequestIdFilter
    ```
