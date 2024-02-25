create table zxsiotcloud.zxs_account
(
    UUID           bigint                              not null comment 'UUID'
        primary key,
    account        bigint                              not null comment '平台账户',
    instance_count int       default 0                 not null comment '实例数量',
    device_count   int       default 0                 not null comment '设备数量',
    status         int       default 0                 not null comment '当前状态(0:正常状态,1:禁用状态)',
    is_delete      int       default 0                 not null comment '是否删除(0:正常,1:删除)',
    update_time    timestamp default CURRENT_TIMESTAMP not null comment '更新时间',
    create_time    timestamp default CURRENT_TIMESTAMP not null comment '创建时间'
)
    comment '账户表';

create index zxs_account_account_create_time_index
    on zxsiotcloud.zxs_account (account, create_time);

