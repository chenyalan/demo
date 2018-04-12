/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50534
Source Host           : localhost:3306
Source Database       : ssm

Target Server Type    : MYSQL
Target Server Version : 50534
File Encoding         : 65001

Date: 2017-11-30 17:25:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deleted` int(11) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `nick_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '0', '0', '2017-11-28 14:14:45', '2017-11-28 14:14:47', '陈陈', '陈亚兰', '123456', '1');
INSERT INTO `user` VALUES ('6', null, null, null, null, '胖虎2', '帅胖虎', '123456', '0');
INSERT INTO `user` VALUES ('7', '0', '0', '2017-11-28 16:25:13', null, '胖虎3', '帅胖虎', '123456', '1');
INSERT INTO `user` VALUES ('8', '0', null, null, '2017-11-30 10:40:24', 'chenyalasimida', null, null, '0');
INSERT INTO `user` VALUES ('9', '0', '0', '2017-11-29 19:39:06', null, 'Honey1', '宝贝1号', 'chenyla', null);
INSERT INTO `user` VALUES ('10', '0', '0', '2017-11-30 09:45:28', null, 'Honeyw', '宝贝1号', 'chenyla', null);
INSERT INTO `user` VALUES ('11', '0', '0', '2017-11-30 10:18:39', null, 'Honey22w', '宝贝1号', 'chenyla', '1');
INSERT INTO `user` VALUES ('12', '0', '0', '2017-11-30 10:19:38', null, 'cyyyy', '宝贝1号', 'chenyla', '0');
INSERT INTO `user` VALUES ('13', '0', '0', '2017-11-30 13:55:36', null, 'and', '宝贝2号', 'bigbear', '1');
INSERT INTO `user` VALUES ('14', '0', '0', '2017-11-30 13:58:03', null, '333', '宝贝3号', 'bigbear', '1');
INSERT INTO `user` VALUES ('15', '0', '0', '2017-11-30 14:26:45', null, 'ck', '宝贝4号', 'bigbear', '1');
INSERT INTO `user` VALUES ('16', '0', '0', '2017-11-30 14:26:55', null, 'ck2', '宝贝5号', 'bigbear', '1');
INSERT INTO `user` VALUES ('17', '0', '0', '2017-11-30 14:32:38', null, 'ck3', '宝贝6号', 'bigbear', '1');
INSERT INTO `user` VALUES ('18', '0', '0', '2017-11-30 14:32:51', null, 'ck33', '宝贝7号', '???', '1');
INSERT INTO `user` VALUES ('19', '0', '0', '2017-11-30 14:33:48', null, 'ck3333', '宝贝8号', '???', '0');
INSERT INTO `user` VALUES ('20', '0', '0', '2017-11-30 14:34:00', null, '九分裤', '宝贝9号', 'kjkfjd', '1');
INSERT INTO `user` VALUES ('21', '0', '0', '2017-11-30 14:34:25', null, '肌肤', '宝贝10号', 'health', '0');
INSERT INTO `user` VALUES ('22', '0', '0', '2017-11-30 14:40:55', null, '九分裤2', '健康', 'dkdkdk', '0');
INSERT INTO `user` VALUES ('23', '0', '0', '2017-11-30 14:48:20', null, '九分裤3', '健康', 'chenji', '1');
INSERT INTO `user` VALUES ('24', '0', '0', '2017-11-30 14:49:36', null, '九分裤4', '健康', '456789', '1');
INSERT INTO `user` VALUES ('25', '0', '0', '2017-11-30 14:52:42', null, '九分裤5', '健康', '345678', '0');
INSERT INTO `user` VALUES ('26', '0', '0', '2017-11-30 14:56:13', null, '九分裤6', '健康', '234567', '1');
INSERT INTO `user` VALUES ('27', '0', '0', '2017-11-30 14:56:20', null, '九分裤7', '健康', '123456', '1');
INSERT INTO `user` VALUES ('28', '0', '0', '2017-11-30 14:58:38', null, '成功', '郑成功', '123456', '1');
INSERT INTO `user` VALUES ('29', '0', '0', '2017-11-30 15:56:39', null, '成功4', '郑成功', '123456', '1');
INSERT INTO `user` VALUES ('30', '0', '0', '2017-11-30 16:49:39', null, 'fdjk', null, null, '1');
INSERT INTO `user` VALUES ('31', '0', '0', '2017-11-30 16:50:17', null, 'fdjk3', null, null, '1');
INSERT INTO `user` VALUES ('32', '0', '0', '2017-11-30 17:04:03', null, 'dd1', '郑成功', '123456', '1');
INSERT INTO `user` VALUES ('33', '0', '0', '2017-11-30 17:05:43', null, 'dd2', '郑成功', '123456', '1');
INSERT INTO `user` VALUES ('34', '0', '0', '2017-11-30 17:09:48', null, 'dd3', '郑成功', '123456', '1');
INSERT INTO `user` VALUES ('35', '0', '0', '2017-11-30 17:14:38', null, 'dd4', '郑成功', '123456', null);
INSERT INTO `user` VALUES ('36', '0', '0', '2017-11-30 17:15:16', null, 'dd', '郑成功', '123456', null);
INSERT INTO `user` VALUES ('37', '0', '0', '2017-11-30 17:16:17', null, 'coco', '郑成功', '123456', null);
INSERT INTO `user` VALUES ('38', '0', '0', '2017-11-30 17:22:32', null, 'coco8', '郑成功', '123456', null);
