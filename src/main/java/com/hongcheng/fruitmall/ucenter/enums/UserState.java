package com.hongcheng.fruitmall.ucenter.enums;

public enum UserState {

    ACTIVE("active"),
    NACTIVE("N-active");

    private String value;

    public String getValue() {
        return value;
    }

    UserState(String value) {
        this.value = value;
    }
}
