/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50610
Source Host           : localhost:3306
Source Database       : crawler

Target Server Type    : MYSQL
Target Server Version : 50610
File Encoding         : 65001

Date: 2017-01-22 22:40:13
*/

-- SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for beautiful_pictures
-- ----------------------------
DROP TABLE IF EXISTS `beautiful_pictures`;
CREATE TABLE `beautiful_pictures` (
  `id` varchar(255) NOT NULL COMMENT '美女图片爬取',
  `title` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `pictureurls_num` int(11) DEFAULT NULL,
  `zan` int(11) DEFAULT NULL,
  `biaoqian` varchar(255) DEFAULT NULL,
  `keywords` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for picture
-- ----------------------------
DROP TABLE IF EXISTS `picture`;
CREATE TABLE `picture` (
  `id` varchar(255) NOT NULL COMMENT '每张图片的地址',
  `pictures_id` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `http_log`;
CREATE TABLE `http_log` (
  `id` varchar(255) NOT NULL COMMENT '日志主键',
  `client_ip` varchar(255) DEFAULT NULL,
  `uri` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `method` varchar(255) DEFAULT NULL,
  `param_data` varchar(255) DEFAULT NULL,
  `session_id` varchar(255) DEFAULT NULL,
  `time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `return_time` varchar(255) DEFAULT NULL,
  `return_data` longtext,
  `http_status_code` varchar(255) DEFAULT NULL,
  `time_consuming` int(8) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;













