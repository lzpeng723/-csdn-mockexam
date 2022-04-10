package net.csdn.ac.c4.exam.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.csdn.ac.c4.exam.entity.BaseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;

@Component
public class BaseEntityMessageConverter extends MappingJackson2HttpMessageConverter {


    public BaseEntityMessageConverter(ObjectMapper objectMapper) {
        super(objectMapper);
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        // 表明只处理BaseEntity类型的参数。
        return BaseEntity.class.isAssignableFrom(clazz);
    }

}