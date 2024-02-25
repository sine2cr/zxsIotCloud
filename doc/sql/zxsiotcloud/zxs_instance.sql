create table zxsiotcloud.zxs_instance
(
    UUID          bigint                              not null comment 'UUID'
        primary key,
    account       bigint                              not null comment '平台账户',
    instance_id   bigint                              not null comment '实例id',
    instance_name varchar(64)                         not null comment '实例名称',
    status        int       default 0                 not null comment '状态(0:正常状态,1:禁用状态)',
    update_time   timestamp default CURRENT_TIMESTAMP not null comment '更新时间',
    create_time   timestamp default CURRENT_TIMESTAMP not null comment '创建时间'
)
    comment '实例表';

