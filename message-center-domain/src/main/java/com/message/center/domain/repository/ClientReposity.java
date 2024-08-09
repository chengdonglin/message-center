package com.message.center.domain.repository;

import com.message.center.domain.entity.Client;

/**
 * @Author linchengdong
 * @Date 2024-08-09 11:22
 * @PackageName:com.message.center.domain.repository
 * @ClassName: ClientReposity
 * @Description: TODO
 * @Version 1.0
 */
public interface ClientReposity {

    Client loadByApiKey(String apiKey);
}
