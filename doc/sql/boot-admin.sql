/*
 Navicat Premium Data Transfer

 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Schema         : boot-admin

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 01/04/2020 10:55:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for pdman_db_version
-- ----------------------------
DROP TABLE IF EXISTS `pdman_db_version`;
CREATE TABLE `pdman_db_version`  (
  `DB_VERSION` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `VERSION_DESC` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `CREATED_TIME` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pdman_db_version
-- ----------------------------
INSERT INTO `pdman_db_version` VALUES ('v0.0.0', '默认版本，新增的版本不能低于此版本', '2020-03-24 14:17:27');
INSERT INTO `pdman_db_version` VALUES ('v1.0.0', 'init user', '2020-03-24 14:17:33');
INSERT INTO `pdman_db_version` VALUES ('v1.0.1', 'add 用户密码', '2020-03-24 15:55:30');
INSERT INTO `pdman_db_version` VALUES ('v1.0.2', 'add rbac', '2020-03-26 09:05:35');
INSERT INTO `pdman_db_version` VALUES ('v1.0.3', 'add org', '2020-03-26 09:11:14');
INSERT INTO `pdman_db_version` VALUES ('v1.0.4', '删除多余的字段', '2020-03-26 09:22:20');
INSERT INTO `pdman_db_version` VALUES ('v1.0.5', 'rename 重命名', '2020-03-26 14:32:41');
INSERT INTO `pdman_db_version` VALUES ('v1.0.6', '删除多余字段', '2020-03-26 17:57:26');
INSERT INTO `pdman_db_version` VALUES ('v1.0.7', 'add 岗位', '2020-03-28 14:55:40');
INSERT INTO `pdman_db_version` VALUES ('v1.0.8', 'update use dept id column', '2020-03-28 17:27:19');
INSERT INTO `pdman_db_version` VALUES ('v1.0.9', '用户岗位', '2020-03-29 14:05:25');
INSERT INTO `pdman_db_version` VALUES ('v1.0.10', 'add column', '2020-03-29 15:29:35');
INSERT INTO `pdman_db_version` VALUES ('v1.0.11', 'add 字典数据表', '2020-03-30 11:15:43');

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
INSERT INTO `t_system_dict` VALUES (1244159630182871041, '2020-03-30 15:36:06', 1244159630182871041, '2020-03-30 16:03:32', 1, 0, 10, '禁止删除', 1244528815203393538, '字典类型', '', 'dict_type', -1, '1', '', '', NULL, '', '0');
INSERT INTO `t_system_dict` VALUES (1244159630182871041, '2020-03-30 17:34:43', 1244159630182871041, '2020-03-31 08:25:39', 1, 0, 5, '禁止删除', 1244558665666502657, '', '', '', 1244528815203393538, 'dict_type', '1', '系统类', 1, '', '0');
INSERT INTO `t_system_dict` VALUES (1244159630182871041, '2020-03-31 08:30:04', 1244159630182871041, '2020-03-31 12:04:09', 1, 0, 2, '禁止删除', 1244783985472507905, '', '', '', 1244528815203393538, 'dict_type', '2', '业务类', 1, '', '1');
INSERT INTO `t_system_dict` VALUES (1244159630182871041, '2020-03-31 08:30:45', 1244159630182871041, '2020-03-31 08:30:50', 1, 0, 3, '禁止删除', 1244784157116010498, '', '', '', 1244528815203393538, 'dict_type', '3', '其他类', 1, '', '2');
INSERT INTO `t_system_dict` VALUES (1244159630182871041, '2020-03-31 12:59:37', NULL, NULL, 0, 1, 1, '', 1244851820513067010, 'test', '', '测试', -1, '2', '', '', NULL, '', '0');
INSERT INTO `t_system_dict` VALUES (1244159630182871041, '2020-03-31 12:59:52', NULL, NULL, 0, 1, 1, '', 1244851885948403714, '', '', '', 1244851820513067010, '测试', '1', '测试', 1, '', '0');
INSERT INTO `t_system_dict` VALUES (1244159630182871041, '2020-03-31 15:17:04', NULL, NULL, 1, 0, 1, '', 1244886412620115969, '系统常量', '', 'system_constant', -1, '1', '', '', NULL, '', '0');
INSERT INTO `t_system_dict` VALUES (1244159630182871041, '2020-03-31 15:17:50', NULL, NULL, 1, 0, 1, '启用', 1244886604421443586, '', '', '', 1244886412620115969, 'system_constant', '1', 'USE', 1, '', '0');
INSERT INTO `t_system_dict` VALUES (1244159630182871041, '2020-03-31 15:18:03', NULL, NULL, 1, 0, 1, '禁用', 1244886660201492481, '', '', '', 1244886412620115969, 'system_constant', '0', 'NOT_USE', 1, '', '0');
INSERT INTO `t_system_dict` VALUES (1244159630182871041, '2020-03-31 15:18:18', NULL, NULL, 1, 0, 1, '是否全部', 1244886723724226562, '', '', '', 1244886412620115969, 'system_constant', '-1', 'IS_ALL', 1, '', '0');
INSERT INTO `t_system_dict` VALUES (1244159630182871041, '2020-03-31 15:18:34', NULL, NULL, 1, 0, 1, '修改', 1244886789922926593, '', '', '', 1244886412620115969, 'system_constant', '1', 'IS_UPDATE', 1, '', '0');
INSERT INTO `t_system_dict` VALUES (1244159630182871041, '2020-03-31 15:18:50', NULL, NULL, 1, 0, 1, '非修改', 1244886857069539329, '', '', '', 1244886412620115969, 'system_constant', '0', 'NO_UPDATE', 1, '', '0');
INSERT INTO `t_system_dict` VALUES (1244159630182871041, '2020-03-31 15:19:10', NULL, NULL, 1, 0, 1, '默认父id为-1', 1244886937805697026, '', '', '', 1244886412620115969, 'system_constant', '-1L', 'PARENT_ID', 1, '', '0');
INSERT INTO `t_system_dict` VALUES (1244159630182871041, '2020-03-31 15:21:23', NULL, NULL, 1, 0, 1, '', 1244887499175538690, '系统响应状态码', '', 'system_code_status', -1, '1', '', '', NULL, '', '0');
INSERT INTO `t_system_dict` VALUES (1244159630182871041, '2020-03-31 15:21:48', NULL, NULL, 1, 0, 1, '请求成功', 1244887604368683010, '', '', '', 1244887499175538690, 'system_code_status', 'BA20000', 'SUCCESS', 1, '', '0');
INSERT INTO `t_system_dict` VALUES (1244159630182871041, '2020-03-31 15:22:03', NULL, NULL, 1, 0, 1, '请求失败', 1244887665584549890, '', '', '', 1244887499175538690, 'system_code_status', 'BA20002', 'FAIL', 1, '', '0');
INSERT INTO `t_system_dict` VALUES (1244159630182871041, '2020-03-31 15:22:16', NULL, NULL, 1, 0, 1, '用户不存在', 1244887718126596097, '', '', '', 1244887499175538690, 'system_code_status', 'BA20004', 'USERNAME_NOTFOUND', 1, '', '0');
INSERT INTO `t_system_dict` VALUES (1244159630182871041, '2020-03-31 15:22:28', NULL, NULL, 1, 0, 1, '未授权', 1244887769871724545, '', '', '', 1244887499175538690, 'system_code_status', 'BA20005', 'UNAUTHORIZED', 1, '', '0');
INSERT INTO `t_system_dict` VALUES (1244159630182871041, '2020-03-31 15:22:40', NULL, NULL, 1, 0, 1, '授权异常', 1244887821000290305, '', '', '', 1244887499175538690, 'system_code_status', 'BA20006', 'FORBIDDEN', 1, '', '0');
INSERT INTO `t_system_dict` VALUES (1244159630182871041, '2020-03-31 15:22:52', NULL, NULL, 1, 0, 1, '未找到', 1244887871973666817, '', '', '', 1244887499175538690, 'system_code_status', 'BA20007', 'NOT_FOUND', 1, '', '0');
INSERT INTO `t_system_dict` VALUES (1244159630182871041, '2020-03-31 15:27:54', NULL, NULL, 1, 0, 1, '默认密码', 1244889138536034306, '', '', '', 1244886412620115969, 'system_constant', '123456', 'DEFAULT_PASSWORD', 1, '', '0');
INSERT INTO `t_system_dict` VALUES (1244159630182871041, '2020-03-31 15:38:00', NULL, NULL, 1, 0, 1, '', 1244891680452030466, '性别', '', 'gender', -1, '2', '', '', NULL, '', '0');
INSERT INTO `t_system_dict` VALUES (1244159630182871041, '2020-03-31 15:39:06', NULL, NULL, 1, 0, 1, '', 1244891955787116546, '', '', '', 1244891680452030466, 'gender', '1', '男', 1, '', '0');
INSERT INTO `t_system_dict` VALUES (1244159630182871041, '2020-03-31 15:39:13', NULL, NULL, 1, 0, 1, '', 1244891984266440705, '', '', '', 1244891680452030466, 'gender', '2', '女', 1, '', '0');
INSERT INTO `t_system_dict` VALUES (1244159630182871041, '2020-03-31 15:39:24', 1244159630182871041, '2020-03-31 15:40:58', 1, 0, 3, '', 1244892033499181058, '', '', '', 1244891680452030466, 'gender', '-1', '未知', 1, '', '0');

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
INSERT INTO `t_system_menu` VALUES (1242391265059778561, '2020-03-26 11:52:57', 1242391265059778561, '2020-03-26 12:07:08', 1, 0, 3, NULL, 1243023104032104449, '系统设置', 'systemManager', '/bootAdmin/systemManager', 'fa fa-gear', 0, -1, 1, 0, 1);
INSERT INTO `t_system_menu` VALUES (1242391265059778561, '2020-03-26 12:07:08', 1244159630182871041, '2020-03-30 11:02:58', 1, 0, 3, '', 1243026675188297730, '菜单管理', 'menuManager', '/bootAdmin/systemManager/menu', '', 0, 1243023104032104449, 0, 0, 2);
INSERT INTO `t_system_menu` VALUES (1242391265059778561, '2020-03-26 12:54:58', 1242391265059778561, '2020-03-26 12:56:10', 1, 0, 3, '', 1243038711322435586, '用户中心', 'userCentor', '/bootAdmin/userManager', 'fa fa-users', 1, -1, 1, 1, 1);
INSERT INTO `t_system_menu` VALUES (1242391265059778561, '2020-03-26 12:56:10', 1244159630182871041, '2020-03-30 11:02:36', 1, 0, 2, '', 1243039012540571649, '用户管理', 'userManager', '/bootAdmin/userManager/userList', '', 0, 1243038711322435586, 0, 0, 2);
INSERT INTO `t_system_menu` VALUES (1242391265059778561, '2020-03-26 13:29:46', NULL, NULL, 0, 1, 1, '', 1243047469293395970, '测试', 'test', 'http://localhost:8080', '', 0, 1243023104032104449, 0, 0, 2);
INSERT INTO `t_system_menu` VALUES (1242391265059778561, '2020-03-28 16:36:41', 1242391265059778561, '2020-03-29 15:41:59', 1, 0, 2, '', 1243819283653275650, '组织管理', 'orgManager', '/bootAdmin/systemManager/org', '', 0, 1243023104032104449, 0, 0, 2);
INSERT INTO `t_system_menu` VALUES (1244159630182871041, '2020-03-29 19:46:24', 1244159630182871041, '2020-03-29 19:46:57', 1, 0, 2, '', 1244229413641314306, '角色管理', 'roleManager', '/bootAdmin/systemManager/role', '', 0, 1243023104032104449, 0, 0, 2);
INSERT INTO `t_system_menu` VALUES (1244159630182871041, '2020-03-29 19:47:46', NULL, NULL, 1, 0, 1, '', 1244229759709143042, '岗位管理', 'postManager', '/bootAdmin/userManager/post', '', 0, 1243038711322435586, 0, 0, 2);
INSERT INTO `t_system_menu` VALUES (1244159630182871041, '2020-03-30 11:40:33', 1244159630182871041, '2020-03-30 11:40:43', 1, 0, 2, '', 1244469536593256449, '字典管理', 'dictManager', '/bootAdmin/systemManager/dict', '', 0, 1243023104032104449, 0, 0, 2);
INSERT INTO `t_system_menu` VALUES (1244159630182871041, '2020-03-31 15:12:18', NULL, NULL, 0, 1, 1, '', 1244885211392421890, '测试', '', 'test', '', 0, -1, 0, 1, 1);

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
INSERT INTO `t_system_menu_permission` VALUES (1242391265059778561, '2020-03-26 16:48:18', NULL, NULL, 1, 0, 1, NULL, 1243097433658863618, 1243026675188297730, 1243097433495285762);
INSERT INTO `t_system_menu_permission` VALUES (1242391265059778561, '2020-03-26 17:27:35', NULL, NULL, 1, 0, 1, NULL, 1243107318849028097, 1243026675188297730, 1243107318739976193);
INSERT INTO `t_system_menu_permission` VALUES (1242391265059778561, '2020-03-26 17:44:17', NULL, NULL, 1, 0, 1, NULL, 1243111519767412738, 1243026675188297730, 1243111519658360833);
INSERT INTO `t_system_menu_permission` VALUES (1242391265059778561, '2020-03-27 17:19:30', NULL, NULL, 1, 0, 1, NULL, 1243467669989961729, 1243026675188297730, 1243467669872521217);
INSERT INTO `t_system_menu_permission` VALUES (1242391265059778561, '2020-03-27 17:37:01', NULL, NULL, 1, 0, 1, NULL, 1243472078249603073, 1243039012540571649, 1243472078094413826);
INSERT INTO `t_system_menu_permission` VALUES (1242391265059778561, '2020-03-29 15:42:39', NULL, NULL, 1, 0, 1, NULL, 1244168071823732737, 1243819283653275650, 1244168071177809922);
INSERT INTO `t_system_menu_permission` VALUES (1244159630182871041, '2020-03-29 19:46:17', NULL, NULL, 1, 0, 1, NULL, 1244229385891799041, 1243023104032104449, 1244229385623363585);
INSERT INTO `t_system_menu_permission` VALUES (1244159630182871041, '2020-03-29 19:47:16', NULL, NULL, 1, 0, 1, NULL, 1244229633926160386, 1244229413641314306, 1244229633804525569);
INSERT INTO `t_system_menu_permission` VALUES (1244159630182871041, '2020-03-29 19:48:07', NULL, NULL, 1, 0, 1, NULL, 1244229849416916994, 1244229759709143042, 1244229849307865089);
INSERT INTO `t_system_menu_permission` VALUES (1244159630182871041, '2020-03-30 11:41:12', NULL, NULL, 1, 0, 1, NULL, 1244469700376633346, 1244469536593256449, 1244469700242415617);
INSERT INTO `t_system_menu_permission` VALUES (1244159630182871041, '2020-03-30 11:41:34', NULL, NULL, 1, 0, 1, NULL, 1244469792668098561, 1244469536593256449, 1244469792550658049);
INSERT INTO `t_system_menu_permission` VALUES (1244159630182871041, '2020-03-30 15:31:19', NULL, NULL, 1, 0, 1, NULL, 1244527609861087234, 1244469536593256449, 1244527609743646721);
INSERT INTO `t_system_menu_permission` VALUES (1244159630182871041, '2020-03-30 15:51:55', NULL, NULL, 1, 0, 1, NULL, 1244532793953722369, 1244469536593256449, 1244532793827893250);
INSERT INTO `t_system_menu_permission` VALUES (1244159630182871041, '2020-03-31 08:38:48', NULL, NULL, 1, 0, 1, NULL, 1244786185951199234, 1244469536593256449, 1244786185800204290);
INSERT INTO `t_system_menu_permission` VALUES (1244159630182871041, '2020-04-01 10:36:01', NULL, NULL, 1, 0, 1, NULL, 1245178069605392386, 1243026675188297730, 1245178069475368962);
INSERT INTO `t_system_menu_permission` VALUES (1244159630182871041, '2020-04-01 10:36:22', NULL, NULL, 1, 0, 1, NULL, 1245178159040536577, 1243026675188297730, 1245178158906318850);
INSERT INTO `t_system_menu_permission` VALUES (1244159630182871041, '2020-04-01 10:38:07', NULL, NULL, 1, 0, 1, NULL, 1245178601250201601, 1243026675188297730, 1245178601090818049);
INSERT INTO `t_system_menu_permission` VALUES (1244159630182871041, '2020-04-01 10:38:26', NULL, NULL, 1, 0, 1, NULL, 1245178679067123713, 1243026675188297730, 1245178678953877505);
INSERT INTO `t_system_menu_permission` VALUES (1244159630182871041, '2020-04-01 10:39:03', NULL, NULL, 1, 0, 1, NULL, 1245178834092793858, 1243819283653275650, 1245178833987936258);
INSERT INTO `t_system_menu_permission` VALUES (1244159630182871041, '2020-04-01 10:39:18', NULL, NULL, 1, 0, 1, NULL, 1245178897204486145, 1243819283653275650, 1245178897108017153);
INSERT INTO `t_system_menu_permission` VALUES (1244159630182871041, '2020-04-01 10:39:38', NULL, NULL, 1, 0, 1, NULL, 1245178981631631361, 1243819283653275650, 1245178981514190849);
INSERT INTO `t_system_menu_permission` VALUES (1244159630182871041, '2020-04-01 10:39:59', NULL, NULL, 1, 0, 1, NULL, 1245179067522588673, 1243819283653275650, 1245179067421925377);
INSERT INTO `t_system_menu_permission` VALUES (1244159630182871041, '2020-04-01 10:40:42', NULL, NULL, 1, 0, 1, NULL, 1245179247617613825, 1244229413641314306, 1245179247521144833);
INSERT INTO `t_system_menu_permission` VALUES (1244159630182871041, '2020-04-01 10:41:03', NULL, NULL, 1, 0, 1, NULL, 1245179336150982657, 1244229413641314306, 1245179336041930754);
INSERT INTO `t_system_menu_permission` VALUES (1244159630182871041, '2020-04-01 10:41:25', NULL, NULL, 1, 0, 1, NULL, 1245179428417282049, 1244229413641314306, 1245179428299841538);
INSERT INTO `t_system_menu_permission` VALUES (1244159630182871041, '2020-04-01 10:42:02', NULL, NULL, 1, 0, 1, NULL, 1245179585556881410, 1244229413641314306, 1245179585460412418);
INSERT INTO `t_system_menu_permission` VALUES (1244159630182871041, '2020-04-01 10:42:38', NULL, NULL, 1, 0, 1, NULL, 1245179736430190594, 1244229413641314306, 1245179736346304513);
INSERT INTO `t_system_menu_permission` VALUES (1244159630182871041, '2020-04-01 10:43:14', NULL, NULL, 1, 0, 1, NULL, 1245179888364658690, 1244229413641314306, 1245179888255606785);
INSERT INTO `t_system_menu_permission` VALUES (1244159630182871041, '2020-04-01 10:43:40', NULL, NULL, 1, 0, 1, NULL, 1245179997001326593, 1244229413641314306, 1245179996820971521);
INSERT INTO `t_system_menu_permission` VALUES (1244159630182871041, '2020-04-01 10:44:03', NULL, NULL, 1, 0, 1, NULL, 1245180091360583682, 1244229413641314306, 1245180091238948866);
INSERT INTO `t_system_menu_permission` VALUES (1244159630182871041, '2020-04-01 10:44:42', NULL, NULL, 1, 0, 1, NULL, 1245180255592751106, 1244469536593256449, 1245180255500476417);
INSERT INTO `t_system_menu_permission` VALUES (1244159630182871041, '2020-04-01 10:45:05', NULL, NULL, 1, 0, 1, NULL, 1245180353831739394, 1244469536593256449, 1245180353731076097);
INSERT INTO `t_system_menu_permission` VALUES (1244159630182871041, '2020-04-01 10:45:37', NULL, NULL, 1, 0, 1, NULL, 1245180487118331906, 1244469536593256449, 1245180486950559746);
INSERT INTO `t_system_menu_permission` VALUES (1244159630182871041, '2020-04-01 10:46:02', NULL, NULL, 1, 0, 1, NULL, 1245180592730906625, 1244469536593256449, 1245180592617660417);
INSERT INTO `t_system_menu_permission` VALUES (1244159630182871041, '2020-04-01 10:46:28', NULL, NULL, 1, 0, 1, NULL, 1245180698507059202, 1244469536593256449, 1245180698104406017);
INSERT INTO `t_system_menu_permission` VALUES (1244159630182871041, '2020-04-01 10:47:20', NULL, NULL, 1, 0, 1, NULL, 1245180920176025602, 1243039012540571649, 1245180920037613569);
INSERT INTO `t_system_menu_permission` VALUES (1244159630182871041, '2020-04-01 10:47:38', NULL, NULL, 1, 0, 1, NULL, 1245180995266650114, 1243039012540571649, 1245180995174375426);
INSERT INTO `t_system_menu_permission` VALUES (1244159630182871041, '2020-04-01 10:48:03', NULL, NULL, 1, 0, 1, NULL, 1245181097758662657, 1243039012540571649, 1245181097620250626);
INSERT INTO `t_system_menu_permission` VALUES (1244159630182871041, '2020-04-01 10:48:18', NULL, NULL, 1, 0, 1, NULL, 1245181160270569473, 1243039012540571649, 1245181160157323265);
INSERT INTO `t_system_menu_permission` VALUES (1244159630182871041, '2020-04-01 10:48:56', NULL, NULL, 1, 0, 1, NULL, 1245181319490543618, 1243039012540571649, 1245181319150804994);
INSERT INTO `t_system_menu_permission` VALUES (1244159630182871041, '2020-04-01 10:49:41', NULL, NULL, 1, 0, 1, NULL, 1245181510713057282, 1244229759709143042, 1245181510595616770);
INSERT INTO `t_system_menu_permission` VALUES (1244159630182871041, '2020-04-01 10:50:07', NULL, NULL, 1, 0, 1, NULL, 1245181618636693506, 1244229759709143042, 1245181618284371969);
INSERT INTO `t_system_menu_permission` VALUES (1244159630182871041, '2020-04-01 10:50:23', NULL, NULL, 1, 0, 1, NULL, 1245181684529209346, 1244229759709143042, 1245181684403380225);
INSERT INTO `t_system_menu_permission` VALUES (1244159630182871041, '2020-04-01 10:50:40', NULL, NULL, 1, 0, 1, NULL, 1245181755836571650, 1244229759709143042, 1245181755471667201);

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
INSERT INTO `t_system_org` VALUES (1242391265059778561, '2020-03-27 09:43:39', NULL, NULL, 1, 0, 1, '', 1243352952386543618, -1, '测试01', '', 'T0001', 0, '-1', '测试', '', '');
INSERT INTO `t_system_org` VALUES (1242391265059778561, '2020-03-27 09:55:57', 1242391265059778561, '2020-03-27 10:18:35', 0, 1, 3, '', 1243356046960812033, 1243352952386543618, '测试0102', '', 'T0002', 0, '-1_1243352952386543618', '测试', '', '');
INSERT INTO `t_system_org` VALUES (1242391265059778561, '2020-03-28 14:10:19', NULL, NULL, 1, 0, 1, '', 1243782449166483457, 1243352952386543618, '测试02', '', 'T0002', 0, '-1_1243352952386543618', 'test', '', '');
INSERT INTO `t_system_org` VALUES (1242391265059778561, '2020-03-28 16:47:40', 1244159630182871041, '2020-03-31 18:13:40', 0, 0, 3, '', 1243822046336471042, 1243352952386543618, '测试03', '', 'T0003', 0, '-1_1243352952386543618', 'test', '', '');
INSERT INTO `t_system_org` VALUES (1242391265059778561, '2020-03-28 16:48:21', 1244159630182871041, '2020-03-31 15:10:28', 1, 0, 2, '', 1243822221792595970, 1243782449166483457, '测试04', '', 'T0004', 0, '-1_1243352952386543618', 'test', '', '');

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
INSERT INTO `t_system_permission` VALUES (1244159630182871041, '2020-04-01 10:36:01', 1244159630182871041, '2020-04-01 10:36:42', 1, 0, 2, '', 1245178069475368962, '菜单查询', 'menuQuery', 'menu:query', 0);
INSERT INTO `t_system_permission` VALUES (1244159630182871041, '2020-04-01 10:36:22', 1244159630182871041, '2020-04-01 10:36:44', 1, 0, 2, '', 1245178158906318850, '菜单新增', 'menuSave', 'menu:save', 0);
INSERT INTO `t_system_permission` VALUES (1244159630182871041, '2020-04-01 10:38:07', NULL, NULL, 1, 0, 1, '', 1245178601090818049, '菜单删除', 'menuDelete', 'menu:delete', 0);
INSERT INTO `t_system_permission` VALUES (1244159630182871041, '2020-04-01 10:38:26', NULL, NULL, 1, 0, 1, '', 1245178678953877505, '菜单修改', 'menuUpdate', 'menu:Update', 0);
INSERT INTO `t_system_permission` VALUES (1244159630182871041, '2020-04-01 10:39:03', NULL, NULL, 1, 0, 1, '', 1245178833987936258, '组织查询', 'orgQuery', 'org:query', 0);
INSERT INTO `t_system_permission` VALUES (1244159630182871041, '2020-04-01 10:39:18', NULL, NULL, 1, 0, 1, '', 1245178897108017153, '组织新增', 'orgSave', 'org:save', 0);
INSERT INTO `t_system_permission` VALUES (1244159630182871041, '2020-04-01 10:39:38', NULL, NULL, 1, 0, 1, '', 1245178981514190849, '组织修改', 'orgUpdate', 'org:update', 0);
INSERT INTO `t_system_permission` VALUES (1244159630182871041, '2020-04-01 10:39:59', NULL, NULL, 1, 0, 1, '', 1245179067421925377, '组织删除', 'orgDelete', 'org:delete', 0);
INSERT INTO `t_system_permission` VALUES (1244159630182871041, '2020-04-01 10:40:42', NULL, NULL, 1, 0, 1, '', 1245179247521144833, '角色查询', 'roleQuery', 'role:query', 0);
INSERT INTO `t_system_permission` VALUES (1244159630182871041, '2020-04-01 10:41:03', NULL, NULL, 1, 0, 1, '', 1245179336041930754, '角色新增', 'roleSave', 'role:save', 0);
INSERT INTO `t_system_permission` VALUES (1244159630182871041, '2020-04-01 10:41:25', NULL, NULL, 1, 0, 1, '', 1245179428299841538, '角色修改', 'roleUpdate', 'role:update', 0);
INSERT INTO `t_system_permission` VALUES (1244159630182871041, '2020-04-01 10:42:02', NULL, NULL, 1, 0, 1, '', 1245179585460412418, '角色删除', 'roleDelete', 'role:delete', 0);
INSERT INTO `t_system_permission` VALUES (1244159630182871041, '2020-04-01 10:42:38', NULL, NULL, 1, 0, 1, '', 1245179736346304513, '角色权限绑定', 'rolePermissionSave', 'role:permission:save', 0);
INSERT INTO `t_system_permission` VALUES (1244159630182871041, '2020-04-01 10:43:14', NULL, NULL, 1, 0, 1, '', 1245179888255606785, '角色权限查询', 'rolePermissionQuery', 'role:permission:query', 0);
INSERT INTO `t_system_permission` VALUES (1244159630182871041, '2020-04-01 10:43:40', NULL, NULL, 1, 0, 1, '', 1245179996820971521, '角色范围绑定', 'roleOrgSave', 'role:org:save', 0);
INSERT INTO `t_system_permission` VALUES (1244159630182871041, '2020-04-01 10:44:03', NULL, NULL, 1, 0, 1, '', 1245180091238948866, '角色范围查询', 'roleOrgQuery', 'role:org:query', 0);
INSERT INTO `t_system_permission` VALUES (1244159630182871041, '2020-04-01 10:44:42', 1244159630182871041, '2020-04-01 10:45:18', 1, 0, 2, '', 1245180255500476417, '字典查询', 'dictQuery', 'dict:query', 0);
INSERT INTO `t_system_permission` VALUES (1244159630182871041, '2020-04-01 10:45:05', NULL, NULL, 1, 0, 1, '', 1245180353731076097, '字典项查询', 'dictDataQuery', 'dict:data:query', 0);
INSERT INTO `t_system_permission` VALUES (1244159630182871041, '2020-04-01 10:45:37', NULL, NULL, 1, 0, 1, '', 1245180486950559746, '字典新增', 'dictSave', 'dict:save', 0);
INSERT INTO `t_system_permission` VALUES (1244159630182871041, '2020-04-01 10:46:02', NULL, NULL, 1, 0, 1, '', 1245180592617660417, '字典修改', 'dictUpdate', 'dict:update', 0);
INSERT INTO `t_system_permission` VALUES (1244159630182871041, '2020-04-01 10:46:27', NULL, NULL, 1, 0, 1, '', 1245180698104406017, '字典删除', 'dictDelete', 'dict:delete', 0);
INSERT INTO `t_system_permission` VALUES (1244159630182871041, '2020-04-01 10:47:20', NULL, NULL, 1, 0, 1, '', 1245180920037613569, '用户查询', 'userQuery', 'user:query', 0);
INSERT INTO `t_system_permission` VALUES (1244159630182871041, '2020-04-01 10:47:38', NULL, NULL, 1, 0, 1, '', 1245180995174375426, '用户新增', 'userSave', 'user:save', 0);
INSERT INTO `t_system_permission` VALUES (1244159630182871041, '2020-04-01 10:48:03', NULL, NULL, 1, 0, 1, '', 1245181097620250626, '用户修改', 'userUpdate', 'user:update', 0);
INSERT INTO `t_system_permission` VALUES (1244159630182871041, '2020-04-01 10:48:18', NULL, NULL, 1, 0, 1, '', 1245181160157323265, '用户删除', 'userDelete', 'user:delete', 0);
INSERT INTO `t_system_permission` VALUES (1244159630182871041, '2020-04-01 10:48:55', NULL, NULL, 1, 0, 1, '', 1245181319150804994, '用户密码重置', 'userResetPassword', 'user:reset:password', 0);
INSERT INTO `t_system_permission` VALUES (1244159630182871041, '2020-04-01 10:49:41', NULL, NULL, 1, 0, 1, '', 1245181510595616770, '岗位查询', 'postQuery', 'post:query', 0);
INSERT INTO `t_system_permission` VALUES (1244159630182871041, '2020-04-01 10:50:07', NULL, NULL, 1, 0, 1, '', 1245181618284371969, '岗位新增', 'postSave', 'post:save', 0);
INSERT INTO `t_system_permission` VALUES (1244159630182871041, '2020-04-01 10:50:23', NULL, NULL, 1, 0, 1, '', 1245181684403380225, '岗位修改', 'postUpdate', 'post:update', 0);
INSERT INTO `t_system_permission` VALUES (1244159630182871041, '2020-04-01 10:50:40', NULL, NULL, 1, 0, 1, '', 1245181755471667201, '岗位删除', 'postDelete', 'post:delete', 0);

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
-- Records of t_system_post
-- ----------------------------
INSERT INTO `t_system_post` VALUES (1242391265059778561, '2020-03-28 16:01:31', 1242391265059778561, '2020-03-28 16:07:09', 0, 1, 3, '测试', 1243810432942043137, '测试01', '', 'test01', 0);
INSERT INTO `t_system_post` VALUES (1242391265059778561, '2020-03-28 16:11:08', NULL, NULL, 1, 0, 1, '', 1243812853739122689, '董事长', '', '0001', 1);
INSERT INTO `t_system_post` VALUES (1242391265059778561, '2020-03-28 18:23:01', NULL, NULL, 0, 0, 1, '', 1243846044386492418, '测试', '', 'test02', 0);
INSERT INTO `t_system_post` VALUES (1242391265059778561, '2020-03-28 18:23:25', NULL, NULL, 1, 0, 1, '', 1243846145951563778, '测试03', '', 'test03', 0);

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
INSERT INTO `t_system_role` VALUES (1242391265059778561, '2020-03-27 13:28:12', 1242391265059778561, '2020-03-27 14:04:37', 1, 1, 1, 'test', 1243409460814024706, '测试', 'test', 0);
INSERT INTO `t_system_role` VALUES (1242391265059778561, '2020-03-27 14:10:50', NULL, NULL, 1, 0, 1, '', 1243420190300942338, '超级管理员', 'Administrator', 0);
INSERT INTO `t_system_role` VALUES (1244159630182871041, '2020-03-30 09:42:11', NULL, NULL, 1, 0, 1, '菜单管理', 1244439745613737986, '菜单管理', 'MENU', 0);
INSERT INTO `t_system_role` VALUES (1244159630182871041, '2020-03-31 15:20:41', 1244159630182871041, '2020-03-31 15:20:49', 1, 1, 1, '', 1244887321395769345, '系统状态码', 'system_code_status', 0);

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
INSERT INTO `t_system_role_org` VALUES (1244159630182871041, '2020-04-01 10:15:55', 1244159630182871041, '2020-04-01 10:30:35', 1, 0, 6, NULL, 1245173013342523394, 1243420190300942338, 1243352952386543618);
INSERT INTO `t_system_role_org` VALUES (1244159630182871041, '2020-04-01 10:15:55', 1244159630182871041, '2020-04-01 10:30:35', 1, 0, 6, NULL, 1245173013363494914, 1243420190300942338, 1243782449166483457);
INSERT INTO `t_system_role_org` VALUES (1244159630182871041, '2020-04-01 10:15:55', 1244159630182871041, '2020-04-01 10:16:10', 1, 0, 7, NULL, 1245173013367689218, 1243420190300942338, 1243822221792595970);

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
INSERT INTO `t_system_role_permission` VALUES (1244159630182871041, '2020-04-01 10:51:13', 1244159630182871041, '2020-04-01 10:51:45', 1, 0, 5, NULL, 1245181896425447425, 1243420190300942338, 1245178069475368962);
INSERT INTO `t_system_role_permission` VALUES (1244159630182871041, '2020-04-01 10:51:13', 1244159630182871041, '2020-04-01 10:51:45', 1, 0, 5, NULL, 1245181896484167682, 1243420190300942338, 1245178158906318850);
INSERT INTO `t_system_role_permission` VALUES (1244159630182871041, '2020-04-01 10:51:13', 1244159630182871041, '2020-04-01 10:51:46', 1, 0, 5, NULL, 1245181896500944897, 1243420190300942338, 1245178601090818049);
INSERT INTO `t_system_role_permission` VALUES (1244159630182871041, '2020-04-01 10:51:13', 1244159630182871041, '2020-04-01 10:51:46', 1, 0, 5, NULL, 1245181896517722114, 1243420190300942338, 1245178678953877505);
INSERT INTO `t_system_role_permission` VALUES (1244159630182871041, '2020-04-01 10:51:13', 1244159630182871041, '2020-04-01 10:51:41', 1, 0, 5, NULL, 1245181896538693634, 1243420190300942338, 1245178833987936258);
INSERT INTO `t_system_role_permission` VALUES (1244159630182871041, '2020-04-01 10:51:13', 1244159630182871041, '2020-04-01 10:51:41', 1, 0, 5, NULL, 1245181896563859458, 1243420190300942338, 1245178897108017153);
INSERT INTO `t_system_role_permission` VALUES (1244159630182871041, '2020-04-01 10:51:13', 1244159630182871041, '2020-04-01 10:51:41', 1, 0, 5, NULL, 1245181896584830978, 1243420190300942338, 1245178981514190849);
INSERT INTO `t_system_role_permission` VALUES (1244159630182871041, '2020-04-01 10:51:13', 1244159630182871041, '2020-04-01 10:51:41', 1, 0, 5, NULL, 1245181896593219586, 1243420190300942338, 1245179067421925377);
INSERT INTO `t_system_role_permission` VALUES (1244159630182871041, '2020-04-01 10:51:13', 1244159630182871041, '2020-04-01 10:51:41', 1, 0, 5, NULL, 1245181896626774017, 1243420190300942338, 1245179247521144833);
INSERT INTO `t_system_role_permission` VALUES (1244159630182871041, '2020-04-01 10:51:13', 1244159630182871041, '2020-04-01 10:51:41', 1, 0, 5, NULL, 1245181896664522753, 1243420190300942338, 1245179336041930754);
INSERT INTO `t_system_role_permission` VALUES (1244159630182871041, '2020-04-01 10:51:13', 1244159630182871041, '2020-04-01 10:51:41', 1, 0, 5, NULL, 1245181896685494274, 1243420190300942338, 1245179428299841538);
INSERT INTO `t_system_role_permission` VALUES (1244159630182871041, '2020-04-01 10:51:13', 1244159630182871041, '2020-04-01 10:51:41', 1, 0, 5, NULL, 1245181896702271490, 1243420190300942338, 1245179585460412418);
INSERT INTO `t_system_role_permission` VALUES (1244159630182871041, '2020-04-01 10:51:13', 1244159630182871041, '2020-04-01 10:51:41', 1, 0, 5, NULL, 1245181896719048705, 1243420190300942338, 1245179736346304513);
INSERT INTO `t_system_role_permission` VALUES (1244159630182871041, '2020-04-01 10:51:13', 1244159630182871041, '2020-04-01 10:51:41', 1, 0, 5, NULL, 1245181896735825922, 1243420190300942338, 1245179888255606785);
INSERT INTO `t_system_role_permission` VALUES (1244159630182871041, '2020-04-01 10:51:13', 1244159630182871041, '2020-04-01 10:51:41', 1, 0, 5, NULL, 1245181896752603138, 1243420190300942338, 1245179996820971521);
INSERT INTO `t_system_role_permission` VALUES (1244159630182871041, '2020-04-01 10:51:13', 1244159630182871041, '2020-04-01 10:51:41', 1, 0, 5, NULL, 1245181896769380354, 1243420190300942338, 1245180091238948866);
INSERT INTO `t_system_role_permission` VALUES (1244159630182871041, '2020-04-01 10:51:13', 1244159630182871041, '2020-04-01 10:51:41', 1, 0, 5, NULL, 1245181896836489218, 1243420190300942338, 1245180255500476417);
INSERT INTO `t_system_role_permission` VALUES (1244159630182871041, '2020-04-01 10:51:13', 1244159630182871041, '2020-04-01 10:51:41', 1, 0, 5, NULL, 1245181896874237954, 1243420190300942338, 1245180353731076097);
INSERT INTO `t_system_role_permission` VALUES (1244159630182871041, '2020-04-01 10:51:13', 1244159630182871041, '2020-04-01 10:51:41', 1, 0, 5, NULL, 1245181896895209473, 1243420190300942338, 1245180486950559746);
INSERT INTO `t_system_role_permission` VALUES (1244159630182871041, '2020-04-01 10:51:13', 1244159630182871041, '2020-04-01 10:51:41', 1, 0, 5, NULL, 1245181896911986689, 1243420190300942338, 1245180592617660417);
INSERT INTO `t_system_role_permission` VALUES (1244159630182871041, '2020-04-01 10:51:13', 1244159630182871041, '2020-04-01 10:51:41', 1, 0, 5, NULL, 1245181896928763905, 1243420190300942338, 1245180698104406017);
INSERT INTO `t_system_role_permission` VALUES (1244159630182871041, '2020-04-01 10:51:13', 1244159630182871041, '2020-04-01 10:51:41', 1, 0, 5, NULL, 1245181896945541121, 1243420190300942338, 1245180920037613569);
INSERT INTO `t_system_role_permission` VALUES (1244159630182871041, '2020-04-01 10:51:13', 1244159630182871041, '2020-04-01 10:51:41', 1, 0, 5, NULL, 1245181896962318337, 1243420190300942338, 1245180995174375426);
INSERT INTO `t_system_role_permission` VALUES (1244159630182871041, '2020-04-01 10:51:13', 1244159630182871041, '2020-04-01 10:51:41', 1, 0, 5, NULL, 1245181896979095554, 1243420190300942338, 1245181097620250626);
INSERT INTO `t_system_role_permission` VALUES (1244159630182871041, '2020-04-01 10:51:13', 1244159630182871041, '2020-04-01 10:51:41', 1, 0, 5, NULL, 1245181896995872769, 1243420190300942338, 1245181160157323265);
INSERT INTO `t_system_role_permission` VALUES (1244159630182871041, '2020-04-01 10:51:13', 1244159630182871041, '2020-04-01 10:51:41', 1, 0, 5, NULL, 1245181897012649986, 1243420190300942338, 1245181319150804994);
INSERT INTO `t_system_role_permission` VALUES (1244159630182871041, '2020-04-01 10:51:13', 1244159630182871041, '2020-04-01 10:51:41', 1, 0, 5, NULL, 1245181897033621505, 1243420190300942338, 1245181510595616770);
INSERT INTO `t_system_role_permission` VALUES (1244159630182871041, '2020-04-01 10:51:13', 1244159630182871041, '2020-04-01 10:51:41', 1, 0, 5, NULL, 1245181897050398721, 1243420190300942338, 1245181618284371969);
INSERT INTO `t_system_role_permission` VALUES (1244159630182871041, '2020-04-01 10:51:13', 1244159630182871041, '2020-04-01 10:51:41', 1, 0, 5, NULL, 1245181897062981634, 1243420190300942338, 1245181684403380225);
INSERT INTO `t_system_role_permission` VALUES (1244159630182871041, '2020-04-01 10:51:13', 1244159630182871041, '2020-04-01 10:51:41', 1, 0, 5, NULL, 1245181897079758850, 1243420190300942338, 1245181755471667201);

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
  `avatar` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户头像',
  `dept_Id` bigint(20) NULL DEFAULT NULL COMMENT '部门ID',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统用户 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_system_user
-- ----------------------------
INSERT INTO `t_system_user` VALUES (7, 0, -1, '2020-03-24 18:02:15', 1242391265059778561, '2020-03-25 17:43:59', 1, 1242391265059778561, 'test', '$2a$10$q8s8qS39wai4idaPxix2DemUX8mFVXB7I241naFbDCh7KSjDh.yye', '测试', '213', '1231', 1, NULL, 1243782449166483457, NULL);
INSERT INTO `t_system_user` VALUES (7, 0, 1242391265059778561, '2020-03-29 15:09:06', 1242391265059778561, '2020-03-29 15:39:22', 1, 1244159630182871041, 'Administrator', '$2a$10$86Oxf5477aSRmeSM1YQz2eVAU.c1G5sc2Iq215I6/5hBJXfBZCIgW', '超级管理员', NULL, NULL, 2, NULL, 1243352952386543618, NULL);
INSERT INTO `t_system_user` VALUES (1, 0, 1244159630182871041, '2020-03-30 09:41:18', NULL, NULL, 1, 1244439525781876738, 'Admin', '$2a$10$QJ.3Im.pNVt0cZZbxtvJnO0yCQJhoXYVUID00.8vw56GrPGZTTF3O', '管理员', NULL, NULL, 1, NULL, 1243352952386543618, 0);

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
INSERT INTO `t_system_user_role` VALUES (1242391265059778561, '2020-03-29 15:09:06', NULL, NULL, 1, 1, 1, NULL, 1244159630841376769, 1244159630182871041, 1243420190300942338);
INSERT INTO `t_system_user_role` VALUES (1242391265059778561, '2020-03-29 15:39:23', NULL, NULL, 1, 1, 1, NULL, 1244167253376610305, 1244159630182871041, 1243420190300942338);
INSERT INTO `t_system_user_role` VALUES (1244159630182871041, '2020-03-31 17:47:35', NULL, NULL, 1, 1, 1, NULL, 1244924288133148674, 1244159630182871041, 1243420190300942338);
INSERT INTO `t_system_user_role` VALUES (1244159630182871041, '2020-03-31 17:47:42', NULL, NULL, 1, 1, 1, NULL, 1244924319045169154, 1244159630182871041, 1243420190300942338);
INSERT INTO `t_system_user_role` VALUES (1244159630182871041, '2020-03-31 17:48:43', NULL, NULL, 1, 1, 1, NULL, 1244924574897713153, 1244159630182871041, 1243420190300942338);
INSERT INTO `t_system_user_role` VALUES (1244159630182871041, '2020-03-31 17:50:13', NULL, NULL, 1, 1, 1, NULL, 1244924950803820546, 1244159630182871041, 1243420190300942338);
INSERT INTO `t_system_user_role` VALUES (1244159630182871041, '2020-03-31 17:50:19', NULL, NULL, 1, 0, 1, NULL, 1244924976431017985, 1244159630182871041, 1243420190300942338);

SET FOREIGN_KEY_CHECKS = 1;
