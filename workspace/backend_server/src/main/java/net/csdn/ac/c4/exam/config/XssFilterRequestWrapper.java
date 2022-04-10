package net.csdn.ac.c4.exam.config;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.EscapeUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import net.csdn.ac.c4.exam.util.DelegatingServletInputStream;
import org.springframework.http.MediaType;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Set;

/**
 * XssFilterRequestWrapper
 * Xss 过滤 请求
 *
 * @author lzpeng
 * @version 1.0
 * @date 2022/3/3 21:45
 */
public class XssFilterRequestWrapper extends HttpServletRequestWrapper {


    private String bodyStr;

    public XssFilterRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getHeader(String name) {
        String header = super.getHeader(name);
        if (header == null || header.isEmpty()) {
            return null;
        }
        return EscapeUtil.escapeHtml4(header);
    }

    @Override
    public String getQueryString() {
        String queryString = super.getQueryString();
        if (queryString == null || queryString.isEmpty()) {
            return null;
        }
        return EscapeUtil.escapeHtml4(queryString);
    }

    @Override
    public String getParameter(String name) {
        String parameter = super.getParameter(name);
        if (parameter == null || parameter.isEmpty()) {
            return null;
        }
        return EscapeUtil.escapeHtml4(parameter);
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(name);
        if (values == null || values.length == 0) {
            return values;
        }
        int length = values.length;
        for (int i = 0; i < length; i++) {
            if (values[i] == null || values[i].isEmpty()) {
                continue;
            }
            values[i] = EscapeUtil.escapeHtml4(values[i]);
        }
        return values;
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> parameterMap = super.getParameterMap();
        if (parameterMap == null || parameterMap.isEmpty()) {
            return parameterMap;
        }
        Set<Map.Entry<String, String[]>> entries = parameterMap.entrySet();
        for (Map.Entry<String, String[]> next : entries) {
            String[] values = next.getValue();
            if (values == null || values.length == 0) {
                continue;
            }
            int length = values.length;
            for (int i = 0; i < length; i++) {
                if (values[i] == null || values[i].isEmpty()) {
                    continue;
                }
                values[i] = EscapeUtil.escapeHtml4(values[i]);
            }
        }
        return super.getParameterMap();
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        if (this.bodyStr == null) {
            this.bodyStr = IoUtil.readUtf8(super.getInputStream());
            String contentType = getContentType();
            if (contentType.contains(MediaType.APPLICATION_JSON_VALUE)) {
                try {
                    JSON json = JSONUtil.parse(this.bodyStr);
                    this.bodyStr = escapeJson(json).toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return new DelegatingServletInputStream(new ByteArrayInputStream(this.bodyStr.getBytes(StandardCharsets.UTF_8)));
    }

    private Object escapeObject(Object obj) {
        if (obj instanceof JSON) {
            return escapeJson((JSON) obj);
        } else if (obj instanceof String) {
            return escapeString((String) obj);
        }
        return obj;
    }

    private JSON escapeJson(JSON json) {
        if (json instanceof JSONArray) {
            return escapeJsonArray((JSONArray) json);
        } else if (json instanceof JSONObject) {
            return escapeJsonObject((JSONObject) json);
        }
        return json;
    }

    private JSONObject escapeJsonObject(JSONObject jsonObject) {
        Set<String> keySet = jsonObject.keySet();
        for (String key : keySet) {
            jsonObject.computeIfPresent(key,(k, v) -> escapeObject(v));
        }
        return jsonObject;
    }

    private JSONArray escapeJsonArray(JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.size(); i++) {
            Object obj = jsonArray.get(i);
            if (obj instanceof JSON) {
                jsonArray.set(i, escapeJson((JSON) obj));
            } else if (obj instanceof String) {
                jsonArray.set(i, escapeString((String) obj));
            }
        }
        return jsonArray;
    }

    private String escapeString(String obj) {
        return EscapeUtil.escapeHtml4(obj);
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

}
