package com.hongcheng.fruitmall.talke.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hongcheng.fruitmall.common.pojo.PageQuery;
import com.hongcheng.fruitmall.talke.pojo.entity.HealthTalkEntity;

public interface HealthTalkEntityMapper {

    int insert(@Param("entity") HealthTalkEntity entity);

    int delete(@Param("id") Integer id);

    List<HealthTalkEntity> listAll(@Param("qo") PageQuery qo);

    Integer getTotal(@Param("qo") PageQuery qo);
}
