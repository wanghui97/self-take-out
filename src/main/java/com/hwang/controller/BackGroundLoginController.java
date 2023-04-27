package com.hwang.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 *
 * @Author 王辉
 * @Create 2023/4/27 16:31
 * @Version 1.0
 */
@RestController
@RequestMapping
public class BackGroundLoginController {

    @RequestMapping("/login")
    public String login(){
        return "登录成功！";
    }
}
