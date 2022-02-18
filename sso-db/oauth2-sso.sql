/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.7.27-log : Database - oauth2_sso_db
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`oauth2_sso_db` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `oauth2_sso_db`;

/*Table structure for table `oauth_client_details` */

DROP TABLE IF EXISTS `oauth_client_details`;

CREATE TABLE `oauth_client_details` (
  `client_id` varchar(255) NOT NULL,
  `resource_ids` varchar(255) DEFAULT NULL,
  `client_secret` varchar(255) DEFAULT NULL,
  `scope` varchar(255) DEFAULT NULL,
  `authorized_grant_types` varchar(255) DEFAULT NULL,
  `web_server_redirect_uri` varchar(255) DEFAULT NULL,
  `authorities` varchar(255) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `oauth_client_details` */

insert  into `oauth_client_details`(`client_id`,`resource_ids`,`client_secret`,`scope`,`authorized_grant_types`,`web_server_redirect_uri`,`authorities`,`access_token_validity`,`refresh_token_validity`,`additional_information`,`autoapprove`) values ('client1','','$2a$10$9WXY5PhzmHFw0pBmLrBXa.90aWbQ150slT4n3gS.RLYZM/Um2oTbu','read_hello,write_bye','authorization_code,refresh_token','http://client1.sso.com/client1/login',NULL,3600,864000,NULL,'true'),('client2','','$2a$10$9WXY5PhzmHFw0pBmLrBXa.90aWbQ150slT4n3gS.RLYZM/Um2oTbu','read_hello,write_bye','authorization_code,refresh_token','http://client2.sso.com/client2/login',NULL,3600,864000,NULL,'true');

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父菜单ID',
  `order_num` int(11) DEFAULT '0' COMMENT '显示顺序',
  `path` varchar(200) DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) DEFAULT NULL COMMENT '组件路径',
  `is_frame` int(11) DEFAULT '1' COMMENT '是否为外链（0是 1否）',
  `menu_type` char(1) DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `status` char(1) DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1063 DEFAULT CHARSET=utf8 COMMENT='菜单权限表';

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`menu_id`,`menu_name`,`parent_id`,`order_num`,`path`,`component`,`is_frame`,`menu_type`,`visible`,`status`,`perms`,`icon`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) values (1,'系统管理',0,1,'system',NULL,1,'M','0','0','','system','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31','系统管理目录'),(3,'系统工具',0,3,'tool',NULL,1,'M','0','0','','tool','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31','系统工具目录'),(100,'用户管理',1,1,'user','system/user/index',1,'C','0','0','system:user:list','user','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31','用户管理菜单'),(101,'角色管理',1,2,'role','system/role/index',1,'C','0','0','system:role:list','peoples','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31','角色管理菜单'),(102,'菜单管理',1,3,'menu','system/menu/index',1,'C','0','0','system:menu:list','tree-table','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31','菜单管理菜单'),(103,'部门管理',1,4,'dept','system/dept/index',1,'C','0','0','system:dept:list','tree','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31','部门管理菜单'),(104,'岗位管理',1,5,'post','system/post/index',1,'C','0','0','system:post:list','post','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31','岗位管理菜单'),(105,'字典管理',1,6,'dict','system/dict/index',1,'C','0','0','system:dict:list','dict','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31','字典管理菜单'),(106,'参数设置',1,7,'config','system/config/index',1,'C','0','0','system:config:list','edit','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31','参数设置菜单'),(107,'终端设置',1,8,'client','system/client/index',1,'C','0','0','system:client:list','client','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31','终端设置菜单'),(108,'通知公告',1,9,'notice','system/notice/index',1,'C','0','0','system:notice:list','message','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31','通知公告菜单'),(109,'日志管理',1,10,'log','system/log/index',1,'M','0','0','','log','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31','日志管理菜单'),(114,'表单构建',3,1,'build','tool/build/index',1,'C','0','0','tool:build:list','build','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31','表单构建菜单'),(115,'代码生成',3,2,'gen','tool/gen/index',1,'C','0','0','tool:gen:list','code','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31','代码生成菜单'),(116,'系统接口',3,3,'http://localhost:8080/swagger-ui.html','',1,'C','0','0','tool:swagger:list','swagger','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31','系统接口菜单'),(500,'操作日志',109,1,'operlog','system/operlog/index',1,'C','0','0','system:operlog:list','form','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31','操作日志菜单'),(501,'登录日志',109,2,'logininfor','system/logininfor/index',1,'C','0','0','system:logininfor:list','logininfor','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31','登录日志菜单'),(1001,'用户查询',100,1,'','',1,'F','0','0','system:user:query','#','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31',''),(1002,'用户新增',100,2,'','',1,'F','0','0','system:user:add','#','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31',''),(1003,'用户修改',100,3,'','',1,'F','0','0','system:user:edit','#','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31',''),(1004,'用户删除',100,4,'','',1,'F','0','0','system:user:remove','#','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31',''),(1005,'用户导出',100,5,'','',1,'F','0','0','system:user:export','#','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31',''),(1006,'用户导入',100,6,'','',1,'F','0','0','system:user:import','#','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31',''),(1007,'重置密码',100,7,'','',1,'F','0','0','system:user:resetPwd','#','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31',''),(1008,'角色查询',101,1,'','',1,'F','0','0','system:role:query','#','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31',''),(1009,'角色新增',101,2,'','',1,'F','0','0','system:role:add','#','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31',''),(1010,'角色修改',101,3,'','',1,'F','0','0','system:role:edit','#','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31',''),(1011,'角色删除',101,4,'','',1,'F','0','0','system:role:remove','#','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31',''),(1012,'角色导出',101,5,'','',1,'F','0','0','system:role:export','#','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31',''),(1013,'菜单查询',102,1,'','',1,'F','0','0','system:menu:query','#','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31',''),(1014,'菜单新增',102,2,'','',1,'F','0','0','system:menu:add','#','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31',''),(1015,'菜单修改',102,3,'','',1,'F','0','0','system:menu:edit','#','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31',''),(1016,'菜单删除',102,4,'','',1,'F','0','0','system:menu:remove','#','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31',''),(1017,'部门查询',103,1,'','',1,'F','0','0','system:dept:query','#','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31',''),(1018,'部门新增',103,2,'','',1,'F','0','0','system:dept:add','#','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31',''),(1019,'部门修改',103,3,'','',1,'F','0','0','system:dept:edit','#','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31',''),(1020,'部门删除',103,4,'','',1,'F','0','0','system:dept:remove','#','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31',''),(1021,'岗位查询',104,1,'','',1,'F','0','0','system:post:query','#','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31',''),(1022,'岗位新增',104,2,'','',1,'F','0','0','system:post:add','#','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31',''),(1023,'岗位修改',104,3,'','',1,'F','0','0','system:post:edit','#','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31',''),(1024,'岗位删除',104,4,'','',1,'F','0','0','system:post:remove','#','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31',''),(1025,'岗位导出',104,5,'','',1,'F','0','0','system:post:export','#','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31',''),(1026,'字典查询',105,1,'#','',1,'F','0','0','system:dict:query','#','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31',''),(1027,'字典新增',105,2,'#','',1,'F','0','0','system:dict:add','#','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31',''),(1028,'字典修改',105,3,'#','',1,'F','0','0','system:dict:edit','#','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31',''),(1029,'字典删除',105,4,'#','',1,'F','0','0','system:dict:remove','#','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31',''),(1030,'字典导出',105,5,'#','',1,'F','0','0','system:dict:export','#','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31',''),(1031,'参数查询',106,1,'#','',1,'F','0','0','system:config:query','#','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31',''),(1032,'参数新增',106,2,'#','',1,'F','0','0','system:config:add','#','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31',''),(1033,'参数修改',106,3,'#','',1,'F','0','0','system:config:edit','#','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31',''),(1034,'参数删除',106,4,'#','',1,'F','0','0','system:config:remove','#','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31',''),(1035,'参数导出',106,5,'#','',1,'F','0','0','system:config:export','#','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31',''),(1036,'终端查询',107,1,'#','',1,'F','0','0','system:client:query','#','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31',''),(1037,'终端新增',107,2,'#','',1,'F','0','0','system:client:add','#','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31',''),(1038,'终端修改',107,3,'#','',1,'F','0','0','system:client:edit','#','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31',''),(1039,'终端删除',107,4,'#','',1,'F','0','0','system:client:remove','#','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31',''),(1040,'终端导出',107,5,'#','',1,'F','0','0','system:client:export','#','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31',''),(1041,'公告查询',108,1,'#','',1,'F','0','0','system:notice:query','#','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31',''),(1042,'公告新增',108,2,'#','',1,'F','0','0','system:notice:add','#','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31',''),(1043,'公告修改',108,3,'#','',1,'F','0','0','system:notice:edit','#','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31',''),(1044,'公告删除',108,4,'#','',1,'F','0','0','system:notice:remove','#','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31',''),(1045,'操作查询',500,1,'#','',1,'F','0','0','system:operlog:query','#','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31',''),(1046,'操作删除',500,2,'#','',1,'F','0','0','system:operlog:remove','#','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31',''),(1047,'日志导出',500,4,'#','',1,'F','0','0','system:operlog:export','#','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31',''),(1048,'登录查询',501,1,'#','',1,'F','0','0','system:logininfor:query','#','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31',''),(1049,'登录删除',501,2,'#','',1,'F','0','0','system:logininfor:remove','#','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31',''),(1050,'日志导出',501,3,'#','',1,'F','0','0','system:logininfor:export','#','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31',''),(1057,'生成查询',115,1,'#','',1,'F','0','0','tool:gen:query','#','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31',''),(1058,'生成修改',115,2,'#','',1,'F','0','0','tool:gen:edit','#','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31',''),(1059,'生成删除',115,3,'#','',1,'F','0','0','tool:gen:remove','#','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31',''),(1060,'导入代码',115,2,'#','',1,'F','0','0','tool:gen:import','#','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31',''),(1061,'预览代码',115,4,'#','',1,'F','0','0','tool:gen:preview','#','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31',''),(1062,'生成代码',115,5,'#','',1,'F','0','0','tool:gen:code','#','system','2022-01-25 11:26:31','system','2022-01-25 11:26:31','');

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) NOT NULL COMMENT '角色权限字符串',
  `role_sort` int(11) NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `status` char(1) NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='角色信息表';

/*Data for the table `sys_role` */

insert  into `sys_role`(`role_id`,`role_name`,`role_key`,`role_sort`,`data_scope`,`status`,`del_flag`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) values (1,'超级管理员','admin',1,'1','0','0','system','2022-01-23 12:00:13','system','2022-01-23 12:00:13','超级管理员'),(2,'普通角色','common',2,'2','0','0','system','2022-01-23 12:00:13','system','2022-01-23 12:00:13','普通角色');

/*Table structure for table `sys_role_menu` */

DROP TABLE IF EXISTS `sys_role_menu`;

CREATE TABLE `sys_role_menu` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色和菜单关联表';

/*Data for the table `sys_role_menu` */

insert  into `sys_role_menu`(`role_id`,`menu_id`) values (1,1),(1,3),(1,100),(1,101),(1,102),(1,103),(1,104),(1,105),(1,106),(1,107),(1,108),(1,109),(1,114),(1,115),(1,500),(1,501),(1,1000),(1,1001),(1,1002),(1,1003),(1,1004),(1,1005),(1,1006),(1,1007),(1,1008),(1,1009),(1,1010),(1,1011),(1,1012),(1,1013),(1,1014),(1,1015),(1,1016),(1,1017),(1,1018),(1,1019),(1,1020),(1,1021),(1,1022),(1,1023),(1,1024),(1,1025),(1,1026),(1,1027),(1,1028),(1,1029),(1,1030),(1,1031),(1,1032),(1,1033),(1,1034),(1,1035),(1,1036),(1,1037),(1,1038),(1,1039),(1,1040),(1,1041),(1,1042),(1,1043),(1,1044),(1,1045),(1,1046),(1,1047),(1,1048),(1,1049),(1,1050),(1,1057),(1,1058),(1,1059),(1,1060),(1,1061),(1,1062),(2,3),(2,114),(2,115),(2,1000),(2,1057),(2,1058),(2,1059),(2,1060),(2,1061),(2,1062);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `user_name` varchar(30) NOT NULL COMMENT '用户账号',
  `nick_name` varchar(30) NOT NULL COMMENT '用户昵称',
  `user_type` varchar(2) DEFAULT '00' COMMENT '用户类型（00系统用户）',
  `email` varchar(50) DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) DEFAULT '' COMMENT '手机号码',
  `sex` char(1) DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) DEFAULT '' COMMENT '头像地址',
  `password` varchar(100) DEFAULT '' COMMENT '密码',
  `status` char(1) DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(50) DEFAULT '' COMMENT '最后登陆IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登陆时间',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

/*Data for the table `sys_user` */

insert  into `sys_user`(`user_id`,`dept_id`,`user_name`,`nick_name`,`user_type`,`email`,`phonenumber`,`sex`,`avatar`,`password`,`status`,`del_flag`,`login_ip`,`login_date`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) values (1,103,'admin','管理员','00','','','1','','$2a$10$9WXY5PhzmHFw0pBmLrBXa.90aWbQ150slT4n3gS.RLYZM/Um2oTbu','0','0','127.0.0.1','2022-01-23 11:59:25','system','2022-01-23 11:59:25','system','2022-01-23 11:59:25','管理员'),(2,105,'test','测试','00','','','1','','$2a$10$9WXY5PhzmHFw0pBmLrBXa.90aWbQ150slT4n3gS.RLYZM/Um2oTbu','0','0','127.0.0.1','2022-01-23 11:59:25','system','2022-01-23 11:59:25','system','2022-01-23 11:59:25','测试员');

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户和角色关联表';

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`user_id`,`role_id`) values (1,1),(2,2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
