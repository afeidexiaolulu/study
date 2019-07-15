package com.atguigu.atcrowdfunding.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Liang Wenjie
 * @version 1.00
 * @time 2019/7/14 0014 下午 10:31
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    private static Logger logger = LoggerFactory.getLogger(AdminController.class);

    @GetMapping("/index.html")
    public String toUserIndex(){

        logger.info("跳转到用户首页");
        return "user/index";
    }
}
