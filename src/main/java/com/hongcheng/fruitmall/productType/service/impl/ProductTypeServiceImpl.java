package com.hongcheng.fruitmall.productType.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongcheng.fruitmall.common.pojo.PageList;
import com.hongcheng.fruitmall.productType.dao.mapper.ProductTypeEntityMapper;
import com.hongcheng.fruitmall.productType.pojo.ProductTypeEntity;
import com.hongcheng.fruitmall.productType.service.ProductTypeService;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {

    @Autowired
    ProductTypeEntityMapper mapper;

    @Override
    public int insert(ProductTypeEntity entity) {
        return mapper.insert(entity);
    }

    @Override
    public PageList<ProductTypeEntity> getAll() {
        List<ProductTypeEntity> list = mapper.list();
        return new PageList<>(list.size(),list);
    }

    @Override
    public int deleteById(Integer id) {
        return mapper.deleteById(id);
    }
}
