package net.csdn.ac.c4.exam.response; /**
 * Created by mrt on 2018/3/5.
 * 10000-- 通用错误代码
 * 22000-- 媒资错误代码
 * 23000-- 用户中心错误代码
 * 24000-- cms错误代码
 * 25000-- 文件系统
 */

/**
 * 错误代码信息接口
 *
 * @date: 2020/2/1
 * @time: 22:50
 * @author: lzpeng
 */
public interface ResultCode {

    /**
     * 操作代码
     *
     * @return
     */
    int code();

    /**
     * 提示信息
     *
     * @return
     */
    String message();

}