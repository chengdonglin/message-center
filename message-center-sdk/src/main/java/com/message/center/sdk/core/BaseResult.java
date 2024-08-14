package com.message.center.sdk.core;

import lombok.Data;

/**
 * @Author linchengdong
 * @Date 2024-08-14 16:00
 * @PackageName:com.message.center.sdk.core
 * @ClassName: BaseResult
 * @Description: TODO
 * @Version 1.0
 */
@Data
public class BaseResult<T> {

    private Boolean success;

    private String code;

    private String message;

    private T data;
}
