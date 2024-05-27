//package com.demo;

//import com.demo.utils.TokenUtil;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;

//import java.util.Map;

//@SpringBootTest
//public class toolTest {

//    @Autowired
 //   TokenUtil tokenUtil;

    //@Test
//    public void testToken(){

//        String token = tokenUtil.getToken("10000", "userRole");
//        System.out.println(token);
//        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0aW1lU3RhbXAiOjE2OTUyNjQxNzY3OTYsInVzZXJSb2xlIjoiUk9MRV9BRE1JTiIsInVzZXJJZCI6IuikmuW4iOS_oSJ9.s26gmkC-o1Caiz--CXieFMxiC_-697sdbptjdAcbypQ";

//        Map<String, String> stringStringMap = tokenUtil.parseToken(token);
//       System.out.println(stringStringMap.get("userId"));
//        System.out.println(Long.parseLong(stringStringMap.get("timeStamp")));
//        System.out.println(stringStringMap.get("userRole"));

//    }

//}
