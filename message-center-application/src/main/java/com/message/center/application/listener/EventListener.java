package com.message.center.application.listener;

import com.message.center.domain.event.MessageSaveEvent;
import com.message.center.infrastructure.delay.DelayTaskProcessor;
import com.message.center.infrastructure.repository.mapper.MessageMapper;
import com.message.center.infrastructure.repository.po.MessagePO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @Author linchengdong
 * @Date 2024-08-13 11:32
 * @PackageName:com.message.center.application.listener
 * @ClassName: MessageSaveListener
 * @Description: TODO
 * @Version 1.0
 */
@Component
@Slf4j
public class EventListener {

    private final MessageMapper messageMapper;

    private final DelayTaskProcessor delayTaskProcessor;

    public EventListener(MessageMapper messageMapper, DelayTaskProcessor delayTaskProcessor) {
        this.messageMapper = messageMapper;
        this.delayTaskProcessor = delayTaskProcessor;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void messageSaveEvent(MessageSaveEvent event) {
      log.info("接收到消息保存事件 eventId = :{}, messageId = :{}", event.getEventId(),event.getMessageId());
        MessagePO po = messageMapper.selectById(event.getMessageId());
        if (po.pushDelayTaskQuick()) {
            delayTaskProcessor.put(po.diff(), TimeUnit.SECONDS,() -> {
                log.info("-------开始执行延迟消息-----");
                LocalDateTime actualTime = LocalDateTime.now();
                po.setActualSendTime(actualTime);
                po.setStatus(1);
                messageMapper.updateById(po);
            });
        }
    }
}
