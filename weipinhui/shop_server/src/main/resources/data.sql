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
  `desc_pic` varchar(200),
  PRIMARY KEY (`id`)
);

INSERT INTO `goods` (`id`, `name`, `price`, `long_pic`, `square_pic`, `desc_pic`)
VALUES
	('1','HUAWEI P50 Pocket 4G 全网通 12GB+512GB 鎏光金',1098800,'https://res.vmallres.com/pimages//uomcdn/CN/pms/202112/gbom/6941487248902//428_428_D730AE43D511A27FCAFB0D2AD675CCB2mp.png','https://res.vmallres.com/pimages//uomcdn/CN/pms/202112/gbom/6941487248902//428_428_D730AE43D511A27FCAFB0D2AD675CCB2mp.png','https://res.vmallres.com//uomcdn/CN/pms/202201/F22F4FE4BE1BF1795F23BCEAF05D4ABE.jpg'),
	('10','ffit8蛋白棒 营养饱腹能量棒 运动健身代餐休闲零食 香蕉蓝莓口味',9900,'https://res.vmallres.com/pimages//uomcdn/CN/pms/202109/gbom/6972596160878//428_428_3AF78AA12FADDAF9EBE11A2CCE9D10A5mp.png','https://res.vmallres.com/pimages//uomcdn/CN/pms/202109/gbom/6972596160878//428_428_3AF78AA12FADDAF9EBE11A2CCE9D10A5mp.png','https://res.vmallres.com//uomcdn/CN/pms/202109/2CDA153B51B266DC4641B190EF17DC6A.jpg'),
	('11','智利原瓶进口 火山顶峰珍藏长相思白葡萄酒 六支装750ml*6 权威酒评金奖',88800,'https://res.vmallres.com/pimages//uomcdn/CN/pms/202111/sbom/3102150244603//428_428_F31CBD2B7B52D107C419178A32A1EDE0mp.png','https://res.vmallres.com/pimages//uomcdn/CN/pms/202111/sbom/3102150244603//428_428_F31CBD2B7B52D107C419178A32A1EDE0mp.png','https://res.vmallres.com/pimages/detailImg/2021/01/15/9A9EF760117CA83CFAC8EAA1152D4E17B7AFBA4EBA82460F.jpg'),
	('12','法国原瓶进口洋酒 轩尼诗XO礼盒 热力升华礼盒',198000,'https://res.vmallres.com/pimages//uomcdn/CN/pms/202111/gbom/72350004//428_428_26672AEF553DFBB98DC7B279E69B0FCEmp.png','https://res.vmallres.com/pimages//uomcdn/CN/pms/202111/gbom/72350004//428_428_26672AEF553DFBB98DC7B279E69B0FCEmp.png','https://res.vmallres.com//uomcdn/CN/pms/202111/5304B8937AD7FEE141F6640EC10F3CE1.jpg'),
	('2','HUAWEI MateBook E 2022款二合一笔记本 11代酷睿 i5 16G 512G Win11 12.6英寸 OLED原色全面屏 多设备互联 星际蓝',699900,'https://res.vmallres.com/pimages//product/6941487238989//428_428_047A8AAD89C45343328FD313EEB94340C088DECAAFD4DB68mp.png','https://res.vmallres.com/pimages//product/6941487238989//428_428_047A8AAD89C45343328FD313EEB94340C088DECAAFD4DB68mp.png','https://res.vmallres.com/pimages/detailImg/2021/11/16/907B9DB84A69B92217450AC0670D0B1A66944FE9070C7F5A.jpg'),
	('3','HUAWEI MatePad Pro 10.8英寸 2021款 8GB+128GB Wi-Fi 夜阑灰 支持M-Pencil 触控笔 绚丽全面屏平板电脑',349900,'https://res.vmallres.com/pimages//uomcdn/CN/pms/202202/displayProduct/10086014411347/428_428_a_mobile197601AECFA46672D9EB830B9F25AAEA.png','https://res.vmallres.com/pimages//product/6941487227624//428_428_5B52FE52A204DB8F572BB1B5B84B024991A392D7AD812FFFmp.png','https://res.vmallres.com//uomcdn/CN/pms/202112/7907B99DAA00DE88585CD2B24247E551.jpg'),
	('4','HUAWEI MateView GT 27英寸2K超清曲面屏 165Hz刷新率90% P3色域 低蓝光无频闪莱茵护眼认证（无HDMI线缆）',209900,'https://h2.appsimg.com/a.appsimg.com/upload/brand/upcb/2022/01/01/73/ias_9a761022729bd2e463f4e7db85b9581d_1135x545_85.jpg','https://res.vmallres.com/pimages//uomcdn/CN/pms/202202/gbom/6941487232963//428_428_91B768F89D6CBB0B25384A645603CBD8mp.png','https://res.vmallres.com//uomcdn/CN/pms/202112/E79EFA13D0888C6728346389A327CD4F.jpg'),
	('5','华为黑白激光多功能打印机 HUAWEI PixLab X1 HarmonyOS 靠近配网 一碰打印 复印扫描',174900,'https://h2.appsimg.com/a.appsimg.com/upload/brand/upcb/2021/10/04/127/ias_9c7558d2138786780222b653aab39b93_1135x545_85.jpg','https://res.vmallres.com/pimages//product/6941487237746//428_428_D1BA03B40ACBDE152A51E93ACE23DA9A227EE48AB3606C39mp.png','https://res.vmallres.com/pimages/detailImg/2021/10/22/917180BF31813CC91FBFC3363245AB3CF8AA6392CBC99AED.jpg'),
	('6','HUAWEI MateStation S 小机箱台式机 7nm锐龙八核R7-4700G 16GB+512GB DDR4双通道内存 多设备高效协同 一键指纹开机解锁 满载28分贝智慧风冷系统 Win10三年保修+华为显示器 S24（含HDMI线）',489900,'https://h2.appsimg.com/a.appsimg.com/upload/brand/upcb/2021/11/06/124/ias_5a816dc1f2b663e0606aa10d80bf99ca_1135x545_85.jpg','https://res.vmallres.com/pimages//product/4601010000203//428_428_5FC77F1CC357DEC5F53D46CE9032F031F1709BAFFBDF1143mp.png','https://res.vmallres.com/pimages/detailImg/2021/09/11/FB0039435C9C788D5874C10749B9826504124CE4E46FBA10.jpg'),
	('7','智利原瓶进口 火山珍藏混酿红葡萄酒十周年纪念版 六支装750ml*6 赤霞珠西拉融合',100800,'https://res.vmallres.com/pimages//uomcdn/CN/pms/202111/sbom/3501020005302//428_428_39611F2854A6EF45D1D638706D8F8E75mp.png','https://res.vmallres.com/pimages//uomcdn/CN/pms/202111/sbom/3501020005302//428_428_39611F2854A6EF45D1D638706D8F8E75mp.png','https://res.vmallres.com//uomcdn/CN/pms/202109/1966DA14E45C3B72612A4E3BC0E1103E.jpg'),
	('8','HUAWEI P50 4G 全网通 8GB+256GB（可可茶金）',498800,'https://h2.appsimg.com/a.appsimg.com/upload/brand/upcb/2021/10/29/13/ias_e3122735df7fad501278e3bafca1aad4_1135x545_85.jpg','https://res.vmallres.com/pimages//product/6941487235780//428_428_C9565892589F30AF38DD6055FDAAAFB2621AFE267731083Fmp.png','https://res.vmallres.com/pimages/detailImg/2021/09/29/1270D124C03769CC0A1F6DC0F5532F2441442B5BE1F00A02.png'),
	('9','智利原瓶进口 火山特科托尼亚赤霞珠红葡萄酒 六支装',147600,'https://res.vmallres.com/pimages//uomcdn/CN/pms/202111/sbom/3102150246302//428_428_579E5D33C0398F426E4C780E960653F9mp.png','https://res.vmallres.com/pimages//uomcdn/CN/pms/202111/sbom/3102150246302//428_428_579E5D33C0398F426E4C780E960653F9mp.png','https://res.vmallres.com//uomcdn/CN/pms/202109/59EE46F995594FD8AEEC42B4D036AB96.jpg');

CREATE TABLE `goods_category` (
  `id` varchar(50) NOT NULL,
  `category_id` varchar(50) DEFAULT NULL,
  `goods_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
);


INSERT INTO `goods_category` (`id`, `category_id`, `goods_id`) VALUES
	('1', '1', '1'),
	('10', '1', '10'),
	('11', '10', '9'),
	('12', '10', '10'),
	('13', '10', '11'),
	('14', '10', '12'),
	('15', '0', '1'),
	('16', '0', '2'),
	('17', '0', '3'),
	('18', '0', '4'),
	('19', '0', '5'),
	('2', '1', '2'),
	('20', '0', '6'),
	('21', '0', '7'),
	('22', '0', '8'),
	('23', '0', '9'),
	('3', '1', '4'),
	('4', '1', '6'),
	('5', '1', '8'),
	('6', '1', '3'),
	('7', '1', '5'),
	('8', '1', '7'),
	('9', '1', '9');

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
  `address` varchar(100) NOT NULL DEFAULT '',
  `default_address` tinyint NOT NULL,
  PRIMARY KEY (`id`)
);

INSERT INTO `user_addr` (`id`, `user_id`, `name`, `phone`, `address`, `default_address`)
VALUES
	('3153c41434a34d8c8fe7f51d94312b1c',0,'咕咕咕','Ghhhhh','Bhjjjjfjkhfdddsdgvgggfff',1),
	('566fa0c9b492465294cb5929bbd7bc10',2,'','','',0),
	('c02aa4a7a0b9423a8fe3b8e86ed0dac2',2,'会','不好好','发广告',1),
	('c05fb972b09840bd805dc5e46086d4b2',1,'135999990000','','',0),
	('d4c5a113d2fb40dfb8ff16438bd0ea51',1,'9999','','',0),
	('lbl97b05845deda4cecbae25af5412b7ed0',5,'44','55','666',1),
	('lbla687ec8d55d54de7aba1d7562ca422f8',0,'555','','',0);


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
