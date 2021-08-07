package net.csdn.ac.check.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import net.csdn.ac.check.core.base.BaseObject;
import net.csdn.ac.check.entity.WorkDay;
import net.csdn.ac.check.core.utils.ResultUtil;
import net.csdn.ac.check.entity.Option;
import net.csdn.ac.check.service.OptionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 规则选项Controller
 *
 * @author xiangwang
 * @since 2021-06-18
 * @version 1.0
 */
@RestController
public class OptionController extends BaseObject {
    @Resource
    private OptionService optionService;

    /**
     * 新建规则选项
     *
     * @param title         规则选项标题
     * @param description   规则选项描述
     * @param timeType      1：星期，2：日期
     * @param week          当timeType=1时此字段有值，具体值为[0,1,2,3]
     * @param date          当timeType=2时此字段有值，具体值为”2021-06-18”
     * @param type          1：固定时间上下班；2：自由上下班；3：不上班
     * @param section       type=1时，表示时段，具体值为09:00,12:00;13:00,18:00
     * @param timeLen       type=1时，该字段无值，为null；type=2时，表示时长，具体值为1～12；type=3时，表示不上班，值为null
     * @return
     */
    @PostMapping(value = "/option/add")
    public JSONObject addOption(final String title, final String description, final Integer timeType, final String[] week, final String[] date, final Integer type, final String[] section, final Integer timeLen) {
        StringBuilder sb = new StringBuilder();
        sb.append("title=").append(title).append("&");
        sb.append("description=").append(description).append("&");
        sb.append("timeType=").append(timeType).append("&");
        sb.append("week=").append(Arrays.toString(week)).append("&");
        sb.append("date=").append(Arrays.toString(date)).append("&");
        sb.append("type=").append(type).append("&");
        sb.append("section=").append(Arrays.toString(section)).append("&");
        sb.append("timeLen=").append(timeLen);
        logger.warn("/option/add - " + sb.toString());
        if (StringUtils.isBlank(title) || null == timeType || null == type) {
            return ResultUtil.failure(10002, "缺少参数");
        }

        String optionid = null;
        if (1 == timeType) {
            if (null == week || week.length == 0) {
                return ResultUtil.failure(10002, "缺少参数");
            }
            Integer[] weekArray = new Integer[week.length];
            for (int i = 0; i < week.length; i++) {
                weekArray[i] = Integer.parseInt(week[i]);
            }
            optionid = optionService.add(title, description, timeType, weekArray, date, type, section, timeLen);
        } else {
            optionid = optionService.add(title, description, timeType, null, date, type, section, timeLen);
        }
        if (StringUtils.isNotBlank(optionid)) {
            JSONObject data = new JSONObject();
            data.put("optionid", optionid);
            return ResultUtil.content(data);
        }
        return ResultUtil.failure(10000, "新建规则选项异常");
    }

    /**
     * 规则选项列表
     *
     * @param start 起始页，从1开始
     * @param size  每页条数
     * @return
     */
    @PostMapping(value = "/option/list")
    public JSONObject list(final Integer start, final Integer size) {
        StringBuilder sb = new StringBuilder();
        sb.append("start=").append(start).append("&");
        sb.append("size=").append(size);
        logger.warn("/option/list - " + sb.toString());
        if (null == start || null == size || 0 == start || 0 == size) {
            return ResultUtil.failure(10002, "缺少参数");
        }

        // 得到规则数量
        Integer count = optionService.count();
        JSONObject data = new JSONObject();
        if (null == count) {
            count = 0;
        }
        List<Option> list = optionService.queryList(start, size);
        if (null == list || 0 == list.size()) {
            data.put("list", new ArrayList<Option>());
        } else {
            JSONArray items = new JSONArray();
            for (Option option : list) {
                JSONObject optionJson = JSON.parseObject(option.toString());
                boolean flag = optionService.deleteable(option.getId());
                if (flag) {
                    optionJson.put("used", 0);
                } else {
                    optionJson.put("used", 1);
                }
                items.add(optionJson);
            }
            data.put("list", items);
        }
        data.put("count", count);
        return ResultUtil.content(data);
    }

    /**
     * 规则选项查询
     *
     * @param start 起始页，从1开始
     * @param size  每页条数
     * @return
     */
    @PostMapping(value = "/option/search")
    public JSONObject search(final String title, final Integer type, final Integer start, final Integer size) {
        StringBuilder sb = new StringBuilder();
        sb.append("title=").append(title).append("&");
        sb.append("type=").append(type).append("&");
        sb.append("start=").append(start).append("&");
        sb.append("size=").append(size);
        logger.warn("/option/search - " + sb.toString());
        if (null == start || null == size || 0 == start || 0 == size) {
            return ResultUtil.failure(10002, "缺少参数");
        }

        // 得到规则数量
        Integer count = optionService.count(title, type);
        JSONObject data = new JSONObject();
        List<Option> list = optionService.queryList(title, type, start, size);
        if (null == count) {
            count = 0;
        }
        if (null == list || 0 == list.size()) {
            data.put("list", new ArrayList<Option>());
        } else {
            JSONArray items = new JSONArray();
            for (Option option : list) {
                JSONObject optionJson = JSON.parseObject(option.toString());
                boolean flag = optionService.deleteable(option.getId());
                if (flag) {
                    optionJson.put("used", 0);
                } else {
                    optionJson.put("used", 1);
                }
                items.add(optionJson);
            }
            data.put("list", items);
        }
        data.put("count", count);
        return ResultUtil.content(data);
    }

    /**
     * 规则选项详情
     *
     * @param optionId
     * @return
     */
    @PostMapping(value = "/option/details")
    public JSONObject details(final String optionId) {
        StringBuilder sb = new StringBuilder();
        sb.append("option_id=").append(optionId);
        logger.warn("/option/details - " + sb.toString());
        if (StringUtils.isBlank(optionId)) {
            return ResultUtil.failure(10002, "缺少参数");
        }

        Option option = optionService.queryOne(optionId);
        if (null != option) {
            int type = option.getType();
            List<WorkDay> list = null;
            // 1：星期，关联weekday表查询
            if (1 == type) {
                list = optionService.WeekdayDetails(optionId);
            }
            // 2：日期，关联dateday表查询
            if (2 == type) {
                list = optionService.DatedayDetails(optionId);
            }
            JSONObject data = new JSONObject();
            data.put("option", option);
            data.put("list", list);
            return ResultUtil.content(data);
        }
        return ResultUtil.failure(10000, "未找到规则详情");
    }

    /**
     * 删除规则选项
     *
     * @param optionId        规则选项id
     * @return
     */
    @PostMapping(value = "/option/remove")
    public JSONObject remove(final String optionId) {
        StringBuilder sb = new StringBuilder();
        sb.append("id=").append(optionId);
        logger.warn("/option/remove - " + sb.toString());
        if (StringUtils.isBlank(optionId)) {
            return ResultUtil.failure(10002, "缺少参数");
        }

        // 判断是否可删除
        boolean flag = optionService.deleteable(optionId);
        if (!flag) {
            return ResultUtil.failure(10000, "不允许删除规则选项");
        }

        flag = optionService.remove(optionId);
        if (flag) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(10000, "删除规则选项异常");
    }

    /**
     * 批量删除规则选项
     *
     * @param optionId        规则选项id数组
     * @return
     */
    @PostMapping(value = "/option/batchRemove")
    public JSONObject batchRemove(final String[] optionId) {
        StringBuilder sb = new StringBuilder();
        sb.append("id=").append(Arrays.toString(optionId));
        logger.warn("/option/batchRemove - " + sb.toString());
        if (null == optionId) {
            return ResultUtil.failure(10002, "缺少参数");
        }

        // 判断是否可删除
        boolean flag = optionService.deleteable(optionId);
        if (!flag) {
            return ResultUtil.failure(10000, "不允许删除规则选项");
        }

        flag = optionService.batchRemove(optionId);
        if (flag) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(10000, "批量删除规则选项异常");
    }
}
