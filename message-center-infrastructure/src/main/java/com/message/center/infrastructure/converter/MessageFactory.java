package com.message.center.infrastructure.converter;

import com.message.center.domain.entity.Message;
import com.message.center.infrastructure.repository.po.MessagePO;

import java.time.LocalDateTime;

/**
 * @Author linchengdong
 * @Date 2024-08-09 17:56
 * @PackageName:com.message.center.infrastructure.converter
 * @ClassName: MessageFactory
 * @Description: TODO
 * @Version 1.0
 */
public class MessageFactory {

    public static MessagePO buildPoFromDomain(Message message) {
        MessagePO po = new MessagePO();
        po.setContent(message.getContent());
        po.setMessageKey(message.getKey());
        po.setApiKey(message.getClient().getApiKey());
        po.setTenantId(message.getClient().getTenantId());
        po.setCallbackUrl(message.getCallbackValueObject().getCallbackUrl());
        po.setCreateTime(LocalDateTime.now());
        po.setUpdateTime(LocalDateTime.now());
        po.setFailMsg("");
        po.setTag(message.getMqValueObject().getTag());
        po.setFailCount(0);
        po.setStatus(0);
        po.setSendMq(message.getMqValueObject().getSendMq());
        po.setExpectSendTime(message.getExpectSendTime());
        po.setRoutingKey(message.getMqValueObject().getRoutingKey());
        po.setSendRetry(1);
        po.setTopic(message.getMqValueObject().getTopic());
        po.setMessageKey(message.getKey());
        po.setMessageKey(message.getKey());
        return po;
    }
}
