package com.demo.controller;

import com.demo.pojo.proto.MessageUserLogin.MessageUserLoginRequest;
import com.demo.pojo.proto.MessageUserLogin.MessageUserLoginResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/users/v1")
public class UserLoginController {

    @PostMapping(value="/getUserInfo" , produces = "application/x-protobuf")
    public MessageUserLoginResponse userLogin(@RequestBody MessageUserLoginRequest request){

        log.info("请求信息:"+request);

        MessageUserLoginResponse.Builder builder = MessageUserLoginResponse.newBuilder();

        builder.setAccessToken(UUID.randomUUID().toString().toUpperCase()+"_res");

        builder.setUsername(request.getUsername()+"_res");

        return builder.build();
    }

}
