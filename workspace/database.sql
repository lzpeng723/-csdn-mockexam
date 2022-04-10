create database `content` character set utf8mb4 collate utf8mb4_general_ci;

use content;

-- ----------------------------
-- Table structure for `data_field`
-- ----------------------------
DROP TABLE IF EXISTS `data_field`;
CREATE TABLE `data_field` (
  `field_guid` varchar(32) NOT NULL,
  `field` varchar(255) NOT NULL COMMENT '字段名 ',
  `field_name` varchar(255) NOT NULL COMMENT '字段别名',
  `type` varchar(32) NOT NULL COMMENT '字段类型',
  `required` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否必填 1必填 2非必填',
  `field_length` int(11) DEFAULT NULL COMMENT '字段长度',
  `table_guid` varchar(32) NOT NULL COMMENT 'mode id',
  `created_time` varchar(255) DEFAULT NULL,
  `input_type` varchar(32) DEFAULT NULL COMMENT '输入框类型',
  `placeholder` text COMMENT '输入提示',
  `is_form` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否出现在表单 1是 2否',
  `allow_empty` tinyint(1) NOT NULL DEFAULT '2' COMMENT '1 允许为空 2 不允许为空',
  `option` text COMMENT '选项列表 ## 隔开不同选项',
  PRIMARY KEY (`field_guid`) USING BTREE,
  UNIQUE KEY `table_field_key` (`table_guid`, `field`) USING BTREE,
  KEY `field_name` (`field_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='字段表';

-- ----------------------------
-- Records of data_field
-- ----------------------------

-- ----------------------------
-- Table structure for `data_table`
-- ----------------------------
DROP TABLE IF EXISTS `data_table`;
CREATE TABLE `data_table` (
  `table_guid` varchar(32) NOT NULL,
  `table_alias` varchar(255) NOT NULL DEFAULT '' COMMENT '表别名',
  `table_name` varchar(255) NOT NULL DEFAULT '' COMMENT '表名',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态 1启用 2禁用',
  `created_time` varchar(255) DEFAULT NULL,
  `describe` text COMMENT '描述',
  PRIMARY KEY (`table_guid`) USING BTREE,
  UNIQUE KEY `table_name` (`table_name`) USING BTREE,
  KEY `mode_name` (`table_alias`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='模型表';

-- ----------------------------
-- Records of data_table
-- ----------------------------
