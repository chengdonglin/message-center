package com.message.center.infrastructure.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author linchengdong
 * @Date 2024-08-12 10:51
 * @PackageName:com.message.center.domain.model
 * @ClassName: SingleResponse
 * @Description: TODO
 * @Version 1.0
 */
public class SingleResponse<T> extends Response {

    @Getter
    @Setter
    private T data;

    public SingleResponse() {
    }

    public static <T> SingleResponse<T> of(T data) {
        SingleResponse<T> singleResponse = new SingleResponse();
        singleResponse.setSuccess(true);
        singleResponse.setData(data);
        return singleResponse;
    }


    public static SingleResponse<Void> empty() {
        SingleResponse<Void> singleResponse = new SingleResponse<>();
        singleResponse.setSuccess(true);
        singleResponse.setData(null);
        return singleResponse;
    }
}
