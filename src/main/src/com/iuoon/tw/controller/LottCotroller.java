package com.iuoon.tw.controller;

import com.iuoon.tw.service.LottService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mwuyz on 2016/8/31.
 */
@RestController
public class LottCotroller {
    @Autowired
    private LottService lottService;

    /**
     * 获取公告
     * @return
     */
    @RequestMapping(value = "/getNotice")
    public String getNotice(){
        return lottService.getNotice();
    }

}
