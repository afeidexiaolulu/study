package com.atguigu.atcrowdfunding.service;

import com.atguigu.atcrowdfunding.bean.TRole;

import java.util.List;

/**
 * @author Liang Wenjie
 * @version 1.00
 * @time 2019/7/17 0017 上午 10:55
 */
public interface RoleService {
    /**
     * 带条件的角色分页查询
     * @param queryCondition
     * @return
     */
    List<TRole> listRoleByCondition(String queryCondition);

    //保存
    int save(String roleName);

    TRole getRole(Integer id);

    //更新
    int updateRole(TRole role);

    //删除
    int deleteRole(Integer id);
}
