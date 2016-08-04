package com.moracle.webticketsystem.model.enums;

/**
 * Created by dmitry on 8/4/2016.
 */
public enum RoleEnum {
    USER("User"), PROGRAMMER("Programmer"), ADMIN("Admin");
    private final String str;
    private static final String[] roles;
    static {
        roles = new String[3];
        roles[0] = RoleEnum.USER.toString();
        roles[1] = RoleEnum.PROGRAMMER.toString();
        roles[2] = RoleEnum.ADMIN.toString();
    }

    RoleEnum(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return str;
    }

    public static String[] getRoles(){
        return roles;
    }
}
