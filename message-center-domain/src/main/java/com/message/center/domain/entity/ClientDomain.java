package com.message.center.domain.entity;

import lombok.Getter;

import java.io.Serializable;

/**
 * @Author linchengdong
 * @Date 2024-08-08 14:33
 * @PackageName:com.message.center.domain.entity
 * @ClassName: ClientInfo
 * @Description: TODO
 * @Version 1.0
 */
@Getter
public class ClientDomain implements Serializable {

    private final Long id;

    private final String apiKey;

    private final String apiSecret;

    public ClientDomain(Long id, String apiKey, String apiSecret) {
        this.id = id;
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
    }


    // 从 客户端的reposity加载
    public void loadFromDb() {

    }

}
