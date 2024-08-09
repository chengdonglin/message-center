package com.message.center.domain.exception;

/**
 * @Author linchengdong
 * @Date 2024-08-08 14:46
 * @PackageName:com.message.center.infrastructure.exception
 * @ClassName: ParamVerifyException
 * @Description: TODO
 * @Version 1.0
 */
public class ParamVerifyException extends BaseException{

    public ParamVerifyException(String errMs) {
        super(errMs);
    }
}
