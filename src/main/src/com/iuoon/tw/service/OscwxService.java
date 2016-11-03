package com.iuoon.tw.service;

/**
 * Created by mwuyz on 2016/10/31.
 */
public interface OscwxService {

    public String getNewsList(int catalog,int pageIndex,int pageSize);

    public String getNewsDetail(int id);
}
