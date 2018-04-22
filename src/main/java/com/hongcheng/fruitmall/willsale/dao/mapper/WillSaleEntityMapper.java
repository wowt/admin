package com.hongcheng.fruitmall.willsale.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hongcheng.fruitmall.willsale.pojo.qo.WillSaleQO;
import com.hongcheng.fruitmall.willsale.pojo.vo.WillSaleInfo;
import com.hongcheng.fruitmall.willsale.pojo.vo.WillSaleVO;

public interface WillSaleEntityMapper {

    List<WillSaleInfo> selectInfoByProductId(@Param("productId") Integer productId);

    List<WillSaleVO> getWillList(@Param("qo")WillSaleQO qo);

    Integer getWillListCount(@Param("qo")WillSaleQO qo);

    int deleteByProductId(@Param("productId") Integer productId);
}
