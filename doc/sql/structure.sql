create table if not exists zxs_account
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
    comment '账户表' row_format = DYNAMIC;

create index zxs_account_account_create_time_index
    on zxs_account (account, create_time);

create table if not exists zxs_device
(
    UUID         bigint                              not null comment 'UUID'
        primary key,
    account      bigint                              not null comment '平台账户',
    device_id    bigint                              not null comment '设备Id号',
    fingerprint  char(64)                            not null comment '设备指纹',
    name         varchar(64)                         not null comment '设备名称',
    type         varchar(64)                         null comment '设备类型',
    connect_type varchar(64)                         null comment '连接协议',
    sn           varchar(64)                         null comment '设备序列号',
    mac          varchar(64)                         null comment '设备mac地址',
    ip           varchar(64)                         null comment '设备ip地址',
    port         int       default 0                 null comment '设备端口',
    proxy_ip     varchar(12)                         null comment '转发IP',
    proxy_port   int(12)                             null comment '转发端口',
    status       int       default 0                 not null comment '设备状态(0:正常状态,1:禁用状态)',
    update_time  timestamp default CURRENT_TIMESTAMP not null comment '更新时间',
    create_time  timestamp default CURRENT_TIMESTAMP not null comment '创建时间'
)
    comment '设备表' row_format = DYNAMIC;

create table if not exists zxs_instance
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
    comment '实例表' row_format = DYNAMIC;

create table if not exists zxs_user
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
    comment '用户表' row_format = DYNAMIC;

create index zxs_index
    on zxs_user (user_name, user_account)
    comment '用户表索引';


