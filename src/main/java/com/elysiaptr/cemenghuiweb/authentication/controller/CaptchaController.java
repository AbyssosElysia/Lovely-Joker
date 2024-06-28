package com.elysiaptr.cemenghuiweb.authentication.controller;

import com.elysiaptr.cemenghuiweb.common.entity.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/open_api")
public class CaptchaController {

    @GetMapping("/captcha")
    public R getCaptcha(@RequestParam String uuid) {
        return R.OK().data("uuid", uuid);
    }
}
