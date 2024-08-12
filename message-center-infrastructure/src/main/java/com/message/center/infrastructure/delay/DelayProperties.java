package com.message.center.infrastructure.delay;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author linchengdong
 * @Date 2024-08-12 11:51
 * @PackageName:com.message.center.infrastructure.delay
 * @ClassName: DelayProperties
 * @Description: TODO
 * @Version 1.0
 */
@ConfigurationProperties(prefix = "delay")
@Component
@Data
public class DelayProperties {

    private Integer capacity;

    private String name;

    private Integer concurrency;
}
