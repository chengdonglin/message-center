package com.message.center.sdk.core.token;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.MD5;

/**
 * @Author linchengdong
 * @Date 2024-08-14 14:55
 * @PackageName:com.message.center.sdk.core.token
 * @ClassName: TokenServiceImpl
 * @Description: TODO
 * @Version 1.0
 */
public class TokenServiceImpl implements TokenService{


    @Override
    public String generate(String apiKey, String secret) {
        return SecureUtil.md5(apiKey  + secret);
    }

}
