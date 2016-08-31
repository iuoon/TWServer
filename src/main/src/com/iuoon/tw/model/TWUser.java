package com.iuoon.tw.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 忽略空值
 * Created by mwuyz on 2016/8/30.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class TWUser {
    @JsonProperty("UserName")
    private String userName; //用户名
    @JsonProperty("Name")
    private String name; //真实姓名
    @JsonProperty("Tel")
    private String tel;  //手机号
    @JsonProperty("IdCard")
    private String idCard; //身份证号
    @JsonProperty("Password")
    private String password;//密码

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
