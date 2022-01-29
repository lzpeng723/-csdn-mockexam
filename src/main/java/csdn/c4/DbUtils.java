package csdn.c4;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

/**
 * 数据库工具类
 *
 * @author lzpeng
 * @version 1.0
 * @date 2022/1/29 13:24
 */
@Slf4j
public class DbUtils {

    /**
     * 获取数据库连接信息
     */
    private static final Db DB = Db.use();

    /**
     * 公司
     */
    private static String COMPANY = "company_ratio";
    /**
     * 个人
     */
    private static String USER = "user_ratio";

    /**
     * 五险数据缓存
     */
    private static List<Entity> insuranceRatioEntityList;
    /**
     * 缴费基数缓存
     */
    private static Map<Integer, BigDecimal> userBaseNumberMap;
    /**
     * 公积金缴费比例缓存
     */
    private static Map<Integer, BigDecimal> userFundRatioMap;

    static {
        // 创建表
        initTable();
        // 初始化数据
        initData();
        // 初始化成员变量
        initField();
        // 计算数据
        calculateData();
    }

    /**
     * 初始化成员变量
     */
    @SneakyThrows
    private static void initField() {
        insuranceRatioEntityList = DB.query("SELECT * FROM insurance_ratio");
        List<Entity> fundDeclareEntityList = DB.query("SELECT * FROM user_fund_declare");
        userBaseNumberMap = new HashMap<>();
        userFundRatioMap = new HashMap<>();
        for (Entity fundDeclareEntity : fundDeclareEntityList) {
            Integer userId = fundDeclareEntity.getInt("id");
            userBaseNumberMap.put(userId, fundDeclareEntity.getBigDecimal("base_number"));
            userFundRatioMap.put(userId, fundDeclareEntity.getBigDecimal("fund_ratio"));
        }
    }

    /**
     * 计算数据
     */
    @SneakyThrows
    private static void calculateData() {
        List<Entity> userFinalSalaryEntityList = new ArrayList<>();
        List<Entity> salaryEntityList = DB.query("SELECT * FROM user_salary");
        for (Entity salaryEntity : salaryEntityList) {
            Entity userFinalSalaryEntity = Entity.create("user_final_salary");
            // 人员 月份固定变量
            Integer month = salaryEntity.getInt("month");
            Integer userId = salaryEntity.getInt("id");
            String name = salaryEntity.getStr("name");
            String dept = salaryEntity.getStr("dept");
            userFinalSalaryEntity.set("month", month);
            userFinalSalaryEntity.set("id", userId);
            userFinalSalaryEntity.set("name", name);
            userFinalSalaryEntity.set("dept", dept);
            // 正收益
            // 底薪
            BigDecimal baseSalary = salaryEntity.getBigDecimal("base_salary");
            // 岗位工资
            BigDecimal postSalary = salaryEntity.getBigDecimal("post_salary");
            // 绩效奖金
            BigDecimal achievementBonus = salaryEntity.getBigDecimal("achievement_bonus");
            // 交通补助
            BigDecimal trafficSubsidy = salaryEntity.getBigDecimal("traffic_subsidy");
            // 通信补助
            BigDecimal phoneSubsidy = salaryEntity.getBigDecimal("phone_subsidy");
            // 负收益
            // 违规处罚
            BigDecimal punishmentViolation = salaryEntity.getBigDecimal("punishment_violation");
            // 考勤扣除
            BigDecimal achievementDeduction = salaryEntity.getBigDecimal("achievement_deduction");
            // 工资
            BigDecimal salary = NumberUtil.add(baseSalary, postSalary, achievementBonus, trafficSubsidy, phoneSubsidy);
            userFinalSalaryEntity.set("user_salary", salary);
            // 扣款
            BigDecimal deduction = NumberUtil.add(punishmentViolation, achievementDeduction);
            userFinalSalaryEntity.set("user_deduction", deduction);
            // 应发工资
            BigDecimal shouldSalary = NumberUtil.sub(salary, deduction);
            userFinalSalaryEntity.set("should_salary", shouldSalary);
            // 缴费基数
            BigDecimal baseNumber = userBaseNumberMap.getOrDefault(userId, BigDecimal.ZERO);
            // 公积金比例
            BigDecimal fundRatio = userFundRatioMap.getOrDefault(userId, BigDecimal.ZERO);
            // 一斤 个人 单位
            BigDecimal fund = NumberUtil.mul(baseNumber, fundRatio);
            // 五险 个人
            BigDecimal userEndowmentInsurance = NumberUtil.mul(baseNumber, getRatio("endowment_insurance", USER));
            userFinalSalaryEntity.set("user_endowment_insurance", userEndowmentInsurance);
            BigDecimal userMedicalInsurance = NumberUtil.mul(baseNumber, getRatio("medical_insurance", USER));
            userFinalSalaryEntity.set("user_medical_insurance", userMedicalInsurance);
            BigDecimal userUnemploymentInsurance = NumberUtil.mul(baseNumber, getRatio("unemployment_insurance", USER));
            userFinalSalaryEntity.set("user_unemployment_insurance", userUnemploymentInsurance);
            BigDecimal userEmploymentInjuryInsurance = NumberUtil.mul(baseNumber, getRatio("employment_injury_insurance", USER));
            userFinalSalaryEntity.set("user_employment_injury_insurance", userEmploymentInjuryInsurance);
            BigDecimal userMaternityInsurance = NumberUtil.mul(baseNumber, getRatio("maternity_insurance", USER));
            userFinalSalaryEntity.set("user_maternity_insurance", userMaternityInsurance);
            // 一金 个人
            userFinalSalaryEntity.set("user_housing_provident_fund", fund);
            BigDecimal userTotal = NumberUtil.add(userEndowmentInsurance, userMedicalInsurance, userUnemploymentInsurance, userEmploymentInjuryInsurance, userMaternityInsurance, fund);
            userFinalSalaryEntity.set("user_total", userTotal);
            // 五险 公司
            BigDecimal companyEndowmentInsurance = NumberUtil.mul(baseNumber, getRatio("endowment_insurance", COMPANY));
            userFinalSalaryEntity.set("company_endowment_insurance", companyEndowmentInsurance);
            BigDecimal companyMedicalInsurance = NumberUtil.mul(baseNumber, getRatio("medical_insurance", COMPANY));
            userFinalSalaryEntity.set("company_medical_insurance", companyMedicalInsurance);
            BigDecimal companyUnemploymentInsurance = NumberUtil.mul(baseNumber, getRatio("unemployment_insurance", COMPANY));
            userFinalSalaryEntity.set("company_unemployment_insurance", companyUnemploymentInsurance);
            BigDecimal companyEmploymentInjuryInsurance = NumberUtil.mul(baseNumber, getRatio("employment_injury_insurance", COMPANY));
            userFinalSalaryEntity.set("company_employment_injury_insurance", companyEmploymentInjuryInsurance);
            BigDecimal companyMaternityInsurance = NumberUtil.mul(baseNumber, getRatio("maternity_insurance", COMPANY));
            userFinalSalaryEntity.set("company_maternity_insurance", companyMaternityInsurance);
            // 一金 单位
            userFinalSalaryEntity.set("company_housing_provident_fund", fund);
            //  企业缴纳五险一金
            BigDecimal companyTotal = NumberUtil.add(companyEndowmentInsurance, companyMedicalInsurance, companyUnemploymentInsurance, companyEmploymentInjuryInsurance, companyMaternityInsurance, fund);
            userFinalSalaryEntity.set("company_total", companyTotal);
            // 企业支出成本
            BigDecimal companyCost = NumberUtil.add(shouldSalary, companyTotal);
            userFinalSalaryEntity.set("company_cost", companyCost);
            userFinalSalaryEntityList.add(userFinalSalaryEntity);
        }
        DB.tx(db -> {
            db.execute("DELETE FROM user_final_salary;");
            db.insert(userFinalSalaryEntityList);
        });

    }

    /**
     * 获得缴纳比例
     *
     * @param number 保险编码
     * @param type   类型 1 公司 0 个人
     * @return
     */
    private static BigDecimal getRatio(String number, String type) {
        return insuranceRatioEntityList.stream()
                .filter(o -> Objects.equals(o.getStr("number"), number))
                .map(o -> o.getBigDecimal(type))
                .findFirst()
                .orElse(BigDecimal.ZERO);
    }

    /**
     * 初始化表结构
     */
    private static void initTable() {
        try (InputStream is = DbUtils.class.getClassLoader().getResourceAsStream("init")) {
            String filesStr = IoUtil.readUtf8(is);
            String[] fileNames = filesStr.split("\r?\n");
            for (String fileName : fileNames) {
                try (InputStream fileInput = DbUtils.class.getClassLoader().getResourceAsStream("init/" + fileName)) {
                    String sql = IoUtil.readUtf8(fileInput);
                    DB.execute(sql);
                } catch (IOException | SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接信息
     *
     * @return
     */
    public static Db getDB() {
        return DB;
    }

    /**
     * 初始化数据
     */
    @SneakyThrows
    private static void initData() {
        List<Entity> insuranceEntityList = initInsuranceData();
        List<Entity> fundDeclareEntityList = initFundDeclareData();
        List<Entity> salaryEntityList = initSalaryData();
        DB.tx(db -> {
            db.execute("DELETE FROM insurance_ratio;");
            db.insert(insuranceEntityList);
            db.execute("DELETE FROM user_fund_declare;");
            db.insert(fundDeclareEntityList);
            db.execute("DELETE FROM user_salary;");
            db.insert(salaryEntityList);
            db.execute("DELETE FROM user_final_salary;");
        });
    }

    /**
     * 初始五险数据
     *
     * @return
     */
    private static List<Entity> initInsuranceData() {
        String userDir = System.getProperty("user.dir");
        File insuranceFile = new File(userDir, "workspace/data/五险缴纳比例.xlsx");
        ExcelReader reader = ExcelUtil.getReader(insuranceFile);
        List<Map<String, Object>> maps = reader.readAll();
        List<Entity> insuranceEntityList = new ArrayList<>();
        for (Map<String, Object> map : maps) {
            //保险比例配置表
            Entity insuranceEntity = Entity.create("insurance_ratio");
            insuranceEntity.set("number", map.get("编码"));
            insuranceEntity.set("name", map.get("名称"));
            insuranceEntity.set("company_ratio", map.get("公司比例"));
            insuranceEntity.set("user_ratio", map.get("个人比例"));
            insuranceEntityList.add(insuranceEntity);
        }
        return insuranceEntityList;
    }

    /**
     * 初始化员工公积金基数及比例
     */
    private static List<Entity> initFundDeclareData() {
        String userDir = System.getProperty("user.dir");
        File fundFile = new File(userDir, "workspace/data/员工五险一金申报表.xlsx");
        ExcelReader reader = ExcelUtil.getReader(fundFile);
        List<Map<String, Object>> maps = reader.readAll();
        List<Entity> fundDeclareEntityList = new ArrayList<>();
        for (Map<String, Object> map : maps) {
            //员工五险一金申报表
            Entity fundDeclareEntity = Entity.create("user_fund_declare");
            // 工号
            fundDeclareEntity.set("id", map.get("工号"));
            // 姓名
            fundDeclareEntity.set("name", map.get("姓名"));
            // 部门
            fundDeclareEntity.set("dept", map.get("部门"));
            // 基数
            fundDeclareEntity.set("base_number", map.get("基数"));
            // 公积金比例
            fundDeclareEntity.set("fund_ratio", map.get("公积金"));
            fundDeclareEntityList.add(fundDeclareEntity);
        }
        return fundDeclareEntityList;
    }

    /**
     * 初始化用户工资考勤数据
     */
    private static List<Entity> initSalaryData() {
        String userDir = System.getProperty("user.dir");
        File dir = new File(userDir, "workspace/data");
        File[] monthFiles = dir.listFiles();
        List<Entity> salaryEntityList = new ArrayList<>();
        for (File monthFile : monthFiles) {
            if (!monthFile.exists()) {
                continue;
            }
            if (monthFile.isFile()) {
                continue;
            }
            String name = monthFile.getName();
            int month = Integer.parseInt(name.substring(0, name.length() - 1));
            File[] salaryFiles = monthFile.listFiles();
            for (File salaryFile : salaryFiles) {
                ExcelReader reader = ExcelUtil.getReader(salaryFile);
                List<Map<String, Object>> maps = reader.readAll();
                for (Map<String, Object> map : maps) {
                    // 薪酬表
                    Entity salaryEntity = Entity.create("user_salary");
                    // 月份
                    salaryEntity.set("month", month);
                    // 工号
                    salaryEntity.set("id", map.get("工号"));
                    // 姓名
                    salaryEntity.set("name", map.get("姓名"));
                    // 部门
                    salaryEntity.set("dept", map.get("部门"));
                    // 底薪
                    salaryEntity.set("base_salary", map.get("底薪"));
                    // 岗位工资
                    salaryEntity.set("post_salary", map.get("岗位工资"));
                    // 绩效奖金
                    salaryEntity.set("achievement_bonus", map.get("绩效奖金"));
                    // 违规处罚
                    salaryEntity.set("punishment_violation", map.get("违规处罚"));
                    // 交通补助
                    salaryEntity.set("traffic_subsidy", map.get("交通补助"));
                    // 通信补助
                    salaryEntity.set("phone_subsidy", map.get("通信补助"));
                    // 考勤扣除
                    salaryEntity.set("achievement_deduction", map.get("考勤扣除"));
                    salaryEntityList.add(salaryEntity);
                }
            }
        }
        return salaryEntityList;
    }


}
