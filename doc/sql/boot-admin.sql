/*
 Source Server         : boot-admin-local
 Source Server Type    : MySQL
 Source Server Version : 80031 (8.0.31)
 Source Host           : localhost:3306
 Source Schema         : boot-admin

 Target Server Type    : MySQL
 Target Server Version : 80031 (8.0.31)
 File Encoding         : 65001

 Date: 15/06/2023 09:29:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for QRTZ_BLOB_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_BLOB_TRIGGERS`;
CREATE TABLE `QRTZ_BLOB_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of QRTZ_BLOB_TRIGGERS
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for QRTZ_CALENDARS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_CALENDARS`;
CREATE TABLE `QRTZ_CALENDARS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of QRTZ_CALENDARS
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for QRTZ_CRON_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_CRON_TRIGGERS`;
CREATE TABLE `QRTZ_CRON_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(200) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of QRTZ_CRON_TRIGGERS
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for QRTZ_FIRED_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_FIRED_TRIGGERS`;
CREATE TABLE `QRTZ_FIRED_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint NOT NULL,
  `SCHED_TIME` bigint NOT NULL,
  `PRIORITY` int NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of QRTZ_FIRED_TRIGGERS
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for QRTZ_JOB_DETAILS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_JOB_DETAILS`;
CREATE TABLE `QRTZ_JOB_DETAILS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of QRTZ_JOB_DETAILS
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for QRTZ_LOCKS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_LOCKS`;
CREATE TABLE `QRTZ_LOCKS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of QRTZ_LOCKS
-- ----------------------------
BEGIN;
INSERT INTO `QRTZ_LOCKS` (`SCHED_NAME`, `LOCK_NAME`) VALUES ('MyScheduler', 'STATE_ACCESS');
INSERT INTO `QRTZ_LOCKS` (`SCHED_NAME`, `LOCK_NAME`) VALUES ('MyScheduler', 'TRIGGER_ACCESS');
INSERT INTO `QRTZ_LOCKS` (`SCHED_NAME`, `LOCK_NAME`) VALUES ('quartzScheduler', 'TRIGGER_ACCESS');
COMMIT;

-- ----------------------------
-- Table structure for QRTZ_PAUSED_TRIGGER_GRPS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_PAUSED_TRIGGER_GRPS`;
CREATE TABLE `QRTZ_PAUSED_TRIGGER_GRPS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of QRTZ_PAUSED_TRIGGER_GRPS
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for QRTZ_SCHEDULER_STATE
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SCHEDULER_STATE`;
CREATE TABLE `QRTZ_SCHEDULER_STATE` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint NOT NULL,
  `CHECKIN_INTERVAL` bigint NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of QRTZ_SCHEDULER_STATE
-- ----------------------------
BEGIN;
INSERT INTO `QRTZ_SCHEDULER_STATE` (`SCHED_NAME`, `INSTANCE_NAME`, `LAST_CHECKIN_TIME`, `CHECKIN_INTERVAL`) VALUES ('MyScheduler', 'huangbingdeMacBook-Pro.local1686792295294', 1686792566695, 10000);
COMMIT;

-- ----------------------------
-- Table structure for QRTZ_SIMPLE_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SIMPLE_TRIGGERS`;
CREATE TABLE `QRTZ_SIMPLE_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint NOT NULL,
  `REPEAT_INTERVAL` bigint NOT NULL,
  `TIMES_TRIGGERED` bigint NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of QRTZ_SIMPLE_TRIGGERS
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for QRTZ_SIMPROP_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SIMPROP_TRIGGERS`;
CREATE TABLE `QRTZ_SIMPROP_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int DEFAULT NULL,
  `INT_PROP_2` int DEFAULT NULL,
  `LONG_PROP_1` bigint DEFAULT NULL,
  `LONG_PROP_2` bigint DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of QRTZ_SIMPROP_TRIGGERS
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for QRTZ_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_TRIGGERS`;
CREATE TABLE `QRTZ_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint DEFAULT NULL,
  `PREV_FIRE_TIME` bigint DEFAULT NULL,
  `PRIORITY` int DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint NOT NULL,
  `END_TIME` bigint DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `QRTZ_JOB_DETAILS` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of QRTZ_TRIGGERS
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'id',
  `created_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '创建者',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '更新者',
  `modified` datetime DEFAULT NULL COMMENT '更新时间',
  `type` tinyint NOT NULL COMMENT '类型 1 系统配置 2 业务配置',
  `code` varchar(50) NOT NULL COMMENT '配置项',
  `value` varchar(255) NOT NULL COMMENT '配置值',
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统配置\n';

-- ----------------------------
-- Records of sys_config
-- ----------------------------
BEGIN;
INSERT INTO `sys_config` (`id`, `created_by`, `created`, `modified_by`, `modified`, `type`, `code`, `value`, `memo`) VALUES ('1669144282138763265', 'admin', '2023-06-15 08:46:23', 'admin', '2023-06-15 09:04:10', 2, 'BIZ_TEST_CACHE', '{\n  \"name\": \"qiu\",\n  \"age\": 18,\n  \"isMan\": false,\n  \"date\": \"2023-06-15T00:26:00.342Z\",\n  \"arr\": [\n    1,\n    2,\n    5\n  ],\n  \"reg\": {}\n}', NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_org
-- ----------------------------
DROP TABLE IF EXISTS `sys_org`;
CREATE TABLE `sys_org` (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'id',
  `created_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '创建者',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '更新者',
  `modified` datetime DEFAULT NULL COMMENT '更新时间',
  `parent_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '父ID',
  `path` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'PATH',
  `code` varchar(32) NOT NULL COMMENT 'CODE',
  `name` varchar(32) NOT NULL COMMENT '机构名称',
  `link_man` varchar(20) DEFAULT NULL COMMENT '联系人',
  `link_tel` varchar(15) DEFAULT NULL COMMENT '联系电话',
  `province_id` varchar(32) DEFAULT NULL COMMENT '省份ID',
  `province` varchar(32) DEFAULT NULL COMMENT '省名称',
  `city_id` varchar(32) DEFAULT NULL COMMENT '城市ID',
  `city` varchar(32) DEFAULT NULL COMMENT '城市名称',
  `county_id` varchar(32) DEFAULT NULL COMMENT '区县ID',
  `county` varchar(32) DEFAULT NULL COMMENT '区县名称',
  `address` varchar(255) DEFAULT NULL COMMENT '详细地址',
  `longitude` varchar(255) DEFAULT NULL COMMENT '经纬度，经度,纬度',
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  `is_system` tinyint DEFAULT '0' COMMENT '是否内置',
  `is_enable` tinyint DEFAULT '1' COMMENT '是否启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='网点机构';

-- ----------------------------
-- Records of sys_org
-- ----------------------------
BEGIN;
INSERT INTO `sys_org` (`id`, `created_by`, `created`, `modified_by`, `modified`, `parent_id`, `path`, `code`, `name`, `link_man`, `link_tel`, `province_id`, `province`, `city_id`, `city`, `county_id`, `county`, `address`, `longitude`, `memo`, `is_system`, `is_enable`) VALUES ('53560448b5870af7c440d9bb92712293', 'admin', '2023-06-05 08:35:24', NULL, NULL, NULL, '53560448b5870af7c440d9bb92712293', '129053495681099', 'BOOT-ADMIN总部', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1);
INSERT INTO `sys_org` (`id`, `created_by`, `created`, `modified_by`, `modified`, `parent_id`, `path`, `code`, `name`, `link_man`, `link_tel`, `province_id`, `province`, `city_id`, `city`, `county_id`, `county`, `address`, `longitude`, `memo`, `is_system`, `is_enable`) VALUES ('53a371dc8b8b4444848c371526cb55f0', 'admin', '2023-06-07 10:57:34', 'admin', '2023-06-07 11:06:58', '53560448b5870af7c440d9bb92712293', '53560448b5870af7c440d9bb92712293,53a371dc8b8b4444848c371526cb55f0', '1666278193130262528', '一级机构', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'id',
  `created_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '创建者',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '更新者',
  `modified` datetime DEFAULT NULL COMMENT '更新时间',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '父ID',
  `path` varchar(100) DEFAULT NULL COMMENT '路由地址',
  `name` varchar(50) DEFAULT NULL COMMENT '路由名字',
  `title` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `icon` varchar(30) DEFAULT NULL COMMENT '菜单icon',
  `show_link` int DEFAULT NULL COMMENT '是否显示',
  `rank` int DEFAULT NULL COMMENT '排序',
  `component` varchar(255) DEFAULT NULL COMMENT '组件',
  `show_parent` int DEFAULT NULL COMMENT '是否显示父菜单',
  `keep_alive` int DEFAULT NULL COMMENT '是否缓存该路由页面',
  `is_frame` int DEFAULT NULL COMMENT '是否iframe',
  `menu_type` int DEFAULT NULL COMMENT '类型',
  `perms` varchar(255) DEFAULT NULL COMMENT '菜单权限编码',
  `is_enable` int DEFAULT '1' COMMENT '是否启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='菜单与权限\n';

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
BEGIN;
INSERT INTO `sys_permission` (`id`, `created_by`, `created`, `modified_by`, `modified`, `parent_id`, `path`, `name`, `title`, `icon`, `show_link`, `rank`, `component`, `show_parent`, `keep_alive`, `is_frame`, `menu_type`, `perms`, `is_enable`) VALUES ('1302747457825828866', 'admin', '2023-02-25 01:48:27', 'admin', '2023-02-26 22:30:25', NULL, '/system', NULL, '系统管理', 'fa:cogs', 1, 1, NULL, 0, 1, 0, 0, NULL, 1);
INSERT INTO `sys_permission` (`id`, `created_by`, `created`, `modified_by`, `modified`, `parent_id`, `path`, `name`, `title`, `icon`, `show_link`, `rank`, `component`, `show_parent`, `keep_alive`, `is_frame`, `menu_type`, `perms`, `is_enable`) VALUES ('1302747607004639233', 'admin', '2023-02-25 01:51:45', 'admin', '2023-02-27 22:07:54', '1302747457825828866', '/sys/menu', 'sysMenu', '菜单管理', 'ep:menu', 1, 3, 'system/menu', 1, 1, 0, 1, NULL, 1);
INSERT INTO `sys_permission` (`id`, `created_by`, `created`, `modified_by`, `modified`, `parent_id`, `path`, `name`, `title`, `icon`, `show_link`, `rank`, `component`, `show_parent`, `keep_alive`, `is_frame`, `menu_type`, `perms`, `is_enable`) VALUES ('1629850171439321089', 'admin', '2023-02-26 22:25:37', NULL, NULL, NULL, '/sys/monitor', NULL, '系统监控', 'ep:monitor', 1, 2, NULL, 1, 0, 0, 0, NULL, 1);
INSERT INTO `sys_permission` (`id`, `created_by`, `created`, `modified_by`, `modified`, `parent_id`, `path`, `name`, `title`, `icon`, `show_link`, `rank`, `component`, `show_parent`, `keep_alive`, `is_frame`, `menu_type`, `perms`, `is_enable`) VALUES ('1629858178575396866', 'admin', '2023-02-26 22:57:26', NULL, NULL, '1302747607004639233', NULL, NULL, '菜单查询', NULL, 1, 1, NULL, 1, 0, 0, 2, 'menu:query', 1);
INSERT INTO `sys_permission` (`id`, `created_by`, `created`, `modified_by`, `modified`, `parent_id`, `path`, `name`, `title`, `icon`, `show_link`, `rank`, `component`, `show_parent`, `keep_alive`, `is_frame`, `menu_type`, `perms`, `is_enable`) VALUES ('1629858282623496193', 'admin', '2023-02-26 22:57:51', 'admin', '2023-02-26 22:57:58', '1302747607004639233', NULL, NULL, '菜单新增', NULL, 1, 2, NULL, 1, 0, 0, 2, 'menu:save', 1);
INSERT INTO `sys_permission` (`id`, `created_by`, `created`, `modified_by`, `modified`, `parent_id`, `path`, `name`, `title`, `icon`, `show_link`, `rank`, `component`, `show_parent`, `keep_alive`, `is_frame`, `menu_type`, `perms`, `is_enable`) VALUES ('1629858627718246401', 'admin', '2023-02-26 22:59:13', 'admin', '2023-02-26 23:00:07', '1302747607004639233', NULL, NULL, '菜单删除', NULL, 1, 4, NULL, 1, 0, 0, 2, 'menu:delete', 1);
INSERT INTO `sys_permission` (`id`, `created_by`, `created`, `modified_by`, `modified`, `parent_id`, `path`, `name`, `title`, `icon`, `show_link`, `rank`, `component`, `show_parent`, `keep_alive`, `is_frame`, `menu_type`, `perms`, `is_enable`) VALUES ('1629858959483498497', 'admin', '2023-02-26 23:00:32', NULL, NULL, '1302747607004639233', NULL, NULL, '菜单修改', NULL, 1, 3, NULL, 1, 0, 0, 2, 'menu:update', 1);
INSERT INTO `sys_permission` (`id`, `created_by`, `created`, `modified_by`, `modified`, `parent_id`, `path`, `name`, `title`, `icon`, `show_link`, `rank`, `component`, `show_parent`, `keep_alive`, `is_frame`, `menu_type`, `perms`, `is_enable`) VALUES ('1630202153043824641', 'admin', '2023-02-27 21:44:16', 'admin', '2023-06-08 08:25:25', '1302747457825828866', '/sys/role', 'sysRole', '角色管理', 'fa-solid:user-secret', 1, 4, 'system/role', 1, 1, 0, 1, NULL, 1);
INSERT INTO `sys_permission` (`id`, `created_by`, `created`, `modified_by`, `modified`, `parent_id`, `path`, `name`, `title`, `icon`, `show_link`, `rank`, `component`, `show_parent`, `keep_alive`, `is_frame`, `menu_type`, `perms`, `is_enable`) VALUES ('1630555946000392194', 'admin', '2023-02-28 21:10:06', NULL, NULL, '1630202153043824641', NULL, NULL, '角色查询', NULL, 1, 1, NULL, 1, 0, 0, 2, 'role:query', 1);
INSERT INTO `sys_permission` (`id`, `created_by`, `created`, `modified_by`, `modified`, `parent_id`, `path`, `name`, `title`, `icon`, `show_link`, `rank`, `component`, `show_parent`, `keep_alive`, `is_frame`, `menu_type`, `perms`, `is_enable`) VALUES ('1630556002766102530', 'admin', '2023-02-28 21:10:20', NULL, NULL, '1630202153043824641', NULL, NULL, '角色新增', NULL, 1, 2, NULL, 1, 0, 0, 2, 'role:save', 1);
INSERT INTO `sys_permission` (`id`, `created_by`, `created`, `modified_by`, `modified`, `parent_id`, `path`, `name`, `title`, `icon`, `show_link`, `rank`, `component`, `show_parent`, `keep_alive`, `is_frame`, `menu_type`, `perms`, `is_enable`) VALUES ('1630556090280255489', 'admin', '2023-02-28 21:10:41', NULL, NULL, '1630202153043824641', NULL, NULL, '角色更新', NULL, 1, 3, NULL, 1, 0, 0, 2, 'role:update', 1);
INSERT INTO `sys_permission` (`id`, `created_by`, `created`, `modified_by`, `modified`, `parent_id`, `path`, `name`, `title`, `icon`, `show_link`, `rank`, `component`, `show_parent`, `keep_alive`, `is_frame`, `menu_type`, `perms`, `is_enable`) VALUES ('1631293373472088065', 'admin', '2023-03-02 22:00:23', 'admin', '2023-03-02 22:11:08', '1302747457825828866', 'sys/org', 'org', '网点管理', 'fa-solid:code-branch', 1, 1, 'system/org', 1, 1, 0, 1, NULL, 1);
INSERT INTO `sys_permission` (`id`, `created_by`, `created`, `modified_by`, `modified`, `parent_id`, `path`, `name`, `title`, `icon`, `show_link`, `rank`, `component`, `show_parent`, `keep_alive`, `is_frame`, `menu_type`, `perms`, `is_enable`) VALUES ('1631293546340327426', 'admin', '2023-03-02 22:01:04', 'admin', '2023-06-05 08:51:12', '1631293373472088065', NULL, NULL, '网点新增', NULL, 1, 2, NULL, 1, 0, 0, 2, 'org:save', 1);
INSERT INTO `sys_permission` (`id`, `created_by`, `created`, `modified_by`, `modified`, `parent_id`, `path`, `name`, `title`, `icon`, `show_link`, `rank`, `component`, `show_parent`, `keep_alive`, `is_frame`, `menu_type`, `perms`, `is_enable`) VALUES ('1631293612983623682', 'admin', '2023-03-02 22:01:20', 'admin', '2023-06-05 08:51:00', '1631293373472088065', NULL, NULL, '网点查询', NULL, 1, 1, NULL, 1, 0, 0, 2, 'org:query', 1);
INSERT INTO `sys_permission` (`id`, `created_by`, `created`, `modified_by`, `modified`, `parent_id`, `path`, `name`, `title`, `icon`, `show_link`, `rank`, `component`, `show_parent`, `keep_alive`, `is_frame`, `menu_type`, `perms`, `is_enable`) VALUES ('1631293680184762370', 'admin', '2023-03-02 22:01:36', 'admin', '2023-06-05 08:51:23', '1631293373472088065', NULL, NULL, '网点修改', NULL, 1, 3, NULL, 1, 0, 0, 2, 'org:update', 1);
INSERT INTO `sys_permission` (`id`, `created_by`, `created`, `modified_by`, `modified`, `parent_id`, `path`, `name`, `title`, `icon`, `show_link`, `rank`, `component`, `show_parent`, `keep_alive`, `is_frame`, `menu_type`, `perms`, `is_enable`) VALUES ('1665521766368813058', 'admin', '2023-06-05 08:51:48', NULL, NULL, '1631293373472088065', NULL, NULL, '网点树形查询', NULL, 1, 4, NULL, 1, 0, 0, 2, 'org:query:tree', 1);
INSERT INTO `sys_permission` (`id`, `created_by`, `created`, `modified_by`, `modified`, `parent_id`, `path`, `name`, `title`, `icon`, `show_link`, `rank`, `component`, `show_parent`, `keep_alive`, `is_frame`, `menu_type`, `perms`, `is_enable`) VALUES ('1666279770513022978', 'admin', '2023-06-07 11:03:50', NULL, NULL, '1631293373472088065', NULL, NULL, '网点删除', NULL, 1, 5, NULL, 1, 0, 0, 2, 'org:del', 1);
INSERT INTO `sys_permission` (`id`, `created_by`, `created`, `modified_by`, `modified`, `parent_id`, `path`, `name`, `title`, `icon`, `show_link`, `rank`, `component`, `show_parent`, `keep_alive`, `is_frame`, `menu_type`, `perms`, `is_enable`) VALUES ('1666602627835207681', 'admin', '2023-06-08 08:26:45', 'admin', '2023-06-08 08:28:24', '1302747457825828866', '/sys/user', 'user', '用户管理', 'ep:user', 1, 2, '/system/user', 1, 0, 0, 1, NULL, 1);
INSERT INTO `sys_permission` (`id`, `created_by`, `created`, `modified_by`, `modified`, `parent_id`, `path`, `name`, `title`, `icon`, `show_link`, `rank`, `component`, `show_parent`, `keep_alive`, `is_frame`, `menu_type`, `perms`, `is_enable`) VALUES ('1666602730528546817', 'admin', '2023-06-08 08:27:10', NULL, NULL, '1666602627835207681', NULL, NULL, '用户分页查询', NULL, 1, 1, NULL, 1, 0, 0, 2, 'user:query:page', 1);
INSERT INTO `sys_permission` (`id`, `created_by`, `created`, `modified_by`, `modified`, `parent_id`, `path`, `name`, `title`, `icon`, `show_link`, `rank`, `component`, `show_parent`, `keep_alive`, `is_frame`, `menu_type`, `perms`, `is_enable`) VALUES ('1666602801617805313', 'admin', '2023-06-08 08:27:27', NULL, NULL, '1666602627835207681', NULL, NULL, '用户列表查询', NULL, 1, 2, NULL, 1, 0, 0, 2, 'user:query:list', 1);
INSERT INTO `sys_permission` (`id`, `created_by`, `created`, `modified_by`, `modified`, `parent_id`, `path`, `name`, `title`, `icon`, `show_link`, `rank`, `component`, `show_parent`, `keep_alive`, `is_frame`, `menu_type`, `perms`, `is_enable`) VALUES ('1666602852381466626', 'admin', '2023-06-08 08:27:39', NULL, NULL, '1666602627835207681', NULL, NULL, '用户新增', NULL, 1, 3, NULL, 1, 0, 0, 2, 'user:save', 1);
INSERT INTO `sys_permission` (`id`, `created_by`, `created`, `modified_by`, `modified`, `parent_id`, `path`, `name`, `title`, `icon`, `show_link`, `rank`, `component`, `show_parent`, `keep_alive`, `is_frame`, `menu_type`, `perms`, `is_enable`) VALUES ('1666602927375622146', 'admin', '2023-06-08 08:27:57', NULL, NULL, '1666602627835207681', NULL, NULL, '用户更新', NULL, 1, 4, NULL, 1, 0, 0, 2, 'user:update', 1);
INSERT INTO `sys_permission` (`id`, `created_by`, `created`, `modified_by`, `modified`, `parent_id`, `path`, `name`, `title`, `icon`, `show_link`, `rank`, `component`, `show_parent`, `keep_alive`, `is_frame`, `menu_type`, `perms`, `is_enable`) VALUES ('1666602980362264578', 'admin', '2023-06-08 08:28:09', NULL, NULL, '1666602627835207681', NULL, NULL, '用户删除', NULL, 1, 5, NULL, 1, 0, 0, 2, 'user:del', 1);
INSERT INTO `sys_permission` (`id`, `created_by`, `created`, `modified_by`, `modified`, `parent_id`, `path`, `name`, `title`, `icon`, `show_link`, `rank`, `component`, `show_parent`, `keep_alive`, `is_frame`, `menu_type`, `perms`, `is_enable`) VALUES ('1667737247392321538', 'admin', '2023-06-11 11:35:19', NULL, NULL, '1666602627835207681', NULL, NULL, '重置密码', NULL, 1, 6, NULL, 1, 0, 0, 2, 'user:reset:passwd', 1);
INSERT INTO `sys_permission` (`id`, `created_by`, `created`, `modified_by`, `modified`, `parent_id`, `path`, `name`, `title`, `icon`, `show_link`, `rank`, `component`, `show_parent`, `keep_alive`, `is_frame`, `menu_type`, `perms`, `is_enable`) VALUES ('1668422240365445122', 'admin', '2023-06-13 08:57:15', 'admin', '2023-06-13 09:02:22', '1302747457825828866', 'sys/config', 'config', '系统配置', 'fa-solid:tools', 1, 5, 'system/config', 1, 1, 0, 1, NULL, 1);
INSERT INTO `sys_permission` (`id`, `created_by`, `created`, `modified_by`, `modified`, `parent_id`, `path`, `name`, `title`, `icon`, `show_link`, `rank`, `component`, `show_parent`, `keep_alive`, `is_frame`, `menu_type`, `perms`, `is_enable`) VALUES ('1668423423549288449', 'admin', '2023-06-13 09:01:57', NULL, NULL, '1668422240365445122', NULL, NULL, '查询', NULL, 1, 1, NULL, 1, 0, 0, 2, 'sys:config:query', 1);
INSERT INTO `sys_permission` (`id`, `created_by`, `created`, `modified_by`, `modified`, `parent_id`, `path`, `name`, `title`, `icon`, `show_link`, `rank`, `component`, `show_parent`, `keep_alive`, `is_frame`, `menu_type`, `perms`, `is_enable`) VALUES ('1668423497108992002', 'admin', '2023-06-13 09:02:14', NULL, NULL, '1668422240365445122', NULL, NULL, '保存', NULL, 1, 2, NULL, 1, 0, 0, 2, 'sys:config:save', 1);
INSERT INTO `sys_permission` (`id`, `created_by`, `created`, `modified_by`, `modified`, `parent_id`, `path`, `name`, `title`, `icon`, `show_link`, `rank`, `component`, `show_parent`, `keep_alive`, `is_frame`, `menu_type`, `perms`, `is_enable`) VALUES ('1668423598976053249', 'admin', '2023-06-13 09:02:38', NULL, NULL, '1668422240365445122', NULL, NULL, '修改', NULL, 1, 3, NULL, 1, 0, 0, 2, 'sys:config:update', 1);
INSERT INTO `sys_permission` (`id`, `created_by`, `created`, `modified_by`, `modified`, `parent_id`, `path`, `name`, `title`, `icon`, `show_link`, `rank`, `component`, `show_parent`, `keep_alive`, `is_frame`, `menu_type`, `perms`, `is_enable`) VALUES ('1668423658757468162', 'admin', '2023-06-13 09:02:53', NULL, NULL, '1668422240365445122', NULL, NULL, '删除', NULL, 1, 4, NULL, 1, 0, 0, 2, 'sys:config:del', 1);
INSERT INTO `sys_permission` (`id`, `created_by`, `created`, `modified_by`, `modified`, `parent_id`, `path`, `name`, `title`, `icon`, `show_link`, `rank`, `component`, `show_parent`, `keep_alive`, `is_frame`, `menu_type`, `perms`, `is_enable`) VALUES ('1668441104545554434', 'admin', '2023-06-13 10:12:12', NULL, NULL, '1668422240365445122', NULL, NULL, '刷新缓存', NULL, 1, 5, NULL, 1, 0, 0, 2, 'sys:config:refresh', 1);
INSERT INTO `sys_permission` (`id`, `created_by`, `created`, `modified_by`, `modified`, `parent_id`, `path`, `name`, `title`, `icon`, `show_link`, `rank`, `component`, `show_parent`, `keep_alive`, `is_frame`, `menu_type`, `perms`, `is_enable`) VALUES ('1668455675150819330', 'admin', '2023-06-13 11:10:06', NULL, NULL, '1629850171439321089', 'monitor/quartz', 'quartz', '定时任务', 'ep:clock', 1, 1, 'monitor/quartz', 1, 1, 0, 1, NULL, 1);
INSERT INTO `sys_permission` (`id`, `created_by`, `created`, `modified_by`, `modified`, `parent_id`, `path`, `name`, `title`, `icon`, `show_link`, `rank`, `component`, `show_parent`, `keep_alive`, `is_frame`, `menu_type`, `perms`, `is_enable`) VALUES ('1668455765403852802', 'admin', '2023-06-13 11:10:28', NULL, NULL, '1668455675150819330', NULL, NULL, '分页查询', NULL, 1, 1, NULL, 1, 0, 0, 2, 'sys:quartz:job:query', 1);
INSERT INTO `sys_permission` (`id`, `created_by`, `created`, `modified_by`, `modified`, `parent_id`, `path`, `name`, `title`, `icon`, `show_link`, `rank`, `component`, `show_parent`, `keep_alive`, `is_frame`, `menu_type`, `perms`, `is_enable`) VALUES ('1668455822781931522', 'admin', '2023-06-13 11:10:41', NULL, NULL, '1668455675150819330', NULL, NULL, '保存', NULL, 1, 2, NULL, 1, 0, 0, 2, 'sys:quartz:job:save', 1);
INSERT INTO `sys_permission` (`id`, `created_by`, `created`, `modified_by`, `modified`, `parent_id`, `path`, `name`, `title`, `icon`, `show_link`, `rank`, `component`, `show_parent`, `keep_alive`, `is_frame`, `menu_type`, `perms`, `is_enable`) VALUES ('1668455888460537858', 'admin', '2023-06-13 11:10:57', NULL, NULL, '1668455675150819330', NULL, NULL, '更新', NULL, 1, 3, NULL, 1, 0, 0, 2, 'sys:quartz:job:update', 1);
INSERT INTO `sys_permission` (`id`, `created_by`, `created`, `modified_by`, `modified`, `parent_id`, `path`, `name`, `title`, `icon`, `show_link`, `rank`, `component`, `show_parent`, `keep_alive`, `is_frame`, `menu_type`, `perms`, `is_enable`) VALUES ('1668455945255608321', 'admin', '2023-06-13 11:11:10', NULL, NULL, '1668455675150819330', NULL, NULL, '暂停', NULL, 1, 4, NULL, 1, 0, 0, 2, 'sys:quartz:job:pause', 1);
INSERT INTO `sys_permission` (`id`, `created_by`, `created`, `modified_by`, `modified`, `parent_id`, `path`, `name`, `title`, `icon`, `show_link`, `rank`, `component`, `show_parent`, `keep_alive`, `is_frame`, `menu_type`, `perms`, `is_enable`) VALUES ('1668456007738155010', 'admin', '2023-06-13 11:11:25', NULL, NULL, '1668455675150819330', NULL, NULL, '恢复', NULL, 1, 5, NULL, 1, 0, 0, 2, 'sys:quartz:job:resume', 1);
INSERT INTO `sys_permission` (`id`, `created_by`, `created`, `modified_by`, `modified`, `parent_id`, `path`, `name`, `title`, `icon`, `show_link`, `rank`, `component`, `show_parent`, `keep_alive`, `is_frame`, `menu_type`, `perms`, `is_enable`) VALUES ('1668456068949827585', 'admin', '2023-06-13 11:11:40', NULL, NULL, '1668455675150819330', NULL, NULL, '执行', NULL, 1, 6, NULL, 1, 0, 0, 2, 'sys:quartz:job:execute', 1);
INSERT INTO `sys_permission` (`id`, `created_by`, `created`, `modified_by`, `modified`, `parent_id`, `path`, `name`, `title`, `icon`, `show_link`, `rank`, `component`, `show_parent`, `keep_alive`, `is_frame`, `menu_type`, `perms`, `is_enable`) VALUES ('1668456129616240641', 'admin', '2023-06-13 11:11:54', NULL, NULL, '1668455675150819330', NULL, NULL, '删除', NULL, 1, 7, NULL, 1, 0, 0, 2, 'sys:quartz:job:delete', 1);
INSERT INTO `sys_permission` (`id`, `created_by`, `created`, `modified_by`, `modified`, `parent_id`, `path`, `name`, `title`, `icon`, `show_link`, `rank`, `component`, `show_parent`, `keep_alive`, `is_frame`, `menu_type`, `perms`, `is_enable`) VALUES ('1668788439079194625', 'admin', '2023-06-14 09:12:23', NULL, NULL, '1629850171439321089', 'monitor/cache', 'Cache', '系统缓存', 'fa-solid:registered', 1, 2, 'monitor/cache', 1, 1, 0, 1, NULL, 1);
INSERT INTO `sys_permission` (`id`, `created_by`, `created`, `modified_by`, `modified`, `parent_id`, `path`, `name`, `title`, `icon`, `show_link`, `rank`, `component`, `show_parent`, `keep_alive`, `is_frame`, `menu_type`, `perms`, `is_enable`) VALUES ('1668788526173917185', 'admin', '2023-06-14 09:12:44', NULL, NULL, '1668788439079194625', NULL, NULL, '缓存列表', NULL, 1, 1, NULL, 1, 0, 0, 2, 'cache:list', 1);
INSERT INTO `sys_permission` (`id`, `created_by`, `created`, `modified_by`, `modified`, `parent_id`, `path`, `name`, `title`, `icon`, `show_link`, `rank`, `component`, `show_parent`, `keep_alive`, `is_frame`, `menu_type`, `perms`, `is_enable`) VALUES ('1668788588144758786', 'admin', '2023-06-14 09:12:59', NULL, NULL, '1668788439079194625', NULL, NULL, '查询缓存', NULL, 1, 2, NULL, 1, 0, 0, 2, 'cache:query', 1);
INSERT INTO `sys_permission` (`id`, `created_by`, `created`, `modified_by`, `modified`, `parent_id`, `path`, `name`, `title`, `icon`, `show_link`, `rank`, `component`, `show_parent`, `keep_alive`, `is_frame`, `menu_type`, `perms`, `is_enable`) VALUES ('1668788650828632066', 'admin', '2023-06-14 09:13:14', NULL, NULL, '1668788439079194625', NULL, NULL, '删除缓存', NULL, 1, 3, NULL, 1, 0, 0, 2, 'cache:remove', 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_quartz_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_quartz_job`;
CREATE TABLE `sys_quartz_job` (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'id',
  `created_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '创建者',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '更新者',
  `modified` datetime DEFAULT NULL COMMENT '更新时间',
  `job_class_name` varchar(255) NOT NULL COMMENT '任务类名',
  `parameter` varchar(255) DEFAULT NULL COMMENT '参数',
  `cron_expression` varchar(32) DEFAULT NULL COMMENT 'cron表达式',
  `status` tinyint NOT NULL DEFAULT '0' COMMENT '状态 1正常 0停止',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='定时任务在线管理';

-- ----------------------------
-- Records of sys_quartz_job
-- ----------------------------
BEGIN;
INSERT INTO `sys_quartz_job` (`id`, `created_by`, `created`, `modified_by`, `modified`, `job_class_name`, `parameter`, `cron_expression`, `status`, `description`) VALUES ('1668497995908870146', 'admin', '2023-06-13 13:58:16', NULL, NULL, 'com.hb0730.boot.admin.job', NULL, '* 5 * * * ? *', 0, '示例不带参定时任务');
INSERT INTO `sys_quartz_job` (`id`, `created_by`, `created`, `modified_by`, `modified`, `job_class_name`, `parameter`, `cron_expression`, `status`, `description`) VALUES ('1668498250847055873', 'admin', '2023-06-13 13:59:17', NULL, NULL, 'com.hb0730.boot.admin.job.SampleParamJob', 'test', '* 5 * * * ? *', 0, '带参数的JOB');
INSERT INTO `sys_quartz_job` (`id`, `created_by`, `created`, `modified_by`, `modified`, `job_class_name`, `parameter`, `cron_expression`, `status`, `description`) VALUES ('1668498450697252865', 'admin', '2023-06-13 14:00:04', 'admin', '2023-06-13 14:00:17', 'com.hb0730.boot.admin.job.SampleInjectBeanJob', 'cccc', '* 5 * * * ? *', 0, '依赖注入JOB');
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'id',
  `created_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '创建者',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '更新者',
  `modified` datetime DEFAULT NULL COMMENT '更新时间',
  `role_name` varchar(30) NOT NULL COMMENT '角色名称',
  `role_code` varchar(20) NOT NULL COMMENT '角色编码',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `is_enable` int DEFAULT '1' COMMENT '启用状态',
  `is_system` int NOT NULL DEFAULT '0' COMMENT '总部标识：1是，0：否',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色信息\n';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` (`id`, `created_by`, `created`, `modified_by`, `modified`, `role_name`, `role_code`, `description`, `is_enable`, `is_system`) VALUES ('1630578910208987138', 'admin', '2023-02-28 22:41:22', NULL, NULL, '管理员', 'admin', '', 1, 1);
INSERT INTO `sys_role` (`id`, `created_by`, `created`, `modified_by`, `modified`, `role_name`, `role_code`, `description`, `is_enable`, `is_system`) VALUES ('1630929286452060162', 'admin', '2023-03-01 21:53:38', NULL, NULL, '测试', 'test', '', 1, 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'id',
  `role_id` varchar(32) NOT NULL COMMENT '角色id',
  `permission_id` varchar(32) NOT NULL COMMENT '权限id',
  `data_rule_ids` varchar(255) DEFAULT NULL COMMENT '数据权限',
  `created` datetime DEFAULT NULL COMMENT '操作时间',
  `created_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '操作ip',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色权限表\n';

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_permission` (`id`, `role_id`, `permission_id`, `data_rule_ids`, `created`, `created_by`) VALUES ('1667767686916071425', '1630929286452060162', '1629858178575396866', NULL, '2023-06-11 13:36:17', 'admin');
INSERT INTO `sys_role_permission` (`id`, `role_id`, `permission_id`, `data_rule_ids`, `created`, `created_by`) VALUES ('1667767686924460034', '1630929286452060162', '1630202153043824641', NULL, '2023-06-11 13:36:17', 'admin');
INSERT INTO `sys_role_permission` (`id`, `role_id`, `permission_id`, `data_rule_ids`, `created`, `created_by`) VALUES ('1667767686924460035', '1630929286452060162', '1630556090280255489', NULL, '2023-06-11 13:36:17', 'admin');
INSERT INTO `sys_role_permission` (`id`, `role_id`, `permission_id`, `data_rule_ids`, `created`, `created_by`) VALUES ('1667767686928654338', '1630929286452060162', '1629858627718246401', NULL, '2023-06-11 13:36:17', 'admin');
INSERT INTO `sys_role_permission` (`id`, `role_id`, `permission_id`, `data_rule_ids`, `created`, `created_by`) VALUES ('1667767686928654339', '1630929286452060162', '1629858282623496193', NULL, '2023-06-11 13:36:17', 'admin');
INSERT INTO `sys_role_permission` (`id`, `role_id`, `permission_id`, `data_rule_ids`, `created`, `created_by`) VALUES ('1667767686928654340', '1630929286452060162', '1630556002766102530', NULL, '2023-06-11 13:36:17', 'admin');
INSERT INTO `sys_role_permission` (`id`, `role_id`, `permission_id`, `data_rule_ids`, `created`, `created_by`) VALUES ('1667767686932848642', '1630929286452060162', '1302747457825828866', NULL, '2023-06-11 13:36:17', 'admin');
INSERT INTO `sys_role_permission` (`id`, `role_id`, `permission_id`, `data_rule_ids`, `created`, `created_by`) VALUES ('1667767686932848643', '1630929286452060162', '1630555946000392194', NULL, '2023-06-11 13:36:17', 'admin');
INSERT INTO `sys_role_permission` (`id`, `role_id`, `permission_id`, `data_rule_ids`, `created`, `created_by`) VALUES ('1667767686932848644', '1630929286452060162', '1629858959483498497', NULL, '2023-06-11 13:36:17', 'admin');
INSERT INTO `sys_role_permission` (`id`, `role_id`, `permission_id`, `data_rule_ids`, `created`, `created_by`) VALUES ('1667767686932848645', '1630929286452060162', '1302747607004639233', NULL, '2023-06-11 13:36:17', 'admin');
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `created_by` varchar(32) DEFAULT NULL COMMENT '创建者',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_by` varchar(32) DEFAULT NULL COMMENT '更新者',
  `modified` datetime DEFAULT NULL COMMENT '更新时间',
  `username` varchar(32) NOT NULL COMMENT '帐号',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `nickname` varchar(30) DEFAULT NULL COMMENT '昵称',
  `phone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '手机号',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `is_enabled` tinyint NOT NULL DEFAULT '1' COMMENT '是否启用',
  `org_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '所属机构',
  `is_manage` int DEFAULT '0' COMMENT '是否网点管理员',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表\n';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` (`id`, `created_by`, `created`, `modified_by`, `modified`, `username`, `password`, `nickname`, `phone`, `email`, `is_enabled`, `org_id`, `is_manage`) VALUES ('1298548255977635841', 'admin', '2023-02-25 01:46:48', NULL, NULL, 'admin', '$2a$10$GX.Ecwhp2gxt9fIM9wClWuUt2kUD0YtotWvNUmHtN7ftt2DISf4mC', '管理员', NULL, NULL, 1, '53560448b5870af7c440d9bb92712293', 1);
INSERT INTO `sys_user` (`id`, `created_by`, `created`, `modified_by`, `modified`, `username`, `password`, `nickname`, `phone`, `email`, `is_enabled`, `org_id`, `is_manage`) VALUES ('1667706007553388545', 'admin', '2023-06-11 09:31:11', 'admin', '2023-06-11 12:45:57', 'test', '$2a$10$W2dc.NZwQGQb8VOp5qZgJO9h4w0zVjmCSbh8rAilt4KB4QgzPdvj6', '测试', NULL, NULL, 1, '53560448b5870af7c440d9bb92712293', 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` varchar(32) NOT NULL,
  `user_id` varchar(32) NOT NULL COMMENT '用户id',
  `role_id` varchar(32) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户角色表\n';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`) VALUES ('f5c9e778ba04602e164ea1510dda9bbd', '1667706007553388545', '1630929286452060162');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
