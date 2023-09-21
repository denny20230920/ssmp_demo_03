package com.demo.service.impl;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CreateCache;
import com.demo.pojo.SmsCode;
import com.demo.service.SmsCodeService;
import com.demo.utils.CodeSmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class SmsCodeServiceImpl implements SmsCodeService {

    @Autowired
    CodeSmsUtil codeSmsUtil;

    @CreateCache(area = "sms",name = "smsCode_",expire = 120)  //默认使用的是远程方案
    //@CreateCache(name = "smsCode_",expire = 120,cacheType = CacheType.LOCAL)//使用本地缓存
    Cache<String,String> jetCache;

    @Override
    public String sendCode(String telephone) {
        String code = codeSmsUtil.opereator(6);
        jetCache.put(telephone,code);
        return code;
    }

    @Override
    public boolean checkCode(SmsCode smsCode) {
        String code = smsCode.getCode();
        String queryCode = jetCache.get(smsCode.getTelephone());
        return code.equals(queryCode);
    }

    @Override
    public void sengImgCode(HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    public boolean checkImgCode(HttpServletRequest request, String code) {
        return false;
    }
}
