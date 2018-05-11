/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : card_gmtools

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2018-05-02 17:52:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for share
-- ----------------------------
DROP TABLE IF EXISTS `share`;
CREATE TABLE `share` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '数据库唯一标识',
  `gameId` int(11) DEFAULT NULL,
  `shareType` int(10) DEFAULT NULL COMMENT '分享类型(1是微信,2是朋友圈)',
  `number` int(10) DEFAULT NULL COMMENT '领取物品数量',
  `numberType` int(10) DEFAULT NULL COMMENT '奖励类型(1是金币,2是房卡,3是元宝)',
  `rewardNumber` int(10) DEFAULT NULL COMMENT '每天可以领取多少次',
  `receiveCD` int(11) DEFAULT NULL COMMENT '间隔多少时间可以领取一次',
  `shareNumber` int(11) DEFAULT NULL COMMENT '分享次数',
  `shareTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of share
-- ----------------------------
INSERT INTO `share` VALUES ('1', '100005', '1', '3', '2', '1', '10', '1', '2018-05-02 13:48:26');
