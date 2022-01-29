package csdn.c4;

import cn.hutool.core.io.IoUtil;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

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

    static {
        initTable();
        initData();
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
        List<Entity> salaryEntityList = initSalaryData();
        List<Entity> fundDeclareEntityList = initFundDeclareData();
        DB.tx(db -> {
            db.execute("DELETE FROM user_salary;");
            db.insert(salaryEntityList);
            db.execute("DELETE FROM user_fund_declare;");
            db.insert(fundDeclareEntityList);
            db.execute("DELETE FROM user_final_salary;");
        });
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
