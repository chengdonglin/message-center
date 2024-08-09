package com.message.center.infrastructure.repository.impl;

import com.message.center.domain.entity.Message;
import com.message.center.domain.repository.MessageRepository;
import com.message.center.infrastructure.converter.MessageFactory;
import com.message.center.infrastructure.repository.mapper.MessageMapper;
import com.message.center.infrastructure.repository.po.MessagePO;
import org.springframework.stereotype.Component;

/**
 * @Author linchengdong
 * @Date 2024-08-09 17:55
 * @PackageName:com.message.center.infrastructure.repository.impl
 * @ClassName: MessageRepositoryImpl
 * @Description: TODO
 * @Version 1.0
 */
@Component
public class MessageRepositoryImpl implements MessageRepository {

    private final MessageMapper mapper;

    public MessageRepositoryImpl(MessageMapper mapper) {
        this.mapper = mapper;
    }


    @Override
    public String insertMessage(Message message) {
        MessagePO po = MessageFactory.buildPoFromDomain(message);
        mapper.insert(po);
        return po.getId();
    }
}
