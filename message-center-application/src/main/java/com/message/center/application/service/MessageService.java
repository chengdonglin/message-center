package com.message.center.application.service;

import com.message.center.application.dto.command.MessageCmd;
import com.message.center.domain.entity.Client;
import com.message.center.domain.entity.Message;
import com.message.center.domain.repository.ClientReposity;
import com.message.center.domain.repository.MessageRepository;
import com.message.center.domain.valueobject.MqValueObject;
import org.springframework.stereotype.Service;

/**
 * @Author linchengdong
 * @Date 2024-08-09 10:59
 * @PackageName:com.message.center.application.service
 * @ClassName: UserService
 * @Description: TODO
 * @Version 1.0
 */
@Service
public class MessageService {


    private final ApiKeyService apiKeyService;
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository, ClientReposity clientReposity, ApiKeyService apiKeyService) {
        this.messageRepository = messageRepository;
        this.apiKeyService = apiKeyService;
    }


    public String createMessage(MessageCmd.CreateCommand command,String apiKey,String apiToken) {
        Client client = this.apiKeyService.checkApiKey(apiKey, apiToken);
        MqValueObject mqValueObject = new MqValueObject(command.getMqType(), command.getTopic(), command.getRoutingKey(), command.getTag(), command.getSendMq());
        mqValueObject.verify();
        Message message = new Message(command.getId(), command.getBusinessType(), command.getContent(), command.getExpectSendTime(),client,mqValueObject);
        message.verify();
        messageRepository.insertMessage(message);
        //todo 发送成功，发送成功事件
        return message.getId();
    }
}
