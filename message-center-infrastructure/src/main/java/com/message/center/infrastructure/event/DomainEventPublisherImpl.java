package com.message.center.infrastructure.event;

import cn.hutool.json.JSONUtil;
import com.message.center.domain.event.DomainEvent;
import com.message.center.domain.share.event.DomainEventPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * @Author linchengdong
 * @Date 2024-08-13 11:15
 * @PackageName:com.message.center.infrastructure.event
 * @ClassName: DomainEventPublisherImpl
 * @Description: TODO
 * @Version 1.0
 */
@Component
@Slf4j
public class DomainEventPublisherImpl implements DomainEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public DomainEventPublisherImpl(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public <T extends DomainEvent> void publish(T event) {
      log.info("发布时间,event :{}", JSONUtil.toJsonStr(event));
      applicationEventPublisher.publishEvent(event);
    }
}
