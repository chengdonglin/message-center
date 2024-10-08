

## 延迟消息中台

基于Mysql + Java Delayed + Job + Redis 实现任意时间延迟消息

### 背景

业务系统中经常需要发送可靠性延迟消息,比如订单超时关闭，延迟提醒类的消息等。通常我们会采用以下几种方案来实现：

1. MySQL + job 定时轮询方案

轮询数据库查询需要处理的消息，频繁访问数据，DB数据库压力大

2. RabbitMQ 延时插件

依赖中间件

3. RocketMQ

依赖中间件，延迟时间有限制

依赖中间件实现也一种稳定可靠的方案，但是我们经常存在一种情况，消息的体量并不大，同时不希望引入其他的中间件增加系统的复杂度，同时在给B端部署的时候， 越简单越方便，那么基于我们基于此，我们采用以下方案:

4. MySQL + job 定时轮询 + DelayQueue

方案的优点是不依赖中间件，job 采用2分钟执行一次，每次拉取未来2分钟内需要处理的消息，放到Java DeleyQueue 延时工具类，这样也可以做到实时性高，对DB的压力也不大，后续也可以直接接入MQ，直接实时投递MQ， 具备非常高的扩展性

### 版本迭代

- [x] 完成延迟消息接收触发功能
- [ ] 用户 + 密钥管理
- [ ] 消息列表管理
- [ ] topic 管理

### 工程结构

| 工程名                           | 说明                                                                                                                                 |
|-------------------------------|------------------------------------------------------------------------------------------------------------------------------------|
| message-center-interfaces     | 用户接口层 ，主要存放用户接口层与前端交互、展现数据相关的代码，向应用服务获取展现所需的数据。这一层主要用来处理用户发送的Restful请求，解析用户输入的配置文件，并将数据传递给Application层。数据的组装、数据传输格式以及Facade接口等代码都会放在这一层目录里。 |
| message-center-application    | 它主要存放应用层服务组合和编排相关的代码。应用服务向下基于微服务内的领域服务或外部微服务的应用服务完成服务的编排和组合，向上为用户接口层提供各种应用数据展现支持服务。应用服务和事件等代码会放在这一层目录里。                            |                                                                                                                     |
| message-center-domain         | 领域层核心业务逻辑相关的代码。领域层可以包含多个聚合代码包，它们共同实现领域模型的核心业务逻                                                                                     |
| message-center-Infrastructure | 它主要存放基础资源服务相关的代码，为其它各层提供的通用技术能力、三方软件包、数据库服务、配置和基础资源服务的代码都会放在这一层目录里                                                                 |
| message-center-boot           | 服务启动层                                                                                                                              |
 | message-center-sdk| 快速接入消息延时消息中台starter组件|
| message-center-demo| 接入工程测试|


### 依赖关系说明

- interfaces 依赖 application
- application 依赖 domain 以及 infrastructure
- infrastructure 依赖domain， 实现domain的repository
- domain 不依赖任何


### 技术栈

| 技术| 版本| 说明|
|----|----|----|
| spring-boot| 2.7.15| springboot基础框架|
| mybatis-plus| 3.5.5 | 数据库操作|
| redis | 6.0.0 | 缓存数据库|


### 安装步骤

#### 采用docker方式
```bash
chmod +777 build.sh
./build.sh

docker run -d -p 9999:9999 --name center-message-boot:1.0 center-message-boot:1.0
```



### 接入手册

1. clone 工程下来
2. 将message-center-sdk 安装到自己的仓库
3. 添加maven依赖

```yaml
        <dependency>
            <groupId>com.ssn.message.center</groupId>
            <artifactId>message-center-sdk</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
```

4. application.yml 配置相关属性

```yaml
message:
  center:
    api-key: xxx
    secret: xxx
    url: http://127.0.0.1:9999/message/push
```

5. 使用

```java

    @Autowired
    private MessagePushService messagePushService;

    @Test
    public void test() {
        MessagePushDTO dto = new MessagePushDTO();
        dto.setBusinessType("demo");
        dto.setContent("demo");
        dto.setTag("demoTag");
        dto.setMqType("kafka");
        dto.setTopic("demo");
        dto.setCallbackUrl("http:127.0.0.1:8000/demo/callback");
        dto.setDelaySecond(10);
        dto.setNeedCallback(true);
        dto.setRoutingKey("demo");
        dto.setSendRetry(1);
        dto.setSendMq(1);
        dto.setMessageKey(UUID.randomUUID().toString());
        PushResult push = messagePushService.push(dto);
        log.info("push id {}",push.getMessageId());
    }

```

6. 参考message-center-demo 工程