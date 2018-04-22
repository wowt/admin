package com.hongcheng.fruitmall.statics.service;

import com.hongcheng.fruitmall.common.pojo.PageList;
import com.hongcheng.fruitmall.statics.pojo.request.StaticRequest;
import com.hongcheng.fruitmall.statics.pojo.vo.StaticVO;

public interface StaticService {

    /**
     * 按条件统计
     * @param request
     * @return
     */
    PageList<StaticVO> getStaticByQuery(StaticRequest request);
}
