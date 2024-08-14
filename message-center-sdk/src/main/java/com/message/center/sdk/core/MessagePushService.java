package com.message.center.sdk.core;

/**
 * @Author linchengdong
 * @Date 2024-08-14 14:46
 * @PackageName:com.message.center.sdk.core
 * @ClassName: MessagePushService
 * @Description: TODO
 * @Version 1.0
 */
public interface MessagePushService {


    PushResult push(MessagePushDTO messagePushDTO);

}
