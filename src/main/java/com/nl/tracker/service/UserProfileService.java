package com.nl.tracker.service;

import com.nl.tracker.model.UserProfile;

import java.util.List;

/**
 * Created by levin1 on 1/13/2017.
 */
public interface UserProfileService {
    UserProfile findById(int id);

    UserProfile findByType(String type);

    List<UserProfile> findAll();
}
