/*
 Source Server         : boot-admin
 Source Server Type    : MySQL
 Source Server Version : 80402 (8.4.2)
 Source Host           : localhost:3306
 Source Schema         : boot-admin

 Target Server Type    : MySQL
 Target Server Version : 80402 (8.4.2)
 File Encoding         : 65001

 Date: 13/06/2025 17:51:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_attachment
-- ----------------------------
DROP TABLE IF EXISTS `sys_attachment`;
CREATE TABLE `sys_attachment`
(
    `id`           varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL,
    `created`      datetime                                                      DEFAULT NULL COMMENT '创建时间',
    `created_by`   varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '创建人',
    `modified`     datetime                                                      DEFAULT NULL COMMENT '更新时间',
    `modified_by`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '更新人',
    `status`       tinyint(1)                                                    DEFAULT NULL COMMENT '状态',
    `display_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件名',
    `media_type`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '文件类型',
    `size`         bigint                                                        DEFAULT NULL COMMENT '大小',
    `permalink`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '预览地址',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='基础-附件';

-- ----------------------------
-- Records of sys_attachment
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`
(
    `id`           varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `created`      datetime                                                      DEFAULT NULL COMMENT '创建时间',
    `created_by`   varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '创建人',
    `modified`     datetime                                                      DEFAULT NULL COMMENT '更新时间',
    `modified_by`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '更新人',
    `status`       tinyint(1)                                                    DEFAULT NULL COMMENT '状态',
    `del_flag`     tinyint(1)                                                    DEFAULT '0' COMMENT '是否删除',
    `dict_name`    varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典名称',
    `dict_code`    varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典编码',
    `dict_type`    varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '数据类型',
    `extra_schema` json                                                          DEFAULT NULL COMMENT '扩展参数',
    `description`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '描述',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='基础-数据字典';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_name`,
                        `dict_code`, `dict_type`, `extra_schema`, `description`)
VALUES ('1844925656477491201', '2024-10-16 10:11:28', 'hb0730', '2024-10-16 10:11:28', 'hb0730', 1, 0, '字典配置值类型',
        'dictValueType', 'STRING', '[
    {
      \"name\": \"color\",
      \"type\": \"COLOR\"
    }
  ]', '基础设施-请勿删除');
INSERT INTO `sys_dict` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_name`,
                        `dict_code`, `dict_type`, `extra_schema`, `description`)
VALUES ('1844931919886237698', '2024-10-12 16:37:12', 'hb0730', '2024-10-16 10:12:04', 'hb0730', 1, 1, '数据字典状态',
        'dictStatus', 'INTEGER', '[
    {
      \"name\": \"color\",
      \"type\": \"COLOR\"
    }
  ]', '基础设施-请勿删除');
INSERT INTO `sys_dict` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_name`,
                        `dict_code`, `dict_type`, `extra_schema`, `description`)
VALUES ('1845053299063595009', '2024-10-12 18:56:54', 'hb0730', '2024-10-12 18:56:54', 'hb0730', 1, 0, '菜单类型',
        'systemMenuType', 'INTEGER', '[
    {
      \"name\": \"color\",
      \"type\": \"COLOR\"
    }
  ]', '基础设施-请勿删除');
INSERT INTO `sys_dict` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_name`,
                        `dict_code`, `dict_type`, `extra_schema`, `description`)
VALUES ('1845056243787964417', '2024-10-12 21:09:32', 'hb0730', '2024-10-12 21:09:32', 'hb0730', 1, 0, '菜单是否隐藏',
        'systemMenuHidden', 'BOOLEAN', '[
    {
      \"name\": \"color\",
      \"type\": \"COLOR\"
    }
  ]', '基础设施-请勿删除');
INSERT INTO `sys_dict` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_name`,
                        `dict_code`, `dict_type`, `extra_schema`, `description`)
VALUES ('1845083181894131713', '2024-10-12 21:09:39', 'hb0730', '2024-10-12 21:09:39', 'hb0730', 1, 0, '页面是否缓存',
        'systemMenuKeepAlive', 'BOOLEAN', '[
    {
      \"name\": \"color\",
      \"type\": \"COLOR\"
    }
  ]', '基础设施-请勿删除');
INSERT INTO `sys_dict` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_name`,
                        `dict_code`, `dict_type`, `extra_schema`, `description`)
VALUES ('1845083886675615746', '2024-10-12 21:09:46', 'hb0730', '2024-10-12 21:09:46', 'hb0730', 1, 0, '标签是否固定',
        'systemMenuAffix', 'BOOLEAN', '[
    {
      \"name\": \"color\",
      \"type\": \"COLOR\"
    }
  ]', '	\n基础设施-请勿删除');
INSERT INTO `sys_dict` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_name`,
                        `dict_code`, `dict_type`, `extra_schema`, `description`)
VALUES ('1845084455054139394', '2024-10-12 21:09:52', 'hb0730', '2024-10-12 21:09:52', 'hb0730', 1, 0, '菜单是否全屏',
        'systemMenuFullScreen', 'BOOLEAN', '[
    {
      \"name\": \"color\",
      \"type\": \"COLOR\"
    }
  ]', '基础设施-请勿删除');
INSERT INTO `sys_dict` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_name`,
                        `dict_code`, `dict_type`, `extra_schema`, `description`)
VALUES ('1845084916922507265', '2024-10-12 20:51:44', 'hb0730', '2024-10-16 10:12:16', 'hb0730', 1, 1, '菜单状态',
        'systemMenuStatus', 'INTEGER', '[
    {
      \"name\": \"color\",
      \"type\": \"COLOR\"
    }
  ]', '基础设施-请勿删除');
INSERT INTO `sys_dict` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_name`,
                        `dict_code`, `dict_type`, `extra_schema`, `description`)
VALUES ('1845278594379116545', '2024-10-13 09:42:04', 'hb0730', '2024-10-13 09:42:04', 'hb0730', 1, 0, '操作日志模块',
        'operatorLogModule', 'STRING', '[]', '基础设施-请勿删除');
INSERT INTO `sys_dict` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_name`,
                        `dict_code`, `dict_type`, `extra_schema`, `description`)
VALUES ('1845278945454944257', '2024-10-13 09:58:13', 'hb0730', '2024-10-13 09:58:13', 'hb0730', 1, 0, '操作风险等级',
        'operatorRiskLevel', 'STRING', '[
    {
      \"name\": \"color\",
      \"type\": \"COLOR\"
    }
  ]', '基础设施-请勿删除');
INSERT INTO `sys_dict` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_name`,
                        `dict_code`, `dict_type`, `extra_schema`, `description`)
VALUES ('1845279278407184385', '2024-10-13 09:43:58', 'hb0730', '2024-10-13 09:43:58', 'hb0730', 1, 0, '日志操作类型',
        'operatorLogType', 'STRING', '[]', '基础设施-请勿删除');
INSERT INTO `sys_dict` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_name`,
                        `dict_code`, `dict_type`, `extra_schema`, `description`)
VALUES ('1845279718423228417', '2024-10-13 09:45:43', 'hb0730', '2024-10-13 09:45:43', 'hb0730', 1, 0, '操作日志结果',
        'operatorLogResult', 'INTEGER', '[
    {
      \"name\": \"color\",
      \"type\": \"COLOR\"
    }
  ]', '基础设施-请勿删除');
INSERT INTO `sys_dict` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_name`,
                        `dict_code`, `dict_type`, `extra_schema`, `description`)
VALUES ('1845646818677460994', '2024-10-14 10:04:33', 'hb0730', '2024-10-14 10:04:33', 'hb0730', 1, 0, '性别',
        'systemGender', 'INTEGER', '[]', '基础设施-请勿删除');
INSERT INTO `sys_dict` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_name`,
                        `dict_code`, `dict_type`, `extra_schema`, `description`)
VALUES ('1845654648541265921', '2024-10-14 10:35:45', 'hb0730', '2024-10-14 10:35:45', 'hb0730', 1, 0, '用户状态',
        'systemUserStatus', 'BOOLEAN', '[
    {
      \"name\": \"color\",
      \"type\": \"COLOR\"
    }
  ]', '基础设施-请勿删除');
INSERT INTO `sys_dict` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_name`,
                        `dict_code`, `dict_type`, `extra_schema`, `description`)
VALUES ('1846365684927852545', '2024-10-16 09:45:43', 'hb0730', '2024-10-16 09:45:43', 'hb0730', 1, 0, '消息类型',
        'messageType', 'STRING', '[]', '基础设施-请勿删除');
INSERT INTO `sys_dict` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_name`,
                        `dict_code`, `dict_type`, `extra_schema`, `description`)
VALUES ('1846370743392694273', '2024-10-16 10:01:15', 'hb0730', '2024-10-16 10:01:15', 'hb0730', 1, 0, '系统通用状态',
        'systemStatus', 'BOOLEAN', '[
    {
      \"name\": \"color\",
      \"type\": \"COLOR\"
    }
  ]', '基础设施-请勿删除');
INSERT INTO `sys_dict` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_name`,
                        `dict_code`, `dict_type`, `extra_schema`, `description`)
VALUES ('1847172232990240769', '2024-10-18 15:06:03', 'hb0730', '2024-10-18 15:06:03', 'hb0730', 1, 0, '消息推送状态',
        'msgSendStatus', 'INTEGER', '[
    {
      \"name\": \"icon\",
      \"type\": \"STRING\"
    }
  ]', '基础设施-请勿删除');
INSERT INTO `sys_dict` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_name`,
                        `dict_code`, `dict_type`, `extra_schema`, `description`)
VALUES ('1847468609909673985', '2024-10-19 10:43:46', 'hb0730', '2025-06-13 14:37:48', 'admin', 1, 1, '系统应用',
        'systemApp', 'STRING', '[]', '基础设施-请勿删除');
INSERT INTO `sys_dict` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_name`,
                        `dict_code`, `dict_type`, `extra_schema`, `description`)
VALUES ('1848584032847724546', '2024-10-22 12:36:03', 'hb0730', '2025-06-13 14:37:37', 'admin', 1, 1, '语录类型',
        'gorokuType', 'STRING', '[]', '语录系统');
INSERT INTO `sys_dict` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_name`,
                        `dict_code`, `dict_type`, `extra_schema`, `description`)
VALUES ('1848585144745132034', '2024-10-22 13:38:31', 'hb0730', '2025-06-13 14:37:45', 'admin', 1, 1, '审核状态',
        'systemAuditStatus', 'INTEGER', '[
    {
      \"name\": \"color\",
      \"type\": \"COLOR\"
    }
  ]', '基础设施-请勿删除');
INSERT INTO `sys_dict` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_name`,
                        `dict_code`, `dict_type`, `extra_schema`, `description`)
VALUES ('1867731068582989826', '2024-12-14 08:39:22', 'hb0730', '2025-06-13 14:37:41', 'admin', 1, 1, '三方登陆类型',
        'systemSocialType', 'STRING', '[]', '基础设施-请勿删除');
INSERT INTO `sys_dict` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_name`,
                        `dict_code`, `dict_type`, `extra_schema`, `description`)
VALUES ('1870421100301193218', '2024-12-21 18:48:36', 'hb0730', '2024-12-21 18:49:27', 'hb0730', 1, 1, '是否隐藏',
        'systemHidden', 'BOOLEAN', '[
    {
      \"name\": \"color\",
      \"type\": \"COLOR\"
    }
  ]', '基础设施-请勿删除');
INSERT INTO `sys_dict` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_name`,
                        `dict_code`, `dict_type`, `extra_schema`, `description`)
VALUES ('1870421514971058178', '2024-12-21 18:50:15', 'hb0730', '2024-12-21 18:50:15', 'hb0730', 1, 0, '是否推荐',
        'SystemRecommend', 'BOOLEAN', '[
    {
      \"name\": \"color\",
      \"type\": \"COLOR\"
    }
  ]', '基础设施-请勿删除');
INSERT INTO `sys_dict` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_name`,
                        `dict_code`, `dict_type`, `extra_schema`, `description`)
VALUES ('1870421727047651329', '2024-12-21 18:51:05', 'hb0730', '2024-12-21 18:51:05', 'hb0730', 1, 0, '是否废弃',
        'systemDeprecated', 'BOOLEAN', '[
    {
      \"name\": \"color\",
      \"type\": \"COLOR\"
    }
  ]', '基础设施-请勿删除');
INSERT INTO `sys_dict` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_name`,
                        `dict_code`, `dict_type`, `extra_schema`, `description`)
VALUES ('1870422175720738818', '2024-12-21 18:52:52', 'hb0730', '2024-12-21 18:52:52', 'hb0730', 1, 0, '是否隐藏',
        'systemHidden', 'BOOLEAN', '[
    {
      \"name\": \"color\",
      \"type\": \"COLOR\"
    }
  ]', '基础设施-请勿删除');
INSERT INTO `sys_dict` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_name`,
                        `dict_code`, `dict_type`, `extra_schema`, `description`)
VALUES ('1872516036131594241', '2024-12-27 13:33:07', 'hb0730', '2024-12-27 13:33:07', 'hb0730', 1, 0, '任务类型',
        'systemTaskType', 'STRING', '[]', '基础设施-请勿删除');
INSERT INTO `sys_dict` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_name`,
                        `dict_code`, `dict_type`, `extra_schema`, `description`)
VALUES ('1872516327954489346', '2024-12-27 13:34:17', 'hb0730', '2024-12-27 13:34:17', 'hb0730', 1, 0, '任务分类',
        'systemTaskCategory', 'STRING', '[]', '基础设施-请勿删除');
INSERT INTO `sys_dict` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_name`,
                        `dict_code`, `dict_type`, `extra_schema`, `description`)
VALUES ('1872517068391751681', '2024-12-27 13:37:13', 'hb0730', '2024-12-27 13:37:13', 'hb0730', 1, 0, '任务状态',
        'systemTaskState', 'INTEGER', '[
    {
      \"name\": \"color\",
      \"type\": \"COLOR\"
    }
  ]', '基础设施-请勿删除');
INSERT INTO `sys_dict` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_name`,
                        `dict_code`, `dict_type`, `extra_schema`, `description`)
VALUES ('1876988656713539585', '2025-01-08 21:45:43', 'hb0730', '2025-01-08 21:45:43', 'hb0730', 1, 0, '读取状态',
        'systemReadStatus', 'INTEGER', '[
    {
      \"name\": \"color\",
      \"type\": \"COLOR\"
    }
  ]', '基础设施-请勿删除');
INSERT INTO `sys_dict` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_name`,
                        `dict_code`, `dict_type`, `extra_schema`, `description`)
VALUES ('1887751597788299266', '2025-02-07 14:33:48', 'hb0730', '2025-02-07 14:35:47', 'hb0730', 1, 0, '逻辑类型',
        'systemLogicType', 'BOOLEAN', '[
    {
      \"name\": \"color\",
      \"type\": \"COLOR\"
    }
  ]', '基础设施-请勿删除');
INSERT INTO `sys_dict` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_name`,
                        `dict_code`, `dict_type`, `extra_schema`, `description`)
VALUES ('1888546734328033282', '2025-02-09 19:13:24', 'hb0730', '2025-06-13 14:37:12', 'admin', 1, 1, '简历用途',
        'resumeUse', 'INTEGER', '[]', '简历模块');
INSERT INTO `sys_dict` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_name`,
                        `dict_code`, `dict_type`, `extra_schema`, `description`)
VALUES ('1888546961747390466', '2025-02-09 19:14:18', 'hb0730', '2025-06-13 14:37:09', 'admin', 1, 1, '简历行业',
        'resumeIndustry', 'INTEGER', '[]', '简历模块');
INSERT INTO `sys_dict` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_name`,
                        `dict_code`, `dict_type`, `extra_schema`, `description`)
VALUES ('1888547212428357633', '2025-02-09 19:15:18', 'hb0730', '2025-06-13 14:37:05', 'admin', 1, 1, '简历岗位',
        'resumePost', 'INTEGER', '[
    {
      \"name\": \"type\",
      \"type\": \"STRING\"
    }
  ]', '简历模块');
COMMIT;

-- ----------------------------
-- Table structure for sys_dict_item
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_item`;
CREATE TABLE `sys_dict_item`
(
    `id`          varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `created`     datetime                                                      DEFAULT NULL COMMENT '创建时间',
    `created_by`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '创建人',
    `modified`    datetime                                                      DEFAULT NULL COMMENT '更新时间',
    `modified_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '更新人',
    `status`      tinyint(1)                                                    DEFAULT NULL COMMENT '状态',
    `del_flag`    tinyint(1)                                                    DEFAULT '0' COMMENT '是否删除',
    `dict_id`     varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '字典ID',
    `item_text`   varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '字典项名称',
    `item_value`  varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '字典项值',
    `extra`       json                                                          DEFAULT NULL COMMENT '额外参数',
    `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '描述',
    `sort`        int                                                           DEFAULT NULL COMMENT '排序',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='基础-数据字典项';

-- ----------------------------
-- Records of sys_dict_item
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1844953003708731393', '2024-10-12 16:37:31', 'hb0730', '2024-10-12 16:37:31', 'hb0730', 1, 0,
        '1844931919886237698', '启用', '1', '{
    \"color\": \"primary\"
  }', NULL, 1);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1844953885984759810', '2024-10-12 16:37:58', 'hb0730', '2024-10-12 16:37:58', 'hb0730', 1, 0,
        '1844931919886237698', '停用', '0', '{
    \"color\": \"warning\"
  }', NULL, 2);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1844955608941928449', '2024-10-14 16:12:16', 'hb0730', '2024-10-14 16:12:16', 'hb0730', 1, 0,
        '1844925656477491201', '字符串', 'STRING', '{
    \"color\": \"primary\"
  }', NULL, 1);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1844955669184716802', '2024-10-12 16:31:23', 'hb0730', '2024-10-12 16:31:23', 'hb0730', 1, 0,
        '1844925656477491201', '整数', 'INTEGER', '{
    \"color\": \"primary\"
  }', NULL, 2);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1844955758884102146', '2024-10-12 16:31:42', 'hb0730', '2024-10-12 16:31:42', 'hb0730', 1, 0,
        '1844925656477491201', '小数', 'DECIMAL', '{
    \"color\": \"purple\"
  }', NULL, 3);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1844955818850066433', '2024-10-12 16:31:52', 'hb0730', '2024-10-12 16:31:52', 'hb0730', 1, 0,
        '1844925656477491201', '布尔值', 'BOOLEAN', '{
    \"color\": \"pinkpurple\"
  }', NULL, 4);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1844955898676060161', '2024-10-12 16:32:11', 'hb0730', '2024-10-12 16:32:11', 'hb0730', 1, 0,
        '1844925656477491201', '颜色', 'COLOR', '{
    \"color\": \"magenta\"
  }', '', 5);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1845053508606828545', '2024-10-12 18:48:20', 'hb0730', '2024-10-12 18:48:20', 'hb0730', 1, 0,
        '1845053299063595009', '菜单', '0', '{
    \"color\": \"primary\"
  }', NULL, 1);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1845053664471359489', '2024-10-12 18:48:24', 'hb0730', '2024-10-12 18:48:24', 'hb0730', 1, 0,
        '1845053299063595009', 'iframe', '1', '{
    \"color\": \"warning\"
  }', NULL, 2);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1845053836215525378', '2024-10-12 18:48:16', 'hb0730', '2024-10-12 18:48:16', 'hb0730', 1, 0,
        '1845053299063595009', '外链', '2', '{
    \"color\": \"danger\"
  }', NULL, 3);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1845054066021441537', '2024-10-12 18:49:03', 'hb0730', '2024-10-12 18:49:03', 'hb0730', 1, 0,
        '1845053299063595009', '按钮', '3', '{
    \"color\": \"info\"
  }', NULL, 4);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1845056351615131650', '2024-10-12 21:10:08', 'hb0730', '2024-10-12 21:10:08', 'hb0730', 1, 0,
        '1845056243787964417', '隐藏', 'true', '{
    \"color\": \"danger\"
  }', NULL, 2);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1845056407495843842', '2024-10-12 21:10:04', 'hb0730', '2024-10-12 21:10:04', 'hb0730', 1, 0,
        '1845056243787964417', '显示', 'false', '{
    \"color\": \"primary\"
  }', NULL, 1);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1845083472286769153', '2024-10-12 21:10:17', 'hb0730', '2024-10-12 21:10:17', 'hb0730', 1, 0,
        '1845083181894131713', '缓存', 'true', '{
    \"color\": \"primary\"
  }', NULL, 1);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1845083523151093762', '2024-10-12 21:10:21', 'hb0730', '2024-10-12 21:10:21', 'hb0730', 1, 0,
        '1845083181894131713', '不缓存', 'false', '{
    \"color\": \"info\"
  }', NULL, 2);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1845084043655831553', '2024-10-12 21:10:29', 'hb0730', '2024-10-12 21:10:29', 'hb0730', 1, 0,
        '1845083886675615746', '固定', 'true', '{
    \"color\": \"primary\"
  }', NULL, 1);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1845084096206266369', '2024-10-12 21:10:33', 'hb0730', '2024-10-12 21:10:33', 'hb0730', 1, 0,
        '1845083886675615746', '不固定', 'false', '{
    \"color\": \"info\"
  }', NULL, 2);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1845084586730119170', '2024-10-12 21:10:39', 'hb0730', '2024-10-12 21:10:39', 'hb0730', 1, 0,
        '1845084455054139394', '全屏', 'true', '{
    \"color\": \"primary\"
  }', NULL, 1);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1845084627737829377', '2024-10-12 21:10:42', 'hb0730', '2024-10-12 21:10:42', 'hb0730', 1, 0,
        '1845084455054139394', '不全屏', 'false', '{
    \"color\": \"info\"
  }', NULL, 2);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1845085042848096257', '2024-10-12 20:52:09', 'hb0730', '2024-10-12 20:52:09', 'hb0730', 1, 0,
        '1845084916922507265', '可用', '1', '{
    \"color\": \"primary\"
  }', NULL, 1);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1845085114419699713', '2024-10-12 20:52:26', 'hb0730', '2024-10-12 20:52:26', 'hb0730', 1, 0,
        '1845084916922507265', '停用', '0', '{
    \"color\": \"danger\"
  }', NULL, 2);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1845282914080206850', '2024-10-13 09:58:25', 'hb0730', '2024-10-13 09:58:25', 'hb0730', 1, 0,
        '1845278945454944257', '低风险', 'L', '{
    \"color\": \"primary\"
  }', NULL, 1);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1845282980639617026', '2024-10-13 09:58:41', 'hb0730', '2024-10-13 09:58:41', 'hb0730', 1, 0,
        '1845278945454944257', '中风险', 'M', '{
    \"color\": \"warning\"
  }', NULL, 2);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1845283072121581570', '2024-10-13 09:59:03', 'hb0730', '2024-10-13 09:59:03', 'hb0730', 1, 0,
        '1845278945454944257', '高风险', 'H', '{
    \"color\": \"danger\"
  }', NULL, 3);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1845283334743732226', '2024-10-13 10:05:21', 'hb0730', '2024-10-13 10:05:21', 'hb0730', 1, 0,
        '1845278594379116545', '身份认证', 'basic:authentication', NULL, NULL, 1);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1845283488641134593', '2024-10-13 10:01:20', 'hb0730', '2024-10-13 10:01:20', 'hb0730', 1, 0,
        '1845278594379116545', '字典配置值', 'basic:dict-item', NULL, NULL, 3);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1845283564490928129', '2024-10-13 10:01:25', 'hb0730', '2024-10-13 10:01:25', 'hb0730', 1, 0,
        '1845278594379116545', '字典配置项', 'basic:dict', NULL, NULL, 2);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1845284968806821890', '2024-10-13 10:06:35', 'hb0730', '2024-10-13 10:06:35', 'hb0730', 1, 0,
        '1845279278407184385', '登录系统', 'authentication:login', NULL, NULL, 1);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1845285077170860033', '2024-10-13 10:07:01', 'hb0730', '2024-10-13 10:07:01', 'hb0730', 1, 0,
        '1845279278407184385', '登出系统', 'authentication:logout', NULL, NULL, 2);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1845285167230955522', '2024-10-13 10:07:22', 'hb0730', '2024-10-13 10:07:22', 'hb0730', 1, 0,
        '1845279278407184385', '修改密码', 'authentication:update-password', NULL, NULL, 3);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1845285394650312706', '2024-10-13 10:08:16', 'hb0730', '2024-10-13 10:08:16', 'hb0730', 1, 0,
        '1845279278407184385', '创建字典配置项', 'dict:create', NULL, NULL, 1);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1845285499436609537', '2024-10-13 10:08:41', 'hb0730', '2024-10-13 10:08:41', 'hb0730', 1, 0,
        '1845279278407184385', '更新字典配置项', 'dict:edit', NULL, NULL, 2);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1845285574200078337', '2024-10-13 10:08:59', 'hb0730', '2024-10-13 10:08:59', 'hb0730', 1, 0,
        '1845279278407184385', '删除字典配置项', 'dict:delete', NULL, NULL, 3);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1845285644643414017', '2024-10-13 10:09:16', 'hb0730', '2024-10-13 10:09:16', 'hb0730', 1, 0,
        '1845279278407184385', '创建字典配置值', 'dict-item:create', NULL, NULL, 1);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1845285702679998465', '2024-10-13 10:09:30', 'hb0730', '2024-10-13 10:09:30', 'hb0730', 1, 0,
        '1845279278407184385', '更新字典配置值', 'dict-item:edit', NULL, NULL, 2);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1845285807214637057', '2024-10-13 10:09:55', 'hb0730', '2024-10-13 10:09:55', 'hb0730', 1, 0,
        '1845279278407184385', '删除字典配置值', 'dict-item:delete', NULL, NULL, 3);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1845286057471979521', '2024-10-13 10:10:55', 'hb0730', '2024-10-13 10:10:55', 'hb0730', 1, 0,
        '1845279718423228417', '成功', '1', '{
    \"color\": \"primary\"
  }', NULL, 1);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1845286125193211906', '2024-10-13 10:11:11', 'hb0730', '2024-10-13 10:11:11', 'hb0730', 1, 0,
        '1845279718423228417', '失败', '0', '{
    \"color\": \"danger\"
  }', NULL, 2);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1845646889120796674', '2024-10-14 10:04:43', 'hb0730', '2024-10-14 10:04:43', 'hb0730', 1, 0,
        '1845646818677460994', '未知', '0', NULL, NULL, 3);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1845646950974197762', '2024-10-14 10:04:58', 'hb0730', '2024-10-14 10:04:58', 'hb0730', 1, 0,
        '1845646818677460994', '男', '1', NULL, NULL, 1);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1845646982490198018', '2024-10-14 10:05:06', 'hb0730', '2024-10-14 10:05:06', 'hb0730', 1, 0,
        '1845646818677460994', '女', '2', NULL, NULL, 2);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1845654878519148545', '2024-10-14 10:36:28', 'hb0730', '2024-10-14 10:36:28', 'hb0730', 1, 0,
        '1845654648541265921', '启用', 'true', '{
    \"color\": \"primary\"
  }', NULL, 1);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1845654944516521985', '2024-10-14 10:36:44', 'hb0730', '2024-10-14 10:36:44', 'hb0730', 1, 0,
        '1845654648541265921', '禁用', 'false', '{
    \"color\": \"danger\"
  }', NULL, 2);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1845774300256706561', '2024-10-14 18:31:01', 'hb0730', '2024-10-14 18:31:01', 'hb0730', 1, 0,
        '1845278594379116545', '系统用户', 'basic:user', NULL, NULL, 4);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1845774402144739329', '2024-10-14 18:31:25', 'hb0730', '2024-10-14 18:31:25', 'hb0730', 1, 0,
        '1845279278407184385', '新增用户', 'sys:user:create', NULL, NULL, 1);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1845774456943321090', '2024-10-14 18:31:38', 'hb0730', '2024-10-14 18:31:38', 'hb0730', 1, 0,
        '1845279278407184385', '修改用户', 'sys:user:edit', NULL, NULL, 2);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1845774525272727553', '2024-10-14 18:31:54', 'hb0730', '2024-10-14 18:31:54', 'hb0730', 1, 0,
        '1845279278407184385', '删除用户', 'sys:user:delete', NULL, NULL, 3);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1846009926864441346', '2024-10-15 10:07:18', 'hb0730', '2024-10-15 10:07:18', 'hb0730', 1, 0,
        '1845278594379116545', '系统权限', 'basic:permission', NULL, NULL, 5);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1846009997676875777', '2024-10-15 10:07:35', 'hb0730', '2024-10-15 10:07:35', 'hb0730', 1, 0,
        '1845278594379116545', '系统角色', 'basic:role', NULL, NULL, 6);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1846010101100023809', '2024-10-15 10:08:00', 'hb0730', '2024-10-15 10:08:00', 'hb0730', 1, 0,
        '1845279278407184385', '新增系统权限', 'sys:permission:create', NULL, NULL, 1);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1846010148470493185', '2024-10-15 10:08:11', 'hb0730', '2024-10-15 10:08:11', 'hb0730', 1, 0,
        '1845279278407184385', '更新系统权限', 'sys:permission:edit', NULL, NULL, 2);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1846010203336183810', '2024-10-15 10:08:24', 'hb0730', '2024-10-15 10:08:24', 'hb0730', 1, 0,
        '1845279278407184385', '删除系统权限', 'sys:permission:delete', NULL, NULL, 3);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1846010319023476737', '2024-10-15 10:08:52', 'hb0730', '2024-10-15 10:08:52', 'hb0730', 1, 0,
        '1845279278407184385', '创建角色', 'sys:role:create', NULL, NULL, 1);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1846010364808499201', '2024-10-15 10:09:03', 'hb0730', '2024-10-15 10:09:03', 'hb0730', 1, 0,
        '1845279278407184385', '更新角色', 'sys:role:edit', NULL, NULL, 2);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1846010402842447874', '2024-10-15 10:09:12', 'hb0730', '2024-10-15 10:09:12', 'hb0730', 1, 0,
        '1845279278407184385', '删除角色', 'sys:role:delete', NULL, NULL, 3);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1846010478390251521', '2024-10-15 10:09:30', 'hb0730', '2024-10-15 10:09:30', 'hb0730', 1, 0,
        '1845279278407184385', '更新角色权限', 'sys:role:grant', NULL, NULL, 4);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1846067890493153281', '2024-10-15 13:57:38', 'hb0730', '2024-10-15 13:57:38', 'hb0730', 1, 0,
        '1845279278407184385', '授权用户角色', 'sys:user:grant:role', NULL, NULL, 4);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1846364707436949506', '2024-10-16 09:37:05', 'hb0730', '2024-10-16 09:37:05', 'hb0730', 1, 0,
        '1845278594379116545', '消息模板', 'basic:message:template', NULL, NULL, 7);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1846364840157310977', '2024-10-16 09:37:36', 'hb0730', '2024-10-16 09:37:36', 'hb0730', 1, 0,
        '1845279278407184385', '创建消息模板', 'sys:message:template:add', NULL, NULL, 1);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1846364895564066818', '2024-10-16 09:37:50', 'hb0730', '2024-10-16 09:37:50', 'hb0730', 1, 0,
        '1845279278407184385', '更新消息模板', 'sys:message:template:edit', NULL, NULL, 2);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1846364946239647746', '2024-10-16 09:38:02', 'hb0730', '2024-10-16 09:38:02', 'hb0730', 1, 0,
        '1845279278407184385', '删除消息模板', 'sys:message:template:delete', NULL, NULL, 3);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1846365772743995394', '2024-10-16 09:41:19', 'hb0730', '2024-10-16 09:41:19', 'hb0730', 1, 0,
        '1846365684927852545', '短信', 'SMS', NULL, NULL, 1);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1846365810383679489', '2024-10-16 09:41:28', 'hb0730', '2024-10-16 09:41:28', 'hb0730', 1, 0,
        '1846365684927852545', '邮件', 'EMAIL', NULL, NULL, 2);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1846371284197863426', '2024-10-16 10:03:13', 'hb0730', '2024-10-16 10:03:13', 'hb0730', 1, 0,
        '1846370743392694273', '启用', 'true', '{
    \"color\": \"primary\"
  }', NULL, 1);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1846371355706552322', '2024-10-16 10:03:30', 'hb0730', '2024-10-16 10:03:30', 'hb0730', 1, 0,
        '1846370743392694273', '禁用', 'false', '{
    \"color\": \"danger\"
  }', NULL, 1);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1847172436732751874', '2024-10-18 15:06:42', 'hb0730', '2024-10-18 15:06:42', 'hb0730', 1, 0,
        '1847172232990240769', '不再推送', '-1', '{
    \"icon\": \"info\"
  }', NULL, 1);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1847172562368933890', '2024-10-18 15:07:12', 'hb0730', '2024-10-18 15:07:12', 'hb0730', 1, 0,
        '1847172232990240769', '未推送', '0', '{
    \"icon\": \"primary\"
  }', NULL, 1);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1847172645156106242', '2024-10-18 15:07:32', 'hb0730', '2024-10-18 15:07:32', 'hb0730', 1, 0,
        '1847172232990240769', '推送成功', '1', '{
    \"icon\": \"success\"
  }', NULL, 1);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1847172714286624770', '2024-10-18 15:07:49', 'hb0730', '2024-10-18 15:07:49', 'hb0730', 1, 0,
        '1847172232990240769', '推送失败', '2', '{
    \"icon\": \"warning\"
  }', NULL, 1);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1847468913770221570', '2024-10-19 10:44:48', 'hb0730', '2024-10-19 10:44:48', 'hb0730', 1, 1,
        '1847468609909673985', 'zoom-p1-job', 'zoom-p1-job', NULL, NULL, 2);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1847468952643031042', '2024-10-19 10:44:57', 'hb0730', '2024-10-19 10:44:57', 'hb0730', 1, 1,
        '1847468609909673985', 'zoom-p1', 'zoom-p1', NULL, NULL, 1);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1848584131124461570', '2024-10-22 12:36:17', 'hb0730', '2024-10-22 12:36:17', 'hb0730', 1, 1,
        '1848584032847724546', '动漫', 'a', NULL, NULL, 1);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1848584187470741505', '2024-10-22 12:36:30', 'hb0730', '2024-10-22 12:36:30', 'hb0730', 1, 1,
        '1848584032847724546', '漫画', 'b', NULL, NULL, 2);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1848584237995327489', '2024-10-22 12:36:42', 'hb0730', '2024-10-22 12:36:42', 'hb0730', 1, 1,
        '1848584032847724546', '游戏', 'c', NULL, NULL, 3);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1848584315833221122', '2024-10-22 12:37:01', 'hb0730', '2024-10-22 12:37:01', 'hb0730', 1, 1,
        '1848584032847724546', '文学', 'd', NULL, NULL, 4);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1848584359277821954', '2024-10-22 12:37:11', 'hb0730', '2024-10-22 12:37:11', 'hb0730', 1, 1,
        '1848584032847724546', '原创', 'e', NULL, NULL, 5);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1848584415221448705', '2024-10-22 12:37:24', 'hb0730', '2024-10-22 12:37:24', 'hb0730', 1, 1,
        '1848584032847724546', '来自网络', 'f', NULL, NULL, 6);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1848584473119621122', '2024-10-22 12:37:38', 'hb0730', '2024-10-22 12:37:38', 'hb0730', 1, 1,
        '1848584032847724546', '其他', 'g', NULL, NULL, 7);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1848584541272866817', '2024-10-22 12:37:54', 'hb0730', '2024-10-22 12:37:54', 'hb0730', 1, 1,
        '1848584032847724546', '影视', 'h', NULL, NULL, 8);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1848584588995657729', '2024-10-22 12:38:06', 'hb0730', '2024-10-22 12:38:06', 'hb0730', 1, 1,
        '1848584032847724546', '诗词', 'i', NULL, NULL, 9);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1848584653529219073', '2024-10-22 12:38:21', 'hb0730', '2024-10-22 12:38:21', 'hb0730', 1, 1,
        '1848584032847724546', '网易云', 'j', NULL, NULL, 10);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1848584719681781762', '2024-10-22 12:38:37', 'hb0730', '2024-10-22 12:38:37', 'hb0730', 1, 1,
        '1848584032847724546', '哲学', 'k', NULL, NULL, 11);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1848584788258652162', '2024-10-22 12:38:53', 'hb0730', '2024-10-22 12:38:53', 'hb0730', 1, 1,
        '1848584032847724546', '抖机灵', 'l', NULL, NULL, 12);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1848585234482266113', '2024-10-22 12:40:40', 'hb0730', '2024-10-22 12:40:40', 'hb0730', 1, 1,
        '1848585144745132034', '未审核', '-1', '{
    \"color\": \"info\"
  }', NULL, 1);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1848585380737646594', '2024-10-22 12:41:14', 'hb0730', '2024-10-22 12:41:14', 'hb0730', 1, 1,
        '1848585144745132034', '已审核', '1', '{
    \"color\": \"success\"
  }', NULL, 2);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1848585495976148993', '2024-10-22 12:41:42', 'hb0730', '2024-10-22 12:41:42', 'hb0730', 1, 1,
        '1848585144745132034', '审核未通过', '0', '{
    \"color\": \"warning\"
  }', NULL, 3);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1848640967756312578', '2024-10-22 16:22:07', 'hb0730', '2024-10-22 16:22:07', 'hb0730', 1, 0,
        '1845278594379116545', '语录系统', 'goroku', NULL, NULL, 8);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1848641219695570946', '2024-10-22 16:23:08', 'hb0730', '2024-10-22 16:24:20', 'hb0730', 1, 1,
        '1845279278407184385', '新增语录', 'goroku:add', NULL, NULL, 1);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1848641264260050945', '2024-10-22 16:23:18', 'hb0730', '2024-10-22 16:23:18', 'hb0730', 1, 0,
        '1845279278407184385', '更新语录信息', 'goroku:edit', NULL, NULL, 2);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1848641323663978497', '2024-10-22 16:23:32', 'hb0730', '2024-10-22 16:23:32', 'hb0730', 1, 0,
        '1845279278407184385', '删除语录信息', 'goroku:delete', NULL, NULL, 3);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1848641477179699202', '2024-10-22 16:24:09', 'hb0730', '2024-10-22 16:24:09', 'hb0730', 1, 0,
        '1845279278407184385', '审核语录信息', 'goroku:audit', NULL, NULL, 4);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1848641554707214337', '2024-10-22 16:24:27', 'hb0730', '2024-10-22 16:24:27', 'hb0730', 1, 0,
        '1845279278407184385', '新增语录信息', 'goroku:add', NULL, NULL, 1);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1852990059835940865', '2024-11-03 16:23:52', 'hb0730', '2024-11-03 16:23:52', 'hb0730', 1, 0,
        '1845278594379116545', '开放接口', 'basic:open:api', NULL, NULL, 9);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1852990200902967298', '2024-11-03 16:24:25', 'hb0730', '2024-11-03 16:24:25', 'hb0730', 1, 0,
        '1845279278407184385', '创建开放接口', 'open:api:create', NULL, NULL, 1);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1852990274835963906', '2024-11-03 16:24:43', 'hb0730', '2024-11-03 16:24:43', 'hb0730', 1, 0,
        '1845279278407184385', '更新开放接口', 'open:api:edit', NULL, NULL, 2);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1852990338996232193', '2024-11-03 16:24:58', 'hb0730', '2024-11-03 16:24:58', 'hb0730', 1, 0,
        '1845279278407184385', '删除开放接口', 'open:api:delete', NULL, NULL, 3);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1852990481220886529', '2024-11-03 16:25:32', 'hb0730', '2024-11-03 16:25:32', 'hb0730', 1, 0,
        '1845279278407184385', '角色分配开放接口', 'sys:role:open_api:grant', NULL, NULL, 5);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1853301563919499265', '2024-11-04 13:01:40', 'hb0730', '2024-11-04 13:01:40', 'hb0730', 1, 0,
        '1845279278407184385', '创建个人令牌', 'authentication:create-access-token', NULL, NULL, 4);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1853301639366639617', '2024-11-04 13:01:58', 'hb0730', '2024-11-04 13:01:58', 'hb0730', 1, 0,
        '1845279278407184385', '撤销个人令牌', 'authentication:cancel-access-token', NULL, NULL, 5);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1853301708480380930', '2024-11-04 13:02:15', 'hb0730', '2024-11-04 13:02:15', 'hb0730', 1, 0,
        '1845279278407184385', '恢复个人令牌', 'authentication:restore-access-token', NULL, NULL, 6);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1853301762054225922', '2024-11-04 13:02:27', 'hb0730', '2024-11-04 13:02:27', 'hb0730', 1, 0,
        '1845279278407184385', '删除个人令牌', 'authentication:delete-access-token', NULL, NULL, 7);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1867731268068282369', '2024-12-14 08:40:10', 'hb0730', '2024-12-14 08:40:10', 'hb0730', 1, 1,
        '1867731068582989826', 'Github', 'Github', NULL, 'Github登陆', 1);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1869760391225606146', '2024-12-19 23:03:10', 'hb0730', '2024-12-19 23:03:10', 'hb0730', 1, 0,
        '1845278594379116545', '定时任务', 'basic:quartz', NULL, NULL, 10);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1869760478970445826', '2024-12-19 23:03:31', 'hb0730', '2024-12-19 23:03:31', 'hb0730', 1, 0,
        '1845279278407184385', '新增定时任务', 'sys:quartz:add', NULL, NULL, 1);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1869760541394272257', '2024-12-19 23:03:46', 'hb0730', '2024-12-19 23:03:46', 'hb0730', 1, 0,
        '1845279278407184385', '更新定时任务', 'sys:quartz:edit', NULL, NULL, 2);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1869760610369601537', '2024-12-19 23:04:03', 'hb0730', '2024-12-19 23:04:03', 'hb0730', 1, 0,
        '1845279278407184385', '删除定时任务', 'sys:quartz:delete', NULL, NULL, 3);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1869760683686035457', '2024-12-19 23:04:20', 'hb0730', '2024-12-19 23:04:20', 'hb0730', 1, 0,
        '1845279278407184385', '操作任务(暂停/启动/运行)', 'sys:quartz:operator', NULL, NULL, 4);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1869761347124264961', '2024-12-19 23:06:58', 'hb0730', '2024-12-19 23:06:58', 'hb0730', 1, 0,
        '1845279278407184385', '更新用户设置', 'authentication:update-user-settings', NULL, NULL, 8);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1869762309796749313', '2024-12-19 23:10:48', 'hb0730', '2024-12-19 23:10:48', 'hb0730', 1, 0,
        '1845279278407184385', '社交账号绑定', 'authentication:social-bind', NULL, NULL, 9);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1869762384841236482', '2024-12-19 23:11:06', 'hb0730', '2024-12-19 23:11:06', 'hb0730', 1, 0,
        '1845279278407184385', '社交账号解绑', 'authentication:social-unbind', NULL, NULL, 10);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1870032791783616514', '2024-12-20 17:05:36', 'hb0730', '2024-12-22 16:36:23', 'hb0730', 1, 1,
        '1845278594379116545', '导航标签', 'nav:tag', NULL, NULL, 11);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1870032903272411137', '2024-12-20 17:06:02', 'hb0730', '2024-12-20 17:06:02', 'hb0730', 1, 0,
        '1845279278407184385', '新增导航标签', 'nav:tag:save', NULL, NULL, 1);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1870032977788416002', '2024-12-20 17:06:20', 'hb0730', '2024-12-20 17:06:20', 'hb0730', 1, 0,
        '1845279278407184385', '修改导航标签', 'nav:tag:update', NULL, NULL, 2);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1870033037590802434', '2024-12-20 17:06:34', 'hb0730', '2024-12-20 17:06:34', 'hb0730', 1, 0,
        '1845279278407184385', '删除导航标签', 'nav:tag:delete', NULL, NULL, 3);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1870129049634398210', '2024-12-20 23:28:05', 'hb0730', '2024-12-22 16:36:21', 'hb0730', 1, 1,
        '1845278594379116545', '导航分类', 'nav:category', NULL, NULL, 12);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1870129173798379521', '2024-12-20 23:28:35', 'hb0730', '2024-12-20 23:28:35', 'hb0730', 1, 0,
        '1845279278407184385', '新增导航分类', 'nav:category:save', NULL, NULL, 1);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1870129281227087874', '2024-12-20 23:29:01', 'hb0730', '2024-12-20 23:29:01', 'hb0730', 1, 0,
        '1845279278407184385', '修改导航分类', 'nav:category:update', NULL, NULL, 2);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1870129331730702338', '2024-12-20 23:29:13', 'hb0730', '2024-12-20 23:29:13', 'hb0730', 1, 0,
        '1845279278407184385', '删除导航分类', 'nav:category:delete', NULL, NULL, 3);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1870421562547048450', '2024-12-21 18:50:26', 'hb0730', '2024-12-21 18:50:26', 'hb0730', 1, 0,
        '1870421514971058178', '是', 'true', '{
    \"color\": \"success\"
  }', NULL, 1);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1870421615214923778', '2024-12-21 18:50:38', 'hb0730', '2024-12-21 18:50:38', 'hb0730', 1, 0,
        '1870421514971058178', '否', 'false', '{
    \"color\": \"info\"
  }', NULL, 2);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1870421839480164354', '2024-12-21 18:51:32', 'hb0730', '2024-12-21 18:52:08', 'hb0730', 1, 1,
        '1870421727047651329', '是', 'true', '{
    \"color\": \"success\"
  }', NULL, 1);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1870421974859714562', '2024-12-21 18:52:04', 'hb0730', '2024-12-21 18:52:04', 'hb0730', 1, 0,
        '1870421727047651329', '否', 'false', '{
    \"color\": \"success\"
  }', NULL, 2);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1870422047802855425', '2024-12-21 18:52:22', 'hb0730', '2024-12-21 18:52:22', 'hb0730', 1, 0,
        '1870421727047651329', '是', 'true', '{
    \"color\": \"danger\"
  }', NULL, 1);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1870422245832724482', '2024-12-21 18:53:09', 'hb0730', '2024-12-21 18:53:09', 'hb0730', 1, 0,
        '1870422175720738818', '是', 'true', '{
    \"color\": \"warning\"
  }', NULL, 1);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1870422287868039170', '2024-12-21 18:53:19', 'hb0730', '2024-12-21 18:53:19', 'hb0730', 1, 0,
        '1870422175720738818', '否', 'false', '{
    \"color\": \"success\"
  }', NULL, 2);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1870743653741809665', '2024-12-22 16:10:18', 'hb0730', '2024-12-22 16:10:18', 'hb0730', 1, 0,
        '1845278594379116545', '导航系统', 'nav', NULL, NULL, 10);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1870744011897622530', '2024-12-22 16:11:44', 'hb0730', '2024-12-22 16:11:44', 'hb0730', 1, 0,
        '1845279278407184385', '新增收录网站', 'nav:list:save', NULL, NULL, 1);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1870744075437133826', '2024-12-22 16:11:59', 'hb0730', '2024-12-22 16:11:59', 'hb0730', 1, 0,
        '1845279278407184385', '更新收录网站', 'nav:list:update', NULL, NULL, 2);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1870744131049410561', '2024-12-22 16:12:12', 'hb0730', '2024-12-22 16:12:12', 'hb0730', 1, 0,
        '1845279278407184385', '删除收录网站', 'nav:list:delete', NULL, NULL, 3);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1871561656586788866', '2024-12-24 22:20:46', 'hb0730', '2024-12-24 22:20:46', 'hb0730', 1, 0,
        '1845279278407184385', '绑定&修改邮箱或手机号', 'authentication:update-email-phone', NULL, NULL, 11);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1872222686136582146', '2024-12-26 18:07:27', 'hb0730', '2024-12-26 18:07:27', 'hb0730', 1, 1,
        '1847468609909673985', '语录系统', 'zoom-goroku', NULL, NULL, 3);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1872222776502861825', '2024-12-26 18:07:49', 'hb0730', '2024-12-27 14:21:40', 'hb0730', 1, 1,
        '1847468609909673985', '导航系统', 'zoom-nav', NULL, NULL, 4);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1872516133607219202', '2024-12-27 13:33:31', 'hb0730', '2024-12-27 13:33:58', 'hb0730', 1, 1,
        '1872516036131594241', '普通导出', 'E', NULL, NULL, 1);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1872516496024444929', '2024-12-27 13:34:57', 'hb0730', '2024-12-27 13:34:57', 'hb0730', 1, 0,
        '1872516327954489346', '普通导出', 'E', NULL, NULL, 1);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1872516537178955777', '2024-12-27 13:35:07', 'hb0730', '2024-12-27 13:35:07', 'hb0730', 1, 0,
        '1872516327954489346', '普通导入', 'I', NULL, NULL, 2);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1872517361909145601', '2024-12-27 13:38:23', 'hb0730', '2024-12-27 13:39:25', 'hb0730', 1, 1,
        '1872517068391751681', '未开始', '0', '{
    \"color\": \"Info\"
  }', NULL, 1);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1872517530985734146', '2024-12-27 13:39:04', 'hb0730', '2024-12-27 17:51:28', 'hb0730', 1, 1,
        '1872517068391751681', '进行中', '1', '{
    \"color\": \"#E6A23C\"
  }', NULL, 2);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1872517607284318209', '2024-12-27 13:39:22', 'hb0730', '2024-12-27 17:51:09', 'hb0730', 1, 1,
        '1872517068391751681', '已完成', '2', '{
    \"color\": \"#67C23A\"
  }', NULL, 3);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1872517700242677762', '2024-12-27 13:39:44', 'hb0730', '2024-12-27 17:52:20', 'hb0730', 1, 1,
        '1872517068391751681', '未开始', '0', '{
    \"color\": \"#909399\"
  }', NULL, 1);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1872528241627582465', '2024-12-27 14:21:37', 'hb0730', '2024-12-27 14:21:37', 'hb0730', 1, 1,
        '1847468609909673985', '语录Job系统', 'zoom-goroku-job', NULL, NULL, 4);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1872580541473742849', '2024-12-27 17:49:27', 'hb0730', '2024-12-27 17:49:27', 'hb0730', 1, 0,
        '1872516036131594241', '语录导出', 'E01', NULL, NULL, 1);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1872581019951554562', '2024-12-27 17:51:21', 'hb0730', '2024-12-27 17:51:21', 'hb0730', 1, 0,
        '1872517068391751681', '已完成', '2', '{
    \"color\": \"success\"
  }', NULL, 3);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1872581258137690114', '2024-12-27 17:52:17', 'hb0730', '2024-12-27 17:52:17', 'hb0730', 1, 0,
        '1872517068391751681', '进行中', '1', '{
    \"color\": \"primary\"
  }', NULL, 2);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1872581327339511810', '2024-12-27 17:52:34', 'hb0730', '2024-12-27 17:52:34', 'hb0730', 1, 0,
        '1872517068391751681', '未开始', '0', '{
    \"color\": \"info\"
  }', NULL, 1);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1872913202440372226', '2024-12-28 15:51:19', 'hb0730', '2024-12-28 15:51:19', 'hb0730', 1, 0,
        '1872516036131594241', '语录导入', 'I01', NULL, NULL, 2);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1874307685908262913', '2025-01-01 12:12:30', 'hb0730', '2025-01-02 10:21:17', 'hb0730', 1, 1,
        '1846365684927852545', '站内消息', 'SYS', NULL, NULL, 3);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1874642120126091266', '2025-01-02 10:21:25', 'hb0730', '2025-02-02 11:46:03', 'hb0730', 1, 1,
        '1846365684927852545', '站内消息', 'SITE', NULL, NULL, 3);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1874675185514790913', '2025-01-02 12:32:49', 'hb0730', '2025-01-02 12:32:49', 'hb0730', 1, 0,
        '1845279278407184385', '更新接收消息管理', 'authentication:update-user-subscribe-msg', NULL, NULL, 12);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1874675385377570817', '2025-01-02 12:33:36', 'hb0730', '2025-01-02 12:33:49', 'hb0730', 1, 1,
        '1845278594379116545', '接收消息管理', 'basic:message:subscribe', NULL, NULL, 1);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1874675479321591809', '2025-01-02 12:33:59', 'hb0730', '2025-01-02 12:33:59', 'hb0730', 1, 0,
        '1845278594379116545', '接收消息管理', 'basic:message:subscribe', NULL, NULL, 11);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1874675559063699458', '2025-01-02 12:34:18', 'hb0730', '2025-01-02 12:34:18', 'hb0730', 1, 0,
        '1845279278407184385', '新增接收消息', 'sys:message:subscribe:create', NULL, NULL, 1);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1874675606656466945', '2025-01-02 12:34:29', 'hb0730', '2025-01-02 12:34:29', 'hb0730', 1, 0,
        '1845279278407184385', '更新接收消息', 'sys:message:subscribe:edit', NULL, NULL, 2);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1874675668430176258', '2025-01-02 12:34:44', 'hb0730', '2025-01-02 12:34:44', 'hb0730', 1, 0,
        '1845279278407184385', '删除接收消息', 'sys:message:subscribe:delete', NULL, NULL, 3);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1876989006921146369', '2025-01-08 21:47:07', 'hb0730', '2025-01-08 21:47:07', 'hb0730', 1, 0,
        '1876988656713539585', '未读', '0', '{
    \"color\": \"danger\"
  }', NULL, 1);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1876989074705293313', '2025-01-08 21:47:23', 'hb0730', '2025-01-08 21:47:23', 'hb0730', 1, 0,
        '1876988656713539585', '已读', '1', '{
    \"color\": \"success\"
  }', NULL, 2);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1885897492992163841', '2025-02-02 11:46:15', 'hb0730', '2025-02-02 11:46:15', 'hb0730', 1, 0,
        '1846365684927852545', '站内消息', 'SYS', NULL, NULL, 3);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1887751825014718466', '2025-02-07 14:34:42', 'hb0730', '2025-02-07 14:34:46', 'hb0730', 1, 1,
        '1887751597788299266', '是', 'true', '{
    \"color\": \"{ \\\"color\\\": \\\"primary\\\" }\"
  }', NULL, 1);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1887751943617052674', '2025-02-07 14:35:11', 'hb0730', '2025-02-07 14:35:11', 'hb0730', 1, 0,
        '1887751597788299266', '是', 'true', '{
    \"color\": \"primary\"
  }', NULL, 1);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1887751996230402050', '2025-02-07 14:35:23', 'hb0730', '2025-02-07 14:35:23', 'hb0730', 1, 0,
        '1887751597788299266', '否', 'false', '{
    \"color\": \"info\"
  }', NULL, 2);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888547803703586817', '2025-02-09 19:17:39', 'hb0730', '2025-02-09 19:17:39', 'hb0730', 1, 1,
        '1888546734328033282', '社招简历', '1', NULL, NULL, 1);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888547854689546242', '2025-02-09 19:17:51', 'hb0730', '2025-02-09 19:17:51', 'hb0730', 1, 1,
        '1888546734328033282', '校招简历', '2', NULL, NULL, 2);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888547891389706241', '2025-02-09 19:17:59', 'hb0730', '2025-02-09 19:17:59', 'hb0730', 1, 1,
        '1888546734328033282', '实习简历', '3', NULL, NULL, 3);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888547935039827970', '2025-02-09 19:18:10', 'hb0730', '2025-02-09 19:18:13', 'hb0730', 1, 1,
        '1888546734328033282', '4', '留学简历', NULL, NULL, 4);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888547968950775810', '2025-02-09 19:18:18', 'hb0730', '2025-02-09 19:18:18', 'hb0730', 1, 1,
        '1888546734328033282', '留学简历', '4', NULL, NULL, 4);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888548009849434114', '2025-02-09 19:18:28', 'hb0730', '2025-02-09 19:18:28', 'hb0730', 1, 1,
        '1888546734328033282', '考研复试', '5', NULL, NULL, 5);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888548054141284353', '2025-02-09 19:18:38', 'hb0730', '2025-02-09 19:18:38', 'hb0730', 1, 1,
        '1888546734328033282', '内推简历', '6', NULL, NULL, 6);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888548107799015425', '2025-02-09 19:18:51', 'hb0730', '2025-02-09 19:18:51', 'hb0730', 1, 1,
        '1888546961747390466', '职能支持', '1', NULL, NULL, 1);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888548144025219074', '2025-02-09 19:19:00', 'hb0730', '2025-02-09 19:19:00', 'hb0730', 1, 1,
        '1888546961747390466', '互联网通信', '2', NULL, NULL, 2);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888548181228695554', '2025-02-09 19:19:09', 'hb0730', '2025-02-09 19:19:09', 'hb0730', 1, 1,
        '1888546961747390466', '金融投资', '3', NULL, NULL, 3);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888548678656372738', '2025-02-09 19:21:07', 'hb0730', '2025-02-09 19:21:07', 'hb0730', 1, 1,
        '1888546961747390466', '房地产建筑', '4', NULL, NULL, 4);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888548759870681089', '2025-02-09 19:21:27', 'hb0730', '2025-02-09 19:21:27', 'hb0730', 1, 1,
        '1888546961747390466', '休闲服务', '5', NULL, NULL, 5);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888548812886683649', '2025-02-09 19:21:39', 'hb0730', '2025-02-09 19:21:39', 'hb0730', 1, 1,
        '1888546961747390466', '教育培训', '6', NULL, NULL, 6);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888548880142348290', '2025-02-09 19:21:55', 'hb0730', '2025-02-09 19:21:55', 'hb0730', 1, 1,
        '1888546961747390466', '广告传媒', '7', NULL, NULL, 7);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888548942574563330', '2025-02-09 19:22:10', 'hb0730', '2025-02-09 19:22:10', 'hb0730', 1, 1,
        '1888546961747390466', '医疗制药', '8', NULL, NULL, 8);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888549052276584450', '2025-02-09 19:22:36', 'hb0730', '2025-02-09 19:22:36', 'hb0730', 1, 1,
        '1888546961747390466', '消费运输', '9', NULL, NULL, 9);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888549088007860226', '2025-02-09 19:22:45', 'hb0730', '2025-02-09 19:22:45', 'hb0730', 1, 1,
        '1888546961747390466', '制造能源', '10', NULL, NULL, 10);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888549131074973697', '2025-02-09 19:22:55', 'hb0730', '2025-02-09 19:22:55', 'hb0730', 1, 1,
        '1888546961747390466', '公共事业', '11', NULL, NULL, 11);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888549296758370306', '2025-02-09 19:23:35', 'hb0730', '2025-02-09 19:23:35', 'hb0730', 1, 1,
        '1888547212428357633', '财务', '1', '{
    \"type\": \"职能支持\"
  }', NULL, 1);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888549388198391810', '2025-02-09 19:23:56', 'hb0730', '2025-02-09 19:23:56', 'hb0730', 1, 1,
        '1888547212428357633', '会计', '2', '{
    \"type\": \"职能支持\"
  }', NULL, 2);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888549477688061954', '2025-02-09 19:24:18', 'hb0730', '2025-02-09 19:24:18', 'hb0730', 1, 1,
        '1888547212428357633', '审计', '3', '{
    \"type\": \"职能支持\"
  }', NULL, 3);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888550312564273153', '2025-02-09 19:27:37', 'hb0730', '2025-02-09 19:27:37', 'hb0730', 1, 1,
        '1888547212428357633', '出纳', '4', '{
    \"type\": \"职能支持\"
  }', NULL, 4);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888550380893679618', '2025-02-09 19:27:53', 'hb0730', '2025-02-09 19:27:53', 'hb0730', 1, 1,
        '1888547212428357633', '税务', '5', '{
    \"type\": \"职能支持\"
  }', NULL, 5);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888550467426365441', '2025-02-09 19:28:14', 'hb0730', '2025-02-09 19:28:39', 'hb0730', 1, 1,
        '1888547212428357633', '统计', '6', '{
    \"type\": \"职能支持\"
  }', NULL, 1);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888550735140401153', '2025-02-09 19:29:17', 'hb0730', '2025-02-09 19:29:17', 'hb0730', 1, 1,
        '1888547212428357633', '统计', '6', '{
    \"type\": \"职能支持\"
  }', NULL, 6);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888550805093003265', '2025-02-09 19:29:34', 'hb0730', '2025-02-09 19:29:34', 'hb0730', 1, 1,
        '1888547212428357633', '成本管理', '7', '{
    \"type\": \"职能支持\"
  }', NULL, 7);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888550856292872194', '2025-02-09 19:29:46', 'hb0730', '2025-02-09 19:29:46', 'hb0730', 1, 1,
        '1888547212428357633', '资产管理', '8', '{
    \"type\": \"职能支持\"
  }', NULL, 8);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888550989822734338', '2025-02-09 19:30:18', 'hb0730', '2025-02-09 19:30:18', 'hb0730', 1, 1,
        '1888547212428357633', '法务', '9', '{
    \"type\": \"职能支持\"
  }', NULL, 9);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888551261043208193', '2025-02-09 19:31:23', 'hb0730', '2025-02-09 19:31:23', 'hb0730', 1, 1,
        '1888547212428357633', '律师', '10', '{
    \"type\": \"职能支持\"
  }', NULL, 10);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888551311496491010', '2025-02-09 19:31:35', 'hb0730', '2025-02-09 19:31:35', 'hb0730', 1, 1,
        '1888547212428357633', '合规', '11', '{
    \"type\": \"职能支持\"
  }', NULL, 11);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888551375073751041', '2025-02-09 19:31:50', 'hb0730', '2025-02-09 19:31:50', 'hb0730', 1, 1,
        '1888547212428357633', '知识产权', '12', '{
    \"type\": \"职能支持\"
  }', NULL, 12);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888551418400911361', '2025-02-09 19:32:00', 'hb0730', '2025-02-09 19:32:00', 'hb0730', 1, 1,
        '1888547212428357633', '律师助理', '13', '{
    \"type\": \"职能支持\"
  }', NULL, 13);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888551490412916738', '2025-02-09 19:32:18', 'hb0730', '2025-02-09 19:32:18', 'hb0730', 1, 1,
        '1888547212428357633', '人力资源', '14', '{
    \"type\": \"职能支持\"
  }', NULL, 14);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888551553960816641', '2025-02-09 19:32:33', 'hb0730', '2025-02-09 19:32:33', 'hb0730', 1, 1,
        '1888547212428357633', 'HRBP', '15', '{
    \"type\": \"职能支持\"
  }', NULL, 15);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888551677508235266', '2025-02-09 19:33:02', 'hb0730', '2025-02-09 19:33:02', 'hb0730', 1, 1,
        '1888547212428357633', '猎头', '16', '{
    \"type\": \"职能支持\"
  }', NULL, 16);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888551730373242881', '2025-02-09 19:33:15', 'hb0730', '2025-02-09 19:33:15', 'hb0730', 1, 1,
        '1888547212428357633', '薪酬福利', '17', '{
    \"type\": \"职能支持\"
  }', NULL, 17);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888551778003759106', '2025-02-09 19:33:26', 'hb0730', '2025-02-09 19:33:26', 'hb0730', 1, 1,
        '1888547212428357633', '绩效考核', '18', '{
    \"type\": \"职能支持\"
  }', NULL, 18);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888551864876183553', '2025-02-09 19:33:47', 'hb0730', '2025-02-09 19:34:05', 'hb0730', 1, 1,
        '1888547212428357633', '企业文化', '19', '{
    \"type\": \"职能支持\"
  }', NULL, 19);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888552693708402690', '2025-02-09 19:37:04', 'hb0730', '2025-02-09 19:37:04', 'hb0730', 1, 1,
        '1888547212428357633', '招聘', '20', '{
    \"type\": \"职能支持\"
  }', NULL, 20);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888552741846429697', '2025-02-09 19:37:16', 'hb0730', '2025-02-09 19:37:16', 'hb0730', 1, 1,
        '1888547212428357633', '培训', '21', '{
    \"type\": \"职能支持\"
  }', NULL, 21);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888552827351511042', '2025-02-09 19:37:36', 'hb0730', '2025-02-09 19:37:36', 'hb0730', 1, 1,
        '1888547212428357633', '行政', '22', '{
    \"type\": \"职能支持\"
  }', NULL, 22);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888552892958814210', '2025-02-09 19:37:52', 'hb0730', '2025-02-09 19:37:52', 'hb0730', 1, 1,
        '1888547212428357633', '前台', '23', '{
    \"type\": \"职能支持\"
  }', NULL, 23);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888552993328508930', '2025-02-09 19:38:16', 'hb0730', '2025-02-09 19:38:16', 'hb0730', 1, 1,
        '1888547212428357633', '秘书', '24', '{
    \"type\": \"职能支持\"
  }', NULL, 24);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888553056830271489', '2025-02-09 19:38:31', 'hb0730', '2025-02-09 19:38:31', 'hb0730', 1, 1,
        '1888547212428357633', '文员', '25', '{
    \"type\": \"职能支持\"
  }', NULL, 25);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888553114694889473', '2025-02-09 19:38:45', 'hb0730', '2025-02-09 19:38:45', 'hb0730', 1, 1,
        '1888547212428357633', '总助', '26', '{
    \"type\": \"职能支持\"
  }', NULL, 26);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888553179220062210', '2025-02-09 19:39:00', 'hb0730', '2025-02-09 19:39:00', 'hb0730', 1, 1,
        '1888547212428357633', '总机', '27', '{
    \"type\": \"职能支持\"
  }', NULL, 27);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888553233146228737', '2025-02-09 19:39:13', 'hb0730', '2025-02-09 19:39:13', 'hb0730', 1, 1,
        '1888547212428357633', '渠道推广', '28', '{
    \"type\": \"职能支持\"
  }', NULL, 28);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888553276024598529', '2025-02-09 19:39:23', 'hb0730', '2025-02-09 19:39:23', 'hb0730', 1, 1,
        '1888547212428357633', 'SEO', '29', '{
    \"type\": \"职能支持\"
  }', NULL, 29);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888553384535437314', '2025-02-09 19:39:49', 'hb0730', '2025-02-09 19:39:49', 'hb0730', 1, 1,
        '1888547212428357633', '前端开发', '1', '{
    \"type\": \"互联网通信\"
  }', NULL, 1);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888553465502281729', '2025-02-09 19:40:08', 'hb0730', '2025-02-09 19:40:08', 'hb0730', 1, 1,
        '1888547212428357633', '后端开发', '2', '{
    \"type\": \"互联网通信\"
  }', NULL, 2);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888553565473517570', '2025-02-09 19:40:32', 'hb0730', '2025-02-09 19:40:32', 'hb0730', 1, 1,
        '1888547212428357633', '移动开发', '3', '{
    \"type\": \"互联网通信\"
  }', NULL, 3);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888553626441920514', '2025-02-09 19:40:47', 'hb0730', '2025-02-09 19:40:47', 'hb0730', 1, 1,
        '1888547212428357633', '测试', '4', '{
    \"type\": \"互联网通信\"
  }', NULL, 4);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888553678510010370', '2025-02-09 19:40:59', 'hb0730', '2025-02-09 19:40:59', 'hb0730', 1, 1,
        '1888547212428357633', '运维', '5', '{
    \"type\": \"互联网通信\"
  }', NULL, 5);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888553779898920962', '2025-02-09 19:41:23', 'hb0730', '2025-02-09 19:41:23', 'hb0730', 1, 1,
        '1888547212428357633', '硬件开发', '6', '{
    \"type\": \"互联网通信\"
  }', NULL, 6);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888553887084359682', '2025-02-09 19:41:49', 'hb0730', '2025-02-09 19:41:49', 'hb0730', 1, 1,
        '1888547212428357633', '项目管理', '7', '{
    \"type\": \"互联网通信\"
  }', NULL, 7);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888553937944489986', '2025-02-09 19:42:01', 'hb0730', '2025-02-09 19:42:01', 'hb0730', 1, 1,
        '1888547212428357633', '网络传输', '8', '{
    \"type\": \"互联网通信\"
  }', NULL, 8);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888553996899627009', '2025-02-09 19:42:15', 'hb0730', '2025-02-09 19:42:15', 'hb0730', 1, 1,
        '1888547212428357633', '数据分析', '9', '{
    \"type\": \"互联网通信\"
  }', NULL, 9);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888554057444405250', '2025-02-09 19:42:30', 'hb0730', '2025-02-09 19:42:30', 'hb0730', 1, 1,
        '1888547212428357633', '产品经理', '10', '{
    \"type\": \"互联网通信\"
  }', NULL, 10);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888554112708554753', '2025-02-09 19:42:43', 'hb0730', '2025-02-09 19:42:43', 'hb0730', 1, 1,
        '1888547212428357633', '产品策划', '11', '{
    \"type\": \"互联网通信\"
  }', NULL, 11);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888554157717630978', '2025-02-09 19:42:53', 'hb0730', '2025-02-09 19:42:53', 'hb0730', 1, 1,
        '1888547212428357633', '游戏策划', '12', '{
    \"type\": \"互联网通信\"
  }', NULL, 12);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888554214437203969', '2025-02-09 19:43:07', 'hb0730', '2025-02-09 19:43:07', 'hb0730', 1, 1,
        '1888547212428357633', '产品助理', '13', '{
    \"type\": \"互联网通信\"
  }', NULL, 13);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888554276944916481', '2025-02-09 19:43:22', 'hb0730', '2025-02-09 19:43:22', 'hb0730', 1, 1,
        '1888547212428357633', '交互设计', '14', '{
    \"type\": \"互联网通信\"
  }', NULL, 14);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888554339171610625', '2025-02-09 19:43:37', 'hb0730', '2025-02-09 19:43:37', 'hb0730', 1, 1,
        '1888547212428357633', '平面设计', '15', '{
    \"type\": \"互联网通信\"
  }', NULL, 15);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888554427738533889', '2025-02-09 19:43:58', 'hb0730', '2025-02-09 19:43:58', 'hb0730', 1, 1,
        '1888547212428357633', '网页设计', '16', '{
    \"type\": \"互联网通信\"
  }', NULL, 16);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888554510047555585', '2025-02-09 19:44:17', 'hb0730', '2025-02-09 19:44:17', 'hb0730', 1, 1,
        '1888547212428357633', '动画人物设计', '17', '{
    \"type\": \"互联网通信\"
  }', NULL, 17);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888554793402150913', '2025-02-09 19:45:25', 'hb0730', '2025-02-09 19:45:25', 'hb0730', 1, 1,
        '1888547212428357633', '游戏原画', '18', '{
    \"type\": \"互联网通信\"
  }', NULL, 18);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888554871047106562', '2025-02-09 19:45:44', 'hb0730', '2025-02-09 19:45:44', 'hb0730', 1, 1,
        '1888547212428357633', '游戏场景', '19', '{
    \"type\": \"互联网通信\"
  }', NULL, 19);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888554913413771266', '2025-02-09 19:45:54', 'hb0730', '2025-02-09 19:45:54', 'hb0730', 1, 1,
        '1888547212428357633', '游戏特效设计', '20', '{
    \"type\": \"互联网通信\"
  }', NULL, 20);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888555017646419969', '2025-02-09 19:46:19', 'hb0730', '2025-02-09 19:46:19', 'hb0730', 1, 1,
        '1888547212428357633', '插画', '21', '{
    \"type\": \"互联网通信\"
  }', NULL, 21);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888555067738992642', '2025-02-09 19:46:30', 'hb0730', '2025-02-09 19:46:30', 'hb0730', 1, 1,
        '1888547212428357633', '产品运营', '22', '{
    \"type\": \"互联网通信\"
  }', NULL, 22);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888555117768650754', '2025-02-09 19:46:42', 'hb0730', '2025-02-09 19:46:42', 'hb0730', 1, 1,
        '1888547212428357633', '新媒体运营', '23', '{
    \"type\": \"互联网通信\"
  }', NULL, 23);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888555166867173377', '2025-02-09 19:46:54', 'hb0730', '2025-02-09 19:46:54', 'hb0730', 1, 1,
        '1888547212428357633', '游戏运营', '24', '{
    \"type\": \"互联网通信\"
  }', NULL, 24);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888555253492133889', '2025-02-09 19:47:15', 'hb0730', '2025-02-09 19:47:15', 'hb0730', 1, 1,
        '1888547212428357633', '用户运营', '25', '{
    \"type\": \"互联网通信\"
  }', NULL, 25);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888555317564321793', '2025-02-09 19:47:30', 'hb0730', '2025-02-09 19:47:30', 'hb0730', 1, 1,
        '1888547212428357633', '活动运营', '26', '{
    \"type\": \"互联网通信\"
  }', NULL, 26);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888555377974882306', '2025-02-09 19:47:44', 'hb0730', '2025-02-09 19:47:44', 'hb0730', 1, 1,
        '1888547212428357633', '社区运营', '27', '{
    \"type\": \"互联网通信\"
  }', NULL, 27);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888555431733276674', '2025-02-09 19:47:57', 'hb0730', '2025-02-09 19:47:57', 'hb0730', 1, 1,
        '1888547212428357633', '内容运营', '28', '{
    \"type\": \"互联网通信\"
  }', NULL, 28);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888555516806344705', '2025-02-09 19:48:18', 'hb0730', '2025-02-09 19:48:18', 'hb0730', 1, 1,
        '1888547212428357633', '客服', '29', '{
    \"type\": \"互联网通信\"
  }', NULL, 29);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888555559126872065', '2025-02-09 19:48:28', 'hb0730', '2025-02-09 19:48:28', 'hb0730', 1, 1,
        '1888547212428357633', '电商运营', '30', '{
    \"type\": \"互联网通信\"
  }', NULL, 30);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888555738261401602', '2025-02-09 19:49:10', 'hb0730', '2025-02-09 19:49:10', 'hb0730', 1, 1,
        '1888547212428357633', '客户经理', '1', '{
    \"type\": \"金融投资\"
  }', NULL, 1);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888555864094715906', '2025-02-09 19:49:40', 'hb0730', '2025-02-09 19:49:40', 'hb0730', 1, 1,
        '1888547212428357633', '业务员', '16', '{
    \"type\": \"金融投资\"
  }', NULL, 16);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888555864094715907', '2025-02-09 19:49:40', 'hb0730', '2025-02-09 19:49:40', 'hb0730', 1, 1,
        '1888547212428357633', '培训讲师', '15', '{
    \"type\": \"金融投资\"
  }', NULL, 15);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888555864094715908', '2025-02-09 19:49:40', 'hb0730', '2025-02-09 19:49:40', 'hb0730', 1, 1,
        '1888547212428357633', '综合柜员', '14', '{
    \"type\": \"金融投资\"
  }', NULL, 14);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888555864094715909', '2025-02-09 19:49:40', 'hb0730', '2025-02-09 19:49:40', 'hb0730', 1, 1,
        '1888547212428357633', '销售代表', '13', '{
    \"type\": \"金融投资\"
  }', NULL, 13);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888555864094715910', '2025-02-09 19:49:40', 'hb0730', '2025-02-09 19:49:40', 'hb0730', 1, 1,
        '1888547212428357633', '基金会计', '12', '{
    \"type\": \"金融投资\"
  }', NULL, 12);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888555864094715911', '2025-02-09 19:49:40', 'hb0730', '2025-02-09 19:49:40', 'hb0730', 1, 1,
        '1888547212428357633', '债券发行', '11', '{
    \"type\": \"金融投资\"
  }', NULL, 11);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888555864094715912', '2025-02-09 19:49:40', 'hb0730', '2025-02-09 19:49:40', 'hb0730', 1, 1,
        '1888547212428357633', '证券分析', '10', '{
    \"type\": \"金融投资\"
  }', NULL, 10);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888555864094715913', '2025-02-09 19:49:40', 'hb0730', '2025-02-09 19:49:40', 'hb0730', 1, 1,
        '1888547212428357633', '基金经理', '9', '{
    \"type\": \"金融投资\"
  }', NULL, 9);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888555864094715914', '2025-02-09 19:49:40', 'hb0730', '2025-02-09 19:49:40', 'hb0730', 1, 1,
        '1888547212428357633', '投资顾问', '8', '{
    \"type\": \"金融投资\"
  }', NULL, 8);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888555864094715915', '2025-02-09 19:49:40', 'hb0730', '2025-02-09 19:49:40', 'hb0730', 1, 1,
        '1888547212428357633', '交易员', '7', '{
    \"type\": \"金融投资\"
  }', NULL, 7);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888555864094715916', '2025-02-09 19:49:40', 'hb0730', '2025-02-09 19:49:40', 'hb0730', 1, 1,
        '1888547212428357633', '理财顾问', '6', '{
    \"type\": \"金融投资\"
  }', NULL, 6);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888555864094715917', '2025-02-09 19:49:40', 'hb0730', '2025-02-09 19:49:40', 'hb0730', 1, 1,
        '1888547212428357633', '风险控制', '5', '{
    \"type\": \"金融投资\"
  }', NULL, 5);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888555864094715918', '2025-02-09 19:49:40', 'hb0730', '2025-02-09 19:49:40', 'hb0730', 1, 1,
        '1888547212428357633', '支行行长', '4', '{
    \"type\": \"金融投资\"
  }', NULL, 4);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888555864094715919', '2025-02-09 19:49:40', 'hb0730', '2025-02-09 19:49:40', 'hb0730', 1, 1,
        '1888547212428357633', '银行柜员', '3', '{
    \"type\": \"金融投资\"
  }', NULL, 3);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888555864094715920', '2025-02-09 19:49:40', 'hb0730', '2025-02-09 19:49:41', 'hb0730', 1, 1,
        '1888547212428357633', '理财规划', '17', '{
    \"type\": \"金融投资\"
  }', NULL, 17);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888555864094715921', '2025-02-09 19:49:40', 'hb0730', '2025-02-09 19:49:42', 'hb0730', 1, 1,
        '1888547212428357633', '产品研发', '18', '{
    \"type\": \"金融投资\"
  }', NULL, 18);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888555864094715922', '2025-02-09 19:49:40', 'hb0730', '2025-02-09 19:49:43', 'hb0730', 1, 1,
        '1888547212428357633', '精算师', '19', '{
    \"type\": \"金融投资\"
  }', NULL, 19);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888555864094715923', '2025-02-09 19:49:40', 'hb0730', '2025-02-09 19:49:44', 'hb0730', 1, 1,
        '1888547212428357633', '信托经理', '20', '{
    \"type\": \"金融投资\"
  }', NULL, 20);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888555864094715924', '2025-02-09 19:49:40', 'hb0730', '2025-02-09 19:49:45', 'hb0730', 1, 1,
        '1888547212428357633', '产品经理', '21', '{
    \"type\": \"金融投资\"
  }', NULL, 21);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888555864094715925', '2025-02-09 19:49:40', 'hb0730', '2025-02-09 19:49:46', 'hb0730', 1, 1,
        '1888547212428357633', '资产管理', '22', '{
    \"type\": \"金融投资\"
  }', NULL, 22);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888555864094715926', '2025-02-09 19:49:40', 'hb0730', '2025-02-09 19:49:47', 'hb0730', 1, 1,
        '1888547212428357633', '资产证券化', '23', '{
    \"type\": \"金融投资\"
  }', NULL, 23);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888555864094715927', '2025-02-09 19:49:40', 'hb0730', '2025-02-09 19:49:48', 'hb0730', 1, 1,
        '1888547212428357633', '期货经纪人', '24', '{
    \"type\": \"金融投资\"
  }', NULL, 24);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888555864094715930', '2025-02-09 19:49:40', 'hb0730', '2025-02-09 19:49:40', 'hb0730', 1, 1,
        '1888547212428357633', '大堂经理', '2', '{
    \"type\": \"金融投资\"
  }', NULL, 2);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888558451976089602', '2025-02-09 19:59:57', 'hb0730', '2025-02-09 19:59:57', 'hb0730', 1, 1,
        '1888547212428357633', '家装', '31', '{
    \"type\": \"房地产建筑\"
  }', NULL, 31);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888558451976089603', '2025-02-09 19:59:57', 'hb0730', '2025-02-09 19:59:57', 'hb0730', 1, 1,
        '1888547212428357633', '城市规划', '30', '{
    \"type\": \"房地产建筑\"
  }', NULL, 30);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888558451976089604', '2025-02-09 19:59:57', 'hb0730', '2025-02-09 19:59:57', 'hb0730', 1, 1,
        '1888547212428357633', '软装设计', '29', '{
    \"type\": \"房地产建筑\"
  }', NULL, 29);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888558451976089605', '2025-02-09 19:59:57', 'hb0730', '2025-02-09 19:59:57', 'hb0730', 1, 1,
        '1888547212428357633', '结构设计', '28', '{
    \"type\": \"房地产建筑\"
  }', NULL, 28);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888558451976089606', '2025-02-09 19:59:57', 'hb0730', '2025-02-09 19:59:57', 'hb0730', 1, 1,
        '1888547212428357633', '景观设计', '27', '{
    \"type\": \"房地产建筑\"
  }', NULL, 27);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888558451976089607', '2025-02-09 19:59:57', 'hb0730', '2025-02-09 19:59:57', 'hb0730', 1, 1,
        '1888547212428357633', '室内设计', '26', '{
    \"type\": \"房地产建筑\"
  }', NULL, 26);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888558451976089608', '2025-02-09 19:59:57', 'hb0730', '2025-02-09 19:59:57', 'hb0730', 1, 1,
        '1888547212428357633', '设计', '25', '{
    \"type\": \"房地产建筑\"
  }', NULL, 25);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888558451976089609', '2025-02-09 19:59:57', 'hb0730', '2025-02-09 19:59:57', 'hb0730', 1, 1,
        '1888547212428357633', '绿化', '24', '{
    \"type\": \"房地产建筑\"
  }', NULL, 24);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888558451976089610', '2025-02-09 19:59:57', 'hb0730', '2025-02-09 19:59:57', 'hb0730', 1, 1,
        '1888547212428357633', '机电维修', '23', '{
    \"type\": \"房地产建筑\"
  }', NULL, 23);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888558451976089611', '2025-02-09 19:59:57', 'hb0730', '2025-02-09 19:59:57', 'hb0730', 1, 1,
        '1888547212428357633', '物业维修', '22', '{
    \"type\": \"房地产建筑\"
  }', NULL, 22);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888558451976089612', '2025-02-09 19:59:57', 'hb0730', '2025-02-09 19:59:57', 'hb0730', 1, 1,
        '1888547212428357633', '客服顾问', '21', '{
    \"type\": \"房地产建筑\"
  }', NULL, 21);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888558451976089613', '2025-02-09 19:59:57', 'hb0730', '2025-02-09 19:59:57', 'hb0730', 1, 1,
        '1888547212428357633', '物业招商', '20', '{
    \"type\": \"房地产建筑\"
  }', NULL, 20);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888558451976089614', '2025-02-09 19:59:57', 'hb0730', '2025-02-09 19:59:57', 'hb0730', 1, 1,
        '1888547212428357633', '设施管理', '19', '{
    \"type\": \"房地产建筑\"
  }', NULL, 19);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888558451976089615', '2025-02-09 19:59:57', 'hb0730', '2025-02-09 19:59:57', 'hb0730', 1, 1,
        '1888547212428357633', '物业管理', '18', '{
    \"type\": \"房地产建筑\"
  }', NULL, 18);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888558451976089616', '2025-02-09 19:59:57', 'hb0730', '2025-02-09 19:59:57', 'hb0730', 1, 1,
        '1888547212428357633', '物业', '17', '{
    \"type\": \"房地产建筑\"
  }', NULL, 17);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888558451976089617', '2025-02-09 19:59:57', 'hb0730', '2025-02-09 19:59:57', 'hb0730', 1, 1,
        '1888547212428357633', '现场管理', '16', '{
    \"type\": \"房地产建筑\"
  }', NULL, 16);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888558451976089618', '2025-02-09 19:59:57', 'hb0730', '2025-02-09 19:59:57', 'hb0730', 1, 1,
        '1888547212428357633', '工程监理', '15', '{
    \"type\": \"房地产建筑\"
  }', NULL, 15);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888558451976089619', '2025-02-09 19:59:57', 'hb0730', '2025-02-09 19:59:57', 'hb0730', 1, 1,
        '1888547212428357633', '水电工程师', '14', '{
    \"type\": \"房地产建筑\"
  }', NULL, 14);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888558451976089620', '2025-02-09 19:59:57', 'hb0730', '2025-02-09 19:59:57', 'hb0730', 1, 1,
        '1888547212428357633', '测绘工程师', '13', '{
    \"type\": \"房地产建筑\"
  }', NULL, 13);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888558451976089621', '2025-02-09 19:59:57', 'hb0730', '2025-02-09 19:59:57', 'hb0730', 1, 1,
        '1888547212428357633', '给排水工程师', '12', '{
    \"type\": \"房地产建筑\"
  }', NULL, 12);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888558451976089622', '2025-02-09 19:59:57', 'hb0730', '2025-02-09 19:59:57', 'hb0730', 1, 1,
        '1888547212428357633', '工程造价师', '11', '{
    \"type\": \"房地产建筑\"
  }', NULL, 11);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888558451976089623', '2025-02-09 19:59:57', 'hb0730', '2025-02-09 19:59:57', 'hb0730', 1, 1,
        '1888547212428357633', '建筑工程师', '10', '{
    \"type\": \"房地产建筑\"
  }', NULL, 10);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888558451976089624', '2025-02-09 19:59:57', 'hb0730', '2025-02-09 19:59:57', 'hb0730', 1, 1,
        '1888547212428357633', '土建', '9', '{
    \"type\": \"房地产建筑\"
  }', NULL, 9);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888558451976089625', '2025-02-09 19:59:57', 'hb0730', '2025-02-09 19:59:57', 'hb0730', 1, 1,
        '1888547212428357633', '房产经纪人', '8', '{
    \"type\": \"房地产建筑\"
  }', NULL, 8);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888558451976089626', '2025-02-09 19:59:57', 'hb0730', '2025-02-09 19:59:57', 'hb0730', 1, 1,
        '1888547212428357633', '房产中介', '7', '{
    \"type\": \"房地产建筑\"
  }', NULL, 7);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888558451976089627', '2025-02-09 19:59:57', 'hb0730', '2025-02-09 19:59:57', 'hb0730', 1, 1,
        '1888547212428357633', '合同管理', '6', '{
    \"type\": \"房地产建筑\"
  }', NULL, 6);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888558451976089628', '2025-02-09 19:59:57', 'hb0730', '2025-02-09 19:59:57', 'hb0730', 1, 1,
        '1888547212428357633', '资产管理', '5', '{
    \"type\": \"房地产建筑\"
  }', NULL, 5);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888558451976089629', '2025-02-09 19:59:57', 'hb0730', '2025-02-09 19:59:57', 'hb0730', 1, 1,
        '1888547212428357633', '项目招投标', '4', '{
    \"type\": \"房地产建筑\"
  }', NULL, 4);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888558451976089630', '2025-02-09 19:59:57', 'hb0730', '2025-02-09 19:59:57', 'hb0730', 1, 1,
        '1888547212428357633', '项目管理', '3', '{
    \"type\": \"房地产建筑\"
  }', NULL, 3);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888558451976089631', '2025-02-09 19:59:57', 'hb0730', '2025-02-09 19:59:57', 'hb0730', 1, 1,
        '1888547212428357633', '项目策划', '2', '{
    \"type\": \"房地产建筑\"
  }', NULL, 2);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888558451976089632', '2025-02-09 19:59:57', 'hb0730', '2025-02-09 19:59:57', 'hb0730', 1, 1,
        '1888547212428357633', '投资分析', '1', '{
    \"type\": \"房地产建筑\"
  }', NULL, 1);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888558451976089636', '2025-02-09 19:59:57', 'hb0730', '2025-02-09 19:59:59', 'hb0730', 1, 1,
        '1888547212428357633', '安装施工', '34', '{
    \"type\": \"房地产建筑\"
  }', NULL, 34);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888558451976089637', '2025-02-09 19:59:57', 'hb0730', '2025-02-09 19:59:59', 'hb0730', 1, 1,
        '1888547212428357633', '木杠', '33', '{
    \"type\": \"房地产建筑\"
  }', NULL, 33);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888558451976089638', '2025-02-09 19:59:57', 'hb0730', '2025-02-09 19:59:59', 'hb0730', 1, 1,
        '1888547212428357633', '工长', '32', '{
    \"type\": \"房地产建筑\"
  }', NULL, 32);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888561103736713219', '2025-02-09 20:10:30', 'hb0730', '2025-02-09 20:10:30', 'hb0730', 1, 1,
        '1888547212428357633', '足球教练', '39', '{
    \"type\": \"休闲服务\"
  }', NULL, 39);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888561103736713220', '2025-02-09 20:10:30', 'hb0730', '2025-02-09 20:10:30', 'hb0730', 1, 1,
        '1888547212428357633', '运动员', '38', '{
    \"type\": \"休闲服务\"
  }', NULL, 38);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888561103736713221', '2025-02-09 20:10:30', 'hb0730', '2025-02-09 20:10:30', 'hb0730', 1, 1,
        '1888547212428357633', '体育馆管理', '37', '{
    \"type\": \"休闲服务\"
  }', NULL, 37);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888561103736713223', '2025-02-09 20:10:30', 'hb0730', '2025-02-09 20:10:30', 'hb0730', 1, 1,
        '1888547212428357633', '赛事策划', '36', '{
    \"type\": \"休闲服务\"
  }', NULL, 36);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888561103736713224', '2025-02-09 20:10:30', 'hb0730', '2025-02-09 20:10:30', 'hb0730', 1, 1,
        '1888547212428357633', '体育教练', '35', '{
    \"type\": \"休闲服务\"
  }', NULL, 35);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888561103736713225', '2025-02-09 20:10:30', 'hb0730', '2025-02-09 20:10:30', 'hb0730', 1, 1,
        '1888547212428357633', '足疗师', '34', '{
    \"type\": \"休闲服务\"
  }', NULL, 34);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888561103736713226', '2025-02-09 20:10:30', 'hb0730', '2025-02-09 20:10:30', 'hb0730', 1, 1,
        '1888547212428357633', '按摩师', '33', '{
    \"type\": \"休闲服务\"
  }', NULL, 33);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888561103736713227', '2025-02-09 20:10:30', 'hb0730', '2025-02-09 20:10:30', 'hb0730', 1, 1,
        '1888547212428357633', '健身顾问', '32', '{
    \"type\": \"休闲服务\"
  }', NULL, 32);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888561103736713228', '2025-02-09 20:10:30', 'hb0730', '2025-02-09 20:10:30', 'hb0730', 1, 1,
        '1888547212428357633', '健身教练', '31', '{
    \"type\": \"休闲服务\"
  }', NULL, 31);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888561103736713229', '2025-02-09 20:10:30', 'hb0730', '2025-02-09 20:10:30', 'hb0730', 1, 1,
        '1888547212428357633', '体育保健', '30', '{
    \"type\": \"休闲服务\"
  }', NULL, 30);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888561103736713230', '2025-02-09 20:10:30', 'hb0730', '2025-02-09 20:10:30', 'hb0730', 1, 1,
        '1888547212428357633', '美容整形', '29', '{
    \"type\": \"休闲服务\"
  }', NULL, 29);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888561103736713231', '2025-02-09 20:10:30', 'hb0730', '2025-02-09 20:10:30', 'hb0730', 1, 1,
        '1888547212428357633', '美发培训', '28', '{
    \"type\": \"休闲服务\"
  }', NULL, 28);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888561103736713232', '2025-02-09 20:10:30', 'hb0730', '2025-02-09 20:10:30', 'hb0730', 1, 1,
        '1888547212428357633', '美体师', '27', '{
    \"type\": \"休闲服务\"
  }', NULL, 27);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888561103736713233', '2025-02-09 20:10:30', 'hb0730', '2025-02-09 20:10:30', 'hb0730', 1, 1,
        '1888547212428357633', '化妆师', '26', '{
    \"type\": \"休闲服务\"
  }', NULL, 26);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888561103736713234', '2025-02-09 20:10:30', 'hb0730', '2025-02-09 20:10:30', 'hb0730', 1, 1,
        '1888547212428357633', '发型师', '25', '{
    \"type\": \"休闲服务\"
  }', NULL, 25);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888561103736713235', '2025-02-09 20:10:30', 'hb0730', '2025-02-09 20:10:30', 'hb0730', 1, 1,
        '1888547212428357633', '美容师', '24', '{
    \"type\": \"休闲服务\"
  }', NULL, 24);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888561103736713236', '2025-02-09 20:10:30', 'hb0730', '2025-02-09 20:10:30', 'hb0730', 1, 1,
        '1888547212428357633', '服务员', '23', '{
    \"type\": \"休闲服务\"
  }', NULL, 23);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888561103736713237', '2025-02-09 20:10:30', 'hb0730', '2025-02-09 20:10:30', 'hb0730', 1, 1,
        '1888547212428357633', '咖啡师', '22', '{
    \"type\": \"休闲服务\"
  }', NULL, 22);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888561103736713238', '2025-02-09 20:10:30', 'hb0730', '2025-02-09 20:10:30', 'hb0730', 1, 1,
        '1888547212428357633', '调酒师', '21', '{
    \"type\": \"休闲服务\"
  }', NULL, 21);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888561103736713239', '2025-02-09 20:10:30', 'hb0730', '2025-02-09 20:10:30', 'hb0730', 1, 1,
        '1888547212428357633', '面点师', '20', '{
    \"type\": \"休闲服务\"
  }', NULL, 20);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888561103736713240', '2025-02-09 20:10:30', 'hb0730', '2025-02-09 20:10:30', 'hb0730', 1, 1,
        '1888547212428357633', '中餐厨师', '19', '{
    \"type\": \"休闲服务\"
  }', NULL, 19);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888561103736713241', '2025-02-09 20:10:30', 'hb0730', '2025-02-09 20:10:30', 'hb0730', 1, 1,
        '1888547212428357633', '西餐厨师', '18', '{
    \"type\": \"休闲服务\"
  }', NULL, 18);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888561103736713242', '2025-02-09 20:10:30', 'hb0730', '2025-02-09 20:10:30', 'hb0730', 1, 1,
        '1888547212428357633', '餐饮', '17', '{
    \"type\": \"休闲服务\"
  }', NULL, 17);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888561103736713243', '2025-02-09 20:10:30', 'hb0730', '2025-02-09 20:10:30', 'hb0730', 1, 1,
        '1888547212428357633', '厨师', '16', '{
    \"type\": \"休闲服务\"
  }', NULL, 16);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888561103736713244', '2025-02-09 20:10:30', 'hb0730', '2025-02-09 20:10:30', 'hb0730', 1, 1,
        '1888547212428357633', '餐厅服务', '15', '{
    \"type\": \"休闲服务\"
  }', NULL, 15);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888561103736713245', '2025-02-09 20:10:30', 'hb0730', '2025-02-09 20:10:30', 'hb0730', 1, 1,
        '1888547212428357633', '客房服务', '14', '{
    \"type\": \"休闲服务\"
  }', NULL, 14);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888561103736713246', '2025-02-09 20:10:30', 'hb0730', '2025-02-09 20:10:30', 'hb0730', 1, 1,
        '1888547212428357633', '行李员', '13', '{
    \"type\": \"休闲服务\"
  }', NULL, 13);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888561103736713247', '2025-02-09 20:10:30', 'hb0730', '2025-02-09 20:10:30', 'hb0730', 1, 1,
        '1888547212428357633', '商务中心', '12', '{
    \"type\": \"休闲服务\"
  }', NULL, 12);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888561103736713248', '2025-02-09 20:10:30', 'hb0730', '2025-02-09 20:10:30', 'hb0730', 1, 1,
        '1888547212428357633', '总机', '11', '{
    \"type\": \"休闲服务\"
  }', NULL, 11);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888561103736713249', '2025-02-09 20:10:30', 'hb0730', '2025-02-09 20:10:30', 'hb0730', 1, 1,
        '1888547212428357633', '礼宾', '10', '{
    \"type\": \"休闲服务\"
  }', NULL, 10);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888561103736713250', '2025-02-09 20:10:30', 'hb0730', '2025-02-09 20:10:30', 'hb0730', 1, 1,
        '1888547212428357633', '大堂经理', '9', '{
    \"type\": \"休闲服务\"
  }', NULL, 9);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888561103736713251', '2025-02-09 20:10:30', 'hb0730', '2025-02-09 20:10:30', 'hb0730', 1, 1,
        '1888547212428357633', '酒店', '8', '{
    \"type\": \"休闲服务\"
  }', NULL, 8);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888561103736713252', '2025-02-09 20:10:30', 'hb0730', '2025-02-09 20:10:30', 'hb0730', 1, 1,
        '1888547212428357633', '会展策划', '7', '{
    \"type\": \"休闲服务\"
  }', NULL, 7);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888561103736713253', '2025-02-09 20:10:30', 'hb0730', '2025-02-09 20:10:30', 'hb0730', 1, 1,
        '1888547212428357633', '票务', '6', '{
    \"type\": \"休闲服务\"
  }', NULL, 6);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888561103736713254', '2025-02-09 20:10:30', 'hb0730', '2025-02-09 20:10:30', 'hb0730', 1, 1,
        '1888547212428357633', '领队', '5', '{
    \"type\": \"休闲服务\"
  }', NULL, 5);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888561103736713255', '2025-02-09 20:10:30', 'hb0730', '2025-02-09 20:10:30', 'hb0730', 1, 1,
        '1888547212428357633', '计调', '4', '{
    \"type\": \"休闲服务\"
  }', NULL, 4);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888561103736713256', '2025-02-09 20:10:30', 'hb0730', '2025-02-09 20:10:30', 'hb0730', 1, 1,
        '1888547212428357633', '线路策划', '3', '{
    \"type\": \"休闲服务\"
  }', NULL, 3);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888561103736713257', '2025-02-09 20:10:30', 'hb0730', '2025-02-09 20:10:30', 'hb0730', 1, 1,
        '1888547212428357633', '旅游顾问', '2', '{
    \"type\": \"休闲服务\"
  }', NULL, 2);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888561103736713258', '2025-02-09 20:10:30', 'hb0730', '2025-02-09 20:10:30', 'hb0730', 1, 1,
        '1888547212428357633', '导游', '1', '{
    \"type\": \"休闲服务\"
  }', NULL, 1);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888564627652521996', '2025-02-09 20:24:30', 'hb0730', '2025-02-09 20:24:30', 'hb0730', 1, 1,
        '1888547212428357633', '调研员', '20', '{
    \"type\": \"教育培训\"
  }', NULL, 20);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888564627652521997', '2025-02-09 20:24:30', 'hb0730', '2025-02-09 20:24:30', 'hb0730', 1, 1,
        '1888547212428357633', '财务咨询', '19', '{
    \"type\": \"教育培训\"
  }', NULL, 19);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888564627652521998', '2025-02-09 20:24:30', 'hb0730', '2025-02-09 20:24:30', 'hb0730', 1, 1,
        '1888547212428357633', '心理咨询', '18', '{
    \"type\": \"教育培训\"
  }', NULL, 18);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888564627652521999', '2025-02-09 20:24:30', 'hb0730', '2025-02-09 20:24:30', 'hb0730', 1, 1,
        '1888547212428357633', '翻译咨询', '17', '{
    \"type\": \"教育培训\"
  }', NULL, 17);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888564627652522000', '2025-02-09 20:24:30', 'hb0730', '2025-02-09 20:24:30', 'hb0730', 1, 1,
        '1888547212428357633', '法律咨询', '16', '{
    \"type\": \"教育培训\"
  }', NULL, 16);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888564627652522001', '2025-02-09 20:24:30', 'hb0730', '2025-02-09 20:24:30', 'hb0730', 1, 1,
        '1888547212428357633', '课程设计', '15', '{
    \"type\": \"教育培训\"
  }', NULL, 15);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888564627652522002', '2025-02-09 20:24:30', 'hb0730', '2025-02-09 20:24:30', 'hb0730', 1, 1,
        '1888547212428357633', '课程顾问', '14', '{
    \"type\": \"教育培训\"
  }', NULL, 14);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888564627652522003', '2025-02-09 20:24:30', 'hb0730', '2025-02-09 20:24:30', 'hb0730', 1, 1,
        '1888547212428357633', '教务助理', '13', '{
    \"type\": \"教育培训\"
  }', NULL, 13);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888564627652522004', '2025-02-09 20:24:30', 'hb0730', '2025-02-09 20:24:30', 'hb0730', 1, 1,
        '1888547212428357633', '培训讲师', '12', '{
    \"type\": \"教育培训\"
  }', NULL, 12);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888564627652522005', '2025-02-09 20:24:30', 'hb0730', '2025-02-09 20:24:30', 'hb0730', 1, 1,
        '1888547212428357633', '家教', '11', '{
    \"type\": \"教育培训\"
  }', NULL, 11);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888564627652522006', '2025-02-09 20:24:30', 'hb0730', '2025-02-09 20:24:30', 'hb0730', 1, 1,
        '1888547212428357633', '幼师', '10', '{
    \"type\": \"教育培训\"
  }', NULL, 10);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888564627652522007', '2025-02-09 20:24:30', 'hb0730', '2025-02-09 20:24:30', 'hb0730', 1, 1,
        '1888547212428357633', '历史老师', '9', '{
    \"type\": \"教育培训\"
  }', NULL, 9);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888564627652522008', '2025-02-09 20:24:30', 'hb0730', '2025-02-09 20:24:30', 'hb0730', 1, 1,
        '1888547212428357633', '政治老师', '8', '{
    \"type\": \"教育培训\"
  }', NULL, 8);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888564627652522009', '2025-02-09 20:24:30', 'hb0730', '2025-02-09 20:24:30', 'hb0730', 1, 1,
        '1888547212428357633', '物理老师', '7', '{
    \"type\": \"教育培训\"
  }', NULL, 7);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888564627652522010', '2025-02-09 20:24:30', 'hb0730', '2025-02-09 20:24:30', 'hb0730', 1, 1,
        '1888547212428357633', '数学老师', '6', '{
    \"type\": \"教育培训\"
  }', NULL, 6);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888564627652522011', '2025-02-09 20:24:30', 'hb0730', '2025-02-09 20:24:30', 'hb0730', 1, 1,
        '1888547212428357633', '化学老师', '5', '{
    \"type\": \"教育培训\"
  }', NULL, 5);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888564627652522012', '2025-02-09 20:24:30', 'hb0730', '2025-02-09 20:24:30', 'hb0730', 1, 1,
        '1888547212428357633', '语文老师', '4', '{
    \"type\": \"教育培训\"
  }', NULL, 4);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888564627652522013', '2025-02-09 20:24:30', 'hb0730', '2025-02-09 20:24:30', 'hb0730', 1, 1,
        '1888547212428357633', '音乐老师', '3', '{
    \"type\": \"教育培训\"
  }', NULL, 3);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888564627652522014', '2025-02-09 20:24:30', 'hb0730', '2025-02-09 20:24:30', 'hb0730', 1, 1,
        '1888547212428357633', '英语老师', '2', '{
    \"type\": \"教育培训\"
  }', NULL, 2);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888564627652522015', '2025-02-09 20:24:30', 'hb0730', '2025-02-09 20:24:30', 'hb0730', 1, 1,
        '1888547212428357633', '舞蹈老师', '1', '{
    \"type\": \"教育培训\"
  }', NULL, 1);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888567792686903301', '2025-02-09 20:37:04', 'hb0730', '2025-02-09 20:37:04', 'hb0730', 1, 1,
        '1888547212428357633', '歌手', '29', '{
    \"type\": \"广告传媒\"
  }', NULL, 29);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888567792686903302', '2025-02-09 20:37:04', 'hb0730', '2025-02-09 20:37:04', 'hb0730', 1, 1,
        '1888547212428357633', '演员', '27', '{
    \"type\": \"广告传媒\"
  }', NULL, 27);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888567792686903303', '2025-02-09 20:37:04', 'hb0730', '2025-02-09 20:37:04', 'hb0730', 1, 1,
        '1888547212428357633', '模特', '26', '{
    \"type\": \"广告传媒\"
  }', NULL, 26);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888567792686903304', '2025-02-09 20:37:04', 'hb0730', '2025-02-09 20:37:04', 'hb0730', 1, 1,
        '1888547212428357633', '主持人', '25', '{
    \"type\": \"广告传媒\"
  }', NULL, 25);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888567792686903305', '2025-02-09 20:37:04', 'hb0730', '2025-02-09 20:37:04', 'hb0730', 1, 1,
        '1888547212428357633', '经纪人', '24', '{
    \"type\": \"广告传媒\"
  }', NULL, 24);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888567792686903306', '2025-02-09 20:37:04', 'hb0730', '2025-02-09 20:37:04', 'hb0730', 1, 1,
        '1888547212428357633', '出版', '23', '{
    \"type\": \"广告传媒\"
  }', NULL, 23);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888567792686903307', '2025-02-09 20:37:04', 'hb0730', '2025-02-09 20:37:04', 'hb0730', 1, 1,
        '1888547212428357633', '排版设计', '22', '{
    \"type\": \"广告传媒\"
  }', NULL, 22);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888567792686903308', '2025-02-09 20:37:04', 'hb0730', '2025-02-09 20:37:04', 'hb0730', 1, 1,
        '1888547212428357633', '美术编辑', '21', '{
    \"type\": \"广告传媒\"
  }', NULL, 21);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888567792686903309', '2025-02-09 20:37:04', 'hb0730', '2025-02-09 20:37:04', 'hb0730', 1, 1,
        '1888547212428357633', '记者', '20', '{
    \"type\": \"广告传媒\"
  }', NULL, 20);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888567792686903310', '2025-02-09 20:37:04', 'hb0730', '2025-02-09 20:37:04', 'hb0730', 1, 1,
        '1888547212428357633', '编辑', '19', '{
    \"type\": \"广告传媒\"
  }', NULL, 19);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888567792686903311', '2025-02-09 20:37:04', 'hb0730', '2025-02-09 20:37:04', 'hb0730', 1, 1,
        '1888547212428357633', '主编', '18', '{
    \"type\": \"广告传媒\"
  }', NULL, 18);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888567792686903312', '2025-02-09 20:37:04', 'hb0730', '2025-02-09 20:37:04', 'hb0730', 1, 1,
        '1888547212428357633', '灯光师', '17', '{
    \"type\": \"广告传媒\"
  }', NULL, 17);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888567792686903313', '2025-02-09 20:37:04', 'hb0730', '2025-02-09 20:37:04', 'hb0730', 1, 1,
        '1888547212428357633', '配音员', '16', '{
    \"type\": \"广告传媒\"
  }', NULL, 16);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888567792686903314', '2025-02-09 20:37:04', 'hb0730', '2025-02-09 20:37:04', 'hb0730', 1, 1,
        '1888547212428357633', '音效师', '15', '{
    \"type\": \"广告传媒\"
  }', NULL, 15);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888567792686903315', '2025-02-09 20:37:04', 'hb0730', '2025-02-09 20:37:04', 'hb0730', 1, 1,
        '1888547212428357633', '后期制作', '14', '{
    \"type\": \"广告传媒\"
  }', NULL, 14);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888567792686903316', '2025-02-09 20:37:04', 'hb0730', '2025-02-09 20:37:04', 'hb0730', 1, 1,
        '1888547212428357633', '摄像师', '13', '{
    \"type\": \"广告传媒\"
  }', NULL, 13);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888567792686903317', '2025-02-09 20:37:04', 'hb0730', '2025-02-09 20:37:04', 'hb0730', 1, 1,
        '1888547212428357633', '艺术指导', '12', '{
    \"type\": \"广告传媒\"
  }', NULL, 12);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888567792686903318', '2025-02-09 20:37:04', 'hb0730', '2025-02-09 20:37:04', 'hb0730', 1, 1,
        '1888547212428357633', '影视制作', '10', '{
    \"type\": \"广告传媒\"
  }', NULL, 10);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888567792686903319', '2025-02-09 20:37:04', 'hb0730', '2025-02-09 20:37:04', 'hb0730', 1, 1,
        '1888547212428357633', '导演助理', '9', '{
    \"type\": \"广告传媒\"
  }', NULL, 9);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888567792686903320', '2025-02-09 20:37:04', 'hb0730', '2025-02-09 20:37:04', 'hb0730', 1, 1,
        '1888547212428357633', '编导', '8', '{
    \"type\": \"广告传媒\"
  }', NULL, 8);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888567792686903321', '2025-02-09 20:37:04', 'hb0730', '2025-02-09 20:37:04', 'hb0730', 1, 1,
        '1888547212428357633', '导演', '7', '{
    \"type\": \"广告传媒\"
  }', NULL, 7);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888567792686903322', '2025-02-09 20:37:04', 'hb0730', '2025-02-09 20:37:04', 'hb0730', 1, 1,
        '1888547212428357633', '广告执行', '6', '{
    \"type\": \"广告传媒\"
  }', NULL, 6);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888567792686903323', '2025-02-09 20:37:04', 'hb0730', '2025-02-09 20:37:04', 'hb0730', 1, 1,
        '1888547212428357633', '文案策划', '5', '{
    \"type\": \"广告传媒\"
  }', NULL, 5);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888567792686903324', '2025-02-09 20:37:04', 'hb0730', '2025-02-09 20:37:04', 'hb0730', 1, 1,
        '1888547212428357633', '广告设计', '4', '{
    \"type\": \"广告传媒\"
  }', NULL, 4);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888567792686903325', '2025-02-09 20:37:04', 'hb0730', '2025-02-09 20:37:04', 'hb0730', 1, 1,
        '1888547212428357633', '广告优化', '3', '{
    \"type\": \"广告传媒\"
  }', NULL, 3);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888567792686903326', '2025-02-09 20:37:04', 'hb0730', '2025-02-09 20:37:04', 'hb0730', 1, 1,
        '1888547212428357633', '广告销售', '2', '{
    \"type\": \"广告传媒\"
  }', NULL, 2);
INSERT INTO `sys_dict_item` (`id`, `created`, `created_by`, `modified`, `modified_by`, `status`, `del_flag`, `dict_id`,
                             `item_text`, `item_value`, `extra`, `description`, `sort`)
VALUES ('1888567792686903327', '2025-02-09 20:37:04', 'hb0730', '2025-02-09 20:37:04', 'hb0730', 1, 1,
        '1888547212428357633', '客户执行', '1', '{
    \"type\": \"广告传媒\"
  }', NULL, 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_operator_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_operator_log`;
CREATE TABLE `sys_operator_log`
(
    `id`            varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `created`       datetime                                                      DEFAULT NULL COMMENT '创建时间',
    `created_by`    varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '创建人',
    `modified`      datetime                                                      DEFAULT NULL COMMENT '更新时间',
    `modified_by`   varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '更新人',
    `operator_id`   varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '操作人ID',
    `operator`      varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '操作人',
    `trace_id`      varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'traceId',
    `address`       varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'ip',
    `location`      varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '所在地',
    `user_agent`    varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'userAgent',
    `module`        varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '操作模块',
    `type`          varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '动作',
    `risk_level`    varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '风险等级 ',
    `log_info`      varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作内容',
    `extra`         json                                                          DEFAULT NULL COMMENT '请求内容',
    `result`        tinyint(1)                                                    DEFAULT NULL COMMENT '结果',
    `error_message` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '错误信息',
    `return_value`  json                                                          DEFAULT NULL COMMENT '响应内容',
    `duration`      int                                                           DEFAULT NULL COMMENT '操作时间',
    `start_time`    datetime                                                      DEFAULT NULL COMMENT '开始时间',
    `end_time`      datetime                                                      DEFAULT NULL COMMENT '结束时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='基础-操作日志';

-- ----------------------------
-- Records of sys_operator_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`
(
    `id`               varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `created`          datetime                                                      DEFAULT NULL COMMENT '创建时间',
    `created_by`       varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '创建人',
    `modified`         datetime                                                      DEFAULT NULL COMMENT '更新时间',
    `modified_by`      varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '更新人',
    `parent_id`        varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '父id',
    `is_leaf`          tinyint(1)                                                    DEFAULT '1' COMMENT '是否叶子节点: 1:是 0:不是',
    `route_path`       varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '路由地址',
    `route_name`       varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '路由名称（必须保持唯一）',
    `redirect`         varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '路由重定向',
    `component`        varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '路由组件',
    `title`            varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '菜单名称',
    `icon`             varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '菜单图标',
    `is_hidden`        tinyint(1)                                                    DEFAULT '0' COMMENT '是否隐藏 0 否 1是',
    `is_keep_alive`    tinyint(1)                                                    DEFAULT '0' COMMENT '是否缓存 0 否 1 是',
    `is_affix`         tinyint(1)                                                    DEFAULT '0' COMMENT '是否固定tag 0 否 1是',
    `is_full_screen`   tinyint(1)                                                    DEFAULT '0' COMMENT '是否大屏 0否 1是',
    `is_require_login` tinyint(1)                                                    DEFAULT '1' COMMENT '是否登陆 0 否 1是',
    `frame_src`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'iframe_url',
    `menu_type`        tinyint(1)                                                    DEFAULT NULL COMMENT '菜单类型 0 菜单 1 frame 2 外链 4 按钮',
    `perms`            varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '权限标识符',
    `sort`             int                                                           DEFAULT NULL COMMENT '排序',
    `status`           tinyint(1)                                                    DEFAULT NULL COMMENT '状态 0 禁用 1 启用',
    `del_flag`         tinyint(1)                                                    DEFAULT '0' COMMENT '是否删除 0 否 1是',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='基础-菜单权限';

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
BEGIN;
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1842431703832432641', '2024-10-05 13:08:44', 'admin', '2024-12-24 12:42:11', 'admin', NULL, 1, '/dashboard',
        'Dashboard', NULL, '/dashboard/index', '首页', 'iconify-ep:home-filled', 0, 1, 1, 0, 1, NULL, 0, NULL, 1, 1, 0);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1842552969956536321', '2024-10-05 21:10:36', 'admin', '2025-02-25 21:38:28', 'admin', NULL, 0, '/system',
        'System', '/system/user', '', '系统管理', 'iconify-ri:tools-fill', 0, 0, 0, 0, 1, NULL, 0, NULL, 90, 1, 0);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1842806617647144962', '2024-10-06 13:58:30', 'admin', '2025-02-25 21:38:16', 'admin', '1846087588270120962', 0,
        '/system/user', 'SystemUser', NULL, '/system/user/index', '用户管理', 'iconify-ep:user-filled', 0, 0, 0, 0, 1,
        NULL, 0, NULL, 1, 1, 0);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1842808380282429442', '2024-10-06 14:05:30', 'admin', '2024-10-06 14:05:30', 'admin', '1842806617647144962', 1,
        NULL, NULL, NULL, NULL, '用户查询', NULL, 0, 1, 0, 0, 1, NULL, 3, 'sys:user:query', 1, 1, 0);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1842808807199662081', '2024-10-06 14:07:12', 'admin', '2025-02-25 21:38:20', 'admin', '1846087588270120962', 0,
        '/system/role', 'SystemRole', NULL, '/system/role/index', '角色管理', 'iconify-ri:admin-fill', 0, 0, 0, 0, 1,
        NULL, 0, NULL, 2, 1, 0);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1842812170972770306', '2024-10-06 14:20:34', 'admin', '2025-02-25 21:38:35', 'admin', '1842552969956536321', 0,
        '/system/menu', 'SystemMenu', NULL, '/system/permission/index', '菜单管理', 'iconify-ep:menu', 0, 0, 0, 0, 1,
        NULL, 0, NULL, 1, 1, 0);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1843577405094551554', '2024-10-08 17:01:20', 'admin', '2025-06-13 14:19:40', 'admin', NULL, 0, '/sub/domains',
        'Domains', NULL, '', '域名系统', 'svg-icon:domains', 0, 0, 0, 0, 1, NULL, 0, NULL, 20, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1846010772209635329', '2024-10-15 10:10:40', 'admin', '2024-10-15 10:10:40', 'admin', '1842806617647144962', 1,
        NULL, NULL, NULL, NULL, '创建用户', NULL, 0, 1, 0, 0, 1, NULL, 3, 'sys:user:add', 2, 1, 0);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1846010824881704962', '2024-10-15 10:10:53', 'admin', '2024-10-15 10:10:53', 'admin', '1842806617647144962', 1,
        NULL, NULL, NULL, NULL, '更新用户', NULL, 0, 1, 0, 0, 1, NULL, 3, 'sys:user:update', 3, 1, 0);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1846010936882204674', '2024-10-15 10:11:19', 'admin', '2024-10-15 10:11:19', 'admin', '1842806617647144962', 1,
        NULL, NULL, NULL, NULL, '删除用户', NULL, 0, 1, 0, 0, 1, NULL, 3, 'sys:user:delete', 4, 1, 0);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1846011021003165697', '2024-10-15 10:11:39', 'admin', '2024-10-15 10:11:39', 'admin', '1842808807199662081', 1,
        NULL, NULL, NULL, NULL, '角色查询', NULL, 0, 1, 0, 0, 1, NULL, 3, 'sys:role:query', 1, 1, 0);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1846011113852473346', '2024-10-15 10:12:01', 'admin', '2024-10-15 10:12:01', 'admin', '1842808807199662081', 1,
        NULL, NULL, NULL, NULL, '创建角色', NULL, 0, 1, 0, 0, 1, NULL, 3, 'sys:role:add', 2, 1, 0);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1846011187743526913', '2024-10-15 10:12:19', 'admin', '2024-10-15 10:12:19', 'admin', '1842808807199662081', 1,
        NULL, NULL, NULL, NULL, '更新角色', NULL, 0, 1, 0, 0, 1, NULL, 3, 'sys:role:update', 3, 1, 0);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1846011245834637313', '2024-10-15 10:12:33', 'admin', '2024-10-15 10:12:33', 'admin', '1842808807199662081', 1,
        NULL, NULL, NULL, NULL, '删除角色', NULL, 0, 1, 0, 0, 1, NULL, 3, 'sys:role:delete', 4, 1, 0);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1846011357717696514', '2024-10-15 10:13:00', 'admin', '2024-10-15 10:13:00', 'admin', '1842808807199662081', 1,
        NULL, NULL, NULL, NULL, '角色授权', NULL, 0, 1, 0, 0, 1, NULL, 3, 'sys:role:grant', 5, 1, 0);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1846011431659081730', '2024-10-15 10:13:17', 'admin', '2024-10-15 10:13:17', 'admin', '1842812170972770306', 1,
        NULL, NULL, NULL, NULL, '菜单查询', NULL, 0, 1, 0, 0, 1, NULL, 3, 'sys:permission:query', 1, 1, 0);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1846011516342079490', '2024-10-15 10:13:37', 'admin', '2024-10-15 10:13:37', 'admin', '1842812170972770306', 1,
        NULL, NULL, NULL, NULL, '创建菜单', NULL, 0, 1, 0, 0, 1, NULL, 3, 'sys:permission:add', 2, 1, 0);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1846011607274590209', '2024-10-15 10:13:59', 'admin', '2024-10-15 10:13:59', 'admin', '1842812170972770306', 1,
        NULL, NULL, NULL, NULL, '更新菜单', NULL, 0, 1, 0, 0, 1, NULL, 3, 'sys:permission:update', 3, 1, 0);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1846011665835462658', '2024-10-15 10:14:13', 'admin', '2024-10-15 10:14:13', 'admin', '1842812170972770306', 1,
        NULL, NULL, NULL, NULL, '删除菜单', NULL, 0, 1, 0, 0, 1, NULL, 3, 'sys:permission:delete', 3, 1, 0);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1846012012519854081', '2024-10-15 10:15:36', 'admin', '2025-02-25 21:38:38', 'admin', '1842552969956536321', 0,
        '/system/dict', 'SystemDict', NULL, '/system/dict/index', '字典管理', 'iconify-ri:book-read-fill', 0, 0, 0, 0,
        1, NULL, 0, NULL, 2, 1, 0);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1846012485125640193', '2024-10-15 10:17:28', 'admin', '2024-10-15 10:17:28', 'admin', '1846012012519854081', 1,
        NULL, NULL, NULL, NULL, '字典查询', NULL, 0, 1, 0, 0, 1, NULL, 3, 'sys:dict:query', 1, 1, 0);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1846012580269232129', '2024-10-15 10:17:51', 'admin', '2024-10-15 10:17:51', 'admin', '1846012012519854081', 1,
        NULL, NULL, NULL, NULL, '创建字典', NULL, 0, 1, 0, 0, 1, NULL, 3, 'sys:dict:add', 2, 1, 0);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1846012650586738690', '2024-10-15 10:18:08', 'admin', '2024-10-15 10:18:08', 'admin', '1846012012519854081', 1,
        NULL, NULL, NULL, NULL, '更新字典', NULL, 0, 1, 0, 0, 1, NULL, 3, 'sys:dict:update', 3, 1, 0);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1846012710917607426', '2024-10-15 10:18:22', 'admin', '2024-10-15 10:18:22', 'admin', '1846012012519854081', 1,
        NULL, NULL, NULL, NULL, '删除字典', NULL, 0, 1, 0, 0, 1, NULL, 3, 'sys:dict:delete', 4, 1, 0);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1846012774335483905', '2024-10-15 10:18:37', 'admin', '2024-10-15 15:11:08', 'admin', '1846012012519854081', 1,
        NULL, NULL, NULL, NULL, '字典项查询', NULL, 0, 1, 0, 0, 1, NULL, 3, 'sys:dict:item:query', 5, 1, 0);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1846071236226576385', '2024-10-15 14:10:56', 'admin', '2024-10-15 14:10:56', 'admin', '1842806617647144962', 1,
        NULL, NULL, NULL, NULL, '重置密码', NULL, 0, 1, 0, 0, 1, NULL, 3, 'sys:user:reset_password', 5, 1, 0);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1846071412664168450', '2024-10-15 14:11:38', 'admin', '2024-10-15 14:11:49', 'admin', '1842806617647144962', 1,
        NULL, NULL, NULL, NULL, '角色授权', NULL, 0, 1, 0, 0, 1, NULL, 3, 'sys:user:assign_role', 6, 1, 0);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1846086497398726658', '2024-10-15 15:11:34', 'admin', '2024-10-15 15:11:34', 'admin', '1846012012519854081', 1,
        NULL, NULL, NULL, NULL, '创建字典项', NULL, 0, 1, 0, 0, 1, NULL, 3, 'sys:dict:item:add', 6, 1, 0);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1846086560053239810', '2024-10-15 15:11:49', 'admin', '2024-10-15 15:11:49', 'admin', '1846012012519854081', 1,
        NULL, NULL, NULL, NULL, '更新字典项', NULL, 0, 1, 0, 0, 1, NULL, 3, 'sys:dict:item:update', 7, 1, 0);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1846086625123672066', '2024-10-15 15:12:05', 'admin', '2024-10-15 15:12:05', 'admin', '1846012012519854081', 1,
        NULL, NULL, NULL, NULL, '删除字典项', NULL, 0, 1, 0, 0, 1, NULL, 3, 'sys:dict:item:delete', 8, 1, 0);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1846087588270120962', '2024-10-15 15:15:54', 'admin', '2025-02-25 21:38:11', 'admin', NULL, 0, '/user/manager',
        'userManager', '/system/user', NULL, '用户管理', 'iconify-ep:user-filled', 0, 0, 0, 0, 1, NULL, 0, NULL, 80, 1,
        0);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1846117585278017538', '2024-10-15 17:15:06', 'admin', '2025-06-13 14:21:10', 'admin', '1846356327611854850', 0,
        '/message/template', 'MessageTemplate', NULL, '/system/message/template/index', '消息模版',
        'iconify-ep:message', 0, 0, 0, 0, 1, NULL, 0, NULL, 2, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1846356327611854850', '2024-10-16 09:03:47', 'admin', '2025-06-13 14:21:10', 'admin', NULL, 0, '/msg/manager',
        'MessageManager', NULL, NULL, '消息管理', 'iconify-ri:message-2-fill', 0, 0, 0, 0, 1, NULL, 0, NULL, 70, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1846356688758206466', '2024-10-16 09:05:13', 'admin', '2025-06-13 14:21:10', 'admin', '1846356327611854850', 0,
        '/message/send', 'MessageManager', NULL, '/system/message/send/index', '消息管理', 'iconify-ri:message-3-fill',
        0, 0, 0, 0, 1, NULL, 0, NULL, 1, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1846365090846666754', '2024-10-16 09:38:36', 'admin', '2025-06-13 14:21:10', 'admin', '1846117585278017538', 1,
        NULL, NULL, NULL, NULL, '消息模板查询', NULL, 0, 1, 0, 0, 1, NULL, 3, 'sys:message:template:query', 1, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1846365154667196418', '2024-10-16 09:38:51', 'admin', '2025-06-13 14:21:10', 'admin', '1846117585278017538', 1,
        NULL, NULL, NULL, NULL, '新增消息模板', NULL, 0, 1, 0, 0, 1, NULL, 3, 'sys:message:template:add', 2, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1846365207100190721', '2024-10-16 09:39:04', 'admin', '2025-06-13 14:21:10', 'admin', '1846117585278017538', 1,
        NULL, NULL, NULL, NULL, '更新消息模板', NULL, 0, 1, 0, 0, 1, NULL, 3, 'sys:message:template:update', 3, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1846365289946083330', '2024-10-16 09:39:24', 'admin', '2025-06-13 14:21:10', 'admin', '1846117585278017538', 1,
        NULL, NULL, NULL, NULL, '删除消息模板', NULL, 0, 1, 0, 0, 1, NULL, 3, 'sys:message:template:delete', 4, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1847213529562165250', '2024-10-18 17:50:00', 'admin', '2025-06-13 14:21:10', 'admin', '1846356688758206466', 1,
        NULL, NULL, NULL, NULL, '消息查询', NULL, 0, 1, 0, 0, 1, NULL, 3, 'sys:message:query', 1, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1847213584360747010', '2024-10-18 17:50:13', 'admin', '2025-06-13 14:21:10', 'admin', '1846356688758206466', 1,
        NULL, NULL, NULL, NULL, '新增消息', NULL, 0, 1, 0, 0, 1, NULL, 3, 'sys:message:add', 2, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1847213642367971330', '2024-10-18 17:50:27', 'admin', '2025-06-13 14:21:10', 'admin', '1846356688758206466', 1,
        NULL, NULL, NULL, NULL, '更新消息', NULL, 0, 1, 0, 0, 1, NULL, 3, 'sys:message:update', 3, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1847213757245763586', '2024-10-18 17:50:54', 'admin', '2025-06-13 14:21:10', 'admin', '1846356688758206466', 1,
        NULL, NULL, NULL, NULL, '删除消息', NULL, 0, 1, 0, 0, 1, NULL, 3, 'sys:message:delete', 4, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1847214971450634242', '2024-10-18 17:55:43', 'admin', '2025-02-25 21:37:44', 'admin', NULL, 0, '/job/manager',
        'JobManager', NULL, NULL, '计划任务', 'iconify-ri:play-list-2-fill', 0, 0, 0, 0, 1, NULL, 0, NULL, 60, 1, 0);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1847215993602519042', '2024-10-18 17:59:47', 'admin', '2025-02-25 21:37:38', 'admin', '1847214971450634242', 0,
        '/job/list', 'JobList', NULL, '/system/job/list/index', '任务列表', 'iconify-ri:list-check', 0, 0, 0, 0, 1,
        NULL, 0, NULL, 1, 1, 0);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1848171817598545921', '2024-10-21 09:17:53', 'admin', '2025-02-25 21:37:35', 'admin', '1847214971450634242', 0,
        '/job/log', 'JobLog', NULL, '/system/job/log/index', '任务日志', 'iconify-ep:clock', 0, 0, 0, 0, 1, NULL, 0,
        NULL, 2, 1, 0);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1848171878990573570', '2024-10-21 09:18:08', 'admin', '2024-10-21 09:18:32', 'admin', '1847215993602519042', 1,
        NULL, NULL, NULL, NULL, '新增任务', NULL, 0, 1, 0, 0, 1, NULL, 3, 'sys:job:add', 2, 1, 0);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1848171947496140801', '2024-10-21 09:18:24', 'admin', '2024-10-21 09:18:24', 'admin', '1847215993602519042', 1,
        NULL, NULL, NULL, NULL, '查询任务', NULL, 0, 1, 0, 0, 1, NULL, 3, 'sys:job:query', 1, 1, 0);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1848172081629982721', '2024-10-21 09:18:56', 'admin', '2024-10-21 09:18:56', 'admin', '1847215993602519042', 1,
        NULL, NULL, NULL, NULL, '更新任务', NULL, 0, 1, 0, 0, 1, NULL, 3, 'sys:job:update', 3, 1, 0);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1848172139419103233', '2024-10-21 09:19:10', 'admin', '2024-10-21 09:19:10', 'admin', '1847215993602519042', 1,
        NULL, NULL, NULL, NULL, '删除任务', NULL, 0, 1, 0, 0, 1, NULL, 3, 'sys:job:delete', 4, 1, 0);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1848172370932101121', '2024-10-21 09:20:05', 'admin', '2024-10-21 09:20:05', 'admin', '1847215993602519042', 1,
        NULL, NULL, NULL, NULL, '执行任务', NULL, 0, 1, 0, 0, 1, NULL, 3, 'sys:job:operate', 5, 1, 0);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1848172465790480385', '2024-10-21 09:20:28', 'admin', '2024-10-21 09:20:28', 'admin', '1848171817598545921', 1,
        NULL, NULL, NULL, NULL, '查询任务日志', NULL, 0, 1, 0, 0, 1, NULL, 3, 'sys:job:log:query', 1, 1, 0);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1848209906077364226', '2024-10-21 11:49:14', 'admin', '2025-06-13 14:19:40', 'admin', '1843577405094551554', 1,
        '/domains/home', 'DomainsHome', NULL, '/domains/home/index', '看板', 'i-ep:data-analysis', 0, 0, 0, 0, 1, NULL,
        0, NULL, 10, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1848228525196800001', '2024-10-21 13:03:13', 'admin', '2025-06-13 14:19:40', 'admin', '1843577405094551554', 1,
        '/domains/manager', 'DomainsManager', NULL, '/domains/manager/index', '域名监控', 'i-ep:data-line', 0, 0, 0, 0,
        1, NULL, 0, NULL, 20, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1848228945101156354', '2024-10-21 13:04:54', 'admin', '2025-06-13 14:19:40', 'admin', '1843577405094551554', 1,
        '/domains/website', 'DonainsWebsite', NULL, '/domains/website/index', '网站监控', 'i-ri:planet-fill', 0, 0, 0,
        0, 1, NULL, 0, NULL, 30, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1848290166093406210', '2024-10-21 17:08:10', 'admin', '2025-06-13 14:19:47', 'admin', NULL, 0, '/goroku',
        'Goroku', NULL, NULL, '语录系统', 'iconify-ri:emphasis-cn', 0, 0, 0, 0, 1, NULL, 0, NULL, 30, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1848290621762592770', '2024-10-21 17:09:58', 'admin', '2025-06-13 14:19:47', 'admin', '1848290166093406210', 0,
        '/goroku/list', 'GorokuManager', NULL, '/sub/goroku/list/index', '管理语录', 'iconify-ri:list-check-2', 0, 0, 0,
        0, 1, NULL, 0, NULL, 10, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1848291645189545986', '2024-10-21 17:14:02', 'admin', '2024-10-22 13:17:41', 'admin', '1848290166093406210', 1,
        '/goroku/type', 'GorokuType', NULL, '/sub/goroku/type/index', '类型设置', 'i-ri:barricade-fill', 0, 1, 0, 0, 1,
        NULL, 0, NULL, 20, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1851237946889863170', '2024-10-29 20:21:36', 'admin', '2025-06-13 14:21:28', 'admin', '1842552969956536321', 0,
        '/sytem/openApi', 'OpenAPI', NULL, '/system/open-api/index', 'OpenAPI', 'iconify-ep:document', 0, 0, 0, 0, 1,
        NULL, 0, NULL, 3, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1852922367049261058', '2024-11-03 11:54:53', 'admin', '2025-06-13 14:21:28', 'admin', '1851237946889863170', 1,
        NULL, NULL, NULL, NULL, '创建OpenAPI', NULL, 0, 1, 0, 0, 1, NULL, 3, 'sys:open:api:save', 2, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1852922486104580097', '2024-11-03 11:55:21', 'admin', '2025-06-13 14:21:28', 'admin', '1851237946889863170', 1,
        NULL, NULL, NULL, NULL, '更新OpenAPI', NULL, 0, 1, 0, 0, 1, NULL, 3, 'sys:open:api:update', 3, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1852922620058066946', '2024-11-03 11:55:53', 'admin', '2025-06-13 14:21:28', 'admin', '1851237946889863170', 1,
        NULL, NULL, NULL, NULL, '删除OpenAPI', NULL, 0, 1, 0, 0, 1, NULL, 3, 'sys:open:api:del', 4, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1852922756955955202', '2024-11-03 11:56:26', 'admin', '2025-06-13 14:21:28', 'admin', '1851237946889863170', 1,
        NULL, NULL, NULL, NULL, '查询OpenAPI', NULL, 0, 1, 0, 0, 1, NULL, 3, 'sys:open:api:query', 1, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1852923773453918210', '2024-11-03 12:00:28', 'admin', '2025-06-13 17:29:31', 'admin', '1842808807199662081', 1,
        NULL, NULL, NULL, NULL, '分配OpenAPI', NULL, 0, 1, 0, 0, 1, NULL, 3, 'sys:role:open:api:grant', 6, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1869602349767016450', '2024-12-19 12:35:10', 'admin', '2025-06-13 14:19:49', 'admin', NULL, 0, '/nav', 'Nav',
        NULL, NULL, '导航系统', 'iconify-ri:compass-3-line', 0, 0, 0, 0, 1, NULL, 0, NULL, 40, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1869604452178673666', '2024-12-19 12:43:32', 'admin', '2025-06-13 14:19:49', 'admin', '1869602349767016450', 0,
        '/nav/category', 'NavCategory', NULL, '/sub/nav/category/index', '分类列表', 'iconify-ri:list-check-2', 0, 0, 0,
        0, 1, NULL, 0, NULL, 20, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1869604788515717121', '2024-12-19 12:44:52', 'admin', '2025-06-13 14:19:50', 'admin', '1869602349767016450', 0,
        '/nav/tag', 'NavTag', NULL, '/sub/nav/tag/index', '标签列表', 'iconify-ri:price-tag-3-line', 0, 0, 0, 0, 1,
        NULL, 0, NULL, 30, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1869605060675715074', '2024-12-19 12:45:57', 'admin', '2025-06-13 14:19:50', 'admin', '1869602349767016450', 0,
        '/nav/list', 'NavList', NULL, '/sub/nav/list/index', '导航列表', 'iconify-ri:compass-3-line', 0, 0, 0, 0, 1,
        NULL, 0, NULL, 10, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1869605357468860417', '2024-12-19 12:47:07', 'admin', '2025-06-13 14:19:50', 'admin', '1869605060675715074', 1,
        NULL, NULL, NULL, NULL, '列表查询', NULL, 0, 1, 0, 0, 1, NULL, 3, 'nav:list:query', 10, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1869605456592846850', '2024-12-19 12:47:31', 'admin', '2025-06-13 14:19:49', 'admin', '1869604452178673666', 1,
        NULL, NULL, NULL, NULL, '列表查询', NULL, 0, 1, 0, 0, 1, NULL, 3, 'nav:category:query', 10, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1869605530345488386', '2024-12-19 12:47:49', 'admin', '2025-06-13 14:19:49', 'admin', '1869604788515717121', 1,
        NULL, NULL, NULL, NULL, '列表查询', NULL, 0, 1, 0, 0, 1, NULL, 3, 'nav:tag:query', 20, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1869758340018012161', '2024-12-19 22:55:01', 'admin', '2025-06-13 14:19:47', 'admin', '1848290621762592770', 1,
        NULL, NULL, NULL, NULL, '语录查询', NULL, 0, 1, 0, 0, 1, NULL, 3, 'goroku:info:query', 10, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1869758422087958530', '2024-12-19 22:55:21', 'admin', '2025-06-13 14:19:47', 'admin', '1848290621762592770', 1,
        NULL, NULL, NULL, NULL, '新增语录', NULL, 0, 1, 0, 0, 1, NULL, 3, 'goroku:info:save', 20, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1869758480359424001', '2024-12-19 22:55:35', 'admin', '2025-06-13 14:19:47', 'admin', '1848290621762592770', 1,
        NULL, NULL, NULL, NULL, '删除语录', NULL, 0, 1, 0, 0, 1, NULL, 3, 'goroku:info:delete', 40, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1869758558616748034', '2024-12-19 22:55:53', 'admin', '2025-06-13 14:19:47', 'admin', '1848290621762592770', 1,
        NULL, NULL, NULL, NULL, '审核语录', NULL, 0, 1, 0, 0, 1, NULL, 3, 'goroku:info:audit', 50, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1869758926021001217', '2024-12-19 22:57:21', 'admin', '2025-06-13 14:19:47', 'admin', '1848290621762592770', 1,
        NULL, NULL, NULL, NULL, '语录编辑', NULL, 0, 1, 0, 0, 1, NULL, 3, 'goroku:info:update', 30, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1869762918734217217', '2024-12-19 23:13:13', 'admin', '2025-06-13 14:19:49', 'admin', '1869604788515717121', 1,
        NULL, NULL, NULL, NULL, '列表新增', NULL, 0, 1, 0, 0, 1, NULL, 3, 'nav:tag:save', 20, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1869762990028996610', '2024-12-19 23:13:30', 'admin', '2025-06-13 14:19:50', 'admin', '1869604788515717121', 1,
        NULL, NULL, NULL, NULL, '列表更新', NULL, 0, 1, 0, 0, 1, NULL, 3, 'nav:tag:update', 30, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1869763056739401729', '2024-12-19 23:13:46', 'admin', '2025-06-13 14:19:50', 'admin', '1869604788515717121', 1,
        NULL, NULL, NULL, NULL, '列表删除', NULL, 0, 1, 0, 0, 1, NULL, 3, 'nav:tag:delete', 40, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1869916852635316225', '2024-12-20 09:24:54', 'admin', '2025-02-25 21:37:28', 'admin', NULL, 0, '/attachment',
        'Attachment', NULL, NULL, '附件系统', 'iconify-ep:files', 0, 0, 0, 0, 1, NULL, 0, NULL, 50, 1, 0);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1869917245691932673', '2024-12-20 09:26:27', 'admin', '2025-02-25 21:37:24', 'admin', '1869916852635316225', 0,
        '/attachment/list', 'AttachmentManager', NULL, '/common/attachment/list/index', '附件管理',
        'iconify-ri:file-list-2-line', 0, 0, 0, 0, 1, NULL, 0, NULL, 10, 1, 0);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1869917466698199042', '2024-12-20 09:27:20', 'admin', '2024-12-20 09:27:20', 'admin', '1869917245691932673', 1,
        NULL, NULL, NULL, NULL, '附件查询', NULL, 0, 1, 0, 0, 1, NULL, 3, 'sys:attachment:query', 10, 1, 0);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1870103593375760386', '2024-12-20 21:46:56', 'admin', '2025-06-13 14:19:49', 'admin', '1869604452178673666', 1,
        NULL, NULL, NULL, NULL, '列表新增', NULL, 0, 1, 0, 0, 1, NULL, 3, 'nav:category:save', 20, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1870103669963751426', '2024-12-20 21:47:14', 'admin', '2025-06-13 14:19:49', 'admin', '1869604452178673666', 1,
        NULL, NULL, NULL, NULL, '列表更新', NULL, 0, 1, 0, 0, 1, NULL, 3, 'nav:category:update', 30, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1870103756622266370', '2024-12-20 21:47:35', 'admin', '2025-06-13 14:19:49', 'admin', '1869604452178673666', 1,
        NULL, NULL, NULL, NULL, '列表删除', NULL, 0, 1, 0, 0, 1, NULL, 3, 'nav:category:delete', 40, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1870741328339947522', '2024-12-22 16:01:04', 'admin', '2025-06-13 14:19:50', 'admin', '1869602349767016450', 0,
        '/nav/website/setting', 'NavWebsiteSetting', NULL, '/sub/nav/setting/index', '网站设置',
        'iconify-ri:settings-5-line', 0, 0, 0, 0, 1, NULL, 0, NULL, 40, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1870741589850607617', '2024-12-22 16:02:06', 'admin', '2025-06-13 14:19:50', 'admin', '1870741328339947522', 1,
        NULL, NULL, NULL, NULL, '管理', NULL, 0, 1, 0, 0, 1, NULL, 3, 'nav:setting:admin', 1, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1870741692233568258', '2024-12-22 16:02:31', 'admin', '2025-06-13 14:19:50', 'admin', '1869605060675715074', 1,
        NULL, NULL, NULL, NULL, '列表新增', NULL, 0, 1, 0, 0, 1, NULL, 3, 'nav:list:save', 20, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1870741761691242497', '2024-12-22 16:02:47', 'admin', '2025-06-13 14:19:50', 'admin', '1869605060675715074', 1,
        NULL, NULL, NULL, NULL, '列表更新', NULL, 0, 1, 0, 0, 1, NULL, 3, 'nav:list:update', 30, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1870741820654768130', '2024-12-22 16:03:01', 'admin', '2025-06-13 14:19:50', 'admin', '1869605060675715074', 1,
        NULL, NULL, NULL, NULL, '列表删除', NULL, 0, 1, 0, 0, 1, NULL, 3, 'nav:list:delete', 40, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1872511330713923585', '2024-12-27 13:14:25', 'admin', '2025-06-13 14:21:20', 'admin', NULL, 0, '/task', 'Task',
        NULL, NULL, '任务中心', 'iconify-tabler:subtask', 0, 0, 0, 0, 1, NULL, 0, NULL, 100, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1872511749129302017', '2024-12-27 13:16:05', 'admin', '2025-06-13 14:21:20', 'admin', '1872511330713923585', 0,
        '/task/manager', 'TaskManger', NULL, '/common/task/list/index', '任务管理', 'iconify-tabler:subtask', 0, 0, 0,
        0, 1, NULL, 0, NULL, 1, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1872511874216030210', '2024-12-27 13:16:35', 'admin', '2025-06-13 14:21:20', 'admin', '1872511749129302017', 1,
        NULL, NULL, NULL, NULL, '任务管理', NULL, 0, 1, 0, 0, 1, NULL, 3, 'task:list:admin', 1, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1872532315169153026', '2024-12-27 14:37:49', 'admin', '2025-06-13 14:19:47', 'admin', '1848290621762592770', 1,
        NULL, NULL, NULL, NULL, '语录导出', NULL, 0, 1, 0, 0, 1, NULL, 3, 'goroku:info:export', 60, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1872532391673257986', '2024-12-27 14:38:07', 'admin', '2025-06-13 14:19:47', 'admin', '1848290621762592770', 1,
        NULL, NULL, NULL, NULL, '语录导入', NULL, 0, 1, 0, 0, 1, NULL, 3, 'goroku:info:import', 70, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1873255043241189377', '2024-12-29 14:29:40', 'admin', '2025-06-13 14:21:20', 'admin', '1872511749129302017', 1,
        NULL, NULL, NULL, NULL, '下载附件', NULL, 0, 1, 0, 0, 1, NULL, 3, 'task:list:download', 2, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1874308533728096258', '2025-01-01 12:15:52', 'admin', '2025-06-13 14:21:10', 'admin', '1846356327611854850', 0,
        '/message/subscribe', 'SubscribeMsg', NULL, '/system/message/subscribe/index', '消息接受管理',
        'iconify-tabler:message-2-star', 0, 0, 0, 0, 1, NULL, 0, NULL, 3, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1874308698681683970', '2025-01-01 12:16:31', 'admin', '2025-06-13 14:21:10', 'admin', '1874308533728096258', 1,
        NULL, NULL, NULL, NULL, '查询消息订阅', NULL, 0, 1, 0, 0, 1, NULL, 3, 'sys:message:subscribe:page', 1, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1874308796522213377', '2025-01-01 12:16:55', 'admin', '2025-06-13 14:21:10', 'admin', '1874308533728096258', 1,
        NULL, NULL, NULL, NULL, '保存消息订阅', NULL, 0, 1, 0, 0, 1, NULL, 3, 'sys:message:subscribe:save', 2, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1874325647763484673', '2025-01-01 13:23:52', 'admin', '2025-06-13 14:21:10', 'admin', '1874308533728096258', 1,
        NULL, NULL, NULL, NULL, '更新消息订阅', NULL, 0, 1, 0, 0, 1, NULL, 3, 'sys:message:subscribe:update', 3, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1874325718076796930', '2025-01-01 13:24:09', 'admin', '2025-06-13 14:21:10', 'admin', '1874308533728096258', 1,
        NULL, NULL, NULL, NULL, '删除消息订阅', NULL, 0, 1, 0, 0, 1, NULL, 3, 'sys:message:subscribe:delete', 4, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1886048515849306114', '2025-02-02 21:46:22', 'admin', '2025-06-13 14:19:45', 'admin', NULL, 0, '/resume',
        'Resume', NULL, '', '简历中心', 'iconify-ep:document-copy', 0, 1, 0, 0, 1, NULL, 0, NULL, 20, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1886049080582979585', '2025-02-02 21:48:37', 'admin', '2025-06-13 14:19:45', 'admin', '1886048515849306114', 0,
        '/resumer/onlinePdf', 'ResumeOnlinePdf', NULL, '/sub/resume/onlinePdf/index', 'PDF简历管理',
        'iconify-ep:document-copy', 0, 1, 0, 0, 1, NULL, 0, NULL, 10, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1886049514576003073', '2025-02-02 21:50:20', 'admin', '2025-06-13 14:19:45', 'admin', '1886049080582979585', 1,
        NULL, NULL, NULL, NULL, '新增', NULL, 0, 1, 0, 0, 1, NULL, 3, 'resume:manage:add', 20, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1886049597937795073', '2025-02-02 21:50:40', 'admin', '2025-06-13 14:19:45', 'admin', '1886049080582979585', 1,
        NULL, NULL, NULL, NULL, '更新', NULL, 0, 1, 0, 0, 1, NULL, 3, 'resume:manage:update', 30, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1886049787658747905', '2025-02-02 21:51:25', 'admin', '2025-06-13 14:19:45', 'admin', '1886049080582979585', 1,
        NULL, NULL, NULL, NULL, '删除', NULL, 0, 1, 0, 0, 1, NULL, 3, 'resume:manage:delete', 40, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1886049853727424513', '2025-02-02 21:51:41', 'admin', '2025-06-13 14:19:45', 'admin', '1886049080582979585', 1,
        NULL, NULL, NULL, NULL, '查询', NULL, 0, 1, 0, 0, 1, NULL, 3, 'resume:manage:query', 10, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1886226447959965697', '2025-02-03 09:33:24', 'admin', '2025-06-13 14:19:45', 'admin', '1886048515849306114', 0,
        '/resume/template/design', 'ResumeTemplateDesign', NULL, '/sub/resume/templateDesign/index', '模板设计',
        'iconify-ep:briefcase', 0, 1, 0, 0, 1, NULL, 0, NULL, 20, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1886226608958324737', '2025-02-03 09:34:03', 'admin', '2025-06-13 14:19:45', 'admin', '1886226447959965697', 1,
        NULL, NULL, NULL, NULL, '管理模板', NULL, 0, 1, 0, 0, 1, NULL, 3, 'resume:template_design:admin', 10, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1886235047914749954', '2025-02-03 10:07:35', 'admin', '2025-06-13 14:19:45', 'admin', '1886048515849306114', 1,
        '/resume/legoDesigner', 'ResumeLegoDesigner', NULL, '/sub/resume/legoDesigner/index', '积木设计', NULL, 1, 0, 0,
        1, 1, NULL, 0, NULL, 30, 1, 1);
INSERT INTO `sys_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `parent_id`, `is_leaf`,
                              `route_path`, `route_name`, `redirect`, `component`, `title`, `icon`, `is_hidden`,
                              `is_keep_alive`, `is_affix`, `is_full_screen`, `is_require_login`, `frame_src`,
                              `menu_type`, `perms`, `sort`, `status`, `del_flag`)
VALUES ('1887753809931350018', '2025-02-07 14:42:36', 'admin', '2025-06-13 14:19:45', 'admin', '1886048515849306114', 1,
        '/resume/printPdfPreview', 'ResumePrintPdfPreview', NULL,
        '/sub/resume/legoDesigner/render/LegoPrintPdfPreview/index', '打印预览页', NULL, 1, 0, 0, 1, 0, NULL, 0, NULL,
        40, 1, 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_quartz_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_quartz_job`;
CREATE TABLE `sys_quartz_job`
(
    `id`              varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL,
    `created`         datetime                                                      DEFAULT NULL COMMENT '创建时间',
    `created_by`      varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '创建人',
    `modified`        datetime                                                      DEFAULT NULL COMMENT '更新时间',
    `modified_by`     varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '更新人',
    `status`          tinyint(1)                                                    DEFAULT NULL COMMENT '状态',
    `del_flag`        tinyint(1)                                                    DEFAULT '0' COMMENT '是否删除',
    `app_name`        varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '应用名称',
    `job_class_name`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '执行的任务类名',
    `parameter`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '参数',
    `description`     varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '描述',
    `cron_expression` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'cron表达式',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='基础-定时任务';

-- ----------------------------
-- Records of sys_quartz_job
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
    `id`          varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `created`     datetime                                                      DEFAULT NULL COMMENT '创建时间',
    `created_by`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '创建人',
    `modified`    datetime                                                      DEFAULT NULL COMMENT '更新时间',
    `modified_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '更新人',
    `role_name`   varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
    `role_code`   varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色编码',
    `remark`      varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '描述',
    `status`      tinyint(1)                                                    DEFAULT NULL COMMENT '状态 0禁用 1启用',
    `del_flag`    tinyint(1)                                                    DEFAULT NULL COMMENT '是否删除 0否 1是',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='基础-角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_name`, `role_code`, `remark`,
                        `status`, `del_flag`)
VALUES ('1843534878857498626', '2024-10-08 14:12:21', 'admin', '2024-10-08 14:12:21', 'admin', '超管', 'admin', NULL, 1,
        0);
INSERT INTO `sys_role` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_name`, `role_code`, `remark`,
                        `status`, `del_flag`)
VALUES ('1894392845919936513', '2025-02-25 22:23:45', 'admin', '2025-02-25 22:23:45', 'admin', '访客', 'guest', NULL, 1,
        0);
COMMIT;

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`
(
    `id`            varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `created`       datetime                                                     DEFAULT NULL COMMENT '创建时间',
    `created_by`    varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
    `modified`      datetime                                                     DEFAULT NULL COMMENT '更新时间',
    `modified_by`   varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人',
    `role_id`       varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色ID',
    `permission_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单ID',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='基础-角色权限';

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028437811201', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1842431703832432641');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028446199810', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1842552969956536321');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028446199811', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1842812170972770306');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028450394113', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1846011431659081730');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028450394114', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1846011516342079490');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028450394115', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1846011607274590209');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028450394116', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1846011665835462658');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028454588418', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1846012012519854081');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028454588419', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1846012485125640193');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028454588420', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1846012580269232129');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028454588421', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1846012650586738690');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028458782722', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1846012710917607426');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028458782723', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1846012774335483905');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028458782724', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1846086497398726658');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028458782725', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1846086560053239810');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028458782726', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1846086625123672066');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028462977026', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1851237946889863170');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028467171329', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1852922756955955202');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028467171330', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1852922367049261058');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028467171331', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1852922486104580097');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028467171332', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1852922620058066946');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028471365634', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1842806617647144962');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028471365635', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1842808380282429442');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028471365636', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1846010772209635329');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028471365637', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1846010824881704962');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028471365638', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1846010936882204674');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028471365639', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1846071236226576385');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028475559938', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1846071412664168450');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028475559939', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1842808807199662081');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028475559940', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1846011021003165697');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028475559941', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1846011113852473346');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028475559942', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1846011187743526913');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028475559943', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1846011245834637313');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028475559944', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1846011357717696514');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028479754241', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1852923773453918210');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028479754242', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1843577405094551554');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028479754243', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1848209906077364226');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028479754244', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1848228525196800001');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028479754245', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1848228945101156354');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028483948546', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1846087588270120962');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028483948547', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1846117585278017538');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028483948548', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1846365090846666754');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028483948549', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1846365154667196418');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028483948550', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1846365207100190721');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028483948551', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1846365289946083330');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028483948552', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1846356327611854850');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028483948553', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1846356688758206466');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028488142850', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1847213529562165250');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028488142851', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1847213584360747010');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028488142852', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1847213642367971330');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028488142853', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1847213757245763586');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028488142854', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1874308533728096258');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028488142855', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1874308698681683970');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028488142856', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1874308796522213377');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028488142857', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1874325647763484673');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028492337154', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1874325718076796930');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028492337155', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1847214971450634242');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028492337156', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1847215993602519042');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028492337157', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1848171947496140801');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028492337158', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1848171878990573570');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028492337159', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1848172081629982721');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028496531457', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1848172139419103233');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028496531458', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1848172370932101121');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028496531459', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1848171817598545921');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028496531460', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1848172465790480385');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028496531461', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1848290166093406210');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028496531462', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1848290621762592770');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028496531463', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1869758340018012161');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028500725762', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1869758422087958530');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028500725763', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1869758926021001217');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028500725764', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1869758480359424001');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028500725765', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1869758558616748034');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028500725766', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1872532315169153026');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028500725767', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1872532391673257986');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028500725768', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1869602349767016450');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028504920065', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1869605060675715074');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028504920066', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1869605357468860417');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028504920067', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1870741692233568258');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028504920068', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1870741761691242497');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028504920069', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1870741820654768130');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028504920070', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1869604452178673666');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028504920071', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1869605456592846850');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028504920072', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1870103593375760386');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028509114370', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1870103669963751426');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028509114371', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1870103756622266370');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028509114372', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1869604788515717121');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028509114373', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1869605530345488386');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028509114374', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1869762918734217217');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028509114375', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1869762990028996610');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028509114376', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1869763056739401729');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028509114377', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1870741328339947522');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028513308674', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1870741589850607617');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028513308675', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1869916852635316225');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028513308676', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1869917245691932673');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028513308677', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1869917466698199042');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028513308678', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1872511330713923585');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028513308679', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1872511749129302017');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028513308680', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1872511874216030210');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028513308681', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1873255043241189377');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028513308682', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1886048515849306114');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028513308683', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1886049080582979585');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028517502977', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1886049853727424513');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028517502978', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1886049514576003073');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028517502979', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1886049597937795073');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028517502980', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1886049787658747905');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028517502981', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1886226447959965697');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028517502982', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1886226608958324737');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028517502983', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1886235047914749954');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1887754028517502984', '2025-02-07 14:43:28', 'admin', '2025-02-07 14:43:28', 'admin', '1843534878857498626',
        '1887753809931350018');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1894407334060933122', '2025-02-25 23:21:19', 'admin', '2025-02-25 23:21:19', 'admin', '1894392845919936513',
        '1842431703832432641');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1894407334065127425', '2025-02-25 23:21:19', 'admin', '2025-02-25 23:21:19', 'admin', '1894392845919936513',
        '1869758340018012161');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1894407334069321730', '2025-02-25 23:21:19', 'admin', '2025-02-25 23:21:19', 'admin', '1894392845919936513',
        '1869602349767016450');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1894407334069321731', '2025-02-25 23:21:19', 'admin', '2025-02-25 23:21:19', 'admin', '1894392845919936513',
        '1869605060675715074');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1894407334069321732', '2025-02-25 23:21:19', 'admin', '2025-02-25 23:21:19', 'admin', '1894392845919936513',
        '1869605357468860417');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1894407334069321733', '2025-02-25 23:21:19', 'admin', '2025-02-25 23:21:19', 'admin', '1894392845919936513',
        '1870741692233568258');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1894407334073516034', '2025-02-25 23:21:19', 'admin', '2025-02-25 23:21:19', 'admin', '1894392845919936513',
        '1870741761691242497');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1894407334073516035', '2025-02-25 23:21:19', 'admin', '2025-02-25 23:21:19', 'admin', '1894392845919936513',
        '1870741820654768130');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1894407334073516036', '2025-02-25 23:21:19', 'admin', '2025-02-25 23:21:19', 'admin', '1894392845919936513',
        '1869604452178673666');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1894407334073516037', '2025-02-25 23:21:19', 'admin', '2025-02-25 23:21:19', 'admin', '1894392845919936513',
        '1869605456592846850');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1894407334073516038', '2025-02-25 23:21:19', 'admin', '2025-02-25 23:21:19', 'admin', '1894392845919936513',
        '1870103593375760386');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1894407334073516039', '2025-02-25 23:21:19', 'admin', '2025-02-25 23:21:19', 'admin', '1894392845919936513',
        '1870103669963751426');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1894407334077710337', '2025-02-25 23:21:19', 'admin', '2025-02-25 23:21:19', 'admin', '1894392845919936513',
        '1870103756622266370');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1894407334077710338', '2025-02-25 23:21:19', 'admin', '2025-02-25 23:21:19', 'admin', '1894392845919936513',
        '1869604788515717121');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1894407334077710339', '2025-02-25 23:21:19', 'admin', '2025-02-25 23:21:19', 'admin', '1894392845919936513',
        '1869605530345488386');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1894407334077710340', '2025-02-25 23:21:19', 'admin', '2025-02-25 23:21:19', 'admin', '1894392845919936513',
        '1869762918734217217');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1894407334077710341', '2025-02-25 23:21:19', 'admin', '2025-02-25 23:21:19', 'admin', '1894392845919936513',
        '1869762990028996610');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1894407334077710342', '2025-02-25 23:21:19', 'admin', '2025-02-25 23:21:19', 'admin', '1894392845919936513',
        '1869763056739401729');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1894407334077710343', '2025-02-25 23:21:19', 'admin', '2025-02-25 23:21:19', 'admin', '1894392845919936513',
        '1870741328339947522');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1894407334077710344', '2025-02-25 23:21:19', 'admin', '2025-02-25 23:21:19', 'admin', '1894392845919936513',
        '1870741589850607617');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1894407334077710345', '2025-02-25 23:21:19', 'admin', '2025-02-25 23:21:19', 'admin', '1894392845919936513',
        '1872511330713923585');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1894407334077710346', '2025-02-25 23:21:19', 'admin', '2025-02-25 23:21:19', 'admin', '1894392845919936513',
        '1872511749129302017');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1894407334081904642', '2025-02-25 23:21:19', 'admin', '2025-02-25 23:21:19', 'admin', '1894392845919936513',
        '1872511874216030210');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1894407334081904643', '2025-02-25 23:21:19', 'admin', '2025-02-25 23:21:19', 'admin', '1894392845919936513',
        '1873255043241189377');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1894407334081904644', '2025-02-25 23:21:19', 'admin', '2025-02-25 23:21:19', 'admin', '1894392845919936513',
        '1869916852635316225');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1894407334081904645', '2025-02-25 23:21:19', 'admin', '2025-02-25 23:21:19', 'admin', '1894392845919936513',
        '1869917245691932673');
INSERT INTO `sys_role_permission` (`id`, `created`, `created_by`, `modified`, `modified_by`, `role_id`, `permission_id`)
VALUES ('1894407334081904646', '2025-02-25 23:21:19', 'admin', '2025-02-25 23:21:19', 'admin', '1894392845919936513',
        '1869917466698199042');
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
    `id`              varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `created`         datetime                                                      DEFAULT NULL COMMENT '创建时间',
    `created_by`      varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '创建人',
    `modified`        datetime                                                      DEFAULT NULL COMMENT '更新时间',
    `modified_by`     varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '更新人',
    `username`        varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账号',
    `password`        varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
    `salt`            varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '盐值',
    `nickname`        varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '昵称',
    `avatar`          varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '头像',
    `email`           varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '邮箱',
    `hash_email`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '邮箱hash值',
    `phone`           varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '手机号',
    `hash_phone`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '手机号hash值',
    `last_login_time` datetime                                                      DEFAULT NULL COMMENT '最后登录时间',
    `gender`          tinyint                                                       DEFAULT NULL COMMENT '性别 0-未知 1-男 2-女\n',
    `is_system`       tinyint(1)                                                    DEFAULT '0' COMMENT '是否内置',
    `status`          tinyint                                                       DEFAULT '1' COMMENT '状态 0-禁用 1-启用\n',
    `del_flag`        tinyint(1)                                                    DEFAULT '0' COMMENT '删除标记',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='基础-用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` (`id`, `created`, `created_by`, `modified`, `modified_by`, `username`, `password`, `salt`,
                        `nickname`, `avatar`, `email`, `hash_email`, `phone`, `hash_phone`, `last_login_time`, `gender`,
                        `is_system`, `status`, `del_flag`)
VALUES ('1838857427002929153', NULL, NULL, '2025-06-13 14:04:44', NULL, 'admin', '35e239279cda321a', 'F2pNu9', 'admin',
        NULL, NULL, NULL, NULL, NULL, '2025-06-13 14:04:44', 1, 1, 1, 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`
(
    `id`          varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `created`     datetime                                                     DEFAULT NULL COMMENT '创建时间',
    `created_by`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
    `modified`    datetime                                                     DEFAULT NULL COMMENT '更新时间',
    `modified_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人',
    `user_id`     varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
    `role_id`     varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色ID',
    `end_time`    datetime                                                     DEFAULT NULL COMMENT '结束时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='基础-用户角色';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` (`id`, `created`, `created_by`, `modified`, `modified_by`, `user_id`, `role_id`, `end_time`)
VALUES ('1846063294219616257', '2024-10-15 13:39:22', 'admin', '2024-10-15 13:39:22', 'admin', '1838857427002929153',
        '1843534878857498626', NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
