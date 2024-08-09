package com.message.center.domain.entity;

import com.message.center.domain.exception.ParamVerifyException;
import com.message.center.domain.valueobject.CallbackValueObject;
import com.message.center.domain.valueobject.MqValueObject;
import lombok.Getter;
import lombok.Setter;
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
public class Message {

    private String id;

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

    public Message(String id, String businessType, String content,  Client client, MqValueObject mqValueObject,CallbackValueObject callbackValueObject) {
        this.id = id;
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
        if (!StringUtils.hasText(id)) {
            throw new ParamVerifyException("id can not be empty");
        }
        if (!StringUtils.hasText(content)) {
            throw new ParamVerifyException("content can not be empty");
        }
    }


    public void calculateExpectTime(Integer delaySecond) {
        if(delaySecond < 0) {
            throw new ParamVerifyException("delaySecond value can not less 0");
        }
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime dateTime = now.plus(delaySecond, ChronoUnit.SECONDS);
        this.expectSendTime = dateTime;
    }

}
