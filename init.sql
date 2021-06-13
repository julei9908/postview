drop table if exists `sys_company`;
create table `sys_company`(
	`company_id` int unsigned not null auto_increment comment '公司id',
	`company_name` varchar(30) comment '公司名称',
	`create_time` datetime comment '创建时间',
	`update_time` timestamp on update current_timestamp comment '修改时间',
	primary key (`company_id`)
) comment '公司' auto_increment=10000;

drop table if exists `sys_menu`;
create table `sys_menu`(
	`menu_id` int unsigned not null comment '菜单id',
	`menu_name` varchar(30) comment '菜单名称',
	`menu_url` varchar(100) comment '菜单地址',
	`menu_icon` varchar(50) comment '菜单图标',
	`sup_menu_id` int unsigned comment '上级菜单id',
	`state` tinyint unsigned comment '状态 1-启用 0-禁用',
	`order` int unsigned comment '排序',
	`create_time` datetime comment '创建时间',
	`update_time` timestamp on update current_timestamp comment '修改时间',
	primary key (`menu_id`)
) comment '菜单';

drop table if exists `sys_user`;
create table `sys_user`(
	`user_id` int unsigned not null auto_increment comment '用户id',
	`username` varchar(30) comment '用户名',
	`password` varchar(100) comment '密码',
	`state` smallint unsigned comment '状态 1-启用 0-禁用',
	`create_time` datetime comment '创建时间',
	`update_time` timestamp on update current_timestamp comment '修改时间',
	primary key (`user_id`)
) comment '用户' auto_increment=10000;

drop table if exists `sys_role`;
create table `sys_role`(
	`role_id` int unsigned not null auto_increment comment '用户id',
	`role_name` varchar(30) comment '角色名称',
	`state` smallint unsigned comment '状态 1-启用 0-禁用',
	`create_time` datetime comment '创建时间',
	`update_time` timestamp on update current_timestamp comment '修改时间',
	primary key (`role_id`)
) comment '角色' auto_increment=10000;

drop table if exists `sys_user_role`;
create table `sys_user_role`(
	`user_id` int unsigned comment '用户id',
	`role_id` int unsigned comment '角色id',
	`create_time` datetime comment '创建时间',
	`update_time` timestamp on update current_timestamp comment '修改时间'
) comment '用户角色' auto_increment=10000;

drop table if exists `sys_role_menu`;
create table `sys_role_menu`(
	`role_id` int unsigned comment '角色id',
	`menu_id` int unsigned comment '菜单id',
	`create_time` datetime comment '创建时间'
) comment '角色菜单' auto_increment=10000;

insert into sys_user(`user_id`, `username`, `password`, `state`, `create_time`) values (9999, 'admin', '$2a$12$HNbM2r04UgY1zl3lBFwsQOMSKBmVINQqX4X2q8LpI5yprMwdQgm6m', 1, now()); --初始密码123456
insert into sys_role(`role_id`, `role_name`, `state`, `create_time`) values (9999, '超级管理员', 1, now());
insert into sys_menu(`menu_id`, `menu_name`, `menu_url`, `menu_icon`, `sup_menu_id`, `state`, `order`, `create_time`) values (1000, '系统设置', null, 'el-icon-setting', null, 1, 10000, now());
insert into sys_menu(`menu_id`, `menu_name`, `menu_url`, `menu_icon`, `sup_menu_id`, `state`, `order`, `create_time`) values (1001, '用户管理', '/system/user', 'el-icon-user', 1000, 0, 10001, now());
insert into sys_menu(`menu_id`, `menu_name`, `menu_url`, `menu_icon`, `sup_menu_id`, `state`, `order`, `create_time`) values (1002, '菜单管理', '/system/menu', 'el-icon-menu', 1000, 1, 10002, now());
insert into sys_menu(`menu_id`, `menu_name`, `menu_url`, `menu_icon`, `sup_menu_id`, `state`, `order`, `create_time`) values (1003, '角色管理', '/system/role', 'el-icon-s-custom', 1000, 1, 10003, now());
insert into sys_menu(`menu_id`, `menu_name`, `menu_url`, `menu_icon`, `sup_menu_id`, `state`, `order`, `create_time`) values (1004, '公司管理', '/system/company', 'el-icon-office-building', 1000, 1, 10004, now());
insert into sys_menu(`menu_id`, `menu_name`, `menu_url`, `menu_icon`, `sup_menu_id`, `state`, `order`, `create_time`) values (2000, '功能管理', null, 'el-icon-goods', null, 1, 20000, now());
insert into sys_menu(`menu_id`, `menu_name`, `menu_url`, `menu_icon`, `sup_menu_id`, `state`, `order`, `create_time`) values (2001, '示例', '/function/demo', 'el-icon-search', 2000, 1, 20001, now());
insert into sys_user_role(`user_id`, `role_id`, `create_time`) values (9999, 9999, now());
insert into sys_role_menu values (9999, 1000, now());
insert into sys_role_menu values (9999, 1001, now());
insert into sys_role_menu values (9999, 1002, now());
insert into sys_role_menu values (9999, 1003, now());
insert into sys_role_menu values (9999, 1004, now());
insert into sys_role_menu values (9999, 1005, now());
insert into sys_role_menu values (9999, 2000, now());
insert into sys_role_menu values (9999, 2001, now());
