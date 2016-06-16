SET NAMES 'utf8';
CREATE DATABASE IF NOT EXISTS webdemo DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;

USE webdemo;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS sys_user;
CREATE TABLE sys_user
(
  id varchar(64) NOT NULL COMMENT '编号',
  login_name varchar(100) BINARY NOT NULL COMMENT '登录名',
  password varchar(100) NOT NULL COMMENT '密码',
  name varchar(100) BINARY NOT NULL COMMENT '姓名',
  email varchar(200) COMMENT '邮箱',
  phone varchar(200) COMMENT '电话',
  del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
  PRIMARY KEY (id)
) COMMENT = '用户表';
SET FOREIGN_KEY_CHECKS = 1;