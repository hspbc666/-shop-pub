DROP TABLE IF EXISTS `note`;
CREATE TABLE `note` (
  `id` int AUTO_INCREMENT NOT NULL,
  `userId` int(11) unsigned NOT NULL,
  `content` TEXT DEFAULT NULL,
  `lastUpdateTime` DATETIME DEFAULT now(),
  PRIMARY KEY (`id`)
);