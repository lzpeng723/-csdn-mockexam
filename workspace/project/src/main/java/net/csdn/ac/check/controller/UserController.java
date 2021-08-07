package net.csdn.ac.check.controller;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.interfaces.Claim;
import net.csdn.ac.check.core.base.BaseObject;
import net.csdn.ac.check.core.utils.SystemUtil;
import net.csdn.ac.check.entity.AdminLogin;
import net.csdn.ac.check.entity.AdminUser;
import net.csdn.ac.check.core.utils.ResultUtil;
import net.csdn.ac.check.entity.Department;
import net.csdn.ac.check.entity.DepartmentUser;
import net.csdn.ac.check.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 用户Controller
 *
 * @author xiangwang
 * @since 2021-06-18
 * @version 1.0
 */
@RestController
public class UserController extends BaseObject {
    @Resource
    private UserService userService;

    /**
     * 生成图形验证码
     *
     * @param number    验证码key
     * @param response
     */
    @GetMapping(value = "captcha/{number}")
    public void captcha(@PathVariable(name = "number") final String number, final HttpServletResponse response) {
        logger.warn("/captcha/" + number);
        try {
            Object[] objs = SystemUtil.createImage();
            // 验证码value
            String code = (String) objs[0];
            // 将验证码存入缓存
            userService.setCache(number, code);
            // 将图片输出给浏览器
            BufferedImage image = (BufferedImage) objs[1];
            response.setContentType("image/jpeg");
            // 服务器自动创建输出流，目标指向浏览器
            OutputStream os = response.getOutputStream();
            ImageIO.write(image, "jpeg", os);
            os.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * 用户登录
     *
     * @param loginName
     * @param password
     * @param number
     * @param code
     * @return
     */
    @PostMapping(value = "login")
    public JSONObject login(final String loginName, final String password, final String number, final String code) {
        StringBuilder sb = new StringBuilder();
        sb.append("loginName=").append(loginName).append("&");
        sb.append("number=").append(number).append("&");
        sb.append("code=").append(code);
        logger.warn("/login - " + sb.toString());
        if (StringUtils.isBlank(loginName) || StringUtils.isBlank(password) || StringUtils.isBlank(number) || StringUtils.isBlank(code)) {
            return ResultUtil.failure(10002, "缺少参数");
        }

        // 从缓存中取出数据
        String resultCode = userService.getCache(number);
        if (!resultCode.equalsIgnoreCase(code)) {
            return ResultUtil.failure(20001, "验证码错误");
        }

        JSONObject data = new JSONObject();
        // 从缓存中取出token
        String token = userService.getCache(loginName);
        if (StringUtils.isBlank(token)) {
            AdminUser user = userService.queryByLoginName(loginName);
            String encryPassword = SystemUtil.sha256(password + user.getSalt());
            // 验证密码是否相等
            if (encryPassword.equalsIgnoreCase(user.getPassword())) {
                token = SystemUtil.createToken(user.getId());
                data.put("token", token);
                // 将token存入缓存
                userService.setCache(loginName, token);
            } else {
                return ResultUtil.failure(20002, "账号或密码错误");
            }
        } else {
            data.put("token", token);
        }
        data.put("expire", new Date(System.currentTimeMillis() + expireTime).getTime() / 1000);
        return ResultUtil.success(0, data);
    }

    /**
     * 用户登出
     *
     * @param loginName
     * @return
     */
    @PostMapping(value = "logout")
    public JSONObject logout(final String loginName) {
        logger.warn("/logout - loginName=" + loginName);
        if (StringUtils.isBlank(loginName)) {
            return ResultUtil.failure(10002, "缺少参数");
        }

        // 从缓存中清除数据
        userService.clearCache(loginName);
        return ResultUtil.success();
    }

    /**
     * 部门人员列表
     *
     * @return
     */
    @GetMapping(value = "deptuser")
    public JSONObject deptuser() {
        logger.warn("/deptuser");
        List<DepartmentUser> list = userService.select();
        List<Department> deptList = userService.deptList();
        JSONObject data = new JSONObject();
        data.put("deptList", deptList);
        data.put("list", list);
        return ResultUtil.content(data);
    }

    /**
     * 默认方法
     *
     * @param request
     * @return
     */
    @GetMapping(value = "getinfo")
    public JSONObject getinfo(HttpServletRequest request) {
        logger.warn("/getinfo - session=" + request.getSession().getId());
        String header = request.getHeader("Authorization");
        header = header.substring(7).trim();
        Map<String, Claim> map = SystemUtil.verifyToken(header);

        String id = map.get("sub").toString();
        AdminLogin adminLogin = userService.queryAdminLogin(Integer.parseInt(id));
        JSONObject data = new JSONObject();
        data.put("id", adminLogin.getId());
        data.put("uid", adminLogin.getUid());
        data.put("login_name", adminLogin.getLogin_name());
        data.put("create_time", adminLogin.getCreate_time());
        return ResultUtil.content(data);
    }
}
