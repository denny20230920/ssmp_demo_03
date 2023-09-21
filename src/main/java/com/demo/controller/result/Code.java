package com.demo.controller.result;

public class Code {

    public static final Integer OK = 20001;
    public static final Integer ERR = 20000;

    public static final Integer SAVE_OK = 20011;
    public static final Integer SAVE_ERR = 20010;

    public static final Integer GET_OK = 20021;
    public static final Integer GET_ERR = 20020;

    public static final Integer UPDATE_OK = 20031;
    public static final Integer UPDATE_ERR = 20030;

    public static final Integer DELETE_OK = 20041;
    public static final Integer DELETE_ERR = 20040;

    public static final Integer SYSTEM_TIMEOUT_ERR = 50001;

    public static final Integer SYSTEM_TOKEN_TIMEOUT_ERR = 50005;//token超时
    public static final Integer SYSTEM_TOKEN_INVALID_ERR = 50006;//token已失效

    public static final Integer BUSINESS_USER_EXISTS_ERR = 60001;//用户名已存在
    public static final Integer BUSINESS_USER_NP_ERR = 60002;//用户名或密码错误
    public static final Integer BUSINESS_USER_NEW_ERR = 60003;//新建用户报错
    public static final Integer BUSINESS_USER_NEXISTS_ERR = 60004;//用户不存在

    public static final Integer BUSINESS_BOOK_MNAME_NULL = 61001;//图书名称不能为空
    public static final Integer BUSINESS_BRANG_NAME_NULL = 61002;//品牌名称不能为空
    public static final Integer BUSINESS_BRANG_DATA_ERR = 61003;//品牌信息不存在

    public static final Integer BUSINESS_CAPTCHA_ERR = 62001;//图片验证码生成异常

    public static final Integer BUSINESS_CAPCHA_SEND_ERR = 63001;//验证码发送失败


    public static final Integer BUSINESS_BOOK_EXISTS_ERR = 70004;//图书名称已存在
    public static final Integer BUSINESS_BOOK_NOEXISTS_ERR = 70005;//图书名称已存在

    public static final Integer SYSTEM_UNKNOW_ERR = 80001;

    public static final Integer BUSINESS_COMMON_PARAM_ERR = 90003;//参数错误

}
