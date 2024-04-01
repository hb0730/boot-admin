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

 Date: 01/04/2024 14:09:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bas_organization
-- ----------------------------
DROP TABLE IF EXISTS `bas_organization`;
CREATE TABLE `bas_organization` (
  `id` varchar(32) COLLATE utf8mb4_general_ci NOT NULL,
  `parent_id` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '父类机构ID',
  `name` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT '机构名称',
  `product_id` int DEFAULT NULL COMMENT '产品ID',
  `link_man` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '联系人',
  `link_tel` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '联系电话',
  `link_email` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '联系邮箱',
  `address` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '所在地',
  `used_end_time` datetime DEFAULT NULL COMMENT '使用截止时间',
  `level` int DEFAULT NULL COMMENT '等级',
  `path` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '路径',
  `type` int DEFAULT NULL COMMENT '类型',
  `memo` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `is_system` bit(1) DEFAULT b'0' COMMENT '是否总部',
  `is_saas` bit(1) DEFAULT NULL COMMENT '是否saas',
  `is_enabled` bit(1) DEFAULT NULL COMMENT '是否可用',
  `sys_code` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT '租户编码',
  `created_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建者',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新者',
  `modified` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='履约端：机构管理';

-- ----------------------------
-- Records of bas_organization
-- ----------------------------
BEGIN;
INSERT INTO `bas_organization` (`id`, `parent_id`, `name`, `product_id`, `link_man`, `link_tel`, `link_email`, `address`, `used_end_time`, `level`, `path`, `type`, `memo`, `is_system`, `is_saas`, `is_enabled`, `sys_code`, `created_by`, `created`, `modified_by`, `modified`) VALUES ('1773607815714332672', NULL, '测试商户01', 1, '测试人员', '13111111111', '', '', '2029-03-01 00:00:00', 1, '1773607815714332672', 1, '', b'1', b'1', b'1', 'T001', 'admin', '2024-03-29 15:07:10', 'admin', '2024-03-30 21:55:16');
INSERT INTO `bas_organization` (`id`, `parent_id`, `name`, `product_id`, `link_man`, `link_tel`, `link_email`, `address`, `used_end_time`, `level`, `path`, `type`, `memo`, `is_system`, `is_saas`, `is_enabled`, `sys_code`, `created_by`, `created`, `modified_by`, `modified`) VALUES ('1774365053912952834', '1773607815714332672', '公司1', NULL, '测试', '13111111111', '', '', NULL, 2, '1773607815714332672,', 2, '', b'0', b'0', b'1', 'T001', '13111111111', '2024-03-31 17:16:10', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for bas_permission
-- ----------------------------
DROP TABLE IF EXISTS `bas_permission`;
CREATE TABLE `bas_permission` (
  `id` int NOT NULL AUTO_INCREMENT,
  `parent_id` int DEFAULT NULL COMMENT '父类id',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '路由地址',
  `route_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '路由名称',
  `redirect` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '路由重定向',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '路由组件',
  `title` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
  `icon` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '菜单图标',
  `show_link` bit(1) DEFAULT b'1' COMMENT '是否展示',
  `rank` int DEFAULT '99' COMMENT '菜单排序',
  `show_parent` bit(1) DEFAULT b'1' COMMENT '是否显示父菜单',
  `keep_alive` bit(1) DEFAULT b'0' COMMENT '是否缓存',
  `frame_src` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '需要内嵌的iframe链接地址',
  `menu_type` int DEFAULT NULL COMMENT '菜单类型 \n1 菜单 2 iframe 3 外链 4 按钮\n',
  `permission` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '权限表示',
  `is_enabled` bit(1) DEFAULT b'1' COMMENT '是否启用',
  `created_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建者',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新者',
  `modified` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='履约端：菜单与权限';

-- ----------------------------
-- Records of bas_permission
-- ----------------------------
BEGIN;
INSERT INTO `bas_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (1, NULL, '/bas', '', '', '', '系统管理', 'ri:settings-2-fill', b'1', 1, b'1', b'0', '', 1, '', b'1', 'admin', '2024-03-28 09:18:29', NULL, NULL);
INSERT INTO `bas_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (2, 1, '/bas/org/index', 'basOrgManager', '', 'basic/org/index', '机构管理', '', b'1', 1, b'1', b'0', '', 1, '', b'1', 'admin', '2024-03-28 09:22:09', 'superadmin', '2024-03-31 08:15:51');
INSERT INTO `bas_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (3, 1, '/bas/user/index', 'basUserManager', '', 'basic/user/index', '用户管理', '', b'1', 2, b'1', b'0', '', 1, '', b'1', 'admin', '2024-03-28 09:38:03', 'superadmin', '2024-03-31 08:16:07');
INSERT INTO `bas_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (4, 1, '/bas/role/index', 'basRoleManager', '', 'basic/role/index', '角色管理', '', b'1', 3, b'1', b'0', '', 1, '', b'1', 'admin', '2024-03-28 09:20:19', 'superadmin', '2024-03-31 17:20:31');
INSERT INTO `bas_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (5, 2, '', '', '', '', '机构新增', '', b'1', 2, b'1', b'0', '', 4, 'bas:org:save', b'1', 'admin', '2024-03-28 09:26:30', 'admin', '2024-03-28 09:27:46');
INSERT INTO `bas_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (6, 2, '', '', '', '', '机构更新', '', b'1', 3, b'1', b'0', '', 4, 'bas:org:update', b'1', 'admin', '2024-03-28 09:26:51', 'admin', '2024-03-28 09:31:03');
INSERT INTO `bas_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (7, 2, '', '', '', '', '机构删除', '', b'1', 4, b'1', b'0', '', 4, 'bas:org:delete', b'1', 'admin', '2024-03-28 09:27:09', 'admin', '2024-03-28 09:37:42');
INSERT INTO `bas_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (8, 2, '', '', '', '', '机构查询', '', b'1', 1, b'1', b'0', '', 4, 'bas:org:query', b'1', 'admin', '2024-03-28 09:27:25', NULL, NULL);
INSERT INTO `bas_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (9, 3, '', '', '', '', '用户查询', '', b'1', 1, b'1', b'0', '', 4, 'bas:user:query', b'1', 'admin', '2024-03-28 09:33:42', NULL, NULL);
INSERT INTO `bas_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (10, 3, '', '', '', '', '用户新增', '', b'1', 2, b'1', b'0', '', 4, 'bas:user:save', b'1', 'admin', '2024-03-28 09:38:29', NULL, NULL);
INSERT INTO `bas_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (11, 3, '', '', '', '', '用户修改', '', b'1', 3, b'1', b'0', '', 4, 'bas:user:update', b'1', 'admin', '2024-03-28 09:39:06', NULL, NULL);
INSERT INTO `bas_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (12, 3, '', '', '', '', '用户删除', '', b'1', 4, b'1', b'0', '', 4, 'bas:user:delete', b'1', 'admin', '2024-03-28 09:39:23', NULL, NULL);
INSERT INTO `bas_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (13, 4, '', '', '', '', '角色查询', '', b'1', 1, b'1', b'0', '', 4, 'bas:role:query', b'1', 'superadmin', '2024-03-31 18:01:27', 'superadmin', '2024-03-31 18:02:00');
INSERT INTO `bas_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (14, 4, '', '', '', '', '角色新增', '', b'1', 2, b'1', b'0', '', 4, 'bas:role:save', b'1', 'superadmin', '2024-03-31 18:01:39', NULL, NULL);
INSERT INTO `bas_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (15, 4, '', '', '', '', '角色修改', '', b'1', 3, b'1', b'0', '', 4, 'bas:role:update', b'1', 'superadmin', '2024-03-31 18:01:55', 'superadmin', '2024-03-31 18:02:04');
INSERT INTO `bas_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (16, 4, '', '', '', '', '角色删除', '', b'1', 4, b'1', b'0', '', 4, 'bas:role:delete', b'1', 'superadmin', '2024-03-31 18:02:17', NULL, NULL);
INSERT INTO `bas_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (17, 4, '', '', '', '', '角色赋权', '', b'1', 5, b'1', b'0', '', 4, 'bas:role:grant', b'1', 'superadmin', '2024-03-31 18:02:31', NULL, NULL);
INSERT INTO `bas_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (18, 3, '', '', '', '', '重置密码', '', b'1', 5, b'1', b'0', '', 4, 'bas:user:restPassword', b'1', 'superadmin', '2024-04-01 10:37:58', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for bas_role
-- ----------------------------
DROP TABLE IF EXISTS `bas_role`;
CREATE TABLE `bas_role` (
  `id` varchar(32) COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `code` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色编码',
  `description` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '描述',
  `org_id` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '所属机构ID',
  `sys_code` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '系统编码',
  `is_system` tinyint(1) DEFAULT '0' COMMENT '是否内置',
  `is_enabled` tinyint(1) DEFAULT '1' COMMENT '是否启用',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建者',
  `modified` datetime DEFAULT NULL COMMENT '修改时间',
  `modified_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='履约端：角色';

-- ----------------------------
-- Records of bas_role
-- ----------------------------
BEGIN;
INSERT INTO `bas_role` (`id`, `name`, `code`, `description`, `org_id`, `sys_code`, `is_system`, `is_enabled`, `created`, `created_by`, `modified`, `modified_by`) VALUES ('1773607815827578880', '管理角色', 'T001', NULL, '1773607815714332672', 'T001', 1, 1, '2024-03-29 15:07:10', 'admin', NULL, NULL);
INSERT INTO `bas_role` (`id`, `name`, `code`, `description`, `org_id`, `sys_code`, `is_system`, `is_enabled`, `created`, `created_by`, `modified`, `modified_by`) VALUES ('1774377228096892929', '测试角色', 'test01', '', NULL, 'T001', 0, 1, '2024-03-31 18:04:32', '13111111111', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for bas_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `bas_role_permission`;
CREATE TABLE `bas_role_permission` (
  `role_id` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色ID',
  `permission_id` int NOT NULL COMMENT '权限ID',
  PRIMARY KEY (`role_id`,`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='履约端：角色权限';

-- ----------------------------
-- Records of bas_role_permission
-- ----------------------------
BEGIN;
INSERT INTO `bas_role_permission` (`role_id`, `permission_id`) VALUES ('1773607815827578880', 1);
INSERT INTO `bas_role_permission` (`role_id`, `permission_id`) VALUES ('1773607815827578880', 2);
INSERT INTO `bas_role_permission` (`role_id`, `permission_id`) VALUES ('1773607815827578880', 3);
INSERT INTO `bas_role_permission` (`role_id`, `permission_id`) VALUES ('1773607815827578880', 4);
INSERT INTO `bas_role_permission` (`role_id`, `permission_id`) VALUES ('1773607815827578880', 5);
INSERT INTO `bas_role_permission` (`role_id`, `permission_id`) VALUES ('1773607815827578880', 6);
INSERT INTO `bas_role_permission` (`role_id`, `permission_id`) VALUES ('1773607815827578880', 7);
INSERT INTO `bas_role_permission` (`role_id`, `permission_id`) VALUES ('1773607815827578880', 8);
INSERT INTO `bas_role_permission` (`role_id`, `permission_id`) VALUES ('1773607815827578880', 9);
INSERT INTO `bas_role_permission` (`role_id`, `permission_id`) VALUES ('1773607815827578880', 10);
INSERT INTO `bas_role_permission` (`role_id`, `permission_id`) VALUES ('1773607815827578880', 11);
INSERT INTO `bas_role_permission` (`role_id`, `permission_id`) VALUES ('1773607815827578880', 12);
INSERT INTO `bas_role_permission` (`role_id`, `permission_id`) VALUES ('1773607815827578880', 13);
INSERT INTO `bas_role_permission` (`role_id`, `permission_id`) VALUES ('1773607815827578880', 14);
INSERT INTO `bas_role_permission` (`role_id`, `permission_id`) VALUES ('1773607815827578880', 15);
INSERT INTO `bas_role_permission` (`role_id`, `permission_id`) VALUES ('1773607815827578880', 16);
INSERT INTO `bas_role_permission` (`role_id`, `permission_id`) VALUES ('1773607815827578880', 17);
INSERT INTO `bas_role_permission` (`role_id`, `permission_id`) VALUES ('1773607815827578880', 18);
INSERT INTO `bas_role_permission` (`role_id`, `permission_id`) VALUES ('1774377228096892929', 1);
INSERT INTO `bas_role_permission` (`role_id`, `permission_id`) VALUES ('1774377228096892929', 2);
INSERT INTO `bas_role_permission` (`role_id`, `permission_id`) VALUES ('1774377228096892929', 5);
INSERT INTO `bas_role_permission` (`role_id`, `permission_id`) VALUES ('1774377228096892929', 6);
INSERT INTO `bas_role_permission` (`role_id`, `permission_id`) VALUES ('1774377228096892929', 7);
INSERT INTO `bas_role_permission` (`role_id`, `permission_id`) VALUES ('1774377228096892929', 8);
COMMIT;

-- ----------------------------
-- Table structure for bas_user
-- ----------------------------
DROP TABLE IF EXISTS `bas_user`;
CREATE TABLE `bas_user` (
  `id` varchar(32) COLLATE utf8mb4_general_ci NOT NULL,
  `org_id` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT '机构ID',
  `username` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户账号',
  `password` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户密码',
  `nickname` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '昵称',
  `phone` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '手机号',
  `email` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '邮箱',
  `avatar` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '头像',
  `gender` tinyint DEFAULT '0' COMMENT '性别,0 保密,1 男,2 女',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `pwd_reset_time` datetime DEFAULT NULL COMMENT '最近修改密码时间',
  `sys_code` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '系统代码',
  `is_system` tinyint(1) DEFAULT '0' COMMENT '是否内置',
  `is_enabled` tinyint(1) DEFAULT '1' COMMENT '是否启用',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建者',
  `modified` datetime DEFAULT NULL COMMENT '修改时间',
  `modified_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='履约端：用户';

-- ----------------------------
-- Records of bas_user
-- ----------------------------
BEGIN;
INSERT INTO `bas_user` (`id`, `org_id`, `username`, `password`, `nickname`, `phone`, `email`, `avatar`, `gender`, `last_login_time`, `pwd_reset_time`, `sys_code`, `is_system`, `is_enabled`, `created`, `created_by`, `modified`, `modified_by`) VALUES ('1773607815873716224', '1773607815714332672', '13111111111', '$2a$10$bzGz6zZzAxkN0CRjPf0Pb.CetihWFQo8X6n0oKpZxb1vmxBZVJHIC', '管理员', '13111111111', NULL, NULL, 0, '2024-04-01 10:38:15', NULL, 'T001', 1, 1, '2024-03-29 15:07:10', 'admin', '2024-03-30 07:34:13', 'admin');
INSERT INTO `bas_user` (`id`, `org_id`, `username`, `password`, `nickname`, `phone`, `email`, `avatar`, `gender`, `last_login_time`, `pwd_reset_time`, `sys_code`, `is_system`, `is_enabled`, `created`, `created_by`, `modified`, `modified_by`) VALUES ('1774624095126200322', '1774365053912952834', 'test@T001', 'Admin123456', '测试', '13111111111', '', '', 1, '2024-04-01 10:26:05', '2024-04-01 11:17:13', 'T001', 0, 1, '2024-04-01 10:25:28', '13111111111', '2024-04-01 11:17:13', '13111111111');
COMMIT;

-- ----------------------------
-- Table structure for bas_user_role
-- ----------------------------
DROP TABLE IF EXISTS `bas_user_role`;
CREATE TABLE `bas_user_role` (
  `role_id` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色ID',
  `user_id` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
  PRIMARY KEY (`role_id`,`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='履约端：用户角色';

-- ----------------------------
-- Records of bas_user_role
-- ----------------------------
BEGIN;
INSERT INTO `bas_user_role` (`role_id`, `user_id`) VALUES ('1773607815827578880', '1773607815873716224');
INSERT INTO `bas_user_role` (`role_id`, `user_id`) VALUES ('1774377228096892929', '1774624095126200322');
COMMIT;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int NOT NULL AUTO_INCREMENT,
  `parent_id` int DEFAULT NULL COMMENT '父类id',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '路由地址',
  `route_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '路由名称',
  `redirect` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '路由重定向',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '路由组件',
  `title` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
  `icon` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '菜单图标',
  `show_link` bit(1) DEFAULT b'1' COMMENT '是否展示',
  `rank` int DEFAULT '99' COMMENT '菜单排序',
  `show_parent` bit(1) DEFAULT b'1' COMMENT '是否显示父菜单',
  `keep_alive` bit(1) DEFAULT b'0' COMMENT '是否缓存',
  `frame_src` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '需要内嵌的iframe链接地址',
  `menu_type` int DEFAULT NULL COMMENT '菜单类型 \n1 菜单 2 iframe 3 外链 4 按钮\n',
  `permission` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '权限表示',
  `is_enabled` bit(1) DEFAULT b'1' COMMENT '是否启用',
  `created_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建者',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新者',
  `modified` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='管理端：履约端菜单';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` int NOT NULL AUTO_INCREMENT,
  `parent_id` int DEFAULT NULL COMMENT '父类id',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '路由地址',
  `route_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '路由名称',
  `redirect` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '路由重定向',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '路由组件',
  `title` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
  `icon` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '菜单图标',
  `show_link` bit(1) DEFAULT b'1' COMMENT '是否展示',
  `rank` int DEFAULT '99' COMMENT '菜单排序',
  `show_parent` bit(1) DEFAULT b'1' COMMENT '是否显示父菜单',
  `keep_alive` bit(1) DEFAULT b'0' COMMENT '是否缓存',
  `frame_src` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '需要内嵌的iframe链接地址',
  `menu_type` int DEFAULT NULL COMMENT '菜单类型 \n1 菜单 2 iframe 3 外链 4 按钮\n',
  `permission` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '权限表示',
  `is_enabled` bit(1) DEFAULT b'1' COMMENT '是否启用',
  `created_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建者',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新者',
  `modified` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='管理端：菜单与权限';

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
BEGIN;
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (1, NULL, '/sys', NULL, NULL, NULL, '系统管理', 'ri:settings-3-line', b'1', 1, b'1', b'0', NULL, 1, NULL, b'1', 'admin', '2024-03-25 09:36:20', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (2, 1, '/sys/user/index', 'userManger', NULL, 'sys/user/index', '用户管理', 'ri:admin-line', b'1', 1, b'1', b'0', NULL, 1, NULL, b'1', 'admin', '2024-03-25 09:37:45', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (3, 1, '/sys/menu/index', 'menuManger', NULL, 'sys/menu/index', '菜单管理', 'ep:menu', b'1', 3, b'1', b'0', NULL, 1, NULL, b'1', 'admin', '2024-03-25 09:40:13', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (4, 3, NULL, NULL, NULL, NULL, '菜单查询', NULL, b'1', 1, b'1', b'0', NULL, 4, 'sys:permission:list', b'1', 'admin', '2024-03-25 13:07:00', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (5, 3, NULL, NULL, NULL, NULL, '菜单新增', NULL, b'1', 2, b'1', b'0', NULL, 4, 'sys:permission:add', b'1', 'admin', '2024-03-25 13:07:37', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (6, 3, NULL, NULL, NULL, NULL, '菜单修改', NULL, b'1', 3, b'1', b'0', NULL, 4, 'sys:permission:update', b'1', 'admin', '2024-03-25 13:08:00', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (7, 3, NULL, NULL, NULL, NULL, '菜单删除', NULL, b'1', 4, b'1', b'0', NULL, 4, 'sys:permission:delete', b'1', 'admin', '2024-03-25 13:08:35', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (8, 1, '/sys/role/index', 'roleManger', NULL, 'sys/role/index', '角色管理', 'ri:admin-fill', b'1', 2, b'1', b'0', NULL, 1, NULL, b'1', 'admin', '2024-03-25 13:11:42', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (9, 8, NULL, NULL, NULL, NULL, '角色查询', NULL, b'1', 1, b'1', b'0', NULL, 4, 'sys:role:list', b'1', 'admin', '2024-03-25 13:12:18', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (10, 8, NULL, NULL, NULL, NULL, '角色新增', NULL, b'1', 2, b'1', b'0', NULL, 4, 'sys:role:add', b'1', 'admin', '2024-03-25 13:12:48', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (11, 8, NULL, NULL, NULL, NULL, '角色修改', NULL, b'1', 3, b'1', b'0', NULL, 4, 'sys:role:update', b'1', 'admin', '2024-03-25 13:13:15', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (12, 8, NULL, NULL, NULL, NULL, '角色删除', NULL, b'1', 4, b'1', b'0', NULL, 4, 'sys:role:delete', b'1', 'admin', '2024-03-25 13:13:42', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (13, 8, NULL, NULL, NULL, NULL, '角色赋权', NULL, b'1', 5, b'1', b'0', NULL, 4, 'sys:role:assignPermission', b'1', 'admin', '2024-03-25 13:14:12', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (14, 2, NULL, NULL, NULL, NULL, '用户查询', NULL, b'1', 1, b'1', b'0', NULL, 4, 'sys:user:list', b'1', 'admin', '2024-03-25 13:14:59', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (15, 2, NULL, NULL, NULL, NULL, '用户新增', NULL, b'1', 2, b'1', b'0', NULL, 4, 'sys:user:save', b'1', 'admin', '2024-03-25 13:15:26', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (16, 2, NULL, NULL, NULL, NULL, '用户修改', NULL, b'1', 3, b'1', b'0', NULL, 4, 'sys:user:update', b'1', 'admin', '2024-03-25 13:16:04', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (17, 2, NULL, NULL, NULL, NULL, '用户删除', NULL, b'1', 4, b'1', b'0', NULL, 4, 'sys:user:delete', b'1', 'admin', '2024-03-25 13:16:32', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (18, 2, NULL, NULL, NULL, NULL, '密码重置', NULL, b'1', 5, b'1', b'0', NULL, 4, 'sys:user:resetPassword', b'1', 'admin', '2024-03-25 13:16:59', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (19, NULL, '/tenant', '', '', '', '商户管理', 'ri:organization-chart', b'1', 2, b'1', b'0', '', 1, '', b'1', 'admin', '2024-03-25 15:03:33', 'admin', '2024-03-25 14:59:12');
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (20, 19, '/tenant/org/index', 'TenantOrgManager', '', 'tenant/org/index', '履约商管理', '', b'1', 1, b'1', b'0', '', 1, '', b'1', NULL, NULL, 'admin', '2024-03-29 14:30:39');
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (21, 19, '/tenant/product/index', 'TenantProductManager', '', 'tenant/product/index', '产品管理', '', b'1', 2, b'1', b'0', '', 1, '', b'1', NULL, NULL, 'admin', '2024-03-29 14:30:43');
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (22, 20, '', '', '', '', '履约商新增', '', b'1', 2, b'1', b'0', '', 4, 'tenant:org:add', b'1', NULL, NULL, 'admin', '2024-03-25 15:17:20');
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (23, 20, '', '', '', '', '履约商查询', '', b'1', 1, b'1', b'0', '', 4, 'tenant:org:query', b'1', 'admin', '2024-03-25 15:11:29', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (24, 20, '', '', '', '', '履约商修改', '', b'1', 3, b'1', b'0', '', 4, 'tenant:org:update', b'1', 'admin', '2024-03-25 15:12:02', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (25, 20, '', '', '', '', '履约商删除', '', b'1', 4, b'1', b'0', '', 4, 'tenant:org:delete', b'1', 'admin', '2024-03-25 15:12:22', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (26, 21, '', '', '', '', '产品查询', '', b'1', 1, b'1', b'0', '', 4, 'tenant:product:query', b'1', 'admin', '2024-03-25 15:12:44', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (27, 21, '', '', '', '', '产品新增', '', b'1', 2, b'1', b'0', '', 4, 'tenant:product:add', b'1', 'admin', '2024-03-25 15:13:06', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (28, 21, '', '', '', '', '产品修改', '', b'1', 3, b'1', b'0', '', 4, 'tenant:product:update', b'1', 'admin', '2024-03-25 15:13:33', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (29, 21, '', '', '', '', '产品删除', '', b'1', 4, b'1', b'0', '', 4, 'tenant:product:delete', b'1', 'admin', '2024-03-25 15:13:55', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (30, 19, '/tenant/permission/index', 'TenantPermissionManger', '', 'tenant/permission/index', '履约端权限', '', b'0', 3, b'1', b'0', '', 1, '', b'1', NULL, NULL, 'admin', '2024-03-29 14:30:47');
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (31, 30, '', '', '', '', '维护履约端权限', '', b'1', 1, b'1', b'0', '', 4, 'tenant:permission:update', b'1', 'admin', '2024-03-27 10:12:33', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`, `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (32, 21, '', '', '', '', '授权', '', b'1', 5, b'1', b'0', '', 4, 'tenant:product:grant', b'1', 'admin', '2024-03-27 10:13:06', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_product
-- ----------------------------
DROP TABLE IF EXISTS `sys_product`;
CREATE TABLE `sys_product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT '产品代码',
  `name` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT '产品名称',
  `principal` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '负责人',
  `contact` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '联系方式',
  `site_num` int DEFAULT NULL COMMENT '站点数量',
  `account_num` int DEFAULT NULL COMMENT '账号数量',
  `description` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '描述',
  `is_enabled` bit(1) DEFAULT b'1' COMMENT '是否启用',
  `created_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建者',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新者',
  `modified` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='管理端：产品管理';

-- ----------------------------
-- Records of sys_product
-- ----------------------------
BEGIN;
INSERT INTO `sys_product` (`id`, `code`, `name`, `principal`, `contact`, `site_num`, `account_num`, `description`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (1, 'TEST001', '测试产品01', '测试', '11@qq.com', 100, 10, '', b'1', 'admin', '2024-03-27 10:08:25', 'superadmin', '2024-03-31 08:14:25');
INSERT INTO `sys_product` (`id`, `code`, `name`, `principal`, `contact`, `site_num`, `account_num`, `description`, `is_enabled`, `created_by`, `created`, `modified_by`, `modified`) VALUES (2, 'TEST002', '测试产品02', '', '', NULL, NULL, '', b'1', 'admin', '2024-03-29 13:43:03', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_product_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_product_permission`;
CREATE TABLE `sys_product_permission` (
  `product_id` int NOT NULL,
  `permission_id` int NOT NULL COMMENT '履约端-权限ID',
  PRIMARY KEY (`product_id`,`permission_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='管理端：产品与菜单';

-- ----------------------------
-- Records of sys_product_permission
-- ----------------------------
BEGIN;
INSERT INTO `sys_product_permission` (`product_id`, `permission_id`) VALUES (1, 1);
INSERT INTO `sys_product_permission` (`product_id`, `permission_id`) VALUES (1, 2);
INSERT INTO `sys_product_permission` (`product_id`, `permission_id`) VALUES (1, 3);
INSERT INTO `sys_product_permission` (`product_id`, `permission_id`) VALUES (1, 4);
INSERT INTO `sys_product_permission` (`product_id`, `permission_id`) VALUES (1, 5);
INSERT INTO `sys_product_permission` (`product_id`, `permission_id`) VALUES (1, 6);
INSERT INTO `sys_product_permission` (`product_id`, `permission_id`) VALUES (1, 7);
INSERT INTO `sys_product_permission` (`product_id`, `permission_id`) VALUES (1, 8);
INSERT INTO `sys_product_permission` (`product_id`, `permission_id`) VALUES (1, 9);
INSERT INTO `sys_product_permission` (`product_id`, `permission_id`) VALUES (1, 10);
INSERT INTO `sys_product_permission` (`product_id`, `permission_id`) VALUES (1, 11);
INSERT INTO `sys_product_permission` (`product_id`, `permission_id`) VALUES (1, 12);
INSERT INTO `sys_product_permission` (`product_id`, `permission_id`) VALUES (1, 13);
INSERT INTO `sys_product_permission` (`product_id`, `permission_id`) VALUES (1, 14);
INSERT INTO `sys_product_permission` (`product_id`, `permission_id`) VALUES (1, 15);
INSERT INTO `sys_product_permission` (`product_id`, `permission_id`) VALUES (1, 16);
INSERT INTO `sys_product_permission` (`product_id`, `permission_id`) VALUES (1, 17);
INSERT INTO `sys_product_permission` (`product_id`, `permission_id`) VALUES (1, 18);
INSERT INTO `sys_product_permission` (`product_id`, `permission_id`) VALUES (2, 1);
INSERT INTO `sys_product_permission` (`product_id`, `permission_id`) VALUES (2, 2);
INSERT INTO `sys_product_permission` (`product_id`, `permission_id`) VALUES (2, 5);
INSERT INTO `sys_product_permission` (`product_id`, `permission_id`) VALUES (2, 6);
INSERT INTO `sys_product_permission` (`product_id`, `permission_id`) VALUES (2, 7);
INSERT INTO `sys_product_permission` (`product_id`, `permission_id`) VALUES (2, 8);
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int NOT NULL,
  `name` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `code` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色标识',
  `description` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '描述',
  `is_system` tinyint(1) DEFAULT '0' COMMENT '是否内置',
  `is_enabled` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建者',
  `modified` datetime DEFAULT NULL COMMENT '修改时间',
  `modified_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='管理端：角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` (`id`, `name`, `code`, `description`, `is_system`, `is_enabled`, `created`, `created_by`, `modified`, `modified_by`) VALUES (1, 'admin', 'admin', '超级管理员', 1, 1, '2024-03-25 09:40:43', 'admin', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `role_id` int NOT NULL,
  `permission_id` int NOT NULL,
  PRIMARY KEY (`role_id`,`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='管理端： 角色权限';

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
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`) VALUES (1, 9);
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`) VALUES (1, 10);
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`) VALUES (1, 11);
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`) VALUES (1, 12);
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`) VALUES (1, 13);
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`) VALUES (1, 14);
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`) VALUES (1, 15);
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`) VALUES (1, 16);
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`) VALUES (1, 17);
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`) VALUES (1, 18);
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`) VALUES (1, 19);
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`) VALUES (1, 20);
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`) VALUES (1, 21);
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`) VALUES (1, 22);
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
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `nickname` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT '昵称',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `gender` tinyint DEFAULT '0' COMMENT '性别 0 未知 1 男 2 女',
  `email` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(15) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '手机号',
  `avatar` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '头像',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `last_login_ip` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '最后登录ip',
  `last_pwd_reset_time` datetime DEFAULT NULL COMMENT '最后修改密码的时间',
  `description` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '描述',
  `is_system` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否内置',
  `is_enabled` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建者',
  `modified` datetime DEFAULT NULL COMMENT '修改时间',
  `modified_by` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='管理端： 用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` (`id`, `username`, `nickname`, `password`, `gender`, `email`, `phone`, `avatar`, `last_login_time`, `last_login_ip`, `last_pwd_reset_time`, `description`, `is_system`, `is_enabled`, `created`, `created_by`, `modified`, `modified_by`) VALUES (1, 'superadmin', '管理员', '$2a$10$bzGz6zZzAxkN0CRjPf0Pb.CetihWFQo8X6n0oKpZxb1vmxBZVJHIC', 0, NULL, NULL, NULL, '2024-04-01 10:37:34', NULL, NULL, '租户-超级管理员', 1, 1, '2024-03-23 09:17:46', 'admin', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='管理端：用户角色';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES (1, 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
