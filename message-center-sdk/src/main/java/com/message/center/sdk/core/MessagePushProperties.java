package com.message.center.sdk.core;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author linchengdong
 * @Date 2024-08-14 14:52
 * @PackageName:com.message.center.sdk.core
 * @ClassName: MessagePushProperties
 * @Description: TODO
 * @Version 1.0
 */
@ConfigurationProperties(prefix = "message.center")
@Data
public class MessagePushProperties {

    private String url;

    private String apiKey;

    private String secret;
}
