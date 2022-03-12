package net.csdn.ac.check.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import net.csdn.ac.check.core.utils.ResultUtil;
import net.csdn.ac.check.param.CheckRecordParam;
import net.csdn.ac.check.service.AddressService;
import net.csdn.ac.check.service.AnalyseService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 月度汇总 Controller
 *
 * @author lzpeng
 * @version 1.0
 * @date 2022/3/5 0:02
 */
@RestController
public class AnalyseController {

    @Resource
    private AnalyseService analyseService;

    /**
     * 根据给定筛选条件进行搜索
     *
     * @param checkRecordParam 搜索条件
     * @return
     */
    @PostMapping("/analyse/month/search")
    public JSONObject monthSearch(@Valid CheckRecordParam checkRecordParam) {
        return ResultUtil.content(analyseService.monthSearch(checkRecordParam));
    }

}
