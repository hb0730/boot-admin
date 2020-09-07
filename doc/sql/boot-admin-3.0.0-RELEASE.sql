/*
 Navicat Premium Data Transfer

 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Schema         : boot-admin

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 07/09/2020 07:36:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_dept`;
CREATE TABLE `t_sys_dept`  (
  `version` int(11) NULL DEFAULT NULL COMMENT '乐观锁',
  `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_enabled` int(11) NULL DEFAULT NULL COMMENT '是否启用',
  `del_flag` int(11) NULL DEFAULT NULL COMMENT '是否删除',
  `description` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `id` bigint(20) NOT NULL COMMENT 'id',
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
  `leader` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '负责人',
  `phone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电话',
  `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父级',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `ancestors` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '祖级关系',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '部门 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_dept
-- ----------------------------
INSERT INTO `t_sys_dept` VALUES (2, -1, '2020-09-07 07:08:41', -1, '2020-09-06 23:19:45', 1, 0, NULL, 1301320317976760321, '华南分部', '超级管理员', NULL, NULL, -1, 0, '-1');
INSERT INTO `t_sys_dept` VALUES (1, -1, '2020-09-06 23:20:05', NULL, NULL, 1, 0, '', 1302748434335297538, '研发部', '超级管理员', '', '', 1301320317976760321, 3, '-1_1301320317976760321');
INSERT INTO `t_sys_dept` VALUES (1, -1, '2020-09-06 23:20:31', NULL, NULL, 1, 0, '', 1302748540644126722, '运维部', '超级管理员', '', '', 1301320317976760321, 4, '-1_1301320317976760321');
INSERT INTO `t_sys_dept` VALUES (1, -1, '2020-09-06 23:20:59', NULL, NULL, 1, 0, '', 1302748659170963458, '华北分部', '超级管理员', '', '', -1, 1, '-1');
INSERT INTO `t_sys_dept` VALUES (1, -1, '2020-09-06 23:21:21', NULL, NULL, 1, 0, '', 1302748750128640001, '测试部', '测试部', '', '', 1302748659170963458, 6, '-1_1302748659170963458');
INSERT INTO `t_sys_dept` VALUES (1, -1, '2020-09-06 23:21:38', NULL, NULL, 1, 0, '', 1302748821356310530, 'UI部门', '超级管理员', '', '', 1302748659170963458, 7, '-1_1302748659170963458');

-- ----------------------------
-- Table structure for t_sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_dict`;
CREATE TABLE `t_sys_dict`  (
  `version` int(11) NULL DEFAULT NULL COMMENT '乐观锁',
  `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_enabled` int(11) NULL DEFAULT NULL COMMENT '是否启用',
  `del_flag` int(11) NULL DEFAULT NULL COMMENT '是否删除',
  `description` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `id` bigint(20) NOT NULL COMMENT 'id',
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '字典名称',
  `type` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '字典类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '数据字典 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_dict
-- ----------------------------
INSERT INTO `t_sys_dict` VALUES (1, -1, '2020-09-06 23:25:36', NULL, NULL, 1, 0, '', 1302749820213030914, '用户性别', 'sys_user_gender');
INSERT INTO `t_sys_dict` VALUES (2, -1, '2020-09-06 23:29:51', -1, '2020-09-06 23:34:11', 1, 0, '', 1302750889848631298, '系统状态', 'sys_common_status');

-- ----------------------------
-- Table structure for t_sys_dict_entry
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_dict_entry`;
CREATE TABLE `t_sys_dict_entry`  (
  `version` int(11) NULL DEFAULT NULL COMMENT '乐观锁',
  `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_enabled` int(11) NULL DEFAULT NULL COMMENT '是否启用',
  `del_flag` int(11) NULL DEFAULT NULL COMMENT '是否删除',
  `description` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `id` bigint(20) NOT NULL COMMENT 'id',
  `parent_id` bigint(20) NOT NULL COMMENT '字典id',
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
  `value` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '字典值',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '数据字典项 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_dict_entry
-- ----------------------------
INSERT INTO `t_sys_dict_entry` VALUES (1, -1, '2020-09-06 23:27:02', NULL, NULL, 1, 0, NULL, 1302750183850799106, 1302749820213030914, '男', '0', 1);
INSERT INTO `t_sys_dict_entry` VALUES (1, -1, '2020-09-06 23:28:21', NULL, NULL, 1, 0, NULL, 1302750511375609857, 1302749820213030914, '女', '1', 2);
INSERT INTO `t_sys_dict_entry` VALUES (1, -1, '2020-09-06 23:29:09', NULL, NULL, 1, 0, NULL, 1302750714396700673, 1302749820213030914, '未知', '2', 3);
INSERT INTO `t_sys_dict_entry` VALUES (1, -1, '2020-09-06 23:30:13', NULL, NULL, 1, 0, NULL, 1302750984195305474, 1302750889848631298, '禁用', '0', 1);
INSERT INTO `t_sys_dict_entry` VALUES (1, -1, '2020-09-06 23:30:27', NULL, NULL, 1, 0, NULL, 1302751041317531649, 1302750889848631298, '启用', '1', 0);

-- ----------------------------
-- Table structure for t_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_menu`;
CREATE TABLE `t_sys_menu`  (
  `version` int(11) NULL DEFAULT NULL COMMENT '乐观锁',
  `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_enabled` int(11) NULL DEFAULT NULL COMMENT '是否启用',
  `del_flag` int(11) NULL DEFAULT NULL COMMENT '是否删除',
  `description` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `id` bigint(20) NOT NULL COMMENT 'id',
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
  `enname` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '组件名称',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父菜单id',
  `path` varchar(3072) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '路由地址',
  `component` varchar(3072) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '组件地址',
  `icon` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图标',
  `sort` int(11) NULL DEFAULT NULL COMMENT '展示顺序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '菜单 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_menu
-- ----------------------------
INSERT INTO `t_sys_menu` VALUES (1, -1, '2020-09-07 07:11:37', NULL, NULL, 1, 0, NULL, 1, '系统管理', 'systemManager', -1, '/boot/admin/system', '', 'fa fa-cogs', 10);
INSERT INTO `t_sys_menu` VALUES (1, -1, '2020-09-07 07:12:51', NULL, NULL, 1, 0, NULL, 2, '菜单管理', 'menuManager', 1, '/boot/admin/system/menu', 'bootAdmin/system/menu/index', 'el-icon-menu', 11);
INSERT INTO `t_sys_menu` VALUES (1, -1, '2020-09-06 23:16:12', NULL, NULL, 1, 0, NULL, 1302747457825828866, '部门管理', 'deptManager', 1, '/boot/admin/system/dept', 'bootAdmin/system/dept/index', 'fa fa-sitemap', 12);
INSERT INTO `t_sys_menu` VALUES (1, -1, '2020-09-06 23:16:48', NULL, NULL, 1, 0, NULL, 1302747607004639233, '角色管理', 'roleManager', 1, '/boot/admin/system/role', 'bootAdmin/system/role/index', 'fa fa-user-secret', 13);
INSERT INTO `t_sys_menu` VALUES (1, -1, '2020-09-06 23:17:47', NULL, NULL, 1, 0, NULL, 1302747852438532097, '岗位管理', 'postManger', 1, '/boot/admin/system/post', 'bootAdmin/system/post/index', 'el-icon-postcard', 14);
INSERT INTO `t_sys_menu` VALUES (2, -1, '2020-09-06 23:18:23', -1, '2020-09-06 23:19:10', 1, 0, NULL, 1302748004347834370, '用户管理', 'userManager', 1, '/boot/admin/system/userList', 'bootAdmin/system/user/list/index', 'fa fa-users', 11);
INSERT INTO `t_sys_menu` VALUES (1, -1, '2020-09-06 23:18:56', NULL, NULL, 1, 0, NULL, 1302748141795176449, '字典管理', 'dictManager', 1, '/boot/admin/system/dict', 'bootAdmin/system/dict/index', 'fa fa-bookmark-o', 16);

-- ----------------------------
-- Table structure for t_sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_permission`;
CREATE TABLE `t_sys_permission`  (
  `version` int(11) NULL DEFAULT NULL COMMENT '乐观锁',
  `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_enabled` int(11) NULL DEFAULT NULL COMMENT '是否启用',
  `del_flag` int(11) NULL DEFAULT NULL COMMENT '是否删除',
  `description` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `id` bigint(20) NOT NULL COMMENT 'id',
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
  `permission` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标识符',
  `menu_id` bigint(20) NULL DEFAULT NULL COMMENT '菜单id',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '权限 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_permission
-- ----------------------------

-- ----------------------------
-- Table structure for t_sys_post
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_post`;
CREATE TABLE `t_sys_post`  (
  `version` int(11) NULL DEFAULT NULL COMMENT '乐观锁',
  `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_enabled` int(11) NULL DEFAULT NULL COMMENT '是否启用',
  `del_flag` int(11) NULL DEFAULT NULL COMMENT '是否删除',
  `description` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `id` bigint(20) NOT NULL COMMENT 'id',
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
  `number` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '编码',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '岗位 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_post
-- ----------------------------
INSERT INTO `t_sys_post` VALUES (1, -1, '2020-09-06 23:23:28', NULL, NULL, 1, 0, '', 1302749284118065153, '全栈开发', 'S001', 1);
INSERT INTO `t_sys_post` VALUES (1, -1, '2020-09-06 23:23:40', NULL, NULL, 1, 0, '', 1302749333476634626, '人事专员', 'S002', 2);
INSERT INTO `t_sys_post` VALUES (1, -1, '2020-09-06 23:23:58', NULL, NULL, 1, 0, '', 1302749408756002818, '产品经理', 'S003', 3);
INSERT INTO `t_sys_post` VALUES (1, -1, '2020-09-06 23:24:10', NULL, NULL, 1, 0, '', 1302749458869547010, '软件测试', 'S004', 4);

-- ----------------------------
-- Table structure for t_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role`;
CREATE TABLE `t_sys_role`  (
  `version` int(11) NULL DEFAULT NULL COMMENT '乐观锁',
  `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_enabled` int(11) NULL DEFAULT NULL COMMENT '是否启用',
  `del_flag` int(11) NULL DEFAULT NULL COMMENT '是否删除',
  `description` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `id` bigint(20) NOT NULL COMMENT 'id',
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
  `code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标识',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_role
-- ----------------------------
INSERT INTO `t_sys_role` VALUES (1, -1, '2020-09-06 23:23:00', NULL, NULL, 1, 0, NULL, 1302749167210229762, '超级管理员', 'ROLE_ADMINISTRATOR', 1);

-- ----------------------------
-- Table structure for t_sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role_dept`;
CREATE TABLE `t_sys_role_dept`  (
  `version` int(11) NULL DEFAULT NULL COMMENT '乐观锁',
  `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_enabled` int(11) NULL DEFAULT NULL COMMENT '是否启用',
  `del_flag` int(11) NULL DEFAULT NULL COMMENT '是否删除',
  `description` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `id` bigint(20) NOT NULL COMMENT 'id',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色id',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色数据权限 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_role_dept
-- ----------------------------
INSERT INTO `t_sys_role_dept` VALUES (1, -1, '2020-09-06 23:23:00', NULL, NULL, 1, 0, NULL, 1302749167365419010, 1302749167210229762, 1301320317976760321);
INSERT INTO `t_sys_role_dept` VALUES (1, -1, '2020-09-06 23:23:00', NULL, NULL, 1, 0, NULL, 1302749167394779138, 1302749167210229762, 1302748659170963458);
INSERT INTO `t_sys_role_dept` VALUES (1, -1, '2020-09-06 23:23:00', NULL, NULL, 1, 0, NULL, 1302749167398973442, 1302749167210229762, 1302748434335297538);
INSERT INTO `t_sys_role_dept` VALUES (1, -1, '2020-09-06 23:23:00', NULL, NULL, 1, 0, NULL, 1302749167398973443, 1302749167210229762, 1302748540644126722);
INSERT INTO `t_sys_role_dept` VALUES (1, -1, '2020-09-06 23:23:00', NULL, NULL, 1, 0, NULL, 1302749167398973444, 1302749167210229762, 1302748750128640001);
INSERT INTO `t_sys_role_dept` VALUES (1, -1, '2020-09-06 23:23:00', NULL, NULL, 1, 0, NULL, 1302749167398973445, 1302749167210229762, 1302748821356310530);

-- ----------------------------
-- Table structure for t_sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role_permission`;
CREATE TABLE `t_sys_role_permission`  (
  `version` int(11) NULL DEFAULT NULL COMMENT '乐观锁',
  `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_enabled` int(11) NULL DEFAULT NULL COMMENT '是否启用',
  `del_flag` int(11) NULL DEFAULT NULL COMMENT '是否删除',
  `description` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `id` bigint(20) NOT NULL COMMENT 'id',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色id',
  `permission_id` bigint(20) NULL DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色权限 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_role_permission
-- ----------------------------

-- ----------------------------
-- Table structure for t_sys_user_account
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user_account`;
CREATE TABLE `t_sys_user_account`  (
  `version` int(11) NULL DEFAULT NULL COMMENT '乐观锁',
  `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_enabled` int(11) NULL DEFAULT NULL COMMENT '是否启用',
  `del_flag` int(11) NULL DEFAULT NULL COMMENT '是否删除',
  `description` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `id` bigint(20) NOT NULL COMMENT 'id',
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户账号',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户密码',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户账号 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_user_account
-- ----------------------------
INSERT INTO `t_sys_user_account` VALUES (1, -1, NULL, NULL, NULL, 1, 0, NULL, 1298548255977635841, 'Administrator', '$2a$10$GX.Ecwhp2gxt9fIM9wClWuUt2kUD0YtotWvNUmHtN7ftt2DISf4mC', -1);

-- ----------------------------
-- Table structure for t_sys_user_info
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user_info`;
CREATE TABLE `t_sys_user_info`  (
  `version` int(11) NULL DEFAULT NULL COMMENT '乐观锁',
  `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_enabled` int(11) NULL DEFAULT NULL COMMENT '是否启用',
  `del_flag` int(11) NULL DEFAULT NULL COMMENT '是否删除',
  `description` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `id` bigint(20) NOT NULL COMMENT 'id',
  `nick_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '昵称',
  `phone_number` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户手机号',
  `gender` int(11) NULL DEFAULT NULL COMMENT '性别',
  `avatar` varchar(3072) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像',
  `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门',
  `is_admin` int(11) NULL DEFAULT NULL COMMENT '是否为admin账号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户信息 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_user_info
-- ----------------------------
INSERT INTO `t_sys_user_info` VALUES (3, -1, NULL, -1, '2020-08-28 08:44:44', 1, 0, NULL, -1, '超级管理员', NULL, 1, '', '1278032416@qq.com', -1, 1);

-- ----------------------------
-- Table structure for t_sys_user_post
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user_post`;
CREATE TABLE `t_sys_user_post`  (
  `version` int(11) NULL DEFAULT NULL COMMENT '乐观锁',
  `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_enabled` int(11) NULL DEFAULT NULL COMMENT '是否启用',
  `del_flag` int(11) NULL DEFAULT NULL COMMENT '是否删除',
  `description` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `id` bigint(20) NOT NULL COMMENT 'id',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `post_id` bigint(20) NULL DEFAULT NULL COMMENT '岗位id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户岗位 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_user_post
-- ----------------------------

-- ----------------------------
-- Table structure for t_sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user_role`;
CREATE TABLE `t_sys_user_role`  (
  `version` int(11) NULL DEFAULT NULL COMMENT '乐观锁',
  `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_enabled` int(11) NULL DEFAULT NULL COMMENT '是否启用',
  `del_flag` int(11) NULL DEFAULT NULL COMMENT '是否删除',
  `description` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `id` bigint(20) NOT NULL COMMENT 'id',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色id'
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户角色 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_user_role
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
