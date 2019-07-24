package com.atguigu.atcrowdfunding.controller;

import com.atguigu.atcrowdfunding.bean.TAdmin;
import com.atguigu.atcrowdfunding.bean.TRole;
import com.atguigu.atcrowdfunding.service.AdminService;
import com.atguigu.atcrowdfunding.util.Const;
import com.atguigu.atcrowdfunding.util.IdsUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.deploy.panel.ITreeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.Session;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Liang Wenjie
 * @version 1.00
 * @time 2019/7/14 0014 下午 10:31
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    private static Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private AdminService adminService;

    /**
     * 分页查询
     * @return
     */
    @GetMapping("/index.html")
    public String toUserIndex(@RequestParam(name = "pageNo",defaultValue = "1")  String pageNo,
                              @RequestParam(name = "pageSize", defaultValue = "4") String pageSize,
                              @RequestParam(name = "queryCondition", defaultValue = "") String queryCondition,
                              Model model, HttpSession session){

        //因表单同步提交会刷新页面 导致查询条件变空，所有将查询条件放到session中 在前台回显
        session.setAttribute(Const.QUERTY_CONDITION, queryCondition);
        session.setAttribute(Const.CURRENT_PAGE, pageNo);

        //pageNo, pageSize
        PageHelper.startPage(new Integer(pageNo), new Integer(pageSize));
        //进行分页查询
        List<TAdmin> adminList =  adminService.listAdmin(queryCondition);

        //包装为pageInfo对象
        PageInfo<TAdmin> adminPageInfo = new PageInfo<>(adminList, Const.NAV_BARPAGE);

        model.addAttribute(Const.PAGE_INFO, adminPageInfo);

        logger.info("跳转到用户首页");
        return "admin/user/index";
    }

    //用户添加页面跳转
    @GetMapping("/toUserAdd.html")
    public String toUserAdd(){

        logger.info("跳往添加用户页面");
        return "admin/user/add";
    }

    //用户添加
    @PostMapping("/userAdd")
    public String userAdd(TAdmin admin, Model model){
        HashMap<String, Object> msgMap = new HashMap<>();
        String msg = adminService.insertAdmin(admin);
        if(msg.equals("用户名已存在")){
            msgMap.put("loginacctMsg",msg);
            model.addAttribute(Const.ADD_MSG, msgMap);
            //返回的是网页地址
            return "admin/user/add";
        }
        if(msg.equals("邮箱已注册")){
            msgMap.put("emailMsg",msg);
            model.addAttribute(Const.ADD_MSG, msgMap);
            return "admin/user/add";
        }
        //重定向  浏览器解析   到用户最后一页，因为合理化配置，所以会到最后一页
        return "redirect:/admin/index.html?pageNo="+Integer.MAX_VALUE/2;
    }

    //去往修改页面
    @GetMapping("/toUpdate.html")
    public String toUpdate(Integer adminId, Model model){
        //通过id查询要修改的用户
        TAdmin admin =  adminService.getAdminById(adminId);
        //放入model中回显
        model.addAttribute(Const.ADMIN, admin);
        return "admin/user/update";
    }

    @PostMapping("/userUpdate")
    public String userUpdate(TAdmin tAdmin, HttpSession session, Model model){
        //先查登陆名

        //查邮箱

        //更新
        adminService.updateUser(tAdmin);

        //更新的用户信息在第几页，更新后仍回到第几页
        model.addAttribute("queryCondition", session.getAttribute(Const.QUERTY_CONDITION));
        model.addAttribute("pageNo", session.getAttribute(Const.CURRENT_PAGE));
        return "redirect:/admin/index.html";

    }

    //用户删除
    @GetMapping("/delete")
    public String deleteUser(String ids, HttpSession session, Model model){

        List<Integer> idList = new ArrayList<>();
        //即将接收到的ids进行拆分 放入list中
        String[] idArray = ids.split(",");
        for (String id : idArray) {
            try {
                idList.add(Integer.parseInt(id));
            } catch (NumberFormatException e) {
                e.printStackTrace();
                logger.error("删除的id转换异常");
            }
        }
        //将用户删除
        Integer deleteNum = adminService.deleteAdmin(idList);
        //删除的用户信息在第几页，更新后仍回到第几页
        model.addAttribute("queryCondition", session.getAttribute(Const.QUERTY_CONDITION));
        model.addAttribute("pageNo", session.getAttribute(Const.CURRENT_PAGE));
        return "redirect:/admin/index.html";
    }



    //角色分配页面跳转
    @GetMapping("/toAssignRole.html")
    public String toAssignRole(Integer adminId, Model model, HttpSession session){
        //将用户id放入session中
        session.setAttribute(Const.ADDMIN_ID ,adminId);

        //查已分配和未分配的角色
        List<TRole> assignRoles = adminService.getAssignRole(adminId);
        List<TRole> unassignRoles = adminService.getUnassignRole(adminId);

        model.addAttribute("assignRoles", assignRoles);
        model.addAttribute("unassignRoles", unassignRoles);

        return "admin/user/assignRole";
    }


    //添加角色，减少角色
    @GetMapping("/assignRole")
    public String assignRole(String ids, String ops, HttpSession session){
        String adminId = String.valueOf(session.getAttribute(Const.ADDMIN_ID));
        //将ids取出
        List<Integer> idsList = IdsUtil.ParseIds(ids);

        //增加
        if("add".equals(ops)){
            adminService.assignRole(adminId, idsList);
        }
        //删除
        if("delete".equals(ops)){
            adminService.deleteRole(adminId, idsList);
        }

        return "redirect:/admin/toAssignRole.html?adminId="+adminId;
    }

}


