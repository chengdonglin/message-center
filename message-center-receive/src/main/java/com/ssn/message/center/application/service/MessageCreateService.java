package com.ssn.message.center.application.service;

import com.ssn.message.center.application.command.MessagePushCommand;
import org.springframework.stereotype.Service;

/**
 * @Author linchengdong
 * @Date 2024-08-07 18:24
 * @PackageName:com.ssn.message.center.application.service
 * @ClassName: MessageCreateService
 * @Description: TODO
 * @Version 1.0
 */
@Service
public interface MessageCreateService {


    void messageCreate(MessagePushCommand command);

}
