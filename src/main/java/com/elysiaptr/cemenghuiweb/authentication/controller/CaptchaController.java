package com.elysiaptr.cemenghuiweb.authentication.controller;

import com.elysiaptr.cemenghuiweb.common.utils.CaptchaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/open_api")
public class CaptchaController {

    @Autowired
    CaptchaUtils captchaUtils;

    @GetMapping("/captcha")
    public ResponseEntity<String> getCaptcha(@RequestParam String uuid) throws IOException {
        return captchaUtils.generateCaptcha(uuid);
    }

}
