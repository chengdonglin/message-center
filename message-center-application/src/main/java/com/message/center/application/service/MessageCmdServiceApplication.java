package com.message.center.application.service;

import com.message.center.application.dto.command.MessageCmd;
import com.message.center.domain.entity.Client;
import com.message.center.domain.entity.Message;
import com.message.center.domain.event.MessageSaveEvent;
import com.message.center.domain.repository.ClientRepository;
import com.message.center.domain.repository.MessageRepository;
import com.message.center.domain.share.event.DomainEventPublisher;
import com.message.center.domain.valueobject.CallbackValueObject;
import com.message.center.domain.valueobject.MqValueObject;
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


    private final ClientQueryServiceApplication clientQueryServiceApplication;
    private final MessageRepository messageRepository;

    private final DomainEventPublisher publisher;

    public MessageCmdServiceApplication(MessageRepository messageRepository, ClientRepository clientRepository, ClientQueryServiceApplication apiKeyService, DomainEventPublisher publisher) {
        this.messageRepository = messageRepository;
        this.clientQueryServiceApplication = apiKeyService;
        this.publisher = publisher;
    }


    @Transactional(rollbackFor = Exception.class)
    public String createMessage(MessageCmd.CreateCommand command,String apiKey,String apiToken) {
        Client client = this.clientQueryServiceApplication.checkApiKey(apiKey, apiToken);
        MqValueObject mqValueObject = new MqValueObject(command.getMqType(), command.getTopic(), command.getRoutingKey(), command.getTag(),command.getSendMq());
        mqValueObject.verify();
        CallbackValueObject callbackValueObject = new CallbackValueObject(command.getNeedCallback(),command.getCallbackUrl());
        Message message = new Message(command.getBusinessType(), command.getContent(),client,mqValueObject,callbackValueObject);
        message.verify();
        message.calculateExpectTime(command.getDelaySecond());
        String messageId = messageRepository.insertMessage(message);
        //todo 发送成功，发送成功事件
        publisher.publish(new MessageSaveEvent(this,messageId));
        return messageId;
    }

}