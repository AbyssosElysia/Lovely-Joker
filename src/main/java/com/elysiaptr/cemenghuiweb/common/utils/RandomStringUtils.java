package com.elysiaptr.cemenghuiweb.common.utils;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomStringUtils {

    public String generateVerificationCode(int length) {
        // 可选的验证码字符
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder code = new StringBuilder();

        for (int i = 0; i < length; i++) {
            // 从字符集中随机选择一个字符
            char randomChar = characters.charAt(random.nextInt(characters.length()));
            code.append(randomChar);
        }

        return code.toString();
    }
}
