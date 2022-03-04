package net.csdn.ac.tasking.springboot.config;

import cn.hutool.core.util.EscapeUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * XssFilterRequestWrapper
 * Xss json 序列化
 *
 * @author lzpeng
 * @version 1.0
 * @date 2022/3/3 21:45
 */
public class XssStringJsonSerializer extends JsonSerializer<String> {
    @Override
    public Class<String> handledType() {
        return String.class;
    }

    @Override
    public void serialize(String value, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {
        if (value != null) {
            String encodedValue = EscapeUtil.escapeHtml4(value);
            jsonGenerator.writeString(encodedValue);
        }
    }
}
