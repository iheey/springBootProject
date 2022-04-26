package org.com.entity;

import lombok.Data;

@Data
public class RespBean {

    private RespBean(Integer code, String message, Object obj){
        this.code=code;
        this.message=message;
        this.obj=obj;
    }

    //这是编码
    private Integer code;
    //返回的提示信息
    private String message;
    //返回的结果
    private Object obj;

    public static RespBean accuess(Object obj){
        return accuess(200,"成功",obj);
    }
    public static RespBean accuess(String message, Object obj){
        return accuess(200,message,obj);
    }

    public static RespBean accuess(Integer code, String message, Object obj){
        return new RespBean(code,message,obj);
    }

    public static RespBean error(){
        return error(500,"系统错误",null);
    }
    public static RespBean error(String message){return error(500,message,null);}
    public static RespBean error(Object obj){
        return error(500,"系统错误",obj);
    }
    public static RespBean error(String message, Object obj){
        return error(500,message,obj);
    }

    public static RespBean error(Integer code, String message, Object obj){
        return new RespBean(code,message,obj);
    }
}