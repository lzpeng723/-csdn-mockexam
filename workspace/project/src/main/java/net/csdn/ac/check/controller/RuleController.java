package net.csdn.ac.check.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import net.csdn.ac.check.core.base.BaseObject;
import net.csdn.ac.check.entity.Address;
import net.csdn.ac.check.entity.Option;
import net.csdn.ac.check.entity.Rule;
import net.csdn.ac.check.entity.User;
import net.csdn.ac.check.core.utils.ResultUtil;
import net.csdn.ac.check.service.RuleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 规则Controller
 *
 * @author xiangwang
 * @since 2021-06-18
 * @version 1.0
 */
@RestController
public class RuleController extends BaseObject {
    @Resource
    private RuleService ruleService;

    /**
     * 新建规则
     *
     * @param title         规则标题
     * @param description   规则描述
     * @param optionId      规则选项id数组
     * @param addressId     打卡地点id数组
     * @return
     */
    @PostMapping(value = "/rule/add")
    public JSONObject add(final String title, final String description, final String[] optionId, final String[] addressId) {
        StringBuilder sb = new StringBuilder();
        sb.append("title=").append(title).append("&");
        sb.append("description=").append(description).append("&");
        sb.append("optionId[]=").append(Arrays.toString(optionId)).append("&");
        sb.append("addressId=").append(Arrays.toString(addressId));
        logger.warn("/rule/add - " + sb.toString());
        if (StringUtils.isBlank(title) || null == addressId || null == optionId) {
            return ResultUtil.failure(10002, "缺少参数");
        }

        Integer[] addressArray = new Integer[addressId.length];
        for (int i = 0; i < addressId.length; i++) {
            addressArray[i] = Integer.parseInt(addressId[i]);
        }

        boolean flag = ruleService.add(title, description, optionId, addressArray);
        if (flag) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(10000, "新建规则异常");
    }

    /**
     * 编辑规则
     *
     * @param ruleId        规则id
     * @return
     */
    @PostMapping(value = "/rule/edit")
    public JSONObject edit(final String ruleId) {
        StringBuilder sb = new StringBuilder();
        sb.append("ruleId=").append(ruleId);
        logger.warn("/rule/edit - " + sb.toString());
        if (StringUtils.isBlank(ruleId)) {
            return ResultUtil.failure(10002, "缺少参数");
        }

        Rule rule = ruleService.queryRule(ruleId);
        if (null != rule) {
            JSONObject data = new JSONObject();
            JSONObject ruleData = JSON.parseObject(rule.toString());
            data.put("ruleData", ruleData);
            List<Address> addrs = ruleService.queryAddressList(ruleId);
            if (null != addrs && addrs.size() > 0) {
                data.put("addrList", addrs);
            } else {
                data.put("addrList", new ArrayList<>());
            }

            List<Option> options = ruleService.queryOptionList(ruleId);
            if (null != options && options.size() > 0) {
                List<String> titles = new ArrayList<>();
                for (Option option : options) {
                    titles.add(option.getId());
                }
                data.put("optionList", titles);
            } else {
                data.put("optionList", new ArrayList<>());
            }
            return ResultUtil.content(data);
        }
        return ResultUtil.failure(10000, "编辑规则异常");
    }

    /**
     * 更新规则
     *
     * @param title         规则标题
     * @param description   规则描述
     * @param optionId      规则选项id数组
     * @param addressId     打卡地点id数组
     * @return
     */
    @PostMapping(value = "/rule/update")
    public JSONObject update(final String ruleId, final String title, final String description, final String[] optionId, final String[] addressId) {
        StringBuilder sb = new StringBuilder();
        sb.append("ruleId=").append(ruleId).append("&");
        sb.append("title=").append(title).append("&");
        sb.append("description=").append(description).append("&");
        sb.append("optionId=").append(Arrays.toString(optionId)).append("&");
        sb.append("addressId=").append(Arrays.toString(addressId));
        logger.warn("/rule/update - " + sb.toString());
        if (StringUtils.isBlank(ruleId) || StringUtils.isBlank(title) || null == addressId || null == optionId) {
            return ResultUtil.failure(10002, "缺少参数");
        }

        Integer[] addressArray = new Integer[addressId.length];
        for (int i = 0; i < addressId.length; i++) {
            addressArray[i] = Integer.parseInt(addressId[i]);
        }

        boolean flag = ruleService.update(ruleId, title, description, optionId, addressArray);
        if (flag) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(10000, "编辑规则异常");
    }

    /**
     * 规则列表
     *
     * @param start         起始页，从1开始
     * @param size          每页条数
     * @return
     */
    @PostMapping(value = "/rule/list")
    public JSONObject list(final Integer start, final Integer size) {
        StringBuilder sb = new StringBuilder();
        sb.append("start=").append(start).append("&");
        sb.append("size=").append(size);
        logger.warn("/rule/list - " + sb.toString());
        if (null == start || null == size || 0 == start || 0 == size) {
            return ResultUtil.failure(10002, "缺少参数");
        }

        JSONObject data = new JSONObject();
        // 得到规则数量
        Integer count = ruleService.count();
        if (null == count) {
            count = 0;
        }
        List<Rule> list = ruleService.queryList(start, size);
        if (null == list || 0 == list.size()) {
            data.put("list", new ArrayList<Rule>());
        } else {
            JSONArray items = new JSONArray();
            for (Rule rule : list) {
                JSONObject ruleAddressJSON = JSONObject.parseObject(rule.toString());
                List<Address> addrs = ruleService.queryAddressList(rule.getId());
                List<String> titles = new ArrayList<>();
                for (Address addr : addrs) {
                    titles.add(addr.getTitle());
                }
                ruleAddressJSON.put("addrtitle", titles);
                items.add(ruleAddressJSON);
            }
            data.put("list", items);
        }
        data.put("count", count);
        return ResultUtil.content(data);
    }

    /**
     * 规则查询
     *
     * @param start         起始页，从1开始
     * @param size          每页条数
     * @return
     */
    @PostMapping(value = "/rule/search")
    public JSONObject search(final String title, final Integer start, final Integer size) {
        StringBuilder sb = new StringBuilder();
        sb.append("title=").append(title).append("&");
        sb.append("start=").append(start).append("&");
        sb.append("size=").append(size);
        logger.warn("/rule/search - " + sb.toString());
        if (null == start || null == size || 0 == start || 0 == size) {
            return ResultUtil.failure(10002, "缺少参数");
        }

        Integer count = 0;
        List<Rule> list = null;
        JSONObject data = new JSONObject();
        // 得到规则数量
        count = ruleService.count(title);
        list = ruleService.queryList(title, start, size);
        if (null == count) {
            count = 0;
        }
        if (null == list || 0 == list.size()) {
            data.put("list", new ArrayList<Rule>());
        } else {
            JSONArray items = new JSONArray();
            for (Rule rule : list) {
                JSONObject ruleAddressJSON = JSONObject.parseObject(rule.toString());
                List<Address> addrs = ruleService.queryAddressList(rule.getId());
                List<String> titles = new ArrayList<>();
                for (Address addr : addrs) {
                    titles.add(addr.getTitle());
                }
                ruleAddressJSON.put("addrtitle", titles);
                items.add(ruleAddressJSON);
            }
            data.put("list", items);
        }
        data.put("count", count);
        return ResultUtil.content(data);
    }

    /**
     * 已选择人员
     *
     * @param ruleId        规则id
     * @return
     */
    @PostMapping(value = "/rule/select")
    public JSONObject select(final String ruleId) {
        StringBuilder sb = new StringBuilder();
        sb.append("ruleId=").append(ruleId);
        logger.warn("/rule/select - " + sb.toString());
        if (StringUtils.isBlank(ruleId)) {
            return ResultUtil.failure(10002, "缺少参数");
        }

        List<User> list = ruleService.select(ruleId);
        JSONObject data = new JSONObject();
        data.put("list", list);
        return ResultUtil.content(data);
    }

    /**
     * 分配人员
     *
     * @param ruleId        规则id
     * @param userId        人员id数组
     * @return
     */
    @PostMapping(value = "/rule/assign")
    public JSONObject assign(final String ruleId, final String[] userId) {
        StringBuilder sb = new StringBuilder();
        sb.append("ruleId=").append(ruleId).append("&");
        sb.append("userId=").append(Arrays.toString(userId));
        logger.warn("/rule/assign - " + sb.toString());
        if (StringUtils.isBlank(ruleId) || null == userId) {
            return ResultUtil.failure(10002, "缺少参数");
        }

        Integer[] userIdArray = new Integer[userId.length];
        for (int i = 0; i < userId.length; i++) {
            userIdArray[i] = Integer.parseInt(userId[i]);
        }

        boolean flag = ruleService.assign(ruleId, userIdArray);
        if (flag) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(10000, "分配人员异常");
    }

    /**
     * 禁用启用规则
     *
     * @param ruleId        规则id
     * @param status        状态，0：未使用；1：已使用；2：已禁用
     * @return
     */
    @PostMapping(value = "/rule/enabled")
    public JSONObject enabled(final String ruleId, final Integer status) {
        StringBuilder sb = new StringBuilder();
        sb.append("ruleId=").append(ruleId).append("&");
        sb.append("status=").append(status);
        logger.warn("/rule/enabled - " + sb.toString());
        if (StringUtils.isBlank(ruleId) || null == status) {
            return ResultUtil.failure(10002, "缺少参数");
        }

        boolean flag = ruleService.enabled(ruleId, status);
        if (flag) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(10000, "禁用启用规则异常");
    }

    /**
     * 删除规则
     *
     * @param ruleId        规则id
     * @return
     */
    @PostMapping(value = "/rule/remove")
    public JSONObject remove(final String ruleId) {
        StringBuilder sb = new StringBuilder();
        sb.append("ruleId=").append(ruleId);
        logger.warn("/rule/remove - " + sb.toString());
        if (StringUtils.isBlank(ruleId)) {
            return ResultUtil.failure(10002, "缺少参数");
        }

//        // 判断是否可删除
//        boolean flag = ruleService.deleteable(ruleId);
//        if (!flag) {
//            return ResultUtil.failure(10000, "不允许删除规则");
//        }

        boolean flag = ruleService.remove(ruleId);
        if (flag) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(10000, "删除规则异常");
    }

    /**
     * 批量删除规则
     *
     * @param ruleId        规则id数组
     * @return
     */
    @PostMapping(value = "/rule/batchRemove")
    public JSONObject batchRemove(final String[] ruleId) {
        StringBuilder sb = new StringBuilder();
        sb.append("ruleId=").append(Arrays.toString(ruleId));
        logger.warn("/rule/batchRemove - " + sb.toString());
        if (null == ruleId) {
            return ResultUtil.failure(10002, "缺少参数");
        }

//        // 判断是否可删除
//        boolean flag = ruleService.deleteable(ruleId);
//        if (!flag) {
//            return ResultUtil.failure(10000, "不允许删除规则");
//        }

        boolean flag = ruleService.batchRemove(ruleId);
        if (flag) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(10000, "批量删除规则异常");
    }
}
