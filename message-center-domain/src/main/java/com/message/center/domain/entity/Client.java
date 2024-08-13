package com.message.center.domain.entity;

import cn.hutool.crypto.SecureUtil;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author linchengdong
 * @Date 2024-08-08 14:33
 * @PackageName:com.message.center.domain.entity
 * @ClassName: ClientInfo
 * @Description: 客户实体
 * @Version 1.0
 */
@Getter
@Setter
public class Client implements Serializable {

    private Long id;

    private String apiKey;

    private String apiSecret;

    private Long tenantId;

    private Integer status;

    public Client() {

    }


    public boolean match(String token) {
        String dbToken = SecureUtil.md5(this.apiKey  + this.apiSecret);
        if (dbToken.equals(token)) {
            return true;
        }
        return false;
    }
}
