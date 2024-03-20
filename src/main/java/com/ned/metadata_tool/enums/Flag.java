package com.ned.metadata_tool.enums;

public enum Flag {
    ACTIVE,
    DELETED;

    private Flag() {
    }

    public static Flag fromString(String value) {
        return ACTIVE.name().equalsIgnoreCase(value)?ACTIVE:(DELETED.name().equalsIgnoreCase(value)?DELETED:null);
    }
}