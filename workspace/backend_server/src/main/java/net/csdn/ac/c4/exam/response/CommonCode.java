package net.csdn.ac.c4.exam.response;

import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * 公共错误代码
 *
 * @date: 2020/2/1
 * @time: 22:50
 * @author: lzpeng
 */

@ToString
@AllArgsConstructor
public enum CommonCode implements ResultCode {

    /**
     * 成功返回码
     */
    SUCCESS(200, "操作成功"),
    /**
     * 系统异常返回码
     */
    SERVER_ERROR(10000, "系统异常"),
    /**
     * 参数为空
     */
    ARGUMENT_EMPTY(10001, "参数为空"),
    /**
     * 缺少参数
     */
    ARGUMENT_LOSS(10002, "缺少参数"),
    /**
     * 请求超时
     */
    REQUEST_TIMEOUT(10003, "请求超时"),
    /**
     * 不允许的HTTP METHOD
     */
    ARGUMENT_FORMAT_ERROR(10005, "参数格式错误");

    /**
     * 操作代码
     */
    int code;

    /**
     * 提示信息
     */
    String message;

    /**
     * 根据 code 获取 CommonCode
     *
     * @param code
     * @return
     */
    public static CommonCode getCommonCode(int code) {
        for (CommonCode commonCode : values()) {
            if (commonCode.code == code) {
                return commonCode;
            }
        }
        return SERVER_ERROR;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }


}