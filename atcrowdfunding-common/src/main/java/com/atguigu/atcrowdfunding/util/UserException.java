package com.atguigu.atcrowdfunding.util;

/**
 *
 * 自定义异常
 * @author Liang Wenjie
 * @version 1.00
 * @time 2019/7/16 0016 上午 10:23
 */

public class UserException extends RuntimeException {

    public UserException(UserExceptionEnum exceptionEnum){
        //将异常枚举的信息给自定义异常
        super(exceptionEnum.getMsg());
    }
}
