package com.moracle.webticketsystem.model.enums;

import java.util.Map;

/**
 * Created by djaler on 05.08.16.
 */
public enum StatusEnum {
    NEW("New"), CONFIRMED("Confirmed"), INVALID("Invalid"), IN_PROGRESS("In progress"), FIXED("Fixed");
    private static final String[] statuses;
    private static Map<String, Integer> idMap;

    static {
        statuses = new String[5];
        statuses[0] = StatusEnum.NEW.toString();
        statuses[1] = StatusEnum.CONFIRMED.toString();
        statuses[2] = StatusEnum.INVALID.toString();
        statuses[3] = StatusEnum.INVALID.toString();
        statuses[4] = StatusEnum.FIXED.toString();
    }

    private final String str;

    StatusEnum(String str) {
        this.str = str;
    }

    public static String[] getStatuses() {
        return statuses;
    }

    public static StatusEnum toEnum(String status) {
        StatusEnum statusEnum = null;
        if (StatusEnum.NEW.toString().equals(status)) {
            statusEnum = StatusEnum.NEW;
        }
        if (StatusEnum.CONFIRMED.toString().equals(status)) {
            statusEnum = StatusEnum.CONFIRMED;
        }
        if (StatusEnum.INVALID.toString().equals(status)) {
            statusEnum = StatusEnum.INVALID;
        }
        if (StatusEnum.IN_PROGRESS.toString().equals(status)) {
            statusEnum = StatusEnum.IN_PROGRESS;
        }
        if (StatusEnum.FIXED.toString().equals(status)) {
            statusEnum = StatusEnum.FIXED;
        }
        return statusEnum;
    }

    public static void setIdMap(Map<String, Integer> idMap) {
        StatusEnum.idMap = idMap;
    }

    @Override
    public String toString() {
        return str;
    }

    public int toID() {
        return idMap.get(str);
    }
}
