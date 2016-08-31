package com.iuoon.tw.domain;

/**
 * Created by mwuyz on 2016/8/30.
 */
public class BaseHandler{

    public String getReturnMessage(String code){
        String message="";
        if (code.equals("0000")){
            message="处理成功";
        }else if (code.equals("0001")){
            message="没有数据";
        }else if (code.equals("0002")){
            message="登陆失败";
        }else{
            message="系统异常";
        }
        return message;
    }

    //判断是否登陆（对需要验证登陆的接口进行验证）
    public boolean isLogin(String key){
        //从缓存获取
        return true;
    }

}
