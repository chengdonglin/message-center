package com.message.center.infrastructure.port;

import com.message.center.domain.entity.Client;
import com.message.center.domain.port.ClientPort;
import com.message.center.domain.repository.ClientRepository;
import org.springframework.stereotype.Component;

/**
 * @Author linchengdong
 * @Date 2024-08-14 10:22
 * @PackageName:com.message.center.infrastructure.port
 * @ClassName: ClientPort
 * @Description: TODO
 * @Version 1.0
 */
@Component
public class ClientPortImpl implements ClientPort {

    private final ClientRepository clientRepository;

    public ClientPortImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client queryClientByApiKey(String apiKey) {
        Client client = clientRepository.loadByApiKey(apiKey);
        if (client == null) {
            return null;
        }
        return client;
    }
}
