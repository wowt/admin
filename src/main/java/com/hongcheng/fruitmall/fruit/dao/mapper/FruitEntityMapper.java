package com.hongcheng.fruitmall.fruit.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hongcheng.fruitmall.fruit.pojo.entity.FruitEntity;
import com.hongcheng.fruitmall.fruit.pojo.qo.FruitQuery;

public interface FruitEntityMapper {

    FruitEntity selectById(Integer id);

    int insert(FruitEntity entity);

    int update(FruitEntity entity);

    List<FruitEntity> getDealFruits();

    List<FruitEntity> getPushFruits();

    List<FruitEntity> getWillSaleFruits();

    List<FruitEntity> getListByQO(@Param("qo") FruitQuery qo);

    int getTotalByQO(@Param("qo") FruitQuery qo);

    Integer updateState(@Param("id") Integer id, @Param("state") String state);
}
