DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog` (
  `id` int AUTO_INCREMENT NOT NULL,
  `userId` int(11) unsigned NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `content` TEXT DEFAULT NULL,
  `lastUpdateTime` DATETIME DEFAULT now(),
  PRIMARY KEY (`id`)
);
