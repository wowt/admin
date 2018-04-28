package com.hongcheng.fruitmall.talke.service;

import java.util.List;

import com.hongcheng.fruitmall.common.pojo.PageForm;
import com.hongcheng.fruitmall.common.pojo.PageList;
import com.hongcheng.fruitmall.talke.pojo.entity.HealthTalkEntity;

public interface HealthTalkService {

    /**
     * 查询全部
     * @param form
     * @return
     */
    PageList<HealthTalkEntity> getList(PageForm form);

    /**
     * 删除
     * @param id
     * @return
     */
    Integer deleteById(Integer id);

    /**
     * 创建
     * @param entity
     * @return
     */
    Integer createTalk(HealthTalkEntity entity);
}
