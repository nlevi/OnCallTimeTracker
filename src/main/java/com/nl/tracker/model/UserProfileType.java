package com.nl.tracker.model;

/**
 * Created by levin1 on 1/11/2017.
 */

public enum UserProfileType {
    TSE("TSE"),
    MANAGER("MANAGER"),
    ADMIN("ADMIN"),
    WFM("WFM");

    String userProfileType;

    private UserProfileType(String userProfileType) {
        this.userProfileType = userProfileType;
    }

    public String getUserProfileType() {
        return userProfileType;
    }

}
