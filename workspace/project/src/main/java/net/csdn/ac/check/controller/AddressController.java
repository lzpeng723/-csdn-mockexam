package net.csdn.ac.check.controller;

import com.alibaba.fastjson.JSONObject;
import net.csdn.ac.check.core.base.BaseObject;
import net.csdn.ac.check.entity.Address;
import net.csdn.ac.check.service.AddressService;
import net.csdn.ac.check.core.utils.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * 打卡地点Controller
 *
 * @author xiangwang
 * @since 2021-06-18
 * @version 1.0
 */
@RestController
public class AddressController extends BaseObject {
    @Resource
    private AddressService addressService;

    /**
     * 打卡地点列表
     *
     * @param start 起始页，从1开始
     * @param size  每页条数
     * @return
     */
    @PostMapping(value = "/address/list")
    public JSONObject list(final Integer start, final Integer size) {
        StringBuilder sb = new StringBuilder();
        sb.append("start=").append(start).append("&");
        sb.append("size=").append(size);
        logger.warn("/address/list - " + sb.toString());
        if (null == start || null == size || 0 == start || 0 == size) {
            return ResultUtil.failure(10002, "缺少参数");
        }

        // 得到打卡地点数量
        Integer count = addressService.count();
        if (null == count) {
            count = 0;
        }
        List<Address> list = addressService.queryList(start, size);
        JSONObject data = new JSONObject();
        data.put("count", count);
        data.put("list", list);
        return ResultUtil.content(data);
    }

    /**
     * 打卡地点查询
     *
     * @param title 查询内容
     * @param start 起始页，从1开始
     * @param size  每页条数
     * @return
     */
    @PostMapping(value = "/address/search")
    public JSONObject login(final String title, final Integer start, final Integer size) {
        StringBuilder sb = new StringBuilder();
        sb.append("title=").append(title).append("&");
        sb.append("start=").append(start).append("&");
        sb.append("size=").append(size);
        logger.warn("/address/search - " + sb.toString());
        if (null == start || null == size || 0 == start || 0 == size) {
            return ResultUtil.failure(10002, "缺少参数");
        }

        Integer count = 0;
        List<Address> list = null;
        if (StringUtils.isBlank(title)) {
            count = addressService.count();
            list = addressService.queryList(start, size);
        } else {
            count = addressService.count(title);
            list = addressService.queryByTitle(title, start, size);
        }
        if (null == count) {
            count = 0;
        }
        JSONObject data = new JSONObject();
        data.put("count", count);
        data.put("list", list);
        return ResultUtil.content(data);
    }
}
