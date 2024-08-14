package com.message.center.infrastructure.utils;

import com.message.center.infrastructure.exception.ValidationException;

/**
 * @Author linchengdong
 * @Date 2024-08-12 16:57
 * @PackageName:com.message.center.infrastructure.utils
 * @ClassName: ValidationUtil
 * @Description: 校验工具类
 * @Version 1.0
 */
public class ValidationUtil {

    public static void isTrue(boolean expect,String code,Object... params) {
        if (!expect) {
            throw ValidationException.of(code,code,params);
        }
    }

    public static void isFalse(boolean expect,String code,Object... params) {
        isTrue(!expect,code,params);
    }

}
