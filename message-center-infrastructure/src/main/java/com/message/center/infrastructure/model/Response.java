package com.message.center.infrastructure.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author linchengdong
 * @Date 2024-08-12 10:48
 * @PackageName:com.message.center.domain.model
 * @ClassName: Response
 * @Description: TODO
 * @Version 1.0
 */
public class Response implements Serializable {

    @Getter
    @Setter
    private boolean success;

    @Getter
    @Setter
    private String code;

    @Getter
    @Setter
    private String message;

    public Response() {
    }

    public static Response buildFailure(String code,String message) {
        Response response = new Response();
        response.setSuccess(false);
        response.setCode(code);
        response.setMessage(message);
        return response;
    }

    public static Response buildSuccess() {
        Response response = new Response();
        response.setSuccess(true);
        response.setCode("0");
        response.setMessage("success");
        return response;
    }
}
