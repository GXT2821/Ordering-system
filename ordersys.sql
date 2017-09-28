/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50508
Source Host           : localhost:3306
Source Database       : ordersys

Target Server Type    : MYSQL
Target Server Version : 50508
File Encoding         : 65001

Date: 2017-07-06 15:05:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `dishesinfo`
-- ----------------------------
DROP TABLE IF EXISTS `dishesinfo`;
CREATE TABLE `dishesinfo` (
  `dishesId` int(11) NOT NULL AUTO_INCREMENT,
  `dishesName` varchar(50) DEFAULT NULL,
  `dishesDiscript` varchar(100) DEFAULT NULL,
  `dishesImg` varchar(100) DEFAULT NULL,
  `dishesTxt` varchar(400) DEFAULT NULL,
  `recommend` int(11) DEFAULT '0',
  `dishesPrice` float DEFAULT '1',
  `state` int(11) DEFAULT '1',
  PRIMARY KEY (`dishesId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dishesinfo
-- ----------------------------
INSERT INTO `dishesinfo` VALUES ('1', '川香回锅肉', '菜色鲜香，肥而不腻', '/order/photo/20170705084837_465.jpg', '采用上等五花肉制作，配以酸姜、\"酸辣椒\"及\'秘制\'豆豉酱，味道鲜美，营养丰富', '1', '26.5', '1');
INSERT INTO `dishesinfo` VALUES ('3', '测试菜品', '测试', '/order/photo/20170705084807_444.jpg', '11111111', '1', '23.5', '1');
INSERT INTO `dishesinfo` VALUES ('4', '测试菜品', '测试', '/order/photo/20170705084734_623.jpg', '测试\n测试\n测试', '0', '10.5', '1');
INSERT INTO `dishesinfo` VALUES ('5', '测试菜品2', '测试菜品，试验一下系统功能是否能够正确处理111', '/order/photo/20170705084642_330.jpg', '测试菜品，试验一下系统功能是否能够正确处理\n测试菜品，试验一下系统功能是否能够正确处理\n测试菜品，试验一下系统功能是否能够正确处理\n重要的事情说三遍~！ok', '0', '16.5', '1');
INSERT INTO `dishesinfo` VALUES ('6', '测试菜品333', '测试菜品，试验一下系统功能是否能够正确处理2222', '/order/photo/20170705084721_902.jpg', '测试菜品，试验一下系统功能是否能够正确处理\n测试菜品，试验一下系统功能是否能够正确处理\n测试菜品，试验一下系统功能是否能够正确处理\n重要的事情说三遍~！！！！1231231', '1', '36', '1');
INSERT INTO `dishesinfo` VALUES ('7', '测试2', '测试。。测试。。测试。。', '/order/photo/20170704093052_379.jpg', '测试中。。\n测试中。。\n测试中。。', '1', '0', '0');
INSERT INTO `dishesinfo` VALUES ('8', 'ww', 'ddsd', '/order/photo/20170705103348_765.jpg', 'dssd', '0', '12', '0');
INSERT INTO `dishesinfo` VALUES ('9', '鱼香肉丝', '测试', 'img/dishes/1.jpg', '测试中', '1', '12.5', '1');
INSERT INTO `dishesinfo` VALUES ('10', '测试1', '测试', 'img/dishes/1.jpg', '测试', '1', '12', '1');

-- ----------------------------
-- Table structure for `orderdishes`
-- ----------------------------
DROP TABLE IF EXISTS `orderdishes`;
CREATE TABLE `orderdishes` (
  `odId` int(11) NOT NULL AUTO_INCREMENT,
  `orderReference` int(11) DEFAULT NULL,
  `dishes` int(11) DEFAULT NULL,
  `num` int(11) DEFAULT '1',
  `dstate` int(11) DEFAULT '0',
  PRIMARY KEY (`odId`),
  KEY `orderReference` (`orderReference`),
  KEY `dishes` (`dishes`),
  CONSTRAINT `orderdishes_ibfk_1` FOREIGN KEY (`orderReference`) REFERENCES `orderinfo` (`orderId`),
  CONSTRAINT `orderdishes_ibfk_2` FOREIGN KEY (`dishes`) REFERENCES `dishesinfo` (`dishesId`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderdishes
-- ----------------------------
INSERT INTO `orderdishes` VALUES ('1', '5', '1', '4', '0');
INSERT INTO `orderdishes` VALUES ('2', '4', '5', '1', '0');
INSERT INTO `orderdishes` VALUES ('3', '4', '4', '2', '0');
INSERT INTO `orderdishes` VALUES ('4', '5', '1', '3', '0');
INSERT INTO `orderdishes` VALUES ('5', '5', '5', '1', '0');
INSERT INTO `orderdishes` VALUES ('6', '6', '4', '1', '0');
INSERT INTO `orderdishes` VALUES ('7', '6', '1', '2', '0');
INSERT INTO `orderdishes` VALUES ('8', '6', '6', '3', '0');
INSERT INTO `orderdishes` VALUES ('9', '6', '4', '1', '0');
INSERT INTO `orderdishes` VALUES ('10', '7', '1', '3', '0');
INSERT INTO `orderdishes` VALUES ('11', '7', '5', '1', '0');
INSERT INTO `orderdishes` VALUES ('12', '7', '6', '1', '0');
INSERT INTO `orderdishes` VALUES ('13', '8', '1', '1', '0');
INSERT INTO `orderdishes` VALUES ('14', '8', '5', '1', '0');
INSERT INTO `orderdishes` VALUES ('15', '3', '1', '3', '0');
INSERT INTO `orderdishes` VALUES ('16', '10', '5', '1', '0');
INSERT INTO `orderdishes` VALUES ('17', '10', '6', '1', '0');
INSERT INTO `orderdishes` VALUES ('18', '1', '1', '4', '0');
INSERT INTO `orderdishes` VALUES ('19', '11', '4', '4', '0');
INSERT INTO `orderdishes` VALUES ('20', '11', '6', '2', '0');
INSERT INTO `orderdishes` VALUES ('21', '12', '1', '1', '0');
INSERT INTO `orderdishes` VALUES ('22', '12', '5', '1', '0');
INSERT INTO `orderdishes` VALUES ('23', '13', '1', '3', '0');
INSERT INTO `orderdishes` VALUES ('24', '13', '5', '5', '0');
INSERT INTO `orderdishes` VALUES ('25', '13', '6', '1', '0');
INSERT INTO `orderdishes` VALUES ('26', '14', '1', '3', '0');
INSERT INTO `orderdishes` VALUES ('27', '14', '5', '1', '0');
INSERT INTO `orderdishes` VALUES ('28', '15', '3', '3', '0');
INSERT INTO `orderdishes` VALUES ('29', '15', '1', '1', '0');
INSERT INTO `orderdishes` VALUES ('30', '16', '1', '4', '0');
INSERT INTO `orderdishes` VALUES ('31', '16', '5', '2', '0');
INSERT INTO `orderdishes` VALUES ('32', '17', '1', '4', '0');
INSERT INTO `orderdishes` VALUES ('33', '17', '5', '2', '0');
INSERT INTO `orderdishes` VALUES ('34', '18', '5', '3', '0');
INSERT INTO `orderdishes` VALUES ('35', '18', '1', '3', '0');
INSERT INTO `orderdishes` VALUES ('36', '19', '1', '3', '0');
INSERT INTO `orderdishes` VALUES ('56', '25', '1', '1', '0');
INSERT INTO `orderdishes` VALUES ('57', '25', '3', '1', '0');
INSERT INTO `orderdishes` VALUES ('58', '25', '1', '1', '0');
INSERT INTO `orderdishes` VALUES ('59', '25', '4', '1', '0');
INSERT INTO `orderdishes` VALUES ('60', '25', '1', '1', '0');
INSERT INTO `orderdishes` VALUES ('61', '25', '3', '1', '0');
INSERT INTO `orderdishes` VALUES ('62', '25', '1', '1', '0');
INSERT INTO `orderdishes` VALUES ('63', '25', '4', '2', '0');
INSERT INTO `orderdishes` VALUES ('64', '25', '1', '1', '0');
INSERT INTO `orderdishes` VALUES ('65', '25', '3', '1', '0');
INSERT INTO `orderdishes` VALUES ('66', '25', '1', '1', '0');
INSERT INTO `orderdishes` VALUES ('67', '25', '4', '1', '0');
INSERT INTO `orderdishes` VALUES ('68', '25', '1', '2', '0');
INSERT INTO `orderdishes` VALUES ('69', '25', '1', '1', '0');
INSERT INTO `orderdishes` VALUES ('70', '25', '1', '1', '0');
INSERT INTO `orderdishes` VALUES ('71', '25', '3', '1', '0');
INSERT INTO `orderdishes` VALUES ('72', '25', '1', '1', '0');
INSERT INTO `orderdishes` VALUES ('73', '25', '3', '1', '0');
INSERT INTO `orderdishes` VALUES ('74', '25', '1', '1', '0');
INSERT INTO `orderdishes` VALUES ('75', '25', '3', '1', '0');
INSERT INTO `orderdishes` VALUES ('76', '26', '1', '1', '0');
INSERT INTO `orderdishes` VALUES ('77', '26', '3', '2', '2');

-- ----------------------------
-- Table structure for `orderinfo`
-- ----------------------------
DROP TABLE IF EXISTS `orderinfo`;
CREATE TABLE `orderinfo` (
  `orderId` int(11) NOT NULL AUTO_INCREMENT,
  `orderBeginDate` datetime DEFAULT NULL,
  `orderEndDate` datetime DEFAULT NULL,
  `waiterId` int(11) DEFAULT NULL,
  `orderState` int(11) DEFAULT '0',
  `tableId` int(11) DEFAULT '1',
  `delorder` int(11) DEFAULT '0',
  PRIMARY KEY (`orderId`),
  KEY `waiterfk` (`waiterId`),
  CONSTRAINT `waiterfk` FOREIGN KEY (`waiterId`) REFERENCES `userinfo` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderinfo
-- ----------------------------
INSERT INTO `orderinfo` VALUES ('1', '2015-09-09 16:49:29', '2015-09-10 11:49:57', '1', '2', '1', '0');
INSERT INTO `orderinfo` VALUES ('2', '2015-09-09 16:51:35', '2015-10-12 11:42:01', '1', '1', '1', '0');
INSERT INTO `orderinfo` VALUES ('3', '2015-09-09 16:53:59', '2015-10-13 10:47:09', '1', '2', '1', '0');
INSERT INTO `orderinfo` VALUES ('4', '2015-09-09 16:56:16', '2015-09-10 14:13:16', '1', '2', '1', '0');
INSERT INTO `orderinfo` VALUES ('5', '2015-10-12 11:15:41', '2015-10-12 11:42:36', '17', '2', '1', '0');
INSERT INTO `orderinfo` VALUES ('6', '2015-10-12 11:46:57', '2015-10-12 11:48:25', '17', '1', '12', '1');
INSERT INTO `orderinfo` VALUES ('7', '2015-10-12 11:51:15', '2015-10-12 11:52:05', '27', '2', '12', '0');
INSERT INTO `orderinfo` VALUES ('8', '2015-10-12 11:52:31', '2015-10-12 11:52:35', '17', '1', '12', '0');
INSERT INTO `orderinfo` VALUES ('9', '2015-10-13 10:46:53', '2015-10-13 10:47:02', '16', '2', '1', '0');
INSERT INTO `orderinfo` VALUES ('10', '2015-10-13 10:47:31', '2015-10-13 10:47:35', '15', '2', '1', '1');
INSERT INTO `orderinfo` VALUES ('11', '2015-10-13 10:50:39', '2015-10-13 10:51:00', '17', '1', '12', '0');
INSERT INTO `orderinfo` VALUES ('12', '2015-10-13 10:52:57', '2015-10-13 10:53:54', '15', '2', '12', '0');
INSERT INTO `orderinfo` VALUES ('13', '2015-10-13 13:31:11', '2015-10-29 14:19:23', '17', '2', '1', '0');
INSERT INTO `orderinfo` VALUES ('14', '2015-10-13 13:34:47', '2015-10-26 16:01:19', '29', '1', '1', '0');
INSERT INTO `orderinfo` VALUES ('15', '2015-10-13 14:50:51', '2015-10-13 14:51:32', '26', '2', '3', '0');
INSERT INTO `orderinfo` VALUES ('16', '2015-10-26 15:32:39', '2015-10-30 13:06:48', '16', '1', '1', '0');
INSERT INTO `orderinfo` VALUES ('17', '2015-10-26 15:33:43', '2017-07-06 14:53:27', '25', '1', '1', '0');
INSERT INTO `orderinfo` VALUES ('18', '2015-10-29 15:49:04', '2015-10-29 15:49:39', '17', '1', '1', '0');
INSERT INTO `orderinfo` VALUES ('19', '2015-11-04 14:05:57', '2015-11-04 14:08:28', '17', '1', '1', '0');
INSERT INTO `orderinfo` VALUES ('25', '2017-07-06 14:42:59', '2017-07-06 14:52:20', '25', '1', '1', '0');
INSERT INTO `orderinfo` VALUES ('26', '2017-07-06 14:56:21', null, '25', '0', '2', '0');

-- ----------------------------
-- Table structure for `roleinfo`
-- ----------------------------
DROP TABLE IF EXISTS `roleinfo`;
CREATE TABLE `roleinfo` (
  `roleId` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roleinfo
-- ----------------------------
INSERT INTO `roleinfo` VALUES ('1', '餐厅管理员');
INSERT INTO `roleinfo` VALUES ('2', '后厨人员');
INSERT INTO `roleinfo` VALUES ('3', '餐厅服务员');

-- ----------------------------
-- Table structure for `shopinfo`
-- ----------------------------
DROP TABLE IF EXISTS `shopinfo`;
CREATE TABLE `shopinfo` (
  `shopId` int(20) NOT NULL AUTO_INCREMENT,
  `dishesId` int(20) DEFAULT NULL,
  `num` int(20) DEFAULT NULL,
  PRIMARY KEY (`shopId`),
  KEY `2` (`dishesId`),
  CONSTRAINT `2` FOREIGN KEY (`dishesId`) REFERENCES `dishesinfo` (`dishesId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shopinfo
-- ----------------------------

-- ----------------------------
-- Table structure for `userinfo`
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userAccount` varchar(30) DEFAULT NULL,
  `userPass` varchar(50) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  `locked` int(11) DEFAULT '0',
  `faceimg` varchar(150) DEFAULT 'default.jpg',
  `userState` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`userId`),
  KEY `fkrole` (`role`),
  CONSTRAINT `fkrole` FOREIGN KEY (`role`) REFERENCES `roleinfo` (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES ('1', 'admin', '25d55ad283aa400af464c76d713c07ad', '1', '0', '/order/photo/20170705084102_622.jpg', '1');
INSERT INTO `userinfo` VALUES ('15', 'User3', '25d55ad283aa400af464c76d713c07ad', '2', '0', 'img/faces/default.jpg', '0');
INSERT INTO `userinfo` VALUES ('16', 'User4', '25d55ad283aa400af464c76d713c07ad', '3', '0', 'img/faces/8.jpg', '0');
INSERT INTO `userinfo` VALUES ('17', 'zyyyl', 'e10adc3949ba59abbe56e057f20f883e', '3', '0', '/order/photo/20170705093218_86.jpg', '0');
INSERT INTO `userinfo` VALUES ('21', 'we', '25d55ad283aa400af464c76d713c07ad', '2', '0', '/order/photo/20170705085149_237.jpg', '0');
INSERT INTO `userinfo` VALUES ('24', 'ww', '25d55ad283aa400af464c76d713c07ad', '1', '0', '/order/photo/20170705085129_894.jpg', '1');
INSERT INTO `userinfo` VALUES ('25', 'waiter1', 'e10adc3949ba59abbe56e057f20f883e', '3', '0', '/order/photo/20170705092449_817.jpg', '1');
INSERT INTO `userinfo` VALUES ('26', 'waiter2', 'e10adc3949ba59abbe56e057f20f883e', '3', '0', '/order/photo/20170705092514_845.jpg', '1');
INSERT INTO `userinfo` VALUES ('27', 'waiter3', 'e10adc3949ba59abbe56e057f20f883e', '3', '0', '/order/photo/20170705093008_304.jpg', '1');
INSERT INTO `userinfo` VALUES ('28', 'waiter4', 'e10adc3949ba59abbe56e057f20f883e', '3', '0', '/order/photo/20170705092559_721.jpg', '1');
INSERT INTO `userinfo` VALUES ('29', 'waiter5', 'e10adc3949ba59abbe56e057f20f883e', '3', '0', '/order/photo/20170705092622_85.jpg', '1');
INSERT INTO `userinfo` VALUES ('30', 'k1', 'e10adc3949ba59abbe56e057f20f883e', '2', '0', '/order/photo/20170705092835_567.jpg', '1');
INSERT INTO `userinfo` VALUES ('31', 'k2', 'e10adc3949ba59abbe56e057f20f883e', '2', '0', '/order/photo/20170705092854_892.jpg', '1');
INSERT INTO `userinfo` VALUES ('32', 'k3', 'e10adc3949ba59abbe56e057f20f883e', '2', '0', '/order/photo/20170705092912_346.jpg', '1');
INSERT INTO `userinfo` VALUES ('33', 'k4', 'e10adc3949ba59abbe56e057f20f883e', '2', '0', '/order/photo/20170705092931_213.jpg', '1');
INSERT INTO `userinfo` VALUES ('34', 'k5', 'e10adc3949ba59abbe56e057f20f883e', '2', '0', '/order/photo/20170705092951_920.jpg', '1');
INSERT INTO `userinfo` VALUES ('35', 'w1', 'e10adc3949ba59abbe56e057f20f883e', '3', '0', '/order/photo/20170706104655_608.jpg', '1');
