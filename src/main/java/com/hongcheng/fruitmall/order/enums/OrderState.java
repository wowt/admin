package com.hongcheng.fruitmall.order.enums;

/**
 * @author wanghongcheng 2018/04/18
 * 订单状态枚举类
 */
public enum  OrderState {

    SUBMITTED("submitted"),
    ACCEPTED("accepted"),
    REFUSED("refused"),
    DISPATCHING("dispatching"),
    SIGNED("signed");

    private String value;

    OrderState(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
