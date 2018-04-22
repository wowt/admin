package com.hongcheng.fruitmall.willsale.service;

import java.util.List;

import com.hongcheng.fruitmall.common.pojo.PageList;
import com.hongcheng.fruitmall.willsale.pojo.qo.WillSaleQO;
import com.hongcheng.fruitmall.willsale.pojo.vo.WillSaleInfo;
import com.hongcheng.fruitmall.willsale.pojo.vo.WillSaleVO;
import com.hongcheng.fruitmall.willsale.request.WillSaleRequest;

public interface WillSaleService {

    List<WillSaleInfo> getInfoByProductId(Integer productId);

    PageList<WillSaleVO> getListByQuery(WillSaleRequest request);

    Integer deleteByProductId(Integer productId);

    Integer expired(Integer productId);
}
