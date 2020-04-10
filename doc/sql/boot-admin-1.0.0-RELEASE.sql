/*
 Navicat Premium Data Transfer

 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Schema         : boot-admin-prod

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 10/04/2020 22:03:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_system_dict
-- ----------------------------
DROP TABLE IF EXISTS `t_system_dict`;
CREATE TABLE `t_system_dict`  (
  `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_enabled` int(2) NULL DEFAULT NULL COMMENT '是否启用',
  `del_flag` int(11) NULL DEFAULT NULL COMMENT '是否删除',
  `version` int(11) NULL DEFAULT NULL COMMENT '版本',
  `description` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `id` bigint(20) NOT NULL COMMENT 'id',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '字典名称',
  `enname` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '字典英文名称',
  `number` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '字典编码',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父id',
  `dict_type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '字典类型',
  `dict_value` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '字典值',
  `dict_label` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '字典标签',
  `is_defeult` int(2) NULL DEFAULT NULL COMMENT '是否默认',
  `params` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '字典参数',
  `sort` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '数据字段类型 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_system_dict
-- ----------------------------
INSERT INTO `t_system_dict` VALUES (-1, '2020-03-30 15:36:06', -1, '2020-03-30 16:03:32', 1, 0, 14, '禁止删除', 1244528815203393538, '字典类型', '', 'dict_type', -1, '1', '', '', NULL, '', '0');
INSERT INTO `t_system_dict` VALUES (-1, '2020-03-30 17:34:43', -1, '2020-03-31 08:25:39', 1, 0, 5, '禁止删除', 1244558665666502657, '', '', '', 1244528815203393538, 'dict_type', '1', '系统类', 1, '', '0');
INSERT INTO `t_system_dict` VALUES (-1, '2020-03-31 08:30:04', -1, '2020-03-31 12:04:09', 1, 0, 2, '禁止删除', 1244783985472507905, '', '', '', 1244528815203393538, 'dict_type', '2', '业务类', 1, '', '1');
INSERT INTO `t_system_dict` VALUES (-1, '2020-03-31 08:30:45', -1, '2020-03-31 08:30:50', 1, 0, 3, '禁止删除', 1244784157116010498, '', '', '', 1244528815203393538, 'dict_type', '3', '其他类', 1, '', '2');
INSERT INTO `t_system_dict` VALUES (-1, '2020-03-31 15:17:04', -1, '2020-04-02 09:20:58', 1, 0, 2, '', 1244886412620115969, '系统常量', '', 'system_constant', -1, '1', '', '', NULL, '', '0');
INSERT INTO `t_system_dict` VALUES (-1, '2020-03-31 15:17:50', NULL, NULL, 1, 0, 1, '启用', 1244886604421443586, '', '', '', 1244886412620115969, 'system_constant', '1', 'USE', 1, '', '0');
INSERT INTO `t_system_dict` VALUES (-1, '2020-03-31 15:18:03', NULL, NULL, 1, 0, 1, '禁用', 1244886660201492481, '', '', '', 1244886412620115969, 'system_constant', '0', 'NOT_USE', 1, '', '0');
INSERT INTO `t_system_dict` VALUES (-1, '2020-03-31 15:18:18', NULL, NULL, 1, 0, 1, '是否全部', 1244886723724226562, '', '', '', 1244886412620115969, 'system_constant', '-1', 'IS_ALL', 1, '', '0');
INSERT INTO `t_system_dict` VALUES (-1, '2020-03-31 15:18:34', NULL, NULL, 1, 0, 1, '修改', 1244886789922926593, '', '', '', 1244886412620115969, 'system_constant', '1', 'IS_UPDATE', 1, '', '0');
INSERT INTO `t_system_dict` VALUES (-1, '2020-03-31 15:18:50', NULL, NULL, 1, 0, 1, '非修改', 1244886857069539329, '', '', '', 1244886412620115969, 'system_constant', '0', 'NO_UPDATE', 1, '', '0');
INSERT INTO `t_system_dict` VALUES (-1, '2020-03-31 15:19:10', NULL, NULL, 1, 0, 1, '默认父id为-1', 1244886937805697026, '', '', '', 1244886412620115969, 'system_constant', '-1L', 'PARENT_ID', 1, '', '0');
INSERT INTO `t_system_dict` VALUES (-1, '2020-03-31 15:21:23', NULL, NULL, 1, 0, 1, '', 1244887499175538690, '系统响应状态码', '', 'system_code_status', -1, '1', '', '', NULL, '', '0');
INSERT INTO `t_system_dict` VALUES (-1, '2020-03-31 15:21:48', NULL, NULL, 1, 0, 1, '请求成功', 1244887604368683010, '', '', '', 1244887499175538690, 'system_code_status', 'BA20000', 'SUCCESS', 1, '', '0');
INSERT INTO `t_system_dict` VALUES (-1, '2020-03-31 15:22:03', NULL, NULL, 1, 0, 1, '请求失败', 1244887665584549890, '', '', '', 1244887499175538690, 'system_code_status', 'BA20002', 'FAIL', 1, '', '0');
INSERT INTO `t_system_dict` VALUES (-1, '2020-03-31 15:22:16', NULL, NULL, 1, 0, 1, '用户不存在', 1244887718126596097, '', '', '', 1244887499175538690, 'system_code_status', 'BA20004', 'USERNAME_NOTFOUND', 1, '', '0');
INSERT INTO `t_system_dict` VALUES (-1, '2020-03-31 15:22:28', NULL, NULL, 1, 0, 1, '未授权', 1244887769871724545, '', '', '', 1244887499175538690, 'system_code_status', 'BA20005', 'UNAUTHORIZED', 1, '', '0');
INSERT INTO `t_system_dict` VALUES (-1, '2020-03-31 15:22:40', NULL, NULL, 1, 0, 1, '授权异常', 1244887821000290305, '', '', '', 1244887499175538690, 'system_code_status', 'BA20006', 'FORBIDDEN', 1, '', '0');
INSERT INTO `t_system_dict` VALUES (-1, '2020-03-31 15:22:52', NULL, NULL, 1, 0, 1, '未找到', 1244887871973666817, '', '', '', 1244887499175538690, 'system_code_status', 'BA20007', 'NOT_FOUND', 1, '', '0');
INSERT INTO `t_system_dict` VALUES (-1, '2020-03-31 15:27:54', NULL, NULL, 1, 0, 1, '默认密码', 1244889138536034306, '', '', '', 1244886412620115969, 'system_constant', '123456', 'DEFAULT_PASSWORD', 1, '', '0');
INSERT INTO `t_system_dict` VALUES (-1, '2020-03-31 15:38:00', NULL, NULL, 1, 0, 1, '', 1244891680452030466, '性别', '', 'gender', -1, '2', '', '', NULL, '', '0');
INSERT INTO `t_system_dict` VALUES (-1, '2020-03-31 15:39:06', NULL, NULL, 1, 0, 1, '', 1244891955787116546, '', '', '', 1244891680452030466, 'gender', '1', '男', 1, '', '0');
INSERT INTO `t_system_dict` VALUES (-1, '2020-03-31 15:39:13', NULL, NULL, 1, 0, 1, '', 1244891984266440705, '', '', '', 1244891680452030466, 'gender', '2', '女', 1, '', '0');
INSERT INTO `t_system_dict` VALUES (-1, '2020-03-31 15:39:24', -1, '2020-03-31 15:40:58', 1, 0, 3, '', 1244892033499181058, '', '', '', 1244891680452030466, 'gender', '-1', '未知', 1, '', '0');
INSERT INTO `t_system_dict` VALUES (-1, '2020-04-01 16:31:21', -1, '2020-04-02 09:57:20', 1, 0, 2, '', 1245267493005524993, '系统状态', '', 'system_status', -1, '1', '', '', NULL, '', '0');
INSERT INTO `t_system_dict` VALUES (-1, '2020-04-02 08:42:46', -1, '2020-04-02 08:43:07', 1, 0, 4, '', 1245511958936825858, '操作类型', '', 'system_oper_type', -1, '1', '', '', NULL, '', '0');
INSERT INTO `t_system_dict` VALUES (-1, '2020-04-02 08:43:36', NULL, NULL, 1, 0, 1, '', 1245512169251811329, '', '', '', 1245511958936825858, 'system_oper_type', '0', '其他', 1, '', '0');
INSERT INTO `t_system_dict` VALUES (-1, '2020-04-02 08:43:51', NULL, NULL, 1, 0, 1, '', 1245512231126183938, '', '', '', 1245511958936825858, 'system_oper_type', '1', '新增', 1, '', '1');
INSERT INTO `t_system_dict` VALUES (-1, '2020-04-02 08:44:00', NULL, NULL, 1, 0, 1, '', 1245512267184615425, '', '', '', 1245511958936825858, 'system_oper_type', '2', '修改', 1, '', '2');
INSERT INTO `t_system_dict` VALUES (-1, '2020-04-02 08:44:09', NULL, NULL, 1, 0, 1, '', 1245512308448178178, '', '', '', 1245511958936825858, 'system_oper_type', '3', '删除', 1, '', '3');
INSERT INTO `t_system_dict` VALUES (-1, '2020-04-02 08:44:41', NULL, NULL, 1, 0, 1, '', 1245512439474040834, '', '', '', 1245511958936825858, 'system_oper_type', '4', '授权', 1, '', '4');
INSERT INTO `t_system_dict` VALUES (-1, '2020-04-02 08:44:51', NULL, NULL, 1, 0, 1, '', 1245512483388403713, '', '', '', 1245511958936825858, 'system_oper_type', '5', '导出', 1, '', '5');
INSERT INTO `t_system_dict` VALUES (-1, '2020-04-02 08:45:02', NULL, NULL, 1, 0, 1, '', 1245512528615583745, '', '', '', 1245511958936825858, 'system_oper_type', '6', '导入', 1, '', '6');
INSERT INTO `t_system_dict` VALUES (-1, '2020-04-02 08:45:17', NULL, NULL, 1, 0, 1, '', 1245512591702110209, '', '', '', 1245511958936825858, 'system_oper_type', '7', '强退', 1, '', '7');
INSERT INTO `t_system_dict` VALUES (-1, '2020-04-02 08:46:08', NULL, NULL, 1, 0, 1, '', 1245512803527045121, '', '', '', 1245511958936825858, 'system_oper_type', '8', '清空', 1, '', '8');
INSERT INTO `t_system_dict` VALUES (-1, '2020-04-02 09:57:53', NULL, NULL, 1, 0, 1, '', 1245530859968073730, '', '', '', 1245267493005524993, 'system_status', '1', '成功', 1, '', '0');
INSERT INTO `t_system_dict` VALUES (-1, '2020-04-02 09:57:59', NULL, NULL, 1, 0, 1, '', 1245530888447397889, '', '', '', 1245267493005524993, 'system_status', '0', '失败', 1, '', '0');
INSERT INTO `t_system_dict` VALUES (-1, '2020-04-02 11:48:12', NULL, NULL, 1, 0, 1, '', 1245558624809373698, '系统启用状态', '', 'system_enabled', -1, '1', '', '', NULL, '', '0');
INSERT INTO `t_system_dict` VALUES (-1, '2020-04-02 11:48:25', NULL, NULL, 1, 0, 1, '', 1245558677200424962, '', '', '', 1245558624809373698, 'system_enabled', '1', '启用', 1, '', '0');
INSERT INTO `t_system_dict` VALUES (-1, '2020-04-02 11:48:50', -1, '2020-04-07 11:16:27', 1, 0, 2, '', 1245558785551880193, '', '', '', 1245558624809373698, 'system_enabled', '0', '禁用', 0, '', '0');
INSERT INTO `t_system_dict` VALUES (-1, '2020-04-02 13:48:19', NULL, NULL, 1, 0, 1, '', 1245588850746523649, '', '', '', 1245588816311287810, 'system_yes_no', '1', '是', 1, '', '0');
INSERT INTO `t_system_dict` VALUES (-1, '2020-04-02 13:48:26', NULL, NULL, 1, 0, 1, '', 1245588882463850497, '', '', '', 1245588816311287810, 'system_yes_no', '0', '否', 1, '', '0');
INSERT INTO `t_system_dict` VALUES (-1, '2020-04-07 11:15:52', NULL, NULL, 1, 0, 1, '', 1247362424624832514, '定时任务状态', '', 'system_job_status', -1, '1', '', '', NULL, '', '0');
INSERT INTO `t_system_dict` VALUES (-1, '2020-04-07 11:16:02', NULL, NULL, 1, 0, 1, '', 1247362469659074562, '', '', '', 1247362424624832514, 'system_job_status', '1', '成功', 1, '', '0');
INSERT INTO `t_system_dict` VALUES (-1, '2020-04-07 11:16:14', NULL, NULL, 1, 0, 1, '', 1247362519407714305, '', '', '', 1247362424624832514, 'system_job_status', '0', '失败', 0, '', '0');

-- ----------------------------
-- Table structure for t_system_job
-- ----------------------------
DROP TABLE IF EXISTS `t_system_job`;
CREATE TABLE `t_system_job`  (
  `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_enabled` int(2) NULL DEFAULT NULL COMMENT '是否启用',
  `del_flag` int(11) NULL DEFAULT NULL COMMENT '是否删除',
  `version` int(11) NULL DEFAULT NULL COMMENT '版本',
  `description` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `id` bigint(20) NOT NULL COMMENT 'id',
  `number` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'number',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'name',
  `bean_name` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '调用目标',
  `method_name` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '调用方法',
  `params` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '参数',
  `cron` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '表达式',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '定时任务 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_system_job_log
-- ----------------------------
DROP TABLE IF EXISTS `t_system_job_log`;
CREATE TABLE `t_system_job_log`  (
  `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_enabled` int(2) NULL DEFAULT NULL COMMENT '是否启用',
  `del_flag` int(11) NULL DEFAULT NULL COMMENT '是否删除',
  `version` int(11) NULL DEFAULT NULL COMMENT '版本',
  `description` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `id` bigint(20) NOT NULL COMMENT 'id',
  `job_id` bigint(20) NULL DEFAULT NULL COMMENT '定时任务id',
  `job_message` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '日志信息',
  `status` int(2) NULL DEFAULT NULL COMMENT '任务状态',
  `exception_info` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '异常信息',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `stop_time` datetime(0) NULL DEFAULT NULL COMMENT '停止时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '定时任务日志 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_system_login_info
-- ----------------------------
DROP TABLE IF EXISTS `t_system_login_info`;
CREATE TABLE `t_system_login_info`  (
  `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_enabled` int(2) NULL DEFAULT NULL COMMENT '是否启用',
  `del_flag` int(11) NULL DEFAULT NULL COMMENT '是否删除',
  `version` int(11) NULL DEFAULT NULL COMMENT '版本',
  `description` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户账号',
  `status` int(2) NULL DEFAULT NULL COMMENT '登录状态',
  `ipaddr` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '登录地址',
  `login_location` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '登录地点',
  `browser` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '浏览器',
  `os` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '操作系统',
  `message` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '提示信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统登录日志 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_system_login_info
-- ----------------------------
INSERT INTO `t_system_login_info` VALUES (NULL, '2020-04-10 17:43:19', NULL, NULL, 1, 0, 1, NULL, 1, 'Administrator', 1, '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', '登录成功');
INSERT INTO `t_system_login_info` VALUES (NULL, '2020-04-10 17:48:14', NULL, NULL, 1, 0, 1, NULL, 2, 'Administrator', 1, '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', '注销成功');
INSERT INTO `t_system_login_info` VALUES (NULL, '2020-04-10 17:48:18', NULL, NULL, 1, 0, 1, NULL, 3, 'Administrator', 1, '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', '登录成功');
INSERT INTO `t_system_login_info` VALUES (NULL, '2020-04-10 17:48:32', NULL, NULL, 1, 0, 1, NULL, 4, 'Administrator', 1, '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', '注销成功');
INSERT INTO `t_system_login_info` VALUES (NULL, '2020-04-10 17:48:37', NULL, NULL, 1, 0, 1, NULL, 5, 'Administrator', 1, '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', '登录成功');
INSERT INTO `t_system_login_info` VALUES (NULL, '2020-04-10 17:49:12', NULL, NULL, 1, 0, 1, NULL, 6, 'Administrator', 1, '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', '注销成功');
INSERT INTO `t_system_login_info` VALUES (NULL, '2020-04-10 17:49:16', NULL, NULL, 1, 0, 1, NULL, 7, 'Administrator', 1, '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', '登录成功');
INSERT INTO `t_system_login_info` VALUES (NULL, '2020-04-10 17:53:15', NULL, NULL, 1, 0, 1, NULL, 8, 'Administrator', 1, '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', '注销成功');
INSERT INTO `t_system_login_info` VALUES (NULL, '2020-04-10 17:53:20', NULL, NULL, 1, 0, 1, NULL, 9, 'Administrator', 1, '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', '登录成功');
INSERT INTO `t_system_login_info` VALUES (NULL, '2020-04-10 17:53:28', NULL, NULL, 1, 0, 1, NULL, 10, 'Administrator', 1, '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', '注销成功');
INSERT INTO `t_system_login_info` VALUES (NULL, '2020-04-10 17:53:33', NULL, NULL, 1, 0, 1, NULL, 11, 'Administrator', 1, '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', '登录成功');
INSERT INTO `t_system_login_info` VALUES (NULL, '2020-04-10 17:54:56', NULL, NULL, 1, 0, 1, NULL, 12, 'Administrator', 1, '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', '注销成功');
INSERT INTO `t_system_login_info` VALUES (NULL, '2020-04-10 17:54:59', NULL, NULL, 1, 0, 1, NULL, 13, 'Administrator', 1, '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', '登录成功');
INSERT INTO `t_system_login_info` VALUES (NULL, '2020-04-10 17:56:06', NULL, NULL, 1, 0, 1, NULL, 14, 'Administrator', 1, '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', '注销成功');
INSERT INTO `t_system_login_info` VALUES (NULL, '2020-04-10 17:56:26', NULL, NULL, 1, 0, 1, NULL, 15, 'Administrator', 1, '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', '登录成功');
INSERT INTO `t_system_login_info` VALUES (NULL, '2020-04-10 17:57:31', NULL, NULL, 1, 0, 1, NULL, 16, 'Administrator', 1, '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', '注销成功');
INSERT INTO `t_system_login_info` VALUES (NULL, '2020-04-10 17:57:50', NULL, NULL, 1, 0, 1, NULL, 17, 'Administrator', 1, '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', '登录成功');
INSERT INTO `t_system_login_info` VALUES (NULL, '2020-04-10 17:58:15', NULL, NULL, 1, 0, 1, NULL, 18, 'Administrator', 1, '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', '注销成功');
INSERT INTO `t_system_login_info` VALUES (NULL, '2020-04-10 17:58:24', NULL, NULL, 1, 0, 1, NULL, 19, 'Administrator', 1, '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', '登录成功');
INSERT INTO `t_system_login_info` VALUES (NULL, '2020-04-10 17:58:32', NULL, NULL, 1, 0, 1, NULL, 20, 'Administrator', 1, '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', '注销成功');
INSERT INTO `t_system_login_info` VALUES (NULL, '2020-04-10 17:58:49', NULL, NULL, 1, 0, 1, NULL, 21, 'Administrator', 1, '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', '登录成功');
INSERT INTO `t_system_login_info` VALUES (NULL, '2020-04-10 17:59:14', NULL, NULL, 1, 0, 1, NULL, 22, 'Administrator', 1, '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', '注销成功');
INSERT INTO `t_system_login_info` VALUES (NULL, '2020-04-10 18:00:09', NULL, NULL, 1, 0, 1, NULL, 23, 'Administrator', 1, '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', '登录成功');
INSERT INTO `t_system_login_info` VALUES (NULL, '2020-04-10 18:01:07', NULL, NULL, 1, 0, 1, NULL, 24, 'Administrator', 1, '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', '注销成功');
INSERT INTO `t_system_login_info` VALUES (NULL, '2020-04-10 18:01:19', NULL, NULL, 1, 0, 1, NULL, 25, 'Administrator', 1, '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', '登录成功');
INSERT INTO `t_system_login_info` VALUES (NULL, '2020-04-10 18:09:45', NULL, NULL, 1, 0, 1, NULL, 26, 'Administrator', 1, '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', '登录成功');
INSERT INTO `t_system_login_info` VALUES (NULL, '2020-04-10 18:10:03', NULL, NULL, 1, 0, 1, NULL, 27, 'Administrator', 1, '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', '注销成功');
INSERT INTO `t_system_login_info` VALUES (NULL, '2020-04-10 18:10:23', NULL, NULL, 1, 0, 1, NULL, 28, 'Administrator', 1, '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', '登录成功');
INSERT INTO `t_system_login_info` VALUES (NULL, '2020-04-10 18:12:01', NULL, NULL, 1, 0, 1, NULL, 29, 'Administrator', 1, '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', '登录成功');
INSERT INTO `t_system_login_info` VALUES (NULL, '2020-04-10 18:12:48', NULL, NULL, 1, 0, 1, NULL, 30, 'Administrator', 1, '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', '注销成功');
INSERT INTO `t_system_login_info` VALUES (NULL, '2020-04-10 18:12:59', NULL, NULL, 1, 0, 1, NULL, 31, 'Administrator', 1, '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', '登录成功');
INSERT INTO `t_system_login_info` VALUES (NULL, '2020-04-10 18:16:36', NULL, NULL, 1, 0, 1, NULL, 32, 'Administrator', 1, '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', '登录成功');
INSERT INTO `t_system_login_info` VALUES (NULL, '2020-04-10 18:17:13', NULL, NULL, 1, 0, 1, NULL, 33, 'Administrator', 1, '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', '注销成功');
INSERT INTO `t_system_login_info` VALUES (NULL, '2020-04-10 18:17:49', NULL, NULL, 1, 0, 1, NULL, 34, 'Administrator', 1, '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', '登录成功');
INSERT INTO `t_system_login_info` VALUES (NULL, '2020-04-10 18:17:58', NULL, NULL, 1, 0, 1, NULL, 35, 'Administrator', 1, '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', '注销成功');
INSERT INTO `t_system_login_info` VALUES (NULL, '2020-04-10 18:21:15', NULL, NULL, 1, 0, 1, NULL, 36, 'Administrator', 1, '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', '登录成功');
INSERT INTO `t_system_login_info` VALUES (NULL, '2020-04-10 20:55:36', NULL, NULL, 1, 0, 1, NULL, 37, 'Administrator', 1, '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', '登录成功');

-- ----------------------------
-- Table structure for t_system_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_system_menu`;
CREATE TABLE `t_system_menu`  (
  `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_enabled` int(2) NULL DEFAULT NULL COMMENT '是否启用',
  `del_flag` int(11) NULL DEFAULT NULL COMMENT '是否删除',
  `version` int(11) NULL DEFAULT NULL COMMENT '版本',
  `description` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `id` bigint(20) NOT NULL COMMENT 'id',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
  `enname` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '英文名称',
  `url` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '路径',
  `icon` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图标',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父id',
  `has_child` int(2) NULL DEFAULT NULL COMMENT '是否有下级',
  `is_root` int(2) NULL DEFAULT NULL COMMENT '是否为根节点',
  `level` int(11) NULL DEFAULT NULL COMMENT '菜单级别',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统菜单 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_system_menu
-- ----------------------------
INSERT INTO `t_system_menu` VALUES (-1, '2020-03-26 11:52:57', -1, '2020-03-26 12:07:08', 1, 0, 3, NULL, 1243023104032104449, '系统设置', 'systemManager', '/bootAdmin/systemManager', 'fa fa-gear', 0, -1, 1, 0, 1);
INSERT INTO `t_system_menu` VALUES (-1, '2020-03-26 12:07:08', -1, '2020-03-30 11:02:58', 1, 0, 3, '', 1243026675188297730, '菜单管理', 'menuManager', '/bootAdmin/systemManager/menu', '', 0, 1243023104032104449, 0, 0, 2);
INSERT INTO `t_system_menu` VALUES (-1, '2020-03-26 12:54:58', -1, '2020-03-26 12:56:10', 1, 0, 3, '', 1243038711322435586, '用户中心', 'userCentor', '/bootAdmin/userManager', 'fa fa-users', 1, -1, 1, 1, 1);
INSERT INTO `t_system_menu` VALUES (-1, '2020-03-26 12:56:10', -1, '2020-03-30 11:02:36', 1, 0, 2, '', 1243039012540571649, '用户管理', 'userManager', '/bootAdmin/userManager/userList', '', 0, 1243038711322435586, 0, 0, 2);
INSERT INTO `t_system_menu` VALUES (-1, '2020-03-28 16:36:41', -1, '2020-03-29 15:41:59', 1, 0, 2, '', 1243819283653275650, '组织管理', 'orgManager', '/bootAdmin/systemManager/org', '', 0, 1243023104032104449, 0, 0, 2);
INSERT INTO `t_system_menu` VALUES (-1, '2020-03-29 19:46:24', -1, '2020-03-29 19:46:57', 1, 0, 2, '', 1244229413641314306, '角色管理', 'roleManager', '/bootAdmin/systemManager/role', '', 0, 1243023104032104449, 0, 0, 2);
INSERT INTO `t_system_menu` VALUES (-1, '2020-03-29 19:47:46', NULL, NULL, 1, 0, 1, '', 1244229759709143042, '岗位管理', 'postManager', '/bootAdmin/userManager/post', '', 0, 1243038711322435586, 0, 0, 2);
INSERT INTO `t_system_menu` VALUES (-1, '2020-03-30 11:40:33', -1, '2020-03-30 11:40:43', 1, 0, 2, '', 1244469536593256449, '字典管理', 'dictManager', '/bootAdmin/systemManager/dict', '', 0, 1243023104032104449, 0, 0, 2);
INSERT INTO `t_system_menu` VALUES (-1, '2020-04-01 15:35:15', -1, '2020-04-01 15:36:35', 1, 0, 2, '', 1245253376169390082, '系统监控', 'systemMonitor', '/bootAdmin/systemMonitor', 'fa fa-video-camera', 0, -1, 1, 1, 1);
INSERT INTO `t_system_menu` VALUES (-1, '2020-04-01 15:36:35', -1, '2020-04-01 15:37:13', 1, 0, 2, '', 1245253710568665089, '日志管理', 'logManager', '/bootAdmin/systemMonitor/logManager', '', 0, 1245253376169390082, 1, 0, 2);
INSERT INTO `t_system_menu` VALUES (-1, '2020-04-01 15:37:13', NULL, NULL, 1, 0, 1, '', 1245253870879158274, '登录日志', 'logininfo', '/bootAdmin/systemMonitor/logManager/logininfo', '', 0, 1245253710568665089, 0, 0, 3);
INSERT INTO `t_system_menu` VALUES (-1, '2020-04-01 17:45:30', NULL, NULL, 1, 0, 1, '', 1245286153090727938, '操作日志', '', '/bootAdmin/systemMonitor/operlog', '', 0, 1245253710568665089, 0, 0, 3);
INSERT INTO `t_system_menu` VALUES (-1, '2020-04-02 16:20:26', NULL, NULL, 1, 0, 1, '', 1245627134071779330, '在线用户', '', '/bootAdmin/systemMonitor/userOnline', '', 0, 1245253376169390082, 0, 0, 2);
INSERT INTO `t_system_menu` VALUES (-1, '2020-04-06 15:15:45', -1, '2020-04-07 10:10:36', 1, 0, 2, '', 1247060409164169218, '定时任务', '', '/bootAdmin/systemMonitor/job', '', 0, 1245253376169390082, 0, 0, 2);

-- ----------------------------
-- Table structure for t_system_menu_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_system_menu_permission`;
CREATE TABLE `t_system_menu_permission`  (
  `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_enabled` int(2) NULL DEFAULT NULL COMMENT '是否启用',
  `del_flag` int(11) NULL DEFAULT NULL COMMENT '是否删除',
  `version` int(11) NULL DEFAULT NULL COMMENT '版本',
  `description` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `id` bigint(20) NOT NULL COMMENT 'id',
  `menu_id` bigint(20) NULL DEFAULT NULL COMMENT '菜单id',
  `permission_id` bigint(20) NULL DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '菜单权限 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_system_menu_permission
-- ----------------------------
INSERT INTO `t_system_menu_permission` VALUES (-1, '2020-04-01 10:36:01', NULL, NULL, 1, 0, 1, NULL, 1245178069605392386, 1243026675188297730, 1245178069475368962);
INSERT INTO `t_system_menu_permission` VALUES (-1, '2020-04-10 20:58:16', NULL, NULL, 1, 0, 1, NULL, 1248596155352145921, 1243026675188297730, 1248596155138236417);
INSERT INTO `t_system_menu_permission` VALUES (-1, '2020-04-10 20:58:37', NULL, NULL, 1, 0, 1, NULL, 1248596245307383810, 1243026675188297730, 1248596245210914817);
INSERT INTO `t_system_menu_permission` VALUES (-1, '2020-04-10 21:00:38', NULL, NULL, 1, 0, 1, NULL, 1248596752704921602, 1243026675188297730, 1248596752621035521);
INSERT INTO `t_system_menu_permission` VALUES (-1, '2020-04-10 21:01:31', NULL, NULL, 1, 0, 1, NULL, 1248596973040099330, 1243026675188297730, 1248596972893298689);
INSERT INTO `t_system_menu_permission` VALUES (-1, '2020-04-10 21:02:07', NULL, NULL, 1, 0, 1, NULL, 1248597123963740162, 1243026675188297730, 1248597123867271169);
INSERT INTO `t_system_menu_permission` VALUES (-1, '2020-04-10 21:02:48', NULL, NULL, 1, 0, 1, NULL, 1248597297230438402, 1243026675188297730, 1248597297092026370);
INSERT INTO `t_system_menu_permission` VALUES (-1, '2020-04-10 21:03:23', NULL, NULL, 1, 0, 1, NULL, 1248597441455775746, 1243819283653275650, 1248597441329946625);
INSERT INTO `t_system_menu_permission` VALUES (-1, '2020-04-10 21:04:14', NULL, NULL, 1, 0, 1, NULL, 1248597655843430401, 1243819283653275650, 1248597655650492418);
INSERT INTO `t_system_menu_permission` VALUES (-1, '2020-04-10 21:04:36', NULL, NULL, 1, 0, 1, NULL, 1248597748441079809, 1243819283653275650, 1248597748256530433);
INSERT INTO `t_system_menu_permission` VALUES (-1, '2020-04-10 21:04:51', NULL, NULL, 1, 0, 1, NULL, 1248597810936209409, 1243819283653275650, 1248597810781020161);
INSERT INTO `t_system_menu_permission` VALUES (-1, '2020-04-10 21:05:32', NULL, NULL, 1, 0, 1, NULL, 1248597985108877313, 1244229413641314306, 1248597984873996289);
INSERT INTO `t_system_menu_permission` VALUES (-1, '2020-04-10 21:05:47', NULL, NULL, 1, 0, 1, NULL, 1248598049491443714, 1244229413641314306, 1248598049311088641);
INSERT INTO `t_system_menu_permission` VALUES (-1, '2020-04-10 21:06:09', NULL, NULL, 1, 0, 1, NULL, 1248598141187317762, 1244229413641314306, 1248598141019545601);
INSERT INTO `t_system_menu_permission` VALUES (-1, '2020-04-10 21:06:26', NULL, NULL, 1, 0, 1, NULL, 1248598212284964865, 1244229413641314306, 1248598212192690178);
INSERT INTO `t_system_menu_permission` VALUES (-1, '2020-04-10 21:06:56', NULL, NULL, 1, 0, 1, NULL, 1248598336771907586, 1244229413641314306, 1248598336591552513);
INSERT INTO `t_system_menu_permission` VALUES (-1, '2020-04-10 21:07:44', NULL, NULL, 1, 0, 1, NULL, 1248598538308214786, 1244229413641314306, 1248598538190774274);
INSERT INTO `t_system_menu_permission` VALUES (-1, '2020-04-10 21:08:10', NULL, NULL, 1, 0, 1, NULL, 1248598649058811905, 1244229413641314306, 1248598648865873921);
INSERT INTO `t_system_menu_permission` VALUES (-1, '2020-04-10 21:08:36', NULL, NULL, 1, 0, 1, NULL, 1248598754436505601, 1244229413641314306, 1248598754298093569);
INSERT INTO `t_system_menu_permission` VALUES (-1, '2020-04-10 21:09:12', NULL, NULL, 1, 0, 1, NULL, 1248598905288843265, 1244469536593256449, 1248598905154625538);
INSERT INTO `t_system_menu_permission` VALUES (-1, '2020-04-10 21:09:40', NULL, NULL, 1, 0, 1, NULL, 1248599023807291393, 1244469536593256449, 1248599023673073665);
INSERT INTO `t_system_menu_permission` VALUES (-1, '2020-04-10 21:10:06', NULL, NULL, 1, 0, 1, NULL, 1248599133756776450, 1244469536593256449, 1248599133643530242);
INSERT INTO `t_system_menu_permission` VALUES (-1, '2020-04-10 21:10:23', NULL, NULL, 1, 0, 1, NULL, 1248599203445137410, 1244469536593256449, 1248599203227033602);
INSERT INTO `t_system_menu_permission` VALUES (-1, '2020-04-10 21:11:09', NULL, NULL, 1, 0, 1, NULL, 1248599396550893570, 1243039012540571649, 1248599396081131522);
INSERT INTO `t_system_menu_permission` VALUES (-1, '2020-04-10 21:11:30', NULL, NULL, 1, 0, 1, NULL, 1248599486954921986, 1243039012540571649, 1248599486774566914);
INSERT INTO `t_system_menu_permission` VALUES (-1, '2020-04-10 21:11:51', NULL, NULL, 1, 0, 1, NULL, 1248599572938153986, 1243039012540571649, 1248599572795547650);
INSERT INTO `t_system_menu_permission` VALUES (-1, '2020-04-10 21:12:33', NULL, NULL, 1, 0, 1, NULL, 1248599751238017026, 1243039012540571649, 1248599751112187906);
INSERT INTO `t_system_menu_permission` VALUES (-1, '2020-04-10 21:12:51', NULL, NULL, 1, 0, 1, NULL, 1248599824223100930, 1243039012540571649, 1248599824055328769);
INSERT INTO `t_system_menu_permission` VALUES (-1, '2020-04-10 21:13:12', NULL, NULL, 1, 0, 1, NULL, 1248599912299290625, 1243039012540571649, 1248599912173461506);
INSERT INTO `t_system_menu_permission` VALUES (-1, '2020-04-10 21:13:28', NULL, NULL, 1, 0, 1, NULL, 1248599981824073729, 1243039012540571649, 1248599981664690177);
INSERT INTO `t_system_menu_permission` VALUES (-1, '2020-04-10 21:14:00', NULL, NULL, 1, 0, 1, NULL, 1248600113630076929, 1244229759709143042, 1248600113541996545);
INSERT INTO `t_system_menu_permission` VALUES (-1, '2020-04-10 21:14:21', NULL, NULL, 1, 0, 1, NULL, 1248600203497234434, 1244229759709143042, 1248600202238943233);
INSERT INTO `t_system_menu_permission` VALUES (-1, '2020-04-10 21:14:40', NULL, NULL, 1, 0, 1, NULL, 1248600283923013634, 1244229759709143042, 1248600283772018689);
INSERT INTO `t_system_menu_permission` VALUES (-1, '2020-04-10 21:14:57', NULL, NULL, 1, 0, 1, NULL, 1248600355955990529, 1244229759709143042, 1248600355872104450);
INSERT INTO `t_system_menu_permission` VALUES (-1, '2020-04-10 21:15:15', NULL, NULL, 1, 0, 1, NULL, 1248600428165128193, 1244229759709143042, 1248600427909275650);
INSERT INTO `t_system_menu_permission` VALUES (-1, '2020-04-10 21:15:31', NULL, NULL, 1, 0, 1, NULL, 1248600495081054210, 1244229759709143042, 1248600494862950401);
INSERT INTO `t_system_menu_permission` VALUES (-1, '2020-04-10 21:16:15', NULL, NULL, 1, 0, 1, NULL, 1248600682973290497, 1245253870879158274, 1248600682826489857);
INSERT INTO `t_system_menu_permission` VALUES (-1, '2020-04-10 21:16:45', NULL, NULL, 1, 0, 1, NULL, 1248600805828648961, 1245253870879158274, 1248600804914290689);
INSERT INTO `t_system_menu_permission` VALUES (-1, '2020-04-10 21:17:02', NULL, NULL, 1, 0, 1, NULL, 1248600878402691074, 1245253870879158274, 1248600878276861954);
INSERT INTO `t_system_menu_permission` VALUES (-1, '2020-04-10 21:17:20', NULL, NULL, 1, 0, 1, NULL, 1248600952117583874, 1245253870879158274, 1248600951983366146);
INSERT INTO `t_system_menu_permission` VALUES (-1, '2020-04-10 21:17:57', NULL, NULL, 1, 0, 1, NULL, 1248601107797565441, 1245286153090727938, 1248601107629793282);
INSERT INTO `t_system_menu_permission` VALUES (-1, '2020-04-10 21:18:25', NULL, NULL, 1, 0, 1, NULL, 1248601225166774274, 1245286153090727938, 1248601225024167938);
INSERT INTO `t_system_menu_permission` VALUES (-1, '2020-04-10 21:19:12', NULL, NULL, 1, 0, 1, NULL, 1248601425755168769, 1245286153090727938, 1248601425595785218);
INSERT INTO `t_system_menu_permission` VALUES (-1, '2020-04-10 21:19:40', NULL, NULL, 1, 0, 1, NULL, 1248601539752157185, 1245286153090727938, 1248601539081068545);
INSERT INTO `t_system_menu_permission` VALUES (-1, '2020-04-10 21:20:16', NULL, NULL, 1, 0, 1, NULL, 1248601690373808129, 1245627134071779330, 1248601690243784705);
INSERT INTO `t_system_menu_permission` VALUES (-1, '2020-04-10 21:20:54', NULL, NULL, 1, 0, 1, NULL, 1248601853515456513, 1245627134071779330, 1248601853272186881);
INSERT INTO `t_system_menu_permission` VALUES (-1, '2020-04-10 21:21:40', NULL, NULL, 1, 0, 1, NULL, 1248602046394720258, 1247060409164169218, 1248602046210170882);
INSERT INTO `t_system_menu_permission` VALUES (-1, '2020-04-10 21:22:03', NULL, NULL, 1, 0, 1, NULL, 1248602140720422913, 1247060409164169218, 1248602140619759618);
INSERT INTO `t_system_menu_permission` VALUES (-1, '2020-04-10 21:22:27', NULL, NULL, 1, 0, 1, NULL, 1248602243480870913, 1247060409164169218, 1248602243212435458);
INSERT INTO `t_system_menu_permission` VALUES (-1, '2020-04-10 21:22:43', NULL, NULL, 1, 0, 1, NULL, 1248602310556180482, 1247060409164169218, 1248602310182887426);
INSERT INTO `t_system_menu_permission` VALUES (-1, '2020-04-10 21:23:04', NULL, NULL, 1, 0, 1, NULL, 1248602395822186497, 1247060409164169218, 1248602395637637122);
INSERT INTO `t_system_menu_permission` VALUES (-1, '2020-04-10 21:23:22', NULL, NULL, 1, 0, 1, NULL, 1248602471781031937, 1247060409164169218, 1248602471051223042);
INSERT INTO `t_system_menu_permission` VALUES (-1, '2020-04-10 21:24:16', NULL, NULL, 1, 0, 1, NULL, 1248602697531056130, 1247060409164169218, 1248602697375866882);
INSERT INTO `t_system_menu_permission` VALUES (-1, '2020-04-10 21:24:52', NULL, NULL, 1, 0, 1, NULL, 1248602850044338177, 1247060409164169218, 1248602849843011586);
INSERT INTO `t_system_menu_permission` VALUES (-1, '2020-04-10 21:25:15', NULL, NULL, 1, 0, 1, NULL, 1248602945754161154, 1247060409164169218, 1248602945598971905);
INSERT INTO `t_system_menu_permission` VALUES (-1, '2020-04-10 21:26:02', NULL, NULL, 1, 0, 1, NULL, 1248603142769008642, 1247060409164169218, 1248603142211166209);
INSERT INTO `t_system_menu_permission` VALUES (-1, '2020-04-10 21:32:48', NULL, NULL, 1, 0, 1, NULL, 1248604844754657282, 1243026675188297730, 1248604844628828161);
INSERT INTO `t_system_menu_permission` VALUES (-1, '2020-04-10 21:46:51', NULL, NULL, 1, 0, 1, NULL, 1248608382931099650, 1244469536593256449, 1248608382771716098);

-- ----------------------------
-- Table structure for t_system_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `t_system_oper_log`;
CREATE TABLE `t_system_oper_log`  (
  `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_enabled` int(2) NULL DEFAULT NULL COMMENT '是否启用',
  `del_flag` int(11) NULL DEFAULT NULL COMMENT '是否删除',
  `version` int(11) NULL DEFAULT NULL COMMENT '版本',
  `description` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '操作账号',
  `module` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '操作模块',
  `title` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标题',
  `status` int(2) NULL DEFAULT NULL COMMENT '状态',
  `business_type` int(2) NULL DEFAULT NULL COMMENT '操作类型',
  `method` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '请求方法',
  `request_method` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '请求方式',
  `oper_url` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '请求地址',
  `oper_ip` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '操作地址',
  `oper_location` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '操作地点',
  `oper_param` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '请求参数',
  `json_result` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '返回参数',
  `error_msg` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '错误消息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '业务操作日志 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_system_oper_log
-- ----------------------------
INSERT INTO `t_system_oper_log` VALUES (-1, '2020-04-10 20:58:16', NULL, NULL, 1, 0, 1, NULL, 1, 'Administrator', '系统菜单', '菜单权限新增', 1, 2, 'com.hb0730.boot.admin.project.system.menu.controller.SystemMenuController.savePermissionByMenuId()', 'POST', '/api/v1/menu/permission/save/1243026675188297730', '127.0.0.1', NULL, '[{\"description\":\"\",\"name\":\"菜单新增\",\"enname\":\"menuSave\",\"mark\":\"menu:save\",\"sort\":0,\"startRow\":1,\"pageSize\":10,\"isEnabled\":1}]', '{\"code\":\"BA20000\",\"message\":\"请求成功\",\"data\":\"新增成功\"}', NULL);
INSERT INTO `t_system_oper_log` VALUES (-1, '2020-04-10 20:58:37', NULL, NULL, 1, 0, 1, NULL, 2, 'Administrator', '系统菜单', '菜单权限新增', 1, 2, 'com.hb0730.boot.admin.project.system.menu.controller.SystemMenuController.savePermissionByMenuId()', 'POST', '/api/v1/menu/permission/save/1243026675188297730', '127.0.0.1', NULL, '[{\"description\":\"\",\"name\":\"菜单修改\",\"enname\":\"menu:update\",\"mark\":\"菜单修改\",\"sort\":0,\"startRow\":1,\"pageSize\":10,\"isEnabled\":1}]', '{\"code\":\"BA20000\",\"message\":\"请求成功\",\"data\":\"新增成功\"}', NULL);
INSERT INTO `t_system_oper_log` VALUES (-1, '2020-04-10 21:00:38', NULL, NULL, 1, 0, 1, NULL, 3, 'Administrator', '系统菜单', '菜单权限新增', 1, 2, 'com.hb0730.boot.admin.project.system.menu.controller.SystemMenuController.savePermissionByMenuId()', 'POST', '/api/v1/menu/permission/save/1243026675188297730', '127.0.0.1', NULL, '[{\"description\":\"\",\"name\":\"菜单删除\",\"enname\":\"menuDelete\",\"mark\":\"menu:delete\",\"sort\":0,\"startRow\":1,\"pageSize\":10,\"isEnabled\":1}]', '{\"code\":\"BA20000\",\"message\":\"请求成功\",\"data\":\"新增成功\"}', NULL);
INSERT INTO `t_system_oper_log` VALUES (-1, '2020-04-10 21:01:31', NULL, NULL, 1, 0, 1, NULL, 4, 'Administrator', '系统菜单', '菜单权限新增', 1, 2, 'com.hb0730.boot.admin.project.system.menu.controller.SystemMenuController.savePermissionByMenuId()', 'POST', '/api/v1/menu/permission/save/1243026675188297730', '127.0.0.1', NULL, '[{\"description\":\"\",\"name\":\"菜单权限新增\",\"enname\":\"menuPermissionSave\",\"mark\":\"menu:permission:save\",\"sort\":0,\"startRow\":1,\"pageSize\":10,\"isEnabled\":1}]', '{\"code\":\"BA20000\",\"message\":\"请求成功\",\"data\":\"新增成功\"}', NULL);
INSERT INTO `t_system_oper_log` VALUES (-1, '2020-04-10 21:02:07', NULL, NULL, 1, 0, 1, NULL, 5, 'Administrator', '系统菜单', '菜单权限新增', 1, 2, 'com.hb0730.boot.admin.project.system.menu.controller.SystemMenuController.savePermissionByMenuId()', 'POST', '/api/v1/menu/permission/save/1243026675188297730', '127.0.0.1', NULL, '[{\"description\":\"\",\"name\":\"菜单权限修改\",\"enname\":\"menuPermissionUpdate\",\"mark\":\"menu:permission:update\",\"sort\":0,\"startRow\":1,\"pageSize\":10,\"isEnabled\":1}]', '{\"code\":\"BA20000\",\"message\":\"请求成功\",\"data\":\"新增成功\"}', NULL);
INSERT INTO `t_system_oper_log` VALUES (-1, '2020-04-10 21:02:48', NULL, NULL, 1, 0, 1, NULL, 6, 'Administrator', '系统菜单', '菜单权限新增', 1, 2, 'com.hb0730.boot.admin.project.system.menu.controller.SystemMenuController.savePermissionByMenuId()', 'POST', '/api/v1/menu/permission/save/1243026675188297730', '127.0.0.1', NULL, '[{\"description\":\"\",\"name\":\"菜单权限删除\",\"enname\":\"menuPermissionDelete\",\"mark\":\"menu:permission:delete\",\"sort\":0,\"startRow\":1,\"pageSize\":10,\"isEnabled\":1}]', '{\"code\":\"BA20000\",\"message\":\"请求成功\",\"data\":\"新增成功\"}', NULL);
INSERT INTO `t_system_oper_log` VALUES (-1, '2020-04-10 21:03:23', NULL, NULL, 1, 0, 1, NULL, 7, 'Administrator', '系统菜单', '菜单权限新增', 1, 2, 'com.hb0730.boot.admin.project.system.menu.controller.SystemMenuController.savePermissionByMenuId()', 'POST', '/api/v1/menu/permission/save/1243819283653275650', '127.0.0.1', NULL, '[{\"description\":\"\",\"name\":\"组织查询\",\"enname\":\"orgSelect\",\"mark\":\"org:select\",\"sort\":0,\"startRow\":1,\"pageSize\":10,\"isEnabled\":1}]', '{\"code\":\"BA20000\",\"message\":\"请求成功\",\"data\":\"新增成功\"}', NULL);
INSERT INTO `t_system_oper_log` VALUES (-1, '2020-04-10 21:04:14', NULL, NULL, 1, 0, 1, NULL, 8, 'Administrator', '系统菜单', '菜单权限新增', 1, 2, 'com.hb0730.boot.admin.project.system.menu.controller.SystemMenuController.savePermissionByMenuId()', 'POST', '/api/v1/menu/permission/save/1243819283653275650', '127.0.0.1', NULL, '[{\"description\":\"\",\"name\":\"组织新增\",\"enname\":\"orgSave\",\"mark\":\"org:save\",\"sort\":0,\"startRow\":1,\"pageSize\":10,\"isEnabled\":1}]', '{\"code\":\"BA20000\",\"message\":\"请求成功\",\"data\":\"新增成功\"}', NULL);
INSERT INTO `t_system_oper_log` VALUES (-1, '2020-04-10 21:04:36', NULL, NULL, 1, 0, 1, NULL, 9, 'Administrator', '系统菜单', '菜单权限新增', 1, 2, 'com.hb0730.boot.admin.project.system.menu.controller.SystemMenuController.savePermissionByMenuId()', 'POST', '/api/v1/menu/permission/save/1243819283653275650', '127.0.0.1', NULL, '[{\"description\":\"\",\"name\":\"组织修改\",\"enname\":\"orgUpdate\",\"mark\":\"org:update\",\"sort\":0,\"startRow\":1,\"pageSize\":10,\"isEnabled\":1}]', '{\"code\":\"BA20000\",\"message\":\"请求成功\",\"data\":\"新增成功\"}', NULL);
INSERT INTO `t_system_oper_log` VALUES (-1, '2020-04-10 21:04:51', NULL, NULL, 1, 0, 1, NULL, 10, 'Administrator', '系统菜单', '菜单权限新增', 1, 2, 'com.hb0730.boot.admin.project.system.menu.controller.SystemMenuController.savePermissionByMenuId()', 'POST', '/api/v1/menu/permission/save/1243819283653275650', '127.0.0.1', NULL, '[{\"description\":\"\",\"name\":\"组织删除\",\"enname\":\"orgDelete\",\"mark\":\"org:delete\",\"sort\":0,\"startRow\":1,\"pageSize\":10,\"isEnabled\":1}]', '{\"code\":\"BA20000\",\"message\":\"请求成功\",\"data\":\"新增成功\"}', NULL);
INSERT INTO `t_system_oper_log` VALUES (-1, '2020-04-10 21:05:32', NULL, NULL, 1, 0, 1, NULL, 11, 'Administrator', '系统菜单', '菜单权限新增', 1, 2, 'com.hb0730.boot.admin.project.system.menu.controller.SystemMenuController.savePermissionByMenuId()', 'POST', '/api/v1/menu/permission/save/1244229413641314306', '127.0.0.1', NULL, '[{\"description\":\"\",\"name\":\"角色查询\",\"enname\":\"roleQuery\",\"mark\":\"role:query\",\"sort\":0,\"startRow\":1,\"pageSize\":10,\"isEnabled\":1}]', '{\"code\":\"BA20000\",\"message\":\"请求成功\",\"data\":\"新增成功\"}', NULL);
INSERT INTO `t_system_oper_log` VALUES (-1, '2020-04-10 21:05:48', NULL, NULL, 1, 0, 1, NULL, 12, 'Administrator', '系统菜单', '菜单权限新增', 1, 2, 'com.hb0730.boot.admin.project.system.menu.controller.SystemMenuController.savePermissionByMenuId()', 'POST', '/api/v1/menu/permission/save/1244229413641314306', '127.0.0.1', NULL, '[{\"description\":\"\",\"name\":\"角色新增\",\"enname\":\"roleSave\",\"mark\":\"role:save\",\"sort\":0,\"startRow\":1,\"pageSize\":10,\"isEnabled\":1}]', '{\"code\":\"BA20000\",\"message\":\"请求成功\",\"data\":\"新增成功\"}', NULL);
INSERT INTO `t_system_oper_log` VALUES (-1, '2020-04-10 21:06:09', NULL, NULL, 1, 0, 1, NULL, 13, 'Administrator', '系统菜单', '菜单权限新增', 1, 2, 'com.hb0730.boot.admin.project.system.menu.controller.SystemMenuController.savePermissionByMenuId()', 'POST', '/api/v1/menu/permission/save/1244229413641314306', '127.0.0.1', NULL, '[{\"description\":\"\",\"name\":\"角色修改\",\"enname\":\"roleUpdate\",\"mark\":\"role:update\",\"sort\":0,\"startRow\":1,\"pageSize\":10,\"isEnabled\":1}]', '{\"code\":\"BA20000\",\"message\":\"请求成功\",\"data\":\"新增成功\"}', NULL);
INSERT INTO `t_system_oper_log` VALUES (-1, '2020-04-10 21:06:26', NULL, NULL, 1, 0, 1, NULL, 14, 'Administrator', '系统菜单', '菜单权限新增', 1, 2, 'com.hb0730.boot.admin.project.system.menu.controller.SystemMenuController.savePermissionByMenuId()', 'POST', '/api/v1/menu/permission/save/1244229413641314306', '127.0.0.1', NULL, '[{\"description\":\"\",\"name\":\"角色删除\",\"enname\":\"roleDelete\",\"mark\":\"role:delete\",\"sort\":0,\"startRow\":1,\"pageSize\":10,\"isEnabled\":1}]', '{\"code\":\"BA20000\",\"message\":\"请求成功\",\"data\":\"新增成功\"}', NULL);
INSERT INTO `t_system_oper_log` VALUES (-1, '2020-04-10 21:06:56', NULL, NULL, 1, 0, 1, NULL, 15, 'Administrator', '系统菜单', '菜单权限新增', 1, 2, 'com.hb0730.boot.admin.project.system.menu.controller.SystemMenuController.savePermissionByMenuId()', 'POST', '/api/v1/menu/permission/save/1244229413641314306', '127.0.0.1', NULL, '[{\"description\":\"\",\"name\":\"角色权限保存\",\"enname\":\"rolePermissionSave\",\"mark\":\"role:permission:save\",\"sort\":0,\"startRow\":1,\"pageSize\":10,\"isEnabled\":1}]', '{\"code\":\"BA20000\",\"message\":\"请求成功\",\"data\":\"新增成功\"}', NULL);
INSERT INTO `t_system_oper_log` VALUES (-1, '2020-04-10 21:07:44', NULL, NULL, 1, 0, 1, NULL, 16, 'Administrator', '系统菜单', '菜单权限新增', 1, 2, 'com.hb0730.boot.admin.project.system.menu.controller.SystemMenuController.savePermissionByMenuId()', 'POST', '/api/v1/menu/permission/save/1244229413641314306', '127.0.0.1', NULL, '[{\"description\":\"\",\"name\":\"角色权限查询\",\"enname\":\"rolePermissionQuery\",\"mark\":\"role:permission:query\",\"sort\":0,\"startRow\":1,\"pageSize\":10,\"isEnabled\":1}]', '{\"code\":\"BA20000\",\"message\":\"请求成功\",\"data\":\"新增成功\"}', NULL);
INSERT INTO `t_system_oper_log` VALUES (-1, '2020-04-10 21:08:11', NULL, NULL, 1, 0, 1, NULL, 17, 'Administrator', '系统菜单', '菜单权限新增', 1, 2, 'com.hb0730.boot.admin.project.system.menu.controller.SystemMenuController.savePermissionByMenuId()', 'POST', '/api/v1/menu/permission/save/1244229413641314306', '127.0.0.1', NULL, '[{\"description\":\"\",\"name\":\"角色组织查询\",\"enname\":\"roleOrgQuery\",\"mark\":\"role:org:query\",\"sort\":0,\"startRow\":1,\"pageSize\":10,\"isEnabled\":1}]', '{\"code\":\"BA20000\",\"message\":\"请求成功\",\"data\":\"新增成功\"}', NULL);
INSERT INTO `t_system_oper_log` VALUES (-1, '2020-04-10 21:08:36', NULL, NULL, 1, 0, 1, NULL, 18, 'Administrator', '系统菜单', '菜单权限新增', 1, 2, 'com.hb0730.boot.admin.project.system.menu.controller.SystemMenuController.savePermissionByMenuId()', 'POST', '/api/v1/menu/permission/save/1244229413641314306', '127.0.0.1', NULL, '[{\"description\":\"\",\"name\":\"角色组织修改\",\"enname\":\"roleOrgUpdate\",\"mark\":\"role:org:update\",\"sort\":0,\"startRow\":1,\"pageSize\":10,\"isEnabled\":1}]', '{\"code\":\"BA20000\",\"message\":\"请求成功\",\"data\":\"新增成功\"}', NULL);
INSERT INTO `t_system_oper_log` VALUES (-1, '2020-04-10 21:09:12', NULL, NULL, 1, 0, 1, NULL, 19, 'Administrator', '系统菜单', '菜单权限新增', 1, 2, 'com.hb0730.boot.admin.project.system.menu.controller.SystemMenuController.savePermissionByMenuId()', 'POST', '/api/v1/menu/permission/save/1244469536593256449', '127.0.0.1', NULL, '[{\"description\":\"\",\"name\":\"字典查询\",\"enname\":\"dictQuery\",\"mark\":\"dict:query\",\"sort\":0,\"startRow\":1,\"pageSize\":10,\"isEnabled\":1}]', '{\"code\":\"BA20000\",\"message\":\"请求成功\",\"data\":\"新增成功\"}', NULL);
INSERT INTO `t_system_oper_log` VALUES (-1, '2020-04-10 21:09:40', NULL, NULL, 1, 0, 1, NULL, 20, 'Administrator', '系统菜单', '菜单权限新增', 1, 2, 'com.hb0730.boot.admin.project.system.menu.controller.SystemMenuController.savePermissionByMenuId()', 'POST', '/api/v1/menu/permission/save/1244469536593256449', '127.0.0.1', NULL, '[{\"description\":\"\",\"name\":\"字典新增\",\"enname\":\"dictSave\",\"mark\":\"dict:save\",\"sort\":0,\"startRow\":1,\"pageSize\":10,\"isEnabled\":1}]', '{\"code\":\"BA20000\",\"message\":\"请求成功\",\"data\":\"新增成功\"}', NULL);
INSERT INTO `t_system_oper_log` VALUES (-1, '2020-04-10 21:10:06', NULL, NULL, 1, 0, 1, NULL, 21, 'Administrator', '系统菜单', '菜单权限新增', 1, 2, 'com.hb0730.boot.admin.project.system.menu.controller.SystemMenuController.savePermissionByMenuId()', 'POST', '/api/v1/menu/permission/save/1244469536593256449', '127.0.0.1', NULL, '[{\"description\":\"\",\"name\":\"字典修改\",\"enname\":\"dictUpdate\",\"mark\":\"dict:update\",\"sort\":0,\"startRow\":1,\"pageSize\":10,\"isEnabled\":1}]', '{\"code\":\"BA20000\",\"message\":\"请求成功\",\"data\":\"新增成功\"}', NULL);
INSERT INTO `t_system_oper_log` VALUES (-1, '2020-04-10 21:10:23', NULL, NULL, 1, 0, 1, NULL, 22, 'Administrator', '系统菜单', '菜单权限新增', 1, 2, 'com.hb0730.boot.admin.project.system.menu.controller.SystemMenuController.savePermissionByMenuId()', 'POST', '/api/v1/menu/permission/save/1244469536593256449', '127.0.0.1', NULL, '[{\"description\":\"\",\"name\":\"字典删除\",\"enname\":\"dictDelete\",\"mark\":\"dict:delete\",\"sort\":0,\"startRow\":1,\"pageSize\":10,\"isEnabled\":1}]', '{\"code\":\"BA20000\",\"message\":\"请求成功\",\"data\":\"新增成功\"}', NULL);
INSERT INTO `t_system_oper_log` VALUES (-1, '2020-04-10 21:11:09', NULL, NULL, 1, 0, 1, NULL, 23, 'Administrator', '系统菜单', '菜单权限新增', 1, 2, 'com.hb0730.boot.admin.project.system.menu.controller.SystemMenuController.savePermissionByMenuId()', 'POST', '/api/v1/menu/permission/save/1243039012540571649', '127.0.0.1', NULL, '[{\"description\":\"\",\"name\":\"用户查询\",\"enname\":\"userQuery\",\"mark\":\"user:query\",\"sort\":0,\"startRow\":1,\"pageSize\":10,\"isEnabled\":1}]', '{\"code\":\"BA20000\",\"message\":\"请求成功\",\"data\":\"新增成功\"}', NULL);
INSERT INTO `t_system_oper_log` VALUES (-1, '2020-04-10 21:11:30', NULL, NULL, 1, 0, 1, NULL, 24, 'Administrator', '系统菜单', '菜单权限新增', 1, 2, 'com.hb0730.boot.admin.project.system.menu.controller.SystemMenuController.savePermissionByMenuId()', 'POST', '/api/v1/menu/permission/save/1243039012540571649', '127.0.0.1', NULL, '[{\"description\":\"\",\"name\":\"用户新增\",\"enname\":\"userSave\",\"mark\":\"user:save\",\"sort\":0,\"startRow\":1,\"pageSize\":10,\"isEnabled\":1}]', '{\"code\":\"BA20000\",\"message\":\"请求成功\",\"data\":\"新增成功\"}', NULL);
INSERT INTO `t_system_oper_log` VALUES (-1, '2020-04-10 21:11:51', NULL, NULL, 1, 0, 1, NULL, 25, 'Administrator', '系统菜单', '菜单权限新增', 1, 2, 'com.hb0730.boot.admin.project.system.menu.controller.SystemMenuController.savePermissionByMenuId()', 'POST', '/api/v1/menu/permission/save/1243039012540571649', '127.0.0.1', NULL, '[{\"description\":\"\",\"name\":\"用户修改\",\"enname\":\"userUpdate\",\"mark\":\"user:update\",\"sort\":0,\"startRow\":1,\"pageSize\":10,\"isEnabled\":1}]', '{\"code\":\"BA20000\",\"message\":\"请求成功\",\"data\":\"新增成功\"}', NULL);
INSERT INTO `t_system_oper_log` VALUES (-1, '2020-04-10 21:12:33', NULL, NULL, 1, 0, 1, NULL, 26, 'Administrator', '系统菜单', '菜单权限新增', 1, 2, 'com.hb0730.boot.admin.project.system.menu.controller.SystemMenuController.savePermissionByMenuId()', 'POST', '/api/v1/menu/permission/save/1243039012540571649', '127.0.0.1', NULL, '[{\"description\":\"\",\"name\":\"用户密码重置\",\"enname\":\"userRestPassword\",\"mark\":\"user:rest:password\",\"sort\":0,\"startRow\":1,\"pageSize\":10,\"isEnabled\":1}]', '{\"code\":\"BA20000\",\"message\":\"请求成功\",\"data\":\"新增成功\"}', NULL);
INSERT INTO `t_system_oper_log` VALUES (-1, '2020-04-10 21:12:51', NULL, NULL, 1, 0, 1, NULL, 27, 'Administrator', '系统菜单', '菜单权限新增', 1, 2, 'com.hb0730.boot.admin.project.system.menu.controller.SystemMenuController.savePermissionByMenuId()', 'POST', '/api/v1/menu/permission/save/1243039012540571649', '127.0.0.1', NULL, '[{\"description\":\"\",\"name\":\"用户删除\",\"enname\":\"userDelete\",\"mark\":\"user:delete\",\"sort\":0,\"startRow\":1,\"pageSize\":10,\"isEnabled\":1}]', '{\"code\":\"BA20000\",\"message\":\"请求成功\",\"data\":\"新增成功\"}', NULL);
INSERT INTO `t_system_oper_log` VALUES (-1, '2020-04-10 21:13:12', NULL, NULL, 1, 0, 1, NULL, 28, 'Administrator', '系统菜单', '菜单权限新增', 1, 2, 'com.hb0730.boot.admin.project.system.menu.controller.SystemMenuController.savePermissionByMenuId()', 'POST', '/api/v1/menu/permission/save/1243039012540571649', '127.0.0.1', NULL, '[{\"description\":\"\",\"name\":\"用户导出\",\"enname\":\"userExport\",\"mark\":\"user:export\",\"sort\":0,\"startRow\":1,\"pageSize\":10,\"isEnabled\":1}]', '{\"code\":\"BA20000\",\"message\":\"请求成功\",\"data\":\"新增成功\"}', NULL);
INSERT INTO `t_system_oper_log` VALUES (-1, '2020-04-10 21:13:28', NULL, NULL, 1, 0, 1, NULL, 29, 'Administrator', '系统菜单', '菜单权限新增', 1, 2, 'com.hb0730.boot.admin.project.system.menu.controller.SystemMenuController.savePermissionByMenuId()', 'POST', '/api/v1/menu/permission/save/1243039012540571649', '127.0.0.1', NULL, '[{\"description\":\"\",\"name\":\"用户导入\",\"enname\":\"userUpload\",\"mark\":\"user:upload\",\"sort\":0,\"startRow\":1,\"pageSize\":10,\"isEnabled\":1}]', '{\"code\":\"BA20000\",\"message\":\"请求成功\",\"data\":\"新增成功\"}', NULL);
INSERT INTO `t_system_oper_log` VALUES (-1, '2020-04-10 21:14:00', NULL, NULL, 1, 0, 1, NULL, 30, 'Administrator', '系统菜单', '菜单权限新增', 1, 2, 'com.hb0730.boot.admin.project.system.menu.controller.SystemMenuController.savePermissionByMenuId()', 'POST', '/api/v1/menu/permission/save/1244229759709143042', '127.0.0.1', NULL, '[{\"description\":\"\",\"name\":\"岗位查询\",\"enname\":\"postQuery\",\"mark\":\"post:query\",\"sort\":0,\"startRow\":1,\"pageSize\":10,\"isEnabled\":1}]', '{\"code\":\"BA20000\",\"message\":\"请求成功\",\"data\":\"新增成功\"}', NULL);
INSERT INTO `t_system_oper_log` VALUES (-1, '2020-04-10 21:14:21', NULL, NULL, 1, 0, 1, NULL, 31, 'Administrator', '系统菜单', '菜单权限新增', 1, 2, 'com.hb0730.boot.admin.project.system.menu.controller.SystemMenuController.savePermissionByMenuId()', 'POST', '/api/v1/menu/permission/save/1244229759709143042', '127.0.0.1', NULL, '[{\"description\":\"\",\"name\":\"岗位新增\",\"enname\":\"postSave\",\"mark\":\"post:save\",\"sort\":0,\"startRow\":1,\"pageSize\":10,\"isEnabled\":1}]', '{\"code\":\"BA20000\",\"message\":\"请求成功\",\"data\":\"新增成功\"}', NULL);
INSERT INTO `t_system_oper_log` VALUES (-1, '2020-04-10 21:14:40', NULL, NULL, 1, 0, 1, NULL, 32, 'Administrator', '系统菜单', '菜单权限新增', 1, 2, 'com.hb0730.boot.admin.project.system.menu.controller.SystemMenuController.savePermissionByMenuId()', 'POST', '/api/v1/menu/permission/save/1244229759709143042', '127.0.0.1', NULL, '[{\"description\":\"\",\"name\":\"岗位修改\",\"enname\":\"postUpdate\",\"mark\":\"post:update\",\"sort\":0,\"startRow\":1,\"pageSize\":10,\"isEnabled\":1}]', '{\"code\":\"BA20000\",\"message\":\"请求成功\",\"data\":\"新增成功\"}', NULL);
INSERT INTO `t_system_oper_log` VALUES (-1, '2020-04-10 21:14:57', NULL, NULL, 1, 0, 1, NULL, 33, 'Administrator', '系统菜单', '菜单权限新增', 1, 2, 'com.hb0730.boot.admin.project.system.menu.controller.SystemMenuController.savePermissionByMenuId()', 'POST', '/api/v1/menu/permission/save/1244229759709143042', '127.0.0.1', NULL, '[{\"description\":\"\",\"name\":\"岗位删除\",\"enname\":\"postDelete\",\"mark\":\"post:delete\",\"sort\":0,\"startRow\":1,\"pageSize\":10,\"isEnabled\":1}]', '{\"code\":\"BA20000\",\"message\":\"请求成功\",\"data\":\"新增成功\"}', NULL);
INSERT INTO `t_system_oper_log` VALUES (-1, '2020-04-10 21:15:15', NULL, NULL, 1, 0, 1, NULL, 34, 'Administrator', '系统菜单', '菜单权限新增', 1, 2, 'com.hb0730.boot.admin.project.system.menu.controller.SystemMenuController.savePermissionByMenuId()', 'POST', '/api/v1/menu/permission/save/1244229759709143042', '127.0.0.1', NULL, '[{\"description\":\"\",\"name\":\"岗位导出\",\"enname\":\"postExport\",\"mark\":\"post:export\",\"sort\":0,\"startRow\":1,\"pageSize\":10,\"isEnabled\":1}]', '{\"code\":\"BA20000\",\"message\":\"请求成功\",\"data\":\"新增成功\"}', NULL);
INSERT INTO `t_system_oper_log` VALUES (-1, '2020-04-10 21:15:31', NULL, NULL, 1, 0, 1, NULL, 35, 'Administrator', '系统菜单', '菜单权限新增', 1, 2, 'com.hb0730.boot.admin.project.system.menu.controller.SystemMenuController.savePermissionByMenuId()', 'POST', '/api/v1/menu/permission/save/1244229759709143042', '127.0.0.1', NULL, '[{\"description\":\"\",\"name\":\"岗位导入\",\"enname\":\"postUpload\",\"mark\":\"post:upload\",\"sort\":0,\"startRow\":1,\"pageSize\":10,\"isEnabled\":1}]', '{\"code\":\"BA20000\",\"message\":\"请求成功\",\"data\":\"新增成功\"}', NULL);
INSERT INTO `t_system_oper_log` VALUES (-1, '2020-04-10 21:16:15', NULL, NULL, 1, 0, 1, NULL, 36, 'Administrator', '系统菜单', '菜单权限新增', 1, 2, 'com.hb0730.boot.admin.project.system.menu.controller.SystemMenuController.savePermissionByMenuId()', 'POST', '/api/v1/menu/permission/save/1245253870879158274', '127.0.0.1', NULL, '[{\"description\":\"\",\"name\":\"登录日志查询\",\"enname\":\"loginLogQuery\",\"mark\":\"login:log:query\",\"sort\":0,\"startRow\":1,\"pageSize\":10,\"isEnabled\":1}]', '{\"code\":\"BA20000\",\"message\":\"请求成功\",\"data\":\"新增成功\"}', NULL);
INSERT INTO `t_system_oper_log` VALUES (-1, '2020-04-10 21:16:45', NULL, NULL, 1, 0, 1, NULL, 37, 'Administrator', '系统菜单', '菜单权限新增', 1, 2, 'com.hb0730.boot.admin.project.system.menu.controller.SystemMenuController.savePermissionByMenuId()', 'POST', '/api/v1/menu/permission/save/1245253870879158274', '127.0.0.1', NULL, '[{\"description\":\"\",\"name\":\"登录日志导出\",\"enname\":\"loginLogExport\",\"mark\":\"login:log:export\",\"sort\":0,\"startRow\":1,\"pageSize\":10,\"isEnabled\":1}]', '{\"code\":\"BA20000\",\"message\":\"请求成功\",\"data\":\"新增成功\"}', NULL);
INSERT INTO `t_system_oper_log` VALUES (-1, '2020-04-10 21:17:02', NULL, NULL, 1, 0, 1, NULL, 38, 'Administrator', '系统菜单', '菜单权限新增', 1, 2, 'com.hb0730.boot.admin.project.system.menu.controller.SystemMenuController.savePermissionByMenuId()', 'POST', '/api/v1/menu/permission/save/1245253870879158274', '127.0.0.1', NULL, '[{\"description\":\"\",\"name\":\"登录日志删除\",\"enname\":\"loginLogDelete\",\"mark\":\"login:log:delete\",\"sort\":0,\"startRow\":1,\"pageSize\":10,\"isEnabled\":1}]', '{\"code\":\"BA20000\",\"message\":\"请求成功\",\"data\":\"新增成功\"}', NULL);
INSERT INTO `t_system_oper_log` VALUES (-1, '2020-04-10 21:17:20', NULL, NULL, 1, 0, 1, NULL, 39, 'Administrator', '系统菜单', '菜单权限新增', 1, 2, 'com.hb0730.boot.admin.project.system.menu.controller.SystemMenuController.savePermissionByMenuId()', 'POST', '/api/v1/menu/permission/save/1245253870879158274', '127.0.0.1', NULL, '[{\"description\":\"\",\"name\":\"登录日志清除\",\"enname\":\"loginLogClean\",\"mark\":\"login:log:clean\",\"sort\":0,\"startRow\":1,\"pageSize\":10,\"isEnabled\":1}]', '{\"code\":\"BA20000\",\"message\":\"请求成功\",\"data\":\"新增成功\"}', NULL);
INSERT INTO `t_system_oper_log` VALUES (-1, '2020-04-10 21:17:57', NULL, NULL, 1, 0, 1, NULL, 40, 'Administrator', '系统菜单', '菜单权限新增', 1, 2, 'com.hb0730.boot.admin.project.system.menu.controller.SystemMenuController.savePermissionByMenuId()', 'POST', '/api/v1/menu/permission/save/1245286153090727938', '127.0.0.1', NULL, '[{\"description\":\"\",\"name\":\"操作日志查询\",\"enname\":\"operLogQuery\",\"mark\":\"oper:log:query\",\"sort\":0,\"startRow\":1,\"pageSize\":10,\"isEnabled\":1}]', '{\"code\":\"BA20000\",\"message\":\"请求成功\",\"data\":\"新增成功\"}', NULL);
INSERT INTO `t_system_oper_log` VALUES (-1, '2020-04-10 21:18:25', NULL, NULL, 1, 0, 1, NULL, 41, 'Administrator', '系统菜单', '菜单权限新增', 1, 2, 'com.hb0730.boot.admin.project.system.menu.controller.SystemMenuController.savePermissionByMenuId()', 'POST', '/api/v1/menu/permission/save/1245286153090727938', '127.0.0.1', NULL, '[{\"description\":\"\",\"name\":\"查看日志删除\",\"enname\":\"operLogDelete\",\"mark\":\"oper:log:delete\",\"sort\":0,\"startRow\":1,\"pageSize\":10,\"isEnabled\":1}]', '{\"code\":\"BA20000\",\"message\":\"请求成功\",\"data\":\"新增成功\"}', NULL);
INSERT INTO `t_system_oper_log` VALUES (-1, '2020-04-10 21:19:13', NULL, NULL, 1, 0, 1, NULL, 42, 'Administrator', '系统菜单', '菜单权限新增', 1, 2, 'com.hb0730.boot.admin.project.system.menu.controller.SystemMenuController.savePermissionByMenuId()', 'POST', '/api/v1/menu/permission/save/1245286153090727938', '127.0.0.1', NULL, '[{\"description\":\"\",\"name\":\"操作日志清除\",\"enname\":\"operLogClean\",\"mark\":\"oper:log:clean\",\"sort\":0,\"startRow\":1,\"pageSize\":10,\"isEnabled\":1}]', '{\"code\":\"BA20000\",\"message\":\"请求成功\",\"data\":\"新增成功\"}', NULL);
INSERT INTO `t_system_oper_log` VALUES (-1, '2020-04-10 21:19:40', NULL, NULL, 1, 0, 1, NULL, 43, 'Administrator', '系统菜单', '菜单权限新增', 1, 2, 'com.hb0730.boot.admin.project.system.menu.controller.SystemMenuController.savePermissionByMenuId()', 'POST', '/api/v1/menu/permission/save/1245286153090727938', '127.0.0.1', NULL, '[{\"description\":\"\",\"name\":\"操作日志导出\",\"enname\":\"operLogExport\",\"mark\":\"oper:log:export\",\"sort\":0,\"startRow\":1,\"pageSize\":10,\"isEnabled\":1}]', '{\"code\":\"BA20000\",\"message\":\"请求成功\",\"data\":\"新增成功\"}', NULL);
INSERT INTO `t_system_oper_log` VALUES (-1, '2020-04-10 21:20:16', NULL, NULL, 1, 0, 1, NULL, 44, 'Administrator', '系统菜单', '菜单权限新增', 1, 2, 'com.hb0730.boot.admin.project.system.menu.controller.SystemMenuController.savePermissionByMenuId()', 'POST', '/api/v1/menu/permission/save/1245627134071779330', '127.0.0.1', NULL, '[{\"description\":\"\",\"name\":\"在线用户查询\",\"enname\":\"userOnlineQuery\",\"mark\":\"user:online:query\",\"sort\":0,\"startRow\":1,\"pageSize\":10,\"isEnabled\":1}]', '{\"code\":\"BA20000\",\"message\":\"请求成功\",\"data\":\"新增成功\"}', NULL);
INSERT INTO `t_system_oper_log` VALUES (-1, '2020-04-10 21:20:55', NULL, NULL, 1, 0, 1, NULL, 45, 'Administrator', '系统菜单', '菜单权限新增', 1, 2, 'com.hb0730.boot.admin.project.system.menu.controller.SystemMenuController.savePermissionByMenuId()', 'POST', '/api/v1/menu/permission/save/1245627134071779330', '127.0.0.1', NULL, '[{\"description\":\"\",\"name\":\"在线用户强退\",\"enname\":\"userOnlineLogout\",\"mark\":\"user:online:logout\",\"sort\":0,\"startRow\":1,\"pageSize\":10,\"isEnabled\":1}]', '{\"code\":\"BA20000\",\"message\":\"请求成功\",\"data\":\"新增成功\"}', NULL);
INSERT INTO `t_system_oper_log` VALUES (-1, '2020-04-10 21:21:41', NULL, NULL, 1, 0, 1, NULL, 46, 'Administrator', '系统菜单', '菜单权限新增', 1, 2, 'com.hb0730.boot.admin.project.system.menu.controller.SystemMenuController.savePermissionByMenuId()', 'POST', '/api/v1/menu/permission/save/1247060409164169218', '127.0.0.1', NULL, '[{\"description\":\"\",\"name\":\"定时任务查询\",\"enname\":\"jobQuery\",\"mark\":\"job:query\",\"sort\":0,\"startRow\":1,\"pageSize\":10,\"isEnabled\":1}]', '{\"code\":\"BA20000\",\"message\":\"请求成功\",\"data\":\"新增成功\"}', NULL);
INSERT INTO `t_system_oper_log` VALUES (-1, '2020-04-10 21:22:03', NULL, NULL, 1, 0, 1, NULL, 47, 'Administrator', '系统菜单', '菜单权限新增', 1, 2, 'com.hb0730.boot.admin.project.system.menu.controller.SystemMenuController.savePermissionByMenuId()', 'POST', '/api/v1/menu/permission/save/1247060409164169218', '127.0.0.1', NULL, '[{\"description\":\"\",\"name\":\"定时任务新增\",\"enname\":\"jobSave\",\"mark\":\"job:save\",\"sort\":0,\"startRow\":1,\"pageSize\":10,\"isEnabled\":1}]', '{\"code\":\"BA20000\",\"message\":\"请求成功\",\"data\":\"新增成功\"}', NULL);
INSERT INTO `t_system_oper_log` VALUES (-1, '2020-04-10 21:22:28', NULL, NULL, 1, 0, 1, NULL, 48, 'Administrator', '系统菜单', '菜单权限新增', 1, 2, 'com.hb0730.boot.admin.project.system.menu.controller.SystemMenuController.savePermissionByMenuId()', 'POST', '/api/v1/menu/permission/save/1247060409164169218', '127.0.0.1', NULL, '[{\"description\":\"\",\"name\":\"定时任务修改\",\"enname\":\"jobUpdate\",\"mark\":\"job:update\",\"sort\":0,\"startRow\":1,\"pageSize\":10,\"isEnabled\":1}]', '{\"code\":\"BA20000\",\"message\":\"请求成功\",\"data\":\"新增成功\"}', NULL);
INSERT INTO `t_system_oper_log` VALUES (-1, '2020-04-10 21:22:44', NULL, NULL, 1, 0, 1, NULL, 49, 'Administrator', '系统菜单', '菜单权限新增', 1, 2, 'com.hb0730.boot.admin.project.system.menu.controller.SystemMenuController.savePermissionByMenuId()', 'POST', '/api/v1/menu/permission/save/1247060409164169218', '127.0.0.1', NULL, '[{\"description\":\"\",\"name\":\"定时任务删除\",\"enname\":\"jobDelete\",\"mark\":\"job:delete\",\"sort\":0,\"startRow\":1,\"pageSize\":10,\"isEnabled\":1}]', '{\"code\":\"BA20000\",\"message\":\"请求成功\",\"data\":\"新增成功\"}', NULL);
INSERT INTO `t_system_oper_log` VALUES (-1, '2020-04-10 21:23:04', NULL, NULL, 1, 0, 1, NULL, 50, 'Administrator', '系统菜单', '菜单权限新增', 1, 2, 'com.hb0730.boot.admin.project.system.menu.controller.SystemMenuController.savePermissionByMenuId()', 'POST', '/api/v1/menu/permission/save/1247060409164169218', '127.0.0.1', NULL, '[{\"description\":\"\",\"name\":\"定时任务导出\",\"enname\":\"jobExport\",\"mark\":\"job:export\",\"sort\":0,\"startRow\":1,\"pageSize\":10,\"isEnabled\":1}]', '{\"code\":\"BA20000\",\"message\":\"请求成功\",\"data\":\"新增成功\"}', NULL);
INSERT INTO `t_system_oper_log` VALUES (-1, '2020-04-10 21:23:22', NULL, NULL, 1, 0, 1, NULL, 51, 'Administrator', '系统菜单', '菜单权限新增', 1, 2, 'com.hb0730.boot.admin.project.system.menu.controller.SystemMenuController.savePermissionByMenuId()', 'POST', '/api/v1/menu/permission/save/1247060409164169218', '127.0.0.1', NULL, '[{\"description\":\"\",\"name\":\"定时任务导入\",\"enname\":\"jobUpload\",\"mark\":\"job:upload\",\"sort\":0,\"startRow\":1,\"pageSize\":10,\"isEnabled\":1}]', '{\"code\":\"BA20000\",\"message\":\"请求成功\",\"data\":\"新增成功\"}', NULL);
INSERT INTO `t_system_oper_log` VALUES (-1, '2020-04-10 21:24:16', NULL, NULL, 1, 0, 1, NULL, 52, 'Administrator', '系统菜单', '菜单权限新增', 1, 2, 'com.hb0730.boot.admin.project.system.menu.controller.SystemMenuController.savePermissionByMenuId()', 'POST', '/api/v1/menu/permission/save/1247060409164169218', '127.0.0.1', NULL, '[{\"description\":\"\",\"name\":\"调度日志查询\",\"enname\":\"jobLogQuery\",\"mark\":\"job:log:query\",\"sort\":0,\"startRow\":1,\"pageSize\":10,\"isEnabled\":1}]', '{\"code\":\"BA20000\",\"message\":\"请求成功\",\"data\":\"新增成功\"}', NULL);
INSERT INTO `t_system_oper_log` VALUES (-1, '2020-04-10 21:24:52', NULL, NULL, 1, 0, 1, NULL, 53, 'Administrator', '系统菜单', '菜单权限新增', 1, 2, 'com.hb0730.boot.admin.project.system.menu.controller.SystemMenuController.savePermissionByMenuId()', 'POST', '/api/v1/menu/permission/save/1247060409164169218', '127.0.0.1', NULL, '[{\"description\":\"\",\"name\":\"调度日志导出\",\"enname\":\"jobLogExport\",\"mark\":\"job:log:export\",\"sort\":0,\"startRow\":1,\"pageSize\":10,\"isEnabled\":1}]', '{\"code\":\"BA20000\",\"message\":\"请求成功\",\"data\":\"新增成功\"}', NULL);
INSERT INTO `t_system_oper_log` VALUES (-1, '2020-04-10 21:25:15', NULL, NULL, 1, 0, 1, NULL, 54, 'Administrator', '系统菜单', '菜单权限新增', 1, 2, 'com.hb0730.boot.admin.project.system.menu.controller.SystemMenuController.savePermissionByMenuId()', 'POST', '/api/v1/menu/permission/save/1247060409164169218', '127.0.0.1', NULL, '[{\"description\":\"\",\"name\":\"调度日志删除\",\"enname\":\"jobLogDelete\",\"mark\":\"log:log:delete\",\"sort\":0,\"startRow\":1,\"pageSize\":10,\"isEnabled\":1}]', '{\"code\":\"BA20000\",\"message\":\"请求成功\",\"data\":\"新增成功\"}', NULL);
INSERT INTO `t_system_oper_log` VALUES (-1, '2020-04-10 21:26:02', NULL, NULL, 1, 0, 1, NULL, 55, 'Administrator', '系统菜单', '菜单权限新增', 1, 2, 'com.hb0730.boot.admin.project.system.menu.controller.SystemMenuController.savePermissionByMenuId()', 'POST', '/api/v1/menu/permission/save/1247060409164169218', '127.0.0.1', NULL, '[{\"description\":\"\",\"name\":\"调度日志清除\",\"enname\":\"jobLogClean\",\"mark\":\"job:log:clean\",\"sort\":0,\"startRow\":1,\"pageSize\":10,\"isEnabled\":1}]', '{\"code\":\"BA20000\",\"message\":\"请求成功\",\"data\":\"新增成功\"}', NULL);
INSERT INTO `t_system_oper_log` VALUES (-1, '2020-04-10 21:26:23', NULL, NULL, 1, 0, 1, NULL, 56, 'Administrator', '系统角色', '角色数据范围更新', 1, 2, 'com.hb0730.boot.admin.project.system.role.controller.SystemRoleController.saveOrgIdByRoleId()', 'POST', '/api/v1/role/org/role/save/-1', '127.0.0.1', NULL, '[[1]]', '{\"code\":\"BA20000\",\"message\":\"请求成功\",\"data\":\"保存成功\"}', NULL);
INSERT INTO `t_system_oper_log` VALUES (-1, '2020-04-10 21:26:56', NULL, NULL, 1, 0, 1, NULL, 57, 'Administrator', '系统角色', '角色权限更新', 1, 2, 'com.hb0730.boot.admin.project.system.role.controller.SystemRoleController.savePermissionByRoleId()', 'POST', '/api/v1/role/permission/save/-1', '127.0.0.1', NULL, '[[1245178069475368962,1248596155138236417,1248596245210914817,1248596752621035521,1248596972893298689,1248597123867271169,1248597297092026370,1248597441329946625,1248597655650492418,1248597748256530433,1248597810781020161,1248597984873996289,1248598049311088641,1248598141019545601,1248598212192690178,1248598336591552513,1248598538190774274,1248598648865873921,1248598754298093569,1248598905154625538,1248599023673073665,1248599133643530242,1248599203227033602,1248599396081131522,1248599486774566914,1248599572795547650,1248599751112187906,1248599824055328769,1248599912173461506,1248599981664690177,1248600113541996545,1248600202238943233,1248600283772018689,1248600355872104450,1248600427909275650,1248600494862950401,1248600682826489857,1248600804914290689,1248600878276861954,1248600951983366146,1248601107629793282,1248601225024167938,1248601425595785218,1248601539081068545,1248601690243784705,1248601853272186881,1248602046210170882,1248602140619759618,1248602243212435458,1248602310182887426,1248602395637637122,1248602471051223042,1248602697375866882,1248602849843011586,1248602945598971905,1248603142211166209]]', '{\"code\":\"BA20000\",\"message\":\"请求成功\",\"data\":\"保存成功\"}', NULL);
INSERT INTO `t_system_oper_log` VALUES (-1, '2020-04-10 21:32:48', NULL, NULL, 1, 0, 1, NULL, 58, 'Administrator', '系统菜单', '菜单权限新增', 1, 2, 'com.hb0730.boot.admin.project.system.menu.controller.SystemMenuController.savePermissionByMenuId()', 'POST', '/api/v1/menu/permission/save/1243026675188297730', '127.0.0.1', NULL, '[{\"description\":\"\",\"name\":\"菜单权限查询\",\"enname\":\"menuPermissionQuery\",\"mark\":\"menu:permission:query\",\"sort\":0,\"startRow\":1,\"pageSize\":10,\"isEnabled\":1}]', '{\"code\":\"BA20000\",\"message\":\"请求成功\",\"data\":\"新增成功\"}', NULL);
INSERT INTO `t_system_oper_log` VALUES (-1, '2020-04-10 21:46:51', NULL, NULL, 1, 0, 1, NULL, 59, 'Administrator', '系统菜单', '菜单权限新增', 1, 2, 'com.hb0730.boot.admin.project.system.menu.controller.SystemMenuController.savePermissionByMenuId()', 'POST', '/api/v1/menu/permission/save/1244469536593256449', '127.0.0.1', NULL, '[{\"description\":\"\",\"name\":\"字典项新增\",\"enname\":\"dictDataSave\",\"mark\":\"dict:data:save\",\"sort\":0,\"startRow\":1,\"pageSize\":10,\"isEnabled\":1}]', '{\"code\":\"BA20000\",\"message\":\"请求成功\",\"data\":\"新增成功\"}', NULL);
INSERT INTO `t_system_oper_log` VALUES (-1, '2020-04-10 22:01:59', NULL, NULL, 1, 0, 1, NULL, 60, 'Administrator', '系统角色', '角色权限更新', 1, 2, 'com.hb0730.boot.admin.project.system.role.controller.SystemRoleController.savePermissionByRoleId()', 'POST', '/api/v1/role/permission/save/-1', '127.0.0.1', NULL, '[[1245178069475368962,1248597123867271169,1248596245210914817,1248596972893298689,1248596155138236417,1248596752621035521,1248597297092026370,1248604844628828161,1248597748256530433,1248597810781020161,1248597655650492418,1248597441329946625,1248602243212435458,1248602310182887426,1248602849843011586,1248602697375866882,1248602140619759618,1248602471051223042,1248602395637637122,1248602046210170882,1248603142211166209,1248602945598971905,1248599824055328769,1248599486774566914,1248599751112187906,1248599396081131522,1248599572795547650,1248599981664690177,1248599912173461506,1248601539081068545,1248601107629793282,1248601225024167938,1248601425595785218,1248599203227033602,1248599133643530242,1248598905154625538,1248599023673073665,1248608382771716098,1248601690243784705,1248601853272186881,1248598049311088641,1248598648865873921,1248598141019545601,1248598212192690178,1248597984873996289,1248598336591552513,1248598538190774274,1248598754298093569,1248600682826489857,1248600951983366146,1248600804914290689,1248600878276861954,1248600494862950401,1248600427909275650,1248600355872104450,1248600202238943233,1248600283772018689,1248600113541996545]]', '{\"code\":\"BA20000\",\"message\":\"请求成功\",\"data\":\"保存成功\"}', NULL);

-- ----------------------------
-- Table structure for t_system_org
-- ----------------------------
DROP TABLE IF EXISTS `t_system_org`;
CREATE TABLE `t_system_org`  (
  `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_enabled` int(2) NULL DEFAULT NULL COMMENT '是否启用',
  `del_flag` int(11) NULL DEFAULT NULL COMMENT '是否删除',
  `version` int(11) NULL DEFAULT NULL COMMENT '版本',
  `description` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `id` bigint(20) NOT NULL COMMENT 'id',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父id',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
  `enname` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '英文名称',
  `number` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '编码',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `ancestors` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '祖级列表',
  `leader` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '负责人',
  `phone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系方式',
  `email` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系邮箱',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统组织 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_system_org
-- ----------------------------
INSERT INTO `t_system_org` VALUES (-1, '2020-03-27 09:43:39', NULL, NULL, 1, 0, 1, '', 1, -1, '系统集团', '', 'T0001', 0, '-1', '系统集团', '', '');

-- ----------------------------
-- Table structure for t_system_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_system_permission`;
CREATE TABLE `t_system_permission`  (
  `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_enabled` int(2) NULL DEFAULT NULL COMMENT '是否启用',
  `del_flag` int(11) NULL DEFAULT NULL COMMENT '是否删除',
  `version` int(11) NULL DEFAULT NULL COMMENT '版本',
  `description` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `id` bigint(20) NOT NULL COMMENT 'id',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限名称',
  `enname` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限英文名称',
  `mark` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限标识(路径)',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统权限 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_system_permission
-- ----------------------------
INSERT INTO `t_system_permission` VALUES (-1, '2020-04-01 10:36:01', -1, '2020-04-01 10:36:42', 1, 0, 2, '', 1245178069475368962, '菜单查询', 'menuQuery', 'menu:query', 0);
INSERT INTO `t_system_permission` VALUES (-1, '2020-04-10 20:58:16', NULL, NULL, 1, 0, 1, '', 1248596155138236417, '菜单新增', 'menuSave', 'menu:save', 0);
INSERT INTO `t_system_permission` VALUES (-1, '2020-04-10 20:58:37', NULL, NULL, 1, 0, 1, '', 1248596245210914817, '菜单修改', 'menuUpdate', 'menu:update', 0);
INSERT INTO `t_system_permission` VALUES (-1, '2020-04-10 21:00:38', NULL, NULL, 1, 0, 1, '', 1248596752621035521, '菜单删除', 'menuDelete', 'menu:delete', 0);
INSERT INTO `t_system_permission` VALUES (-1, '2020-04-10 21:01:31', NULL, NULL, 1, 0, 1, '', 1248596972893298689, '菜单权限新增', 'menuPermissionSave', 'menu:permission:save', 0);
INSERT INTO `t_system_permission` VALUES (-1, '2020-04-10 21:02:07', NULL, NULL, 1, 0, 1, '', 1248597123867271169, '菜单权限修改', 'menuPermissionUpdate', 'menu:permission:update', 0);
INSERT INTO `t_system_permission` VALUES (-1, '2020-04-10 21:02:48', NULL, NULL, 1, 0, 1, '', 1248597297092026370, '菜单权限删除', 'menuPermissionDelete', 'menu:permission:delete', 0);
INSERT INTO `t_system_permission` VALUES (-1, '2020-04-10 21:03:22', NULL, NULL, 1, 0, 1, '', 1248597441329946625, '组织查询', 'orgQuery', 'org:query', 0);
INSERT INTO `t_system_permission` VALUES (-1, '2020-04-10 21:04:14', NULL, NULL, 1, 0, 1, '', 1248597655650492418, '组织新增', 'orgSave', 'org:save', 0);
INSERT INTO `t_system_permission` VALUES (-1, '2020-04-10 21:04:36', NULL, NULL, 1, 0, 1, '', 1248597748256530433, '组织修改', 'orgUpdate', 'org:update', 0);
INSERT INTO `t_system_permission` VALUES (-1, '2020-04-10 21:04:51', NULL, NULL, 1, 0, 1, '', 1248597810781020161, '组织删除', 'orgDelete', 'org:delete', 0);
INSERT INTO `t_system_permission` VALUES (-1, '2020-04-10 21:05:32', NULL, NULL, 1, 0, 1, '', 1248597984873996289, '角色查询', 'roleQuery', 'role:query', 0);
INSERT INTO `t_system_permission` VALUES (-1, '2020-04-10 21:05:47', NULL, NULL, 1, 0, 1, '', 1248598049311088641, '角色新增', 'roleSave', 'role:save', 0);
INSERT INTO `t_system_permission` VALUES (-1, '2020-04-10 21:06:09', NULL, NULL, 1, 0, 1, '', 1248598141019545601, '角色修改', 'roleUpdate', 'role:update', 0);
INSERT INTO `t_system_permission` VALUES (-1, '2020-04-10 21:06:26', NULL, NULL, 1, 0, 1, '', 1248598212192690178, '角色删除', 'roleDelete', 'role:delete', 0);
INSERT INTO `t_system_permission` VALUES (-1, '2020-04-10 21:06:56', NULL, NULL, 1, 0, 1, '', 1248598336591552513, '角色权限保存', 'rolePermissionSave', 'role:permission:save', 0);
INSERT INTO `t_system_permission` VALUES (-1, '2020-04-10 21:07:44', NULL, NULL, 1, 0, 1, '', 1248598538190774274, '角色权限查询', 'rolePermissionQuery', 'role:permission:query', 0);
INSERT INTO `t_system_permission` VALUES (-1, '2020-04-10 21:08:10', NULL, NULL, 1, 0, 1, '', 1248598648865873921, '角色组织查询', 'roleOrgQuery', 'role:org:query', 0);
INSERT INTO `t_system_permission` VALUES (-1, '2020-04-10 21:08:36', NULL, NULL, 1, 0, 1, '', 1248598754298093569, '角色组织修改', 'roleOrgUpdate', 'role:org:update', 0);
INSERT INTO `t_system_permission` VALUES (-1, '2020-04-10 21:09:11', NULL, NULL, 1, 0, 1, '', 1248598905154625538, '字典查询', 'dictQuery', 'dict:query', 0);
INSERT INTO `t_system_permission` VALUES (-1, '2020-04-10 21:09:40', NULL, NULL, 1, 0, 1, '', 1248599023673073665, '字典新增', 'dictSave', 'dict:save', 0);
INSERT INTO `t_system_permission` VALUES (-1, '2020-04-10 21:10:06', NULL, NULL, 1, 0, 1, '', 1248599133643530242, '字典修改', 'dictUpdate', 'dict:update', 0);
INSERT INTO `t_system_permission` VALUES (-1, '2020-04-10 21:10:23', NULL, NULL, 1, 0, 1, '', 1248599203227033602, '字典删除', 'dictDelete', 'dict:delete', 0);
INSERT INTO `t_system_permission` VALUES (-1, '2020-04-10 21:11:09', NULL, NULL, 1, 0, 1, '', 1248599396081131522, '用户查询', 'userQuery', 'user:query', 0);
INSERT INTO `t_system_permission` VALUES (-1, '2020-04-10 21:11:30', NULL, NULL, 1, 0, 1, '', 1248599486774566914, '用户新增', 'userSave', 'user:save', 0);
INSERT INTO `t_system_permission` VALUES (-1, '2020-04-10 21:11:51', NULL, NULL, 1, 0, 1, '', 1248599572795547650, '用户修改', 'userUpdate', 'user:update', 0);
INSERT INTO `t_system_permission` VALUES (-1, '2020-04-10 21:12:33', NULL, NULL, 1, 0, 1, '', 1248599751112187906, '用户密码重置', 'userRestPassword', 'user:rest:password', 0);
INSERT INTO `t_system_permission` VALUES (-1, '2020-04-10 21:12:51', NULL, NULL, 1, 0, 1, '', 1248599824055328769, '用户删除', 'userDelete', 'user:delete', 0);
INSERT INTO `t_system_permission` VALUES (-1, '2020-04-10 21:13:12', NULL, NULL, 1, 0, 1, '', 1248599912173461506, '用户导出', 'userExport', 'user:export', 0);
INSERT INTO `t_system_permission` VALUES (-1, '2020-04-10 21:13:28', NULL, NULL, 1, 0, 1, '', 1248599981664690177, '用户导入', 'userUpload', 'user:upload', 0);
INSERT INTO `t_system_permission` VALUES (-1, '2020-04-10 21:14:00', NULL, NULL, 1, 0, 1, '', 1248600113541996545, '岗位查询', 'postQuery', 'post:query', 0);
INSERT INTO `t_system_permission` VALUES (-1, '2020-04-10 21:14:21', NULL, NULL, 1, 0, 1, '', 1248600202238943233, '岗位新增', 'postSave', 'post:save', 0);
INSERT INTO `t_system_permission` VALUES (-1, '2020-04-10 21:14:40', NULL, NULL, 1, 0, 1, '', 1248600283772018689, '岗位修改', 'postUpdate', 'post:update', 0);
INSERT INTO `t_system_permission` VALUES (-1, '2020-04-10 21:14:57', NULL, NULL, 1, 0, 1, '', 1248600355872104450, '岗位删除', 'postDelete', 'post:delete', 0);
INSERT INTO `t_system_permission` VALUES (-1, '2020-04-10 21:15:15', NULL, NULL, 1, 0, 1, '', 1248600427909275650, '岗位导出', 'postExport', 'post:export', 0);
INSERT INTO `t_system_permission` VALUES (-1, '2020-04-10 21:15:31', NULL, NULL, 1, 0, 1, '', 1248600494862950401, '岗位导入', 'postUpload', 'post:upload', 0);
INSERT INTO `t_system_permission` VALUES (-1, '2020-04-10 21:16:15', NULL, NULL, 1, 0, 1, '', 1248600682826489857, '登录日志查询', 'loginLogQuery', 'login:log:query', 0);
INSERT INTO `t_system_permission` VALUES (-1, '2020-04-10 21:16:44', NULL, NULL, 1, 0, 1, '', 1248600804914290689, '登录日志导出', 'loginLogExport', 'login:log:export', 0);
INSERT INTO `t_system_permission` VALUES (-1, '2020-04-10 21:17:02', NULL, NULL, 1, 0, 1, '', 1248600878276861954, '登录日志删除', 'loginLogDelete', 'login:log:delete', 0);
INSERT INTO `t_system_permission` VALUES (-1, '2020-04-10 21:17:19', NULL, NULL, 1, 0, 1, '', 1248600951983366146, '登录日志清除', 'loginLogClean', 'login:log:clean', 0);
INSERT INTO `t_system_permission` VALUES (-1, '2020-04-10 21:17:57', NULL, NULL, 1, 0, 1, '', 1248601107629793282, '操作日志查询', 'operLogQuery', 'oper:log:query', 0);
INSERT INTO `t_system_permission` VALUES (-1, '2020-04-10 21:18:25', NULL, NULL, 1, 0, 1, '', 1248601225024167938, '操作日志删除', 'operLogDelete', 'oper:log:delete', 0);
INSERT INTO `t_system_permission` VALUES (-1, '2020-04-10 21:19:12', NULL, NULL, 1, 0, 1, '', 1248601425595785218, '操作日志清除', 'operLogClean', 'oper:log:clean', 0);
INSERT INTO `t_system_permission` VALUES (-1, '2020-04-10 21:19:39', NULL, NULL, 1, 0, 1, '', 1248601539081068545, '操作日志导出', 'operLogExport', 'oper:log:export', 0);
INSERT INTO `t_system_permission` VALUES (-1, '2020-04-10 21:20:16', NULL, NULL, 1, 0, 1, '', 1248601690243784705, '在线用户查询', 'userOnlineQuery', 'user:online:query', 0);
INSERT INTO `t_system_permission` VALUES (-1, '2020-04-10 21:20:54', NULL, NULL, 1, 0, 1, '', 1248601853272186881, '在线用户强退', 'userOnlineLogout', 'user:online:logout', 0);
INSERT INTO `t_system_permission` VALUES (-1, '2020-04-10 21:21:40', NULL, NULL, 1, 0, 1, '', 1248602046210170882, '定时任务查询', 'jobQuery', 'job:query', 0);
INSERT INTO `t_system_permission` VALUES (-1, '2020-04-10 21:22:03', NULL, NULL, 1, 0, 1, '', 1248602140619759618, '定时任务新增', 'jobSave', 'job:save', 0);
INSERT INTO `t_system_permission` VALUES (-1, '2020-04-10 21:22:27', NULL, NULL, 1, 0, 1, '', 1248602243212435458, '定时任务修改', 'jobUpdate', 'job:update', 0);
INSERT INTO `t_system_permission` VALUES (-1, '2020-04-10 21:22:43', NULL, NULL, 1, 0, 1, '', 1248602310182887426, '定时任务删除', 'jobDelete', 'job:delete', 0);
INSERT INTO `t_system_permission` VALUES (-1, '2020-04-10 21:23:04', NULL, NULL, 1, 0, 1, '', 1248602395637637122, '定时任务导出', 'jobExport', 'job:export', 0);
INSERT INTO `t_system_permission` VALUES (-1, '2020-04-10 21:23:22', NULL, NULL, 1, 0, 1, '', 1248602471051223042, '定时任务导入', 'jobUpload', 'job:upload', 0);
INSERT INTO `t_system_permission` VALUES (-1, '2020-04-10 21:24:16', NULL, NULL, 1, 0, 1, '', 1248602697375866882, '调度日志查询', 'jobLogQuery', 'job:log:query', 0);
INSERT INTO `t_system_permission` VALUES (-1, '2020-04-10 21:24:52', NULL, NULL, 1, 0, 1, '', 1248602849843011586, '调度日志导出', 'jobLogExport', 'job:log:export', 0);
INSERT INTO `t_system_permission` VALUES (-1, '2020-04-10 21:25:15', NULL, NULL, 1, 0, 1, '', 1248602945598971905, '调度日志删除', 'jobLogDelete', 'jog:log:delete', 0);
INSERT INTO `t_system_permission` VALUES (-1, '2020-04-10 21:26:02', NULL, NULL, 1, 0, 1, '', 1248603142211166209, '调度日志清除', 'jobLogClean', 'job:log:clean', 0);
INSERT INTO `t_system_permission` VALUES (-1, '2020-04-10 21:32:48', NULL, NULL, 1, 0, 1, '', 1248604844628828161, '菜单权限查询', 'menuPermissionQuery', 'menu:permission:query', 0);
INSERT INTO `t_system_permission` VALUES (-1, '2020-04-10 21:46:51', NULL, NULL, 1, 0, 1, '', 1248608382771716098, '字典项新增', 'dictDataSave', 'dict:data:save', 0);

-- ----------------------------
-- Table structure for t_system_post
-- ----------------------------
DROP TABLE IF EXISTS `t_system_post`;
CREATE TABLE `t_system_post`  (
  `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_enabled` int(2) NULL DEFAULT NULL COMMENT '是否启用',
  `del_flag` int(11) NULL DEFAULT NULL COMMENT '是否删除',
  `version` int(11) NULL DEFAULT NULL COMMENT '版本',
  `description` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `id` bigint(20) NOT NULL COMMENT 'id',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '岗位名称',
  `enname` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '岗位英文名称',
  `number` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '岗位编码',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统岗位 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_system_role
-- ----------------------------
DROP TABLE IF EXISTS `t_system_role`;
CREATE TABLE `t_system_role`  (
  `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_enabled` int(2) NULL DEFAULT NULL COMMENT '是否启用',
  `del_flag` int(11) NULL DEFAULT NULL COMMENT '是否删除',
  `version` int(11) NULL DEFAULT NULL COMMENT '版本',
  `description` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `id` bigint(20) NOT NULL COMMENT 'id',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
  `enname` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '英文名称',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统角色 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_system_role
-- ----------------------------
INSERT INTO `t_system_role` VALUES (-1, '2020-03-27 14:10:50', NULL, NULL, 1, 0, 1, '', -1, '超级管理员', 'Administrator', 0);

-- ----------------------------
-- Table structure for t_system_role_org
-- ----------------------------
DROP TABLE IF EXISTS `t_system_role_org`;
CREATE TABLE `t_system_role_org`  (
  `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_enabled` int(2) NULL DEFAULT NULL COMMENT '是否启用',
  `del_flag` int(11) NULL DEFAULT NULL COMMENT '是否删除',
  `version` int(11) NULL DEFAULT NULL COMMENT '版本',
  `description` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `id` bigint(20) NOT NULL COMMENT 'id',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色id',
  `org_id` bigint(20) NULL DEFAULT NULL COMMENT '组织id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色组织(数据范围) ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_system_role_org
-- ----------------------------
INSERT INTO `t_system_role_org` VALUES (-1, '2020-04-10 21:26:22', NULL, NULL, 1, 0, 1, NULL, 1248603229419134978, -1, 1);

-- ----------------------------
-- Table structure for t_system_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_system_role_permission`;
CREATE TABLE `t_system_role_permission`  (
  `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_enabled` int(2) NULL DEFAULT NULL COMMENT '是否启用',
  `del_flag` int(11) NULL DEFAULT NULL COMMENT '是否删除',
  `version` int(11) NULL DEFAULT NULL COMMENT '版本',
  `description` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `id` bigint(20) NOT NULL COMMENT 'id',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色id',
  `permission_id` bigint(20) NULL DEFAULT NULL COMMENT '权限Id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色权限 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_system_role_permission
-- ----------------------------
INSERT INTO `t_system_role_permission` VALUES (-1, '2020-04-01 10:51:13', -1, '2020-04-01 10:51:45', 1, 0, 11, NULL, 1245181896425447425, -1, 1245178069475368962);
INSERT INTO `t_system_role_permission` VALUES (-1, '2020-04-10 21:26:55', -1, '2020-04-10 22:01:58', 1, 0, 2, NULL, 1248603364769325057, -1, 1248596155138236417);
INSERT INTO `t_system_role_permission` VALUES (-1, '2020-04-10 21:26:55', -1, '2020-04-10 22:01:58', 1, 0, 2, NULL, 1248603364777713666, -1, 1248596245210914817);
INSERT INTO `t_system_role_permission` VALUES (-1, '2020-04-10 21:26:55', -1, '2020-04-10 22:01:58', 1, 0, 2, NULL, 1248603364777713667, -1, 1248596752621035521);
INSERT INTO `t_system_role_permission` VALUES (-1, '2020-04-10 21:26:55', -1, '2020-04-10 22:01:58', 1, 0, 2, NULL, 1248603364777713668, -1, 1248596972893298689);
INSERT INTO `t_system_role_permission` VALUES (-1, '2020-04-10 21:26:55', -1, '2020-04-10 22:01:58', 1, 0, 2, NULL, 1248603364777713669, -1, 1248597123867271169);
INSERT INTO `t_system_role_permission` VALUES (-1, '2020-04-10 21:26:55', -1, '2020-04-10 22:01:58', 1, 0, 2, NULL, 1248603364777713670, -1, 1248597297092026370);
INSERT INTO `t_system_role_permission` VALUES (-1, '2020-04-10 21:26:55', -1, '2020-04-10 22:01:58', 1, 0, 2, NULL, 1248603364777713671, -1, 1248597441329946625);
INSERT INTO `t_system_role_permission` VALUES (-1, '2020-04-10 21:26:55', -1, '2020-04-10 22:01:58', 1, 0, 2, NULL, 1248603364777713672, -1, 1248597655650492418);
INSERT INTO `t_system_role_permission` VALUES (-1, '2020-04-10 21:26:55', -1, '2020-04-10 22:01:58', 1, 0, 2, NULL, 1248603364786102274, -1, 1248597748256530433);
INSERT INTO `t_system_role_permission` VALUES (-1, '2020-04-10 21:26:55', -1, '2020-04-10 22:01:58', 1, 0, 2, NULL, 1248603364786102275, -1, 1248597810781020161);
INSERT INTO `t_system_role_permission` VALUES (-1, '2020-04-10 21:26:55', -1, '2020-04-10 22:01:58', 1, 0, 2, NULL, 1248603364786102276, -1, 1248597984873996289);
INSERT INTO `t_system_role_permission` VALUES (-1, '2020-04-10 21:26:55', -1, '2020-04-10 22:01:58', 1, 0, 2, NULL, 1248603364786102277, -1, 1248598049311088641);
INSERT INTO `t_system_role_permission` VALUES (-1, '2020-04-10 21:26:55', -1, '2020-04-10 22:01:58', 1, 0, 2, NULL, 1248603364786102278, -1, 1248598141019545601);
INSERT INTO `t_system_role_permission` VALUES (-1, '2020-04-10 21:26:55', -1, '2020-04-10 22:01:58', 1, 0, 2, NULL, 1248603364786102279, -1, 1248598212192690178);
INSERT INTO `t_system_role_permission` VALUES (-1, '2020-04-10 21:26:55', -1, '2020-04-10 22:01:58', 1, 0, 2, NULL, 1248603364786102280, -1, 1248598336591552513);
INSERT INTO `t_system_role_permission` VALUES (-1, '2020-04-10 21:26:55', -1, '2020-04-10 22:01:58', 1, 0, 2, NULL, 1248603364794490882, -1, 1248598538190774274);
INSERT INTO `t_system_role_permission` VALUES (-1, '2020-04-10 21:26:55', -1, '2020-04-10 22:01:58', 1, 0, 2, NULL, 1248603364794490883, -1, 1248598648865873921);
INSERT INTO `t_system_role_permission` VALUES (-1, '2020-04-10 21:26:55', -1, '2020-04-10 22:01:58', 1, 0, 2, NULL, 1248603364794490884, -1, 1248598754298093569);
INSERT INTO `t_system_role_permission` VALUES (-1, '2020-04-10 21:26:55', -1, '2020-04-10 22:01:58', 1, 0, 2, NULL, 1248603364794490885, -1, 1248598905154625538);
INSERT INTO `t_system_role_permission` VALUES (-1, '2020-04-10 21:26:55', -1, '2020-04-10 22:01:58', 1, 0, 2, NULL, 1248603364802879489, -1, 1248599023673073665);
INSERT INTO `t_system_role_permission` VALUES (-1, '2020-04-10 21:26:55', -1, '2020-04-10 22:01:58', 1, 0, 2, NULL, 1248603364802879490, -1, 1248599133643530242);
INSERT INTO `t_system_role_permission` VALUES (-1, '2020-04-10 21:26:55', -1, '2020-04-10 22:01:58', 1, 0, 2, NULL, 1248603364802879491, -1, 1248599203227033602);
INSERT INTO `t_system_role_permission` VALUES (-1, '2020-04-10 21:26:55', -1, '2020-04-10 22:01:58', 1, 0, 2, NULL, 1248603364802879492, -1, 1248599396081131522);
INSERT INTO `t_system_role_permission` VALUES (-1, '2020-04-10 21:26:55', -1, '2020-04-10 22:01:58', 1, 0, 2, NULL, 1248603364802879493, -1, 1248599486774566914);
INSERT INTO `t_system_role_permission` VALUES (-1, '2020-04-10 21:26:55', -1, '2020-04-10 22:01:58', 1, 0, 2, NULL, 1248603364802879494, -1, 1248599572795547650);
INSERT INTO `t_system_role_permission` VALUES (-1, '2020-04-10 21:26:55', -1, '2020-04-10 22:01:58', 1, 0, 2, NULL, 1248603364802879495, -1, 1248599751112187906);
INSERT INTO `t_system_role_permission` VALUES (-1, '2020-04-10 21:26:55', -1, '2020-04-10 22:01:58', 1, 0, 2, NULL, 1248603364802879496, -1, 1248599824055328769);
INSERT INTO `t_system_role_permission` VALUES (-1, '2020-04-10 21:26:55', -1, '2020-04-10 22:01:58', 1, 0, 2, NULL, 1248603364802879497, -1, 1248599912173461506);
INSERT INTO `t_system_role_permission` VALUES (-1, '2020-04-10 21:26:55', -1, '2020-04-10 22:01:58', 1, 0, 2, NULL, 1248603364802879498, -1, 1248599981664690177);
INSERT INTO `t_system_role_permission` VALUES (-1, '2020-04-10 21:26:55', -1, '2020-04-10 22:01:58', 1, 0, 2, NULL, 1248603364802879499, -1, 1248600113541996545);
INSERT INTO `t_system_role_permission` VALUES (-1, '2020-04-10 21:26:55', -1, '2020-04-10 22:01:58', 1, 0, 2, NULL, 1248603364802879500, -1, 1248600202238943233);
INSERT INTO `t_system_role_permission` VALUES (-1, '2020-04-10 21:26:55', -1, '2020-04-10 22:01:58', 1, 0, 2, NULL, 1248603364802879501, -1, 1248600283772018689);
INSERT INTO `t_system_role_permission` VALUES (-1, '2020-04-10 21:26:55', -1, '2020-04-10 22:01:58', 1, 0, 2, NULL, 1248603364802879502, -1, 1248600355872104450);
INSERT INTO `t_system_role_permission` VALUES (-1, '2020-04-10 21:26:55', -1, '2020-04-10 22:01:58', 1, 0, 2, NULL, 1248603364802879503, -1, 1248600427909275650);
INSERT INTO `t_system_role_permission` VALUES (-1, '2020-04-10 21:26:55', -1, '2020-04-10 22:01:58', 1, 0, 2, NULL, 1248603364811268098, -1, 1248600494862950401);
INSERT INTO `t_system_role_permission` VALUES (-1, '2020-04-10 21:26:55', -1, '2020-04-10 22:01:58', 1, 0, 2, NULL, 1248603364811268099, -1, 1248600682826489857);
INSERT INTO `t_system_role_permission` VALUES (-1, '2020-04-10 21:26:55', -1, '2020-04-10 22:01:58', 1, 0, 2, NULL, 1248603364811268100, -1, 1248600804914290689);
INSERT INTO `t_system_role_permission` VALUES (-1, '2020-04-10 21:26:55', -1, '2020-04-10 22:01:58', 1, 0, 2, NULL, 1248603364811268101, -1, 1248600878276861954);
INSERT INTO `t_system_role_permission` VALUES (-1, '2020-04-10 21:26:55', -1, '2020-04-10 22:01:58', 1, 0, 2, NULL, 1248603364811268102, -1, 1248600951983366146);
INSERT INTO `t_system_role_permission` VALUES (-1, '2020-04-10 21:26:55', -1, '2020-04-10 22:01:58', 1, 0, 2, NULL, 1248603364811268103, -1, 1248601107629793282);
INSERT INTO `t_system_role_permission` VALUES (-1, '2020-04-10 21:26:55', -1, '2020-04-10 22:01:58', 1, 0, 2, NULL, 1248603364811268104, -1, 1248601225024167938);
INSERT INTO `t_system_role_permission` VALUES (-1, '2020-04-10 21:26:55', -1, '2020-04-10 22:01:58', 1, 0, 2, NULL, 1248603364811268105, -1, 1248601425595785218);
INSERT INTO `t_system_role_permission` VALUES (-1, '2020-04-10 21:26:55', -1, '2020-04-10 22:01:58', 1, 0, 2, NULL, 1248603364811268106, -1, 1248601539081068545);
INSERT INTO `t_system_role_permission` VALUES (-1, '2020-04-10 21:26:55', -1, '2020-04-10 22:01:58', 1, 0, 2, NULL, 1248603364811268107, -1, 1248601690243784705);
INSERT INTO `t_system_role_permission` VALUES (-1, '2020-04-10 21:26:55', -1, '2020-04-10 22:01:58', 1, 0, 2, NULL, 1248603364811268108, -1, 1248601853272186881);
INSERT INTO `t_system_role_permission` VALUES (-1, '2020-04-10 21:26:55', -1, '2020-04-10 22:01:58', 1, 0, 2, NULL, 1248603364811268109, -1, 1248602046210170882);
INSERT INTO `t_system_role_permission` VALUES (-1, '2020-04-10 21:26:55', -1, '2020-04-10 22:01:58', 1, 0, 2, NULL, 1248603364811268110, -1, 1248602140619759618);
INSERT INTO `t_system_role_permission` VALUES (-1, '2020-04-10 21:26:55', -1, '2020-04-10 22:01:58', 1, 0, 2, NULL, 1248603364811268111, -1, 1248602243212435458);
INSERT INTO `t_system_role_permission` VALUES (-1, '2020-04-10 21:26:55', -1, '2020-04-10 22:01:58', 1, 0, 2, NULL, 1248603364811268112, -1, 1248602310182887426);
INSERT INTO `t_system_role_permission` VALUES (-1, '2020-04-10 21:26:55', -1, '2020-04-10 22:01:58', 1, 0, 2, NULL, 1248603364819656706, -1, 1248602395637637122);
INSERT INTO `t_system_role_permission` VALUES (-1, '2020-04-10 21:26:55', -1, '2020-04-10 22:01:58', 1, 0, 2, NULL, 1248603364819656707, -1, 1248602471051223042);
INSERT INTO `t_system_role_permission` VALUES (-1, '2020-04-10 21:26:55', -1, '2020-04-10 22:01:58', 1, 0, 2, NULL, 1248603364819656708, -1, 1248602697375866882);
INSERT INTO `t_system_role_permission` VALUES (-1, '2020-04-10 21:26:55', -1, '2020-04-10 22:01:58', 1, 0, 2, NULL, 1248603364819656709, -1, 1248602849843011586);
INSERT INTO `t_system_role_permission` VALUES (-1, '2020-04-10 21:26:55', -1, '2020-04-10 22:01:58', 1, 0, 2, NULL, 1248603364819656710, -1, 1248602945598971905);
INSERT INTO `t_system_role_permission` VALUES (-1, '2020-04-10 21:26:55', -1, '2020-04-10 22:01:58', 1, 0, 2, NULL, 1248603364819656711, -1, 1248603142211166209);
INSERT INTO `t_system_role_permission` VALUES (-1, '2020-04-10 22:01:59', NULL, NULL, 1, 0, 1, NULL, 1248612189442334721, -1, 1248604844628828161);
INSERT INTO `t_system_role_permission` VALUES (-1, '2020-04-10 22:01:59', NULL, NULL, 1, 0, 1, NULL, 1248612189450723330, -1, 1248608382771716098);

-- ----------------------------
-- Table structure for t_system_user
-- ----------------------------
DROP TABLE IF EXISTS `t_system_user`;
CREATE TABLE `t_system_user`  (
  `version` int(11) NULL DEFAULT NULL COMMENT '乐观锁',
  `del_flag` int(11) NULL DEFAULT NULL COMMENT '是否删除',
  `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_enabled` int(2) NULL DEFAULT NULL COMMENT '是否启用',
  `id` bigint(20) NOT NULL COMMENT 'id',
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户账号',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户密码',
  `nick_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `email` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户邮箱',
  `phonenumber` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号码',
  `sex` int(2) NULL DEFAULT NULL COMMENT '用户性别',
  `avatar` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户头像',
  `dept_Id` bigint(20) NULL DEFAULT NULL COMMENT '部门ID',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统用户 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_system_user
-- ----------------------------
INSERT INTO `t_system_user` VALUES (0, 0, -1, '2020-03-29 15:09:06', -1, '2020-03-29 15:39:22', 1, -1, 'Administrator', '$2a$10$86Oxf5477aSRmeSM1YQz2eVAU.c1G5sc2Iq215I6/5hBJXfBZCIgW', '超级管理员', NULL, NULL, 2, '', 1, 0);

-- ----------------------------
-- Table structure for t_system_user_post
-- ----------------------------
DROP TABLE IF EXISTS `t_system_user_post`;
CREATE TABLE `t_system_user_post`  (
  `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_enabled` int(2) NULL DEFAULT NULL COMMENT '是否启用',
  `del_flag` int(11) NULL DEFAULT NULL COMMENT '是否删除',
  `version` int(11) NULL DEFAULT NULL COMMENT '版本',
  `description` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `id` bigint(20) NOT NULL COMMENT 'id',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `post_id` bigint(20) NULL DEFAULT NULL COMMENT '岗位id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户岗位 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_system_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_system_user_role`;
CREATE TABLE `t_system_user_role`  (
  `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_enabled` int(2) NULL DEFAULT NULL COMMENT '是否启用',
  `del_flag` int(11) NULL DEFAULT NULL COMMENT '是否删除',
  `version` int(11) NULL DEFAULT NULL COMMENT '版本',
  `description` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `id` bigint(20) NOT NULL COMMENT 'id',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户角色 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_system_user_role
-- ----------------------------
INSERT INTO `t_system_user_role` VALUES (-1, '2020-03-29 15:09:06', NULL, NULL, 1, 0, 1, NULL, 1244159630841376769, -1, -1);

SET FOREIGN_KEY_CHECKS = 1;
