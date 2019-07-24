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

    //新增
    int addMenu(TMenu menu);

    //删除
    int deleteMenu(List<Integer> idList);

    //更新
    int updateMenu(TMenu menu);

    //查询
    TMenu getMenu(Integer id);

    //查出所有的父菜单
    List<TMenu> getParentMenu();
}
