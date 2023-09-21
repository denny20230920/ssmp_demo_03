package com.demo.controller;

import com.demo.controller.result.Code;
import com.demo.controller.result.JsonResult;
import com.demo.pojo.SmsCode;
import com.demo.service.SmsCodeService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/capcha")
public class SmsCodeController {

    @Autowired
    SmsCodeService smsCodeService;

    @GetMapping("/v2/{telephone}")
    public JsonResult<String> sendCode(@PathVariable String telephone){

        String code = smsCodeService.sendCode(telephone);

        return new JsonResult<>(Code.OK,code);
    }

    @GetMapping("/v2")
    public JsonResult<Boolean> checkCode(SmsCode smsCode){

        boolean bool = smsCodeService.checkCode(smsCode);

        return new JsonResult<>(bool?Code.OK:Code.ERR,bool);
    }

}
