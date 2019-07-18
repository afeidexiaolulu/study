package com.atguigu.atcrowdfunding.service.impl;

import com.atguigu.atcrowdfunding.bean.TMenu;
import com.atguigu.atcrowdfunding.bean.TMenuExample;
import com.atguigu.atcrowdfunding.mapper.TMenuMapper;
import com.atguigu.atcrowdfunding.service.TMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
