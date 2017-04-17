/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 10.2.2-MariaDB-1~xenial-log : Database - rencaijia
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`springbootdb` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `springbootdb`;

/*Table structure for table `city` */

DROP TABLE IF EXISTS `city`;

CREATE TABLE `city` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(6) DEFAULT NULL,
  `name` varchar(72) DEFAULT NULL,
  `number` varchar(36) DEFAULT NULL,
  `parent_number` varchar(36) DEFAULT NULL,
  `order_num` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_number` (`number`)
) ENGINE=InnoDB AUTO_INCREMENT=11345 DEFAULT CHARSET=utf8;

/*Data for the table `city` */

insert  into `city`(`id`,`type`,`name`,`number`,`parent_number`,`order_num`) values (1,'10','亚洲','1',NULL,10),(2,'10','北美洲','2',NULL,20),(3,'10','南美洲','3',NULL,30);
/*Table structure for table `constants_record` */

DROP TABLE IF EXISTS `constants_record`;

CREATE TABLE `constants_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(72) DEFAULT NULL,
  `value` int(11) NOT NULL,
  `type` varchar(50) NOT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `parent_value` int(11) DEFAULT NULL,
  `order_num` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `index_v_t` (`value`,`type`)
) ENGINE=InnoDB AUTO_INCREMENT=683 DEFAULT CHARSET=utf8;

/*Data for the table `constants_record` */

insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order_num`) values (1,'test',100,'busiType','busiTypeDesc',NULL,10);
/*Table structure for table `industry` */

DROP TABLE IF EXISTS `industry`;

CREATE TABLE `industry` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `number` varchar(75) DEFAULT NULL,
  `type` varchar(75) DEFAULT NULL,
  `parent_number` varchar(32) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `order_num` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_number` (`number`)
) ENGINE=InnoDB AUTO_INCREMENT=163 DEFAULT CHARSET=utf8;

/*Data for the table `industry` */

insert  into `industry`(`id`,`name`,`number`,`type`,`parent_number`,`description`,`order_num`) values (79,'IT/通信/电子/互联网','0201','2',NULL,NULL,10),(80,'房地产/建筑','0202','2',NULL,NULL,20);

/*Table structure for table `message` */

DROP TABLE IF EXISTS `message`;

CREATE TABLE `message` (
  `id` varchar(32) NOT NULL,
  `created_by` varchar(32) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_by` varchar(32) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `user_id` varchar(32) NOT NULL,
  `message_template_type` varchar(32) NOT NULL,
  `busi_id` varchar(32) DEFAULT NULL,
  `redirect_type` varchar(32) DEFAULT NULL,
  `redirect_id` varchar(32) DEFAULT NULL,
  `status` int(11) DEFAULT 10,
  PRIMARY KEY (`id`),
  KEY `index_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `message` */

insert  into `message`(`id`,`created_by`,`created_date`,`modified_by`,`modified_date`,`user_id`,`message_template_type`,`busi_id`,`redirect_type`,`redirect_id`,`status`) values
('00e0edd9ba324950b8760f50206d9cd9','e086a2b072714b48965a23c7d10d9dd3','2017-04-13 17:05:52','e086a2b072714b48965a23c7d10d9dd3','2017-04-13 17:05:52','e086a2b072714b48965a23c7d10d9dd3','205',NULL,NULL,NULL,10);

/*Table structure for table `message_clear` */

DROP TABLE IF EXISTS `message_clear`;

CREATE TABLE `message_clear` (
  `id` varchar(32) NOT NULL,
  `created_by` varchar(32) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_by` varchar(32) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `message_id` varchar(32) NOT NULL,
  `user_id` varchar(32) NOT NULL,
  `clear` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`id`),
  KEY `index_message_id` (`message_id`),
  KEY `index_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `message_clear` */

insert  into `message_clear`(`id`,`created_by`,`created_date`,`modified_by`,`modified_date`,`message_id`,`user_id`,`clear`) values 
('04fe3b9abacc4aaaac05dd9c6a818f4d','699ac94e722c4a1d800c6c28d46027a3','2017-04-06 10:34:12','699ac94e722c4a1d800c6c28d46027a3','2017-04-06 10:34:12','messageId1301','699ac94e722c4a1d800c6c28d46027a3','1');
/*Table structure for table `message_read` */

DROP TABLE IF EXISTS `message_read`;

CREATE TABLE `message_read` (
  `id` varchar(32) NOT NULL,
  `created_by` varchar(32) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_by` varchar(32) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `message_id` varchar(32) NOT NULL,
  `user_id` varchar(32) NOT NULL,
  `read` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `index_message_id` (`message_id`),
  KEY `index_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `message_read` */

insert  into `message_read`(`id`,`created_by`,`created_date`,`modified_by`,`modified_date`,`message_id`,`user_id`,`read`) values 
('069cd2b27d094cefa3e5733eaecec8f6','737e131ad18f4a5db66d8052f3cee54a','2017-03-23 23:32:19','737e131ad18f4a5db66d8052f3cee54a','2017-03-23 23:32:19','messageId1301','737e131ad18f4a5db66d8052f3cee54a','1');
/*Table structure for table `message_template` */

DROP TABLE IF EXISTS `message_template`;

CREATE TABLE `message_template` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(32) NOT NULL,
  `template` varchar(2000) NOT NULL,
  `status` int(11) DEFAULT 10,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

/*Data for the table `message_template` */

insert  into `message_template`(`id`,`type`,`template`,`status`) values (3,'101','您获取了xx{0} [{1}] 的联系方式<br/>扣除{2}资金',10);
/*Table structure for table `module` */

DROP TABLE IF EXISTS `module`;

CREATE TABLE `module` (
  `id` varchar(32) NOT NULL,
  `name` varchar(40) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `code` varchar(15) DEFAULT NULL,
  `parent_id` varchar(32) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `enabled` int(11) DEFAULT 1,
  `created_date` datetime DEFAULT NULL,
  `created_by` varchar(32) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `modified_by` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `module` */

/*Table structure for table `module_permission` */

DROP TABLE IF EXISTS `module_permission`;

CREATE TABLE `module_permission` (
  `id` varchar(32) NOT NULL,
  `module_id` varchar(32) NOT NULL,
  `permission_id` varchar(32) NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `created_by` varchar(32) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `modified_by` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `module_permission` */

/*Table structure for table `path_intercepted` */

DROP TABLE IF EXISTS `path_intercepted`;

CREATE TABLE `path_intercepted` (
  `id` varchar(32) NOT NULL,
  `pathregex` varchar(100) NOT NULL,
  `required_filter` varchar(30) DEFAULT NULL,
  `required_role` varchar(50) DEFAULT NULL,
  `required_auth` varchar(50) DEFAULT NULL,
  `pathorder` varchar(10) NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `created_by` varchar(32) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `modified_by` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `path_intercepted` */

insert  into `path_intercepted`(`id`,`pathregex`,`required_filter`,`required_role`,`required_auth`,`pathorder`,`created_date`,`created_by`,`modified_date`,`modified_by`) values 
('1','/','anon',NULL,NULL,'1',NULL,NULL,NULL,NULL),('10','/image/**','anon',NULL,NULL,'1',NULL,NULL,NULL,NULL),('11','/test/**','anon',NULL,NULL,'1',NULL,NULL,NULL,NULL),
('14','/code/**','anon',NULL,NULL,'1',NULL,NULL,NULL,NULL),('15','/error/**','anon',NULL,NULL,'1',NULL,NULL,NULL,NULL),('16','/echo/**','anon',NULL,NULL,'1',NULL,NULL,NULL,NULL),
('17','/sockjs/echo/**','anon',NULL,NULL,'1',NULL,NULL,NULL,NULL),('18','/role/roles/**','anon',NULL,NULL,'1',NULL,NULL,NULL,NULL),('19','/callback/**','anon',NULL,NULL,'1',NULL,NULL,NULL,NULL),
('2','/resources/**','anon',NULL,NULL,'1',NULL,NULL,NULL,NULL),('20','/forget/**','anon',NULL,NULL,'1',NULL,NULL,NULL,NULL),('21','/ws/**','anon',NULL,NULL,'1',NULL,NULL,NULL,NULL),
('23','/constant/**','anon',NULL,NULL,'1',NULL,NULL,NULL,NULL),
('3','/favicon.ico','anon',NULL,NULL,'1',NULL,NULL,NULL,NULL),
('57','/permission/**','user','ADMIN',NULL,'800',NULL,NULL,NULL,NULL),('58','/module/**','user','ADMIN',NULL,'800',NULL,NULL,NULL,NULL),('59','/role/**','user','ADMIN',NULL,'800',NULL,NULL,NULL,NULL),
('6','/api-docs/**','anon',NULL,NULL,'1',NULL,NULL,NULL,NULL),
('61','/hirer/**','user','HIRER',NULL,'900',NULL,NULL,NULL,NULL),
('63','/talent/**','user','TALENT',NULL,'900',NULL,NULL,NULL,NULL),
('66','/agent/**','user','AGENT',NULL,'900',NULL,NULL,NULL,NULL),
('7','/login/**','anon',NULL,NULL,'1',NULL,NULL,NULL,NULL),
('8','/register/**','anon',NULL,NULL,'1',NULL,NULL,NULL,NULL),
('999','/**','user',NULL,NULL,'999',NULL,NULL,NULL,NULL);

/*Table structure for table `permission` */

DROP TABLE IF EXISTS `permission`;

CREATE TABLE `permission` (
  `id` varchar(32) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `code` varchar(15) DEFAULT NULL,
  `enabled` int(11) DEFAULT 1,
  `created_date` datetime DEFAULT NULL,
  `created_by` varchar(32) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `modified_by` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `permission` */

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` varchar(32) NOT NULL,
  `name` varchar(75) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `code` varchar(15) DEFAULT NULL,
  `parent_id` varchar(32) DEFAULT NULL,
  `enabled` int(11) DEFAULT 1,
  `created_date` datetime DEFAULT NULL,
  `created_by` varchar(32) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `modified_by` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`id`,`name`,`description`,`code`,`parent_id`,`enabled`,`created_date`,`created_by`,`modified_date`,`modified_by`) values 
('a1f5fbb126494d32b2b3f9ecdf986c8a','普通用户','','TALENT','',1,'2016-07-06 13:37:00','019202efc52c4901abcf0ef2b2212ac0','2016-07-06 13:37:00','019202efc52c4901abcf0ef2b2212ac0');
/*Table structure for table `role_module_permission` */

DROP TABLE IF EXISTS `role_module_permission`;

CREATE TABLE `role_module_permission` (
  `role_id` varchar(32) NOT NULL,
  `module_permission_id` varchar(32) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `created_by` varchar(32) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `modified_by` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`role_id`,`module_permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `role_module_permission` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` varchar(32) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `nickname` varchar(50) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `mobile` varchar(15) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `active_validate_code` varchar(32) DEFAULT NULL COMMENT '用邮箱找回密码的验证',
  `enabled` int(11) DEFAULT 1,
  `created_date` datetime DEFAULT NULL,
  `created_by` varchar(32) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `modified_by` varchar(32) DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL,
  `forget_password` varchar(8) DEFAULT NULL,
  `residence` varchar(200) DEFAULT NULL,
  `current_residence` varchar(200) DEFAULT NULL,
  `work_experience` varchar(20) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `salt` varchar(32) NOT NULL,
  `job_wanted_status` int(11) DEFAULT 10,
  `talent_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`,`nickname`,`age`,`sex`,`email`,`mobile`,`address`,`active_validate_code`,`enabled`,`created_date`,`created_by`,`modified_date`,`modified_by`,`last_login_time`,`forget_password`,`residence`,`current_residence`,`work_experience`,`birthday`,`salt`,`job_wanted_status`,`talent_id`) 
values ('025672d5721a402584f6b557657c036f','18676742287','ac765aa19929605fe088995d5c39c1ce','test',11,20,'','13534138318',NULL,NULL,-1,'2016-07-12 11:14:11','anonymous','2016-08-19 14:07:58','anonymous',
NULL,NULL,NULL,NULL,NULL,NULL,'025672d5721a402584f6b557657c036f',10,NULL),('0312241651ce4d49a44e331a7d70c833','13534138318','37224f66be5a279ff6fd20cf0fdd6f38',NULL,0,20,NULL,'13534138318',NULL,NULL,1,'2016-12-06 23:40:01','anonymous','2016-12-06 23:40:01','anonymous',NULL,NULL,NULL,NULL,NULL,NULL,'db3def83c1fb444c8b4c40ae28f22c32',10,NULL);
/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `user_id` varchar(32) NOT NULL,
  `role_id` varchar(32) NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `created_by` varchar(32) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `modified_by` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_role` */

insert  into `user_role`(`user_id`,`role_id`,`created_date`,`created_by`,`modified_date`,`modified_by`) values ('025672d5721a402584f6b557657c036f','17348cd3c9ae47d8891f54dd1d4bb00d',NULL,NULL,NULL,NULL);
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
