package com.message.center.domain.entity;

import cn.hutool.crypto.SecureUtil;
import com.message.center.infrastructure.constants.SystemConstants;
import com.message.center.infrastructure.exception.ParamVerifyException;
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
public class Client implements Serializable {

    private Long id;

    private String apiKey;

    private String apiSecret;

    private Long tenantId;

    public Client() {

    }


    public void match(String token) {
        String dbToken = SecureUtil.md5(this.apiKey + SystemConstants.KEY + this.apiSecret);
        if (dbToken.equals(token)) {
            return;
        }
        throw new ParamVerifyException("无效的token");
    }
}
