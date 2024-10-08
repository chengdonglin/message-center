package com.message.center.domain.valueobject;

import lombok.Getter;
import org.springframework.util.StringUtils;

/**
 * @Author linchengdong
 * @Date 2024-08-08 14:37
 * @PackageName:com.message.center.domain.entity
 * @ClassName: MqDomain
 * @Description: TODO
 * @Version 1.0
 */
@Getter
public class MqValueObject {

    // mq 类型
    private final String mqType;

    private final String topic;

    private final String routingKey;

    private final String tag;

    private final Integer sendMq;

    public MqValueObject(String mqType, String topic, String routingKey, String tag, Integer sendMq) {
        this.mqType = mqType;
        this.topic = topic;
        this.routingKey = routingKey;
        this.tag = tag;
        this.sendMq = sendMq;
    }

    public void verify() {
        if (sendMq.equals(1)) {
            if (!StringUtils.hasText(topic)) {
                throw new IllegalArgumentException("send mq topic can not be empty");
            }
            if (!StringUtils.hasText(routingKey)) {
                throw new IllegalArgumentException("send mq routingKey can not be empty");
            }
            if (!StringUtils.hasText(tag)) {
                throw new IllegalArgumentException("send mq tag can not be empty");
            }
        }
    }
}
