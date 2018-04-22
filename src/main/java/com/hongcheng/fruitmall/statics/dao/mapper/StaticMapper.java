package com.hongcheng.fruitmall.statics.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hongcheng.fruitmall.statics.pojo.qo.StaticQuery;
import com.hongcheng.fruitmall.statics.pojo.vo.StaticVO;

public interface StaticMapper {

    List<StaticVO> getStaticByQuery(@Param("qo") StaticQuery qo);

    Integer countStatic(@Param("qo") StaticQuery qo);
}
