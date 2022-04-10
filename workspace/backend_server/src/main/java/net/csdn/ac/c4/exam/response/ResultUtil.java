package net.csdn.ac.c4.exam.response;

/**
 * 构建返回值工具
 *
 * @date: 2020/2/1
 * @time: 23:07
 * @author: lzpeng
 */
public class ResultUtil {

    private static Result SUCCESS = Result.create(CommonCode.SUCCESS);

    private static Result FAIL = Result.create(CommonCode.SERVER_ERROR);

    public static Result success() {
        return SUCCESS;
    }

    public static Result fail() {
        return FAIL;
    }

    public static <T> Result<T> fail(T data) {
        return Result.create(CommonCode.SERVER_ERROR, data);
    }

    public static <T> Result<T> success(T data) {
        return Result.create(CommonCode.SUCCESS, data);
    }

    public static <T> Result<T> create(ResultCode result, T data) {
        return Result.create(result, data);
    }
}