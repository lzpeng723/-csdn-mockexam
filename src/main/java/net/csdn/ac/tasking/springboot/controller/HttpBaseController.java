package net.csdn.ac.tasking.springboot.controller;

import cn.hutool.core.util.TypeUtil;
import net.csdn.ac.tasking.springboot.entity.BaseEntity;
import net.csdn.ac.tasking.springboot.response.QueryResult;
import net.csdn.ac.tasking.springboot.response.Result;
import net.csdn.ac.tasking.springboot.response.ResultUtil;
import net.csdn.ac.tasking.springboot.service.BaseService;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Type;
import java.util.List;

/**
 * 基础的Controller
 *
 * @date: 2020/2/5
 * @time: 22:12
 * @author: lzpeng
 * 谁说 HTTP GET 就不能通过 Body 来发送数据呢？ https://www.jianshu.com/p/c025273d78db
 */
public class HttpBaseController {

    protected HttpServletRequest request;

    protected HttpServletResponse response;

    protected HttpSession session;


    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        this.request = request;
        this.response = response;
        this.session = session;
    }

}