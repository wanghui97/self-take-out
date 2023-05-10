/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50731
Source Host           : localhost:3306
Source Database       : self_take_out

Target Server Type    : MYSQL
Target Server Version : 50731
File Encoding         : 65001

Date: 2023-05-10 09:57:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for address_book
-- ----------------------------
DROP TABLE IF EXISTS `address_book`;
CREATE TABLE `address_book` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `consignee` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '收货人',
  `sex` tinyint(4) NOT NULL COMMENT '性别 0 女 1 男',
  `phone` varchar(11) COLLATE utf8_bin NOT NULL COMMENT '手机号',
  `province_code` varchar(12) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '省级区划编号',
  `province_name` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '省级名称',
  `city_code` varchar(12) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '市级区划编号',
  `city_name` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '市级名称',
  `district_code` varchar(12) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '区级区划编号',
  `district_name` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '区级名称',
  `detail` varchar(200) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '详细地址',
  `label` varchar(100) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '标签',
  `is_default` tinyint(1) NOT NULL DEFAULT '0' COMMENT '默认 0 否 1是',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user` bigint(20) NOT NULL COMMENT '创建人',
  `update_user` bigint(20) NOT NULL COMMENT '修改人',
  `del_flag` int(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='地址管理';

-- ----------------------------
-- Records of address_book
-- ----------------------------
INSERT INTO `address_book` VALUES ('1', '1', '小明', '1', '17395043749', null, null, null, null, null, null, '昌平区金燕龙办公楼', '家', '0', '2021-07-20 17:22:12', '2023-05-08 22:34:29', '1417012167126876200', '1', '0');
INSERT INTO `address_book` VALUES ('2', '2', '小李', '1', '13512345678', null, null, null, null, null, null, '测试', '家', '0', '2021-07-20 17:23:47', '2021-07-20 17:23:47', '1417012167126876162', '1417012167126876162', '0');
INSERT INTO `address_book` VALUES ('4', '1', '李四', '1', '17395043749', null, null, null, null, null, null, '银川市', '家', '0', '2023-05-08 18:05:23', '2023-05-08 18:30:11', '1', '1', '1');
INSERT INTO `address_book` VALUES ('5', '1', '王辉', '1', '17395043749', null, null, null, null, null, null, '银川市', '家', '1', '2023-05-08 21:47:35', '2023-05-08 22:34:29', '1', '1', '0');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` int(11) DEFAULT NULL COMMENT '类型   1 菜品分类 2 套餐分类',
  `name` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '分类名称',
  `sort` int(11) NOT NULL DEFAULT '0' COMMENT '顺序',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user` bigint(20) NOT NULL COMMENT '创建人',
  `update_user` bigint(20) NOT NULL COMMENT '修改人',
  `del_flag` int(1) NOT NULL DEFAULT '0' COMMENT '删除标志-0:正常,1:删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `idx_category_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='菜品及套餐分类';

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', '1', '湘菜', '1', '2021-05-27 09:16:58', '2021-07-15 20:25:23', '1', '1', '0');
INSERT INTO `category` VALUES ('2', '1', '川菜', '2', '2021-05-27 09:17:07', '2021-06-02 14:27:22', '1', '1', '0');
INSERT INTO `category` VALUES ('3', '1', '粤菜', '3', '2021-05-27 09:17:28', '2021-07-09 14:37:13', '1', '1', '0');
INSERT INTO `category` VALUES ('4', '1', '饮品', '11', '2021-07-09 11:36:15', '2021-07-09 14:39:15', '1', '1', '0');
INSERT INTO `category` VALUES ('5', '2', '商务套餐', '5', '2021-07-09 11:40:30', '2021-07-09 14:43:45', '1', '1', '0');
INSERT INTO `category` VALUES ('6', '1', '主食', '12', '2021-07-09 14:30:07', '2021-07-09 14:39:19', '1', '1', '0');
INSERT INTO `category` VALUES ('7', '2', '儿童套餐', '6', '2021-07-09 14:35:02', '2021-07-09 14:39:05', '1', '1', '0');
INSERT INTO `category` VALUES ('8', '1', '宁夏菜', '1', '2023-05-04 14:34:16', '2023-05-04 14:34:16', '1', '1', '0');
INSERT INTO `category` VALUES ('9', '1', '甘肃菜', '1', '2023-05-04 14:34:31', '2023-05-04 14:34:31', '1', '1', '0');
INSERT INTO `category` VALUES ('10', '1', '北京菜', '23', '2023-05-04 14:34:45', '2023-05-04 14:45:22', '1', '1', '0');
INSERT INTO `category` VALUES ('11', '1', '天津菜', '3', '2023-05-04 14:35:04', '2023-05-04 14:35:04', '1', '1', '0');
INSERT INTO `category` VALUES ('12', '2', '快餐', '1', '2023-05-04 14:35:38', '2023-05-04 14:35:38', '1', '1', '1');
INSERT INTO `category` VALUES ('13', '1', '大苏打', '22', '2023-05-04 15:19:32', '2023-05-04 15:20:29', '1', '1', '1');
INSERT INTO `category` VALUES ('14', '1', '我去饿v', '2', '2023-05-04 15:26:12', '2023-05-04 15:26:12', '1', '1', '1');
INSERT INTO `category` VALUES ('15', '1', '十大', '23', '2023-05-04 15:26:26', '2023-05-04 15:26:43', '1', '1', '1');

-- ----------------------------
-- Table structure for dish
-- ----------------------------
DROP TABLE IF EXISTS `dish`;
CREATE TABLE `dish` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '菜品名称',
  `category_id` bigint(20) NOT NULL COMMENT '菜品分类id',
  `price` decimal(10,2) NOT NULL COMMENT '菜品价格',
  `code` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '商品码',
  `image` varchar(200) COLLATE utf8_bin NOT NULL COMMENT '图片',
  `description` varchar(400) COLLATE utf8_bin DEFAULT NULL COMMENT '描述信息',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '0 停售 1 起售',
  `sort` int(11) NOT NULL DEFAULT '0' COMMENT '顺序',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user` bigint(20) NOT NULL COMMENT '创建人',
  `update_user` bigint(20) NOT NULL COMMENT '修改人',
  `del_flag` int(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `idx_dish_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='菜品管理';

-- ----------------------------
-- Records of dish
-- ----------------------------
INSERT INTO `dish` VALUES ('1', '辣子鸡', '1', '7800.00', '222222222', 'f966a38e-0780-40be-bb52-5699d13cb3d9.jpg', '来自鲜嫩美味的小鸡，值得一尝', '1', '0', '2021-05-27 09:38:43', '2023-05-05 10:22:06', '1', '1', '0');
INSERT INTO `dish` VALUES ('2', '毛氏红烧肉', '1', '6800.00', '123412341234', '0a3b3288-3446-4420-bbff-f263d0c02d8e.jpg', '毛氏红烧肉毛氏红烧肉，确定不来一份？', '1', '0', '2021-05-27 09:40:19', '2023-05-04 21:40:44', '1', '1', '0');
INSERT INTO `dish` VALUES ('3', '组庵鱼翅', '1', '4800.00', '123412341234', '740c79ce-af29-41b8-b78d-5f49c96e38c4.jpg', '组庵鱼翅，看图足以表明好吃程度', '1', '0', '2021-05-27 09:41:19', '2023-05-04 21:40:44', '1', '1', '0');
INSERT INTO `dish` VALUES ('4', '霸王别姬', '1', '12800.00', '123412341234', '057dd338-e487-4bbc-a74c-0384c44a9ca3.jpg', '还有什么比霸王别姬更美味的呢？', '1', '0', '2021-05-27 09:43:08', '2021-05-27 09:43:08', '1', '1', '0');
INSERT INTO `dish` VALUES ('5', '全家福', '1', '11800.00', '23412341234', 'a53a4e6a-3b83-4044-87f9-9d49b30a8fdc.jpg', '别光吃肉啦，来份全家福吧，让你长寿又美味', '1', '0', '2021-05-27 09:44:08', '2021-05-27 09:44:08', '1', '1', '0');
INSERT INTO `dish` VALUES ('6', '邵阳猪血丸子', '1', '13800.00', '1246812345678', '2a50628e-7758-4c51-9fbb-d37c61cdacad.jpg', '看，美味不？来嘛来嘛，这才是最爱吖', '1', '0', '2021-05-27 09:45:12', '2021-05-27 09:45:12', '1', '1', '0');
INSERT INTO `dish` VALUES ('7', '口味蛇', '1', '16800.00', '1234567812345678', '0f4bd884-dc9c-4cf9-b59e-7d5958fec3dd.jpg', '爬行界的扛把子，东兴-口味蛇，让你欲罢不能', '1', '0', '2021-05-27 09:46:23', '2021-05-27 09:46:23', '1', '1', '0');
INSERT INTO `dish` VALUES ('8', '辣子鸡丁', '2', '8800.00', '2346812468', 'ef2b73f2-75d1-4d3a-beea-22da0e1421bd.jpg', '辣子鸡丁，辣子鸡丁，永远的魂', '1', '0', '2021-05-27 09:49:16', '2021-05-27 09:49:16', '1', '1', '0');
INSERT INTO `dish` VALUES ('9', '麻辣兔头', '2', '19800.00', '123456787654321', '2a2e9d66-b41d-4645-87bd-95f2cfeed218.jpg', '麻辣兔头的详细制作，麻辣鲜香，色泽红润，回味悠长', '1', '0', '2021-05-27 09:52:24', '2021-05-27 09:52:24', '1', '1', '0');
INSERT INTO `dish` VALUES ('10', '蒜泥白肉', '2', '9800.00', '1234321234321', 'd2f61d70-ac85-4529-9b74-6d9a2255c6d7.jpg', '多么的有食欲啊', '1', '0', '2021-05-27 09:54:30', '2021-05-27 09:54:30', '1', '1', '0');
INSERT INTO `dish` VALUES ('11', '鱼香肉丝', '2', '3800.00', '1234212321234', '8dcfda14-5712-4d28-82f7-ae905b3c2308.jpg', '鱼香肉丝简直就是我们童年回忆的一道经典菜，上学的时候点个鱼香肉丝盖饭坐在宿舍床上看着肥皂剧，绝了！现在完美复刻一下上学的时候感觉', '1', '0', '2021-05-27 09:55:13', '2021-05-27 09:55:13', '1', '1', '0');
INSERT INTO `dish` VALUES ('12', '麻辣水煮鱼', '2', '14800.00', '2345312·345321', '1fdbfbf3-1d86-4b29-a3fc-46345852f2f8.jpg', '鱼片是买的切好的鱼片，放几个虾，增加味道', '1', '0', '2021-05-27 09:58:15', '2021-05-27 09:58:15', '1', '1', '0');
INSERT INTO `dish` VALUES ('13', '鱼香炒鸡蛋', '2', '2000.00', '23456431·23456', '0f252364-a561-4e8d-8065-9a6797a6b1d3.jpg', '鱼香菜也是川味的特色。里面没有鱼却鱼香味', '1', '0', '2021-05-27 09:59:06', '2021-05-27 09:59:06', '1', '1', '0');
INSERT INTO `dish` VALUES ('14', '脆皮烧鹅', '3', '12800.00', '123456786543213456', 'e476f679-5c15-436b-87fa-8c4e9644bf33.jpeg', '“广东烤鸭美而香，却胜烧鹅说古冈（今新会），燕瘦环肥各佳妙，君休偏重便宜坊”，可见烧鹅与烧鸭在粤菜之中已早负盛名。作为广州最普遍和最受欢迎的烧烤肉食，以它的“色泽金红，皮脆肉嫩，味香可口”的特色，在省城各大街小巷的烧卤店随处可见。', '1', '0', '2021-05-27 10:20:27', '2021-05-27 10:20:27', '1', '1', '0');
INSERT INTO `dish` VALUES ('15', '白切鸡', '3', '6600.00', '12345678654', '9ec6fc2d-50d2-422e-b954-de87dcd04198.jpeg', '白切鸡是一道色香味俱全的特色传统名肴，又叫白斩鸡，是粤菜系鸡肴中的一种，始于清代的民间。白切鸡通常选用细骨农家鸡与沙姜、蒜茸等食材，慢火煮浸白切鸡皮爽肉滑，清淡鲜美。著名的泮溪酒家白切鸡，曾获商业部优质产品金鼎奖。湛江白切鸡更是驰名粤港澳。粤菜厨坛中，鸡的菜式有200余款之多，而最为人常食不厌的正是白切鸡，深受食家青睐。', '1', '0', '2021-05-27 10:21:48', '2021-05-27 10:21:48', '1', '1', '0');
INSERT INTO `dish` VALUES ('16', '烤乳猪', '3', '38800.00', '213456432123456', '2e96a7e3-affb-438e-b7c3-e1430df425c9.jpeg', '广式烧乳猪主料是小乳猪，辅料是蒜，调料是五香粉、芝麻酱、八角粉等，本菜品主要通过将食材放入炭火中烧烤而成。烤乳猪是广州最著名的特色菜，并且是“满汉全席”中的主打菜肴之一。烤乳猪也是许多年来广东人祭祖的祭品之一，是家家都少不了的应节之物，用乳猪祭完先人后，亲戚们再聚餐食用。', '1', '0', '2021-05-27 10:22:39', '2021-05-27 10:22:39', '1', '1', '0');
INSERT INTO `dish` VALUES ('17', '脆皮乳鸽', '3', '10800.00', '1234563212345', '3fabb83a-1c09-4fd9-892b-4ef7457daafa.jpeg', '“脆皮乳鸽”是广东菜中的一道传统名菜，属于粤菜系，具有皮脆肉嫩、色泽红亮、鲜香味美的特点，常吃可使身体强健，清肺顺气。随着菜品制作工艺的不断发展，逐渐形成了熟炸法、生炸法和烤制法三种制作方法。无论那种制作方法，都是在鸽子经过一系列的加工，挂脆皮水后再加工而成，正宗的“脆皮乳鸽皮脆肉嫩、色泽红亮、鲜香味美、香气馥郁。这三种方法的制作过程都不算复杂，但想达到理想的效果并不容易。', '1', '0', '2021-05-27 10:23:19', '2021-05-27 10:23:19', '1', '1', '0');
INSERT INTO `dish` VALUES ('18', '清蒸河鲜海鲜', '3', '38800.00', '1234567876543213456', '1405081e-f545-42e1-86a2-f7559ae2e276.jpeg', '新鲜的海鲜，清蒸是最好的处理方式。鲜，体会为什么叫海鲜。清蒸是广州最经典的烹饪手法，过去岭南地区由于峻山大岭阻隔，交通不便，经济发展起步慢，自家打的鱼放在锅里煮了就吃，没有太多的讲究，但却发现这清淡的煮法能使鱼的鲜甜跃然舌尖。', '1', '0', '2021-05-27 10:26:11', '2021-05-27 10:26:11', '1', '1', '0');
INSERT INTO `dish` VALUES ('19', '老火靓汤', '3', '49800.00', '123456786532455', '583df4b7-a159-4cfc-9543-4f666120b25f.jpeg', '老火靓汤又称广府汤，是广府人传承数千年的食补养生秘方，慢火煲煮的中华老火靓汤，火候足，时间长，既取药补之效，又取入口之甘甜。 广府老火汤种类繁多，可以用各种汤料和烹调方法，烹制出各种不同口味、不同功效的汤来。', '1', '0', '2021-05-27 10:28:14', '2021-05-27 10:28:14', '1', '1', '0');
INSERT INTO `dish` VALUES ('20', '上汤焗龙虾', '3', '108800.00', '1234567865432', '5b8d2da3-3744-4bb3-acdc-329056b8259d.jpeg', '上汤焗龙虾是一道色香味俱全的传统名菜，属于粤菜系。此菜以龙虾为主料，配以高汤制成的一道海鲜美食。本品肉质洁白细嫩，味道鲜美，蛋白质含量高，脂肪含量低，营养丰富。是色香味俱全的传统名菜。', '1', '0', '2021-05-27 10:29:20', '2021-05-27 10:29:20', '1', '1', '0');
INSERT INTO `dish` VALUES ('21', '北冰洋', '4', '500.00', '', 'c99e0aab-3cb7-4eaa-80fd-f47d4ffea694.png', '', '1', '0', '2021-07-09 11:39:35', '2021-07-09 15:12:18', '1', '1', '0');
INSERT INTO `dish` VALUES ('22', '王老吉', '4', '500.00', '', '00874a5e-0df2-446b-8f69-a30eb7d88ee8.png', '', '1', '0', '2021-07-09 14:29:20', '2021-07-12 09:09:16', '1', '1', '0');
INSERT INTO `dish` VALUES ('23', '米饭', '6', '200.00', '', 'ee04a05a-1230-46b6-8ad5-1a95b140fff3.png', '', '1', '0', '2021-07-09 14:31:17', '2023-05-04 20:59:47', '1', '1', '0');
INSERT INTO `dish` VALUES ('24', '米饭11', '6', '200.00', '', 'ee04a05a-1230-46b6-8ad5-1a95b140fff3.png', '', '1', '0', '2021-07-09 14:31:17', '2023-05-04 20:59:47', '1', '1', '0');
INSERT INTO `dish` VALUES ('25', '米饭111', '6', '200.00', '', 'ee04a05a-1230-46b6-8ad5-1a95b140fff3.png', '', '1', '0', '2021-07-09 14:31:17', '2023-05-04 20:59:47', '1', '1', '0');
INSERT INTO `dish` VALUES ('26', '米饭112', '6', '200.00', '', 'ee04a05a-1230-46b6-8ad5-1a95b140fff3.png', '', '1', '0', '2021-07-09 14:31:17', '2023-05-04 20:59:47', '1', '1', '0');
INSERT INTO `dish` VALUES ('27', '米饭114', '6', '200.00', '', 'ee04a05a-1230-46b6-8ad5-1a95b140fff3.png', '', '1', '0', '2021-07-09 14:31:17', '2023-05-04 20:59:47', '1', '1', '0');

-- ----------------------------
-- Table structure for dish_flavor
-- ----------------------------
DROP TABLE IF EXISTS `dish_flavor`;
CREATE TABLE `dish_flavor` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dish_id` bigint(20) NOT NULL COMMENT '菜品',
  `name` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '口味名称',
  `value` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '口味数据list',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user` bigint(20) NOT NULL COMMENT '创建人',
  `update_user` bigint(20) NOT NULL COMMENT '修改人',
  `del_flag` int(11) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='菜品口味关系表';

-- ----------------------------
-- Records of dish_flavor
-- ----------------------------
INSERT INTO `dish_flavor` VALUES ('4', '9', '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]', '2021-05-27 09:39:30', '2021-05-27 09:39:30', '1', '1', '0');
INSERT INTO `dish_flavor` VALUES ('5', '8', '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-27 09:39:30', '2021-05-27 09:39:30', '1', '1', '0');
INSERT INTO `dish_flavor` VALUES ('6', '2', '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]', '2021-05-27 09:40:19', '2021-05-27 09:40:19', '1', '1', '0');
INSERT INTO `dish_flavor` VALUES ('7', '2', '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-27 09:40:19', '2021-05-27 09:40:19', '1', '1', '0');
INSERT INTO `dish_flavor` VALUES ('8', '3', '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-27 09:41:19', '2021-05-27 09:41:19', '1', '1', '0');
INSERT INTO `dish_flavor` VALUES ('9', '3', '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-27 09:41:19', '2021-05-27 09:41:19', '1', '1', '0');
INSERT INTO `dish_flavor` VALUES ('10', '6', '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]', '2021-05-27 09:42:16', '2021-05-27 09:42:16', '1', '1', '0');
INSERT INTO `dish_flavor` VALUES ('11', '7', '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-27 09:42:16', '2021-05-27 09:42:16', '1', '1', '0');
INSERT INTO `dish_flavor` VALUES ('12', '4', '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]', '2021-05-27 09:43:08', '2021-05-27 09:43:08', '1', '1', '0');
INSERT INTO `dish_flavor` VALUES ('13', '4', '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-27 09:43:08', '2021-05-27 09:43:08', '1', '1', '0');
INSERT INTO `dish_flavor` VALUES ('14', '5', '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]', '2021-05-27 09:44:08', '2021-05-27 09:44:08', '1', '1', '0');
INSERT INTO `dish_flavor` VALUES ('15', '5', '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-27 09:44:08', '2021-05-27 09:44:08', '1', '1', '0');
INSERT INTO `dish_flavor` VALUES ('16', '6', '温度', '[\"热饮\",\"常温\",\"去冰\",\"少冰\",\"多冰\"]', '2021-05-27 09:45:12', '2021-05-27 09:45:12', '1', '1', '0');
INSERT INTO `dish_flavor` VALUES ('17', '6', '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]', '2021-05-27 09:45:12', '2021-05-27 09:45:12', '1', '1', '0');
INSERT INTO `dish_flavor` VALUES ('18', '6', '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-27 09:45:12', '2021-05-27 09:45:12', '1', '1', '0');
INSERT INTO `dish_flavor` VALUES ('19', '7', '温度', '[\"热饮\",\"常温\",\"去冰\",\"少冰\",\"多冰\"]', '2021-05-27 09:46:23', '2021-05-27 09:46:23', '1', '1', '0');
INSERT INTO `dish_flavor` VALUES ('20', '8', '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]', '2021-05-27 09:49:16', '2021-05-27 09:49:16', '1', '1', '0');
INSERT INTO `dish_flavor` VALUES ('21', '8', '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-27 09:49:16', '2021-05-27 09:49:16', '1', '1', '0');
INSERT INTO `dish_flavor` VALUES ('22', '9', '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-27 09:52:24', '2021-05-27 09:52:24', '1', '1', '0');
INSERT INTO `dish_flavor` VALUES ('23', '9', '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-27 09:53:22', '2021-05-27 09:53:22', '1', '1', '0');
INSERT INTO `dish_flavor` VALUES ('24', '10', '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]', '2021-05-27 09:54:30', '2021-05-27 09:54:30', '1', '1', '0');
INSERT INTO `dish_flavor` VALUES ('25', '11', '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-27 09:55:13', '2021-05-27 09:55:13', '1', '1', '0');
INSERT INTO `dish_flavor` VALUES ('26', '11', '温度', '[\"热饮\",\"常温\",\"去冰\",\"少冰\",\"多冰\"]', '2021-05-27 09:56:11', '2021-05-27 09:56:11', '1', '1', '0');
INSERT INTO `dish_flavor` VALUES ('27', '12', '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]', '2021-05-27 09:58:15', '2021-05-27 09:58:15', '1', '1', '0');
INSERT INTO `dish_flavor` VALUES ('28', '12', '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-27 09:58:15', '2021-05-27 09:58:15', '1', '1', '0');
INSERT INTO `dish_flavor` VALUES ('29', '13', '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-27 09:59:06', '2021-05-27 09:59:06', '1', '1', '0');
INSERT INTO `dish_flavor` VALUES ('30', '13', '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-27 10:02:35', '2021-05-27 10:02:35', '1', '1', '0');
INSERT INTO `dish_flavor` VALUES ('31', '14', '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]', '2021-05-27 10:03:14', '2021-05-27 10:03:14', '1', '1', '0');
INSERT INTO `dish_flavor` VALUES ('32', '13', '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-27 10:04:21', '2021-05-27 10:04:21', '1', '1', '0');
INSERT INTO `dish_flavor` VALUES ('33', '15', '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-27 10:15:45', '2021-05-27 10:15:45', '1', '1', '0');
INSERT INTO `dish_flavor` VALUES ('34', '15', '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-27 10:16:37', '2021-05-27 10:16:37', '1', '1', '0');
INSERT INTO `dish_flavor` VALUES ('35', '17', '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-27 10:17:27', '2021-05-27 10:17:27', '1', '1', '0');
INSERT INTO `dish_flavor` VALUES ('36', '18', '甜味', '[\"无糖\",\"少糖\",\"半躺\",\"多糖\",\"全糖\"]', '2021-05-27 10:18:32', '2021-05-27 10:18:32', '1', '1', '0');
INSERT INTO `dish_flavor` VALUES ('37', '14', '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-27 10:20:27', '2021-05-27 10:20:27', '1', '1', '0');
INSERT INTO `dish_flavor` VALUES ('38', '17', '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-27 10:23:19', '2021-05-27 10:23:19', '1', '1', '0');
INSERT INTO `dish_flavor` VALUES ('39', '12', '甜味', '[\"无糖\",\"少糖\",\"半躺\",\"多糖\",\"全糖\"]', '2021-05-27 10:24:00', '2021-05-27 10:24:00', '1', '1', '0');
INSERT INTO `dish_flavor` VALUES ('40', '11', '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-27 10:24:56', '2021-05-27 10:24:56', '1', '1', '0');
INSERT INTO `dish_flavor` VALUES ('41', '18', '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]', '2021-05-27 10:26:11', '2021-05-27 10:26:11', '1', '1', '0');
INSERT INTO `dish_flavor` VALUES ('42', '19', '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]', '2021-05-27 10:27:02', '2021-05-27 10:27:02', '1', '1', '0');
INSERT INTO `dish_flavor` VALUES ('43', '19', '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]', '2021-05-27 10:28:14', '2021-05-27 10:28:14', '1', '1', '0');
INSERT INTO `dish_flavor` VALUES ('44', '20', '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-27 10:29:20', '2021-05-27 10:29:20', '1', '1', '0');
INSERT INTO `dish_flavor` VALUES ('45', '13', '温度', '[\"热饮\",\"常温\",\"去冰\",\"少冰\",\"多冰\"]', '2021-05-28 01:31:38', '2021-05-28 01:31:38', '1', '1', '0');
INSERT INTO `dish_flavor` VALUES ('46', '13', '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-28 01:32:34', '2021-05-28 01:32:34', '1', '1', '0');
INSERT INTO `dish_flavor` VALUES ('47', '13', '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]', '2021-05-28 01:33:27', '2021-05-28 01:33:27', '1', '1', '0');
INSERT INTO `dish_flavor` VALUES ('48', '13', '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]', '2021-05-28 01:34:29', '2021-05-28 01:34:29', '1', '1', '0');
INSERT INTO `dish_flavor` VALUES ('49', '15', '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-28 01:35:14', '2021-05-28 01:35:14', '1', '1', '0');
INSERT INTO `dish_flavor` VALUES ('50', '16', '温度', '[\"热饮\",\"常温\",\"去冰\",\"少冰\",\"多冰\"]', '2021-05-28 01:36:09', '2021-05-28 01:36:09', '1', '1', '0');
INSERT INTO `dish_flavor` VALUES ('51', '17', '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]', '2021-05-28 01:36:43', '2021-05-28 01:36:43', '1', '1', '0');
INSERT INTO `dish_flavor` VALUES ('52', '20', '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-28 01:37:26', '2021-05-28 01:37:26', '1', '1', '0');
INSERT INTO `dish_flavor` VALUES ('53', '20', '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-28 01:38:35', '2021-05-28 01:38:35', '1', '1', '0');
INSERT INTO `dish_flavor` VALUES ('54', '20', '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]', '2021-05-28 01:39:35', '2021-05-28 01:39:35', '1', '1', '0');
INSERT INTO `dish_flavor` VALUES ('55', '21', '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-28 01:40:18', '2021-05-28 01:40:18', '1', '1', '0');
INSERT INTO `dish_flavor` VALUES ('56', '22', '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-28 01:40:56', '2021-05-28 01:40:56', '1', '1', '0');
INSERT INTO `dish_flavor` VALUES ('57', '23', '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-28 01:41:45', '2021-05-28 01:41:45', '1', '1', '0');
INSERT INTO `dish_flavor` VALUES ('58', '23', '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-28 01:42:30', '2021-05-28 01:42:30', '1', '1', '0');
INSERT INTO `dish_flavor` VALUES ('59', '21', '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-28 01:49:24', '2021-05-28 01:49:24', '1', '1', '0');
INSERT INTO `dish_flavor` VALUES ('60', '22', '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-28 01:50:53', '2021-05-28 01:50:53', '1', '1', '0');
INSERT INTO `dish_flavor` VALUES ('61', '22', '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-06-01 03:50:25', '2021-06-01 03:50:25', '1399309715396669441', '21', '0');
INSERT INTO `dish_flavor` VALUES ('62', '22', '温度', '[\"热饮\",\"常温\",\"去冰\",\"少冰\",\"多冰\"]', '2021-07-12 09:09:16', '2021-07-12 09:09:16', '1', '1', '0');
INSERT INTO `dish_flavor` VALUES ('63', '21', '温度', '[\"热饮\",\"常温\",\"去冰\",\"少冰\",\"多冰\"]', '2021-07-09 15:12:18', '2021-07-09 15:12:18', '1', '1', '0');
INSERT INTO `dish_flavor` VALUES ('68', '1', '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-27 09:37:27', '2021-05-27 09:37:27', '1', '1', '0');
INSERT INTO `dish_flavor` VALUES ('69', '1', '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]', '2021-05-27 09:38:43', '2021-05-27 09:38:43', '1', '1', '0');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '姓名',
  `username` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `password` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '密码',
  `phone` varchar(11) COLLATE utf8_bin NOT NULL COMMENT '手机号',
  `sex` varchar(2) COLLATE utf8_bin NOT NULL COMMENT '性别',
  `id_number` varchar(18) COLLATE utf8_bin NOT NULL COMMENT '身份证号',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态 0:禁用，1:正常',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user` bigint(20) NOT NULL COMMENT '创建人',
  `update_user` bigint(20) NOT NULL COMMENT '修改人',
  `del_flag` int(1) NOT NULL DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `idx_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='员工信息';

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('1', '管理员', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '13812312312', '1', '110101199001010047', '1', '2021-05-06 17:20:07', '2021-05-10 02:24:09', '1', '1', '0');
INSERT INTO `employee` VALUES ('2', 's1234', 'dade', 'e10adc3949ba59abbe56e057f20f883e', '13812312312', '0', '110101199001010047', '0', '2021-05-06 17:20:07', '2023-05-03 19:42:11', '1', '1', '0');
INSERT INTO `employee` VALUES ('3', '管理员', 'admin2', '508df4cb2f4d8f80519256258cfb975f', '13812312312', '1', '110101199001010047', '1', '2021-05-06 17:20:07', '2023-05-05 20:58:15', '1', '1', '0');
INSERT INTO `employee` VALUES ('4', '管理员', 'admin4', 'e10adc3949ba59abbe56e057f20f883e', '13812312312', '1', '110101199001010047', '1', '2021-05-06 17:20:07', '2023-05-03 19:37:16', '1', '1', '0');
INSERT INTO `employee` VALUES ('5', '管理员', 'admin5', 'e10adc3949ba59abbe56e057f20f883e', '13812312312', '1', '110101199001010047', '1', '2021-05-06 17:20:07', '2021-05-10 02:24:09', '1', '1', '0');
INSERT INTO `employee` VALUES ('6', '管理员', 'admina', 'e10adc3949ba59abbe56e057f20f883e', '13812312312', '1', '110101199001010047', '1', '2021-05-06 17:20:07', '2021-05-10 02:24:09', '1', '1', '0');
INSERT INTO `employee` VALUES ('7', '管理员', 'admins', 'e10adc3949ba59abbe56e057f20f883e', '13812312312', '1', '110101199001010047', '1', '2021-05-06 17:20:07', '2021-05-10 02:24:09', '1', '1', '0');
INSERT INTO `employee` VALUES ('8', '管理员', 'admind', 'e10adc3949ba59abbe56e057f20f883e', '13812312312', '1', '110101199001010047', '1', '2021-05-06 17:20:07', '2021-05-10 02:24:09', '1', '1', '0');
INSERT INTO `employee` VALUES ('9', '管理员', 'adminc', 'e10adc3949ba59abbe56e057f20f883e', '13812312312', '1', '110101199001010047', '1', '2021-05-06 17:20:07', '2021-05-10 02:24:09', '1', '1', '0');
INSERT INTO `employee` VALUES ('10', '管理员', 'adminf', 'e10adc3949ba59abbe56e057f20f883e', '13812312312', '1', '110101199001010047', '1', '2021-05-06 17:20:07', '2021-05-10 02:24:09', '1', '1', '0');
INSERT INTO `employee` VALUES ('11', '管理员', 'adming', 'e10adc3949ba59abbe56e057f20f883e', '13812312312', '1', '110101199001010047', '1', '2021-05-06 17:20:07', '2021-05-10 02:24:09', '1', '1', '0');
INSERT INTO `employee` VALUES ('12', '管理员', 'adminz', 'e10adc3949ba59abbe56e057f20f883e', '13812312312', '1', '110101199001010047', '1', '2021-05-06 17:20:07', '2021-05-10 02:24:09', '1', '1', '0');
INSERT INTO `employee` VALUES ('13', '管理员', 'adminx', 'e10adc3949ba59abbe56e057f20f883e', '13812312312', '1', '110101199001010047', '1', '2021-05-06 17:20:07', '2021-05-10 02:24:09', '1', '1', '0');
INSERT INTO `employee` VALUES ('14', '管理员', 'adminv', 'e10adc3949ba59abbe56e057f20f883e', '13812312312', '1', '110101199001010047', '1', '2021-05-06 17:20:07', '2021-05-10 02:24:09', '1', '1', '0');
INSERT INTO `employee` VALUES ('15', '管理员', 'adminq', 'e10adc3949ba59abbe56e057f20f883e', '13812312312', '1', '110101199001010047', '1', '2021-05-06 17:20:07', '2021-05-10 02:24:09', '1', '1', '0');
INSERT INTO `employee` VALUES ('16', '管理员', 'adminw', 'e10adc3949ba59abbe56e057f20f883e', '13812312312', '1', '110101199001010047', '1', '2021-05-06 17:20:07', '2021-05-10 02:24:09', '1', '1', '0');
INSERT INTO `employee` VALUES ('17', '管理员', 'admine', 'e10adc3949ba59abbe56e057f20f883e', '13812312312', '1', '110101199001010047', '1', '2021-05-06 17:20:07', '2021-05-10 02:24:09', '1', '1', '0');
INSERT INTO `employee` VALUES ('18', '管理员', 'adminr', 'e10adc3949ba59abbe56e057f20f883e', '13812312312', '1', '110101199001010047', '1', '2021-05-06 17:20:07', '2021-05-10 02:24:09', '1', '1', '0');
INSERT INTO `employee` VALUES ('19', '管理员', 'admint', 'e10adc3949ba59abbe56e057f20f883e', '13812312312', '1', '110101199001010047', '1', '2021-05-06 17:20:07', '2021-05-10 02:24:09', '1', '1', '0');
INSERT INTO `employee` VALUES ('20', '管理员', 'adminy', 'e10adc3949ba59abbe56e057f20f883e', '13812312312', '1', '110101199001010047', '1', '2021-05-06 17:20:07', '2021-05-10 02:24:09', '1', '1', '0');
INSERT INTO `employee` VALUES ('21', '管理员', 'adminu', 'e10adc3949ba59abbe56e057f20f883e', '13812312312', '1', '110101199001010047', '1', '2021-05-06 17:20:07', '2021-05-10 02:24:09', '1', '1', '0');
INSERT INTO `employee` VALUES ('22', '管理员', 'admini', 'e10adc3949ba59abbe56e057f20f883e', '13812312312', '1', '110101199001010047', '1', '2021-05-06 17:20:07', '2021-05-10 02:24:09', '1', '1', '0');
INSERT INTO `employee` VALUES ('23', '王辉', 'wanghui', 'e10adc3949ba59abbe56e057f20f883e', '17395043749', '0', '642221199709080430', '1', '2023-04-28 21:30:28', '2023-04-28 21:30:28', '1', '1', '0');
INSERT INTO `employee` VALUES ('24', '1234567', 'wanghui123', '91d8db7adce76454cad3396e68a1c47b', '13243224567', '1', '110101199001010047', '1', '2023-05-03 18:50:10', '2023-05-03 18:50:10', '1', '1', '0');
INSERT INTO `employee` VALUES ('25', 'ewew', 'ewe', 'bc62bbe97a22c7ffa517d28ac697f029', '13244343345', '1', '110101199001010047', '1', '2023-05-03 18:50:44', '2023-05-03 18:50:44', '1', '1', '0');
INSERT INTO `employee` VALUES ('26', 'wq', '123', 'e10adc3949ba59abbe56e057f20f883e', '13243565678', '1', '110101199001010047', '1', '2023-05-03 19:02:55', '2023-05-03 19:02:55', '1', '1', '0');
INSERT INTO `employee` VALUES ('27', 'er', '2232', '4768fc31e6c740901e817aab74ff86a5', '13812312312', '1', '110101199001010047', '1', '2023-05-03 19:44:01', '2023-05-03 19:44:01', '1', '1', '0');
INSERT INTO `employee` VALUES ('28', 'ewer', '2342423', '61b80f94cdd6d632f7bc38fd9ed91d9c', '13812312312', '1', '110101199001010047', '1', '2023-05-03 19:44:18', '2023-05-03 19:44:18', '1', '1', '0');
INSERT INTO `employee` VALUES ('29', 'wqw', 'qweqrqw', 'fcea920f7412b5da7be0cf42b8c93759', '13812312312', '0', '110101199001010047', '1', '2023-05-04 10:36:48', '2023-05-04 10:36:48', '1', '1', '0');
INSERT INTO `employee` VALUES ('30', 'sad', '1234', 'e10adc3949ba59abbe56e057f20f883e', '13812312312', '1', '110101199001010047', '1', '2023-05-04 11:42:42', '2023-05-04 11:42:42', '1', '1', '0');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `number` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '订单号',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '订单状态 1待付款，2待派送，3已派送，4已完成，5已取消',
  `user_id` bigint(20) NOT NULL COMMENT '下单用户',
  `address_book_id` bigint(20) NOT NULL COMMENT '地址id',
  `order_time` datetime NOT NULL COMMENT '下单时间',
  `checkout_time` datetime NOT NULL COMMENT '结账时间',
  `pay_method` int(11) NOT NULL DEFAULT '1' COMMENT '支付方式 1微信,2支付宝',
  `amount` decimal(10,2) NOT NULL COMMENT '实收金额',
  `remark` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `phone` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `address` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `user_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `consignee` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='订单表';

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('1', '1', '1', '1', '1', '2023-05-05 23:26:12', '2023-05-05 23:26:06', '1', '1.00', '1', '1', '1', '1', '1');
INSERT INTO `orders` VALUES ('2', '2', '4', '1', '5', '2023-05-09 22:53:42', '2023-05-09 22:53:42', '1', '40.00', '', '17395043749', '银川市', null, '王辉');
INSERT INTO `orders` VALUES ('3', '3', '4', '1', '5', '2023-05-09 22:54:36', '2023-05-09 22:54:36', '1', '116.00', '阿萨', '17395043749', '银川市', null, '王辉');
INSERT INTO `orders` VALUES ('8', '3', '1', '1', '5', '2023-05-10 09:40:50', '2023-05-10 09:40:50', '1', '116.00', '阿萨', '17395043749', '银川市', null, '王辉');

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '名字',
  `image` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '图片',
  `order_id` bigint(20) NOT NULL COMMENT '订单id',
  `dish_id` bigint(20) DEFAULT NULL COMMENT '菜品id',
  `setmeal_id` bigint(20) DEFAULT NULL COMMENT '套餐id',
  `dish_flavor` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '口味',
  `number` int(11) NOT NULL DEFAULT '1' COMMENT '数量',
  `amount` decimal(10,2) NOT NULL COMMENT '金额',
  `del_flag` int(1) NOT NULL DEFAULT '0' COMMENT '删除标志：0:正常,1:删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='订单明细表';

-- ----------------------------
-- Records of order_detail
-- ----------------------------
INSERT INTO `order_detail` VALUES ('1', '测试订单', '1', '1', '1', '1', '1', '1', '1.00', '0');
INSERT INTO `order_detail` VALUES ('2', '儿童套餐A计划', '236ef13d-b4d0-462d-add0-ea19a6520d47.png', '2', null, '1', null, '1', '40.00', '0');
INSERT INTO `order_detail` VALUES ('3', '毛氏红烧肉', '0a3b3288-3446-4420-bbff-f263d0c02d8e.jpg', '3', '2', null, '不要葱,微辣', '1', '68.00', '0');
INSERT INTO `order_detail` VALUES ('4', '组庵鱼翅', '740c79ce-af29-41b8-b78d-5f49c96e38c4.jpg', '3', '3', null, '微辣,微辣', '1', '48.00', '0');
INSERT INTO `order_detail` VALUES ('7', '毛氏红烧肉', '0a3b3288-3446-4420-bbff-f263d0c02d8e.jpg', '8', '2', null, '不要葱,微辣', '1', '68.00', '0');
INSERT INTO `order_detail` VALUES ('8', '组庵鱼翅', '740c79ce-af29-41b8-b78d-5f49c96e38c4.jpg', '8', '3', null, '微辣,微辣', '1', '48.00', '0');

-- ----------------------------
-- Table structure for setmeal
-- ----------------------------
DROP TABLE IF EXISTS `setmeal`;
CREATE TABLE `setmeal` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `category_id` bigint(20) NOT NULL COMMENT '菜品分类id',
  `name` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '套餐名称',
  `price` decimal(10,2) NOT NULL COMMENT '套餐价格',
  `status` int(11) DEFAULT NULL COMMENT '状态 0:停用 1:启用',
  `code` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '编码',
  `description` varchar(512) COLLATE utf8_bin DEFAULT NULL COMMENT '描述信息',
  `image` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '图片',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user` bigint(20) NOT NULL COMMENT '创建人',
  `update_user` bigint(20) NOT NULL COMMENT '修改人',
  `del_flag` int(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `idx_setmeal_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='套餐';

-- ----------------------------
-- Records of setmeal
-- ----------------------------
INSERT INTO `setmeal` VALUES ('1', '7', '儿童套餐A计划', '4000.00', '1', '', '啊实打实的', '236ef13d-b4d0-462d-add0-ea19a6520d47.png', '2021-07-15 15:52:55', '2023-05-05 22:58:05', '1', '1', '0');
INSERT INTO `setmeal` VALUES ('2', '5', '二点三', '4500.00', '1', '', '额为全额', '65256c59-1804-4069-bd19-2ba8e462ecdd.png', '2023-05-05 23:11:50', '2023-05-05 23:18:47', '1', '1', '0');
INSERT INTO `setmeal` VALUES ('3', '5', '第三方', '5400.00', '1', '', '让穷人发', '7194379c-bea0-4750-af6e-ca2c190ef31b.png', '2023-05-05 23:12:37', '2023-05-05 23:18:47', '1', '1', '0');

-- ----------------------------
-- Table structure for setmeal_dish
-- ----------------------------
DROP TABLE IF EXISTS `setmeal_dish`;
CREATE TABLE `setmeal_dish` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `setmeal_id` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '套餐id ',
  `dish_id` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '菜品id',
  `name` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '菜品名称 （冗余字段）',
  `price` decimal(10,2) DEFAULT NULL COMMENT '菜品原价（冗余字段）',
  `copies` int(11) NOT NULL COMMENT '份数',
  `sort` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user` bigint(20) NOT NULL COMMENT '创建人',
  `update_user` bigint(20) NOT NULL COMMENT '修改人',
  `del_flag` int(11) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='套餐菜品关系';

-- ----------------------------
-- Records of setmeal_dish
-- ----------------------------
INSERT INTO `setmeal_dish` VALUES ('22', '1', '19', '老火靓汤', '49800.00', '1', '0', '2023-05-05 22:58:05', '2023-05-05 22:58:05', '1', '1', '0');
INSERT INTO `setmeal_dish` VALUES ('23', '1', '21', '北冰洋', '500.00', '1', '0', '2023-05-05 22:58:05', '2023-05-05 22:58:05', '1', '1', '0');
INSERT INTO `setmeal_dish` VALUES ('24', '1', '23', '米饭', '200.00', '1', '0', '2023-05-05 22:58:05', '2023-05-05 22:58:05', '1', '1', '0');
INSERT INTO `setmeal_dish` VALUES ('25', '2', '1', '辣子鸡', '7800.00', '1', '0', '2023-05-05 23:11:50', '2023-05-05 23:11:50', '1', '1', '0');
INSERT INTO `setmeal_dish` VALUES ('26', '2', '9', '麻辣兔头', '19800.00', '1', '0', '2023-05-05 23:11:50', '2023-05-05 23:11:50', '1', '1', '0');
INSERT INTO `setmeal_dish` VALUES ('27', '2', '23', '米饭', '200.00', '1', '0', '2023-05-05 23:11:50', '2023-05-05 23:11:50', '1', '1', '0');
INSERT INTO `setmeal_dish` VALUES ('28', '3', '1', '辣子鸡', '7800.00', '1', '0', '2023-05-05 23:12:37', '2023-05-05 23:12:37', '1', '1', '0');
INSERT INTO `setmeal_dish` VALUES ('29', '3', '2', '毛氏红烧肉', '6800.00', '1', '0', '2023-05-05 23:12:37', '2023-05-05 23:12:37', '1', '1', '0');
INSERT INTO `setmeal_dish` VALUES ('30', '3', '3', '组庵鱼翅', '4800.00', '1', '0', '2023-05-05 23:12:37', '2023-05-05 23:12:37', '1', '1', '0');
INSERT INTO `setmeal_dish` VALUES ('31', '3', '23', '米饭', '200.00', '1', '0', '2023-05-05 23:12:37', '2023-05-05 23:12:37', '1', '1', '0');

-- ----------------------------
-- Table structure for shopping_cart
-- ----------------------------
DROP TABLE IF EXISTS `shopping_cart`;
CREATE TABLE `shopping_cart` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '名称',
  `image` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '图片',
  `user_id` bigint(20) NOT NULL COMMENT '主键',
  `dish_id` bigint(20) DEFAULT NULL COMMENT '菜品id',
  `setmeal_id` bigint(20) DEFAULT NULL COMMENT '套餐id',
  `dish_flavor` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '口味',
  `number` int(11) NOT NULL DEFAULT '1' COMMENT '数量',
  `amount` decimal(10,2) NOT NULL COMMENT '金额',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='购物车';

-- ----------------------------
-- Records of shopping_cart
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '姓名',
  `phone` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '手机号',
  `sex` varchar(2) COLLATE utf8_bin DEFAULT NULL COMMENT '性别',
  `id_number` varchar(18) COLLATE utf8_bin DEFAULT NULL COMMENT '身份证号',
  `avatar` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '头像',
  `status` int(11) DEFAULT '0' COMMENT '状态 0:禁用，1:正常',
  `del_flag` int(1) DEFAULT '0' COMMENT '删除标志：0:正常,1:删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户信息';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', null, '17395043749', null, null, null, '1', '0');
