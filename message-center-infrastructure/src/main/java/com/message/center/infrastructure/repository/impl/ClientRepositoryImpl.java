package com.message.center.infrastructure.repository.impl;

import com.message.center.domain.entity.Client;
import com.message.center.domain.repository.ClientRepository;
import com.message.center.infrastructure.converter.ClientFactory;
import com.message.center.infrastructure.repository.mapper.ClientMapper;
import com.message.center.infrastructure.repository.po.ClientPO;
import org.springframework.stereotype.Component;

/**
 * @Author linchengdong
 * @Date 2024-08-09 17:38
 * @PackageName:com.message.center.infrastructure.repository.impl
 * @ClassName: ClientRepository
 * @Description: TODO
 * @Version 1.0
 */
@Component
public class ClientRepositoryImpl implements ClientRepository {

    private final ClientMapper clientMapper;

    public ClientRepositoryImpl(ClientMapper clientMapper) {
        this.clientMapper = clientMapper;
    }

    @Override
    public Client loadByApiKey(String apiKey) {
        ClientPO clientPO = clientMapper.selectById(apiKey);
        return ClientFactory.buildClientFromDb(clientPO);
    }
}
