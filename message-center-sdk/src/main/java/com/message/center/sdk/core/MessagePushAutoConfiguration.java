package com.message.center.sdk.core;

import com.message.center.sdk.core.token.TokenService;
import com.message.center.sdk.core.token.TokenServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

/**
 * @Author linchengdong
 * @Date 2024-08-14 14:52
 * @PackageName:com.message.center.sdk.core
 * @ClassName: MessagePushAutoConfiguration
 * @Description: TODO
 * @Version 1.0
 */
@Configuration
@EnableConfigurationProperties(MessagePushProperties.class)
public class MessagePushAutoConfiguration {


    @Bean
    @ConditionalOnMissingBean
    public TokenService tokenService() {
        return new TokenServiceImpl();
    }



    @Bean
    @ConditionalOnMissingBean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .requestFactory(HttpComponentsClientHttpRequestFactory.class)
                .setConnectTimeout(Duration.ofMillis(6000))  //设置http的链接超时时间
                .setReadTimeout(Duration.ofMillis(6000)) //设置http的响应超时时间
                .build();
    }

    @Bean
    @ConditionalOnMissingBean
    public MessagePushService messagePushService(MessagePushProperties properties,RestTemplate restTemplate, TokenService tokenService) {
        return new MessagePushServiceImpl(properties,restTemplate,tokenService);
    }
}
