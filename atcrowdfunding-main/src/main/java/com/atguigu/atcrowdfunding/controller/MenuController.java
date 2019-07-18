package com.atguigu.atcrowdfunding.controller;

import com.atguigu.atcrowdfunding.bean.TMenu;
import com.atguigu.atcrowdfunding.service.TMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

/**
 *
 * 权限管理控制器
 * @author Liang Wenjie
 * @version 1.00
 * @time 2019/7/18 0018 上午 10:13
 */
@RequestMapping("/menu")
@Controller
public class MenuController {

    @Autowired
    private TMenuService tMenuService;

    //首页跳转
    @GetMapping("/index.html")
    public String toMenuIndex(){

        return "admin/menu/index";

    }

    /**
     * 获取Ztree数据
     * @return
     */
    @GetMapping("getList")
    @ResponseBody
    public List<TMenu> getList(){
        List<TMenu> menuList = tMenuService.getList();
        return menuList;
    }


}
