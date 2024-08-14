
create database message_center;

use message_center;

create table message_center.t_client
(
    api_key    varchar(64)  not null comment 'API 密钥，作为主键'
        primary key,
    api_secret varchar(128) null comment 'API 密钥的密文',
    status     int          null comment '客户端状态',
    tenant_id  bigint       null comment '租户ID'
)
    comment '客户端信息表' charset = utf8mb4;

create table message_center.t_message
(
    id               varchar(64)  not null comment '主键'
        primary key,
    content          varchar(255) null comment '消息体',
    expect_send_time datetime     null comment '消息期望投递时间，大于当前时间，则为延迟消息，否则会立即投递',
    actual_send_time datetime     null comment '消息实际投递时间',
    status           int          null comment '消息状态，0：待投递到mq，1：投递成功，2：投递失败',
    fail_msg         varchar(255) null comment 'status=0时，记录消息投递失败的原因',
    fail_count       int          null comment '已投递失败次数',
    send_retry       int          null comment '投递MQ失败了，是否还需要重试？1：是，0：否',
    next_retry_time  datetime     null comment '投递失败后，下次重试时间',
    create_time      datetime     null comment '创建时间',
    update_time      datetime     null comment '更新时间',
    need_callback    int          null comment '是否需要回调',
    callback_url     varchar(255) null comment '回调地址',
    send_mq          int          null comment '是否发送MQ',
    topic            varchar(255) null comment '主题',
    message_key      varchar(255) null comment '消息key',
    routing_key      varchar(255) null comment '路由key',
    tag              varchar(255) null comment '标签',
    tenant_id        bigint       null comment '租户id',
    api_key          varchar(255) null comment 'key 查询'
)
    comment '消息表' charset = utf8mb4;



INSERT INTO message_center.t_client (api_key, api_secret, status, tenant_id) VALUES ('xxx', 'xxx', 1, 1);
