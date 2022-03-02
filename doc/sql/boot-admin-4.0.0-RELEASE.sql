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

 Date: 09/09/2020 17:39:26
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
-- Table structure for t_sys_job
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_job`;
CREATE TABLE `t_sys_job`  (
  `version` int(11) NULL DEFAULT NULL COMMENT '乐观锁',
  `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_enabled` int(11) NULL DEFAULT NULL COMMENT '是否启用',
  `del_flag` int(11) NULL DEFAULT NULL COMMENT '是否删除',
  `description` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '任务名称',
  `group` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '任务组',
  `bean_name` varchar(3072) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'Bean名称',
  `bean_method` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '方法名称',
  `method_params` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '参数',
  `cron` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'cron表达式',
  `person_in_charge` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '负责人',
  `email` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '负责人邮箱',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '定时任务(quratz) ' ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for t_sys_job_log
-- ----------------------------

DROP TABLE IF EXISTS `t_sys_job_log`;
CREATE TABLE `t_sys_job_log`  (
  `version` int(11) NULL DEFAULT NULL COMMENT '乐观锁',
  `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_enabled` int(11) NULL DEFAULT NULL COMMENT '是否启用',
  `del_flag` int(11) NULL DEFAULT NULL COMMENT '是否删除',
  `description` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `job_id` bigint(20) NULL DEFAULT NULL COMMENT '任务id',
  `job_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '任务名称',
  `job_group` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '任务组',
  `invoke_target` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '调用目标',
  `method_params` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '调用参数',
  `job_message` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '日志信息',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态 0失败1成功',
  `exception_info` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '异常信息',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '任务日志 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_sys_login_log
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_login_log`;
CREATE TABLE `t_sys_login_log`  (
  `version` int(11) NULL DEFAULT NULL COMMENT '乐观锁',
  `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_enabled` int(11) NULL DEFAULT NULL COMMENT '是否启用',
  `del_flag` int(11) NULL DEFAULT NULL COMMENT '是否删除',
  `description` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `id` bigint(20) NOT NULL COMMENT 'id',
  `username` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '登录名',
  `login_ip` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '登录ip',
  `browser` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '浏览器',
  `os` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '操作系统',
  `status` int(11) NULL DEFAULT NULL COMMENT '登录状态',
  `message` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '登录信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '登录日志 ' ROW_FORMAT = Dynamic;

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
-- Table structure for t_sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_oper_log`;
CREATE TABLE `t_sys_oper_log`  (
  `version` int(11) NULL DEFAULT NULL COMMENT '乐观锁',
  `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_enabled` int(11) NULL DEFAULT NULL COMMENT '是否启用',
  `del_flag` int(11) NULL DEFAULT NULL COMMENT '是否删除',
  `description` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `id` bigint(20) NOT NULL COMMENT 'id',
  `username` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '操作人员',
  `oper_type` int(11) NULL DEFAULT NULL COMMENT '操作类型',
  `oper_ip` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '操作ip',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态',
  `request_url` varchar(3072) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '请求地址',
  `request_method` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '请求方法',
  `oper_method` varchar(3072) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '操作方法',
  `request_params` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '请求参数',
  `request_result` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '响应参数',
  `error_message` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '错误信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '操作日志 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_oper_log
-- ----------------------------

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
-- Table structure for t_sys_option
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_option`;
CREATE TABLE `t_sys_option`  (
  `version` int(11) NULL DEFAULT NULL COMMENT '乐观锁',
  `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_enabled` int(11) NULL DEFAULT NULL COMMENT '是否启用',
  `del_flag` int(11) NULL DEFAULT NULL COMMENT '是否删除',
  `description` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `option_key` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'key',
  `option_value` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'value',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = 'options选项 ' ROW_FORMAT = Dynamic;


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
-- Records of t_sys_dict
-- ----------------------------
INSERT INTO `t_sys_dict` VALUES (3, -1, '2020-09-06 23:25:36', -1, '2020-09-07 07:04:35', 1, 0, '', 1302749820213030914, '用户性别', 'sys_user_gender');
INSERT INTO `t_sys_dict` VALUES (2, -1, '2020-09-06 23:29:51', -1, '2020-09-06 23:34:11', 1, 0, '', 1302750889848631298, '系统状态', 'sys_common_status');
INSERT INTO `t_sys_dict` VALUES (1, -1, '2020-09-07 04:57:10', NULL, NULL, 1, 0, '', 1302833264498651138, '任务分组', 'sys_job_group');
INSERT INTO `t_sys_dict` VALUES (1, -1, '2020-09-09 06:00:31', NULL, NULL, 1, 0, '', 1303573982095642626, '操作类型', 'sys_oper_type');
INSERT INTO `t_sys_dict` VALUES (1, -1, '2020-09-10 01:41:32', NULL, NULL, 1, 0, '', 1303871194717536257, '系统常量', 'sys_constant');

-- ----------------------------
-- Records of t_sys_dict_entry
-- ----------------------------
INSERT INTO `t_sys_dict_entry` VALUES (1, -1, '2020-09-06 23:27:02', NULL, NULL, 1, 0, NULL, 1302750183850799106, 1302749820213030914, '男', '0', 1);
INSERT INTO `t_sys_dict_entry` VALUES (1, -1, '2020-09-06 23:28:21', NULL, NULL, 1, 0, NULL, 1302750511375609857, 1302749820213030914, '女', '1', 2);
INSERT INTO `t_sys_dict_entry` VALUES (1, -1, '2020-09-06 23:29:09', NULL, NULL, 1, 0, NULL, 1302750714396700673, 1302749820213030914, '未知', '2', 3);
INSERT INTO `t_sys_dict_entry` VALUES (1, -1, '2020-09-06 23:30:13', NULL, NULL, 1, 0, NULL, 1302750984195305474, 1302750889848631298, '禁用', '0', 1);
INSERT INTO `t_sys_dict_entry` VALUES (1, -1, '2020-09-06 23:30:27', NULL, NULL, 1, 0, NULL, 1302751041317531649, 1302750889848631298, '启用', '1', 0);
INSERT INTO `t_sys_dict_entry` VALUES (1, -1, '2020-09-07 04:57:37', NULL, NULL, 1, 0, NULL, 1302833377216376833, 1302833264498651138, '默认', 'DEFAULT', 1);
INSERT INTO `t_sys_dict_entry` VALUES (1, -1, '2020-09-07 04:59:13', NULL, NULL, 1, 0, NULL, 1302833778644824065, 1302833264498651138, '系统', 'SYSTEM', 2);
INSERT INTO `t_sys_dict_entry` VALUES (1, -1, '2020-09-09 06:00:52', NULL, NULL, 1, 0, NULL, 1303574068863209474, 1303573982095642626, '新增', '1', 1);
INSERT INTO `t_sys_dict_entry` VALUES (1, -1, '2020-09-09 06:01:13', NULL, NULL, 1, 0, NULL, 1303574158638092290, 1303573982095642626, '修改', '2', 2);
INSERT INTO `t_sys_dict_entry` VALUES (2, -1, '2020-09-09 06:01:26', -1, '2020-09-09 06:01:55', 1, 0, NULL, 1303574211159166978, 1303573982095642626, '删除', '3', 3);
INSERT INTO `t_sys_dict_entry` VALUES (1, -1, '2020-09-09 06:02:25', NULL, NULL, 1, 0, NULL, 1303574459608764417, 1303573982095642626, '授权', '4', 4);
INSERT INTO `t_sys_dict_entry` VALUES (1, -1, '2020-09-09 06:02:37', NULL, NULL, 1, 0, NULL, 1303574507927146498, 1303573982095642626, '导出', '5', 5);
INSERT INTO `t_sys_dict_entry` VALUES (1, -1, '2020-09-09 06:02:46', NULL, NULL, 1, 0, NULL, 1303574547710119938, 1303573982095642626, '导入', '6', 6);
INSERT INTO `t_sys_dict_entry` VALUES (1, -1, '2020-09-09 06:02:55', NULL, NULL, 1, 0, NULL, 1303574583936323585, 1303573982095642626, '强退', '7', 7);
INSERT INTO `t_sys_dict_entry` VALUES (1, -1, '2020-09-09 06:03:10', NULL, NULL, 1, 0, NULL, 1303574647953985538, 1303573982095642626, '清空数据', '8', 8);
INSERT INTO `t_sys_dict_entry` VALUES (1, -1, '2020-09-09 06:03:20', NULL, NULL, 1, 0, NULL, 1303574689183993858, 1303573982095642626, '执行', '9', 9);
INSERT INTO `t_sys_dict_entry` VALUES (1, -1, '2020-09-09 06:03:38', NULL, NULL, 1, 0, NULL, 1303574766149472258, 1303573982095642626, '其他', '0', 999);
INSERT INTO `t_sys_dict_entry` VALUES (1, -1, '2020-09-10 01:41:56', NULL, NULL, 1, 0, NULL, 1303871294541971458, 1303871194717536257, 'default_password', '123456', 1);

-- ----------------------------
-- Records of t_sys_job
-- ----------------------------
truncate table `t_sys_job`;

INSERT INTO `t_sys_job` VALUES (19, -1, '2020-09-08 00:24:33', -1, '2020-09-08 00:42:22', 0, 0, '', 1, '测试01', 'SYSTEM', 'taskTest', 'params', '{\"java.lang.String\":\"test\"}', '0/10 * * * * ?', '超级管理员', '1278032416@qq.com');
INSERT INTO `t_sys_job` VALUES (3, -1, '2020-09-08 00:45:51', -1, '2020-09-08 00:46:07', 1, 1, '', 2, '測試02', 'SYSTEM', 'com.hb0730.boot.admin.project.system.quartz.handler.TaskTest', 'multipleParams', '{java.lang.String:test,java.lang.Boolean:true,java.lang.Long:123L,java.lang.Double:123.03D,java.lang.Integer:123}', '0/10 * * * * ?', '', '');

-- ----------------------------
-- Records of t_sys_job_log
-- ----------------------------
truncate table `t_sys_job_log`;

-- ----------------------------
-- Records of t_sys_menu
-- ----------------------------
INSERT INTO  `t_sys_menu` VALUES (4, -1, '2020-09-07 07:11:37', -1, '2022-02-19 08:45:21', 1, 0, NULL, 1, '系统管理',
                                  'systemManager', -1, '/system', '', 'fa fa-cogs', 10);
INSERT INTO  `t_sys_menu` VALUES (4, -1, '2020-09-07 07:12:51', -1, '2020-09-07 03:34:09', 1, 0, NULL, 2, '菜单管理',
                                  'menuManager', 1, '/system/menu', 'system/menu/index', 'el-icon-menu', 13);
INSERT INTO  `t_sys_menu` VALUES (4, -1, '2020-09-06 23:16:12', -1, '2020-09-07 03:34:21', 1, 0, NULL,
                                  1302747457825828866, '部门管理', 'deptManager', 1, '/system/dept', 'system/dept/index', 'fa fa-sitemap', 14);
INSERT INTO  `t_sys_menu` VALUES (6, -1, '2020-09-06 23:16:48', -1, '2020-09-07 03:34:01', 1, 0, NULL,
                                  1302747607004639233, '角色管理', 'roleManager', 1, '/system/role', 'system/role/index', 'fa fa-user-secret', 12);
INSERT INTO  `t_sys_menu` VALUES (4, -1, '2020-09-06 23:17:47', -1, '2020-09-07 03:34:31', 1, 0, NULL,
                                  1302747852438532097, '岗位管理', 'postManger', 1, '/system/post', 'system/post/index', 'el-icon-postcard', 15);
INSERT INTO  `t_sys_menu` VALUES (9, -1, '2020-09-06 23:18:23', -1, '2022-02-15 08:35:54', 1, 0, NULL,
                                  1302748004347834370, '用户管理', 'userManager', 1, '/system/user', 'system/user/index', 'fa fa-users', 11);
INSERT INTO  `t_sys_menu` VALUES (2, -1, '2020-09-06 23:18:56', -1, '2022-02-23 09:33:00', 1, 0, NULL,
                                  1302748141795176449, '字典管理', 'dictManager', 1, '/system/dict', 'system/dict/index', 'fa fa-bookmark-o', 16);
INSERT INTO  `t_sys_menu` VALUES (2, -1, '2020-09-07 03:38:20', -1, '2022-02-23 09:33:54', 1, 0, NULL,
                                  1302813425436504066, '任务调度', 'scheduleManager', 1, '/system/job', 'system/job/index', 'fa fa-tasks', 17);

-- ----------------------------
-- Records of t_sys_permission
-- ----------------------------
INSERT INTO `t_sys_permission` VALUES (1, -1, '2020-09-09 07:42:03', NULL, NULL, 1, 0, '', 1303599531669086209, '用户查询', 'user:query', 1302748004347834370, 1);
INSERT INTO `t_sys_permission` VALUES (1, -1, '2020-09-09 07:42:44', NULL, NULL, 1, 0, '', 1303599704965144578, '角色查询', 'role:query', 1302747607004639233, 1);
INSERT INTO `t_sys_permission` VALUES (1, -1, '2020-09-09 07:43:02', NULL, NULL, 1, 0, '', 1303599778839420930, '菜单查询', 'menu:query', 2, 1);
INSERT INTO `t_sys_permission` VALUES (1, -1, '2020-09-09 07:43:23', NULL, NULL, 1, 0, '', 1303599867314069506, '部门查询', 'dept:query', 1302747457825828866, 1);
INSERT INTO `t_sys_permission` VALUES (1, -1, '2020-09-09 07:43:39', NULL, NULL, 1, 0, '', 1303599936679469058, '岗位查询', 'post:query', 1302747852438532097, 1);
INSERT INTO `t_sys_permission` VALUES (1, -1, '2020-09-09 07:43:59', NULL, NULL, 1, 0, '', 1303600019739271169, '字典查询', 'dict:query', 1302748141795176449, 1);
INSERT INTO `t_sys_permission` VALUES (1, -1, '2020-09-09 07:44:15', NULL, NULL, 1, 0, '', 1303600088454553601, '任务查询', 'job:query', 1302813425436504066, 1);
INSERT INTO `t_sys_permission` VALUES (1, -1, '2020-09-09 07:44:33', NULL, NULL, 1, 0, '', 1303600161578049538, '在线用户查询', 'online:user:query', 1303215983061684225, 1);
INSERT INTO `t_sys_permission` VALUES (1, -1, '2020-09-09 07:44:53', NULL, NULL, 1, 0, '', 1303600245443158017, '操作日志查询', 'oper:log:query', 1303573496625926146, 1);
INSERT INTO `t_sys_permission` VALUES (1, -1, '2020-09-09 09:06:55', NULL, NULL, 1, 0, '', 1303620891703312386, '用户新增', 'user:save', 1302748004347834370, 2);
INSERT INTO `t_sys_permission` VALUES (1, -1, '2020-09-09 09:07:13', NULL, NULL, 1, 0, '', 1303620964906500097, '用户修改', 'user:update', 1302748004347834370, 3);
INSERT INTO `t_sys_permission` VALUES (1, -1, '2020-09-09 09:07:29', NULL, NULL, 1, 0, '', 1303621032820670466, '用户删除', 'user:delete', 1302748004347834370, 4);
INSERT INTO `t_sys_permission` VALUES (1, -1, '2020-09-09 09:14:46', NULL, NULL, 1, 0, '', 1303622865614077953, '用户密码重置', 'user:rest:password', 1302748004347834370, 5);
INSERT INTO `t_sys_permission` VALUES (1, -1, '2020-09-09 09:17:31', NULL, NULL, 1, 0, '', 1303623556374003714, '角色新增', 'role:save', 1302747607004639233, 2);
INSERT INTO `t_sys_permission` VALUES (1, -1, '2020-09-09 09:17:44', NULL, NULL, 1, 0, '', 1303623613932437506, '角色修改', 'role:update', 1302747607004639233, 3);
INSERT INTO `t_sys_permission` VALUES (1, -1, '2020-09-09 09:18:15', NULL, NULL, 1, 0, '', 1303623741074374658, '角色删除', 'role:delete', 1302747607004639233, 4);
INSERT INTO `t_sys_permission` VALUES (1, -1, '2020-09-09 09:19:42', NULL, NULL, 1, 0, '', 1303624109267156993, '角色权限分配', 'role:permission:save', 1302747607004639233, 5);
INSERT INTO `t_sys_permission` VALUES (1, -1, '2020-09-09 09:20:28', NULL, NULL, 1, 0, '', 1303624298254106626, '菜单新增', 'menu:save', 2, 2);
INSERT INTO `t_sys_permission` VALUES (1, -1, '2020-09-09 09:20:42', NULL, NULL, 1, 0, '', 1303624356861116418, '菜单修改', 'menu:update', 2, 3);
INSERT INTO `t_sys_permission` VALUES (1, -1, '2020-09-09 09:21:09', NULL, NULL, 1, 0, '', 1303624470224764929, '菜单删除', 'menu:delete', 2, 4);
INSERT INTO `t_sys_permission` VALUES (1, -1, '2020-09-09 09:22:18', NULL, NULL, 1, 0, '', 1303624760768397313, '部门新增', 'dept:save', 1302747457825828866, 2);
INSERT INTO `t_sys_permission` VALUES (1, -1, '2020-09-09 09:22:33', NULL, NULL, 1, 0, '', 1303624825390039041, '部门修改', 'dept:update', 1302747457825828866, 3);
INSERT INTO `t_sys_permission` VALUES (1, -1, '2020-09-09 09:22:59', NULL, NULL, 1, 0, '', 1303624933565333506, '部门删除', 'dept:delete', 1302747457825828866, 4);
INSERT INTO `t_sys_permission` VALUES (1, -1, '2020-09-09 09:23:50', NULL, NULL, 1, 0, '', 1303625145641926657, '岗位新增', 'post:save', 1302747852438532097, 2);
INSERT INTO `t_sys_permission` VALUES (1, -1, '2020-09-09 09:24:07', NULL, NULL, 1, 0, '', 1303625218148859905, '岗位修改', 'post:update', 1302747852438532097, 3);
INSERT INTO `t_sys_permission` VALUES (1, -1, '2020-09-09 09:24:23', NULL, NULL, 1, 0, '', 1303625286843170817, '岗位删除', 'post:delete', 1302747852438532097, 4);
INSERT INTO `t_sys_permission` VALUES (1, -1, '2020-09-09 09:25:38', NULL, NULL, 1, 0, '', 1303625601537605634, '字典新增', 'dict:save', 1302748141795176449, 2);
INSERT INTO `t_sys_permission` VALUES (1, -1, '2020-09-09 09:25:52', NULL, NULL, 1, 0, '', 1303625660408856578, '字典修改', 'dict:update', 1302748141795176449, 3);
INSERT INTO `t_sys_permission` VALUES (1, -1, '2020-09-09 09:26:07', NULL, NULL, 1, 0, '', 1303625720320294914, '字典删除', 'dict:delete', 1302748141795176449, 4);
INSERT INTO `t_sys_permission` VALUES (1, -1, '2020-09-09 09:26:28', NULL, NULL, 1, 0, '', 1303625808648142849, '任务新增', 'job:save', 1302813425436504066, 2);
INSERT INTO `t_sys_permission` VALUES (1, -1, '2020-09-09 09:26:40', NULL, NULL, 1, 0, '', 1303625861810946050, '任务修改', 'job:update', 1302813425436504066, 3);
INSERT INTO `t_sys_permission` VALUES (1, -1, '2020-09-09 09:26:55', NULL, NULL, 1, 0, '', 1303625923207168002, '任务删除', 'job:remove', 1302813425436504066, 4);
INSERT INTO `t_sys_permission` VALUES (2, -1, '2020-09-09 09:27:45', -1, '2020-09-09 09:29:40', 1, 0, '', 1303626131441778689, '在线用户强退', 'online:user:logout', 1303215983061684225, 2);
INSERT INTO `t_sys_permission` VALUES (1, -1, '2020-09-09 09:30:28', NULL, NULL, 1, 0, '', 1303626818481356802, '操作日志删除', 'oper:log:delete', 1303573496625926146, 2);
INSERT INTO `t_sys_permission` VALUES (1, -1, '2020-09-09 09:30:49', NULL, NULL, 1, 0, '', 1303626905810960386, '操作日志清除', 'oper:log:clean', 1303573496625926146, 3);
INSERT INTO `t_sys_permission` VALUES (1, -1, '2020-09-09 09:31:14', NULL, NULL, 1, 0, '', 1303627009196359681, '登录日志查询', 'login:log:query', 1303611980636012546, 1);
INSERT INTO `t_sys_permission` VALUES (1, -1, '2020-09-09 09:31:39', NULL, NULL, 1, 0, '', 1303627115731681281, '登录日志删除', 'login:log:delete', 1303611980636012546, 2);
INSERT INTO `t_sys_permission` VALUES (1, -1, '2020-09-09 09:31:56', NULL, NULL, 1, 0, '', 1303627184333717505, '登录日志清除', 'login:log:clean', 1303611980636012546, 3);
INSERT INTO `t_sys_permission` VALUES (1, -1, '2020-09-10 00:57:41', NULL, NULL, 1, 0, '', 1303860158241234945, '权限查询', 'permission:query', 2, 5);
INSERT INTO `t_sys_permission` VALUES (1, -1, '2020-09-10 00:58:17', NULL, NULL, 1, 0, '', 1303860308321820673, '权限新增', 'permission:save', 2, 6);
INSERT INTO `t_sys_permission` VALUES (1, -1, '2020-09-10 00:58:37', NULL, NULL, 1, 0, '', 1303860394313441281, '权限修改', 'permission:update', 2, 7);
INSERT INTO `t_sys_permission` VALUES (1, -1, '2020-09-10 00:58:52', NULL, NULL, 1, 0, '', 1303860457823592449, '权限删除', 'permission:delete', 2, 8);
INSERT INTO `t_sys_permission` VALUES (1, -1, '2020-09-10 01:17:45', NULL, NULL, 1, 0, '', 1303865207134658561, '字典项查询', 'dict:entry:query', 1302748141795176449, 5);
INSERT INTO `t_sys_permission` VALUES (1, -1, '2020-09-10 01:18:07', NULL, NULL, 1, 0, '', 1303865300629889026, '字典项新增', 'dict:entry:save', 1302748141795176449, 6);
INSERT INTO `t_sys_permission` VALUES (1, -1, '2020-09-10 01:18:26', NULL, NULL, 1, 0, '', 1303865381466710018, '字典项修改', 'dict:entry:update', 1302748141795176449, 7);
INSERT INTO `t_sys_permission` VALUES (1, -1, '2020-09-10 01:18:42', NULL, NULL, 1, 0, '', 1303865447745101825, '字典项删除', 'dict:entry:delete', 1302748141795176449, 8);
INSERT INTO `t_sys_permission` VALUES (1, -1, '2020-09-10 01:23:12', NULL, NULL, 1, 0, '', 1303866580093935617, '立即执行', 'job:exec', 1302813425436504066, 5);
INSERT INTO `t_sys_permission` VALUES (1, -1, '2020-09-15 05:22:04', NULL, NULL, 1, 0, '', 1305738631863914497, '邮件详情', 'mail:info', 1305737755229212674, 1);
INSERT INTO `t_sys_permission` VALUES (1, -1, '2020-09-15 05:22:23', NULL, NULL, 1, 0, '', 1305738710540668930, '邮件保存', 'mail:save', 1305737755229212674, 2);

-- ----------------------------
-- Records of t_sys_post
-- ----------------------------
INSERT INTO `t_sys_post` VALUES (1, -1, '2020-09-06 23:23:28', NULL, NULL, 1, 0, '', 1302749284118065153, '全栈开发', 'S001', 1);
INSERT INTO `t_sys_post` VALUES (1, -1, '2020-09-06 23:23:40', NULL, NULL, 1, 0, '', 1302749333476634626, '人事专员', 'S002', 2);
INSERT INTO `t_sys_post` VALUES (1, -1, '2020-09-06 23:23:58', NULL, NULL, 1, 0, '', 1302749408756002818, '产品经理', 'S003', 3);
INSERT INTO `t_sys_post` VALUES (1, -1, '2020-09-06 23:24:10', NULL, NULL, 1, 0, '', 1302749458869547010, '软件测试', 'S004', 4);

-- ----------------------------
-- Records of t_sys_role
-- ----------------------------
INSERT INTO `t_sys_role` VALUES (1, -1, '2020-09-06 23:23:00', NULL, NULL, 1, 0, NULL, 1302749167210229762, '超级管理员', 'ROLE_ADMINISTRATOR', 1);

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
-- Records of t_sys_user_account
-- ----------------------------
INSERT INTO `t_sys_user_account` VALUES (1, -1, NULL, NULL, NULL, 1, 0, NULL, 1298548255977635841, 'Administrator', '$2a$10$GX.Ecwhp2gxt9fIM9wClWuUt2kUD0YtotWvNUmHtN7ftt2DISf4mC', -1);

-- ----------------------------
-- Records of t_sys_user_info
-- ----------------------------
INSERT INTO `t_sys_user_info` VALUES (3, -1, NULL, -1, '2020-08-28 08:44:44', 1, 0, NULL, -1, '超级管理员', NULL, 1, '', '1278032416@qq.com', -1, 1);


-- ----------------------------
-- Records of t_sys_user_role
-- ----------------------------

-- ----------------------------
-- Records of t_sys_job_log
-- ----------------------------
truncate table `t_sys_option`;


SET FOREIGN_KEY_CHECKS = 1;
