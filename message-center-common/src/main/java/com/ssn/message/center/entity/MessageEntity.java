package com.ssn.message.center.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author linchengdong
 * @Date 2024-08-07 17:23
 * @PackageName:com.ssn.message.center.infrastructure.entity
 * @ClassName: MessageEntity
 * @Description: TODO
 * @Version 1.0
 */
@TableName("t_msg")
@Data
@Builder
public class MessageEntity{

    /**
     * 消息id
     */
    private String id;

    /**
     * 业务类型
     */
    private String businessType;

    /**
     * 主题， rabbitmq 等同于exchange
     */
    private String topic;


    /**
     * 路由key
     */
    private String routingKey;

    /**
     * 消息体
     */
    private String content;


    /**
     * 消息期望投递时间，大于当前时间，则为延时消息，否则会立即投递
     */
    private LocalDateTime expectSendTime;


    /**
     * 消息实际发送的时间
     */
    private LocalDateTime actualSendTime;

    /**
     * 消息状态， 0-待投递MQ，1-成功，2-失败
     */
    private Integer status;

    /**
     * 是否投递到mq
     */
    private Integer sendMq;

    /**
     * mq 类型，rocketmq, rabbitmq, kafka
     */
    private String mqType;

    /**
     * 投递失败，回调失败的原因
     */
    private String failMsg;


    /**
     * 投递失败次数
     */
    private Integer failCount;

    /**
     * 是否需要重试， 1-是，0-否
     */
    private Integer sendRetry;


    /**
     * 下次重试的时间
     */
    private LocalDateTime nextRetryTime;


    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


    /**
     * 租户id
     */
    private Long tenantId;

    /**
     * 回调地址
     */
    private String callbackUrl;
}
