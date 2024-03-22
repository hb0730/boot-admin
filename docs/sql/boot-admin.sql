/*
 Navicat Premium Data Transfer

 Source Server         : boot-admin-local
 Source Server Type    : MySQL
 Source Server Version : 80300 (8.3.0)
 Source Host           : localhost:3306
 Source Schema         : boot-admin

 Target Server Type    : MySQL
 Target Server Version : 80300 (8.3.0)
 File Encoding         : 65001

 Date: 22/03/2024 22:12:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` int NOT NULL AUTO_INCREMENT,
  `parent_id` int DEFAULT NULL,
  `code` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门编码',
  `name` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门名称',
  `type` tinyint DEFAULT NULL COMMENT '部门类型',
  `principal` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '部门联系人',
  `phone` varchar(12) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '联系电话',
  `email` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '联系邮箱',
  `sort` int DEFAULT '99' COMMENT '排序',
  `description` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '描述',
  `is_enabled` tinyint(1) DEFAULT NULL COMMENT '是否启用',
  `created_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建者',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新者',
  `modified` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='系统：部门';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
BEGIN;
INSERT INTO `sys_dept` (`id`, `parent_id`, `code`, `name`, `type`, `principal`, `phone`, `email`, `sort`, `description`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (4, NULL, 'T001', '集团公司', 1, '', '', '', 1, '', 1, 'admin', '2024-03-18 10:53:50', NULL, NULL);
INSERT INTO `sys_dept` (`id`, `parent_id`, `code`, `name`, `type`, `principal`, `phone`, `email`, `sort`, `description`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (5, 4, 'T002', '财务部门', 3, '', '', '', 2, '', 1, 'admin', '2024-03-18 10:54:22', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` int NOT NULL AUTO_INCREMENT,
  `parent_id` int DEFAULT NULL COMMENT '父类id',
  `path` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '路由地址',
  `route_name` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '路由名称',
  `redirect` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '路由重定向',
  `component` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '路由组件',
  `title` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
  `icon` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '菜单图标',
  `show_link` bit(1) DEFAULT NULL COMMENT '是否展示',
  `rank` int DEFAULT '99' COMMENT '菜单排序',
  `show_parent` bit(1) DEFAULT NULL COMMENT '是否显示父菜单',
  `keep_alive` bit(1) DEFAULT NULL COMMENT '是否缓存',
  `frame_src` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '需要内嵌的iframe链接地址',
  `menu_type` int DEFAULT NULL COMMENT '菜单类型 \n1 菜单 2 iframe 3 外链 4 按钮\n',
  `permission` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '权限表示',
  `is_enabled` bit(1) DEFAULT NULL COMMENT '是否启用',
  `created_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建者',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新者',
  `modified` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='系统：菜单与权限';

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
BEGIN;
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (1, NULL, '/sys', '', '', '', '系统管理', 'ri:settings-3-line', b'1', 1, b'1', b'0', '', 1, '', b'1', NULL, NULL, NULL, '2024-03-21 10:07:54');
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (2, 1, '/sys/user/index', 'userManger', '', 'sys/user/index', '用户管理', 'ri:admin-line', b'1', 1, b'1', b'0', '', 1, '', b'1', NULL, NULL, NULL, '2024-03-21 10:20:38');
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (3, 1, '/sys/menu/index', 'menuManger', NULL, 'sys/menu/index', '菜单管理', 'ep:menu', b'1', 3, b'1', b'0', NULL, 1, NULL, b'1', 'admin', '2024-03-13 08:41:08', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (4, 3, '', '', '', '', '菜单查询', '', b'1', 1, b'1', b'0', '', 4, 'sys:permission:list', b'1', NULL, NULL, NULL, '2024-03-21 10:29:24');
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (5, 3, '', '', '', '', '菜单新增', '', b'1', 2, b'1', b'0', '', 4, 'sys:permission:add', b'1', NULL, NULL, NULL, '2024-03-21 10:29:31');
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (6, 3, '', '', '', '', '菜单修改', '', b'1', 3, b'1', b'0', '', 4, 'sys:permission:update', b'1', NULL, NULL, NULL, '2024-03-21 10:29:34');
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (7, 3, '', '', '', '', '菜单删除', '', b'1', 4, b'1', b'0', '', 4, 'sys:permission:delete', b'1', NULL, NULL, NULL, '2024-03-21 10:29:40');
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (8, 1, '/sys/role/index', 'roleManager', '', 'sys/role/index', '角色管理', 'ri:admin-fill', b'1', 2, b'1', b'0', '', 1, '', b'1', NULL, NULL, NULL, '2024-03-21 10:29:09');
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (23, 8, '', '', '', '', '角色查询', '', b'1', 1, b'1', b'0', '', 4, 'sys:role:list', b'1', NULL, NULL, NULL, '2024-03-21 10:24:52');
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (24, 8, '', '', '', '', '角色新增', '', b'1', 2, b'1', b'0', '', 4, 'sys:role:add', b'1', NULL, NULL, NULL, '2024-03-21 10:24:57');
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (25, 8, '', '', '', '', '角色更新', '', b'1', 3, b'1', b'0', '', 4, 'sys:role:update', b'1', NULL, NULL, NULL, '2024-03-21 10:25:38');
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (26, 8, '', '', '', '', '角色删除', '', b'1', 4, b'1', b'0', '', 4, 'sys:role:delete', b'1', NULL, NULL, NULL, '2024-03-21 10:26:31');
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (27, 8, '', '', '', '', '角色赋权', '', b'1', 5, b'1', b'0', '', 4, 'sys:role:assignPermission', b'1', NULL, NULL, NULL, '2024-03-21 10:26:37');
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (28, 1, '/sys/dept/index', 'deptManger', '', 'sys/dept/index', '部门管理', 'ri:node-tree', b'1', 4, b'1', b'0', '', 1, '', b'1', NULL, NULL, NULL, '2024-03-21 10:29:53');
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (29, 28, '', '', '', '', '部门保存', '', b'1', 1, b'1', b'0', '', 4, 'sys:dept:save', b'1', NULL, NULL, NULL, '2024-03-21 10:29:58');
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (30, 28, '', '', '', '', '部门修改', '', b'1', 2, b'1', b'0', '', 4, 'sys:dept:update', b'1', NULL, NULL, NULL, '2024-03-21 10:30:07');
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (31, 28, '', '', '', '', '部门删除', '', b'1', 3, b'1', b'0', '', 4, 'sys:dept:delete', b'1', NULL, NULL, NULL, '2024-03-21 10:30:11');
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (32, 2, '', '', '', '', '用户查询', '', b'1', 1, b'1', b'0', '', 4, 'sys:user:list', b'1', NULL, NULL, NULL, '2024-03-21 09:39:51');
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (33, 2, '', '', '', '', '用户新增', '', b'1', 2, b'1', b'0', '', 4, 'sys:user:save', b'1', NULL, NULL, NULL, '2024-03-21 09:40:12');
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (34, 2, '', '', '', '', '用户修改', '', b'1', 3, b'1', b'0', '', 4, 'sys:user:update', b'1', NULL, NULL, NULL, '2024-03-21 09:40:16');
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (35, 2, '', '', '', '', '用户删除', '', b'1', 4, b'1', b'0', '', 4, 'sys:user:delete', b'1', NULL, NULL, NULL, '2024-03-21 09:40:20');
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (36, 2, '', '', '', '', '密码重置', '', b'1', 5, b'1', b'0', '', 4, 'sys:user:resetPassword', b'1', NULL, NULL, NULL, '2024-03-21 09:46:56');
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (37, 1, '/sys/timing/index', 'Timing', '', 'sys/timing/index', '定时任务', 'ri:time-fill', b'1', 6, b'1', b'0', '', 1, '', b'1', 'admin', '2024-03-21 08:31:21', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (38, 37, '', '', '', '', '定时任务查询', '', b'1', 1, b'1', b'0', '', 4, 'sys:quartz:job:list', b'1', NULL, NULL, NULL, '2024-03-21 10:30:20');
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (39, 37, '', '', '', '', '定时任务新增', '', b'1', 2, b'1', b'0', '', 4, 'sys:quartz:job:save', b'1', NULL, NULL, NULL, '2024-03-21 10:30:25');
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (40, 37, '', '', '', '', '定时任务修改', '', b'1', 3, b'1', b'0', '', 4, 'sys:quartz:job:update', b'1', NULL, NULL, NULL, '2024-03-21 10:30:28');
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (41, 37, '', '', '', '', '定时任务删除', '', b'1', 4, b'1', b'0', '', 4, 'sys:quartz:job:delete', b'1', NULL, NULL, NULL, '2024-03-21 10:30:32');
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (42, 37, '', '', '', '', '定时任务执行', '', b'1', 5, b'1', b'0', '', 4, 'sys:quartz:job:run', b'1', NULL, NULL, NULL, '2024-03-22 10:07:18');
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (43, 37, '', '', '', '', '定时任务暂停', '', b'1', 6, b'1', b'0', '', 4, 'sys:quartz:job:pause', b'1', NULL, NULL, NULL, '2024-03-21 10:30:44');
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (44, 37, '', '', '', '', '定时任务恢复', '', b'1', 7, b'1', b'0', '', 4, 'sys:quartz:job:resume', b'1', NULL, NULL, NULL, '2024-03-21 10:30:47');
COMMIT;

-- ----------------------------
-- Table structure for sys_quartz_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_quartz_job`;
CREATE TABLE `sys_quartz_job` (
  `id` int NOT NULL AUTO_INCREMENT,
  `job_name` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务名称',
  `job_class_name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务类名/bean',
  `parameter` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '参数',
  `cron_expression` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'cron表达式',
  `is_enabled` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态 1 正常 0 禁用',
  `description` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '描述',
  `created_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建者',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新者',
  `modified` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='系统：定时任务';

-- ----------------------------
-- Records of sys_quartz_job
-- ----------------------------
BEGIN;
INSERT INTO `sys_quartz_job` (`id`, `job_name`, `job_class_name`, `parameter`, `cron_expression`, `is_enabled`, `description`, `created_by`, `created`, `modified_by`, `modified`) VALUES (1, '测试1', 'com.hb0730.job.TestJob1', '', '0 0/1 * * * ?', 0, '测试', 'admin', '2024-03-22 09:54:06', NULL, NULL);
INSERT INTO `sys_quartz_job` (`id`, `job_name`, `job_class_name`, `parameter`, `cron_expression`, `is_enabled`, `description`, `created_by`, `created`, `modified_by`, `modified`) VALUES (2, '测试1', 'com.hb0730.job.TestJob2', '{\"test\":\"测试\",\"test2\":1,\"test3\":false}', '0 0/1 * * * ?', 0, '测试参数', 'admin', '2024-03-22 09:56:28', NULL, NULL);
INSERT INTO `sys_quartz_job` (`id`, `job_name`, `job_class_name`, `parameter`, `cron_expression`, `is_enabled`, `description`, `created_by`, `created`, `modified_by`, `modified`) VALUES (3, '测试3', 'testJob3', '', '0 0/1 * * * ?', 0, 'Spring Bean测试', 'admin', '2024-03-22 10:46:25', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `code` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色标识',
  `description` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '描述',
  `is_enabled` bit(1) DEFAULT b'1' COMMENT '是否启用',
  `created_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建者',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新者',
  `modified` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='系统：角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` (`id`, `name`, `code`, `description`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (1, '管理员', 'ROLE_ADMIN', '管理员角色', b'1', 'admin', '2024-02-28 09:18:25', NULL, NULL);
INSERT INTO `sys_role` (`id`, `name`, `code`, `description`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (3, '测试', 'ROLE_test', '', b'1', 'admin', '2024-03-20 09:19:56', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `role_id` int NOT NULL COMMENT '角色ID',
  `permission_id` int NOT NULL COMMENT '菜单与权限ID',
  PRIMARY KEY (`role_id`,`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='系统：角色权限关联表';

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`) VALUES (1, 1);
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`) VALUES (1, 2);
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`) VALUES (1, 3);
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`) VALUES (1, 4);
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`) VALUES (1, 5);
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`) VALUES (1, 6);
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`) VALUES (1, 7);
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`) VALUES (1, 8);
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`) VALUES (1, 23);
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`) VALUES (1, 24);
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`) VALUES (1, 25);
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`) VALUES (1, 26);
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`) VALUES (1, 27);
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`) VALUES (1, 28);
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`) VALUES (1, 29);
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`) VALUES (1, 30);
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`) VALUES (1, 31);
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`) VALUES (1, 32);
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`) VALUES (1, 33);
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`) VALUES (1, 34);
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`) VALUES (1, 35);
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`) VALUES (1, 36);
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`) VALUES (1, 37);
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`) VALUES (1, 38);
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`) VALUES (1, 39);
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`) VALUES (1, 40);
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`) VALUES (1, 41);
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`) VALUES (1, 42);
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`) VALUES (1, 43);
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`) VALUES (1, 44);
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `nickname` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT '昵称',
  `email` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(12) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '手机号',
  `avatar` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '头像',
  `gender` tinyint DEFAULT NULL COMMENT '性别',
  `is_admin` tinyint(1) DEFAULT '0' COMMENT '是否管理员',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `pwd_reset_time` datetime DEFAULT NULL COMMENT '最近修改密码时间',
  `is_enabled` tinyint(1) DEFAULT '1' COMMENT '状态1 正常 0 禁用',
  `dept_id` int DEFAULT NULL COMMENT '用户所在部门',
  `created_by` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建者',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_by` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新者',
  `modified` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='系统：用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` (`id`, `username`, `password`, `nickname`, `email`, `phone`, `avatar`, `gender`, `is_admin`, `last_login_time`, `pwd_reset_time`, `is_enabled`, `dept_id`, `created_by`, `created`, `modified_by`, `modified`) VALUES (1, 'admin', '$2a$10$bzGz6zZzAxkN0CRjPf0Pb.CetihWFQo8X6n0oKpZxb1vmxBZVJHIC', '管理员', NULL, NULL, NULL, NULL, 1, '2024-03-22 17:13:25', NULL, 1, 4, 'admin', '2024-02-23 09:03:02', NULL, NULL);
INSERT INTO `sys_user` (`id`, `username`, `password`, `nickname`, `email`, `phone`, `avatar`, `gender`, `is_admin`, `last_login_time`, `pwd_reset_time`, `is_enabled`, `dept_id`, `created_by`, `created`, `modified_by`, `modified`) VALUES (2, 'test', '$2a$10$fyGuWNTBECPtgn2ifciwP.n9NzTGaDBWvxed8QBPfgIyOwGH2HG2m', '测试', '', '', NULL, NULL, 0, '2024-03-20 10:18:29', '2024-03-20 10:16:51', 1, 5, 'admin', '2024-03-20 09:23:04', 'admin', '2024-03-20 10:16:51');
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` int NOT NULL COMMENT '用户id',
  `role_id` int NOT NULL COMMENT '角色id',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='系统：用户角色-关联表\n';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES (1, 1);
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES (2, 3);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
