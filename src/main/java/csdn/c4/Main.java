package csdn.c4;

import cn.hutool.db.Entity;
import cn.hutool.http.HttpUtil;
import csdn.c4.dao.DbUtil;

import java.io.File;
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
        File file = DbUtil.generateExcel(10);
        HttpUtil.createServer(8080)
                .addAction("/", (req, res) -> {
                    res.write(file);
                }).start();
        System.out.println("浏览器访问 ip:8080 下载最终文件");
    }

}
