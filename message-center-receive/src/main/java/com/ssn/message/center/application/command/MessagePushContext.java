package com.ssn.message.center.application.command;

import lombok.Builder;
import lombok.Data;

/**
 * @Author linchengdong
 * @Date 2024-08-07 18:09
 * @PackageName:com.ssn.message.center.application.command
 * @ClassName: MessagePushContext
 * @Description: TODO
 * @Version 1.0
 */
@Data
@Builder
public class MessagePushContext {

    private MessagePushCommand messagePushCommand;

    private TenantInfo tenantInfo;

    private AuthSession session;

    @Data
    public static class TenantInfo {
        private Long tenantId;
    }


    @Data
    @Builder
    public static class AuthSession {

        private String key;

        private String secret;
    }

}
