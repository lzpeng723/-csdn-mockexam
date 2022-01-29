package csdn.c4;

import cn.hutool.db.Entity;
import csdn.c4.dao.DbUtil;

import java.util.List;

/**
 * 主类
 *
 * @author lzpeng
 * @version 1.0
 * @date 2022/1/29 17:37
 */
public class Main {

    public static void main(String[] args) {
        // 创建表
        DbUtil.initTable();
        // 初始化数据
        DbUtil.initData();
        // 初始化成员变量
        DbUtil.initField();
        // 计算五险一金数据
        List<Entity> userFinalSalaryEntityList = DbUtil.calculateInsuranceFundData();
        // 计算个人所得税数据
        DbUtil.calculateTaxData(userFinalSalaryEntityList);
        // 生成 Excel
        DbUtil.generateExcel(10);
    }

}
