/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50616
 Source Host           : localhost:3306
 Source Schema         : czlshop

 Target Server Type    : MySQL
 Target Server Version : 50616
 File Encoding         : 65001

 Date: 31/03/2020 16:22:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

create database `czlshop` default character set utf8 collate utf8_general_ci;

use czlshop;
-- ----------------------------
-- Table structure for biz_buy
-- ----------------------------
DROP TABLE IF EXISTS `biz_buy`;
CREATE TABLE `biz_buy`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `product_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `time` timestamp(0) NULL DEFAULT NULL,
  `num` int(11) NULL DEFAULT NULL,
  `price` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of biz_buy
-- ----------------------------
INSERT INTO `biz_buy` VALUES ('1', '1', 'USERee31ea93e5ac4f7ea0d8b044dff641ec', '2020-03-30 04:53:45', 10, '2');
INSERT INTO `biz_buy` VALUES ('2', '2', 'USERee31ea93e5ac4f7ea0d8b044dff641ec', '2020-03-19 04:54:07', 10, '2');
INSERT INTO `biz_buy` VALUES ('3', '3', '3', NULL, 11, '3');

-- ----------------------------
-- Table structure for biz_car
-- ----------------------------
DROP TABLE IF EXISTS `biz_car`;
CREATE TABLE `biz_car`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `product_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `num` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of biz_car
-- ----------------------------
INSERT INTO `biz_car` VALUES ('1', '5', 'USERee31ea93e5ac4f7ea0d8b044dff641ec', 1);
INSERT INTO `biz_car` VALUES ('2', '2', 'USERee31ea93e5ac4f7ea0d8b044dff641ec', 3);
INSERT INTO `biz_car` VALUES ('3', '7', 'USERee31ea93e5ac4f7ea0d8b044dff641ec', 2);
INSERT INTO `biz_car` VALUES ('4', '3', 'USERee31ea93e5ac4f7ea0d8b044dff641ec', 2);
INSERT INTO `biz_car` VALUES ('55', '55', '55', NULL);
INSERT INTO `biz_car` VALUES ('CAR9a826a5c814e4060b413f9c00dc8bb8e', '67', '565', 10);
INSERT INTO `biz_car` VALUES ('CARa6c2535bca5f46d09b8af7a0272ab094', '67', '564', 5);

-- ----------------------------
-- Table structure for biz_product
-- ----------------------------
DROP TABLE IF EXISTS `biz_product`;
CREATE TABLE `biz_product`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `summary` varchar(140) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of biz_product
-- ----------------------------
INSERT INTO `biz_product` VALUES ('1', '1', 'images/book/2ab7b86be448341df0247b4e44fc2e3b.png', '1', '啊啊', '1', 1);
INSERT INTO `biz_product` VALUES ('10', '10', 'images/book/2ab7b86be448341df0247b4e44fc2e3b.png', '10', '10', '10', 1);
INSERT INTO `biz_product` VALUES ('11', '11', 'images/book/2ab7b86be448341df0247b4e44fc2e3b.png', '11', '11', '11', 1);
INSERT INTO `biz_product` VALUES ('12', '121', 'images/book/2ab7b86be448341df0247b4e44fc2e3b.png', '12', '12', '12', 1);
INSERT INTO `biz_product` VALUES ('13', '13', 'images/book/2ab7b86be448341df0247b4e44fc2e3b.png', '13', '13', '13', 1);
INSERT INTO `biz_product` VALUES ('14', '14', 'images/book/2ab7b86be448341df0247b4e44fc2e3b.png', '14', '14', '14', 1);
INSERT INTO `biz_product` VALUES ('15', '15', 'images/book/2ab7b86be448341df0247b4e44fc2e3b.png', '15', '15', '15', 1);
INSERT INTO `biz_product` VALUES ('16', '16', 'images/book/2ab7b86be448341df0247b4e44fc2e3b.png', '16', '16', '16', 1);
INSERT INTO `biz_product` VALUES ('17', '17', 'images/book/2ab7b86be448341df0247b4e44fc2e3b.png', '17', '17', '17', 1);
INSERT INTO `biz_product` VALUES ('18', '18', 'images/2de3494c8b2f08a6bfa70eda07e76e5a.jpg', '18', '18', '18', 1);
INSERT INTO `biz_product` VALUES ('19', '19', 'images/book/2ab7b86be448341df0247b4e44fc2e3b.png', '19', '19', '19', 1);
INSERT INTO `biz_product` VALUES ('2', '2', 'images/book/2ab7b86be448341df0247b4e44fc2e3b.png', '2', '2', '2', 1);
INSERT INTO `biz_product` VALUES ('3', '3', 'images/2de3494c8b2f08a6bfa70eda07e76e5a.jpg', '3.2', '3', '3', 1);
INSERT INTO `biz_product` VALUES ('4', '4', 'images/book/2ab7b86be448341df0247b4e44fc2e3b.png', '4', '4', '4', 1);
INSERT INTO `biz_product` VALUES ('5', '5', 'images/book/2ab7b86be448341df0247b4e44fc2e3b.png', '5', '5', '5', 1);
INSERT INTO `biz_product` VALUES ('6', '6', 'images/book/2ab7b86be448341df0247b4e44fc2e3b.png', '6', '6', '6', 1);
INSERT INTO `biz_product` VALUES ('7', '7', 'images/book/2ab7b86be448341df0247b4e44fc2e3b.png', '7', '7', '7', 0);
INSERT INTO `biz_product` VALUES ('8', '8', 'images/book/2ab7b86be448341df0247b4e44fc2e3b.png', '8', '8', '8', 1);
INSERT INTO `biz_product` VALUES ('9', '9', 'images/book/2ab7b86be448341df0247b4e44fc2e3b.png', '9', '9', '9', 1);

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `permission` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `desc` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1', 'permissionOfRole:update', '修改角色对应的权限');
INSERT INTO `sys_permission` VALUES ('2', 'permission:query', '查询所有的权限');
INSERT INTO `sys_permission` VALUES ('3', 'car:delete', '删除购物车产品');
INSERT INTO `sys_permission` VALUES ('4', 'car:query', '查询购物车产品');
INSERT INTO `sys_permission` VALUES ('5', 'product:buyerQuery', '买家产品首页列表查询');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `desc` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'admin', '超级管理员');
INSERT INTO `sys_role` VALUES ('2', 'seller', '卖家');
INSERT INTO `sys_role` VALUES ('3', 'buyer', '买家(用户)');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `role_id` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `permission_id` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`role_id`, `permission_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('3', '1');
INSERT INTO `sys_role_permission` VALUES ('3', '2');
INSERT INTO `sys_role_permission` VALUES ('3', '3');
INSERT INTO `sys_role_permission` VALUES ('3', '4');
INSERT INTO `sys_role_permission` VALUES ('3', '5');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nickname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `register_time` timestamp(0) NULL DEFAULT NULL,
  `user_state` int(11) NULL DEFAULT NULL COMMENT '1启用，0关闭',
  `role_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `role_id`(`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('USER0279082f105b4a94acd2bf7538c21e52', 'seller', 'relles', '陈泽磊(卖家)', NULL, '2020-03-28 02:37:23', 1, '2');
INSERT INTO `sys_user` VALUES ('USERee31ea93e5ac4f7ea0d8b044dff641ec', 'buyer', 'reyub', '陈泽磊(买家)', NULL, '2020-03-28 02:35:52', 1, '3');

SET FOREIGN_KEY_CHECKS = 1;
