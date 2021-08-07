package net.csdn.ac.check.core.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 * 公共父类
 *
 * @author xiangwang
 * @since 2021-06-18
 * @version 1.0
 */
public abstract class BaseObject {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    // jwt密钥
    protected static String secretKey;
    // 过期时间
    protected static long expireTime;

    public static String getSecretKey() {
        return secretKey;
    }

    @Value("${customer.secretKey}")
    public void setSecretKey(String secretKey) {
        BaseObject.secretKey = secretKey;
    }

    public static long getExpireTime() {
        return expireTime;
    }

    @Value("${customer.expireTime}")
    public void setExpireTime(long expireTime) {
        BaseObject.expireTime = expireTime;
    }
}
