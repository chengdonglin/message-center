package com.message.center.domain.valueobject;

import lombok.Getter;
import org.springframework.util.StringUtils;

/**
 * @Author linchengdong
 * @Date 2024-08-08 14:52
 * @PackageName:com.message.center.domain.valueobject
 * @ClassName: CallbackValueObject
 * @Description: TODO
 * @Version 1.0
 */
@Getter
public class CallbackValueObject {

    // 是否需要返回callback
    private final Boolean needCallback;

    // callback地址
    private final String callbackUrl;

    public CallbackValueObject(Boolean needCallback, String callbackUrl) {
        this.needCallback = needCallback;
        this.callbackUrl = callbackUrl;
    }

    public void verify() {
        if (needCallback) {
            if (!StringUtils.hasText(callbackUrl)) {
                throw new IllegalArgumentException("setting send callback callbackUrl can not be empty");
            }
        }
    }
}
