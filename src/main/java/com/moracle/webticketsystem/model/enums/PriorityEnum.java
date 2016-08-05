package com.moracle.webticketsystem.model.enums;

/**
 * Created by boggard on 05.08.2016.
 */
public enum PriorityEnum {
    LOW("Low"), MEDIUM("Medium"), HIGH("High"), CRITICAL("Critical");
    private static final String[] priorities;

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

    @Override
    public String toString() {
        return str;
    }
}
