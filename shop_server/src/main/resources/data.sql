DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`name` varchar(255) NOT NULL,
`password` varchar(255) NOT NULL,
PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
`id` int(11) NOT NULL,
`name` varchar(255) NOT NULL,
PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
`user_id` int(11) NOT NULL,
`role_id` int(11) NOT NULL,
PRIMARY KEY (`user_id`,`role_id`)
);

INSERT INTO `sys_role` VALUES ('1', 'ROLE_ADMIN');
INSERT INTO `sys_role` VALUES ('2', 'ROLE_USER');
