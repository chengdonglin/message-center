package com.message.center.interfaces.controller;

import com.message.center.application.dto.command.MessageCmd;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class MessageController {

    @PostMapping("push")
    public void push(@RequestBody  MessageCmd.CreateCommand createCommand) {

    }
}
