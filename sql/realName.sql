/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : card_gmtools

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2018-04-26 11:10:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for realname
-- ----------------------------
DROP TABLE IF EXISTS `realname`;
CREATE TABLE `realname` (
  `id` int(11) NOT NULL,
  `pnickName` varchar(255) DEFAULT NULL COMMENT '游戏昵称',
  `gameId` int(11) DEFAULT NULL COMMENT '游戏id',
  `pName` varchar(255) DEFAULT NULL COMMENT '玩家姓名',
  `realId` int(11) DEFAULT NULL COMMENT '身份证',
  `phoneNumber` varchar(11) DEFAULT NULL COMMENT '电话号码',
  `applyTime` datetime DEFAULT NULL COMMENT '申请时间',
  `applyState` int(11) DEFAULT NULL COMMENT '申请状态  1. 正在申请 2. 同意 3. 拒绝',
  `approveTime` datetime DEFAULT NULL COMMENT '批准时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of realname
-- ----------------------------
