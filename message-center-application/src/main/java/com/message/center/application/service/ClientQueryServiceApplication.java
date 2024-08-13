package com.message.center.application.service;

import com.message.center.domain.entity.Client;
import com.message.center.infrastructure.exception.ParamVerifyException;
import com.message.center.domain.repository.ClientRepository;
import org.springframework.stereotype.Service;

/**
 * @Author linchengdong
 * @Date 2024-08-09 11:27
 * @PackageName:com.message.center.application.service
 * @ClassName: ApiKeyService
 * @Description: 客户端编排服务
 * @Version 1.0
 */
@Service
public class ClientQueryServiceApplication {


    private final ClientRepository clientRepository;

    public ClientQueryServiceApplication(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    /**
     * token 验证
     */
    Client checkApiKey(String apiKey,String token) {
        Client client = clientRepository.loadByApiKey(apiKey);
        if (client == null) {
            throw new ParamVerifyException("apiKey 不存在");
        }
        boolean match = client.match(token);
        if (!match) {
            throw new IllegalArgumentException("密钥非法");
        }
        return client;
    }
}
