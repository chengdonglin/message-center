package com.message.center.domain.repository;

import com.message.center.domain.entity.Message;

/**
 * @Author linchengdong
 * @Date 2024-08-08 15:21
 * @PackageName:com.message.center.domain.repository
 * @ClassName: MessageRepository
 * @Description: TODO
 * @Version 1.0
 */
public interface MessageRepository {


    String insertMessage(Message message);

    
}
