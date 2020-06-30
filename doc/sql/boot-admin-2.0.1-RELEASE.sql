/*
 Navicat Premium Data Transfer

 Source Server         :
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           :
 Source Schema         : boot-admin

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 30/06/2020 16:27:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_base_img
-- ----------------------------
DROP TABLE IF EXISTS `t_base_img`;
CREATE TABLE `t_base_img`  (
  `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_enabled` int(2) NULL DEFAULT NULL COMMENT '是否启用',
  `del_flag` int(11) NULL DEFAULT NULL COMMENT '是否删除',
  `version` int(11) NULL DEFAULT NULL COMMENT '版本',
  `description` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `id` bigint(20) NULL DEFAULT NULL COMMENT 'id',
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
  `path` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '路径',
  `file_key` varchar(3072) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密钥',
  `thumb_path` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '缩略图访问路径',
  `media_type` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '类型',
  `suffix` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '后缀',
  `width` int(11) NULL DEFAULT NULL COMMENT '宽',
  `height` int(11) NULL DEFAULT NULL COMMENT '高',
  `size` bigint(20) NULL DEFAULT NULL COMMENT '大小',
  `type` int(11) NULL DEFAULT NULL COMMENT '上传类型'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '图库 ' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

-- SQL
INSERT INTO `t_system_menu`(`create_user_id`, `create_time`, `update_user_id`, `update_time`, `is_enabled`, `del_flag`, `version`, `description`, `id`, `name`, `enname`, `url`, `icon`, `sort`, `parent_id`, `has_child`, `is_root`, `level`) VALUES (-1, '2020-06-30 09:36:36', NULL, NULL, 1, 0, 1, '', 1277778026561613826, '资源管理', '', '/bootAdmin/systemManager/imag', '', 4, 1243023104032104449, 0, 0, 2);
