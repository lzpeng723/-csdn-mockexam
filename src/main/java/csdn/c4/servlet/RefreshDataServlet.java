package csdn.c4.servlet;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.ZipUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.db.Entity;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import csdn.c4.dao.DbUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 刷新数据 Servlet
 *
 * @author lzpeng
 * @version 1.0
 * @date 2022/1/16 21:15
 */
@Slf4j
@WebServlet(name = "refreshData", urlPatterns = "/csdn/c4/refreshData")
public class RefreshDataServlet extends AbstractHttpServlet {

    /**
     * 处理请求响应
     *
     * @param method 请求方式
     * @param req    请求
     * @param resp   响应
     */
    @Override
    protected Object process(Method method, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        DbUtil.initData();
        JSONObject obj = JSONUtil.createObj();
        obj.set("success", true);
        obj.set("msg", "文件下载并加密成功");
        return obj;
    }
}
