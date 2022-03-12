package net.csdn.ac.check.core.utils;

import net.csdn.ac.check.core.base.BaseObject;
import net.csdn.ac.check.core.constant.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author xiangwang
 * @since 2021-06-18
 * @version 1.0
 */
public class DateUtil extends BaseObject {
	protected static Logger logger = LoggerFactory.getLogger(DateUtil.class);
	private static DateFormat sdf = new SimpleDateFormat(Constant.DATE_FORMAT_DATE_SECOND);
	private static DateFormat sdf_day = new SimpleDateFormat(Constant.DATE_FORMAT_DATE_DAY);

	// 字符串转时间戳
	public static long string2Stamp(String date) {
		try {
			return sdf.parse(date).getTime();
		} catch (ParseException e) {
			logger.error(e.getMessage());
		}
		return Constant.DEFAULT_INDEX;
	}

	// 当前时间
	public static String now() {
		try {
			return sdf.format(new Date());
		} catch (Exception e) {
			logger.error("DateUtil exception: {}", e.getMessage());
		}
		return null;
	}

	// 日期字符串转时间戳
	public static long timestamp(final String date) {
		try {
			return sdf.parse(date).getTime();
		} catch (ParseException e) {
			logger.error("DateUtil exception: {}", e.getMessage());
		}
		return Constant.FAILURE_CODE;
	}

	// 解析字符串yyyy-MM-dd HH:mm:ss为日期对象
	public static Date format(final String date) {
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			logger.error("DateUtil exception: {}", e.getMessage());
		}
		return null;
	}

	// 以yyyy-MM-dd格式解析日期
	public static String parseDay(final Date date) {
		try {
			if (date == null) {
				return null;
			}
			return sdf_day.format(date);
		} catch (Exception e) {
			logger.error("DateUtil exception: {}", e.getMessage());
		}
		return null;
	}

	// 以yyyy-MM-dd HH:mm:ss格式解析日期
	public static String parse(final Date date) {
		try {
			if (date == null) {
				return null;
			}
			return sdf.format(date);
		} catch (Exception e) {
			logger.error("DateUtil exception: {}", e.getMessage());
		}
		return null;
	}

	// 以yyyy-MM-dd格式解析日期
	public static String day(final Date date) {
		try {
			return sdf_day.format(date);
		} catch (Exception e) {
			logger.error("DateUtil exception: {}", e.getMessage());
		}
		return null;
	}

	// 获取多久之前的数据，单位毫秒
	public static String before(final long before) {
		return parse(new Date(System.currentTimeMillis() - before * 1000));
	}

	// 获取多久之前的数据，单位毫秒
	public static Date beforeDate(final long before) {
		return new Date(System.currentTimeMillis() - before * 1000);
	}

	// 获取多久之后的数据，单位毫秒
	public static String after(final long after) {
		return parse(new Date(System.currentTimeMillis() + after * 1000));
	}

	// 获取多久之后的数据，单位毫秒
	public static Date afterDate(final long after) {
		return new Date(System.currentTimeMillis() + after * 1000);
	}

	// 判断是否本月
	public static boolean isCurrentMonth(final String date) {
		LocalDate localDate = LocalDate.parse(date);
		LocalDate now = LocalDate.now();
		return localDate.isBefore(now.with(TemporalAdjusters.lastDayOfMonth())) &&
				localDate.isAfter(now.with(TemporalAdjusters.firstDayOfMonth()));
	}

	// 得到指定月份的第一天
	public static LocalDate getFirstDayOfMonth(final String date) {
		return LocalDate.parse(date).with(TemporalAdjusters.firstDayOfMonth());
	}

	// 得到指定月份的最后一天
	public static LocalDate getLastDayOfMonth(final String date) {
		return LocalDate.parse(date).with(TemporalAdjusters.lastDayOfMonth());
	}
}
