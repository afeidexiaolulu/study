package com.atguigu.atcrowdfunding.service.impl;


import com.atguigu.atcrowdfunding.bean.TAdmin;
import com.atguigu.atcrowdfunding.bean.TAdminExample;
import com.atguigu.atcrowdfunding.mapper.TAdminMapper;
import com.atguigu.atcrowdfunding.service.LoginService;
import com.atguigu.atcrowdfunding.util.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;



@Service
@Transactional
public class LoginServiceImpl implements LoginService {

    //加logger日志
    private static Logger logger=LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    private TAdminMapper adminMapper;

    //登录
    @Override
    public TAdmin login(TAdmin tAdmin) {
        //加密密码
        String digestPassWord = MD5Util.digestPassWord(tAdmin.getUserpswd());
        logger.info("用户正在登陆验证，用户名{}，密码{}",tAdmin.getLoginacct(),digestPassWord);
        //创建example
        TAdminExample example = new TAdminExample();

        //包装example
        example.createCriteria().andLoginacctEqualTo(tAdmin.getLoginacct())
                .andUserpswdEqualTo(digestPassWord);

        List<TAdmin> tAdmins = adminMapper.selectByExample(example);
        return tAdmins.size()==1?tAdmins.get(0):null;
    }
}
