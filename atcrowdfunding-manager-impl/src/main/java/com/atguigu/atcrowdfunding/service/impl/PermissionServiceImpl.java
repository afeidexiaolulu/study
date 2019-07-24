package com.atguigu.atcrowdfunding.service.impl;


import com.atguigu.atcrowdfunding.bean.TPermission;
import com.atguigu.atcrowdfunding.bean.TPermissionExample;
import com.atguigu.atcrowdfunding.bean.TRolePermissionExample;
import com.atguigu.atcrowdfunding.mapper.TPermissionMapper;
import com.atguigu.atcrowdfunding.mapper.TRolePermissionMapper;
import com.atguigu.atcrowdfunding.service.PermissionService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     * 获取所有的权限
     * @return
     */
    @Override
    public List<TPermission> getAllPermission() {
        TPermissionExample tPermissionExample = new TPermissionExample();
        return tPermissionMapper.selectByExample(tPermissionExample);
    }


    //分配角色
    @Override
    public void assignPermission(String rid, List<Integer> idsList) {
        //分配权限前先将用户已有的权限删除
        TRolePermissionExample example = new TRolePermissionExample();
        example.createCriteria().andRoleidEqualTo(Integer.valueOf(rid));
        tRolePermissionMapper.deleteByExample(example);
        //插入
        tRolePermissionMapper.assignPermission(rid, idsList);
    }

    //根据角色查询权限
    @Override
    public List<TPermission> getPermissionByRoleId(Integer roleId) {
        return tPermissionMapper.getPermissionByRoleId(roleId);
    }
}
