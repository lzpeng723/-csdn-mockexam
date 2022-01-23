package csdn.c4.servlet;

import cn.hutool.core.util.XmlUtil;
import cn.hutool.db.Db;
import cn.hutool.http.ContentType;
import cn.hutool.http.Method;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import csdn.c4.dao.DbUtil;
import org.w3c.dom.Node;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

/**
 * @author lzpeng
 * @version 1.0
 * @date 2022/1/22 10:34
 */
public abstract class AbstractHttpServlet extends HttpServlet {

    protected static final Db db = DbUtil.getDb();

    /**
     * 处理请求响应
     *
     * @param method 请求方式
     * @param req    请求
     * @param resp   响应
     */
    protected abstract Object process(Method method, HttpServletRequest req, HttpServletResponse resp) throws Exception;

    @Override
    protected final void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Object result = process(Method.POST, req, resp);
            handlerResult(result, req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            handlerException(e, req, resp);
        }
    }

    @Override
    protected final void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Object result = process(Method.GET, req, resp);
            handlerResult(result, req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            handlerException(e, req, resp);
        }
    }

    @Override
    protected void doTrace(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Object result = process(Method.TRACE, req, resp);
            handlerResult(result, req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            handlerException(e, req, resp);
        }
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Object result = process(Method.OPTIONS, req, resp);
            handlerResult(result, req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            handlerException(e, req, resp);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Object result = process(Method.DELETE, req, resp);
            handlerResult(result, req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            handlerException(e, req, resp);
        }
    }

    @Override
    protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Object result = process(Method.HEAD, req, resp);
            handlerResult(result, req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            handlerException(e, req, resp);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Object result = process(Method.PUT, req, resp);
            handlerResult(result, req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            handlerException(e, req, resp);
        }
    }

    private void handlerResult(Object result, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        if (result instanceof JSON) {
            resp.setContentType(ContentType.JSON.toString());
            resp.getWriter().println(result);
        }
        if (result instanceof Node) {
            resp.setContentType(ContentType.XML.toString());
            resp.getWriter().println(XmlUtil.toStr((Node) result));
        }
        if (result instanceof CharSequence) {
            resp.setContentType(ContentType.TEXT_PLAIN.toString());
            resp.getWriter().println(result);
        }
    }

    private void handlerException(Exception e, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        JSONObject obj = JSONUtil.createObj();
        obj.set("success", false);
        obj.set("msg", sw.toString());
        handlerResult(obj, req, resp);
    }


}
