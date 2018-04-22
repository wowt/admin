package com.hongcheng.fruitmall.willsale.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongcheng.fruitmall.common.beanMapper.BeanMapperFactory;
import com.hongcheng.fruitmall.common.pojo.PageList;
import com.hongcheng.fruitmall.fruit.service.FruitService;
import com.hongcheng.fruitmall.willsale.dao.mapper.WillSaleEntityMapper;
import com.hongcheng.fruitmall.willsale.pojo.qo.WillSaleQO;
import com.hongcheng.fruitmall.willsale.pojo.vo.WillSaleInfo;
import com.hongcheng.fruitmall.willsale.pojo.vo.WillSaleVO;
import com.hongcheng.fruitmall.willsale.request.WillSaleRequest;
import com.hongcheng.fruitmall.willsale.service.WillSaleService;

@Service
public class WillSaleServiceImpl implements WillSaleService {

    @Autowired
    private WillSaleEntityMapper mapper;

    @Autowired
    private FruitService fruitService;

    @Override
    public List<WillSaleInfo> getInfoByProductId(Integer productId) {
        return mapper.selectInfoByProductId(productId);
    }

    @Override
    public PageList<WillSaleVO> getListByQuery(WillSaleRequest request) {
        WillSaleQO qo = createQO(request);
        return new PageList<>(mapper.getWillListCount(qo),mapper.getWillList(qo));
    }

    @Override
    public Integer deleteByProductId(Integer productId) {
        return mapper.deleteByProductId(productId);
    }

    @Override
    public Integer expired(Integer productId) {
        fruitService.removeWill(productId);
        mapper.deleteByProductId(productId);
        return productId;
    }

    private WillSaleQO createQO(WillSaleRequest request) {
        WillSaleQO qo = BeanMapperFactory.getMapperFacade().map(request, WillSaleQO.class);
        qo.paging(request);
        return qo;
    }
}
