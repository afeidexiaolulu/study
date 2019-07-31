package com.atguigu.atcrowdfunding.controller;



import com.atguigu.atcrowdfunding.bean.TAdmin;
import com.atguigu.atcrowdfunding.bean.TMenu;
import com.atguigu.atcrowdfunding.service.LoginService;
import com.atguigu.atcrowdfunding.service.SystemMenuService;
import com.atguigu.atcrowdfunding.util.Const;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.List;


@Controller
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
    @Autowired
    private LoginService loginService;

    @Autowired
    private SystemMenuService systemMenuService;


    //此方法为了让重定向方法跳转到主页面
    @RequestMapping("/main.html")
    public String main(HttpSession session){
        if(session.getAttribute(Const.SYS_MENU)==null){
            //1、查询系统的所有菜单
            List<TMenu> listMenus = systemMenuService.listMenus();
            //2、session；
            session.setAttribute(Const.SYS_MENU, listMenus);
        }
        logger.info("正要跳转页面到main");
        return "main";
    }

    @Deprecated //标注为过时的
    @RequestMapping("/login")
    public String login(TAdmin tAdmin, HttpSession session, Model model){
        //登陆
        TAdmin loginUser = loginService.login(tAdmin);
        //如果用户为空  用户名或密码错误
        if(loginUser == null){
            //将用户名通过model对象返回到前台,同时提醒用户登陆失败
            model.addAttribute(Const.LOGIN_ERROR,"用户名或密码错误!");
            model.addAttribute(Const.LOGIN_ACCT, tAdmin.getLoginacct());

            //转发到登录页面  重定向url地址变化
            return "forward:/login.jsp";
        }
        //如果不为空
        session.setAttribute(Const.LOGIN_USER,loginUser);
        //登录完成 重定向到主页面
        //转发能访问受保护的资源，重定向不能；
        return "redirect:/main.html";
    }

}
