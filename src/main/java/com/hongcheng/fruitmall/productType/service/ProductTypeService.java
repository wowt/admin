package com.hongcheng.fruitmall.productType.service;

import java.util.List;

import com.hongcheng.fruitmall.common.pojo.PageList;
import com.hongcheng.fruitmall.productType.pojo.ProductTypeEntity;

public interface ProductTypeService {

    int insert(ProductTypeEntity entity);

    PageList<ProductTypeEntity> getAll();

    int deleteById(Integer id);
}
