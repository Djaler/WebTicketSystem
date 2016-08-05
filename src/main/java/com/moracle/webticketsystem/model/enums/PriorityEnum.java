package com.moracle.webticketsystem.model.enums;

import java.util.Map;

/**
 * Created by boggard on 05.08.2016.
 */
public enum PriorityEnum {
    LOW("Low"), MEDIUM("Medium"), HIGH("High"), CRITICAL("Critical");
    private static final String[] priorities;
    private static Map<String, Integer> idMap;

    static {
        priorities = new String[4];
        priorities[0] = PriorityEnum.LOW.toString();
        priorities[1] = PriorityEnum.MEDIUM.toString();
        priorities[2] = PriorityEnum.HIGH.toString();
        priorities[3] = PriorityEnum.CRITICAL.toString();
    }

    private final String str;

    PriorityEnum(String str) {
        this.str = str;
    }

    public static String[] getPriorities() {
        return priorities;
    }

    public static PriorityEnum toEnum(String priority) {
        PriorityEnum priorityEnum = null;
        if (PriorityEnum.LOW.toString().equals(priority)) {
            priorityEnum = PriorityEnum.LOW;
        }
        if (PriorityEnum.MEDIUM.toString().equals(priority)) {
            priorityEnum = PriorityEnum.MEDIUM;
        }
        if (PriorityEnum.HIGH.toString().equals(priority)) {
            priorityEnum = PriorityEnum.HIGH;
        }
        if (PriorityEnum.CRITICAL.toString().equals(priority)) {
            priorityEnum = PriorityEnum.CRITICAL;
        }
        return priorityEnum;
    }

    public static void setIdMap(Map<String, Integer> idMap) {
        PriorityEnum.idMap = idMap;
    }

    @Override
    public String toString() {
        return str;
    }

    public int toID() {
        return idMap.get(str);
    }
}
