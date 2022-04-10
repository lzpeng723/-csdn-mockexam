
package net.csdn.ac.c4.exam.config;


import lombok.extern.slf4j.Slf4j;
import net.csdn.ac.c4.exam.response.Result;
import net.csdn.ac.c4.exam.response.ResultUtil;
import net.csdn.ac.c4.exam.util.ExceptionUtils;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Filter -> ControllerAdvice -> Interceptor -> AOP
 * 自定义异常响应
 * RestControllerAdvice 在 Interceptor 之前执行
 * Interceptor 中的 afterCompletion 将获取不到 RestControllerAdvice 处理过的异常
 * @author lzpeng
 *
 */
@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {

    /**
     * 参数解析异常
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<String> handleValidateException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        String errorMsg = bindingResult.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(",", "[", "]"));
        log.error("参数错误: " + ExceptionUtils.getDetailMessage(e));
        return ResultUtil.fail(errorMsg);
    }

    /**
     * 文件大小超过限制
     * @param e
     * @return
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<String> handleValidateException(MaxUploadSizeExceededException e) {
        e.printStackTrace();
        // 最大字节数
        long maxUploadSize = e.getMaxUploadSize();
        // 不知道为什么获取出来是 -1
        if (maxUploadSize == -1) {
            throw e;
        }
        // 转为 MB ，忽略 1kb 以下
        double mb = (maxUploadSize >> 10) / (1 << 10);
        log.error("参数错误: " + ExceptionUtils.getDetailMessage(e));
        return ResultUtil.fail(String.format("只允许上传 %.3fMB 以内的文件", mb));
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<String> handleException(BindException e) {
        log.error("参数校验失败: " + ExceptionUtils.getDetailMessage(e));
        StringBuilder sb = new StringBuilder();
        List<FieldError> fieldErrors = e.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            String field = fieldError.getField();
            Object rejectedValue = fieldError.getRejectedValue();
            String defaultMessage = fieldError.getDefaultMessage();
            sb.append("传入的 ")
                    .append(field)
                    .append(" 为 ")
                    .append(rejectedValue)
                    .append(", 需要满足 ")
                    .append(defaultMessage)
                    .append("\r\n");
        }
        return ResultUtil.fail(sb.toString());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public Result<String> handleException(Exception e) {
        log.error("服务器内部错误: " + ExceptionUtils.getDetailMessage(e));
        return ResultUtil.fail(e.getMessage());
    }

}