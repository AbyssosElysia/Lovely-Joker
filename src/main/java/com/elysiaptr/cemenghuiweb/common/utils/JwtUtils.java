package com.elysiaptr.cemenghuiweb.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.sql.Date;
import java.util.Base64;
import java.util.UUID;

public class JwtUtils {

    // 有效期
    public static final Long JWT_TTL = 1000 * 60 * 60 * 24L; // 一天

    // 密钥
    public static final String JWT_KEY = Base64.getEncoder().encodeToString("ElysiaPtr".getBytes());

    // 生成令牌
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    // 生成jwt
    public static String generateJWT(String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, getUUID());
        return builder.compact();
    }

    // 生成jwt的业务逻辑
    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid) {

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        SecretKey secretKey = generalKey();

        long nowMillis = System.currentTimeMillis(); // 当前时间

        Date now = new Date(nowMillis);
        if (ttlMillis == null) {
            ttlMillis = JwtUtils.JWT_TTL;

        }

        long expMillis = nowMillis + ttlMillis; // 过期时间

        Date exp = new Date(expMillis);

        return Jwts.builder()
                .setId(uuid) // 设置唯一编号
                .setIssuedAt(now) // 设置签发时间
                .setSubject(subject) // 设置主题
                .setIssuer("elysiaptr") // 设置签发者
                .signWith(signatureAlgorithm, secretKey) // 设置签名算法和密钥
                .setExpiration(exp);
    }

    // 生成加密后的密钥
    public static SecretKey generalKey() {
        byte[] keyBytes = Base64.getDecoder().decode(JwtUtils.JWT_KEY);
        return new SecretKeySpec(keyBytes, 0, keyBytes.length, "AES");
    }

    // 解析jwt
    public static Claims parseJWT(String jwt) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }
}
