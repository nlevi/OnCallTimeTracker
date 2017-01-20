package com.nl.tracker.service;

import com.nl.tracker.dao.UserProfileDAO;
import com.nl.tracker.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by levin1 on 1/13/2017.
 */
@Service("userProfileService")
@Transactional
public class UserProfileServiceImpl implements UserProfileService{

    @Autowired
    UserProfileDAO userProfileDAO;

    public UserProfile findById(int id) {
        return userProfileDAO.findById(id);
    }

    public UserProfile findByType(String type){
        return userProfileDAO.findByType(type);
    }

    public List<UserProfile> findAll() {
        return userProfileDAO.findAll();
    }
}
