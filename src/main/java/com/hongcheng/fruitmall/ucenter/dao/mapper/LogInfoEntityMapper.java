package com.hongcheng.fruitmall.ucenter.dao.mapper;


import com.hongcheng.fruitmall.ucenter.entity.LogInfoEntity;

public interface LogInfoEntityMapper {

    LogInfoEntity getByEmail(String email);

    int update(LogInfoEntity entity);

    int setState(String email, String state);

    int insert(LogInfoEntity entity);

}
