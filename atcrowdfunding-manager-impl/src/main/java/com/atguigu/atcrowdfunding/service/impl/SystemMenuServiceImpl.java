package com.atguigu.atcrowdfunding.service.impl;

import com.atguigu.atcrowdfunding.bean.TMemberExample;
import com.atguigu.atcrowdfunding.bean.TMenu;
import com.atguigu.atcrowdfunding.bean.TMenuExample;
import com.atguigu.atcrowdfunding.mapper.TMenuMapper;
import com.atguigu.atcrowdfunding.service.SystemMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Liang Wenjie
 * @version 1.00
 * @time 2019/7/11 0011 下午 6:25
 */
@Service
@Transactional
public class SystemMenuServiceImpl implements SystemMenuService {

    private static Logger logger = LoggerFactory.getLogger(SystemMenuServiceImpl.class);

    @Autowired
    private TMenuMapper menuMapper;

    //获取所有都侧边栏菜单
    @Override
    public List<TMenu> listMenus() {
        TMenuExample tMenuExample = new TMenuExample();
        List<TMenu> tMenus = menuMapper.selectByExample(tMenuExample);
        logger.info("所有的侧边菜单为："+tMenus);

        //循环tmeus  将自身id和数据封装为map
        HashMap<Integer, TMenu> menuMap = new HashMap<>();
        for (TMenu tMenu : tMenus) {
            menuMap.put(tMenu.getId(),tMenu);
        }
        logger.info("所有的侧边菜单封装为map："+menuMap);

        ArrayList<TMenu> parentList = new ArrayList<>();
        //再次循环tMenus
        for (TMenu tMenu : tMenus) {
            //判断是否为一级菜单
            if(tMenu.getPid() == 0){
                //父菜单
                parentList.add(tMenu);
            }else {
                //自己的父id为其他菜单的id
                Integer id = tMenu.getPid();
                menuMap.get(id).gettMenuList().add(tMenu);
            }
        }

        return parentList;
    }
}
