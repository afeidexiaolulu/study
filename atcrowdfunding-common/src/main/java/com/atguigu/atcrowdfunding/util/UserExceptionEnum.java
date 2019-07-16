package com.atguigu.atcrowdfunding.util;

/**
 * 用户异常的枚举类
 *
 * @author Liang Wenjie
 * @version 1.00
 * @time 2019/7/16 0016 上午 10:26
 */

public enum UserExceptionEnum {
    //枚举列 每个对象之间用户","进行隔开
    LOGINACCT_EXIST(1,"用户已存在"),

    EMAIL_EXIST(2,"邮箱已存在"),

    LOGINACCT_LOCKED(3, "用户被锁定");


    private int errorCode;

    private String msg;

    //构造器
    UserExceptionEnum(int code, String msg){
        this.errorCode = code;
        this.msg = msg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
