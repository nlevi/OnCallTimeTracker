package com.nl.tracker.dao;

import com.nl.tracker.model.UserProfile;

import java.util.List;

/**
 * Created by levin1 on 1/13/2017.
 */
public interface UserProfileDAO {
    List<UserProfile> findAll();
    UserProfile findByType(String type);
    UserProfile findById(int id);
}
