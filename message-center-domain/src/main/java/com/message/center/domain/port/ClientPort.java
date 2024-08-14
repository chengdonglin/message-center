package com.message.center.domain.port;

import com.message.center.domain.entity.Client;

/**
 * @Author linchengdong
 * @Date 2024-08-14 10:20
 * @PackageName:com.message.center.domain.port
 * @ClassName: ClientPort
 * @Description: TODO
 * @Version 1.0
 */
public interface ClientPort {

    Client queryClientByApiKey(String apiKey);
}
