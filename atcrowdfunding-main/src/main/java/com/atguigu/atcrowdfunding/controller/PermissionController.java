package com.atguigu.atcrowdfunding.controller;

import com.atguigu.atcrowdfunding.bean.TPermission;
import com.atguigu.atcrowdfunding.service.PermissionService;
import com.atguigu.atcrowdfunding.util.IdsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限管理的controller
 * @author Liang Wenjie
 * @version 1.00
 * @time 2019/7/22 0022 下午 10:25
 */

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    //获取所有的权限
    @GetMapping("/getAllPermission")
    @ResponseBody
    public List<TPermission> getAllPermission(){
        List<TPermission> permissions =  permissionService.getAllPermission();
        return permissions;
    }

    //分配角色
    @RequestMapping("/assignPermission")
    @ResponseBody
    public boolean assignPermission(String Rid, String ids){

        List<Integer> idsList = IdsUtil.ParseIds(ids);

        permissionService.assignPermission(Rid,idsList);
        return true;
    }

    @ResponseBody
    @RequestMapping("/getPermissionByRoleId")
    public List<TPermission> getPermissionByRoleId(Integer roleId){
        return permissionService.getPermissionByRoleId(roleId);
    }


}
