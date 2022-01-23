package csdn.c4.servlet;

import cn.hutool.core.io.FileUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.db.Entity;
import cn.hutool.http.Method;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * 获取列表数据 Servlet
 *
 * @author lzpeng
 * @version 1.0
 * @date 2022/1/16 21:15
 */
@Slf4j
@WebServlet(name = "getFile", urlPatterns = "/csdn/c4/getFile")
public class GetFileServlet extends AbstractHttpServlet {

    /**
     * 处理请求响应
     *
     * @param method 请求方式
     * @param req    请求
     * @param resp   响应
     */
    @Override
    protected Object process(Method method, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String fileName = req.getParameter("fileName");
        Entity entity = db.queryOne("SELECT * FROM file_encryption WHERE file_name = ?;", fileName);
        JSONObject obj = JSONUtil.createObj();
        if (entity == null) {
            obj.set("success", false);
            obj.set("msg", "文件不存在");
        } else {
            obj.set("success", true);
            obj.set("msg", "文件解密成功");
            String key = entity.getStr("key");
            String iv = entity.getStr("iv");
            AES aes = new AES(Mode.CTS, Padding.PKCS5Padding, key.getBytes(), iv.getBytes());
            String parentFile = System.getProperty("parent_file");
            File file = new File(parentFile, fileName);
            String utf8String = FileUtil.readUtf8String(file);
            String decryptStr = aes.decryptStr(utf8String);
            obj.set("data", decryptStr);
        }
        return obj;
    }

}
