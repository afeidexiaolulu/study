package com.atguigu.atcrowdfunding.service.impl;

import com.atguigu.atcrowdfunding.bean.TRole;
import com.atguigu.atcrowdfunding.bean.TRoleExample;
import com.atguigu.atcrowdfunding.mapper.TRoleMapper;
import com.atguigu.atcrowdfunding.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 *
 * 角色逻辑层
 * @author Liang Wenjie
 * @version 1.00
 * @time 2019/7/17 0017 上午 10:59
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private TRoleMapper roleMapper;

    /**
     * 带条件的分页查询
     */
    @Override
    public List<TRole> listRoleByCondition(String queryCondition) {

        TRoleExample example = new TRoleExample();
        //判断不是空串
        if(!StringUtils.isEmpty(queryCondition)){
            example.createCriteria().andNameLike("%"+ queryCondition +"%");
        }
        return roleMapper.selectByExample(example);
    }


    /**
     * 保存角色
     */
    @Override
    public int save(String roleName) {
        TRole tRole = new TRole();
        tRole.setName(roleName);
        return roleMapper.insertSelective(tRole);
    }

    //查询
    @Override
    public TRole getRole(Integer id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    //更新
    @Override
    public int updateRole(TRole role) {
        return roleMapper.updateByPrimaryKeySelective(role);
    }

    //删除
    @Override
    public int deleteRole(Integer id) {
        return roleMapper.deleteByPrimaryKey(id);
    }
}
