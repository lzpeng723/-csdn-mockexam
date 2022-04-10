package net.csdn.ac.c4.exam.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * 基础的Controller
 *
 * @date: 2020/2/5
 * @time: 22:12
 * @author: lzpeng
 * 谁说 HTTP GET 就不能通过 Body 来发送数据呢？ https://www.jianshu.com/p/c025273d78db
 * @see RequestContextHolder
 */
public abstract class BaseHttpController {

    @Autowired
    protected ObjectMapper objectMapper;

    protected HttpServletRequest request;

    protected HttpServletResponse response;

    protected HttpSession session;


    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        this.request = request;
        this.response = response;
        this.session = session;
    }

    protected <T> T mapToObj(Map<String, Object> map, Class<T> clazz) {
        try {
            String bodyMapStr = objectMapper.writer().writeValueAsString(map);
            return objectMapper.reader().readValue(bodyMapStr, clazz);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}