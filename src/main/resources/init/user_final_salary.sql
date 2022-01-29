-- 不存在表则创建 企业员工月度工资成本支付表
CREATE TABLE IF NOT EXISTS `user_final_salary`
(
    `month`                 int NOT NULL COMMENT '月份',
    `id`                    int NOT NULL COMMENT '工号',
    `name`                  varchar(6) NOT NULL COMMENT '姓名',
    `dept`                  varchar(10)                                                 NOT NULL COMMENT '部门',
    `user_salary`           decimal(10, 2) unsigned NOT NULL DEFAULT '0.00' COMMENT '工资',
    `user_deduction`           decimal(10, 2) unsigned NOT NULL DEFAULT '0.00' COMMENT '扣款',
    `user_endowment_insurance`     decimal(10, 2) unsigned NOT NULL DEFAULT '0.00' COMMENT '养老(个人)',
    `user_medical_insurance`  decimal(10, 2) unsigned NOT NULL DEFAULT '0.00' COMMENT '医疗(个人)',
    `user_unemployment_insurance`       decimal(10, 2) unsigned NOT NULL DEFAULT '0.00' COMMENT '失业(个人)',
    `user_employment_injury_insurance`         decimal(10, 2) unsigned NOT NULL DEFAULT '0.00' COMMENT '工伤(个人)',
    `user_maternity_insurance` decimal(10, 2) unsigned NOT NULL DEFAULT '0.00' COMMENT '生育(个人)',
    `user_housing_provident_fund`           decimal(10, 2) unsigned NOT NULL DEFAULT '0.00' COMMENT '公积金(个人)',
    `user_total`           decimal(10, 2) unsigned NOT NULL DEFAULT '0.00' COMMENT '合计(个人)',
    `company_endowment_insurance`     decimal(10, 2) unsigned NOT NULL DEFAULT '0.00' COMMENT '养老(企业)',
    `company_medical_insurance`  decimal(10, 2) unsigned NOT NULL DEFAULT '0.00' COMMENT '医疗(企业)',
    `company_unemployment_insurance`       decimal(10, 2) unsigned NOT NULL DEFAULT '0.00' COMMENT '失业(企业)',
    `company_employment_injury_insurance`         decimal(10, 2) unsigned NOT NULL DEFAULT '0.00' COMMENT '工伤(企业)',
    `company_maternity_insurance` decimal(10, 2) unsigned NOT NULL DEFAULT '0.00' COMMENT '生育(企业)',
    `company_housing_provident_fund`           decimal(10, 2) unsigned NOT NULL DEFAULT '0.00' COMMENT '公积金(企业)',
    `company_total`           decimal(10, 2) unsigned NOT NULL DEFAULT '0.00' COMMENT '合计(企业)',
    `user_tax`     decimal(10, 2) unsigned NOT NULL DEFAULT '0.00' COMMENT '个税金额',
    `should_salary`  decimal(10, 2) unsigned NOT NULL DEFAULT '0.00' COMMENT '应发工资',
    `actual_salary`       decimal(10, 2) unsigned NOT NULL DEFAULT '0.00' COMMENT '实发工资',
    `company_cost`         decimal(10, 2) unsigned NOT NULL DEFAULT '0.00' COMMENT '企业支出成本',
    PRIMARY KEY (`id`,`month`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT '企业员工月度工资成本支付表';