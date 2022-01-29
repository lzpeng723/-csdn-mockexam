-- 不存在表则创建 薪酬表
CREATE TABLE IF NOT EXISTS `user_salary`
(
    `month`                 int                                                         NOT NULL COMMENT '月份',
    `id`                    int                                                         NOT NULL COMMENT '工号',
    `name`                  varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '姓名',
    `dept`                  varchar(10)                                                 NOT NULL COMMENT '部门',
    `base_salary`           decimal(10, 2) unsigned NOT NULL DEFAULT '0.00' COMMENT '底薪',
    `post_salary`           decimal(10, 2) unsigned NOT NULL DEFAULT '0.00' COMMENT '岗位工资',
    `achievement_bonus`     decimal(10, 2) unsigned NOT NULL DEFAULT '0.00' COMMENT '绩效奖金',
    `punishment_violation`  decimal(10, 2) unsigned NOT NULL DEFAULT '0.00' COMMENT '违规处罚',
    `traffic_subsidy`       decimal(10, 2) unsigned NOT NULL DEFAULT '0.00' COMMENT '交通补助',
    `phone_subsidy`         decimal(10, 2) unsigned NOT NULL DEFAULT '0.00' COMMENT '通信补助',
    `achievement_deduction` decimal(10, 2) unsigned NOT NULL DEFAULT '0.00' COMMENT '考勤扣除',
    PRIMARY KEY (`id`,`month`) USING BTREE
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT '薪酬表';