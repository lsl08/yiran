/*
Navicat MySQL Data Transfer
Source Server Version : 50723
Source Database       : ry

Date: 2019-11-20 20:43:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_weixin_moving_car`
-- ----------------------------
DROP TABLE IF EXISTS `sys_weixin_moving_car`;
CREATE TABLE `sys_weixin_moving_car` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `open_id` varchar(50) DEFAULT NULL COMMENT '微信openId',
  `nick_name` varchar(100) DEFAULT NULL COMMENT '昵称',
  `license_plate` varchar(20) DEFAULT NULL COMMENT '车牌',
  `phone_number1` varchar(11) DEFAULT NULL COMMENT '手机号',
  `phone_number2` varchar(11) DEFAULT NULL COMMENT '备用手机',
  `qr_code_url` varchar(200) DEFAULT NULL COMMENT '二维码路径',
  `extend1` varchar(200) DEFAULT NULL COMMENT '扩展字段1',
  `extend2` varchar(200) DEFAULT NULL COMMENT '扩展字段2',
  `extend3` varchar(200) DEFAULT NULL COMMENT '扩展字段3',
  `create_by` varchar(64) CHARACTER SET utf8 DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='微信挪车用户信息';

-- ----------------------------
-- Records of sys_weixin_moving_car
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_weixin_moving_car_detail`
-- ----------------------------
DROP TABLE IF EXISTS `sys_weixin_moving_car_detail`;
CREATE TABLE `sys_weixin_moving_car_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `open_id` varchar(50) DEFAULT NULL COMMENT '微信openId',
  `license_plate` varchar(50) DEFAULT NULL COMMENT '车牌',
  `phone_number` varchar(11) DEFAULT NULL COMMENT '手机号',
  `address` varchar(1000) DEFAULT NULL COMMENT '违停地址',
  `notiy_type` int(2) DEFAULT NULL COMMENT '通知方式1微信通知2短信通知',
  `extend1` varchar(200) DEFAULT NULL COMMENT '扩展字段1',
  `extend2` varchar(200) DEFAULT NULL COMMENT '扩展字段2',
  `extend3` varchar(200) DEFAULT NULL COMMENT '扩展字段3',
  `create_by` varchar(64) CHARACTER SET utf8 DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='挪车明细';

-- ----------------------------
-- Records of sys_weixin_moving_car_detail
-- ----------------------------
