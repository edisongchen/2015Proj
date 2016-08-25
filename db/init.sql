drop database if exists webdemo;
CREATE DATABASE if not exists `webdemo` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
use webdemo;
CREATE USER 'webdemo'@'localhost' IDENTIFIED BY 'webdemo' PASSWORD EXPIRE NEVER;

grant all privileges on webdemo.* to 'webdemo'@'%' identified by 'webdemo';
grant all privileges on webdemo.* to 'webdemo'@'localhost' identified by 'webdemo';
grant all privileges on webdemo.* to 'webdemo'@'127.0.0.1' identified by 'webdemo';

flush privileges;

