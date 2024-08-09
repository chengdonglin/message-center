package com.message.center.infrastructure.converter;

import com.message.center.domain.entity.Client;
import com.message.center.infrastructure.repository.po.ClientPO;

/**
 * @Author linchengdong
 * @Date 2024-08-09 17:49
 * @PackageName:com.message.center.infrastructure.converter
 * @ClassName: ClientFactory
 * @Description: TODO
 * @Version 1.0
 */
public class ClientFactory {


    public static Client buildClientFromDb(ClientPO po) {
        Client client = new Client();
        client.setId(client.getId());
        client.setApiSecret(po.getApiSecret());
        client.setApiKey(po.getApiKey());
        client.setStatus(po.getStatus());
        return client;
    }
}
