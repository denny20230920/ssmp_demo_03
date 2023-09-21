package com.demo.utils;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class CodeSmsUtil {

    //获取随机生成的n位数
    public String opereator(int n){

        String chars = "abcdefghijklmnopqrstuvwxyz0123456789";

        StringBuffer stringBuffer = new StringBuffer();

        Random random = new Random();
        //循环n次，每次获取一个随机数
        for (int i = 0; i < n; i++) {
            int index = random.nextInt(chars.length());
            stringBuffer.append(chars.charAt(index));
        }

        return stringBuffer.toString();
    }

}
