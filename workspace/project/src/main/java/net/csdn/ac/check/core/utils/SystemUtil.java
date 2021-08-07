package net.csdn.ac.check.core.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import net.csdn.ac.check.core.base.BaseObject;
import net.csdn.ac.check.core.constant.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * 系统工具类
 *
 * @author xiangwang
 * @since 2021-06-18
 * @version 1.0
 */
public class SystemUtil extends BaseObject {
    protected static Logger logger = LoggerFactory.getLogger(SystemUtil.class);

    /**
     * 产生6位数随机数
     *
     */
    public static String smscode() {
        return String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
    }

    /**
     * 生成32位secret或者salt
     *
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 生成md5
     *
     */
    public static String md5(final String plainText) {
        // 定义一个字节数组
        byte[] secretBytes = null;
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance(Constant.CRYPTO_TYPE_MD5);
            // 对字符串进行加密
            md.update(plainText.getBytes());
            // 获得加密后的数据
            secretBytes = md.digest();
        } catch (NoSuchAlgorithmException e) {
            logger.warn("MD5异常: {}", e.getMessage());
            return null;
        }
        // 将加密后的数据转换为16进制数字
        String md5code = new BigInteger(1, secretBytes).toString(16);// 16进制数字
        // 如果不足32位，就补足成32位
        if (md5code.length() < 32) {
            for (int i = 0; i < 32 - md5code.length() + 1; i++) {
                md5code = Constant.DEFAULT_VALUE + md5code;
            }
        }

        return md5code;
    }

    /**
     * SHA256加密
     *
     */
    public static String sha256(final String str) {
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance(Constant.CRYPTO_TYPE_SHA256);
            messageDigest.update(str.getBytes(StandardCharsets.UTF_8));
            return byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 将byte转为16进制
     *
     */
    private static String byte2Hex(final byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        String temp = null;
        for (byte aByte : bytes) {
            temp = Integer.toHexString(aByte & 0xFF);
            if (temp.length() == 1) {
                sb.append("0");
            }
            sb.append(temp);
        }
        return sb.toString();
    }

    /**
     * 生成jwt
     *
     * @param id    登录用户id
     * @return
     */
    public static String createToken(final Integer id) {
        // 设置token超时时间
        Date expireDate = new Date(System.currentTimeMillis() + expireTime);
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        String token = JWT.create()
                .withHeader(map)// 添加头部
                // 可以将基本信息放到claims中
                // .withClaim("id", user.getId())
                .withIssuer("http://csdn.net/login")
                .withExpiresAt(expireDate)
                .withIssuedAt(new Date())
                .withClaim("sub", id)
                .withClaim("nbf", new Date())
                .withClaim("jti", uuid())
                .sign(Algorithm.HMAC256(secretKey));
        return token;
    }

    /**
     * 校验并解析token
     *
     */
    public static Map<String, Claim> verifyToken(String token) {
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secretKey)).build();
            jwt = verifier.verify(token);
            return jwt.getClaims();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 生成随机验证码及图片
     * 数组中[验证码，图片]
     *
     */
    public static Object[] createImage() {
        StringBuffer sb = new StringBuffer();
        // 创建空白图片
        BufferedImage image = new BufferedImage(Constant.VERIFY_WIDTH, Constant.VERIFY_HEIGHT, BufferedImage.TYPE_INT_RGB);
        // 获取图片画笔
        Graphics graphic = image.getGraphics();
        // 设置画笔颜色
        graphic.setColor(Color.WHITE);
        // 绘制矩形背景
        graphic.fillRect(0, 0, Constant.VERIFY_WIDTH, Constant.VERIFY_HEIGHT);
        // 画随机字符
        Random random = new Random();
        for (int i = 0; i < Constant.VERIFY_CHARACTER_NUMBER; i++) {
            // 取随机字符索引
            int n = random.nextInt(Constant.VERIFY_CHARACTERS.length);
            // 设置随机颜色
            graphic.setColor(new Color(112, 55, 112));
            // 设置字体大小
            graphic.setFont(new Font(null, Font.BOLD + Font.ITALIC, Constant.VERIFY_FONT_SIZE));
            // 画字符
            graphic.drawString(Constant.VERIFY_CHARACTERS[n] + "", i * Constant.VERIFY_WIDTH / Constant.VERIFY_CHARACTER_NUMBER, Constant.VERIFY_HEIGHT / 2);
            // 记录字符
            sb.append(Constant.VERIFY_CHARACTERS[n]);
        }
        // 返回验证码和图片
        return new Object[] { sb.toString(), image };
    }
}
