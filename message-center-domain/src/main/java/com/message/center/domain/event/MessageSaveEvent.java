package com.message.center.domain.event;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author linchengdong
 * @Date 2024-08-13 11:09
 * @PackageName:com.message.center.domain.event
 * @ClassName: MessageSaveEvent
 * @Description: TODO
 * @Version 1.0
 */
public class MessageSaveEvent extends DomainEvent{

    @Getter
    @Setter
    private String messageId;

    public MessageSaveEvent(Object source,String messageId) {
        super(source);
        this.messageId = messageId;
    }

}
