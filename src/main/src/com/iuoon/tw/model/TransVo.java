package com.iuoon.tw.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 基础信息
 * Created by mwuyz on 2016/8/30.
 */
public class TransVo<T> {
    @JsonProperty("Code")
    private String code;
    @JsonProperty("Message")
    private String message;
    @JsonProperty("Params")
    private T params;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getParams() {
        return params;
    }

    public void setParams(T params) {
        this.params = params;
    }
}
