/*
Navicat MySQL Data Transfer

Source Server         : springcloud
Source Server Version : 80033
Source Host           : 192.168.3.41:3306
Source Database       : KeleBlog

Target Server Type    : MYSQL
Target Server Version : 80033
File Encoding         : 65001

Date: 2023-05-31 02:25:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_article
-- ----------------------------
DROP TABLE IF EXISTS `sys_article`;
CREATE TABLE `sys_article` (
  `id` int NOT NULL AUTO_INCREMENT,
  `category_id` int DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `content` text,
  `user_id` int DEFAULT NULL,
  `views` int DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `is_delete` tinyint DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sys_article
-- ----------------------------
INSERT INTO `sys_article` VALUES ('18', '1', '真正的文章测试', '真正的文章测试\n![](https://kele-blog.oss-cn-beijing.aliyuncs.com/imgs/1c387a7d-d57d-4c9d-9e81-5503de5173ab.png)\n```mermaid\nflowchart TD \n  Start --> Stop\n```\n', '1', '11', '2023-04-23 04:48:20', '2023-04-23 12:24:33', '0');
INSERT INTO `sys_article` VALUES ('19', '1', 'wenzhang2', 'wenzhang2', '1', '2', '2023-04-23 09:07:22', '2023-04-23 12:36:23', '0');
INSERT INTO `sys_article` VALUES ('20', '1', '文章3', '文章3', '1', '0', '2023-04-23 09:07:38', null, '0');
INSERT INTO `sys_article` VALUES ('21', '1', '文章4', '文章4123', '1', '1', '2023-04-23 09:07:55', '2023-04-27 07:15:25', '0');
INSERT INTO `sys_article` VALUES ('22', '1', '发布一个新的文章试一下', '发布一个新的文章试一下\n![](https://kele-blog.oss-cn-beijing.aliyuncs.com/imgs/9c6a9d6b-5d55-402e-9663-07b7bdfd61de.png)\n陶喆是我最近非常喜欢的歌手之一', '1', '2', '2023-04-23 11:47:36', '2023-04-25 05:12:18', '0');
INSERT INTO `sys_article` VALUES ('23', '1', '胖真让人羡慕啊', '# 胖真让人羡慕啊\n![](https://kele-blog.oss-cn-beijing.aliyuncs.com/imgs/b8d7f4dc-bdbc-4126-bd97-90a67b957663.png)\n```唯爱胖啊\n| col | col | col | col |\n| - | - | - | - |\n| 胖| 胖| 胖| 胖 |\n| 胖| 胖| 胖| 胖|\n| 胖| 胖| 胖| 胖|', '1', '4', '2023-04-23 12:39:32', '2023-04-23 12:40:12', '0');
INSERT INTO `sys_article` VALUES ('24', '1', '上传蜡笔小新的图片', '![](https://kele-blog.oss-cn-beijing.aliyuncs.com/imgs/4147423d-835d-4321-9c36-a304addb77c7.jpg)\n', '1', '6', '2023-04-25 05:30:42', '2023-04-25 05:30:43', '0');

-- ----------------------------
-- Table structure for sys_category
-- ----------------------------
DROP TABLE IF EXISTS `sys_category`;
CREATE TABLE `sys_category` (
  `id` int NOT NULL COMMENT '文章分类id',
  `class_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '文章分类名',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_delete` tinyint DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sys_category
-- ----------------------------
INSERT INTO `sys_category` VALUES ('1', '杂谈', '2023-04-27 07:25:06', null, '0');
INSERT INTO `sys_category` VALUES ('2', '科技', '2023-04-27 07:29:45', null, '0');

-- ----------------------------
-- Table structure for sys_comment
-- ----------------------------
DROP TABLE IF EXISTS `sys_comment`;
CREATE TABLE `sys_comment` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `article_id` int DEFAULT NULL COMMENT '文章id',
  `comment` varchar(255) DEFAULT NULL COMMENT '评论内容',
  `user_id` int DEFAULT NULL COMMENT '发表评论用户的id',
  `p_id` int DEFAULT NULL COMMENT '父评论id',
  `top_id` int DEFAULT NULL COMMENT '指向root评论',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_delete` tinyint DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sys_comment
-- ----------------------------
INSERT INTO `sys_comment` VALUES ('1', '18', '我是顶级评论', '1', '0', '0', '2023-04-24 01:43:40', null, '0');
INSERT INTO `sys_comment` VALUES ('2', '18', '我是二级评论', '1', '1', '1', '2023-04-24 01:44:02', null, '0');
INSERT INTO `sys_comment` VALUES ('3', '18', '我是三级评论', '2', '2', '1', '2023-04-25 02:39:29', null, '0');
INSERT INTO `sys_comment` VALUES ('5', '18', '我是顶级评论', '2', '0', '0', '2023-04-24 06:58:02', null, '0');
INSERT INTO `sys_comment` VALUES ('6', '18', '我是二级评论', '1', '5', '5', '2023-04-24 19:08:54', null, '0');
INSERT INTO `sys_comment` VALUES ('11', '18', '测试发表一个顶级评论', '1', '0', '0', '2023-04-25 05:20:41', null, '0');
INSERT INTO `sys_comment` VALUES ('12', '18', '尝试回复三级评论', '1', '3', '1', '2023-04-25 05:31:50', null, '0');
INSERT INTO `sys_comment` VALUES ('13', '18', '默认输入', '1', '0', '0', '2023-04-27 04:27:07', null, '0');
INSERT INTO `sys_comment` VALUES ('14', '18', '胖是个大帅哥', '1', '0', '0', '2023-05-16 00:44:22', null, '0');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `perms` varchar(255) DEFAULT NULL,
  `menu_name` varchar(255) DEFAULT NULL,
  `creat_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `is_delete` tinyint DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', 'Admin权限', 'ROLE_admin', '/*', '2023-04-14 17:24:26', '2023-04-14 17:24:28', '0');
INSERT INTO `sys_menu` VALUES ('2', '删除', 'delete', null, '2023-04-19 04:10:03', null, '0');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int NOT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `is_delete` tinyint DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '管理员', '2023-04-14 17:23:18', '2023-04-14 17:23:20', '0');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` int NOT NULL,
  `role_id` int DEFAULT NULL,
  `menu_id` int DEFAULT NULL,
  `signature` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `is_delete` tinyint DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('1', '1', '1', null, '2023-04-14 17:22:43', '2023-04-14 17:22:46', '0');
INSERT INTO `sys_role_menu` VALUES ('2', '1', '2', null, '2023-04-19 04:10:21', '2023-04-19 04:10:25', '0');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除1代表删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'kele', '$2a$10$Nrub2Dw3PhikhyYHTvKB2eO1PMhbm6PsmTjNV.XoCvpmtYv7VHyWa', 'https://kele-blog.oss-cn-beijing.aliyuncs.com/imgs/70e6eb72-335b-4ad9-a13e-b06ceae61688.jpg', '2023-04-14 17:22:00', '2023-04-21 04:26:36', '0');
INSERT INTO `sys_user` VALUES ('2', 'zs', '$2a$10$HdztkNdHsCcJkT1V.Mcn1.FpIE/HpKnEBUubInXdy5b0rF4goDYKi', 'https://kele-blog.oss-cn-beijing.aliyuncs.com/imgs/4147423d-835d-4321-9c36-a304addb77c7.jpg', '2023-04-25 02:36:01', null, '0');
INSERT INTO `sys_user` VALUES ('24', 'pang', '$2a$10$RrW.s2cYUrfr7pWmFKLMD.rKwjkDA8kDk0W2Pm7qL.Pi/53BWL2ni', 'beijing.aliyuncs.com/icon/default.png', '2023-05-16 00:51:39', null, '0');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` int NOT NULL,
  `user_id` int DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `is_delete` tinyint DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1', '1', '2023-04-14 17:22:10', '2023-04-14 17:22:12', '0');
INSERT INTO `sys_user_role` VALUES ('2', '24', '1', '2023-05-16 00:53:18', '2023-05-16 00:53:22', '0');
