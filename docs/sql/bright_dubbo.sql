/*
 Navicat Premium Dump SQL

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80041 (8.0.41)
 Source Host           : localhost:3306
 Source Schema         : bright_dubbo

 Target Server Type    : MySQL
 Target Server Version : 80041 (8.0.41)
 File Encoding         : 65001

 Date: 23/03/2025 17:29:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `BLOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `CALENDAR_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `CALENDAR_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `CRON_EXPRESSION` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TIME_ZONE_ID` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `ENTRY_ID` varchar(95) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `INSTANCE_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `FIRED_TIME` bigint NOT NULL,
  `SCHED_TIME` bigint NOT NULL,
  `PRIORITY` int NOT NULL,
  `STATE` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `ENTRY_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `IS_DURABLE` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `IS_UPDATE_DATA` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `JOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `LOCK_NAME` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `LOCK_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks` VALUES ('bright-server-base-Scheduler', 'STATE_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('bright-server-base-Scheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `INSTANCE_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `LAST_CHECKIN_TIME` bigint NOT NULL,
  `CHECKIN_INTERVAL` bigint NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `INSTANCE_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------
INSERT INTO `qrtz_scheduler_state` VALUES ('bright-server-base-Scheduler', 'DESKTOP-VSG3FKS1742621642982', 1742621645287, 15000);

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `REPEAT_COUNT` bigint NOT NULL,
  `REPEAT_INTERVAL` bigint NOT NULL,
  `TIMES_TRIGGERED` bigint NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `STR_PROP_1` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `STR_PROP_2` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `STR_PROP_3` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `INT_PROP_1` int NULL DEFAULT NULL,
  `INT_PROP_2` int NULL DEFAULT NULL,
  `LONG_PROP_1` bigint NULL DEFAULT NULL,
  `LONG_PROP_2` bigint NULL DEFAULT NULL,
  `DEC_PROP_1` decimal(13, 4) NULL DEFAULT NULL,
  `DEC_PROP_2` decimal(13, 4) NULL DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint NULL DEFAULT NULL,
  `PREV_FIRE_TIME` bigint NULL DEFAULT NULL,
  `PRIORITY` int NULL DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_TYPE` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `START_TIME` bigint NOT NULL,
  `END_TIME` bigint NULL DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `MISFIRE_INSTR` smallint NULL DEFAULT NULL,
  `JOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `SCHED_NAME`(`SCHED_NAME` ASC, `JOB_NAME` ASC, `JOB_GROUP` ASC) USING BTREE,
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for t_enterprise
-- ----------------------------
DROP TABLE IF EXISTS `t_enterprise`;
CREATE TABLE `t_enterprise`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `business_license_number` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '工商注册码',
  `enterprise_code` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '企业编号',
  `enterprise_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '企业名称',
  `industry_code` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '所属行业',
  `area_code` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '所属区域',
  `enterprise_type` tinyint NULL DEFAULT NULL COMMENT '企业类型(国企:0，民企:1，私企:2，外企:3)',
  `telephone` varchar(60) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '企业联系电话',
  `email` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '企业邮箱',
  `zip_code` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '邮政编码',
  `legal_person` varchar(60) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '法人',
  `main_person` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '企业负责人姓名',
  `main_person_mobile` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '企业负责人电话',
  `map_x` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT 'x坐标',
  `map_y` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT 'y坐标',
  `map_z` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT 'z坐标',
  `address` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '地址',
  `status` tinyint NULL DEFAULT 0 COMMENT '企业状态（0-正常，1-禁用）',
  `create_time` datetime NULL DEFAULT NULL COMMENT '记录创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '记录最后修改时间',
  `create_user` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '记录创建者(用户)',
  `update_user` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '记录最后修改者(用户)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '企业信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_enterprise
-- ----------------------------
INSERT INTO `t_enterprise` VALUES (1, '20181218', '96', '测试企业1', '0', '0', 1, '13888888888', 'tuminglong@126.com', '430071', '测试人', '测试人', '13888888888', '100', '100', '100', '测试', 0, '2018-12-18 10:56:46', '2020-04-08 18:46:02', NULL, 'admin');

-- ----------------------------
-- Table structure for t_enterprise_department
-- ----------------------------
DROP TABLE IF EXISTS `t_enterprise_department`;
CREATE TABLE `t_enterprise_department`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `parent_id` bigint NULL DEFAULT NULL COMMENT '企业部门父ID',
  `enterprise_id` bigint NULL DEFAULT NULL COMMENT '企业ID(对应企业主表ID)',
  `department_code` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '部门代码(可添加多个部门ID，用逗号隔开，表示该部门可以管理多个部门)',
  `department_name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '部门名称',
  `create_time` datetime NULL DEFAULT NULL COMMENT '记录创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '记录最后修改时间',
  `create_user` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '记录创建者(用户)',
  `update_user` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '记录最后修改者(用户)',
  `parameter1` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '预留1',
  `parameter2` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '预留2',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '企业部门表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_enterprise_department
-- ----------------------------

-- ----------------------------
-- Table structure for t_enterprise_job
-- ----------------------------
DROP TABLE IF EXISTS `t_enterprise_job`;
CREATE TABLE `t_enterprise_job`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `department_id` bigint NULL DEFAULT NULL COMMENT '企业部门表ID',
  `job_code` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '职务代码',
  `job_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '职务名称',
  `create_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '记录创建时间',
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '记录最后更新时间',
  `create_user` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '记录创建用户',
  `update_user` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '记录最后更新用户',
  `parameter1` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '预留1',
  `parameter2` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '预留2',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '企业职务配置表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_enterprise_job
-- ----------------------------

-- ----------------------------
-- Table structure for t_schedule_job
-- ----------------------------
DROP TABLE IF EXISTS `t_schedule_job`;
CREATE TABLE `t_schedule_job`  (
  `job_id` bigint NOT NULL AUTO_INCREMENT COMMENT '任务id',
  `bean_name` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT 'spring bean名称',
  `method_name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '方法名',
  `params` varchar(2000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '参数',
  `cron_expression` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT 'cron表达式',
  `status` tinyint NULL DEFAULT NULL COMMENT '任务状态  0：正常  1：暂停',
  `remark` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '定时任务' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_schedule_job
-- ----------------------------

-- ----------------------------
-- Table structure for t_schedule_job_log
-- ----------------------------
DROP TABLE IF EXISTS `t_schedule_job_log`;
CREATE TABLE `t_schedule_job_log`  (
  `log_id` bigint NOT NULL AUTO_INCREMENT COMMENT '任务日志id',
  `job_id` bigint NOT NULL COMMENT '任务id',
  `bean_name` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT 'spring bean名称',
  `method_name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '方法名',
  `params` varchar(2000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '参数',
  `status` tinyint NOT NULL COMMENT '任务状态    0：成功    1：失败',
  `error` varchar(2000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '失败信息',
  `times` int NOT NULL COMMENT '耗时(单位：毫秒)',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`log_id`) USING BTREE,
  INDEX `job_id`(`job_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '定时任务日志' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_schedule_job_log
-- ----------------------------

-- ----------------------------
-- Table structure for t_sys_dic
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_dic`;
CREATE TABLE `t_sys_dic`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `parent_id` bigint NOT NULL COMMENT '父变量ID',
  `var_code` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '变量代码',
  `var_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '变量名称',
  `create_time` datetime NULL DEFAULT NULL COMMENT '记录创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '记录修改时间',
  `create_user` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '记录创建者（用户）',
  `update_user` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '记录最后修改者（用户）',
  `is_sync` tinyint NULL DEFAULT NULL COMMENT '数据是否同步(0:是,1:否)',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_dic_code`(`var_code` ASC) USING BTREE,
  INDEX `index_dic_name`(`var_name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '数据字典表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_sys_dic
-- ----------------------------
INSERT INTO `t_sys_dic` VALUES (9, 0, '2017052', '行业', '2018-05-07 20:27:53', '2018-06-14 19:19:12', 'admin', 'admin', NULL);
INSERT INTO `t_sys_dic` VALUES (16, 0, '20184049', '区域', '2018-05-07 20:28:52', '2018-05-07 20:28:54', 'admin', 'admin', NULL);

-- ----------------------------
-- Table structure for t_sys_file
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_file`;
CREATE TABLE `t_sys_file`  (
  `id` bigint NOT NULL,
  `table_id` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '附件类型(哪个表的附件)',
  `record_id` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `attachment_group` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '表的记录Id下的附件分组的组名',
  `attachment_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '附件名称',
  `attachment_path` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '附件路径',
  `attachment_type` tinyint NULL DEFAULT NULL COMMENT '附件类型(0-word,1-excel,2-pdf,3-jpg,png,4-其他)',
  `enterprise_id` bigint NULL DEFAULT NULL,
  `save_type` tinyint NULL DEFAULT NULL COMMENT '存储类型（0：本地存储，1:fastdfs）',
  `create_time` datetime NULL DEFAULT NULL COMMENT '记录创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '记录最后修改时间',
  `create_user` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '记录创建者(用户)',
  `update_user` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '记录最后修改者(用户)',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `table_id`(`table_id` ASC, `record_id` ASC, `attachment_group` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '附件表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_sys_file
-- ----------------------------
INSERT INTO `t_sys_file` VALUES (1247838103305990145, 't_enterprise', '1', '企业', '学习笔记.jpg', 'http://localhost:8182//upload/enterprise/20200408/1586342757782.jpg', 3, NULL, 0, '2020-04-08 18:46:02', NULL, 'admin', NULL);

-- ----------------------------
-- Table structure for t_sys_log
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_log`;
CREATE TABLE `t_sys_log`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `login_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '登陆名',
  `log_type` tinyint NULL DEFAULT NULL COMMENT '日志类型（0:操作日志，1：登录日志）',
  `log_content` varchar(1024) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '日志内容',
  `operate_type` tinyint NULL DEFAULT NULL COMMENT '操作类型（1查询，2添加，3修改，4删除）',
  `class_name` varchar(1024) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '类名',
  `method` varchar(1024) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '请求方法',
  `params` varchar(1024) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '请求参数',
  `time` bigint NULL DEFAULT NULL COMMENT '执行时长',
  `client_ip` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '客户端ip',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 62 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '系统日志表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for t_sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_notice`;
CREATE TABLE `t_sys_notice`  (
  `ID` bigint NOT NULL AUTO_INCREMENT,
  `TITLE` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '标题',
  `MSG_ABSTRACT` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '摘要',
  `MSG_CONTENT` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '内容',
  `START_TIME` datetime NULL DEFAULT NULL COMMENT '开始时间',
  `END_TIME` datetime NULL DEFAULT NULL COMMENT '结束时间',
  `PRIORITY` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '优先级（L低，M中，H高）',
  `MSG_CATEGORY` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '2' COMMENT '消息类型1:通知公告2:系统消息',
  `MSG_TYPE` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '通告对象类型（USER:指定用户，ALL:全体用户）',
  `SEND_STATUS` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '发布状态（0未发布，1已发布，2已撤销）',
  `SEND_TIME` datetime NULL DEFAULT NULL COMMENT '发布时间',
  `CANCEL_TIME` datetime NULL DEFAULT NULL COMMENT '撤销时间',
  `USER_IDS` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '指定用户',
  `BUS_TYPE` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '业务类型(email:邮件 bpm:流程)',
  `BUS_ID` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '业务id',
  `OPEN_TYPE` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '打开方式(组件：component 路由：url)',
  `OPEN_PAGE` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '组件/路由 地址',
  `CREATE_USER` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `CREATE_TIME` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATE_USER` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `UPDATE_TIME` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `DEL_FLAG` varchar(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '删除状态（0，正常，1已删除）',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '系统通告表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_sys_notice
-- ----------------------------
INSERT INTO `t_sys_notice` VALUES (4, '测试1', '测试1', '测试', '2020-10-14 15:20:00', '2020-12-31 23:59:59', 'H', '2', 'ALL', '1', '2020-11-22 17:45:22', NULL, NULL, NULL, NULL, NULL, NULL, 'admin', '2020-09-21 15:19:22', 'admin', '2020-11-22 17:45:22', '0');
INSERT INTO `t_sys_notice` VALUES (5, '测试2', '测试2', '测试2', '2020-10-14 11:20:00', '2020-12-31 23:59:59', 'H', '1', 'ALL', '1', '2020-11-22 17:45:14', NULL, NULL, NULL, NULL, NULL, NULL, 'admin', '2020-09-21 16:58:50', 'admin', '2020-11-22 17:45:14', '0');
INSERT INTO `t_sys_notice` VALUES (6, '测试3', '测试3', '测试3', '2020-10-14 11:20:00', '2020-12-31 23:59:59', 'L', '2', 'ALL', '1', '2020-11-22 17:45:01', NULL, NULL, NULL, NULL, NULL, NULL, 'admin', '2020-09-21 17:22:47', 'admin', '2020-11-22 17:45:01', '0');
INSERT INTO `t_sys_notice` VALUES (7, '测试4', '测试4', '测试4', '2020-10-14 11:30:00', '2020-12-31 23:59:59', 'H', '1', 'ALL', '2', '2020-11-22 17:44:39', '2020-11-22 18:31:24', NULL, NULL, NULL, NULL, NULL, 'admin', '2020-10-14 11:28:14', 'admin', '2020-11-22 18:31:24', '0');

-- ----------------------------
-- Table structure for t_sys_notice_send
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_notice_send`;
CREATE TABLE `t_sys_notice_send`  (
  `ID` bigint NOT NULL AUTO_INCREMENT,
  `NOTICE_ID` bigint NULL DEFAULT NULL COMMENT '通告ID',
  `USER_ID` bigint NULL DEFAULT NULL COMMENT '用户id',
  `READ_FLAG` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '阅读状态（0未读，1已读）',
  `READ_TIME` datetime NULL DEFAULT NULL COMMENT '阅读时间',
  `CREATE_USER` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `CREATE_TIME` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATE_USER` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `UPDATE_TIME` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '用户通告阅读标记表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_sys_notice_send
-- ----------------------------
INSERT INTO `t_sys_notice_send` VALUES (12, 4, 1, '0', NULL, 'admin', '2020-11-22 17:48:14', 'admin', '2020-11-22 17:48:14');
INSERT INTO `t_sys_notice_send` VALUES (13, 5, 1, '0', NULL, 'admin', '2020-11-22 17:48:14', 'admin', '2020-11-22 17:48:14');
INSERT INTO `t_sys_notice_send` VALUES (14, 6, 1, '0', NULL, 'admin', '2020-11-22 17:48:14', 'admin', '2020-11-22 17:48:14');
INSERT INTO `t_sys_notice_send` VALUES (15, 7, 1, '0', NULL, 'admin', '2020-11-22 17:48:14', 'admin', '2020-11-22 17:48:14');

-- ----------------------------
-- Table structure for t_sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_resource`;
CREATE TABLE `t_sys_resource`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` bigint NULL DEFAULT NULL COMMENT '父级资源ID',
  `name` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '资源名称',
  `url` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '资源路径',
  `description` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '资源介绍',
  `icon` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '资源图标',
  `seq` tinyint NOT NULL DEFAULT 0 COMMENT '排序',
  `resource_type` tinyint NOT NULL DEFAULT 0 COMMENT '资源类别(0：菜单，1：按钮)',
  `status` tinyint NOT NULL DEFAULT 0 COMMENT '状态(0：开，1：关）',
  `create_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '记录创建时间',
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '记录最后修改时间',
  `create_user` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '记录创建用户',
  `update_user` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '记录最后修改用户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1193731450753265674 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '资源表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_sys_resource
-- ----------------------------
INSERT INTO `t_sys_resource` VALUES (1, 0, '系统管理', '', '系统管理', 'config', 3, 0, 0, '2019-08-01 15:16:55', '2019-08-01 15:16:55', NULL, 'admin');
INSERT INTO `t_sys_resource` VALUES (11, 1, '菜单管理', 'sys/menu', '菜单管理', 'menu', 3, 1, 0, '2019-08-08 15:47:19', '2019-08-08 15:47:19', NULL, 'admin');
INSERT INTO `t_sys_resource` VALUES (12, 1, '角色管理', 'sys/role', '角色管理', 'role', 2, 1, 0, '2018-12-17 19:11:24', '2018-12-17 19:11:25', NULL, 'admin');
INSERT INTO `t_sys_resource` VALUES (13, 1, '用户管理', 'sys/user', '用户管理', 'admin', 1, 1, 0, '2018-12-17 19:11:13', '2018-12-17 19:11:14', NULL, 'admin');
INSERT INTO `t_sys_resource` VALUES (111, 11, '列表', 'sys/menu/list', '资源列表', '', 0, 2, 0, '2018-12-13 19:32:40', '2018-12-13 19:32:40', NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (112, 11, '添加', 'sys/menu/save', '资源添加', '', 0, 2, 0, '2018-12-13 19:32:40', '2018-12-13 19:32:40', NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (113, 11, '编辑', 'sys/menu/update', '资源编辑', '', 0, 2, 0, '2018-12-13 19:32:40', '2018-12-13 19:32:40', NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (114, 11, '删除', 'sys/menu/delete', '资源删除', '', 0, 2, 0, '2018-12-13 19:32:40', '2018-12-13 19:32:40', NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (115, 11, '选择', 'sys/menu/select', '菜单选择', NULL, 4, 2, 0, '2018-12-14 16:44:29', '2018-12-14 16:44:32', NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (116, 11, '信息', 'sys/menu/info', '菜单信息', NULL, 5, 2, 0, '2018-12-14 16:45:04', '2018-12-14 16:45:06', NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (121, 12, '列表', 'sys/role/list', '角色列表', '', 0, 2, 0, '2018-12-13 19:32:40', '2018-12-13 19:32:40', NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (122, 12, '添加', 'sys/role/save', '角色添加', '', 0, 2, 0, '2018-12-13 19:32:40', '2018-12-13 19:32:40', NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (123, 12, '编辑', 'sys/role/update', '角色编辑', '', 0, 2, 0, '2018-12-13 19:32:40', '2018-12-13 19:32:40', NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (124, 12, '删除', 'sys/role/delete', '角色删除', '', 0, 2, 0, '2018-12-13 19:32:40', '2018-12-13 19:32:40', NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (125, 12, '选择', 'sys/role/select', '角色选择', NULL, 5, 2, 0, '2018-12-14 13:52:39', '2018-12-14 13:52:42', NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (126, 12, '信息', 'sys/role/info', '角色信息', NULL, 6, 2, 0, '2018-12-14 14:32:15', '2018-12-14 14:32:15', NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (131, 13, '列表', 'sys/user/list', '用户列表', '', 1, 2, 0, '2018-12-13 19:32:40', '2018-12-13 19:32:40', NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (132, 13, '添加', 'sys/user/save', '用户添加', '', 2, 2, 0, '2018-12-13 19:32:40', '2018-12-13 19:32:40', NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (133, 13, '编辑', 'sys/user/update', '用户编辑', '', 3, 2, 0, '2018-12-13 19:32:40', '2018-12-13 19:32:40', NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (134, 13, '删除', 'sys/user/delete', '用户删除', '', 4, 2, 0, '2018-12-13 19:32:40', '2018-12-13 19:32:40', NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (135, 13, '信息', 'sys/user/info', '用户信息', NULL, 5, 2, 0, '2018-12-14 14:05:39', '2018-12-14 14:05:41', NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (227, 1, '日志管理', 'sys/log', '日志管理', 'log', 8, 1, 0, '2020-11-22 16:51:28', '2020-11-22 16:51:28', NULL, 'admin');
INSERT INTO `t_sys_resource` VALUES (228, 1, 'Druid监控', 'http://localhost:8181/druid', 'Druid监控', 'sql', 10, 1, 0, '2020-11-22 16:52:33', '2020-11-22 16:52:34', NULL, 'admin');
INSERT INTO `t_sys_resource` VALUES (234, 1, '字典管理', 'sys/dic', '字典管理', 'menu', 4, 1, 0, '2018-12-17 19:12:03', '2018-12-17 19:12:04', NULL, 'admin');
INSERT INTO `t_sys_resource` VALUES (235, 234, '数据字典列表', 'sys/dic/list', '数据字典列表', '', 0, 2, 0, '2018-12-13 19:37:23', '2018-12-13 19:37:23', NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (236, 234, '添加', 'sys/dic/save', '数据字典添加', '', 1, 2, 0, '2018-12-13 19:37:03', '2018-12-13 19:37:03', NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (237, 234, '编辑', 'sys/dic/update', '数据字典编辑', '', 2, 2, 0, '2018-12-13 19:32:40', '2018-12-13 19:32:40', NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (238, 234, '删除', 'sys/dic/delete', '数据字典删除', '', 3, 2, 0, '2018-12-13 19:32:40', '2018-12-13 19:32:40', NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (239, 1, '定时任务', 'sys/schedule', NULL, 'job', 5, 1, 0, '2018-12-15 19:10:38', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (240, 239, '列表', 'sys/schedule/list', NULL, '', 1, 2, 0, '2018-12-15 19:15:10', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (241, 239, '添加', 'sys/schedule/save', NULL, '', 2, 2, 0, '2018-12-15 19:16:20', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (242, 239, '编辑', 'sys/schedule/update', '定时任务编辑', '', 3, 2, 0, '2018-12-15 19:22:18', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (243, 239, '删除', 'sys/schedule/delete', '定时任务删除', '', 4, 2, 0, '2018-12-15 19:23:09', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (244, 239, '信息', 'sys/schedule/info', '定时任务信息', '', 5, 2, 0, '2018-12-15 19:23:33', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (245, 239, '暂停', 'sys/schedule/pause', '定时任务暂停', '', 6, 2, 0, '2018-12-15 19:25:39', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (246, 239, '恢复', 'sys/schedule/resume', '定时任务恢复', '', 7, 2, 0, '2018-12-15 19:26:19', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (247, 239, '立即执行', 'sys/schedule/run', '定时任务立即执行', '', 8, 2, 0, '2018-12-15 19:27:46', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (248, 239, '日志', 'sys/schedule/log', '定时任务日志', '', 9, 2, 0, '2018-12-15 19:28:40', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (249, 0, '企业管理', '', '企业管理', 'menu', 1, 0, 0, '2018-12-17 15:23:12', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (250, 249, '企业信息', 'enterprise/enterprise', '企业信息', 'menu', 1, 1, 0, '2018-12-17 15:25:18', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (251, 250, '列表', 'enterprise/enterprise/list', '列表', '', 1, 2, 0, '2018-12-17 15:25:59', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (252, 250, '添加', 'enterprise/enterprise/save', '添加', '', 2, 2, 0, '2018-12-17 15:26:34', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (253, 250, '编辑', 'enterprise/enterprise/update', '编辑', '', 3, 2, 0, '2018-12-17 15:27:14', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (254, 250, '删除', 'enterprise/enterprise/delete', '删除', '', 4, 2, 0, '2018-12-17 15:27:35', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (255, 250, '信息', 'enterprise/enterprise/info', '信息', '', 5, 2, 0, '2018-12-17 15:28:04', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (256, 249, '企业部门管理', 'enterprise/enterpriseDepartment', '企业部门管理', 'menu', 2, 1, 0, '2020-11-22 14:23:26', '2020-11-22 14:23:26', 'admin', 'admin');
INSERT INTO `t_sys_resource` VALUES (257, 249, '企业职务管理', 'enterprise/enterpriseJob', '企业职务管理', 'menu', 3, 1, 0, '2020-11-22 14:23:42', '2020-11-22 14:23:42', 'admin', 'admin');
INSERT INTO `t_sys_resource` VALUES (258, 256, '列表', 'enterprise/enterpriseDepartment/list', '企业部门列表', '', 1, 2, 0, '2018-12-17 19:05:51', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (259, 256, '添加', 'enterprise/enterpriseDepartment/save', '企业部门添加', '', 2, 2, 0, '2018-12-17 19:06:09', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (260, 256, '编辑', 'enterprise/enterpriseDepartment/update', '企业部门编辑', '', 3, 2, 0, '2018-12-17 19:06:34', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (261, 256, '删除', 'enterprise/enterpriseDepartment/delete', '企业部门删除', '', 4, 2, 0, '2018-12-17 19:07:01', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (262, 256, '信息', 'enterprise/enterpriseDepartment/info', '企业部门信息', '', 5, 2, 0, '2018-12-17 19:07:24', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (263, 257, '列表', 'enterprise/enterpriseJob/list', '企业职务', '', 1, 2, 0, '2018-12-17 19:10:45', '2018-12-17 19:10:46', 'admin', 'admin');
INSERT INTO `t_sys_resource` VALUES (264, 257, '添加', 'enterprise/enterpriseJob/save', '企业职务添加', '', 2, 2, 0, '2018-12-17 19:08:53', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (265, 257, '编辑', 'enterprise/enterpriseJob/update', '企业职务编辑', '', 3, 2, 0, '2018-12-17 19:09:20', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (266, 257, '删除', 'enterprise/enterpriseJob/delete', '企业职务删除', '', 4, 2, 0, '2018-12-17 19:09:48', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (267, 257, '信息', 'enterprise/enterpriseJob/info', '企业职务信息', '', 5, 2, 0, '2018-12-17 19:10:15', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (268, 1, 'API接口', 'http://localhost:8190/doc.html', 'API接口', 'config', 9, 1, 0, '2020-11-22 19:06:09', '2020-11-22 19:06:09', 'admin', 'admin');
INSERT INTO `t_sys_resource` VALUES (269, 1, '系统通知', 'notice/sysNotice', '系统通知', 'tixing', 6, 1, 0, '2020-11-22 17:34:07', '2020-11-22 17:34:07', 'admin', 'admin');
INSERT INTO `t_sys_resource` VALUES (1193731450753265666, 1, '代码生成', 'generator/generator', '代码生成', 'bianji', 7, 1, 0, '2020-11-22 16:51:36', '2020-11-22 16:51:37', 'admin', 'admin');
INSERT INTO `t_sys_resource` VALUES (1193731450753265667, 269, '列表', 'notice/list', '列表', '', 1, 2, 0, '2020-11-22 16:55:21', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (1193731450753265668, 269, '添加', 'notice/save', '添加', '', 2, 2, 0, '2020-11-22 16:58:21', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (1193731450753265669, 269, '编辑', 'notice/update', '编辑', '', 3, 2, 0, '2020-11-22 16:58:55', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (1193731450753265670, 269, '删除', 'notice/delete', '删除', '', 4, 2, 0, '2020-11-22 16:59:20', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (1193731450753265671, 269, '信息', 'notice/info', '信息', '', 5, 2, 0, '2020-11-22 17:00:01', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (1193731450753265672, 269, '发布', 'notice/release', '发布', '', 6, 2, 0, '2020-11-22 17:40:40', '2020-11-22 17:40:41', 'admin', 'admin');
INSERT INTO `t_sys_resource` VALUES (1193731450753265673, 269, '撤销', 'notice/revoke', '撤销', '', 7, 2, 0, '2020-11-22 17:01:40', NULL, 'admin', NULL);

-- ----------------------------
-- Table structure for t_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role`;
CREATE TABLE `t_sys_role`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '角色名',
  `seq` tinyint NOT NULL DEFAULT 0 COMMENT '排序号',
  `description` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '简介',
  `status` tinyint NOT NULL DEFAULT 0 COMMENT '状态(0：开启，1：关闭)',
  `create_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '记录创建时间',
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '记录最后修改时间',
  `create_user_id` bigint NULL DEFAULT NULL COMMENT '记录创建者ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_sys_role
-- ----------------------------
INSERT INTO `t_sys_role` VALUES (1, 'admin', 1, '超级管理员', 0, '2020-11-22 17:44:03', '2020-11-22 17:44:04', 1);
INSERT INTO `t_sys_role` VALUES (2, '应用管理员', 2, '应用管理员', 0, '2020-11-22 17:05:58', '2020-11-22 17:05:59', 1);
INSERT INTO `t_sys_role` VALUES (3, '测试人员', 3, '测试人员', 0, '2020-11-22 17:06:30', '2020-11-22 17:06:31', 1);
INSERT INTO `t_sys_role` VALUES (4, '运维人员', 4, '运维人员', 0, '2020-11-22 17:07:29', '2020-11-22 17:07:29', 1);
INSERT INTO `t_sys_role` VALUES (5, '运营人员', 5, '运营人员', 0, '2020-11-22 17:08:15', '2020-11-22 17:08:16', 1);

-- ----------------------------
-- Table structure for t_sys_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role_resource`;
CREATE TABLE `t_sys_role_resource`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `role_id` bigint NOT NULL COMMENT '角色id',
  `resource_id` bigint NOT NULL COMMENT '资源id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_role_resource_ids`(`role_id` ASC, `resource_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1195165701516345742 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '角色资源表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_sys_role_resource
-- ----------------------------
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345694, 1, 1);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345708, 1, 11);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345701, 1, 12);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345695, 1, 13);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345709, 1, 111);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345710, 1, 112);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345711, 1, 113);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345712, 1, 114);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345713, 1, 115);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345714, 1, 116);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345702, 1, 121);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345703, 1, 122);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345704, 1, 123);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345705, 1, 124);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345706, 1, 125);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345707, 1, 126);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345696, 1, 131);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345697, 1, 132);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345698, 1, 133);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345699, 1, 134);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345700, 1, 135);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345739, 1, 227);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345741, 1, 228);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345715, 1, 234);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345716, 1, 235);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345717, 1, 236);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345718, 1, 237);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345719, 1, 238);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345720, 1, 239);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345721, 1, 240);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345722, 1, 241);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345723, 1, 242);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345724, 1, 243);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345725, 1, 244);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345726, 1, 245);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345727, 1, 246);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345728, 1, 247);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345729, 1, 248);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345675, 1, 249);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345676, 1, 250);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345677, 1, 251);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345678, 1, 252);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345679, 1, 253);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345680, 1, 254);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345681, 1, 255);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345682, 1, 256);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345688, 1, 257);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345683, 1, 258);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345684, 1, 259);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345685, 1, 260);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345686, 1, 261);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345687, 1, 262);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345689, 1, 263);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345690, 1, 264);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345691, 1, 265);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345692, 1, 266);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345693, 1, 267);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345740, 1, 268);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345730, 1, 269);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345738, 1, 1193731450753265666);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345731, 1, 1193731450753265667);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345732, 1, 1193731450753265668);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345733, 1, 1193731450753265669);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345734, 1, 1193731450753265670);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345735, 1, 1193731450753265671);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345736, 1, 1193731450753265672);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345737, 1, 1193731450753265673);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345511, 2, 1);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345478, 2, 11);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345513, 2, 12);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345512, 2, 13);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345479, 2, 111);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345480, 2, 112);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345481, 2, 113);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345482, 2, 114);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345483, 2, 115);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345484, 2, 116);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345474, 2, 121);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345475, 2, 122);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345476, 2, 125);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345477, 2, 126);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345472, 2, 131);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345473, 2, 132);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345508, 2, 227);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345510, 2, 228);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345485, 2, 234);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345486, 2, 235);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345487, 2, 236);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345488, 2, 237);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345489, 2, 238);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345490, 2, 239);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345491, 2, 240);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345492, 2, 241);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345493, 2, 242);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345494, 2, 243);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345495, 2, 244);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345496, 2, 245);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345497, 2, 246);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345498, 2, 247);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345499, 2, 248);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345453, 2, 249);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345454, 2, 250);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345455, 2, 251);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345456, 2, 252);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345457, 2, 253);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345458, 2, 254);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345459, 2, 255);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345460, 2, 256);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345466, 2, 257);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345461, 2, 258);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345462, 2, 259);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345463, 2, 260);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345464, 2, 261);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345465, 2, 262);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345467, 2, 263);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345468, 2, 264);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345469, 2, 265);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345470, 2, 266);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345471, 2, 267);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345509, 2, 268);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345500, 2, 269);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345501, 2, 1193731450753265667);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345502, 2, 1193731450753265668);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345503, 2, 1193731450753265669);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345504, 2, 1193731450753265670);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345505, 2, 1193731450753265671);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345506, 2, 1193731450753265672);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345507, 2, 1193731450753265673);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345572, 3, 1);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345575, 3, 11);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345574, 3, 12);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345573, 3, 13);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345542, 3, 111);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345543, 3, 115);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345544, 3, 116);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345537, 3, 121);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345538, 3, 122);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345539, 3, 123);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345540, 3, 125);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345541, 3, 126);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345533, 3, 131);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345534, 3, 132);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345535, 3, 133);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345536, 3, 135);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345569, 3, 227);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345571, 3, 228);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345545, 3, 234);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345546, 3, 235);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345547, 3, 236);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345548, 3, 237);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345549, 3, 238);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345550, 3, 239);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345551, 3, 240);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345552, 3, 241);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345553, 3, 242);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345554, 3, 243);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345555, 3, 244);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345556, 3, 245);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345557, 3, 246);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345558, 3, 247);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345559, 3, 248);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345514, 3, 249);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345515, 3, 250);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345516, 3, 251);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345517, 3, 252);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345518, 3, 253);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345519, 3, 254);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345520, 3, 255);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345521, 3, 256);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345527, 3, 257);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345522, 3, 258);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345523, 3, 259);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345524, 3, 260);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345525, 3, 261);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345526, 3, 262);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345528, 3, 263);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345529, 3, 264);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345530, 3, 265);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345531, 3, 266);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345532, 3, 267);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345570, 3, 268);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345560, 3, 269);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345568, 3, 1193731450753265666);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345561, 3, 1193731450753265667);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345562, 3, 1193731450753265668);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345563, 3, 1193731450753265669);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345564, 3, 1193731450753265670);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345565, 3, 1193731450753265671);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345566, 3, 1193731450753265672);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345567, 3, 1193731450753265673);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345632, 4, 1);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345635, 4, 11);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345634, 4, 12);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345633, 4, 13);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345603, 4, 111);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345604, 4, 115);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345605, 4, 116);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345599, 4, 121);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345600, 4, 122);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345601, 4, 125);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345602, 4, 126);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345595, 4, 131);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345596, 4, 132);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345597, 4, 133);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345598, 4, 135);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345629, 4, 227);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345631, 4, 228);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345606, 4, 234);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345607, 4, 235);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345608, 4, 236);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345609, 4, 237);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345610, 4, 238);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345611, 4, 239);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345612, 4, 240);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345613, 4, 241);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345614, 4, 242);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345615, 4, 243);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345616, 4, 244);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345617, 4, 245);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345618, 4, 246);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345619, 4, 247);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345620, 4, 248);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345576, 4, 249);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345577, 4, 250);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345578, 4, 251);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345579, 4, 252);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345580, 4, 253);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345581, 4, 254);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345582, 4, 255);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345583, 4, 256);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345589, 4, 257);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345584, 4, 258);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345585, 4, 259);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345586, 4, 260);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345587, 4, 261);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345588, 4, 262);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345590, 4, 263);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345591, 4, 264);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345592, 4, 265);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345593, 4, 266);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345594, 4, 267);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345630, 4, 268);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345621, 4, 269);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345622, 4, 1193731450753265667);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345623, 4, 1193731450753265668);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345624, 4, 1193731450753265669);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345625, 4, 1193731450753265670);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345626, 4, 1193731450753265671);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345627, 4, 1193731450753265672);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345628, 4, 1193731450753265673);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345672, 5, 1);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345674, 5, 12);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345673, 5, 13);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345659, 5, 121);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345660, 5, 122);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345661, 5, 123);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345662, 5, 125);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345663, 5, 126);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345655, 5, 131);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345656, 5, 132);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345657, 5, 133);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345658, 5, 135);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345636, 5, 249);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345637, 5, 250);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345638, 5, 251);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345639, 5, 252);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345640, 5, 253);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345641, 5, 254);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345642, 5, 255);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345643, 5, 256);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345649, 5, 257);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345644, 5, 258);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345645, 5, 259);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345646, 5, 260);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345647, 5, 261);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345648, 5, 262);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345650, 5, 263);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345651, 5, 264);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345652, 5, 265);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345653, 5, 266);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345654, 5, 267);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345664, 5, 269);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345665, 5, 1193731450753265667);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345666, 5, 1193731450753265668);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345667, 5, 1193731450753265669);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345668, 5, 1193731450753265670);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345669, 5, 1193731450753265671);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345670, 5, 1193731450753265672);
INSERT INTO `t_sys_role_resource` VALUES (1195165701516345671, 5, 1193731450753265673);

-- ----------------------------
-- Table structure for t_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `login_name` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '登陆名',
  `name` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '密码',
  `salt` varchar(36) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '密码加密盐',
  `sex` tinyint NOT NULL DEFAULT 0 COMMENT '性别(0:男，1：女)',
  `phone` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `user_type` tinyint NOT NULL DEFAULT 0 COMMENT '用户类别（0：超级管理员，1：企业用户，2：监管用户）',
  `status` tinyint NOT NULL DEFAULT 0 COMMENT '用户状态(0：正常，1：不正常)',
  `expired` tinyint NULL DEFAULT 0 COMMENT '过期字段（0-不过期，1-过期）',
  `enterprise_id` bigint NULL DEFAULT NULL COMMENT '所属企业',
  `department_id` bigint NULL DEFAULT NULL COMMENT '所属部门',
  `job_id` bigint NULL DEFAULT NULL COMMENT '用户职务',
  `is_leader` tinyint NULL DEFAULT NULL COMMENT '是否领导（0-是，1-否）',
  `create_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '记录创建时间',
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '记录最后修改时间',
  `create_user_id` bigint NULL DEFAULT NULL COMMENT '记录创建用户ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_user_login_name`(`login_name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_sys_user
-- ----------------------------
INSERT INTO `t_sys_user` VALUES (1, 'admin', 'admin', '4dcdca8c65b16d7d82dfbe6462fc2af9', 'b4752b4b73034de06afb2db30fe19061', 0, '13888888888', 'tuminglong@126.com', 0, 0, 0, NULL, NULL, NULL, 0, '2025-03-22 13:41:11', '2025-03-22 13:41:11', 1);
INSERT INTO `t_sys_user` VALUES (2, 'test', 'test', '0fb8fa98b83d36ddb8fd49d0de34b758', '13608cadcaf242a5b8130182ef8c8d84', 0, '13888888888', 'tuminglong@126.com', 1, 0, 0, NULL, NULL, NULL, 1, '2025-03-22 13:41:11', '2025-03-22 13:41:11', 1);
INSERT INTO `t_sys_user` VALUES (3, 'manager', 'manager', '6e0f33fd0dc901b0c2e0f16a5b683d8a', '5d0fa374af44f73972c4a75c5a746fa7', 0, '13888888888', 'tuminglong@126.com', 2, 0, 1, NULL, NULL, NULL, 1, '2025-03-22 13:41:11', '2025-03-22 13:41:11', 1);

-- ----------------------------
-- Table structure for t_sys_user_enterprise
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user_enterprise`;
CREATE TABLE `t_sys_user_enterprise`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint NULL DEFAULT NULL COMMENT '角色id',
  `enterprise_id` bigint NULL DEFAULT NULL COMMENT '企业id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id` ASC, `enterprise_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '监管用户与企业关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_sys_user_enterprise
-- ----------------------------

-- ----------------------------
-- Table structure for t_sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user_role`;
CREATE TABLE `t_sys_user_role`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `role_id` bigint NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_user_role_ids`(`user_id` ASC, `role_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2542 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '用户角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_sys_user_role
-- ----------------------------
INSERT INTO `t_sys_user_role` VALUES (2541, 1, 1);
INSERT INTO `t_sys_user_role` VALUES (2536, 2, 2);
INSERT INTO `t_sys_user_role` VALUES (2533, 3, 3);

SET FOREIGN_KEY_CHECKS = 1;
