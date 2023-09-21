package com.demo.pojo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;


@Data
public class SmsCode {

    @Length(min = 9,max = 20,message = "手机号码最小位数{min},最大位数{max}")
    private String telephone;

    private String code;

}
