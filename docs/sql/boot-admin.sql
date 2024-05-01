/*
 Source Server         : boot-admin-local
 Source Server Type    : MySQL
 Source Server Version : 80300 (8.3.0)
 Source Host           : localhost:3306
 Source Schema         : boot-admin

 Target Server Type    : MySQL
 Target Server Version : 80300 (8.3.0)
 File Encoding         : 65001

 Date: 01/05/2024 19:48:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bas_notice_record
-- ----------------------------
DROP TABLE IF EXISTS `bas_notice_record`;
CREATE TABLE `bas_notice_record`
(
    `id`        varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `notice_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公告ID',
    `user_id`   varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
    `is_read`   bit(1)   DEFAULT NULL COMMENT '是否已读',
    `read_time` datetime DEFAULT NULL COMMENT '阅读时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='履约端：用户阅读公告记录';

-- ----------------------------
-- Records of bas_notice_record
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for bas_organization
-- ----------------------------
DROP TABLE IF EXISTS `bas_organization`;
CREATE TABLE `bas_organization`
(
    `id`            varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `parent_id`     varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '父类机构ID',
    `name`          varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '机构名称',
    `product_id`    varchar(32) COLLATE utf8mb4_general_ci                        DEFAULT NULL COMMENT '产品ID',
    `link_man`      varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '联系人',
    `link_tel`      varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '联系电话',
    `link_email`    varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '联系邮箱',
    `address`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '所在地',
    `used_end_time` datetime                                                      DEFAULT NULL COMMENT '使用截止时间',
    `level`         int                                                           DEFAULT NULL COMMENT '等级',
    `path`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '路径',
    `type`          int                                                           DEFAULT NULL COMMENT '类型',
    `memo`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
    `is_system`     bit(1)                                                        DEFAULT b'0' COMMENT '是否总部',
    `is_saas`       bit(1)                                                        DEFAULT NULL COMMENT '是否saas',
    `is_enabled`    bit(1)                                                        DEFAULT NULL COMMENT '是否可用',
    `sys_code`      varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '租户编码',
    `created_by`    varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '创建者',
    `created`       datetime                                                      DEFAULT NULL COMMENT '创建时间',
    `modified_by`   varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '更新者',
    `modified`      datetime                                                      DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='履约端：机构管理';

-- ----------------------------
-- Records of bas_organization
-- ----------------------------
BEGIN;
INSERT INTO `bas_organization` (`id`, `parent_id`, `name`, `product_id`, `link_man`, `link_tel`, `link_email`,
                                `address`, `used_end_time`, `level`, `path`, `type`, `memo`, `is_system`, `is_saas`,
                                `is_enabled`, `sys_code`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('1785229829122285568', NULL, 'pure-admin科技有限公司', '1785229367733444609', '测试联系人', '13111111111', '',
        '', NULL, 1, '1785229829122285568', 1, '', b'1', b'1', b'1', 'PA001', 'superadmin', '2024-04-30 16:48:54',
        'superadmin', '2024-04-30 18:04:44');
COMMIT;

-- ----------------------------
-- Table structure for bas_permission
-- ----------------------------
DROP TABLE IF EXISTS `bas_permission`;
CREATE TABLE `bas_permission`
(
    `id`          varchar(32) COLLATE utf8mb4_general_ci                       NOT NULL,
    `parent_id`   varchar(32) COLLATE utf8mb4_general_ci                        DEFAULT NULL COMMENT '父类id',
    `path`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '路由地址',
    `route_name`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '路由名称',
    `redirect`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '路由重定向',
    `component`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '路由组件',
    `title`       varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
    `icon`        varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '菜单图标',
    `show_link`   bit(1)                                                        DEFAULT b'1' COMMENT '是否展示',
    `rank`        int                                                           DEFAULT '99' COMMENT '菜单排序',
    `show_parent` bit(1)                                                        DEFAULT b'1' COMMENT '是否显示父菜单',
    `keep_alive`  bit(1)                                                        DEFAULT b'0' COMMENT '是否缓存',
    `frame_src`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '需要内嵌的iframe链接地址',
    `menu_type`   int                                                           DEFAULT NULL COMMENT '菜单类型 \n1 菜单 2 iframe 3 外链 4 按钮\n',
    `permission`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '权限表示',
    `is_enabled`  bit(1)                                                        DEFAULT b'1' COMMENT '是否启用',
    `created_by`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '创建者',
    `created`     datetime                                                      DEFAULT NULL COMMENT '创建时间',
    `modified_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '更新者',
    `modified`    datetime                                                      DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='履约端：菜单与权限';

-- ----------------------------
-- Records of bas_permission
-- ----------------------------
BEGIN;
INSERT INTO `bas_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`,
                              `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`,
                              `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('1785232090411360258', NULL, '/bas', '', '', '', '系统管理', 'ep:setting', b'1', 1, b'1', b'0', '', 1, '', b'1',
        'superadmin', '2024-04-30 16:57:53', 'superadmin', '2024-04-30 17:10:43');
INSERT INTO `bas_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`,
                              `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`,
                              `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('1785232201522667522', '1785232090411360258', '/bas/org/index', 'BasOrgManager', '', 'basic/org/index',
        '机构管理', '', b'1', 1, b'1', b'0', '', 1, '', b'1', 'superadmin', '2024-04-30 16:58:20', 'superadmin',
        '2024-04-30 16:59:29');
INSERT INTO `bas_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`,
                              `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`,
                              `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('1785232361749274625', '1785232090411360258', '/bas/user/index', 'BasUserManager', '', 'basic/user/index',
        '用户管理', '', b'1', 2, b'1', b'0', '', 1, '', b'1', 'superadmin', '2024-04-30 16:58:58', 'superadmin',
        '2024-04-30 17:12:34');
INSERT INTO `bas_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`,
                              `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`,
                              `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('1785232457840779265', '1785232090411360258', '/bas/role/index', 'BasRoleManager', '', 'basic/role/index',
        '角色管理', '', b'1', 3, b'1', b'0', '', 1, '', b'1', 'superadmin', '2024-04-30 16:59:21', 'superadmin',
        '2024-04-30 17:12:38');
INSERT INTO `bas_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`,
                              `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`,
                              `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('1785232570692722689', '1785232201522667522', '', '', '', '', '机构查询', '', b'1', 1, b'1', b'0', '', 4,
        'bas:org:query', b'1', 'superadmin', '2024-04-30 16:59:48', NULL, NULL);
INSERT INTO `bas_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`,
                              `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`,
                              `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('1785232622488182786', '1785232201522667522', '', '', '', '', '机构新增', '', b'1', 2, b'1', b'0', '', 4,
        'bas:org:save', b'1', 'superadmin', '2024-04-30 17:00:00', NULL, NULL);
INSERT INTO `bas_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`,
                              `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`,
                              `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('1785232698728046594', '1785232201522667522', '', '', '', '', '机构修改', '', b'1', 3, b'1', b'0', '', 4,
        'bas:org:update', b'1', 'superadmin', '2024-04-30 17:00:18', NULL, NULL);
INSERT INTO `bas_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`,
                              `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`,
                              `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('1785232754180939778', '1785232201522667522', '', '', '', '', '机构删除', '', b'1', 4, b'1', b'0', '', 4,
        'bas:org:delete', b'1', 'superadmin', '2024-04-30 17:00:31', NULL, NULL);
INSERT INTO `bas_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`,
                              `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`,
                              `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('1785232852340236290', '1785232361749274625', '', '', '', '', '用户查询', '', b'1', 1, b'1', b'0', '', 4,
        'bas:user:query', b'1', 'superadmin', '2024-04-30 17:00:55', NULL, NULL);
INSERT INTO `bas_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`,
                              `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`,
                              `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('1785232914025865217', '1785232361749274625', '', '', '', '', '用户新增', '', b'1', 2, b'1', b'0', '', 4,
        'bas:user:save', b'1', 'superadmin', '2024-04-30 17:01:09', NULL, NULL);
INSERT INTO `bas_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`,
                              `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`,
                              `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('1785232967222222850', '1785232361749274625', '', '', '', '', '用户更新', '', b'1', 3, b'1', b'0', '', 4,
        'bas:user:update', b'1', 'superadmin', '2024-04-30 17:01:22', NULL, NULL);
INSERT INTO `bas_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`,
                              `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`,
                              `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('1785233044208672769', '1785232361749274625', '', '', '', '', '重置密码', '', b'1', 4, b'1', b'0', '', 4,
        'bas:user:restPassword', b'1', 'superadmin', '2024-04-30 17:01:40', NULL, NULL);
INSERT INTO `bas_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`,
                              `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`,
                              `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('1785233103767789570', '1785232361749274625', '', '', '', '', '用户删除', '', b'1', 5, b'1', b'0', '', 4,
        'bas:user:delete', b'1', 'superadmin', '2024-04-30 17:01:55', NULL, NULL);
INSERT INTO `bas_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`,
                              `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`,
                              `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('1785233168422985730', '1785232457840779265', '', '', '', '', '角色查询', '', b'1', 1, b'1', b'0', '', 4,
        'bas:role:query', b'1', 'superadmin', '2024-04-30 17:02:10', NULL, NULL);
INSERT INTO `bas_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`,
                              `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`,
                              `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('1785233233040433154', '1785232457840779265', '', '', '', '', '角色新增', '', b'1', 2, b'1', b'0', '', 4,
        'bas:role:save', b'1', 'superadmin', '2024-04-30 17:02:25', NULL, NULL);
INSERT INTO `bas_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`,
                              `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`,
                              `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('1785233282499665922', '1785232457840779265', '', '', '', '', '角色修改', '', b'1', 3, b'1', b'0', '', 4,
        'bas:role:update', b'1', 'superadmin', '2024-04-30 17:02:37', NULL, NULL);
INSERT INTO `bas_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`,
                              `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`,
                              `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('1785233355887403010', '1785232457840779265', '', '', '', '', '角色删除', '', b'1', 4, b'1', b'0', '', 4,
        'bas:role:delete', b'1', 'superadmin', '2024-04-30 17:02:55', NULL, NULL);
INSERT INTO `bas_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`,
                              `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`,
                              `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('1785233423612829698', '1785232457840779265', '', '', '', '', '角色赋权', '', b'1', 5, b'1', b'0', '', 4,
        'bas:role:grant', b'1', 'superadmin', '2024-04-30 17:03:11', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for bas_role
-- ----------------------------
DROP TABLE IF EXISTS `bas_role`;
CREATE TABLE `bas_role`
(
    `id`          varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `name`        varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
    `code`        varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色编码',
    `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '描述',
    `org_id`      varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '所属机构ID',
    `sys_code`    varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '系统编码',
    `is_system`   tinyint(1)                                                    DEFAULT '0' COMMENT '是否内置',
    `is_enabled`  tinyint(1)                                                    DEFAULT '1' COMMENT '是否启用',
    `created`     datetime                                                      DEFAULT NULL COMMENT '创建时间',
    `created_by`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '创建者',
    `modified`    datetime                                                      DEFAULT NULL COMMENT '修改时间',
    `modified_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='履约端：角色';

-- ----------------------------
-- Records of bas_role
-- ----------------------------
BEGIN;
INSERT INTO `bas_role` (`id`, `name`, `code`, `description`, `org_id`, `sys_code`, `is_system`, `is_enabled`, `created`,
                        `created_by`, `modified`, `modified_by`)
VALUES ('1785229829160034304', '管理角色', 'PA001', NULL, '1785229829122285568', 'PA001', 1, 1, '2024-04-30 16:48:54',
        'superadmin', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for bas_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `bas_role_permission`;
CREATE TABLE `bas_role_permission`
(
    `role_id`       varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色ID',
    `permission_id` varchar(32) COLLATE utf8mb4_general_ci                       NOT NULL COMMENT '权限ID',
    PRIMARY KEY (`role_id`, `permission_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='履约端：角色权限';

-- ----------------------------
-- Records of bas_role_permission
-- ----------------------------
BEGIN;
INSERT INTO `bas_role_permission` (`role_id`, `permission_id`)
VALUES ('1785229829160034304', '1785232090411360258');
INSERT INTO `bas_role_permission` (`role_id`, `permission_id`)
VALUES ('1785229829160034304', '1785232201522667522');
INSERT INTO `bas_role_permission` (`role_id`, `permission_id`)
VALUES ('1785229829160034304', '1785232361749274625');
INSERT INTO `bas_role_permission` (`role_id`, `permission_id`)
VALUES ('1785229829160034304', '1785232457840779265');
INSERT INTO `bas_role_permission` (`role_id`, `permission_id`)
VALUES ('1785229829160034304', '1785232570692722689');
INSERT INTO `bas_role_permission` (`role_id`, `permission_id`)
VALUES ('1785229829160034304', '1785232622488182786');
INSERT INTO `bas_role_permission` (`role_id`, `permission_id`)
VALUES ('1785229829160034304', '1785232698728046594');
INSERT INTO `bas_role_permission` (`role_id`, `permission_id`)
VALUES ('1785229829160034304', '1785232754180939778');
INSERT INTO `bas_role_permission` (`role_id`, `permission_id`)
VALUES ('1785229829160034304', '1785232852340236290');
INSERT INTO `bas_role_permission` (`role_id`, `permission_id`)
VALUES ('1785229829160034304', '1785232914025865217');
INSERT INTO `bas_role_permission` (`role_id`, `permission_id`)
VALUES ('1785229829160034304', '1785232967222222850');
INSERT INTO `bas_role_permission` (`role_id`, `permission_id`)
VALUES ('1785229829160034304', '1785233044208672769');
INSERT INTO `bas_role_permission` (`role_id`, `permission_id`)
VALUES ('1785229829160034304', '1785233103767789570');
INSERT INTO `bas_role_permission` (`role_id`, `permission_id`)
VALUES ('1785229829160034304', '1785233168422985730');
INSERT INTO `bas_role_permission` (`role_id`, `permission_id`)
VALUES ('1785229829160034304', '1785233233040433154');
INSERT INTO `bas_role_permission` (`role_id`, `permission_id`)
VALUES ('1785229829160034304', '1785233282499665922');
INSERT INTO `bas_role_permission` (`role_id`, `permission_id`)
VALUES ('1785229829160034304', '1785233355887403010');
INSERT INTO `bas_role_permission` (`role_id`, `permission_id`)
VALUES ('1785229829160034304', '1785233423612829698');
COMMIT;

-- ----------------------------
-- Table structure for bas_user
-- ----------------------------
DROP TABLE IF EXISTS `bas_user`;
CREATE TABLE `bas_user`
(
    `id`              varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL,
    `org_id`          varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '机构ID',
    `username`        varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '用户账号',
    `password`        varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户密码',
    `nickname`        varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '昵称',
    `phone`           varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '手机号',
    `email`           varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '邮箱',
    `avatar`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '头像',
    `gender`          tinyint                                                       DEFAULT '0' COMMENT '性别,0 保密,1 男,2 女',
    `last_login_time` datetime                                                      DEFAULT NULL COMMENT '最后登录时间',
    `pwd_reset_time`  datetime                                                      DEFAULT NULL COMMENT '最近修改密码时间',
    `sys_code`        varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '系统代码',
    `is_system`       tinyint(1)                                                    DEFAULT '0' COMMENT '是否内置',
    `is_enabled`      tinyint(1)                                                    DEFAULT '1' COMMENT '是否启用',
    `created`         datetime                                                      DEFAULT NULL COMMENT '创建时间',
    `created_by`      varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '创建者',
    `modified`        datetime                                                      DEFAULT NULL COMMENT '修改时间',
    `modified_by`     varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='履约端：用户';

-- ----------------------------
-- Records of bas_user
-- ----------------------------
BEGIN;
INSERT INTO `bas_user` (`id`, `org_id`, `username`, `password`, `nickname`, `phone`, `email`, `avatar`, `gender`,
                        `last_login_time`, `pwd_reset_time`, `sys_code`, `is_system`, `is_enabled`, `created`,
                        `created_by`, `modified`, `modified_by`)
VALUES ('1785229829181005824', '1785229829122285568', '13111111111',
        '$2a$10$bzGz6zZzAxkN0CRjPf0Pb.CetihWFQo8X6n0oKpZxb1vmxBZVJHIC', '管理员', '13111111111', NULL, NULL, 0,
        '2024-04-30 18:07:46', NULL, 'PA001', 1, 1, '2024-04-30 16:48:54', 'superadmin', '2024-04-30 18:04:44',
        'superadmin');
COMMIT;

-- ----------------------------
-- Table structure for bas_user_role
-- ----------------------------
DROP TABLE IF EXISTS `bas_user_role`;
CREATE TABLE `bas_user_role`
(
    `role_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色ID',
    `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
    PRIMARY KEY (`role_id`, `user_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='履约端：用户角色';

-- ----------------------------
-- Records of bas_user_role
-- ----------------------------
BEGIN;
INSERT INTO `bas_user_role` (`role_id`, `user_id`)
VALUES ('1785229829160034304', '1785229829181005824');
COMMIT;

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`
(
    `id`                varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `title`             varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标题',
    `content`           text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci        NOT NULL COMMENT '内容',
    `notice_time_start` datetime                                                      DEFAULT NULL COMMENT '公告日期',
    `notice_time_end`   datetime                                                      DEFAULT NULL COMMENT '公共结束时间',
    `is_enabled`        bit(1)                                                        DEFAULT b'1' COMMENT '是否启用',
    `org_id`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '网点ID',
    `created`           datetime                                                      DEFAULT NULL COMMENT '创建时间',
    `created_by`        varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '创建者',
    `modified`          datetime                                                      DEFAULT NULL COMMENT '修改时间',
    `modified_by`       varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='管理端：系统公告';

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_oss_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_oss_config`;
CREATE TABLE `sys_oss_config`
(
    `id`          varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL,
    `oss_name`    varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT 'oss名称',
    `oss_type`    varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT 'oss类型',
    `access_key`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '访问key',
    `secret_key`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '访问密钥',
    `bucket_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '桶名称',
    `endpoint`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'endpoint',
    `domain`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '自定义域名',
    `region`      varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '区域',
    `sys_code`    varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '商户识别码',
    `created_by`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '创建者',
    `created`     datetime                                                      DEFAULT NULL COMMENT '创建时间',
    `modified_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '更新者',
    `modified`    datetime                                                      DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='管理端：商户oss配置';

-- ----------------------------
-- Records of sys_oss_config
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`
(
    `id`          varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'id',
    `parent_id`   varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '父类id',
    `path`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '路由地址',
    `route_name`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '路由名称',
    `redirect`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '路由重定向',
    `component`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '路由组件',
    `title`       varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
    `icon`        varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '菜单图标',
    `show_link`   bit(1)                                                        DEFAULT b'1' COMMENT '是否展示',
    `rank`        int                                                           DEFAULT '99' COMMENT '菜单排序',
    `show_parent` bit(1)                                                        DEFAULT b'1' COMMENT '是否显示父菜单',
    `keep_alive`  bit(1)                                                        DEFAULT b'0' COMMENT '是否缓存',
    `frame_src`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '需要内嵌的iframe链接地址',
    `menu_type`   int                                                           DEFAULT NULL COMMENT '菜单类型 \n1 菜单 2 iframe 3 外链 4 按钮\n',
    `permission`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '权限表示',
    `is_enabled`  bit(1)                                                        DEFAULT b'1' COMMENT '是否启用',
    `created_by`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '创建者',
    `created`     datetime                                                      DEFAULT NULL COMMENT '创建时间',
    `modified_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '更新者',
    `modified`    datetime                                                      DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='管理端：菜单与权限';

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
BEGIN;
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`,
                              `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`,
                              `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('1', NULL, '/sys', NULL, NULL, NULL, '系统管理', 'ri:settings-3-line', b'1', 1, b'1', b'0', NULL, 1, NULL, b'1',
        'admin', '2024-03-25 09:36:20', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`,
                              `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`,
                              `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('10', '8', NULL, NULL, NULL, NULL, '角色新增', NULL, b'1', 2, b'1', b'0', NULL, 4, 'sys:role:add', b'1',
        'admin', '2024-03-25 13:12:48', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`,
                              `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`,
                              `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('11', '8', NULL, NULL, NULL, NULL, '角色修改', NULL, b'1', 3, b'1', b'0', NULL, 4, 'sys:role:update', b'1',
        'admin', '2024-03-25 13:13:15', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`,
                              `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`,
                              `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('12', '8', NULL, NULL, NULL, NULL, '角色删除', NULL, b'1', 4, b'1', b'0', NULL, 4, 'sys:role:delete', b'1',
        'admin', '2024-03-25 13:13:42', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`,
                              `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`,
                              `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('13', '8', NULL, NULL, NULL, NULL, '角色赋权', NULL, b'1', 5, b'1', b'0', NULL, 4, 'sys:role:assignPermission',
        b'1', 'admin', '2024-03-25 13:14:12', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`,
                              `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`,
                              `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('1785218829636894721', '2', '', '', '', '', '用户查询', '', b'1', 1, b'1', b'0', '', 4, 'sys:user:list', b'1',
        'superadmin', '2024-04-30 16:05:11', 'superadmin', '2024-04-30 16:05:20');
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`,
                              `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`,
                              `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('1785219038316101633', '2', '', '', '', '', '用户新增', '', b'1', 2, b'1', b'0', '', 4, 'sys:user:save', b'1',
        'superadmin', '2024-04-30 16:06:01', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`,
                              `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`,
                              `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('1785219103000657921', '2', '', '', '', '', '用户修改', '', b'1', 3, b'1', b'0', '', 4, 'sys:user:update', b'1',
        'superadmin', '2024-04-30 16:06:17', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`,
                              `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`,
                              `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('1785219231749013505', '2', '', '', '', '', '用户删除', '', b'1', 4, b'1', b'0', '', 4, 'sys:user:delete', b'1',
        'superadmin', '2024-04-30 16:06:47', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`,
                              `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`,
                              `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('1785219294940397569', '2', '', '', '', '', '重置密码', '', b'1', 5, b'1', b'0', '', 4,
        'sys:user:resetPassword', b'1', 'superadmin', '2024-04-30 16:07:02', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`,
                              `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`,
                              `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('1785225857788874754', '1', '/sys/notice/index', 'noticeManager', '', 'sys/notice/index', '系统公告',
        'ep:bell-filled', b'1', 4, b'1', b'0', '', 1, '', b'1', 'superadmin', '2024-04-30 16:33:07', 'superadmin',
        '2024-04-30 16:33:53');
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`,
                              `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`,
                              `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('1785226155672539138', '1785225857788874754', '', '', '', '', '公告查询', '', b'1', 1, b'1', b'0', '', 4,
        'sys:notice:query', b'1', 'superadmin', '2024-04-30 16:34:18', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`,
                              `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`,
                              `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('1785226275357003777', '1785225857788874754', '', '', '', '', '公告新增', '', b'1', 2, b'1', b'0', '', 4,
        'sys:notice:save', b'1', 'superadmin', '2024-04-30 16:34:47', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`,
                              `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`,
                              `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('1785226339936702465', '1785225857788874754', '', '', '', '', '公告修改', '', b'1', 3, b'1', b'0', '', 4,
        'sys:notice:update', b'1', 'superadmin', '2024-04-30 16:35:02', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`,
                              `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`,
                              `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('1785226422149255170', '1785225857788874754', '', '', '', '', '公告删除', '', b'1', 4, b'1', b'0', '', 4,
        'sys:notice:delete', b'1', 'superadmin', '2024-04-30 16:35:22', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`,
                              `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`,
                              `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('1785226686633676802', NULL, '/tenant', '', '', '', '商户管理', 'ri:organization-chart', b'1', 2, b'1', b'0',
        '', 1, '', b'1', 'superadmin', '2024-04-30 16:36:25', 'superadmin', '2024-04-30 16:36:50');
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`,
                              `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`,
                              `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('1785227063890350082', '1785226686633676802', '/tenant/org/index', 'TenantOrgManager', '',
        'sys/tenant/org/index', '履约商管理', '', b'1', 1, b'1', b'0', '', 1, '', b'1', 'superadmin',
        '2024-04-30 16:37:55', 'superadmin', '2024-04-30 16:40:12');
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`,
                              `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`,
                              `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('1785227207171969025', '1785227063890350082', '', '', '', '', '履约商查询', '', b'1', 1, b'1', b'0', '', 4,
        'tenant:org:query', b'1', 'superadmin', '2024-04-30 16:38:29', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`,
                              `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`,
                              `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('1785227275484598274', '1785227063890350082', '', '', '', '', '履约商新增', '', b'1', 2, b'1', b'0', '', 4,
        'tenant:org:add', b'1', 'superadmin', '2024-04-30 16:38:45', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`,
                              `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`,
                              `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('1785227334389403649', '1785227063890350082', '', '', '', '', '履约商修改', '', b'1', 3, b'1', b'0', '', 4,
        'tenant:org:update', b'1', 'superadmin', '2024-04-30 16:38:59', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`,
                              `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`,
                              `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('1785227498244083713', '1785227063890350082', '', '', '', '', '履约商删除', '', b'1', 4, b'1', b'0', '', 4,
        'tenant:org:delete', b'1', 'superadmin', '2024-04-30 16:39:38', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`,
                              `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`,
                              `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('1785228542034370562', '1785226686633676802', '/tenant/product/index', 'ProductManager', '',
        'sys/tenant/product/index', '产品管理', '', b'1', 2, b'1', b'0', '', 1, '', b'1', 'superadmin',
        '2024-04-30 16:43:47', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`,
                              `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`,
                              `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('1785228667859296258', '1785228542034370562', '', '', '', '', '产品查询', '', b'1', 1, b'1', b'0', '', 4,
        'tenant:product:query', b'1', 'superadmin', '2024-04-30 16:44:17', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`,
                              `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`,
                              `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('1785228729737863169', '1785228542034370562', '', '', '', '', '产品新增', '', b'1', 2, b'1', b'0', '', 4,
        'tenant:product:add', b'1', 'superadmin', '2024-04-30 16:44:32', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`,
                              `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`,
                              `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('1785228787493429250', '1785228542034370562', '', '', '', '', '产品更新', '', b'1', 3, b'1', b'0', '', 4,
        'tenant:product:update', b'1', 'superadmin', '2024-04-30 16:44:46', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`,
                              `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`,
                              `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('1785228851183935490', '1785228542034370562', '', '', '', '', '产品删除', '', b'1', 4, b'1', b'0', '', 4,
        'tenant:product:delete', b'1', 'superadmin', '2024-04-30 16:45:01', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`,
                              `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`,
                              `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('1785228948500176897', '1785228542034370562', '', '', '', '', '产品赋权', '', b'1', 5, b'1', b'0', '', 4,
        'tenant:product:grant', b'1', 'superadmin', '2024-04-30 16:45:24', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`,
                              `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`,
                              `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('1785231431528144897', '1785226686633676802', '/tenant/permission/index', 'TenantPermissionManger', '',
        'sys/tenant/permission/index', '履约端权限', '', b'0', 3, b'1', b'0', '', 1, '', b'1', 'superadmin',
        '2024-04-30 16:55:16', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`,
                              `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`,
                              `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('1785231706154393601', '1785231431528144897', '', '', '', '', '维护履约端权限', '', b'1', 1, b'1', b'0', '', 4,
        'tenant:permission:update', b'1', 'superadmin', '2024-04-30 16:56:21', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`,
                              `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`,
                              `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('1785243846168895490', NULL, '/monitor', '', '', '', '系统监控', 'ep:monitor', b'1', 3, b'1', b'0', '', 1, '',
        b'1', 'superadmin', '2024-04-30 17:44:36', 'superadmin', '2024-04-30 17:46:16');
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`,
                              `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`,
                              `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('1785244014243045378', '1785243846168895490', '/monitor/task/index', 'TaskJobManger', '',
        'sys/monitor/task/index', '定时任务', '', b'1', 1, b'1', b'0', '', 1, '', b'1', 'superadmin',
        '2024-04-30 17:45:16', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`,
                              `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`,
                              `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('1785474078014992385', '1785244014243045378', '', '', '', '', '定时任务查询', '', b'1', 1, b'1', b'0', '', 4,
        'sys:quartz:job:page', b'1', 'superadmin', '2024-05-01 08:59:27', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`,
                              `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`,
                              `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('1785474153176920065', '1785244014243045378', '', '', '', '', '定时任务详情', '', b'1', 2, b'1', b'0', '', 4,
        'sys:quartz:job:info', b'1', 'superadmin', '2024-05-01 08:59:45', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`,
                              `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`,
                              `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('1785474214451507202', '1785244014243045378', '', '', '', '', '定时任务新增', '', b'1', 3, b'1', b'0', '', 4,
        'sys:quartz:job:save', b'1', 'superadmin', '2024-05-01 09:00:00', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`,
                              `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`,
                              `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('1785474310224244738', '1785244014243045378', '', '', '', '', '定时任务修改', '', b'1', 4, b'1', b'0', '', 4,
        'sys:quartz:job:update', b'1', 'superadmin', '2024-05-01 09:00:23', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`,
                              `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`,
                              `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('1785474379866468353', '1785244014243045378', '', '', '', '', '定时任务删除', '', b'1', 5, b'1', b'0', '', 4,
        'sys:quartz:job:delete', b'1', 'superadmin', '2024-05-01 09:00:39', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`,
                              `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`,
                              `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('1785474480643010562', '1785244014243045378', '', '', '', '', '定时任务暂停', '', b'1', 6, b'1', b'0', '', 4,
        'sys:quartz:job:pause', b'1', 'superadmin', '2024-05-01 09:01:03', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`,
                              `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`,
                              `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('1785474547487633409', '1785244014243045378', '', '', '', '', '定时任务恢复', '', b'1', 7, b'1', b'0', '', 4,
        'sys:quartz:job:resume', b'1', 'superadmin', '2024-05-01 09:01:19', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`,
                              `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`,
                              `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('1785474601975836674', '1785244014243045378', '', '', '', '', '立即执行', '', b'1', 8, b'1', b'0', '', 4,
        'sys:quartz:job:run', b'1', 'superadmin', '2024-05-01 09:01:32', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`,
                              `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`,
                              `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('2', '1', '/sys/user/index', 'userManger', NULL, 'sys/user/index', '用户管理', 'ri:admin-line', b'1', 1, b'1',
        b'0', NULL, 1, NULL, b'1', 'admin', '2024-03-25 09:37:45', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`,
                              `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`,
                              `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('3', '1', '/sys/menu/index', 'menuManger', NULL, 'sys/menu/index', '菜单管理', 'ep:menu', b'1', 3, b'1', b'0',
        NULL, 1, NULL, b'1', 'admin', '2024-03-25 09:40:13', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`,
                              `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`,
                              `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('4', '3', NULL, NULL, NULL, NULL, '菜单查询', NULL, b'1', 1, b'1', b'0', NULL, 4, 'sys:permission:list', b'1',
        'admin', '2024-03-25 13:07:00', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`,
                              `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`,
                              `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('5', '3', NULL, NULL, NULL, NULL, '菜单新增', NULL, b'1', 2, b'1', b'0', NULL, 4, 'sys:permission:add', b'1',
        'admin', '2024-03-25 13:07:37', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`,
                              `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`,
                              `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('6', '3', NULL, NULL, NULL, NULL, '菜单修改', NULL, b'1', 3, b'1', b'0', NULL, 4, 'sys:permission:update', b'1',
        'admin', '2024-03-25 13:08:00', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`,
                              `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`,
                              `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('7', '3', NULL, NULL, NULL, NULL, '菜单删除', NULL, b'1', 4, b'1', b'0', NULL, 4, 'sys:permission:delete', b'1',
        'admin', '2024-03-25 13:08:35', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`,
                              `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`,
                              `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('8', '1', '/sys/role/index', 'roleManger', NULL, 'sys/role/index', '角色管理', 'ri:admin-fill', b'1', 2, b'1',
        b'0', NULL, 1, NULL, b'1', 'admin', '2024-03-25 13:11:42', NULL, NULL);
INSERT INTO `sys_permission` (`id`, `parent_id`, `path`, `route_name`, `redirect`, `component`, `title`, `icon`,
                              `show_link`, `rank`, `show_parent`, `keep_alive`, `frame_src`, `menu_type`, `permission`,
                              `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('9', '8', NULL, NULL, NULL, NULL, '角色查询', NULL, b'1', 1, b'1', b'0', NULL, 4, 'sys:role:list', b'1',
        'admin', '2024-03-25 13:12:18', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_product
-- ----------------------------
DROP TABLE IF EXISTS `sys_product`;
CREATE TABLE `sys_product`
(
    `id`          varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'id',
    `code`        varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '产品代码',
    `name`        varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '产品名称',
    `principal`   varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '负责人',
    `contact`     varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '联系方式',
    `site_num`    int                                                           DEFAULT NULL COMMENT '站点数量',
    `account_num` int                                                           DEFAULT NULL COMMENT '账号数量',
    `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '描述',
    `is_enabled`  bit(1)                                                        DEFAULT b'1' COMMENT '是否启用',
    `created_by`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '创建者',
    `created`     datetime                                                      DEFAULT NULL COMMENT '创建时间',
    `modified_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '更新者',
    `modified`    datetime                                                      DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='管理端：产品管理';

-- ----------------------------
-- Records of sys_product
-- ----------------------------
BEGIN;
INSERT INTO `sys_product` (`id`, `code`, `name`, `principal`, `contact`, `site_num`, `account_num`, `description`,
                           `is_enabled`, `created_by`, `created`, `modified_by`, `modified`)
VALUES ('1785229367733444609', 'T001', '正式产品', '', '', 0, 0, '', b'1', 'superadmin', '2024-04-30 16:47:04',
        'superadmin', '2024-04-30 17:51:34');
COMMIT;

-- ----------------------------
-- Table structure for sys_product_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_product_permission`;
CREATE TABLE `sys_product_permission`
(
    `product_id`    varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT '产品ID',
    `permission_id` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT '履约端-权限ID',
    PRIMARY KEY (`product_id`, `permission_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='管理端：产品与菜单';

-- ----------------------------
-- Records of sys_product_permission
-- ----------------------------
BEGIN;
INSERT INTO `sys_product_permission` (`product_id`, `permission_id`)
VALUES ('1785229367733444609', '1785232090411360258');
INSERT INTO `sys_product_permission` (`product_id`, `permission_id`)
VALUES ('1785229367733444609', '1785232201522667522');
INSERT INTO `sys_product_permission` (`product_id`, `permission_id`)
VALUES ('1785229367733444609', '1785232361749274625');
INSERT INTO `sys_product_permission` (`product_id`, `permission_id`)
VALUES ('1785229367733444609', '1785232457840779265');
INSERT INTO `sys_product_permission` (`product_id`, `permission_id`)
VALUES ('1785229367733444609', '1785232570692722689');
INSERT INTO `sys_product_permission` (`product_id`, `permission_id`)
VALUES ('1785229367733444609', '1785232622488182786');
INSERT INTO `sys_product_permission` (`product_id`, `permission_id`)
VALUES ('1785229367733444609', '1785232698728046594');
INSERT INTO `sys_product_permission` (`product_id`, `permission_id`)
VALUES ('1785229367733444609', '1785232754180939778');
INSERT INTO `sys_product_permission` (`product_id`, `permission_id`)
VALUES ('1785229367733444609', '1785232852340236290');
INSERT INTO `sys_product_permission` (`product_id`, `permission_id`)
VALUES ('1785229367733444609', '1785232914025865217');
INSERT INTO `sys_product_permission` (`product_id`, `permission_id`)
VALUES ('1785229367733444609', '1785232967222222850');
INSERT INTO `sys_product_permission` (`product_id`, `permission_id`)
VALUES ('1785229367733444609', '1785233044208672769');
INSERT INTO `sys_product_permission` (`product_id`, `permission_id`)
VALUES ('1785229367733444609', '1785233103767789570');
INSERT INTO `sys_product_permission` (`product_id`, `permission_id`)
VALUES ('1785229367733444609', '1785233168422985730');
INSERT INTO `sys_product_permission` (`product_id`, `permission_id`)
VALUES ('1785229367733444609', '1785233233040433154');
INSERT INTO `sys_product_permission` (`product_id`, `permission_id`)
VALUES ('1785229367733444609', '1785233282499665922');
INSERT INTO `sys_product_permission` (`product_id`, `permission_id`)
VALUES ('1785229367733444609', '1785233355887403010');
INSERT INTO `sys_product_permission` (`product_id`, `permission_id`)
VALUES ('1785229367733444609', '1785233423612829698');
COMMIT;

-- ----------------------------
-- Table structure for sys_quartz_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_quartz_job`;
CREATE TABLE `sys_quartz_job`
(
    `id`              varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL,
    `job_name`        varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '任务名称',
    `job_class_name`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务类名/bean名称',
    `parameter`       text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '参数',
    `cron_expression` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT 'cron表达式',
    `is_enabled`      bit(1)                                                        DEFAULT b'1' COMMENT '状态',
    `description`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '描述',
    `sys_code`        varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '商户识别码/分组',
    `created`         datetime                                                      DEFAULT NULL COMMENT '创建时间',
    `created_by`      varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '创建者',
    `modified`        datetime                                                      DEFAULT NULL COMMENT '修改时间',
    `modified_by`     varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='管理端：定时任务';

-- ----------------------------
-- Records of sys_quartz_job
-- ----------------------------
BEGIN;
INSERT INTO `sys_quartz_job` (`id`, `job_name`, `job_class_name`, `parameter`, `cron_expression`, `is_enabled`,
                              `description`, `sys_code`, `created`, `created_by`, `modified`, `modified_by`)
VALUES ('1785475494498562049', '简单任务', 'com.hb0730.jobs.TestJob1', '', '0 0/2 * * * ?', b'0', '无参', 'DEFAULT',
        '2024-05-01 09:05:05', 'superadmin', '2024-05-01 09:25:14', 'superadmin');
INSERT INTO `sys_quartz_job` (`id`, `job_name`, `job_class_name`, `parameter`, `cron_expression`, `is_enabled`,
                              `description`, `sys_code`, `created`, `created_by`, `modified`, `modified_by`)
VALUES ('1785475804470210562', '有参测试', 'com.hb0730.jobs.TestJob1', '{\"key\":\"xxx\",\"value\":\"xxx\"}',
        '0 0/2 * * * ?', b'0', '有参测试', 'DEFAULT', '2024-05-01 09:06:19', 'superadmin', NULL, NULL);
INSERT INTO `sys_quartz_job` (`id`, `job_name`, `job_class_name`, `parameter`, `cron_expression`, `is_enabled`,
                              `description`, `sys_code`, `created`, `created_by`, `modified`, `modified_by`)
VALUES ('1785475946246074370', 'Spring Bean测试', 'testJob3', '', '0 0/2 * * * ?', b'0', 'Spring Bean测试', 'DEFAULT',
        '2024-05-01 09:06:53', 'superadmin', NULL, NULL);
INSERT INTO `sys_quartz_job` (`id`, `job_name`, `job_class_name`, `parameter`, `cron_expression`, `is_enabled`,
                              `description`, `sys_code`, `created`, `created_by`, `modified`, `modified_by`)
VALUES ('1785632158581387266', '关闭已过期公告', 'noticeTimeoutJob', '', '0 0 2 * * ?', b'1',
        '每天凌晨2点关闭超时的公告', 'DEFAULT', '2024-05-01 19:27:37', 'superadmin', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
    `id`          varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'id',
    `name`        varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
    `code`        varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色标识',
    `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci         DEFAULT NULL COMMENT '描述',
    `is_system`   tinyint(1)                                                            DEFAULT '0' COMMENT '是否内置',
    `is_enabled`  tinyint(1)                                                   NOT NULL DEFAULT '1' COMMENT '状态',
    `created`     datetime                                                              DEFAULT NULL COMMENT '创建时间',
    `created_by`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci          DEFAULT NULL COMMENT '创建者',
    `modified`    datetime                                                              DEFAULT NULL COMMENT '修改时间',
    `modified_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci          DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='管理端：角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` (`id`, `name`, `code`, `description`, `is_system`, `is_enabled`, `created`, `created_by`,
                        `modified`, `modified_by`)
VALUES ('1', 'admin', 'admin', '超级管理员', 1, 1, '2024-03-25 09:40:43', 'admin', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`
(
    `role_id`       varchar(32) COLLATE utf8mb4_general_ci NOT NULL,
    `permission_id` varchar(32) COLLATE utf8mb4_general_ci NOT NULL,
    PRIMARY KEY (`role_id`, `permission_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='管理端： 角色权限';

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`)
VALUES ('1', '1');
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`)
VALUES ('1', '10');
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`)
VALUES ('1', '11');
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`)
VALUES ('1', '12');
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`)
VALUES ('1', '13');
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`)
VALUES ('1', '1785218829636894721');
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`)
VALUES ('1', '1785219038316101633');
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`)
VALUES ('1', '1785219103000657921');
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`)
VALUES ('1', '1785219231749013505');
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`)
VALUES ('1', '1785219294940397569');
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`)
VALUES ('1', '1785225857788874754');
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`)
VALUES ('1', '1785226155672539138');
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`)
VALUES ('1', '1785226275357003777');
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`)
VALUES ('1', '1785226339936702465');
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`)
VALUES ('1', '1785226422149255170');
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`)
VALUES ('1', '1785226686633676802');
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`)
VALUES ('1', '1785227063890350082');
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`)
VALUES ('1', '1785227207171969025');
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`)
VALUES ('1', '1785227275484598274');
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`)
VALUES ('1', '1785227334389403649');
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`)
VALUES ('1', '1785227498244083713');
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`)
VALUES ('1', '1785228542034370562');
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`)
VALUES ('1', '1785228667859296258');
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`)
VALUES ('1', '1785228729737863169');
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`)
VALUES ('1', '1785228787493429250');
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`)
VALUES ('1', '1785228851183935490');
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`)
VALUES ('1', '1785228948500176897');
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`)
VALUES ('1', '1785231431528144897');
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`)
VALUES ('1', '1785231706154393601');
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`)
VALUES ('1', '1785243846168895490');
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`)
VALUES ('1', '1785244014243045378');
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`)
VALUES ('1', '1785474078014992385');
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`)
VALUES ('1', '1785474153176920065');
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`)
VALUES ('1', '1785474214451507202');
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`)
VALUES ('1', '1785474310224244738');
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`)
VALUES ('1', '1785474379866468353');
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`)
VALUES ('1', '1785474480643010562');
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`)
VALUES ('1', '1785474547487633409');
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`)
VALUES ('1', '1785474601975836674');
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`)
VALUES ('1', '2');
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`)
VALUES ('1', '3');
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`)
VALUES ('1', '4');
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`)
VALUES ('1', '5');
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`)
VALUES ('1', '6');
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`)
VALUES ('1', '7');
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`)
VALUES ('1', '8');
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`)
VALUES ('1', '9');
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
    `id`                  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT 'id',
    `username`            varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '用户名',
    `nickname`            varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '昵称',
    `password`            varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
    `gender`              tinyint                                                                DEFAULT '0' COMMENT '性别 0 未知 1 男 2 女',
    `email`               varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci           DEFAULT NULL COMMENT '邮箱',
    `phone`               varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci           DEFAULT NULL COMMENT '手机号',
    `avatar`              varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci          DEFAULT NULL COMMENT '头像',
    `last_login_time`     datetime                                                               DEFAULT NULL COMMENT '最后登录时间',
    `last_login_ip`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci           DEFAULT NULL COMMENT '最后登录ip',
    `last_pwd_reset_time` datetime                                                               DEFAULT NULL COMMENT '最后修改密码的时间',
    `description`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci          DEFAULT NULL COMMENT '描述',
    `is_system`           tinyint(1)                                                    NOT NULL DEFAULT '0' COMMENT '是否内置',
    `is_enabled`          tinyint(1)                                                    NOT NULL DEFAULT '1' COMMENT '状态',
    `created`             datetime                                                               DEFAULT NULL COMMENT '创建时间',
    `created_by`          varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci           DEFAULT NULL COMMENT '创建者',
    `modified`            datetime                                                               DEFAULT NULL COMMENT '修改时间',
    `modified_by`         varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci           DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='管理端： 用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` (`id`, `username`, `nickname`, `password`, `gender`, `email`, `phone`, `avatar`,
                        `last_login_time`, `last_login_ip`, `last_pwd_reset_time`, `description`, `is_system`,
                        `is_enabled`, `created`, `created_by`, `modified`, `modified_by`)
VALUES ('1', 'superadmin', '管理员', '$2a$10$bzGz6zZzAxkN0CRjPf0Pb.CetihWFQo8X6n0oKpZxb1vmxBZVJHIC', 0, NULL, NULL,
        NULL, '2024-05-01 19:25:29', NULL, NULL, '租户-超级管理员', 1, 1, '2024-03-23 09:17:46', 'admin', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`
(
    `user_id` varchar(32) COLLATE utf8mb4_general_ci NOT NULL,
    `role_id` varchar(32) COLLATE utf8mb4_general_ci NOT NULL,
    PRIMARY KEY (`user_id`, `role_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='管理端：用户角色';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` (`user_id`, `role_id`)
VALUES ('1', '1');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
