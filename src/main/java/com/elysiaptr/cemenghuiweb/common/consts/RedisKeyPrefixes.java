package com.elysiaptr.cemenghuiweb.common.consts;

public interface RedisKeyPrefixes {

    /**
     * 所有key前面加上服务名
     */
    String PREFIX_BASE = "CeMengHui:";

    /**
     * 用户登录的验证码
     */
    String PREFIX_CAPTCHA = PREFIX_BASE + "CAPTCHA:";

    /**
     * 用户授权JWT
     */
    String PREFIX_LOGIN_TOKEN = PREFIX_BASE + "LOGIN_TOKEN:";

    String RESET_CAPTCHA = PREFIX_BASE + "RESET_USER:";
}
