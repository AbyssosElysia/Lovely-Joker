package com.elysiaptr.cemenghuiweb.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptUtil {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    /**
     * 加密密码
     * @param password 要加密的密码
     * @return 加密后的密码
     */
    public static String encode(String password) {
        return encoder.encode(password);
    }

    /**
     * 验证密码是否匹配
     * @param rawPassword 用户输入的密码（明文）
     * @param encodedPassword 数据库中存储的加密密码
     * @return 如果密码匹配返回 true，否则返回 false
     */
    public static boolean matches(String rawPassword, String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }
}

