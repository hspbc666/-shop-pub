CREATE TABLE `cart` (
  `id` varchar(50) NOT NULL,
  `user_id` int NOT NULL,
  `goods_id` varchar(50) NOT NULL,
  `quantity` int DEFAULT NULL,
  PRIMARY KEY (`id`)
);


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
  `original_link` varchar(200),
  PRIMARY KEY (`id`)
);

INSERT INTO `goods` (`id`, `name`, `price`, `long_pic`, `square_pic`, `desc_pic`, `original_link`) VALUES
	('1', 'HUAWEI P50 Pocket 4G 全网通 12GB+512GB 鎏光金', 1098800, 'https://res.vmallres.com/pimages//uomcdn/CN/pms/202112/gbom/6941487248902//428_428_D730AE43D511A27FCAFB0D2AD675CCB2mp.png', 'https://res.vmallres.com/pimages//uomcdn/CN/pms/202112/gbom/6941487248902//428_428_D730AE43D511A27FCAFB0D2AD675CCB2mp.png', 'https://res.vmallres.com//uomcdn/CN/pms/202201/F22F4FE4BE1BF1795F23BCEAF05D4ABE.jpg', ''),
	('10', 'ffit8蛋白棒 营养饱腹能量棒 运动健身代餐休闲零食 香蕉蓝莓口味', 9900, 'https://res.vmallres.com/pimages//uomcdn/CN/pms/202109/gbom/6972596160878//428_428_3AF78AA12FADDAF9EBE11A2CCE9D10A5mp.png', 'https://res.vmallres.com/pimages//uomcdn/CN/pms/202109/gbom/6972596160878//428_428_3AF78AA12FADDAF9EBE11A2CCE9D10A5mp.png', 'https://res.vmallres.com//uomcdn/CN/pms/202109/2CDA153B51B266DC4641B190EF17DC6A.jpg', ''),
	('11', '智利原瓶进口 火山顶峰珍藏长相思白葡萄酒 六支装750ml*6 权威酒评金奖', 88800, 'https://res.vmallres.com/pimages//uomcdn/CN/pms/202111/sbom/3102150244603//428_428_F31CBD2B7B52D107C419178A32A1EDE0mp.png', 'https://res.vmallres.com/pimages//uomcdn/CN/pms/202111/sbom/3102150244603//428_428_F31CBD2B7B52D107C419178A32A1EDE0mp.png', 'https://res.vmallres.com/pimages/detailImg/2021/01/15/9A9EF760117CA83CFAC8EAA1152D4E17B7AFBA4EBA82460F.jpg', ''),
	('12', '法国原瓶进口洋酒 轩尼诗XO礼盒 热力升华礼盒', 198000, 'https://res.vmallres.com/pimages//uomcdn/CN/pms/202111/gbom/72350004//428_428_26672AEF553DFBB98DC7B279E69B0FCEmp.png', 'https://res.vmallres.com/pimages//uomcdn/CN/pms/202111/gbom/72350004//428_428_26672AEF553DFBB98DC7B279E69B0FCEmp.png', 'https://res.vmallres.com//uomcdn/CN/pms/202111/5304B8937AD7FEE141F6640EC10F3CE1.jpg', ''),
	('2', 'HUAWEI MateBook E 2022款二合一笔记本 11代酷睿 i5 16G 512G Win11 12.6英寸 OLED原色全面屏 多设备互联 星际蓝', 699900, 'https://res.vmallres.com/pimages//product/6941487238989//428_428_047A8AAD89C45343328FD313EEB94340C088DECAAFD4DB68mp.png', 'https://res.vmallres.com/pimages//product/6941487238989//428_428_047A8AAD89C45343328FD313EEB94340C088DECAAFD4DB68mp.png', 'https://res.vmallres.com/pimages/detailImg/2021/11/16/907B9DB84A69B92217450AC0670D0B1A66944FE9070C7F5A.jpg', ''),
	('3', 'HUAWEI MatePad Pro 10.8英寸 2021款 8GB+128GB Wi-Fi 夜阑灰 支持M-Pencil 触控笔 绚丽全面屏平板电脑', 349900, 'https://res.vmallres.com/pimages//uomcdn/CN/pms/202202/displayProduct/10086014411347/428_428_a_mobile197601AECFA46672D9EB830B9F25AAEA.png', 'https://res.vmallres.com/pimages//product/6941487227624//428_428_5B52FE52A204DB8F572BB1B5B84B024991A392D7AD812FFFmp.png', 'https://res.vmallres.com//uomcdn/CN/pms/202112/7907B99DAA00DE88585CD2B24247E551.jpg', ''),
	('4', 'HUAWEI MateView GT 27英寸2K超清曲面屏 165Hz刷新率90% P3色域 低蓝光无频闪莱茵护眼认证（无HDMI线缆）', 209900, 'https://h2.appsimg.com/a.appsimg.com/upload/brand/upcb/2022/01/01/73/ias_9a761022729bd2e463f4e7db85b9581d_1135x545_85.jpg', 'https://res.vmallres.com/pimages//uomcdn/CN/pms/202202/gbom/6941487232963//428_428_91B768F89D6CBB0B25384A645603CBD8mp.png', 'https://res.vmallres.com//uomcdn/CN/pms/202112/E79EFA13D0888C6728346389A327CD4F.jpg', ''),
	('5', '华为黑白激光多功能打印机 HUAWEI PixLab X1 HarmonyOS 靠近配网 一碰打印 复印扫描', 174900, 'https://h2.appsimg.com/a.appsimg.com/upload/brand/upcb/2021/10/04/127/ias_9c7558d2138786780222b653aab39b93_1135x545_85.jpg', 'https://res.vmallres.com/pimages//product/6941487237746//428_428_D1BA03B40ACBDE152A51E93ACE23DA9A227EE48AB3606C39mp.png', 'https://res.vmallres.com/pimages/detailImg/2021/10/22/917180BF31813CC91FBFC3363245AB3CF8AA6392CBC99AED.jpg', ''),
	('6', 'HUAWEI MateStation S 小机箱台式机 7nm锐龙八核R7-4700G 16GB+512GB DDR4双通道内存 多设备高效协同 一键指纹开机解锁 满载28分贝智慧风冷系统 Win10三年保修+华为显示器 S24（含HDMI线）', 489900, 'https://h2.appsimg.com/a.appsimg.com/upload/brand/upcb/2021/11/06/124/ias_5a816dc1f2b663e0606aa10d80bf99ca_1135x545_85.jpg', 'https://res.vmallres.com/pimages//product/4601010000203//428_428_5FC77F1CC357DEC5F53D46CE9032F031F1709BAFFBDF1143mp.png', 'https://res.vmallres.com/pimages/detailImg/2021/09/11/FB0039435C9C788D5874C10749B9826504124CE4E46FBA10.jpg', ''),
	('7', '智利原瓶进口 火山珍藏混酿红葡萄酒十周年纪念版 六支装750ml*6 赤霞珠西拉融合', 100800, 'https://res.vmallres.com/pimages//uomcdn/CN/pms/202111/sbom/3501020005302//428_428_39611F2854A6EF45D1D638706D8F8E75mp.png', 'https://res.vmallres.com/pimages//uomcdn/CN/pms/202111/sbom/3501020005302//428_428_39611F2854A6EF45D1D638706D8F8E75mp.png', 'https://res.vmallres.com//uomcdn/CN/pms/202109/1966DA14E45C3B72612A4E3BC0E1103E.jpg', ''),
	('8', 'HUAWEI P50 4G 全网通 8GB+256GB（可可茶金）', 498800, 'https://h2.appsimg.com/a.appsimg.com/upload/brand/upcb/2021/10/29/13/ias_e3122735df7fad501278e3bafca1aad4_1135x545_85.jpg', 'https://res.vmallres.com/pimages//product/6941487235780//428_428_C9565892589F30AF38DD6055FDAAAFB2621AFE267731083Fmp.png', 'https://res.vmallres.com/pimages/detailImg/2021/09/29/1270D124C03769CC0A1F6DC0F5532F2441442B5BE1F00A02.png', ''),
	('9', '智利原瓶进口 火山特科托尼亚赤霞珠红葡萄酒 六支装', 147600, 'https://res.vmallres.com/pimages//uomcdn/CN/pms/202111/sbom/3102150246302//428_428_579E5D33C0398F426E4C780E960653F9mp.png', 'https://res.vmallres.com/pimages//uomcdn/CN/pms/202111/sbom/3102150246302//428_428_579E5D33C0398F426E4C780E960653F9mp.png', 'https://res.vmallres.com//uomcdn/CN/pms/202109/59EE46F995594FD8AEEC42B4D036AB96.jpg', '');

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
	(1,'lbl','$2a$10$4OeO8oPIfAYKmTwF3cIXku9h3awL8nXyQceAzMX9XaobxvhRsjnIW'),
	(2,'lblbc','$2a$10$4OeO8oPIfAYKmTwF3cIXku9h3awL8nXyQceAzMX9XaobxvhRsjnIW');


CREATE TABLE `sys_user_role` (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`)
);


INSERT INTO `sys_user_role` (`user_id`, `role_id`)
VALUES
	(1,1),
	(2,1);


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
	('3153c41434a34d8c8fe7f51d94312b1c',1,'蓝不蓝编程','13900000000','北京市朝阳区XX小区',1);


CREATE TABLE `user_order` (
  `id` varchar(50) NOT NULL,
  `user_id` int NOT NULL,
  `status` int DEFAULT NULL,
  `create_time` bigint DEFAULT NULL,
  `user_addr_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
);