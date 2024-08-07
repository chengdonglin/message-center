package com.ssn.message.center.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author linchengdong
 * @Date 2024-08-07 17:27
 * @PackageName:com.ssn.message.center.entity
 * @ClassName: BaseEntity
 * @Description: TODO
 * @Version 1.0
 */
public class BaseEntity implements Serializable {

    @TableId(type = IdType.AUTO)
    @Setter
    @Getter
    private Long id;
}
