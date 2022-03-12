/**
 *
 */
package net.csdn.ac.check.core.configure;

import com.alibaba.fastjson.JSONObject;
import net.csdn.ac.check.core.utils.ExceptionUtils;
import net.csdn.ac.check.core.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RestControllerAdvice
public class ControllerExceptionHandler {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 参数解析异常
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public JSONObject handleValidateException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        String errorMsg = bindingResult.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(",", "[", "]"));
        logger.error("参数错误: " + ExceptionUtils.getDetailMessage(e));
        return ResultUtil.failure(10000 + HttpStatus.BAD_REQUEST.value(), errorMsg);
    }

    /**
     * 文件大小超过限制
     * @param e
     * @return
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public JSONObject handleValidateException(MaxUploadSizeExceededException e) {
        e.printStackTrace();
        // 最大字节数
        long maxUploadSize = e.getMaxUploadSize();
        // 不知道为什么获取出来是 -1
        if (maxUploadSize == -1) {
            throw e;
        }
        // 转为 MB ，忽略 1kb 以下
        double mb = (maxUploadSize >> 10) / (1 << 10);
        logger.error("参数错误: " + ExceptionUtils.getDetailMessage(e));
        return ResultUtil.failure(10000 + HttpStatus.BAD_REQUEST.value(), String.format("只允许上传 %.3fMB 以内的文件", mb));
    }
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public JSONObject handleException(BindException e) {
        logger.error("参数校验失败: " + ExceptionUtils.getDetailMessage(e));
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
        return ResultUtil.failure(10000 + HttpStatus.INTERNAL_SERVER_ERROR.value(),  sb.toString());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public JSONObject handleException(Exception e) {
        logger.error("服务器内部错误: " + ExceptionUtils.getDetailMessage(e));
        return ResultUtil.failure(10000 + HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

}