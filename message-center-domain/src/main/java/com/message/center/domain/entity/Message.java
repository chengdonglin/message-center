package com.message.center.domain.entity;

import com.message.center.domain.valueobject.CallbackValueObject;
import com.message.center.domain.valueobject.MqValueObject;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * @Author linchengdong
 * @Date 2024-08-08 13:47
 * @PackageName:com.message.center.domain.entity
 * @ClassName: MessageDomain
 * @Description: 消息实体
 * @Version 1.0
 */
@Setter
@Getter
@Slf4j
public class Message {

    private String businessType;

    /**
     * 消息体
     */
    private String content;


    private String key;


    /**
     *  消息实际投递时间
     */
    private LocalDateTime actualSendTime;


    /**
     * 消息状态，0：待投递到mq，1：投递成功，2：投递失败
     */
    private Integer status;


    /**
     * status=0时，记录消息投递失败的原因
     */
    private String failMsg;


    /**
     * 已投递失败次数
     */
    private Integer failCount;


    /**
     * 投递MQ失败了，是否还需要重试？1：是，0：否
     */
    private Integer sendRetry;

    /**
     * 投递失败后，下次重试时间
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


    private Client client;

    private MqValueObject mqValueObject;


    private CallbackValueObject callbackValueObject;

    /**
     * 消息期望投递时间，大于当前时间，则为延迟消息，否则会立即投递
     */
    private LocalDateTime expectSendTime;

    public Message(String businessType, String content,  Client client, MqValueObject mqValueObject,CallbackValueObject callbackValueObject) {
        this.businessType = businessType;
        this.content = content;
        this.client = client;
        this.mqValueObject = mqValueObject;
        this.failMsg = "";
        this.sendRetry = 0;
        this.status = 0;
        this.callbackValueObject = callbackValueObject;
    }


    public void verify() {
        if (!StringUtils.hasText(content)) {
            throw new IllegalArgumentException("content can not be empty");
        }
    }


    public void calculateExpectTime(Integer delaySecond) {
        if(delaySecond < 0) {
            throw new IllegalArgumentException("delaySecond value can not less 0");
        }
        LocalDateTime now = LocalDateTime.now();
        log.info("now :{}",now);
        LocalDateTime dateTime = now.plusSeconds(delaySecond);
        log.info("expectTime :{}",dateTime);
        this.expectSendTime = dateTime;
    }

}
