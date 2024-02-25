create table zxsiotcloud.zxs_device
(
    UUID         bigint                              not null comment 'UUID'
        primary key,
    account      bigint                              not null comment '平台账户',
    device_id    bigint                              not null comment '设备Id号',
    name         varchar(64)                         not null comment '设备名称',
    type         varchar(64)                         not null comment '设备类型',
    connect_type varchar(64)                         not null comment '连接协议',
    sn           varchar(64)                         null comment '设备序列号',
    mac          varchar(64)                         null comment '设备mac地址',
    ip           varchar(64)                         null comment '设备ip地址',
    port         int       default 0                 null comment '设备端口',
    status       int       default 0                 not null comment '设备状态(0:正常状态,1:禁用状态)',
    update_time  timestamp default CURRENT_TIMESTAMP not null comment '更新时间',
    create_time  timestamp default CURRENT_TIMESTAMP not null comment '创建时间'
)
    comment '设备表';

