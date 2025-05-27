package com.example.blinddate.common;


import lombok.Data;

@Data
public class CommonDto<T> {

    //业务上的成功与失败
    private Boolean success = true;

    //返回给前端的信息
    private String message;

    //返回范型数据,自定义类型
    private T content;


}
