package com.moracle.webticketsystem.model.enums;

import java.util.Map;

/**
 * Created by dmitry on 8/4/2016.
 */
public enum RoleEnum {
    USER("User"), PROGRAMMER("Programmer"), ADMIN("Admin");
    private static final String[] roles;
    private static Map<String, Integer> idMap;

    static {
        roles = new String[3];
        roles[0] = RoleEnum.USER.toString();
        roles[1] = RoleEnum.PROGRAMMER.toString();
        roles[2] = RoleEnum.ADMIN.toString();
    }

    private final String str;

    RoleEnum(String str) {
        this.str = str;
    }

    public static String[] getRoles() {
        return roles;
    }

    public static RoleEnum toEnum(String priority) {
        RoleEnum roleEnum = null;
        if (RoleEnum.USER.toString().equals(priority)) {
            roleEnum = RoleEnum.USER;
        }
        if (RoleEnum.PROGRAMMER.toString().equals(priority)) {
            roleEnum = RoleEnum.PROGRAMMER;
        }
        if (RoleEnum.ADMIN.toString().equals(priority)) {
            roleEnum = RoleEnum.ADMIN;
        }
        return roleEnum;
    }

    public static void setIdMap(Map<String, Integer> idMap) {
        RoleEnum.idMap = idMap;
    }

    @Override
    public String toString() {
        return str;
    }

    public int toID() {
        return idMap.get(str);
    }
}
