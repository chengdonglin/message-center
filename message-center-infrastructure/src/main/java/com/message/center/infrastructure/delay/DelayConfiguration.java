package com.message.center.infrastructure.delay;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @Author linchengdong
 * @Date 2024-08-12 11:47
 * @PackageName:com.message.center.infrastructure.delay
 * @ClassName: DelayConfiguration
 * @Description: TODO
 * @Version 1.0
 */
@Configuration
public class DelayConfiguration {

    private final DelayProperties delayProperties;

    public DelayConfiguration(DelayProperties delayProperties) {
        this.delayProperties = delayProperties;
    }

    @Bean
    public DelayTaskProcessor delayMsgProcessor() {
        return new DelayTaskProcessor("delayMsgProcessor",delayProperties.getCapacity(), delayProperties.getConcurrency());
    }

}
