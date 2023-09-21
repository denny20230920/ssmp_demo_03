package com.demo.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class Md5PasswordUtil {

    public static String getMd5Password(String password,String salt){

        for (int i = 0; i < 3; i++) {

            password = salt + password + salt;

            password = DigestUtils.md5Hex(password);
        }

        return password;
    }

}
