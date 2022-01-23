package csdn.c4.servlet;

import cn.hutool.db.Entity;
import cn.hutool.http.Method;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 获取列表数据 Servlet
 *
 * @author lzpeng
 * @version 1.0
 * @date 2022/1/16 21:15
 */
@Slf4j
@WebServlet(name = "listFile", urlPatterns = "/csdn/c4/listFile")
public class ListFileServlet extends AbstractHttpServlet {

    /**
     * 处理请求响应
     *
     * @param method 请求方式
     * @param req    请求
     * @param resp   响应
     */
    @Override
    protected Object process(Method method, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String parentFile = System.getProperty("parent_file");
        List<Entity> entityList = db.query("SELECT * FROM file_encryption;");
        JSONObject obj = JSONUtil.createObj();
        JSONObject dataObj = JSONUtil.createObj();
        dataObj.set("parentFile", parentFile);
        dataObj.set("files", entityList);
        obj.set("success", true);
        obj.set("msg", "文件列表加载成功");
        obj.set("data", dataObj);
        return obj;
    }

}
