package com.message.center.infrastructure.repository.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author linchengdong
 * @Date 2024-08-09 17:39
 * @PackageName:com.message.center.infrastructure.repository.po
 * @ClassName: ClientPO
 * @Description: TODO
 * @Version 1.0
 */
@TableName("t_client")
@Getter
@Setter
public class ClientPO {

    @TableId
    private String apiKey;

    private String apiSecret;

    private Integer status;

    private Long tenantId;
}
