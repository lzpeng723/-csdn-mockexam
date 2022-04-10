package net.csdn.ac.c4.exam.config;

import cn.hutool.core.date.DateUtil;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;

/**
 * 字符串转本地时间
 *
 * @author lzpeng
 * @version 1.0
 * @date 2022/2/22 21:38
 */
public class LocalDateTimeConverter implements Converter<String, LocalDateTime> {

    @Override
    public LocalDateTime convert(String source) {
        return DateUtil.parseLocalDateTime(source);
    }
}
