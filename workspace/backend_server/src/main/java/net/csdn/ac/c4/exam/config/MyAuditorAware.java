package net.csdn.ac.c4.exam.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * 获取当前上下文用户人
 *
 * @author lzpeng
 * @version 1.0
 * @date 2022/4/6 20:49
 */
@Component
public class MyAuditorAware implements AuditorAware<String> {
    /**
     * Returns the current auditor of the application.
     *
     * @return the current auditor.
     */
    @Override
    public Optional<String> getCurrentAuditor() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String userId = request.getHeader("User-ID");
        return Optional.ofNullable(userId);
    }
}
