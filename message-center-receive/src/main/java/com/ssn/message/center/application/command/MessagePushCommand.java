package com.ssn.message.center.application.command;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author linchengdong
 * @Date 2024-08-07 18:05
 * @PackageName:com.ssn.message.center.application.command
 * @ClassName: MessagePushCommand
 * @Description: 客户端请求对象
 * @Version 1.0
 */
@Data
@Builder
public class MessagePushCommand {


    private String id;

    private String businessType;

    private String topic;

    private String routingKey;

    private String content;

    private LocalDateTime expectSendTime;

    private LocalDateTime actualSendTime;

    private Integer sendRetry;

    private String callbackUrl;

    @Data
    @Builder
    public static class MqSetting  {
        private Integer sendMq;
        private String mqType;
    }

}
