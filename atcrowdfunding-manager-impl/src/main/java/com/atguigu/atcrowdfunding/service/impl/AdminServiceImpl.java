package com.atguigu.atcrowdfunding.service.impl;

import com.atguigu.atcrowdfunding.bean.TAdmin;
import com.atguigu.atcrowdfunding.bean.TAdminExample;
import com.atguigu.atcrowdfunding.mapper.TAdminMapper;
import com.atguigu.atcrowdfunding.service.AdminService;
import com.atguigu.atcrowdfunding.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Liang Wenjie
 * @version 1.00
 * @time 2019/7/15 0015 上午 11:19
 */
@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private TAdminMapper tAdminMapper;

    private static Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

    //带条件的分页查询
    @Override
    public List<TAdmin> listAdmin(String queryCondition) {
        //三个条件 中间为or
        TAdminExample example = new TAdminExample();
        //如果条件不为空
        if(!StringUtils.isEmpty(queryCondition)){

            TAdminExample.Criteria criteria1 = example.createCriteria();
            criteria1.andLoginacctLike("%" + queryCondition + "%");

            TAdminExample.Criteria criteria2 = example.createCriteria();
            criteria2.andUsernameLike("%" + queryCondition + "%");

            TAdminExample.Criteria criteria3 = example.createCriteria();
            criteria3.andEmailLike("%" + queryCondition + "%");

            //包装条件
            example.or(criteria2);
            example.or(criteria3);

        }
        List<TAdmin> tAdminList = tAdminMapper.selectByExample(example);

        return tAdminList;
    }

    //添加用户
    @Override
    public String insertAdmin(TAdmin admin) {

        //判断用户名的唯一性
        TAdminExample example = new TAdminExample();
        TAdminExample.Criteria criteria = example.createCriteria();
        criteria.andLoginacctEqualTo(admin.getLoginacct());
        if(tAdminMapper.selectByExample(example).size() != 0){
            return "用户名已存在";
        }

        //判断邮箱的唯一性
        TAdminExample example1 = new TAdminExample();
        TAdminExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andEmailEqualTo(admin.getEmail());
        if(tAdminMapper.selectByExample(example1).size() != 0){
            return "邮箱已注册";
        }
        //插入  密码为固定密码   创建时间
        //密码加密
        String userpswd = "123";
        String digestPassWord = MD5Util.digestPassWord(userpswd);
        admin.setUserpswd(digestPassWord);
        SimpleDateFormat sDF = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        admin.setCreatetime(sDF.format(new Date()));
        tAdminMapper.insert(admin);
        return "插入成功";
    }

    //根据id选择用户
    @Override
    public TAdmin getAdminById(Integer adminId) {
        return tAdminMapper.selectByPrimaryKey(adminId);
    }

    //更新用户
    @Override
    public void updateUser(TAdmin tAdmin) {
        tAdminMapper.updateByPrimaryKeySelective(tAdmin);
    }

    //删除用户
    @Override
    public Integer deleteAdmin(List<Integer> idList) {
        //创建example
        TAdminExample tAdminExample = new TAdminExample();
        tAdminExample.createCriteria().andIdIn(idList);
        //删除
        return tAdminMapper.deleteByExample(tAdminExample);
    }
}
