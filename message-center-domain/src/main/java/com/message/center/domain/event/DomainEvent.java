package com.message.center.domain.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.util.Date;
import java.util.UUID;

/**
 * @Author linchengdong
 * @Date 2024-08-12 11:13
 * @PackageName:com.message.center.domain.event
 * @ClassName: DomainEvent
 * @Description: TODO
 * @Version 1.0
 */
@Getter
@Setter
public abstract class DomainEvent extends ApplicationEvent {

    private String eventId;

    private Date eventTime;

    public DomainEvent(Object source) {
        super(source);
        this.eventId = UUID.randomUUID().toString();
        this.eventTime = new Date();
    }
}
