CREATE TABLE `blog` (
  `id` int NOT NULL AUTO_INCREMENT,
  `userId` int unsigned NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `content` text,
  `lastUpdateTime` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
);


CREATE TABLE `cart` (
  `id` varchar(50) NOT NULL,
  `user_id` int NOT NULL,
  `goods_id` varchar(50) NOT NULL,
  `quantity` int DEFAULT NULL,
  PRIMARY KEY (`id`)
);


INSERT INTO `cart` (`id`, `user_id`, `goods_id`, `quantity`)
VALUES
	('13170bff3e2c48bfaf6c01dd216c9c8a',5,'7b7dfe134d2b4473aa025fb2c0daa576',1),
	('lbl038644a24a904224a9ecc774cdb7bc85',0,'7b7dfe134d2b4473aa025fb2c0daa573',1),
	('lbl41cb6fb895ae41329b804ca529ae5e99',5,'7b7dfe134d2b4473aa025fb2c0daa574',1),
	('lbl4ec315c28cfc4df4a0ae62b5a63bba82',5,'7b7dfe134d2b4473aa025fb2c0daa572',1);


CREATE TABLE `category` (
  `id` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
);


INSERT INTO `category` (`id`, `name`)
VALUES
	('1','推荐'),
	('10','食品'),
	('2','美妆'),
	('3','母婴'),
	('4','女装'),
	('5','内衣'),
	('6','男装'),
	('7','家居'),
	('8','运动'),
	('9','家电');


CREATE TABLE `goods` (
  `id` varchar(50) NOT NULL,
  `name` varchar(200) NOT NULL,
  `price` int NOT NULL,
  `long_pic` varchar(200),
  `square_pic` varchar(200),
  PRIMARY KEY (`id`)
);


INSERT INTO `goods` (`id`, `name`, `price`, `long_pic`, `square_pic`)
VALUES
	('7b7dfe134d2b4473aa025fb2c0daa571','ROG游戏手机5s 16GB+256GB 暗影黑 骁龙888plus 144Hz三星E4屏 2x3双操控肩键 6000mAh电池 5G ROG5s',3000,'https://h2.appsimg.com/a.appsimg.com/upload/brand/upcb/2022/01/06/102/ias_a28a4efde3be0890bb22724e5dedaeb5_1135x545_85.jpg','https://h2.appsimg.com/a.appsimg.com/upload/merchandise/pdcvis/2021/09/28/144/f74a0540-ac76-4b03-87d5-54262db72bc1_512x512_90.jpg'),
	('7b7dfe134d2b4473aa025fb2c0daa572','万魔(1MORE)舒适豆升级版 ComfoBuds 2真无线半入耳式蓝牙耳机无线耳机 通用苹果华为小米手机 ES303 云母白',3000,'https://h2.appsimg.com/a.appsimg.com/upload/brand/upcb/2021/07/30/160/ias_86095df3cfe17ce6437098d7d0519d9f_1135x545_85.jpg','https://h2.appsimg.com/a.appsimg.com/upload/merchandise/pdcvis/2021/07/23/12/1b8634fc-124c-4526-bbd4-7baadf58a091_512x512_90.jpg'),
	('7b7dfe134d2b4473aa025fb2c0daa573','ROG游戏手机5s 16GB+256GB 暗影黑 骁龙888plus 144Hz三星E4屏 2x3双操控肩键 6000mAh电池 5G ROG5s',2800,'https://h2.appsimg.com/a.appsimg.com/upload/brand/upcb/2021/11/01/161/ias_f2b8d6ecbc6ec61a89d20d19a6a98b8c_1135x545_85.jpg','https://h2.appsimg.com/a.appsimg.com/upload/merchandise/pdcvis/2021/09/17/84/78e122cd-87e8-4a04-99e8-6fbdb4d60e9f_512x512_90.jpg'),
	('7b7dfe134d2b4473aa025fb2c0daa574','瑞士品牌I&W手表男表男士机械表全自动超薄进口机芯时尚简约商务罗马蓝宝石玻璃防水爱沃驰IW腕表送男友 本色白面｜黑色牛皮带｜进口超薄机芯',3000,'https://h2.appsimg.com/a.appsimg.com/upload/brand/upcb/2022/01/01/73/ias_9a761022729bd2e463f4e7db85b9581d_1135x545_85.jpg','https://h2.appsimg.com/a.appsimg.com/upload/merchandise/pdcvis/619937/2021/1119/101/3d70a594-9a8d-4f9c-95d5-824a31f4886a_512x512_90.jpg'),
	('7b7dfe134d2b4473aa025fb2c0daa575','ROG游戏手机5s 16GB+256GB 暗影黑 骁龙888plus 144Hz三星E4屏 2x3双操控肩键 6000mAh电池 5G ROG5s',2960,'https://h2.appsimg.com/a.appsimg.com/upload/brand/upcb/2021/10/04/127/ias_9c7558d2138786780222b653aab39b93_1135x545_85.jpg','https://h2.appsimg.com/a.appsimg.com/upload/merchandise/pdcvis/619937/2021/1102/52/9388789a-093c-4f8d-aaf0-425fd5cd8622_512x512_90.jpg'),
	('7b7dfe134d2b4473aa025fb2c0daa576','wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww',3990,'https://h2.appsimg.com/a.appsimg.com/upload/brand/upcb/2021/11/06/124/ias_5a816dc1f2b663e0606aa10d80bf99ca_1135x545_85.jpg','https://h2.appsimg.com/a.appsimg.com/upload/merchandise/pdcvis/2021/11/14/131/77896c01-6ece-4b45-9e22-47ed2815d8b0_360x456_85.jpg!75.webp'),
	('7b7dfe134d2b4473aa025fb2c0daa577','ROG游戏手机5s 16GB+256GB 暗影黑 骁龙888plus 144Hz三星E4屏 2x3双操控肩键 6000mAh电池 5G ROG5s',4999,'https://h2.appsimg.com/a.appsimg.com/upload/brand/upcb/2021/10/29/13/ias_e3122735df7fad501278e3bafca1aad4_1135x545_85.jpg','https://h2.appsimg.com/a.appsimg.com/upload/merchandise/ugcaudit/2021/06/25/167/61714f36-d585-11eb-b37d-02020ad0ad6d_512x512_90.jpg'),
	('7b7dfe134d2b4473aa025fb2c0daa578','eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee',4999,'https://h2.appsimg.com/a.appsimg.com/upload/brand/upcb/2021/10/29/13/ias_e3122735df7fad501278e3bafca1aad4_1135x545_85.jpg','https://h2.appsimg.com/a.appsimg.com/upload/merchandise/ugcaudit/2021/06/25/167/61714f36-d585-11eb-b37d-02020ad0ad6d_512x512_90.jpg');

CREATE TABLE `goods_category` (
  `id` varchar(50) NOT NULL,
  `category_id` varchar(50) DEFAULT NULL,
  `goods_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
);


INSERT INTO `goods_category` (`id`, `category_id`, `goods_id`)
VALUES
	('1','1','7b7dfe134d2b4473aa025fb2c0daa571'),
	('10','2','7b7dfe134d2b4473aa025fb2c0daa577'),
	('11','0','7b7dfe134d2b4473aa025fb2c0daa571'),
	('12','0','7b7dfe134d2b4473aa025fb2c0daa572'),
	('13','0','7b7dfe134d2b4473aa025fb2c0daa573'),
	('14','0','7b7dfe134d2b4473aa025fb2c0daa574'),
	('15','0','7b7dfe134d2b4473aa025fb2c0daa575'),
	('16','0','7b7dfe134d2b4473aa025fb2c0daa576'),
	('17','0','7b7dfe134d2b4473aa025fb2c0daa577'),
	('18','0','7b7dfe134d2b4473aa025fb2c0daa578'),
	('2','1','7b7dfe134d2b4473aa025fb2c0daa572'),
	('3','1','7b7dfe134d2b4473aa025fb2c0daa573'),
	('4','1','7b7dfe134d2b4473aa025fb2c0daa574'),
	('5','1','7b7dfe134d2b4473aa025fb2c0daa575'),
	('6','1','7b7dfe134d2b4473aa025fb2c0daa576'),
	('7','1','7b7dfe134d2b4473aa025fb2c0daa577'),
	('8','1','7b7dfe134d2b4473aa025fb2c0daa578'),
	('9','2','7b7dfe134d2b4473aa025fb2c0daa577');

CREATE TABLE `note` (
  `id` int NOT NULL AUTO_INCREMENT,
  `userId` int unsigned NOT NULL,
  `content` text,
  `lastUpdateTime` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
);


INSERT INTO `note` (`id`, `userId`, `content`, `lastUpdateTime`)
VALUES
	(1,0,'55555','2022-01-21 08:52:31'),
	(2,0,'555','2022-01-21 08:53:02'),
	(3,1,'5555','2022-01-21 08:55:53'),
	(4,1,'666','2022-01-21 08:56:17');

CREATE TABLE `orders` (
  `id` varchar(50) NOT NULL,
  `goods_id` varchar(50) NOT NULL,
  `quantity` int DEFAULT NULL,
  `order_id` varchar(50),
  PRIMARY KEY (`id`)
);


INSERT INTO `orders` (`id`, `goods_id`, `quantity`, `order_id`)
VALUES
	('12e019414723462ebd46b6e2676b797e','7b7dfe134d2b4473aa025fb2c0daa573',1,'7d9a9aca23d941c3a297c61ccacd37cf'),
	('19d5c6f3fe694a8493f473996bb237d7','7b7dfe134d2b4473aa025fb2c0daa573',1,'b45047c7450d40d59bad1105835fb494'),
	('359e46a96c344f879e31e475a430e354','7b7dfe134d2b4473aa025fb2c0daa574',2,'b45047c7450d40d59bad1105835fb494'),
	('3894101f406b41f1a9a3899f32dfa007','7b7dfe134d2b4473aa025fb2c0daa574',1,'4ee5ed6d84bf4e77899c5d48bb18dd53'),
	('49e639be467546c9a622f393c4011192','7b7dfe134d2b4473aa025fb2c0daa574',1,'266fef1f202940a4b496c63269bf8b8e'),
	('4e3db1ca1887405c868d3a30ee58492b','7b7dfe134d2b4473aa025fb2c0daa571',1,'f75585233454453c8700b607f8b92e0d'),
	('7d9cb1ae648340fa96d408138b49cef4','7b7dfe134d2b4473aa025fb2c0daa573',1,'1c9fc57159404155897e97276d1096cd'),
	('89726b252dcd454eae683cace07725db','7b7dfe134d2b4473aa025fb2c0daa572',4,'b45047c7450d40d59bad1105835fb494'),
	('9c3eeb3464994a4ba182dbd1e4a1d977','7b7dfe134d2b4473aa025fb2c0daa573',2,'266fef1f202940a4b496c63269bf8b8e'),
	('bc23bc403c4e42ff8e7e40ca3761686c','7b7dfe134d2b4473aa025fb2c0daa574',1,'76ce3cf9dff1491286dfba256bc12a92'),
	('e6c4eea9918a4e97b911b61a6f9c9fef','7b7dfe134d2b4473aa025fb2c0daa571',3,'266fef1f202940a4b496c63269bf8b8e'),
	('lbl1963cc57ceb345dfa9b1609e08f7c3be','7b7dfe134d2b4473aa025fb2c0daa572',1,'lblba32b81c55564dc0903c0932b7bf62de'),
	('lbl22aaa51ab5784f9d9cb3325af1ffc780','7b7dfe134d2b4473aa025fb2c0daa574',1,'lbl8b50a358da0943cf9703dfecfa263e3f'),
	('lbl28d49b26bfe842e7bbf54203bcc5bc4f','7b7dfe134d2b4473aa025fb2c0daa571',1,'lblc1531feb023c4d1da32248c9034df228'),
	('lbl2d5eb684b51d44718a27dea1705bedf5','7b7dfe134d2b4473aa025fb2c0daa574',2,'lbl23d7a2fa9e6143149d49571771dd6dde'),
	('lbl386c6192fe074fa99770f0b45584b0f2','7b7dfe134d2b4473aa025fb2c0daa571',5,'lbl2b89f6c543dc4e29bdf325c3aa1b91bd'),
	('lbl3bc2ac7983df48988808b29ec8753989','7b7dfe134d2b4473aa025fb2c0daa576',1,'lbl496e60d065fe4bd198f714402738cd09'),
	('lbl50d427c688744513b1a8f4cdb20ef3b6','7b7dfe134d2b4473aa025fb2c0daa574',1,'lbl8d69cd6e1abb4d80b39936c248f598e0'),
	('lbl80426834103049c980addc3116cffc4d','7b7dfe134d2b4473aa025fb2c0daa574',1,'lbl1062d2a35dff40b2847c0855c0383bc0'),
	('lbl886906f1e1b54bcca7da183f15348ec2','7b7dfe134d2b4473aa025fb2c0daa574',1,'lblf84fd0ea0dca4da0ad01873c77ed3655'),
	('lbla9811df052734a02846d5da71c14b094','7b7dfe134d2b4473aa025fb2c0daa578',1,'lbl419d3293da3f4cd591ac34eb9123cfb7'),
	('lbla9a0113c56fc44e2b9a52e7571b1eefb','7b7dfe134d2b4473aa025fb2c0daa573',1,'lbl23d7a2fa9e6143149d49571771dd6dde'),
	('lblaa385d423ef6463e93496b1cf1245c95','7b7dfe134d2b4473aa025fb2c0daa574',1,'lbl1ed427bb4a1a41c8be879c07d8bd3624'),
	('lblbbca073d48e0477ab2b8235380988e1f','7b7dfe134d2b4473aa025fb2c0daa574',1,'lbl1a970fe1d42849b4ab4e49549c232205'),
	('lblcefff28e14994e0c8dbf272ee69b1291','7b7dfe134d2b4473aa025fb2c0daa575',7,'lblc39229d734ba452d86e05a3982ea215d'),
	('lblfdc18493efcf4d05ab1d20b3621e132c','7b7dfe134d2b4473aa025fb2c0daa571',1,'lbl496e60d065fe4bd198f714402738cd09');


CREATE TABLE `sys_role` (
  `id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
);


INSERT INTO `sys_role` (`id`, `name`)
VALUES
	(1,'ROLE_ADMIN'),
	(2,'ROLE_USER');


CREATE TABLE `sys_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
);


INSERT INTO `sys_user` (`id`, `name`, `password`)
VALUES
	(1,'o4tAE5I9xQqSlAKpykWieTAcEXfo','$2a$10$kFPg8JzkYO40RTl3DnpUxOOWMncJAY.vFXa6uYSWkLDwiaBFZvd2C'),
	(4,'lblbc','$2a$10$hDl4sYqKI.8alZAO9V9oMOkH/zhjDCKAZKglQW0NnJOm9veaNchGO'),
	(5,'lbl','$2a$10$4OeO8oPIfAYKmTwF3cIXku9h3awL8nXyQceAzMX9XaobxvhRsjnIW');


CREATE TABLE `sys_user_role` (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`)
);


INSERT INTO `sys_user_role` (`user_id`, `role_id`)
VALUES
	(1,1),
	(4,1),
	(5,1);


CREATE TABLE `user_addr` (
  `id` varchar(50) NOT NULL DEFAULT '',
  `user_id` int NOT NULL DEFAULT '0',
  `name` varchar(50) NOT NULL DEFAULT '',
  `phone` varchar(50) NOT NULL DEFAULT '0',
  `region` varchar(100) ,
  `address` varchar(100) NOT NULL DEFAULT '',
  `default_address` tinyint NOT NULL,
  `addr_type` int DEFAULT NULL,
  PRIMARY KEY (`id`)
);

INSERT INTO `user_addr` (`id`, `user_id`, `name`, `phone`, `region`, `address`, `default_address`, `addr_type`)
VALUES
	('3153c41434a34d8c8fe7f51d94312b1c',0,'咕咕咕','56655525','Ghhhhh','Bhjjjjfjkhfdddsdgvgggfff',1,0),
	('566fa0c9b492465294cb5929bbd7bc10',2,'','56662','','',0,0),
	('c02aa4a7a0b9423a8fe3b8e86ed0dac2',2,'会','2354666','不好好','发广告',1,2),
	('c05fb972b09840bd805dc5e46086d4b2',1,'uuuu55','135999990000','','',0,0),
	('d4c5a113d2fb40dfb8ff16438bd0ea51',1,'009','9999','','',0,0),
	('lbl97b05845deda4cecbae25af5412b7ed0',5,'33','44','55','666',1,0),
	('lbla687ec8d55d54de7aba1d7562ca422f8',0,'4444','555','','',0,0);


CREATE TABLE `user_order` (
  `id` varchar(50) NOT NULL,
  `user_id` int NOT NULL,
  `status` int DEFAULT NULL,
  `create_time` bigint DEFAULT NULL,
  `user_addr_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

INSERT INTO `user_order` (`id`, `user_id`, `status`, `create_time`, `user_addr_id`)
VALUES
	('1c9fc57159404155897e97276d1096cd',2,1,1641900407164,NULL),
	('266fef1f202940a4b496c63269bf8b8e',0,2,1643118262939,NULL),
	('4ee5ed6d84bf4e77899c5d48bb18dd53',2,1,1641953716361,NULL),
	('76ce3cf9dff1491286dfba256bc12a92',0,4,1642429125735,NULL),
	('7d9a9aca23d941c3a297c61ccacd37cf',1,2,1642727555518,NULL),
	('b45047c7450d40d59bad1105835fb494',1,5,1642727281950,NULL),
	('f75585233454453c8700b607f8b92e0d',1,6,1641900639121,NULL),
	('lbl1062d2a35dff40b2847c0855c0383bc0',5,1,1643723128227,'lbl97b05845deda4cecbae25af5412b7ed0'),
	('lbl1a970fe1d42849b4ab4e49549c232205',5,1,1643723100242,'lbl97b05845deda4cecbae25af5412b7ed0'),
	('lbl1ed427bb4a1a41c8be879c07d8bd3624',5,1,1643723184894,'lbl97b05845deda4cecbae25af5412b7ed0'),
	('lbl23d7a2fa9e6143149d49571771dd6dde',1,1,1643724381961,'c05fb972b09840bd805dc5e46086d4b2'),
	('lbl2b89f6c543dc4e29bdf325c3aa1b91bd',5,1,1643203149490,'c05fb972b09840bd805dc5e46086d4b2'),
	('lbl419d3293da3f4cd591ac34eb9123cfb7',0,1,1643722700699,'lbla687ec8d55d54de7aba1d7562ca422f8'),
	('lbl496e60d065fe4bd198f714402738cd09',5,1,1643725381512,'lbl97b05845deda4cecbae25af5412b7ed0'),
	('lbl8b50a358da0943cf9703dfecfa263e3f',5,1,1643723118981,'lbl97b05845deda4cecbae25af5412b7ed0'),
	('lbl8d69cd6e1abb4d80b39936c248f598e0',5,1,1643723139974,'lbl97b05845deda4cecbae25af5412b7ed0'),
	('lblba32b81c55564dc0903c0932b7bf62de',5,2,1643239248469,'3153c41434a34d8c8fe7f51d94312b1c'),
	('lblc1531feb023c4d1da32248c9034df228',5,2,1643203533351,'3153c41434a34d8c8fe7f51d94312b1c'),
	('lblc39229d734ba452d86e05a3982ea215d',1,1,1643724911145,'c05fb972b09840bd805dc5e46086d4b2'),
	('lblf84fd0ea0dca4da0ad01873c77ed3655',0,1,1643723242407,'3153c41434a34d8c8fe7f51d94312b1c');
