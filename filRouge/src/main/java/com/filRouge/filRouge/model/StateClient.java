package com.filRouge.filRouge.model;

public enum StateClient {
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE");

    private String value;
    private StateClient(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
