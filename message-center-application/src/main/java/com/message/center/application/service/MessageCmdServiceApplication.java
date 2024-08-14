package com.message.center.application.service;

import com.message.center.application.dto.command.MessageCmd;
import com.message.center.application.dto.vo.MessagePushVO;
import com.message.center.domain.entity.Client;
import com.message.center.domain.entity.Message;
import com.message.center.domain.event.MessageSaveEvent;
import com.message.center.domain.port.ClientPort;
import com.message.center.domain.repository.ClientRepository;
import com.message.center.domain.repository.MessageRepository;
import com.message.center.domain.share.event.DomainEventPublisher;
import com.message.center.domain.valueobject.CallbackValueObject;
import com.message.center.domain.valueobject.MqValueObject;
import com.message.center.infrastructure.exception.ValidationException;
import com.message.center.infrastructure.utils.ValidationUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author linchengdong
 * @Date 2024-08-09 10:59
 * @PackageName:com.message.center.application.service
 * @ClassName: UserService
 * @Description: 消息编排服务
 * @Version 1.0
 */
@Service
public class MessageCmdServiceApplication {

        private final MessageRepository messageRepository;

    private final ClientPort clientPort;

    private final DomainEventPublisher publisher;

    public MessageCmdServiceApplication(MessageRepository messageRepository, ClientRepository clientRepository,ClientPort clientPort, DomainEventPublisher publisher) {
        this.messageRepository = messageRepository;
        this.clientPort = clientPort;
        this.publisher = publisher;
    }


    @Transactional(rollbackFor = Exception.class)
    public MessagePushVO createMessage(MessageCmd.CreateCommand command, String apiKey, String apiToken) {
        Client client = this.clientPort.queryClientByApiKey(apiKey);
        if (client == null) {
            throw ValidationException.of("400","can not found apiKey from db",null);
        }
        boolean match = client.match(apiToken);
        ValidationUtil.isTrue(match,"token is error", null);
        MqValueObject mqValueObject = new MqValueObject(command.getMqType(), command.getTopic(), command.getRoutingKey(), command.getTag(),command.getSendMq());
        mqValueObject.verify();
        CallbackValueObject callbackValueObject = new CallbackValueObject(command.getNeedCallback(),command.getCallbackUrl());
        Message message = new Message(command.getBusinessType(), command.getContent(), client, command.getMessageKey(),mqValueObject,callbackValueObject);
        message.verify();
        message.calculateExpectTime(command.getDelaySecond());
        String messageId = messageRepository.insertMessage(message);
        //todo 发送成功，发送成功事件
        publisher.publish(new MessageSaveEvent(this,messageId));
        MessagePushVO vo = new MessagePushVO();
        vo.setMessageId(messageId);
        vo.setMessageKey(command.getMessageKey());
        return vo;
    }

}
