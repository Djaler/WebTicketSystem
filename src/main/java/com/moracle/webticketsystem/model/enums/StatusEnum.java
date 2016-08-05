package com.moracle.webticketsystem.model.enums;

/**
 * Created by djaler on 05.08.16.
 */
public enum StatusEnum {
    NEW("New"), CONFIRMED("Confirmed"), INVALID("Invalid"), IN_PROGRESS("In progress"), FIXED("Fixed");
    private static final String[] statuses;

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

    @Override
    public String toString() {
        return str;
    }
}
