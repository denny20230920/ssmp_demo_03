package com.demo.service;

import com.demo.pojo.SmsCode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface SmsCodeService {

    //发送验证码
    String sendCode(String telephone);

    //校验验证码
    boolean checkCode(SmsCode smsCode);

    //发送图片验证码
    void sengImgCode(HttpServletRequest request, HttpServletResponse response);

    //校验图片验证码
    boolean checkImgCode(HttpServletRequest request,String code);

}
