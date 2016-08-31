package com.iuoon.tw.domain;

import com.iuoon.tw.common.Config;
import com.iuoon.tw.model.TWUser;
import com.iuoon.tw.model.TransVo;
import com.iuoon.tw.service.UserService;
import com.iuoon.tw.util.JSONUtils;
import org.springframework.stereotype.Service;

/**
 * 用户操作处理
 * Created by mwuyz on 2016/8/30.
 */
@Service
public class UserHandler  extends BaseHandler implements UserService {


    @Override
    public String login(String userName, String password) {
        TransVo<TWUser> vo =new TransVo<TWUser>();
        //这里就简明做下处理 //实际项目可自行修改逻辑
        if ("iuoon".equals(userName)&&"123456".equals(password)){
            vo.setCode(Config.success_code);
            TWUser user=new TWUser();
            user.setUserName(userName);
            user.setPassword(password);
            user.setName("iuoon");
            vo.setParams(user);
            vo.setMessage(getReturnMessage(Config.success_code));
            /***************/
            //这里可以存入缓存
        }else{
            vo.setCode(Config.loginfail_code);
            vo.setMessage(getReturnMessage(Config.loginfail_code));
        }
        return JSONUtils.obj2json(vo);
    }

    @Override
    public String register(TWUser user) {
        return null;
    }

    @Override
    public String getCheckMaskCode(String tel) {
        return null;
    }

    @Override
    public String updateUserInfo(TWUser user) {
        return null;
    }


}
