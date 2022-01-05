DROP TABLE IF EXISTS `goods`;
CREATE TABLE IF NOT EXISTS `goods` (
  `id` varchar(50) NOT NULL DEFAULT '',
  `name` varchar(200) NOT NULL,
  `price` int NOT NULL,
  `pics` text,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `order`;
CREATE TABLE IF NOT EXISTS `order` (
  `id` varchar(50) NOT NULL DEFAULT '',
  `user_id` int NOT NULL,
  `goods_id` varchar(50) DEFAULT NULL
);