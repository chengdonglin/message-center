package com.message.center.domain.share.event;

import com.message.center.domain.event.DomainEvent;

/**
 * @Author linchengdong
 * @Date 2024-08-13 11:12
 * @PackageName:com.message.center.domain.share.event
 * @ClassName: DomainEventPublisher
 * @Description: TODO
 * @Version 1.0
 */
public interface DomainEventPublisher {

    <T extends DomainEvent> void publish(T event);
}
