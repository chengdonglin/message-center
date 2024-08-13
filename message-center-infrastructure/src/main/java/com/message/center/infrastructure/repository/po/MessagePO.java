package com.message.center.infrastructure.repository.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * @Author linchengdong
 * @Date 2024-08-09 15:14
 * @PackageName:com.message.center.infrastructure.repository.po
 * @ClassName: MessagePO
 * @Description: TODO
 * @Version 1.0
 */
@TableName("t_message")
@Getter
@Setter
@Slf4j
public class MessagePO {

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 消息体
     */
    private String content;

    /**
     * 消息期望投递时间，大于当前时间，则为延迟消息，否则会立即投递
     */
    private LocalDateTime expectSendTime;

    /**
     * 消息实际投递时间
     */
    private LocalDateTime actualSendTime;

    /**
     * 消息状态，0：待投递到mq，1：投递成功，2：投递失败
     */
    private Integer status;

    /**
     * status=0时，记录消息投递失败的原因
     */
    private String failMsg;

    /**
     * 已投递失败次数
     */
    private Integer failCount;

    /**
     * 投递MQ失败了，是否还需要重试？1：是，0：否
     */
    private Integer sendRetry;

    /**
     * 投递失败后，下次重试时间
     */
    private LocalDateTime nextRetryTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 创建时间
     */
    private LocalDateTime updateTime;


    private Integer needCallback;


    private String callbackUrl;

    private Integer sendMq;

    private String topic;

    @TableField(value = "message_key")
    private String messageKey;

    private String routingKey;

    private String tag;

    private Long tenantId;

    private String apiKey;


    public boolean pushDelayTaskQuick() {
        if (this.getExpectSendTime().isBefore(this.getCreateTime())) {
            return true;
        }
        long between = diff();
        log.info("时间相差 :{}",between);
        if (between > 120) {
            return false;
        }
        return true;
    }
    public long diff() {
        return Math.abs(ChronoUnit.SECONDS.between(this.getExpectSendTime(), this.getCreateTime()));
    }
}
