package com.atguigu.atcrowdfunding.service;

import com.atguigu.atcrowdfunding.bean.TPermission;
import java.util.List;

/**
 * @author Liang Wenjie
 * @version 1.00
 * @time 2019/7/22 0022 下午 10:29
 */
public interface PermissionService {

    //查询所有的权限
    List<TPermission> getAllPermission();

    //分配角色
    void assignPermission(String rid, List<Integer> idsList);

    //根据角色id查询出权限
    List<TPermission> getPermissionByRoleId(Integer roleId);
}
