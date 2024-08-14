package com.message.center.sdk.core.token;

/**
 * @Author linchengdong
 * @Date 2024-08-14 14:55
 * @PackageName:com.message.center.sdk.core.token
 * @ClassName: TokenService
 * @Description: TODO
 * @Version 1.0
 */
public interface TokenService {

    String generate(String apiKey,String secret);

}
