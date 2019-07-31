package com.atguigu.atcrowdfunding.mapper;

import com.atguigu.atcrowdfunding.bean.TPermission;
import com.atguigu.atcrowdfunding.bean.TPermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TPermissionMapper {
    long countByExample(TPermissionExample example);

    int deleteByExample(TPermissionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TPermission record);

    int insertSelective(TPermission record);

    List<TPermission> selectByExample(TPermissionExample example);

    TPermission selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TPermission record, @Param("example") TPermissionExample example);

    int updateByExample(@Param("record") TPermission record, @Param("example") TPermissionExample example);

    int updateByPrimaryKeySelective(TPermission record);

    int updateByPrimaryKey(TPermission record);

    //分配权限
    void assignPermission(@Param("rid") String rid, @Param("idsList") List<Integer> idsList);

    //根据角色查询权限
    List<TPermission> getPermissionByRoleId(Integer roleId);

    //根据菜单查询出权限
    List<TPermission> getPermissionByMenuId(Integer mId);

    //根据用户id查询权限
    List<TPermission> listPermissionByUserId(Integer userId);
}