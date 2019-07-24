package com.atguigu.atcrowdfunding.service.impl;


import com.atguigu.atcrowdfunding.bean.TPermission;
import com.atguigu.atcrowdfunding.bean.TPermissionExample;
import com.atguigu.atcrowdfunding.bean.TPermissionMenuExample;
import com.atguigu.atcrowdfunding.bean.TRolePermissionExample;
import com.atguigu.atcrowdfunding.mapper.TPermissionMapper;
import com.atguigu.atcrowdfunding.mapper.TPermissionMenuMapper;
import com.atguigu.atcrowdfunding.mapper.TRolePermissionMapper;
import com.atguigu.atcrowdfunding.service.PermissionService;
import com.atguigu.atcrowdfunding.util.IdsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Liang Wenjie
 * @version 1.00
 * @time 2019/7/22 0022 下午 10:29
 */
@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private TPermissionMapper tPermissionMapper;

    @Autowired
    private TRolePermissionMapper tRolePermissionMapper;

    @Autowired
    private TPermissionMenuMapper tPermissionMenuMapper;

    /**
     * 获取所有的权限
     * @return
     */
    @Override
    public List<TPermission> getAllPermission() {
        TPermissionExample tPermissionExample = new TPermissionExample();
        return tPermissionMapper.selectByExample(tPermissionExample);
    }


    //给角色分配权限
    @Override
    public void assignPermission(String rid, List<Integer> idsList) {
        //分配权限前先将用户已有的权限删除
        TRolePermissionExample example = new TRolePermissionExample();
        example.createCriteria().andRoleidEqualTo(Integer.valueOf(rid));
        tRolePermissionMapper.deleteByExample(example);
        //插入
        if(idsList.size()!=0){
            tRolePermissionMapper.assignPermission(rid, idsList);
        }

    }

    //根据角色查询权限
    @Override
    public List<TPermission> getPermissionByRoleId(Integer roleId) {
        return tPermissionMapper.getPermissionByRoleId(roleId);
    }

    //根据菜单查询权限
    @Override
    public List<TPermission> getPermissionByMenuId(Integer mId) {
        return tPermissionMapper.getPermissionByMenuId(mId);
    }

    @Override
    public List<TPermission> assignPermissionToMenu(Integer mId, String ids) {
        List<Integer> integers = IdsUtil.ParseIds(ids);
        //给菜单分配前先将菜单已有的权限删除
        TPermissionMenuExample example = new TPermissionMenuExample();
        example.createCriteria().andMenuidEqualTo(mId);
        tPermissionMenuMapper.deleteByExample(example);
        //如果不为ids不为0才插入
        if(!(integers.size() == 0)){
            tPermissionMenuMapper.assignPermissionToMenu(mId, integers);
        }
        return new ArrayList<>();
    }
}
