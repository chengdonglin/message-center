package com.message.center.application.dto.query;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author linchengdong
 * @Date 2024-08-09 15:09
 * @PackageName:com.message.center.application.dto.query
 * @ClassName: MessageQuery
 * @Description: TODO
 * @Version 1.0
 */
public class MessageQuery {

    @Getter
    @Setter
    public static class IdQuery {
        private String id;
    }


    public static class KeyQuery {
        private String key;
    }

}
