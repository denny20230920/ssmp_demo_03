package com.demo.testController;

import com.demo.pojo.proto.MessageUserLogin;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.entity.ByteArrayEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

//@SpringBootTest
public class UserLoginController {

   // @Test
    public void test() {

        MessageUserLogin.MessageUserLoginRequest.Builder builder = MessageUserLogin.MessageUserLoginRequest.newBuilder();
        builder.setUsername("张三");
        builder.setPassword("123456");

        MessageUserLogin.MessageUserLoginRequest request = builder.build();
        System.out.println(builder);

        byte[] bytes = request.toByteArray();

//        System.out.println("protobuf数据bytes[]:"+ Arrays.toString(bytes));
//        System.out.println("protobuf序列化大小:"+bytes.length);

        //将二进制数据打印成文件，作为jmeter的请求参数
        File file = new File("D:\\login.pb") ; // 建立文件

         try (FileOutputStream fos = new FileOutputStream(file)){

             if (!file.exists()) file.createNewFile() ;

                fos.write(request.toByteArray());
                System.out.println("Successfully written data to the file");

                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

    }
}
