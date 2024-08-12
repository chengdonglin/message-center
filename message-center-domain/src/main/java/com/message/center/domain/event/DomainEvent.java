package com.message.center.domain.event;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

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
public abstract class DomainEvent {

    private String eventId;

    private Date eventTime;
}
