package com.atguigu.atcrowdfunding.service.security;

import com.atguigu.atcrowdfunding.bean.TAdmin;
import com.atguigu.atcrowdfunding.bean.TAdminExample;
import com.atguigu.atcrowdfunding.bean.TPermission;
import com.atguigu.atcrowdfunding.bean.TRole;
import com.atguigu.atcrowdfunding.mapper.TAdminMapper;
import com.atguigu.atcrowdfunding.mapper.TPermissionMapper;
import com.atguigu.atcrowdfunding.mapper.TRoleMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

/**
 *
 * 实现此接口 ，用来自定义用户的角色和权限
 * @author Liang Wenjie
 * @version 1.00
 * @time 2019/7/30 0030 下午 11:04
 */
@Service
public class MyUserServiceDetails implements UserDetailsService {

    @Autowired
    private TAdminMapper tAdminMapper;

    @Autowired
    private TRoleMapper tRoleMapper;

    @Autowired
    private TPermissionMapper tPermissionMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询用户
        TAdminExample tAdminExample = new TAdminExample();
        //对example进行包装
        tAdminExample.createCriteria().andLoginacctEqualTo(username);
        //查询用户
        List<TAdmin> tAdmins = tAdminMapper.selectByExample(tAdminExample);
        if(!tAdmins.isEmpty() && tAdmins.size() == 1){
            TAdmin admin = tAdmins.get(0);      //获取用户
            Integer userId = admin.getId();        //用户id
            //查询角色
            List<TRole> roleList = tRoleMapper.listRoleNameByUserId(userId);
            //查询权限
            List<TPermission> permissionList = tPermissionMapper.listPermissionByUserId(userId);
            //封装用户的角色和权限
            //创建权限集合
            HashSet<GrantedAuthority> authorities = new HashSet<>();
            //循环角色
            for (TRole tRole : roleList) {
                if(!StringUtils.isEmpty(tRole.getName())){
                    SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_"+tRole.getName());
                    authorities.add(simpleGrantedAuthority);
                }
            }
            //循环权限
            for (TPermission tPermission : permissionList) {
                if(!StringUtils.isEmpty(tPermission.getName())){
                    SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(tPermission.getName());
                    authorities.add(simpleGrantedAuthority);
                }
            }
            //封装为自定义的User类
            return new MyUser(admin,authorities);
        }
        return null;
    }
}
