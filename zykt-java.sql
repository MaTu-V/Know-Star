/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : zykt

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2020-02-15 16:46:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for zykt_book
-- ----------------------------
DROP TABLE IF EXISTS `zykt_book`;
CREATE TABLE `zykt_book` (
  `id` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '默认id',
  `name` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '书名',
  `author` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '作者',
  `publisher` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '出版社',
  `edition` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '版本',
  `publish_time` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '出版时间',
  `keyword` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '搜素关键字',
  `image_url` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '图片路径',
  `insert_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '插入及更新时间',
  `collection_number` int(11) NOT NULL DEFAULT '0' COMMENT '收藏人数',
  `status` smallint(5) unsigned zerofill NOT NULL COMMENT '更新状态',
  `classify_id` int(11) DEFAULT NULL COMMENT '类目id',
  `describes` text CHARACTER SET utf8 COMMENT '书籍描述信息',
  `ranges` text CHARACTER SET utf8 COMMENT '书籍使用人群',
  PRIMARY KEY (`id`),
  KEY `classify_id` (`classify_id`),
  CONSTRAINT `zykt_book_ibfk_1` FOREIGN KEY (`classify_id`) REFERENCES `zykt_classify` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for zykt_category
-- ----------------------------
DROP TABLE IF EXISTS `zykt_category`;
CREATE TABLE `zykt_category` (
  `id` int(255) NOT NULL COMMENT '默认id',
  `name` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '分类名称',
  `series_id` int(255) DEFAULT NULL COMMENT '所属课程id',
  PRIMARY KEY (`id`),
  KEY `series_id` (`series_id`),
  CONSTRAINT `zykt_category_ibfk_1` FOREIGN KEY (`series_id`) REFERENCES `zykt_series` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for zykt_classify
-- ----------------------------
DROP TABLE IF EXISTS `zykt_classify`;
CREATE TABLE `zykt_classify` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '默认id',
  `name` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '分类名称',
  `icon` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '图标',
  `icon_back` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '图标背景色',
  `category_id` int(255) NOT NULL COMMENT '分类属性',
  `status` smallint(6) NOT NULL COMMENT '推荐使用',
  PRIMARY KEY (`id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `zykt_classify_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `zykt_category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=154 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for zykt_collection
-- ----------------------------
DROP TABLE IF EXISTS `zykt_collection`;
CREATE TABLE `zykt_collection` (
  `id` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '默认id',
  `user_id` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '收藏人id',
  `book_id` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '收藏书籍id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`,`book_id`),
  KEY `book_id` (`book_id`),
  CONSTRAINT `zykt_collection_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `zykt_user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `zykt_collection_ibfk_2` FOREIGN KEY (`book_id`) REFERENCES `zykt_book` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for zykt_hotword
-- ----------------------------
DROP TABLE IF EXISTS `zykt_hotword`;
CREATE TABLE `zykt_hotword` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '默认id',
  `hotword` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '热搜词',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=285 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for zykt_material
-- ----------------------------
DROP TABLE IF EXISTS `zykt_material`;
CREATE TABLE `zykt_material` (
  `id` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '默认id',
  `name` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '文档名称',
  `file_url` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '文件路径',
  `types` smallint(6) NOT NULL COMMENT '类型',
  `insert_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '插入及更新时间',
  `user_id` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '上传者id',
  `book_id` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '对应书籍编号',
  PRIMARY KEY (`id`),
  KEY `book_id` (`book_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `zykt_material_ibfk_1` FOREIGN KEY (`book_id`) REFERENCES `zykt_book` (`id`),
  CONSTRAINT `zykt_material_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `zykt_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for zykt_series
-- ----------------------------
DROP TABLE IF EXISTS `zykt_series`;
CREATE TABLE `zykt_series` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '默认id',
  `name` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '所属系列',
  `icon` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '图标样式',
  `back` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '背景色',
  `image_url` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '图片位置',
  `describe` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for zykt_user
-- ----------------------------
DROP TABLE IF EXISTS `zykt_user`;
CREATE TABLE `zykt_user` (
  `id` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '默认id',
  `open_id` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '小程序用户openid',
  `nick_name` varchar(100) CHARACTER SET utf8mb4 NOT NULL COMMENT '用户名',
  `gender` int(11) DEFAULT NULL COMMENT '性别',
  `avatar_url` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户头像',
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建/注册时间',
  `collection` int(11) unsigned zerofill DEFAULT '00000000000' COMMENT '收藏数量',
  `publish` int(11) unsigned zerofill DEFAULT '00000000000' COMMENT '发布数量',
  `energy` int(11) unsigned zerofill DEFAULT '00000000000' COMMENT '能量值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for zykt_website
-- ----------------------------
DROP TABLE IF EXISTS `zykt_website`;
CREATE TABLE `zykt_website` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '默认id',
  `title` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '网站标题',
  `keyword` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '搜索关键字',
  `describe` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '网站描述',
  `web_back` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '背景图片',
  `app_back` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT 'app背景图',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Procedure structure for addCollection
-- ----------------------------
DROP PROCEDURE IF EXISTS `addCollection`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `addCollection`(IN `id` varchar(255),IN `userId` varchar(255),IN `bookId` varchar(255))
BEGIN
	#Routine body goes here...

  update zykt_user as Us set collection = (Us.collection+1) where Us.id = userId;
  update zykt_book as Bo set collection_number = (Bo.collection_number+1) where Bo.id = bookId;
  INSERT INTO zykt_collection VALUES(id,userId,bookId);
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for delCollection
-- ----------------------------
DROP PROCEDURE IF EXISTS `delCollection`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `delCollection`(IN `userId` varchar(255),IN `bookId` varchar(255))
BEGIN
	#Routine body goes here...

  update zykt_user as Us set collection = (Us.collection-1) where Us.id = userId;
  update zykt_book as Bo set collection_number = (Bo.collection_number-1) where Bo.id = bookId;
  DELETE from zykt_collection where user_id= userId and book_id = bookId;
END
;;
DELIMITER ;
