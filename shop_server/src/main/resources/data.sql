# ************************************************************
# Sequel Ace SQL dump
# 版本号： 20021
#
# https://sequel-ace.com/
# https://github.com/Sequel-Ace/Sequel-Ace
#
# 主机: localhost (MySQL 8.0.25)
# 数据库: hsp
# 生成时间: 2022-01-17 14:21:30 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
SET NAMES utf8mb4;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE='NO_AUTO_VALUE_ON_ZERO', SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# 转储表 cart
# ------------------------------------------------------------

DROP TABLE IF EXISTS `cart`;

CREATE TABLE `cart` (
  `id` varchar(50) NOT NULL,
  `user_id` int NOT NULL,
  `goods_id` varchar(50) NOT NULL,
  `quantity` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;

INSERT INTO `cart` (`id`, `user_id`, `goods_id`, `quantity`)
VALUES
	('3cf19bd86b2b4c71a6e788ff8f34f523',2,'7b7dfe134d2b4473aa025fb2c0daa574',2),
	('b684be49b65346ac97c0e81a116716a2',0,'7b7dfe134d2b4473aa025fb2c0daa572',38),
	('b93a48e6f80f43ad98f837dbc4e011e2',2,'7b7dfe134d2b4473aa025fb2c0daa572',4),
	('da3aae1fc6dd483c8b72fe5bcc7d3e3f',2,'7b7dfe134d2b4473aa025fb2c0daa573',1);

/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;


# 转储表 goods
# ------------------------------------------------------------

DROP TABLE IF EXISTS `goods`;

CREATE TABLE `goods` (
  `id` varchar(50) NOT NULL,
  `name` varchar(200) NOT NULL,
  `price` int NOT NULL,
  `long_pic` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `square_pic` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `goods` WRITE;
/*!40000 ALTER TABLE `goods` DISABLE KEYS */;

INSERT INTO `goods` (`id`, `name`, `price`, `long_pic`, `square_pic`)
VALUES
	('7b7dfe134d2b4473aa025fb2c0daa571','123',3000,'https://h2.appsimg.com/a.appsimg.com/upload/brand/upcb/2022/01/06/102/ias_a28a4efde3be0890bb22724e5dedaeb5_1135x545_85.jpg','https://h2.appsimg.com/a.appsimg.com/upload/merchandise/pdcvis/2021/09/28/144/f74a0540-ac76-4b03-87d5-54262db72bc1_512x512_90.jpg'),
	('7b7dfe134d2b4473aa025fb2c0daa572','122',3000,'https://h2.appsimg.com/a.appsimg.com/upload/brand/upcb/2021/07/30/160/ias_86095df3cfe17ce6437098d7d0519d9f_1135x545_85.jpg','https://h2.appsimg.com/a.appsimg.com/upload/merchandise/pdcvis/2021/07/23/12/1b8634fc-124c-4526-bbd4-7baadf58a091_512x512_90.jpg'),
	('7b7dfe134d2b4473aa025fb2c0daa573','121',2800,'https://h2.appsimg.com/a.appsimg.com/upload/brand/upcb/2021/11/01/161/ias_f2b8d6ecbc6ec61a89d20d19a6a98b8c_1135x545_85.jpg','https://h2.appsimg.com/a.appsimg.com/upload/merchandise/pdcvis/2021/09/17/84/78e122cd-87e8-4a04-99e8-6fbdb4d60e9f_512x512_90.jpg'),
	('7b7dfe134d2b4473aa025fb2c0daa574','120',3000,'https://h2.appsimg.com/a.appsimg.com/upload/brand/upcb/2022/01/01/73/ias_9a761022729bd2e463f4e7db85b9581d_1135x545_85.jpg','https://h2.appsimg.com/a.appsimg.com/upload/merchandise/pdcvis/619937/2021/1119/101/3d70a594-9a8d-4f9c-95d5-824a31f4886a_512x512_90.jpg'),
	('7b7dfe134d2b4473aa025fb2c0daa575','190',2960,'https://h2.appsimg.com/a.appsimg.com/upload/brand/upcb/2021/10/04/127/ias_9c7558d2138786780222b653aab39b93_1135x545_85.jpg','https://h2.appsimg.com/a.appsimg.com/upload/merchandise/pdcvis/619937/2021/1102/52/9388789a-093c-4f8d-aaf0-425fd5cd8622_512x512_90.jpg'),
	('7b7dfe134d2b4473aa025fb2c0daa576','187',3990,'https://h2.appsimg.com/a.appsimg.com/upload/brand/upcb/2021/11/06/124/ias_5a816dc1f2b663e0606aa10d80bf99ca_1135x545_85.jpg','https://h2.appsimg.com/a.appsimg.com/upload/merchandise/pdcvis/2021/11/14/131/77896c01-6ece-4b45-9e22-47ed2815d8b0_360x456_85.jpg!75.webp'),
	('7b7dfe134d2b4473aa025fb2c0daa577','125',4999,'https://h2.appsimg.com/a.appsimg.com/upload/brand/upcb/2021/10/29/13/ias_e3122735df7fad501278e3bafca1aad4_1135x545_85.jpg','https://h2.appsimg.com/a.appsimg.com/upload/merchandise/ugcaudit/2021/06/25/167/61714f36-d585-11eb-b37d-02020ad0ad6d_512x512_90.jpg');

/*!40000 ALTER TABLE `goods` ENABLE KEYS */;
UNLOCK TABLES;


# 转储表 orders
# ------------------------------------------------------------

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `id` varchar(50) NOT NULL,
  `goods_id` varchar(50) NOT NULL,
  `quantity` int DEFAULT NULL,
  `order_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;

INSERT INTO `orders` (`id`, `goods_id`, `quantity`, `order_id`)
VALUES
	('3894101f406b41f1a9a3899f32dfa007','7b7dfe134d2b4473aa025fb2c0daa574',1,'4ee5ed6d84bf4e77899c5d48bb18dd53'),
	('4e3db1ca1887405c868d3a30ee58492b','7b7dfe134d2b4473aa025fb2c0daa571',1,'f75585233454453c8700b607f8b92e0d'),
	('7d9cb1ae648340fa96d408138b49cef4','7b7dfe134d2b4473aa025fb2c0daa573',1,'1c9fc57159404155897e97276d1096cd'),
	('bc23bc403c4e42ff8e7e40ca3761686c','7b7dfe134d2b4473aa025fb2c0daa574',1,'76ce3cf9dff1491286dfba256bc12a92');

/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;


# 转储表 sys_role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;

INSERT INTO `sys_role` (`id`, `name`)
VALUES
	(1,'ROLE_ADMIN'),
	(2,'ROLE_USER');

/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;


# 转储表 sys_user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;

INSERT INTO `sys_user` (`id`, `name`, `password`)
VALUES
	(1,'o4tAE5I9xQqSlAKpykWieTAcEXfo','$2a$10$kFPg8JzkYO40RTl3DnpUxOOWMncJAY.vFXa6uYSWkLDwiaBFZvd2C'),
	(2,'hsp','$2a$10$skH.DAUVH7peKEB7kW4oiuJAs90bT.LHm./7gCYRKv.hlYB8JHGQC'),
	(3,'hsp1','$2a$10$DC9X35MEqaTYKpjt1VYgRe46HCIcKzs2mh7Ig.AMoTCIFJafLPvxe');

/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;


# 转储表 sys_user_role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `sys_user_role` WRITE;
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;

INSERT INTO `sys_user_role` (`user_id`, `role_id`)
VALUES
	(1,1),
	(2,1),
	(3,1);

/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;


# 转储表 user_addr
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user_addr`;

CREATE TABLE `user_addr` (
  `id` varchar(50) NOT NULL DEFAULT '',
  `user_id` int NOT NULL DEFAULT '0',
  `name` varchar(50) NOT NULL DEFAULT '',
  `phone` varchar(50) NOT NULL DEFAULT '0',
  `region` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '',
  `address` varchar(100) NOT NULL DEFAULT '',
  `default_address` bit(1) NOT NULL DEFAULT b'0',
  `addr_type` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `user_addr` WRITE;
/*!40000 ALTER TABLE `user_addr` DISABLE KEYS */;

INSERT INTO `user_addr` (`id`, `user_id`, `name`, `phone`, `region`, `address`, `default_address`, `addr_type`)
VALUES
	('02cfc7ddb10a4e2b8991262b202bbb51',2,'过眼云烟','',NULL,'哈哈哈哈',b'0',NULL),
	('9da4c2031f2f4473ac07451ee9b7ecf1',0,'eeeee','','','',b'1',NULL),
	('c05fb972b09840bd805dc5e46086d4b2',0,'uuuu','','','',b'0',NULL),
	('f10b6b33beb843cda9f5f62edc71a93c',2,'hhh','',NULL,'不好好',b'0',NULL);

/*!40000 ALTER TABLE `user_addr` ENABLE KEYS */;
UNLOCK TABLES;


# 转储表 user_order
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user_order`;

CREATE TABLE `user_order` (
  `id` varchar(50) NOT NULL,
  `user_id` int NOT NULL,
  `status` int DEFAULT NULL COMMENT '1???????2??????????3?????ջ???4???????ۣ?5???˻??У?6???رգ?',
  `create_time` bigint DEFAULT NULL,
  `user_addr_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `user_order` WRITE;
/*!40000 ALTER TABLE `user_order` DISABLE KEYS */;

INSERT INTO `user_order` (`id`, `user_id`, `status`, `create_time`, `user_addr_id`)
VALUES
	('1c9fc57159404155897e97276d1096cd',2,2,1641900407164,NULL),
	('4ee5ed6d84bf4e77899c5d48bb18dd53',0,2,1641953716361,NULL),
	('76ce3cf9dff1491286dfba256bc12a92',0,2,1642429125735,NULL),
	('f75585233454453c8700b607f8b92e0d',2,2,1641900639121,NULL);

/*!40000 ALTER TABLE `user_order` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
