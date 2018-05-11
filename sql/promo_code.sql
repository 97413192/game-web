/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : card_gmtools

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2018-05-03 18:40:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for promo_code
-- ----------------------------
DROP TABLE IF EXISTS `promo_code`;
CREATE TABLE `promo_code` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `account` varchar(32) NOT NULL DEFAULT '无' COMMENT '代理商账号',
  `roomCard` int(11) NOT NULL DEFAULT '0' COMMENT '代理商房卡',
  `gameId` int(11) NOT NULL COMMENT '玩家id',
  `userID` int(11) NOT NULL COMMENT '代理商id',
  `bindingTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '绑定时间',
  `modifyTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `pNickName` varchar(30) DEFAULT NULL COMMENT '玩家昵称',
  `playerRoomCard` int(11) NOT NULL COMMENT '玩家房卡',
  `yb` int(11) NOT NULL DEFAULT '0' COMMENT '标志是否被绑定 1未被绑定 0被绑定',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=utf8;
