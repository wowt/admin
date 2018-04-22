package com.hongcheng.fruitmall.productType.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hongcheng.fruitmall.productType.pojo.ProductTypeEntity;

public interface ProductTypeEntityMapper {

    int insert(@Param("entity")ProductTypeEntity entity);

    int deleteById(@Param("id")Integer id);

    List<ProductTypeEntity> list();
}
