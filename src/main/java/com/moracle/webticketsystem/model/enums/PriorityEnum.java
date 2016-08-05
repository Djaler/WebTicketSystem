package com.moracle.webticketsystem.model.enums;

/**
 * Created by boggard on 05.08.2016.
 */
public enum PriorityEnum {
    LOW("Low"), MEDIUM("Medium"), HIGHT("Hight"), CRITICAL("Critical");
    private final String str;
    private static final String[] priorities;
    static {
        priorities = new String[4];
        priorities[0] = PriorityEnum.LOW.toString();
        priorities[1] = PriorityEnum.MEDIUM.toString();
        priorities[2] = PriorityEnum.HIGHT.toString();
        priorities[3] = PriorityEnum.CRITICAL.toString();
    }

    PriorityEnum(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return str;
    }

    public static String[] getPriorities(){
        return priorities;
    }
}
