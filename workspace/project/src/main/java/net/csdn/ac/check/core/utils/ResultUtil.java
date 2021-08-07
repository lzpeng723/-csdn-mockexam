package net.csdn.ac.check.core.utils;

import com.alibaba.fastjson.JSONObject;
import net.csdn.ac.check.core.base.BaseObject;
import net.csdn.ac.check.core.constant.Constant;

/**
 * 返回结果工具类
 *
 * @author xiangwang
 * @since 2021-06-18
 * @version 1.0
 */
public abstract class ResultUtil extends BaseObject {
	private static JSONObject results = null;

	public static JSONObject success() {
		results = new JSONObject();
		results.put("code", Constant.SUCCESS_CODE);
		results.put("data", new JSONObject());
		results.put("msg", "");
		return results;
	}

	public static JSONObject success(final int code, final JSONObject data) {
		results = new JSONObject();
		results.put("code", code);
		results.put("data", data);
		results.put("msg", "");
		return results;
	}

	public static JSONObject content(final JSONObject data) {
		results = new JSONObject();
		results.put("code", Constant.SUCCESS_CODE);
		results.put("data", data);
		results.put("msg", "");
		return results;
	}

	public static JSONObject failure(final int code, final String msg) {
		results = new JSONObject();
		results.put("code", code);
		results.put("data", new JSONObject());
		results.put("msg", msg);
		return results;
	}
}
