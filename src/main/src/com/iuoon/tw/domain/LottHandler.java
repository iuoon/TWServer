package com.iuoon.tw.domain;

import com.iuoon.tw.common.Config;
import com.iuoon.tw.model.TWNotice;
import com.iuoon.tw.model.TransVo;
import com.iuoon.tw.service.LottService;
import com.iuoon.tw.util.JSONUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mwuyz on 2016/8/31.
 */
@Service
public class LottHandler extends BaseHandler implements LottService{

    @Override
    public String getNotice() {
        TransVo<List<TWNotice>> vo =new TransVo<List<TWNotice>>();
        List<TWNotice> list=new ArrayList<TWNotice>();
        TWNotice n1=new TWNotice();
        n1.setNoticeLink("http://www.baidu.com");
        n1.setNoticeTitle("百度一下，你就知道");
        list.add(n1);
        vo.setCode(Config.success_code);
        vo.setMessage(getReturnMessage(Config.success_code));
        vo.setParams(list);
        return JSONUtils.obj2json(vo);
    }
}
