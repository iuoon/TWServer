package com.iuoon.tw.domain;

import com.iuoon.tw.service.OscwxService;
import com.iuoon.tw.util.HttpUtil;
import org.springframework.stereotype.Service;

/**
 * Created by mwuyz on 2016/10/31.
 */
@Service
public class OscwxHandler implements OscwxService {

    @Override
    public String getNewsList(int catalog, int pageIndex, int pageSize) {
        return HttpUtil.getMsg("news?catalog="+catalog+"&pageIndex="+pageIndex+"&pageSize="+pageSize);
    }

    @Override
    public String getNewsDetail(int id) {
        return HttpUtil.getMsg("news?id="+id+"");
    }
}
