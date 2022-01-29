-- 不存在表则创建 保险配置表
CREATE TABLE IF NOT EXISTS `insurance_ratio`
(
    `number`                varchar(32) NOT NULL COMMENT '编码',
    `name`                  varchar(32) NOT NULL COMMENT '名称',
    `company_ratio`         decimal(10, 4) unsigned NOT NULL DEFAULT '0.00' COMMENT '公司比例',
    `user_ratio`            decimal(10, 4) unsigned NOT NULL DEFAULT '0.00' COMMENT '个人比例',
    PRIMARY KEY (`number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT '保险配置表';