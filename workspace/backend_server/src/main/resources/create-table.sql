CREATE TABLE IF NOT EXISTS `${tableName}`
(
    `_id`           varchar(255) NOT NULL COMMENT '主键',
    `created_time` datetime(6) DEFAULT NULL COMMENT '创建时间',
    `updated_time` datetime(6) DEFAULT NULL COMMENT '更新时间',
    `created_by`   varchar(255) DEFAULT NULL COMMENT '创建者',
    `updated_by`   varchar(255) DEFAULT NULL COMMENT '更新者',
    PRIMARY KEY (`_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8  COMMENT='${tableAlias}';