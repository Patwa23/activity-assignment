package com.tacx.assignment.enums;

import java.util.HashMap;
import java.util.Map;

public enum FileType {

    CSV("csv");

    private final String label;

    private static final Map<String, FileType> FILE_TYPE_MAP = new HashMap<>();

    static {
        for (FileType type : values()) {
            FILE_TYPE_MAP.put(type.label, type);
        }
    }

    FileType(final String label) {
        this.label = label;
    }

    public static FileType fromLabel(final String label) {
        return FILE_TYPE_MAP.get(label.toLowerCase());
    }

}

