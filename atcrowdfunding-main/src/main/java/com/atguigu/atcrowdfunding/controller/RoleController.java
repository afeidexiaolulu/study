package com.atguigu.atcrowdfunding.controller;

import com.atguigu.atcrowdfunding.bean.TRole;
import com.atguigu.atcrowdfunding.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Liang Wenjie
 * @version 1.00
 * @time 2019/7/17 0017 上午 10:24
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 角色模块的用户跳转
     * @return
     */
    @RequestMapping("/index.html")
    public String toRoleList(){

        return "admin/role/index";
    }


    /**
     * 用户模块带条件的分页查询
     *
     */
    @RequestMapping("/listRole")
    @ResponseBody
    public PageInfo<TRole> listRole(@RequestParam(name="pageNo", defaultValue = "1") Integer pageNo,
                                    @RequestParam(name="pageSize", defaultValue = "5") Integer pageSize,
                                    @RequestParam(name = "queryCondition", defaultValue = "") String queryCondition){
        //pageHelper的使用
        PageHelper.startPage(pageNo, pageSize);

        List<TRole> roleList = roleService.listRoleByCondition(queryCondition);

        PageInfo<TRole> tRolePageInfo = new PageInfo<>(roleList,5);

        return tRolePageInfo;
    }


    /**
     * 角色新增保存
     */
    @RequestMapping("/addRole")
    @ResponseBody
    public Object addRole(String roleName){

        //保存
        int save = roleService.save(roleName);

        return (save == 1 );
    }

    /**
     * 根据id查询角色
     */
    @ResponseBody
    @RequestMapping("/getRole")
    public TRole getRole(Integer id){

        return roleService.getRole(id);

    }

    /**
     * 更新按钮
     *
     */
    @PostMapping("/updateRole")
    @ResponseBody
    public boolean updateRole(TRole role){
       int num = roleService.updateRole(role);
       return (num == 1);
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ResponseBody
    public boolean deleteRole(Integer ids){
        int num = roleService.deleteRole(ids);
        return (num == 1);
    }
}
