package net.csdn.ac.check.core.constant;

/**
 * 全局常量
 *
 * @author xiangwang
 * @since 2021-06-18
 * @version 1.0
 */
public interface Constant {
	// 编码及加密方法常量
	public static final String CRYPTO_TYPE_MD5 = "MD5";
	public static final String CRYPTO_TYPE_SHA256 = "SHA-256";
	// 日期格式
	public static final String DATE_FORMAT_DATE_SECOND = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_FORMAT_DATE_DAY = "yyyy-MM-dd";
	public static final long EXPIRE_ONE_DAY_SECOND_TIMEOUT = 24 * 60 * 60 * 1000;	// 失效时间（1天，单位秒）
	// 数值常量
	public static final String DEFAULT_VALUE = "0";
	public static final int DEFAULT_INDEX = 0;
	// 成功和失败状态码
	public static final int SUCCESS_CODE = 0;
	public static final int FAILURE_CODE = -1;
	// 验证码字符集
	public static final char[] VERIFY_CHARACTERS = {
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
			'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
			'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
	// 字符数量
	public static final int VERIFY_CHARACTER_NUMBER = 4;
	// 干扰线数量
	public static final int VERIFY_LINES = 3;
	// 宽度
	public static final int VERIFY_WIDTH = 150;
	// 高度
	public static final int VERIFY_HEIGHT = 50;
	// 字体大小
	public static final int VERIFY_FONT_SIZE = 22;
}
