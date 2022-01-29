-- 不存在表则创建 员工五险一金申报表
CREATE TABLE IF NOT EXISTS `user_fund_declare`
(
    `id`                    int NOT NULL COMMENT '工号',
    `name`                  varchar(6) NOT NULL COMMENT '姓名',
    `dept`                  varchar(10)                                                 NOT NULL COMMENT '部门',
    `base_number`           decimal(10, 2) unsigned NOT NULL DEFAULT '0.00' COMMENT '基数',
    `fund_ratio`            decimal(10, 2) unsigned NOT NULL DEFAULT '0.00' COMMENT '公积金比例',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT '员工五险一金申报表';