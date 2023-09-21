package com.demo.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
//支持序列化和反序列化
public class User implements Serializable {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;//   bigint comment '用户ID',

    private String username;//   varchar(20) not null unique comment '用户名',

    private String password;//   char(32) not null comment '密码',

    private String salt;//   char(36) not null comment '盐值',

    private Integer gender;//   int comment '性别:1-男,2-女',

    private Integer age;//   int comment '年龄',

    private String avatar;//   varchar(50) comment '头像',

    @TableLogic(value = "1",delval = "2")
    private Integer deleted;//   int default 1 comment '是否删除:1-未删除,2-已删除',

    private Integer disabled;//   int default 1 comment '是否禁用:1-未禁用,2-已禁用',

    private Date birthday;//   date comment '生日',

    @TableField(value = "create_time")
    private String createTime;//   datetime comment '创建时间',

    @TableField(value = "create_user")
    private String createUser;//   varchar(20) comment '创建人',

    @TableField(value = "update_time")
    private String updateTime;//   datetime comment '更新时间',

    @TableField(value = "update_user")
    private String updateUser;//   varchar(20) comment '更新人',

    @Version
    private Integer version;//   int default 1 comment '乐观锁控制字段',

    @TableField(exist = false)
    private String token; //临时字段，不存到数据库

}
