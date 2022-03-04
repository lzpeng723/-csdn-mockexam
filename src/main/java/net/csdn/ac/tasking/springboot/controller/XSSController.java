package net.csdn.ac.tasking.springboot.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.csdn.ac.tasking.springboot.response.Result;
import net.csdn.ac.tasking.springboot.response.ResultUtil;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * XSS测试
 *
 * @author : lzpeng
 * @date : 2020-5-16
 * @time : 21:03:56
 */
@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/xss")
@Api(tags = "XSS测试", value = "XSS测试")
public class XSSController extends HttpBaseController {

    @GetMapping
    @ApiOperation("GET XSS测试")
    public Result<Object> testGetXss(@RequestParam("xssTest") String xssTest) {
        return ResultUtil.success(xssTest);
    }

    @PostMapping
    @ApiOperation("POST  XSS测试")
    public Result<Object> testPostXss(@RequestBody Map<String, Object> bodyMap) {
        return ResultUtil.success(bodyMap);
    }


}