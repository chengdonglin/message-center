package com.message.center.application.dto.command;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @Author linchengdong
 * @Date 2024-08-08 15:34
 * @PackageName:com.message.center.application.dto.command
 * @ClassName: MsgCmd
 * @Description: TODO
 * @Version 1.0
 */
public class MessageCmd {


    @Getter
    @Setter
    public static class CreateCommand {

        private String id;

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
}
