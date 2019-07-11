package com.atguigu.atcrowdfunding.service;

import com.atguigu.atcrowdfunding.bean.TMenu;

import java.util.List;

/**
 * @author Liang Wenjie
 * @version 1.00
 * @time 2019/7/11 0011 下午 6:23
 */
public interface SystemMenuService {
    //获取侧边菜单
    List<TMenu> listMenus();
}
