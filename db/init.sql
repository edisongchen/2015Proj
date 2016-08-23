drop database if exists webdemo;
CREATE DATABASE if not exists `webdemo` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
use webdemo;
CREATE USER 'webdemo'@'localhost' IDENTIFIED BY 'webdemo' PASSWORD EXPIRE NEVER;

UPDATE user SET authentication_string= password ('48STX2X') WHERE user='root';

flush privileges;

grant all privileges on *.* to 'root'@'%' identified by '48STX2X';
grant all privileges on *.* to 'root'@'localhost' identified by '48STX2X';
grant all privileges on *.* to 'root'@'127.0.0.1' identified by '48STX2X';

grant all privileges on iot.* to 'webdemo'@'%' identified by 'webdemo';
grant all privileges on iot.* to 'webdemo'@'localhost' identified by 'webdemo';
grant all privileges on iot.* to 'webdemo'@'127.0.0.1' identified by 'webdemo';

flush privileges;

