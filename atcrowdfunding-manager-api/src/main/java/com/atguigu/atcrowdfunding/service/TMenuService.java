package com.atguigu.atcrowdfunding.service;

import com.atguigu.atcrowdfunding.bean.TMenu;

import java.util.List;

/**
 * @author Liang Wenjie
 * @version 1.00
 * @time 2019/7/18 0018 上午 10:57
 */
public interface TMenuService {
    //获取菜单数据
    List<TMenu> getList();
}
