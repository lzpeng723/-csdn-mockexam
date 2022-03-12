package net.csdn.ac.check.service;

import com.alibaba.fastjson.JSONObject;
import net.csdn.ac.check.param.CheckRecordParam;

/**
 * 月度汇总 Service 接口
 *
 * @author lzpeng
 * @version 1.0
 * @date 2022/3/5 0:24
 */
public interface AnalyseService {

    /**
     * 根据给定筛选条件进行搜索
     *
     * @param checkRecordParam 搜索条件
     * @return
     */
    JSONObject monthSearch(CheckRecordParam checkRecordParam);

}
