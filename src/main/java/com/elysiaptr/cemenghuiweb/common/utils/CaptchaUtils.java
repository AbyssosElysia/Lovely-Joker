package com.elysiaptr.cemenghuiweb.common.utils;

import com.elysiaptr.cemenghuiweb.common.consts.RedisKeyPrefixes;
import com.elysiaptr.cemenghuiweb.common.properties.CaptchaProperties;
import com.elysiaptr.cemenghuiweb.web.po.Response;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

@Component
public class CaptchaUtils {

    @Autowired
    StringRedisUtils stringRedisUtils;

    private static final Logger log = LoggerFactory.getLogger(CaptchaUtils.class);

    public ResponseEntity<String> generateCaptcha(String uuid) throws IOException {

        Integer width = CaptchaProperties.width;
        Integer height = CaptchaProperties.height;
        Integer length = CaptchaProperties.length;

        Captcha captcha = new SpecCaptcha(width, height);

        captcha.setLen(length);
        captcha.setCharType(Captcha.TYPE_DEFAULT);

        String code = captcha.text();
        System.out.println(code);

        String key = RedisKeyPrefixes.PREFIX_CAPTCHA + uuid;
        stringRedisUtils.set(key, code);
        stringRedisUtils.expire(key, 3, TimeUnit.MINUTES);

        System.out.println(stringRedisUtils.get(key));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        captcha.out(outputStream);
        byte[] image = outputStream.toByteArray();

        String base64Image = Base64.getEncoder().encodeToString(image);
        String dateUri = "data:image/png;base64," + base64Image;

        return new ResponseEntity<>(dateUri, HttpStatus.OK);
    }
}
