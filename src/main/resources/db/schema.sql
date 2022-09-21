
--图书表
--author:hqc
--2022/09/19
CREATE TABLE book (
  `id` INT  NOT NULL  AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(100) NOT NULL COMMENT '图书名称',
  `author` varchar(50) not null COMMENT '作者',
  `borrowing_num` int  DEFAULT 0 COMMENT '借阅次数',
  PRIMARY KEY (`id`)
);

--用户表
--author:hqc
--2022/09/21
CREATE TABLE account (
  `id` int  NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(20) NOT NULL COMMENT '账号',
  `password` varchar(20) NOT NULL COMMENT '密码',
  `perms` varchar(20) DEFAULT NULL COMMENT '权限',
  `role` varchar(20) DEFAULT NULL COMMENT '角色',
   PRIMARY KEY (`id`)
) ;






