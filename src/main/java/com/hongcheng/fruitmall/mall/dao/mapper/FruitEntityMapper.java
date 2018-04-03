package com.hongcheng.fruitmall.mall.dao.mapper;

import java.util.List;

import com.hongcheng.fruitmall.mall.entity.FruitEntity;

public interface FruitEntityMapper {

    FruitEntity selectById(Integer id);

    int insert(FruitEntity entity);

    int update(FruitEntity entity);

    List<FruitEntity> getDealFruits();

    List<FruitEntity> getPushFruits();

    List<FruitEntity> getWillSaleFruits();
}
