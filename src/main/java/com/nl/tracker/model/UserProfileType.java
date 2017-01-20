package com.nl.tracker.model;

/**
 * Created by levin1 on 1/11/2017.
 */

public enum UserProfileType {
    USER("TSE"),
    MANAGER("MANAGER"),
    ADMIN("ADMIN");

    String userProfileType;

    private UserProfileType(String userProfileType){
        this.userProfileType = userProfileType;
    }

    public String getUserProfileType(){
        return userProfileType;
    }

}
