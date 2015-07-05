/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50624
 Source Host           : localhost
 Source Database       : alizee

 Target Server Type    : MySQL
 Target Server Version : 50624
 File Encoding         : utf-8

 Date: 06/29/2015 21:39:17 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `t_about`
-- ----------------------------
DROP TABLE IF EXISTS `t_about`;
CREATE TABLE `t_about` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `companyName` varchar(255) DEFAULT NULL,
  `companyInfo` varchar(4000) DEFAULT NULL,
  `companyProduct` varchar(4000) DEFAULT NULL,
  `companyPhone` varchar(255) DEFAULT NULL,
  `companyMobile` varchar(255) DEFAULT NULL,
  `companyEmail` varchar(255) DEFAULT NULL,
  `companyQQ` varchar(255) DEFAULT NULL,
  `companyAddress` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_activity`
-- ----------------------------
DROP TABLE IF EXISTS `t_activity`;
CREATE TABLE `t_activity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `description` mediumtext,
  `createDate` datetime DEFAULT NULL,
  `endDate` datetime DEFAULT NULL,
  `merchant` varchar(255) DEFAULT NULL,
  `merchantAddress` varchar(1000) DEFAULT NULL,
  `merchantPhone` varchar(32) DEFAULT NULL,
  `picPath` varchar(255) DEFAULT NULL,
  `picName` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `published` char(1) DEFAULT NULL,
  `startDate` datetime DEFAULT NULL,
  `lastUpdateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_activityuser`
-- ----------------------------
DROP TABLE IF EXISTS `t_activityuser`;
CREATE TABLE `t_activityuser` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) DEFAULT NULL,
  `activityId` bigint(20) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`) USING BTREE,
  KEY `activityId` (`activityId`) USING BTREE,
  CONSTRAINT `t_activityuser_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `t_user` (`id`),
  CONSTRAINT `t_activityuser_ibfk_2` FOREIGN KEY (`activityId`) REFERENCES `t_activity` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_advert`
-- ----------------------------
DROP TABLE IF EXISTS `t_advert`;
CREATE TABLE `t_advert` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `description` mediumtext,
  `picPath` varchar(255) DEFAULT NULL,
  `picName` varchar(255) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `enabled` char(1) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_applicationconfig`
-- ----------------------------
DROP TABLE IF EXISTS `t_applicationconfig`;
CREATE TABLE `t_applicationconfig` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `systemKey` varchar(25) DEFAULT NULL,
  `systemValue` tinytext,
  PRIMARY KEY (`id`),
  KEY `systemKey` (`systemKey`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_commend`
-- ----------------------------
DROP TABLE IF EXISTS `t_commend`;
CREATE TABLE `t_commend` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) DEFAULT NULL,
  `typeId` int(10) DEFAULT NULL,
  `itemId` bigint(20) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`) USING BTREE,
  KEY `typeId` (`typeId`) USING BTREE,
  KEY `itemId` (`itemId`) USING BTREE,
  CONSTRAINT `t_commend_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_comment`
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) DEFAULT NULL,
  `typeId` int(10) DEFAULT NULL,
  `itemId` bigint(20) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `rank` int(10) DEFAULT NULL,
  `content` tinytext,
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`) USING BTREE,
  KEY `typeId` (`typeId`) USING BTREE,
  KEY `itemId` (`itemId`) USING BTREE,
  CONSTRAINT `t_comment_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_feature`
-- ----------------------------
DROP TABLE IF EXISTS `t_feature`;
CREATE TABLE `t_feature` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `displayName` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `enabled` char(1) DEFAULT NULL,
  `parentId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_food`
-- ----------------------------
DROP TABLE IF EXISTS `t_food`;
CREATE TABLE `t_food` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `description` mediumtext,
  `createDate` datetime DEFAULT NULL,
  `picPath` varchar(255) DEFAULT NULL,
  `picName` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_game`
-- ----------------------------
DROP TABLE IF EXISTS `t_game`;
CREATE TABLE `t_game` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` mediumtext,
  `createDate` datetime DEFAULT NULL,
  `popularity` int(10) DEFAULT NULL,
  `version` varchar(255) DEFAULT NULL,
  `platform` int(10) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `picPath` varchar(255) DEFAULT NULL,
  `picName` varchar(255) DEFAULT NULL,
  `picUrl` varchar(255) DEFAULT NULL,
  `gamePath` varchar(255) DEFAULT NULL,
  `gameName` varchar(255) DEFAULT NULL,
  `gameUrl` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` tinytext,
  `displayName` varchar(255) DEFAULT NULL,
  `enabled` char(1) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `lastUpdateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_rolefeature`
-- ----------------------------
DROP TABLE IF EXISTS `t_rolefeature`;
CREATE TABLE `t_rolefeature` (
  `roleId` bigint(20) DEFAULT NULL,
  `featureId` bigint(20) DEFAULT NULL,
  KEY `roleId` (`roleId`) USING BTREE,
  KEY `featureId` (`featureId`) USING BTREE,
  CONSTRAINT `t_rolefeature_ibfk_1` FOREIGN KEY (`roleId`) REFERENCES `t_role` (`id`),
  CONSTRAINT `t_rolefeature_ibfk_2` FOREIGN KEY (`featureId`) REFERENCES `t_feature` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `roleId` bigint(20) DEFAULT NULL,
  `accountExpired` char(1) DEFAULT NULL,
  `accountLocked` char(1) DEFAULT NULL,
  `credentialsExpired` char(1) DEFAULT NULL,
  `enabled` char(1) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `gender` char(1) DEFAULT NULL,
  `mobile` varchar(12) DEFAULT NULL,
  `nickName` varchar(255) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `lastUpdateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `username` (`username`) USING BTREE,
  KEY `roleId` (`roleId`) USING BTREE,
  KEY `mobile` (`mobile`) USING BTREE,
  CONSTRAINT `t_user_ibfk_1` FOREIGN KEY (`roleId`) REFERENCES `t_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_userrole`
-- ----------------------------
DROP TABLE IF EXISTS `t_userrole`;
CREATE TABLE `t_userrole` (
  `userId` bigint(20) DEFAULT NULL,
  `roleId` bigint(20) DEFAULT NULL,
  KEY `roleId` (`roleId`) USING BTREE,
  KEY `userId` (`userId`) USING BTREE,
  CONSTRAINT `t_userrole_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `t_user` (`id`),
  CONSTRAINT `t_userrole_ibfk_2` FOREIGN KEY (`roleId`) REFERENCES `t_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_validatecode`
-- ----------------------------
DROP TABLE IF EXISTS `t_validatecode`;
CREATE TABLE `t_validatecode` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mobile` varchar(255) DEFAULT NULL,
  `code` varchar(6) DEFAULT NULL,
  `loseDate` datetime DEFAULT NULL,
  `type` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `mobile` (`mobile`) USING BTREE,
  KEY `type` (`type`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
