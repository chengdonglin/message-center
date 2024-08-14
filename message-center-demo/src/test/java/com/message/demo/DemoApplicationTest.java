package com.message.demo;

import com.message.center.sdk.core.MessagePushDTO;
import com.message.center.sdk.core.MessagePushService;
import com.message.center.sdk.core.PushResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

/**
 * @Author linchengdong
 * @Date 2024-08-14 15:30
 * @PackageName:com.message.demo
 * @ClassName: DemoApplicationTest
 * @Description: TODO
 * @Version 1.0
 */
@SpringBootTest
@Slf4j
public class DemoApplicationTest {


    @Autowired
    private MessagePushService messagePushService;

    @Test
    public void test() {
        MessagePushDTO dto = new MessagePushDTO();
        dto.setBusinessType("demo");
        dto.setContent("demo");
        dto.setTag("demoTag");
        dto.setMqType("kafka");
        dto.setTopic("demo");
        dto.setCallbackUrl("http:127.0.0.1:8000/demo/callback");
        dto.setDelaySecond(10);
        dto.setNeedCallback(true);
        dto.setRoutingKey("demo");
        dto.setSendRetry(1);
        dto.setSendMq(1);
        dto.setMessageKey(UUID.randomUUID().toString());
        PushResult push = messagePushService.push(dto);
        log.info("push id {}",push.getMessageId());
    }

}
