create table zxsiotcloud.zxs_user
(
    UUID          bigint                              not null comment 'UUID'
        primary key,
    user_name     varchar(32)                         not null comment '用户名',
    user_password varchar(64)                         not null comment '用户密码',
    user_account  bigint                              not null comment '平台账户',
    user_status   int       default 0                 not null comment '当前状态(0:正常状态,1:禁用状态)',
    is_manager    int       default 0                 not null comment '是否为管理员账户(0:普通用户,1:管理员)',
    is_delete     int       default 0                 not null comment '是否删除(0:正常,1:删除)',
    update_time   timestamp default CURRENT_TIMESTAMP not null comment '更新时间',
    create_time   timestamp default CURRENT_TIMESTAMP not null comment '创建时间'
)
    comment '用户表';

create index zxs_index
    on zxsiotcloud.zxs_user (user_name, user_account)
    comment '用户表索引';

