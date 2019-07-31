package com.atguigu.atcrowdfunding.service.security;

import com.atguigu.atcrowdfunding.bean.TAdmin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * 此类为继承springSecurity框架的User类。对User
 * 对象进行拓展
 * @author Liang Wenjie
 * @version 1.00
 * @time 2019/7/31 0031 下午 1:43
 */
public class MyUser extends User {

    //定义一个属性
    private TAdmin originalAdmin;

    //自己写的构造器
    public MyUser(TAdmin tAdmin, Collection<? extends GrantedAuthority> authorities){
        super(tAdmin.getUsername(), tAdmin.getUserpswd(), true, true, true, true, authorities);
        originalAdmin = tAdmin;
        originalAdmin.setUserpswd("");  //擦除密码
    }

    //继承的构造器
    public MyUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    //创建originalAdmin的get set方法
    public TAdmin getOriginalAdmin() {
        return originalAdmin;
    }

    public void setOriginalAdmin(TAdmin originalAdmin) {
        this.originalAdmin = originalAdmin;
    }

}
