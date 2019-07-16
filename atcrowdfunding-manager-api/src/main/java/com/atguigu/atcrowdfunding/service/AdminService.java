package com.atguigu.atcrowdfunding.service;

import com.atguigu.atcrowdfunding.bean.TAdmin;

import java.util.List;

/**
 * @author Liang Wenjie
 * @version 1.00
 * @time 2019/7/15 0015 上午 11:19
 */
public interface AdminService {
    //带条件的分页查询
    List<TAdmin> listAdmin(String queryContidion);

    //添加用户
    String insertAdmin(TAdmin admin);

    //根据id选择用户
    TAdmin getAdminById(Integer adminId);

    //更新用户
    void updateUser(TAdmin tAdmin);

    //删除用户
    Integer deleteAdmin(List<Integer> idList);
}
