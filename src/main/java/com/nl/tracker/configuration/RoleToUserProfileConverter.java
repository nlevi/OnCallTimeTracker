package com.nl.tracker.configuration;

import com.nl.tracker.model.UserProfile;
import com.nl.tracker.service.UserProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created by levin1 on 1/12/2017.
 */
@Component
public class RoleToUserProfileConverter implements Converter<Object, Set<UserProfile>> {

    static final Logger logger = LoggerFactory.getLogger(RoleToUserProfileConverter.class);

    @Autowired
    UserProfileService userProfileService;


    public Set<UserProfile> convert(Object profileId) {

        Integer id = Integer.parseInt((String) profileId);
        UserProfile profile = userProfileService.findById(id);
        logger.info("Profile : {}", profile);
        Set<UserProfile> profiles = null;
        profiles.add(profile);
        return profiles;
    }
}
