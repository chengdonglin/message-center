package com.message.center.interfaces.controller;

import com.message.center.application.dto.command.MessageCmd;
import com.message.center.application.service.MessageCmdServiceApplication;
import com.message.center.infrastructure.model.SingleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @Author linchengdong
 * @Date 2024-08-09 10:56
 * @PackageName:com.message.center.interfaces.controller
 * @ClassName: MesageController
 * @Description: TODO
 * @Version 1.0
 */
@RestController
@RequestMapping("message")
@RequiredArgsConstructor
public class MessageController {

    private final MessageCmdServiceApplication messageCmdServiceApplication;

    @PostMapping("push")
    public SingleResponse<String> push(@RequestBody  MessageCmd.CreateCommand createCommand, @RequestHeader(value = "apiKey")String apiKey, @RequestHeader(value = "token")String token) {
        String message = messageCmdServiceApplication.createMessage(createCommand, apiKey, token);
        return SingleResponse.of(message);
    }


}
