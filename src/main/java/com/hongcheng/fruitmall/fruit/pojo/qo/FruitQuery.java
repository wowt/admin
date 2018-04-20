package com.hongcheng.fruitmall.fruit.pojo.qo;

import com.hongcheng.fruitmall.common.pojo.PageQuery;

/**
 * @author wanghongcheng 2018/04/14
 * fruit查询对象
 */
public class FruitQuery extends PageQuery {

    private String keyWord;

    private Integer productType;

    private String state;

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
