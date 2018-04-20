package com.hongcheng.fruitmall.common.constants;

public enum FruitState {

    PREPUBLISH("prepublish"),
    PUBLISH("publish"),
    WILL("will"),
    PUSH("push");

    private String value;

    FruitState(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
