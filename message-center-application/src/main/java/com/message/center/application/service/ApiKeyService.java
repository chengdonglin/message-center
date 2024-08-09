package com.message.center.application.service;

import com.message.center.domain.entity.Client;
import com.message.center.domain.repository.ClientReposity;
import com.message.center.infrastructure.exception.ParamVerifyException;
import org.springframework.stereotype.Service;

/**
 * @Author linchengdong
 * @Date 2024-08-09 11:27
 * @PackageName:com.message.center.application.service
 * @ClassName: ApiKeyService
 * @Description: TODO
 * @Version 1.0
 */
@Service
public class ApiKeyService {


    private final ClientReposity clientReposity;

    public ApiKeyService(ClientReposity clientReposity) {
        this.clientReposity = clientReposity;
    }

    /**
     * token 验证
     */
    Client checkApiKey(String apiKey,String token) {
        Client client = clientReposity.loadByApiKey(apiKey);
        if (client == null) {
            throw new ParamVerifyException("apiKey 不存在");
        }
        client.match(token);
        return client;
    }
}
