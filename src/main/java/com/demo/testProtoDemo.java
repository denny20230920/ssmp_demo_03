package com.demo;

import com.demo.pojo.proto.DemoProto;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;

import java.util.Arrays;

public class testProtoDemo {

    public static void main(String[] args) {

        //初始化数据
        DemoProto.Demo.Builder demo = DemoProto.Demo.newBuilder();
        demo.setId(1)
                .setCode("001")
                .setName("张三")
                .build();

        //序列化数据
        DemoProto.Demo build = demo.build();
        //转化成字节数组
        byte[] bytes = build.toByteArray();
        System.out.println("protobuf数据bytes[]:"+ Arrays.toString(bytes));
        System.out.println("protobuf序列化大小:"+bytes.length);

        DemoProto.Demo demo1 = null;
        String jsonObject = null;

        try {
            //反序列化
            demo1 = DemoProto.Demo.parseFrom(bytes);
            //转 json
            jsonObject = JsonFormat.printer().print(demo1);
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Json格式化结果:\n"+jsonObject);
        System.out.println("Json格式化数据大小:"+jsonObject.getBytes().length);
    }

}
