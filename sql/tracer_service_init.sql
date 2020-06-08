# create user 'test'@'%' identified by 'pass';
# grant all privileges on *.* to 'test'@'%' with grant option;
# flush privileges;
CREATE USER 'test'@'docker-mysql-db' IDENTIFIED BY 'pass';

CREATE DATABASE IF NOT EXISTS tracer_service;

GRANT ALL PRIVILEGES ON tracer_service.* TO 'test'@'docker-mysql-db';