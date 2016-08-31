package com.iuoon.tw.service;

import com.iuoon.tw.model.TWUser;

/**
 * Created by mwuyz on 2016/8/30.
 */
public interface UserService {
     //登陆
     String login( String userName, String password);
     //注册
     String register(TWUser user);
     //下发短信验证码
     String getCheckMaskCode(String tel);
     //用户信息更新
     String updateUserInfo(TWUser user);

}
