package com.atguigu.atcrowdfunding.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 继承SecurityConfigurerAdapter，重写里面的方法
 * @author Liang Wenjie
 * @version 1.00
 * @time 2019/7/30 0030 下午 10:46
 */
@EnableWebSecurity//开启springSecurity框架
@EnableGlobalMethodSecurity//开启全局方法注解
@Configuration //配置类
public class AtcrowdFundingSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserServiceDetails myUserServiceDetails;

    /**
     * spring框架默认为单例
     * @return
     */
    @Bean("MyPassWordEncoder")
    public BCryptPasswordEncoder getBCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /*public static void main(String[] args) {
        String encode = new BCryptPasswordEncoder().encode("123456");
        System.out.println(encode);
    }*/

    /*配置UserServiceDetails*/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);
        auth.userDetailsService(myUserServiceDetails).passwordEncoder(getBCryptPasswordEncoder());
    }

    /*配置登陆页和放行页*/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);  指定登陆页和登陆成功后去的页面
        http.formLogin().loginPage("/login.jsp").usernameParameter("loginacct").passwordParameter("userpswd").defaultSuccessUrl("/main.html").permitAll();

        //放行静态资源,登陆页
        http.authorizeRequests().antMatchers("/static/**","/login.jsp").permitAll().anyRequest().authenticated();

        //关闭csrf功能
        http.csrf().disable();
    }


}
