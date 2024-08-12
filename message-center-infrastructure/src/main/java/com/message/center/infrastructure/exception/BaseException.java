package com.message.center.infrastructure.exception;

/**
 * @Author linchengdong
 * @Date 2024-08-08 14:43
 * @PackageName:com.message.center.infrastructure.exception
 * @ClassName: BaseException
 * @Description: TODO
 * @Version 1.0
 */
public class BaseException extends RuntimeException{

    private String code;

    public BaseException(String errMs) {
        super(errMs);
        this.code = "defaultError";
    }

    public BaseException(String code,String errMs) {
        super(errMs);
        this.code = code;
    }
}
