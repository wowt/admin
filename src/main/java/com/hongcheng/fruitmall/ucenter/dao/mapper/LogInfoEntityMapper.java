package com.hongcheng.fruitmall.ucenter.dao.mapper;

import java.time.LocalDateTime;

import com.hongcheng.fruitmall.ucenter.entity.LogInfoEntity;

public interface LogInfoEntityMapper {

    LogInfoEntity selectByNameAndPassword(String name,String password);

    LogInfoEntity getByEmail(String email);

    int update(LogInfoEntity entity);

    int insert(LogInfoEntity entity);

}
