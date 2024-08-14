package com.message.center.sdk.core;

import lombok.Data;

/**
 * @Author linchengdong
 * @Date 2024-08-14 14:47
 * @PackageName:com.message.center.sdk.core
 * @ClassName: MessagePushDTO
 * @Description: TODO
 * @Version 1.0
 */
@Data
public class MessagePushDTO {

    private String businessType;

    private String content;

    private Integer delaySecond;

    // 投递失败了，是否还需要重试？1：是，0：否
    private Integer sendRetry;

    private String  topic;

    private String routingKey;

    private String tag;

    private Integer sendMq;

    private String callbackUrl;

    private Boolean needCallback;

    private String mqType;

}
