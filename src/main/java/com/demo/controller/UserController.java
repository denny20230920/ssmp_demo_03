package com.demo.controller;

import com.demo.controller.result.Code;
import com.demo.controller.result.JsonResult;
import com.demo.pojo.User;
import com.demo.service.UserSerice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserSerice userSerice;

    //用户登录
    @PostMapping("/login")
    public JsonResult<User> login(@RequestBody User user){

        User loginUser = userSerice.login(user);

        Integer code = loginUser!=null? Code.OK:Code.ERR;

        String msg = loginUser!=null? "success":"fail";

        return new JsonResult<>(code,loginUser,msg);
    }

    //用户注册
    @PostMapping("/register")
    public JsonResult<User> register(@RequestBody User user){

        User register = userSerice.register(user);

        Integer code = register!=null?Code.SAVE_OK:Code.SAVE_ERR;

        String msg = register!=null?"success":"fail";

        return new JsonResult<>(code,register,msg);
    }

}
