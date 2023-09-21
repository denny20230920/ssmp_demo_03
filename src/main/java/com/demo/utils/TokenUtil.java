package com.demo.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TokenUtil {

    @Value("${jwt.secretKey}")
    private String secretKey;

//    private int expiration;

    //生成token值的方法
    public String getToken(String userId,String userRole){

        //System.out.println("配置中获取的secretKey值："+secretKey);
        //这个放到payload里面
        //生成token
        String token = JWT.create()
                          .withClaim("userId", userId)
                          .withClaim("userRole", userRole)
                          .withClaim("timeStamp", ""+System.currentTimeMillis())
                          .sign(Algorithm.HMAC256(secretKey));

        return token;
    }

    //解析token
    public Map<String,String> parseToken(String token){
        HashMap<String,String> hashMap = new HashMap<>();
        DecodedJWT decodedJWT = JWT.require(Algorithm
                                   .HMAC256(secretKey))
                                   .build()
                                   .verify(token);

        Claim userId = decodedJWT.getClaim("userId");
        Claim userRole = decodedJWT.getClaim("userRole");
        Claim timeStamp = decodedJWT.getClaim("timeStamp");

        hashMap.put("userId",userId.asString());
        hashMap.put("userRole",userRole.asString());
        hashMap.put("timeStamp",timeStamp.asString());

        return hashMap;
    }

}
