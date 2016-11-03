package com.iuoon.tw.controller;

import com.iuoon.tw.service.OscwxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mwuyz on 2016/10/31.
 */
@RestController
public class OscwxController {

    @Autowired
    public OscwxService oscwxService;

    @RequestMapping(value = "/news_list")
    public String getNewsList(@RequestParam  int catalog,@RequestParam int pageIndex,@RequestParam int pageSize){
        return oscwxService.getNewsList(catalog,pageIndex,pageSize);
    }

    @RequestMapping(value = "/news_detail")
    public String getNewsDetail(@RequestParam  int id){
        return oscwxService.getNewsDetail(id);
    }




}
