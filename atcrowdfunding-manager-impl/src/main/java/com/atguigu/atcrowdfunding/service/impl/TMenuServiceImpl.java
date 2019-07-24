package com.atguigu.atcrowdfunding.service.impl;

import com.atguigu.atcrowdfunding.bean.TMenu;
import com.atguigu.atcrowdfunding.bean.TMenuExample;
import com.atguigu.atcrowdfunding.mapper.TMenuMapper;
import com.atguigu.atcrowdfunding.service.TMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Liang Wenjie
 * @version 1.00
 * @time 2019/7/18 0018 上午 10:57
 */
@Service
@Transactional
public class TMenuServiceImpl implements TMenuService {

    @Autowired
    private TMenuMapper tMenuMapper;

    //获取菜单数据
    @Override
    public List<TMenu> getList() {
        TMenuExample example = new TMenuExample();
        return tMenuMapper.selectByExample(example);
    }

    //新增
    @Override
    public int addMenu(TMenu menu) {
        return tMenuMapper.insertSelective(menu);
    }

    //删除
    @Override
    public int deleteMenu(List<Integer> idList) {
        //如果菜单有子菜单就不删除
        ArrayList<Integer> list = new ArrayList<>();
        for (Integer pid : idList) {
            TMenuExample example = new TMenuExample();
            example.createCriteria().andPidEqualTo(pid);
            long l = tMenuMapper.countByExample(example);
            if (l == 0) {
                //自己的id不是别人的Pid就说明  自己没有子元素
                list.add(pid);
            }
        }

        TMenuExample tMenuExample = new TMenuExample();
        tMenuExample.createCriteria().andIdIn(list);
        return tMenuMapper.deleteByExample(tMenuExample);
    }

    //更新
    @Override
    public int updateMenu(TMenu menu) {
        return tMenuMapper.updateByPrimaryKeySelective(menu);
    }

    //查询
    @Override
    public TMenu getMenu(Integer id) {

        return tMenuMapper.selectByPrimaryKey(id);
    }

    //查出所有的父菜单
    @Override
    public List<TMenu> getParentMenu() {
        ArrayList<Integer> tMenuList = new ArrayList<>();
        TMenuExample example = new TMenuExample();
        List<TMenu> menuList = tMenuMapper.selectByExample(example);
        for (TMenu menu : menuList) {
            if(menu.getPid() == 0){
                tMenuList.add(menu.getId());
            }
        }
        TMenuExample tMenuExample = new TMenuExample();
        tMenuExample.createCriteria().andIdIn(tMenuList);
        return tMenuMapper.selectByExample(tMenuExample);
    }
}
