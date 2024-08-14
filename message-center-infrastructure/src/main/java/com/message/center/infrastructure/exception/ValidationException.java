package com.message.center.infrastructure.exception;

import lombok.Getter;

/**
 * @Author linchengdong
 * @Date 2024-08-12 16:59
 * @PackageName:com.message.center.infrastructure.exception
 * @ClassName: ValidationException
 * @Description: 校验异常类
 * @Version 1.0
 */
public class ValidationException extends BaseException{


    @Getter
    private Object[] params;

    public ValidationException(String errMs) {
        super(errMs);
    }

    public ValidationException(String message, Object[] params) {
        super(message);
        this.params = params;
    }

    public ValidationException(String code, String message, Object[] params) {
        super(code, message);
        this.params = params;
    }

    public static ValidationException of(String code, Object[] params) {
        return new ValidationException(code, null, params);
    }

    public static ValidationException of(String code, String message, Object[] params) {
        return new ValidationException(code,message,params);
    }
}
